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

	@Override
	public boolean hasProperty(Object object, String property, IEolContext context) {
		try (ObjectMethod om = getMethodFor(object, property, context)) {
			if (om.getMethod() != null) {
				return true;
			}
		}
		// Can be a stereotype tag?
		IRPModelElement element = (IRPModelElement) object;
		return element.getTag(property) != null;
	}
	
	@Override
	public Object invoke(
		Object target,
		String property,
		IEolContext context) throws EolRuntimeException {
		if (!(target instanceof IRPModelElement)) {
			throw new IllegalArgumentException("Can't get ptoperty of none IRPModelElement");
		}
		try (ObjectMethod objectMethod = getMethodFor(target, property, context)) {
			if (objectMethod.getMethod() != null) {
				ModuleElement ast = context.getExecutorFactory().getActiveModuleElement();
				Object value = null;
				try {
					value = objectMethod.execute(ast, context);
				} catch (RhapsodyRuntimeException e) {
					throw new EolRuntimeException("Error invoking Rhapsody API.", e);
				}
				if (value != null) {
					if (value instanceof IRPCollection) {
						return ((IRPCollection)value).toList();
					}
					return value;
				}
			}
		}
		// Can be a stereotype tag?
		IRPModelElement element = (IRPModelElement) target;
		IRPTag tag = element.getTag(property);
		if (tag == null) {
			LOG.error("Could not find a property or tag with name {}", property);
			throw new EolIllegalPropertyException(
					target,
					property,
					context.getExecutorFactory().getActiveModuleElement(),
					context);
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
				throw new IllegalStateException("Unknown value specification type: " + valSpec.getClass().getName());
			}
		}
		if (result.size() == 1) {
			return result.get(0);
		} else {
			return result;
		}
		
	}
	
	public boolean knowsAboutProperty(IRPModelElement instance, String property) {
		// Look for a getX() method
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
		if (om != null) {
			return true;
		}
		IRPTag tag = instance.getTag(property);
		return tag != null;
	}
	
	private static final Logger LOG = LogManager.getLogger(RhapsodyPropertyGetter.class);

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
	 * @param object
	 * @param property
	 * @param context
	 * @return the {@link ObjectMethod} to call
	 */
	private ObjectMethod getMethodFor(Object object, String property, IEolContext context) {
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

}
