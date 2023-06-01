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

import java.util.Collection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.epsilon.eol.exceptions.EolIllegalPropertyException;
import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
import org.eclipse.epsilon.eol.execute.context.IEolContext;
import org.eclipse.epsilon.eol.execute.introspection.IPropertySetter;
import org.eclipse.epsilon.eol.execute.introspection.java.JavaPropertySetter;
import org.eclipse.epsilon.eol.execute.introspection.java.ObjectMethod;

import com.telelogic.rhapsody.core.IRPModelElement;
import com.telelogic.rhapsody.core.IRPTag;

/**
 * This {@link IPropertySetter} implementation supports for Java property setter and a custom
 * implementation to support SysML Stereotype tags (as properties).
 * 
 * @author Horacio Hoyos Rodriguez
 *
 */
public class RhapsodyPropertySetter extends JavaPropertySetter {

	@Override
	public void invoke(
		Object target,
		String property,
		Object value,
		IEolContext context) throws EolRuntimeException {
		if (!(target instanceof IRPModelElement)) {
			throw new IllegalArgumentException("Can't set ptoperty of none IRPModelElement");
		}
		try (ObjectMethod objectMethod = getMethodFor(target, property, value, context)) {
			if (objectMethod.getMethod() != null) {
				objectMethod.execute(
						context.getExecutorFactory().getActiveModuleElement(),
						context,
						value);		
				return;
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
		element.setTagElementValue(tag, null);
		element.setTagValue(tag, null);
		if (value instanceof Collection<?>) {
			var colVal = (Collection<?>) value;
			colVal.forEach(v -> {
				if (v instanceof IRPModelElement) {
					tag.addElementDefaultValue((IRPModelElement) v);
				} else {
					tag.addStringDefaultValue(String.valueOf(v));
				}
			});
		} else {
			if (value instanceof IRPModelElement) {
				element.setTagElementValue(tag, (IRPModelElement) value);
			} else {
				element.setTagValue(tag, String.valueOf(value));
			}
		}	
	}
	
	private static final Logger LOG = LogManager.getLogger(RhapsodyPropertySetter.class);

}
