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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.eclipse.epsilon.common.util.StringProperties;
import org.eclipse.epsilon.eol.exceptions.models.EolModelLoadingException;
import org.eclipse.epsilon.eol.models.IModel;
import org.eclipse.epsilon.eol.models.Model;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import cas.mcmaster.epsilon.emc.RhapsodyModel;

/**
 * To run this tests you need a Rhapsody Installation on you machine
 * <ul>
 * 	<li> RHAPSODY_PATH: The full path to the Rhapsody installation, till the version folder.
 * </ul>
 * 
 * Getting elements by Stereotype is slow.
 * 
 * @author Horacio Hoyos Rodriguez
 *
 */
public class RhapsodyModelLoadingTests {
	
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
	void cant_load_model_without_installation_directory() {
		model = new RhapsodyModel();
		EolModelLoadingException thrown = Assertions.assertThrows(
				EolModelLoadingException.class,
				() -> {
					StringProperties properties = defaultProperties();
					properties.put(RhapsodyModel.PROPERTIES_PROJECT_PATH, "");
					model.load(properties);
				});
		assertTrue(thrown.getMessage().contains("No active project on Rhapsody"));
	}
	
	/**
	 * This test will prompt user interaction
	 */
	@Tag("prompt")
	@Test
	@Disabled
	void cant_load_project_from_invalid_path() {
		model = new RhapsodyModel();
		EolModelLoadingException thrown = Assertions.assertThrows(
				EolModelLoadingException.class,
				() -> {
					StringProperties properties = defaultProperties();
					properties.put(RhapsodyModel.PROPERTIES_PROJECT_PATH, "path/to/project.rpyx");
					model.load(properties);
				});
		assertTrue(thrown.getMessage().contains("Invalid project name and/or path"));
	}
	
	@Test
	void loads_project_from_path() {
		model = new RhapsodyModel();
		try {
			StringProperties properties = defaultProperties();
			model.load(properties);
		} catch (EolModelLoadingException e) {
			fail("Should not throw exception", e);
		}
	}
	
	@Test
	void name_is_default_if_not_provided() {
		model = new RhapsodyModel();
		try {
			model.load(defaultProperties());
		} catch (EolModelLoadingException e) {
			fail("Should not throw exception", e);
		}
		assertEquals("Rhapsody", model.getName());
	}
	
	@Test
	void loads_if_main_package_not_provided() {
		model = new RhapsodyModel();
		try {
			model.load(defaultProperties());
		} catch (EolModelLoadingException e) {
			fail("Should not throw exception", e);
		}
		assertEquals("Rhapsody", model.getName());
	}
	
	@Test
	void loads_if_main_package_exists() {
		model = new RhapsodyModel();
		try {
			StringProperties properties = defaultProperties();
			properties.put(RhapsodyModel.PROPERTIES_MAIN_PACKAGE_NAME, "RoadMonitoringPkg");
			model.load(properties);
		} catch (EolModelLoadingException e) {
			fail("Should not throw exception", e);
		}
	}
	
	@Test
	void cant_load_if_main_doesnt_package_exists() {
		model = new RhapsodyModel();
		EolModelLoadingException thrown = Assertions.assertThrows(
				EolModelLoadingException.class,
				() -> {
					StringProperties properties = defaultProperties();
					properties.put(RhapsodyModel.PROPERTIES_MAIN_PACKAGE_NAME, "UnkownPkg");
					model.load(properties);
				});
		assertTrue(thrown.getMessage().contains("A package with name UnkownPkg could not be found"));
	}
	
	@Test
	void aliases_match_provided_ones( ) {
		model = new RhapsodyModel();
		try {
			StringProperties properties = defaultProperties();
			properties.put(Model.PROPERTY_ALIASES, "sysml,source");
			model.load(properties);
		} catch (EolModelLoadingException e) {
			fail("Should not throw exception", e);
		}
		List<String> aliases = model.getAliases();
		assertEquals(2, aliases.size(), "Should have two aliases");
		assertTrue(aliases.contains("sysml"), "Should have 'sysml' alias");
		assertTrue(aliases.contains("source"), "Should have 'source' alias");
	}
	

	private IModel model;
	
	private StringProperties defaultProperties() {
		StringProperties properties = new StringProperties();
		properties.put(RhapsodyModel.PROPERTIES_INSTALLATION_DIRECTORY, System.getenv("RHAPSODY_PATH"));
		properties.put(RhapsodyModel.PROPERTIES_PROJECT_PATH, "resources/TollRoad.rpyx");
		return properties;
	}
	
}
