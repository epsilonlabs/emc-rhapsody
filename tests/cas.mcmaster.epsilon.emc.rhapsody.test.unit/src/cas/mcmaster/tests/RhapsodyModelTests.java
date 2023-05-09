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
package cas.mcmaster.tests;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.eclipse.epsilon.common.util.StringProperties;
import org.eclipse.epsilon.eol.exceptions.models.EolModelLoadingException;
import org.eclipse.epsilon.eol.models.IModel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cas.mcmaster.epsilon.emc.RhapsodyModel;

public class RhapsodyModelTests {
	
	@AfterEach
	void close() {
		if(this.model != null) {
			this.model.dispose();
		}
	}
	
	@Test
	void cant_load_model_without_properties() {
		model = new RhapsodyModel();
		EolModelLoadingException thrown = Assertions.assertThrows(
				EolModelLoadingException.class,
				() -> {
					
					model.load();
				});
		assertTrue(thrown.getMessage().contains("Rhapsody models can't be loaded without a properties file"));
	}
	
	@Test
	void cant_load_model_without_installation_directory_property() {
		model = new RhapsodyModel();
		EolModelLoadingException thrown = Assertions.assertThrows(
				EolModelLoadingException.class,
				() -> {
					model.load(new StringProperties());
				});
		assertTrue(thrown.getMessage().contains("No active project on Rhapsody"));
	}
	
	/**
	 * This test will prompt user interaction
	 */
	@Test
	void cant_load_project_from_invalid_path() {
		model = new RhapsodyModel();
		EolModelLoadingException thrown = Assertions.assertThrows(
				EolModelLoadingException.class,
				() -> {
					StringProperties properties = new StringProperties();
					properties.put(RhapsodyModel.PROPERTIES_INSTALLATION_DIRECTORY, "path/to/rhapsody");
					properties.put(RhapsodyModel.PROPERTIES_PROJECT_PATH, "path/to/project.rpyx");
					model.load(properties);
				});
		assertTrue(thrown.getMessage().contains("Invalid project name and/or path"));
	}
	
	
	@Test
	void loads_project_from_path() {
		model = new RhapsodyModel();
		try {
			StringProperties properties = new StringProperties();
			properties.put(RhapsodyModel.PROPERTIES_INSTALLATION_DIRECTORY, "path/to/rhapsody");
			properties.put(RhapsodyModel.PROPERTIES_PROJECT_PATH, System.getenv("PRJ"));
			model.load(properties);
		} catch (EolModelLoadingException e) {
			fail("Should not throw exception", e);
		}
	}
	
	
	private IModel model;
	
}
