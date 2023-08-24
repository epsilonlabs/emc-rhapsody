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

import java.util.Objects;

/**
 * Key for the property caches.
 * 
 * @author Horacio Hoyos Rodriguez
 */
public class IRPKey {
	
	public IRPKey(String gUID, String property) {
		super();
		GUID = gUID;
		this.property = property;
	}

	@Override
	public int hashCode() {
		return Objects.hash(GUID, property);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IRPKey other = (IRPKey) obj;
		return Objects.equals(GUID, other.GUID) && Objects.equals(property, other.property);
	}
	
	
	private final String GUID;
	private final String property;
	

}
