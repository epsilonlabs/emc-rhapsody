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
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Iterator;
import java.util.List;

import org.eclipse.epsilon.common.util.StringProperties;
import org.eclipse.epsilon.eol.exceptions.models.EolEnumerationValueNotFoundException;
import org.eclipse.epsilon.eol.exceptions.models.EolModelElementTypeNotFoundException;
import org.eclipse.epsilon.eol.exceptions.models.EolModelLoadingException;
import org.eclipse.epsilon.eol.models.IModel;
import org.eclipse.epsilon.eol.models.Model;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.telelogic.rhapsody.core.IRPOperation;

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
	void name_is_projects_name() {
		model = new RhapsodyModel();
		try {
			model.load(defaultProperties());
		} catch (EolModelLoadingException e) {
			fail("Should not throw exception", e);
		}
		assertEquals("TollRoad", model.getName());
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
	
	@Test
	void enumeration_value_fails_for_invalid_name() {
		model = new RhapsodyModel();
		try {
			model.load(defaultProperties());
		} catch (EolModelLoadingException e) {
			fail("Should not throw exception", e);
		}
		EolEnumerationValueNotFoundException thrown = Assertions.assertThrows(
				EolEnumerationValueNotFoundException.class,
				() -> {
					model.getEnumerationValue("TestEnumWrong", "SOME_VALUE");
				});
		assertTrue(thrown.getMessage().contains("Cannot find enumeration literal TestEnumWrong#SOME_VALUE in model TollRoad"));
	}
	
	@Test
	void enumeration_value_fails_for_invalid_literal() {
		model = new RhapsodyModel();
		try {
			model.load(defaultProperties());
		} catch (EolModelLoadingException e) {
			fail("Should not throw exception", e);
		}
		EolEnumerationValueNotFoundException thrown = Assertions.assertThrows(
				EolEnumerationValueNotFoundException.class,
				() -> {
					model.getEnumerationValue("TestEnum", "SOME_VALUE");
				});
		assertTrue(thrown.getMessage().contains("Cannot find enumeration literal TestEnum#SOME_VALUE in model TollRoad"));
	}
	
	@Test
	void get_enumeration_value_matches() {
		model = new RhapsodyModel();
		try {
			model.load(defaultProperties());
			var value = (String) model.getEnumerationValue("TestEnum", "TEST_1");
			assertEquals("1", value);
			value = (String) model.getEnumerationValue("TestEnum", "TEST_2");
			assertEquals("2", value);
		} catch (EolModelLoadingException | EolEnumerationValueNotFoundException e) {
			fail("Should not throw exception", e);
		}
	}
	
	@Test
	void get_all_by_type_fails_if_unknown_stereotype_or_type() {
		model = new RhapsodyModel();
		try {
			model.load(defaultProperties());
		} catch (EolModelLoadingException e) {
			fail("Should not throw exception", e);
		}
		EolModelElementTypeNotFoundException thrown = assertThrows(
				EolModelElementTypeNotFoundException.class,
				() -> model.getAllOfType("car"));
		assertTrue(thrown.getMessage().contains( "Cannot find meta-class 'car' in model 'TollRoad'"));
	}
	
	@Test
	void get_all_by_type_for_type() {
		model = new RhapsodyModel();
		try {
			model.load(defaultProperties());
			var packages = model.getAllOfType("Package");
			assertEquals(14, packages.size());
		} catch (EolModelLoadingException | EolModelElementTypeNotFoundException e) {
			fail("Should not throw exception", e);
		}
	}
	
	@Test
	void get_all_by_type_for_stereotype() {
		model = new RhapsodyModel();
		try {
			model.load(defaultProperties());
			var blocks = model.getAllOfType("Block");
			assertEquals(5, blocks.size());
		} catch (EolModelLoadingException | EolModelElementTypeNotFoundException e) {
			fail("Should not throw exception", e);
		}
	}
	
	@Test
	void get_all_by_kind_fails_if_unknown_stereotype_or_type() {
		model = new RhapsodyModel();
		try {
			model.load(defaultProperties());
		} catch (EolModelLoadingException e) {
			fail("Should not throw exception", e);
		}
		EolModelElementTypeNotFoundException thrown = assertThrows(
				EolModelElementTypeNotFoundException.class,
				() -> model.getAllOfKind("car"));
		assertTrue(thrown.getMessage().contains( "Cannot find meta-class 'car' in model 'TollRoad'"));
	}
	
	@Test
	void get_all_by_kind_for_type() {
		model = new RhapsodyModel();
		try {
			model.load(defaultProperties());
			var packages = model.getAllOfKind("Package");
			assertEquals(14, packages.size());
		} catch (EolModelLoadingException | EolModelElementTypeNotFoundException e) {
			fail("Should not throw exception", e);
		}
	}
	
	@Test
	void get_all_by_kind_for_stereotype() {
		model = new RhapsodyModel();
		try {
			model.load(defaultProperties());
			var blocks = model.getAllOfKind("Block");
			assertEquals(5, blocks.size());
		} catch (EolModelLoadingException | EolModelElementTypeNotFoundException e) {
			fail("Should not throw exception", e);
		}
	}
	

	private IModel model;
	
	private StringProperties defaultProperties() {
		StringProperties properties = new StringProperties();
		properties.put(RhapsodyModel.PROPERTIES_INSTALLATION_DIRECTORY, System.getenv("RHAPSODY_PATH"));
		//properties.put(RhapsodyModel.PROPERTIES_PROJECT_PATH, System.getenv("PRJ"));
		properties.put(RhapsodyModel.PROPERTIES_PROJECT_PATH, "resources/TollRoad.rpyx");
		return properties;
	}
	
}
