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

import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;

/**
 * Store the value of a property that might have thrown an exception during evaluation.
 * 
 * @author Horacio Hoyos Rodriguez
 */
public class PropertyValue {
		
	public PropertyValue(EolRuntimeException error) {
		this(null, error);
	}
	public PropertyValue(Object value) {
		this(value, null);
	}
	
	Object get() throws EolRuntimeException {
		if (this.error != null) {
			throw this.error;
		}
		return this.value;
	}
	
	private PropertyValue(Object value, EolRuntimeException error) {
		super();
		this.value = value;
		this.error = error;
	}
	private final Object value;
	private final EolRuntimeException error;
}