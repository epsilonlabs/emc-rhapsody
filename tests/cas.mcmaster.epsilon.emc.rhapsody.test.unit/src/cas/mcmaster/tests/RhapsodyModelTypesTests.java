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
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

import org.eclipse.epsilon.common.util.StringProperties;
import org.eclipse.epsilon.eol.exceptions.models.EolEnumerationValueNotFoundException;
import org.eclipse.epsilon.eol.exceptions.models.EolModelElementTypeNotFoundException;
import org.eclipse.epsilon.eol.exceptions.models.EolModelLoadingException;
import org.eclipse.epsilon.eol.exceptions.models.EolNotInstantiableModelElementTypeException;
import org.eclipse.epsilon.eol.models.IModel;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.telelogic.rhapsody.core.IRPPackage;

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
public class RhapsodyModelTypesTests {
	
	
	@BeforeAll
	static void load() throws EolModelLoadingException {
		underTest = new RhapsodyModel();
		underTest.load(defaultProperties());
	}
	
	@AfterAll
	static void unload() {
		if(underTest != null) {
			underTest.dispose();
		}
	}
	
	@Test
	void enumeration_value_fails_for_invalid_name() {
		EolEnumerationValueNotFoundException thrown = Assertions.assertThrows(
				EolEnumerationValueNotFoundException.class,
				() -> {
					underTest.getEnumerationValue("TestEnumWrong", "SOME_VALUE");
				});
		assertTrue(thrown.getMessage().contains("Cannot find enumeration literal TestEnumWrong#SOME_VALUE in model"));
	}
	
	@Test
	void enumeration_value_fails_for_invalid_literal() {
		EolEnumerationValueNotFoundException thrown = Assertions.assertThrows(
				EolEnumerationValueNotFoundException.class,
				() -> {
					underTest.getEnumerationValue("TestEnum", "SOME_VALUE");
				});
		assertTrue(thrown.getMessage().contains("Cannot find enumeration literal TestEnum#SOME_VALUE in model"));
	}
	
	@Test
	void get_enumeration_value_matches() {
		try {
			var value = (String) underTest.getEnumerationValue("TestEnum", "TEST_1");
			assertEquals("1", value);
			value = (String) underTest.getEnumerationValue("TestEnum", "TEST_2");
			assertEquals("2", value);
		} catch (EolEnumerationValueNotFoundException e) {
			fail("Should not throw exception", e);
		}
	}
	
	@Test
	void get_all_by_type_fails_if_unknown_stereotype_or_type() {
		EolModelElementTypeNotFoundException thrown = assertThrows(
				EolModelElementTypeNotFoundException.class,
				() -> underTest.getAllOfType("car"));
		assertTrue(thrown.getMessage().contains( "Cannot find meta-class 'car' in model"));
	}
	
	@Test
	void get_all_by_type_for_type() {
		try {
			var packages = underTest.getAllOfType("Package");
			assertEquals(14, packages.size());
		} catch (EolModelElementTypeNotFoundException e) {
			fail("Should not throw exception", e);
		}
	}
	
	@Test
	void get_all_by_type_for_stereotype() {
		try {
			var blocks = underTest.getAllOfType("Block");
			assertEquals(5, blocks.size());
		} catch (EolModelElementTypeNotFoundException e) {
			fail("Should not throw exception", e);
		}
	}
	
	@Test
	void get_all_by_kind_fails_if_unknown_stereotype_or_type() {
		EolModelElementTypeNotFoundException thrown = assertThrows(
				EolModelElementTypeNotFoundException.class,
				() -> underTest.getAllOfKind("car"));
		assertTrue(thrown.getMessage().contains( "Cannot find meta-class 'car' in model"));
	}
	
	@Test
	void get_all_by_kind_for_type() {
		try {
			var packages = underTest.getAllOfKind("Package");
			assertEquals(14, packages.size());
		} catch (EolModelElementTypeNotFoundException e) {
			fail("Should not throw exception", e);
		}
	}
	
	@Test
	void get_all_by_kind_for_stereotype() {
		try {
			var blocks = underTest.getAllOfKind("Block");
			assertEquals(5, blocks.size());
		} catch (EolModelElementTypeNotFoundException e) {
			fail("Should not throw exception", e);
		}
	}
	
	@Test
	void has_type_is_false_for_unknown( ) {
		assertFalse(underTest.hasType("car"));
	}
	
	@Test
	void has_type_is_true_for_class( ) {
		assertTrue(underTest.hasType("Operation"));
	}
	
	@Test
	void has_type_is_true_for_stereotype( ) {
		assertTrue(underTest.hasType("Block"));
	}

	@Test
	void is_instantiable_is_false_for_unknown() {
		assertFalse(underTest.isInstantiable("car"));
	}
	
	@Test
	void is_instantiable_is_true_for_known_class() {
		assertTrue(underTest.isInstantiable("Class"));
	}
	
	@Test
	void is_instantiable_is_false_for_known_stereotype() {
		assertFalse(underTest.isInstantiable("Block"));
	}

	@Test
	void create_instance_needs_paramteres() {
		UnsupportedOperationException thrown = assertThrows(
				UnsupportedOperationException.class, () -> underTest.createInstance("Class"));
		assertTrue(thrown.getMessage().contains("To create an instance use"));
	}
	
//	@Test
//	void create_instance_valid_type_wrong_package() throws EolModelElementTypeNotFoundException {
//		try {
//			Object newElement = underTest.createInstance("Package", Arrays.asList("RoadMonitoringPkg", "RoadMonitoringPkg"));
//		} catch (EolModelElementTypeNotFoundException | EolNotInstantiableModelElementTypeException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	
	
	static private IModel underTest;
	
	static private StringProperties defaultProperties() {
		StringProperties properties = new StringProperties();
		properties.put(RhapsodyModel.PROPERTIES_INSTALLATION_DIRECTORY, System.getenv("RHAPSODY_PATH"));
		properties.put(RhapsodyModel.PROPERTIES_PROJECT_PATH, "resources/TollRoad.rpyx");
		return properties;
	}
	
}
