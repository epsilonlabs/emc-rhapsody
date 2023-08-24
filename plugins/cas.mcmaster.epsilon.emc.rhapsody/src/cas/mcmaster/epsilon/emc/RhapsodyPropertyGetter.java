/********************************************************************************
 * Copyright (c) 2023 McMaster University
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0, or the Apache License, Version 2.0
 * which is available at https://www.apache.org/licenses/LICENSE-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0 OR Apache-2.0
 ********************************************************************************/
package cas.mcmaster.epsilon.emc;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.epsilon.common.module.ModuleElement;
import org.eclipse.epsilon.eol.exceptions.EolIllegalPropertyException;
import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
import org.eclipse.epsilon.eol.execute.context.IEolContext;
import org.eclipse.epsilon.eol.execute.introspection.IPropertyGetter;
import org.eclipse.epsilon.eol.execute.introspection.java.ObjectMethod;
import org.eclipse.epsilon.eol.execute.operations.contributors.OperationContributorRegistry;
import org.eclipse.epsilon.eol.util.ReflectionUtil;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.telelogic.rhapsody.core.IRPCollection;
import com.telelogic.rhapsody.core.IRPInstanceValue;
import com.telelogic.rhapsody.core.IRPLiteralSpecification;
import com.telelogic.rhapsody.core.IRPModelElement;
import com.telelogic.rhapsody.core.IRPTag;
import com.telelogic.rhapsody.core.RhapsodyRuntimeException;

/**
 * This {@link IPropertyGetter} implementation supports for Java property getter and a custom
 * implementation to support SysML Stereotype tags (as properties).
 * 
 * @author Horacio Hoyos Rodriguez
 *
 */
public class RhapsodyPropertyGetter implements IPropertyGetter {

	public RhapsodyPropertyGetter(Cache<IRPKey, PropertyValue> cache) {
		this.cache = cache;
		this.hasCache = Caffeine.newBuilder()
				.expireAfterWrite(10, TimeUnit.MINUTES)
			    .maximumSize(10_000)
			    .build();
	}

	@Override
	public boolean hasProperty(Object object, String property, IEolContext context) {
		if (!(object instanceof IRPModelElement)) {
			return false;
		}
		IRPModelElement element = (IRPModelElement) object;
		return this.hasCache.get(new IRPKey(
				element.getGUID(), property),
				k -> {
					try (ObjectMethod om = getMethodFor(element, property, context)) {
							if (om.getMethod() != null) {
								return true;
							}
						}
						// Can be a stereotype tag?
						return element.getTag(property) != null;
					});
	}
	
	@Override
	public Object invoke(
		Object target,
		String property,
		IEolContext context) throws EolRuntimeException {
		if (!(target instanceof IRPModelElement)) {
			throw new IllegalArgumentException("Can't get ptoperty of none IRPModelElement");
		}
		IRPModelElement element = (IRPModelElement) target;
		LOG.info("Getting property {} from {}", property, element.getGUID());
		PropertyValue value =  this.cache.get(
				new IRPKey(element.getGUID(), property),
				k -> {
					return computeValue(property, context, element);
				});
		return value.get();
		
	}	
	
	public boolean knowsAboutProperty(IRPModelElement instance, String property) {
		Method om = nativeMethod(instance, property);
		if (om != null) {
			return true;
		}
		IRPTag tag = instance.getTag(property);
		return tag != null;
	}
	
	private static final Logger LOG = LogManager.getLogger(RhapsodyPropertyGetter.class);
	private final Cache<IRPKey, PropertyValue> cache;
	private final Cache<IRPKey, Boolean> hasCache;

	private PropertyValue computeValue(String property, IEolContext context, IRPModelElement element) {
		LOG.info("Property {} value not cached, computing", property);
		try (ObjectMethod objectMethod = getMethodFor(element, property, context)) {
			if (objectMethod.getMethod() != null) {
				LOG.info("Execuing method {} to get property {}.",
						objectMethod.getMethod().getName(),
						property);
				ModuleElement ast = context.getExecutorFactory().getActiveModuleElement();
				Object value = null;
				try {
					value = objectMethod.execute(ast, context);
				} catch (RhapsodyRuntimeException e) {
					return new PropertyValue(new EolRuntimeException("Error invoking Rhapsody API.", e));
				} catch (EolRuntimeException e) {
					return new PropertyValue(e);
				}
				if (value != null) {
					if (value instanceof IRPCollection) {
						return new PropertyValue(((IRPCollection)value).toList());
					}
					return new PropertyValue(value);
				}
			}
		}
		// Can be a stereotype tag?
		LOG.info("Could not find a property with name {}, looking for tag.", property);
		IRPTag tag = element.getTag(property);
		if (tag == null) {
			LOG.error("Could not find a property or tag with name {}", property);
			return new PropertyValue(new EolIllegalPropertyException(
					element,
					property,
					context.getExecutorFactory().getActiveModuleElement(),
					context));
		}
		var valSpecs = tag.getValueSpecifications();
		var looper = valSpecs.toList().iterator();
		var result = new ArrayList<Object>();
		while(looper.hasNext()) {
			var valSpec = looper.next();
			if (valSpec instanceof IRPInstanceValue) {
				result.add(((IRPInstanceValue)valSpec).getValue());
			} else if (valSpec instanceof IRPLiteralSpecification) {
				result.add( getLiteralSpecAsPrimitive(tag, (IRPLiteralSpecification) valSpec));
			} else {
				new PropertyValue(new EolRuntimeException("Unknown value specification type: " + valSpec.getClass().getName()));				
			}
		}
		if (result.isEmpty()) {
			return new PropertyValue(tag.getValue());
		}
		if (result.size() == 1) {
			return new PropertyValue(result.get(0));
		} else {
			return new PropertyValue(result);
		}
	}
	/**
	 * Gets the {@link IRPLiteralSpecification} value and tries to cast it to the correct Java
	 * primitive.
	 * 
	 * @param tag
	 * @param valSpec
	 * @return the primitive value, or the string representation if casting failed.
	 */
	private Object getLiteralSpecAsPrimitive(IRPTag tag, IRPLiteralSpecification valSpec) {
		var valSpecVal = valSpec.getValue();
		var tagTypeName = tag.getType().getName();
		Object result = null;
		try {
			switch(tagTypeName) {
				case "RhpReal":
					result = Float.parseFloat(valSpecVal);
					break;
				case "RhpInteger":
					result = Integer.parseInt(valSpecVal);
					break;
				case "RhpBoolean":
					result = Boolean.parseBoolean(valSpecVal);
					break;
				case "RhpString":
					result = valSpecVal;
					break;
				default:
					LOG.warn("Primtive value not considered: {}", tagTypeName);
					result = valSpecVal;
			}
		} catch (NumberFormatException e1) {
			LOG.warn("Unable to parse the tag value as specified type: {} as {}. Will return string value.", valSpecVal, tagTypeName);
			result = valSpecVal;
		}
		return result;
	}
	
	/**
	 * Use the {@link OperationContributorRegistry} to find a plausible java method to get the 
	 * property.
	 * <p>
	 * A matching Java method is searched by name with the following combinations, in order of
	 * priority:
	 * <ul>
	 * 	<li> X()
	 *  <li> getX()
	 *  <li> isX()
	 *  <li> hasX()
	 * </ul> 
	 * As a result, for example in the case of IRPModelElement, the expression
	 * <code>element.nestedElements</code> will always result in a call to <i>getNestedElements</i>
	 * as opposed to <i>hasNestedElements</i>. To get the hasNestedElements value, you will
	 * need to use <code>element.hasNestedElements</code>.
	 * 
	 * If a Java (native) method can't be found, the Epsilon OperationContributorRegistry is used.
	 * 
	 * @param object
	 * @param property
	 * @param context
	 * @return the {@link ObjectMethod} to call
	 */
	private ObjectMethod getMethodFor(IRPModelElement object, String property, IEolContext context) {
		Method m = nativeMethod(object, property);
		if (m != null) {
			return new ObjectMethod(object, m);
		}
		LOG.info("Looking for method for property {} in the OperationContributorRegistry", property);
		OperationContributorRegistry registry = context.getOperationContributorRegistry();
		
		// Look for an X() method
		ObjectMethod om = registry.findContributedMethodForEvaluatedParameters(object, property, new Object[]{}, context);
		if (om != null) return om;
				
		// Look for a getX() method
		om = registry.findContributedMethodForEvaluatedParameters(object, "get" + property, new Object[]{}, context);
		if (om != null) return om;

		// Look for an isX() method
		om = registry.findContributedMethodForEvaluatedParameters(object, "is" + property, new Object[]{}, context);
		if (om != null) return om;
		
		// Look for an hasX() method
		om = registry.findContributedMethodForEvaluatedParameters(object, "has" + property, new Object[]{}, context);
		if (om != null) return om;
		
		return new ObjectMethod(object);
	}
	
	
	private Method nativeMethod(IRPModelElement instance, String property) {
		LOG.info("Looking for native method for property {}", property);
		Method om = null;
		try {
			om = ReflectionUtil.getMethodFor(instance, "get" + property, new Object[]{}, true, true);
			if (om == null) {
				// Look for an isX() method
				om = ReflectionUtil.getMethodFor(instance, "is" + property, new Object[]{}, true, true);
			}
			if (om == null) {
				// Look for a hasX() method
				om = ReflectionUtil.getMethodFor(instance, "has" + property, new Object[]{}, true, true);
			}
		} catch (SecurityException e) {
			// We can't determine if the method exists
			LOG.error("Unable to get property methods", e);
		}
		return om;
	}

}
