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
package cas.mcmaster.epsilon.emc.tests;

import static org.junit.jupiter.api.Assertions.fail;

import org.eclipse.epsilon.common.util.StringProperties;
import org.eclipse.epsilon.eol.exceptions.models.EolModelLoadingException;
import org.eclipse.epsilon.eol.models.IModel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import cas.mcmaster.epsilon.emc.RhapsodyModel;

/**
 * To run this tests, Rhapsody needs to be opened with a loaded project.
 * @author Horacio Hoyos Rodriguez
 *
 */
public class RhapsodyModelTestsActivePrj {
	
	@AfterEach
	void close() {
		if(this.model != null) {
			this.model.dispose();
		}
	}
	
	@Test
	void loads_active_project_if_path_missing() {
		model = new RhapsodyModel();
		try {
			StringProperties properties = new StringProperties();
			properties.put(RhapsodyModel.PROPERTY_INSTALLATION_DIRECTORY, "path/to/rhapsody");
			model.load(properties);
		} catch (EolModelLoadingException e) {
			fail("Should not throw exception", e);
		}
	}
	
	
	private IModel model;
	
}
