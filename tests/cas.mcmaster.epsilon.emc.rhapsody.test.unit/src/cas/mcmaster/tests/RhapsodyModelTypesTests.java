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
import java.util.List;

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

import com.telelogic.rhapsody.core.IRPClass;
import com.telelogic.rhapsody.core.IRPModelElement;
import com.telelogic.rhapsody.core.IRPStereotype;

import cas.mcmaster.epsilon.emc.RhapsodyModel;

/**
 * To run this tests you need a Rhapsody Installation on you machine
 * <ul>
 * 	<li> RHAPSODY_PATH: The full path to the Rhapsody installation, till the version folder.
 * </ul>
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
	void get_all_by_type_fails_if_no_new_term_stereotype() {
		EolModelElementTypeNotFoundException thrown = assertThrows(
				EolModelElementTypeNotFoundException.class,
				() -> underTest.getAllOfType("Usage"));
		assertTrue(thrown.getMessage().contains( "Cannot find meta-class 'Usage' in model"));
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
	void get_all_by_type_for_new_term_stereotype() {
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
	void get_all_by_kind_fails_if_no_new_term_stereotype() {
		EolModelElementTypeNotFoundException thrown = assertThrows(
				EolModelElementTypeNotFoundException.class,
				() -> underTest.getAllOfKind("Usage"));
		assertTrue(thrown.getMessage().contains( "Cannot find meta-class 'Usage' in model"));
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
	void get_all_by_kind_for_new_term_stereotype() {
		try {
			var blocks = underTest.getAllOfKind("Block");
			assertEquals(5, blocks.size());
		} catch (EolModelElementTypeNotFoundException e) {
			fail("Should not throw exception", e);
		}
	}
	
	@Test
	void has_type_is_false_for_unknown_metaclass_or_stereotype( ) {
		assertFalse(underTest.hasType("car"));
	}

	@Test
	void has_type_is_false_for_no_new_term_stereotype( ) {
		assertFalse(underTest.hasType("Usage"));
	}
	
	@Test
	void has_type_is_true_for_class( ) {
		assertTrue(underTest.hasType("Operation"));
	}
	
	@Test
	void has_type_is_true_for_new_term_stereotype( ) {
		assertTrue(underTest.hasType("Block"));
	}

	@Test
	void is_instantiable_is_false_for_unknown_metaclass_or_stereotype() {
		assertFalse(underTest.isInstantiable("car"));
	}
	
	@Test
	void is_instantiable_is_false_for_no_new_term_stereotype() {
		assertFalse(underTest.isInstantiable("Usage"));
	}
	
	@Test
	void is_instantiable_is_true_for_known_class() {
		assertTrue(underTest.isInstantiable("Class"));
	}
	
	@Test
	void is_instantiable_is_true_for_new_term_stereotype() {
		assertTrue(underTest.isInstantiable("Block"));
	}
	
	@Test
	void create_instance_fails_for_unknown_type() {
		assertThrows(
				EolModelElementTypeNotFoundException.class, 
				() -> underTest.createInstance("Clazz"));
	}
	
	@Test
	void create_instance_fails_for_unknown_stereotype() {
		assertThrows(
				EolModelElementTypeNotFoundException.class, 
				() -> underTest.createInstance("Black"));
	}
	
	@Test
	void create_instance_fails_for_no_new_term_stereotype() {
		assertThrows(
				EolModelElementTypeNotFoundException.class, 
				() -> underTest.createInstance("Usage"));
	}

	@Test
	void create_metaclass_instance_without_paramteres() {
		try {
			Object newElement = underTest.createInstance("Class");
			assertTrue(newElement instanceof IRPModelElement);
			var element = (IRPModelElement) newElement;
			assertTrue(element.getName().contains("class"));
		} catch (EolModelElementTypeNotFoundException | EolNotInstantiableModelElementTypeException e) {
			fail("Should not throw exception", e);
		}
	}
	
	@Test
	void create_metaclass_instance_with_name() throws EolModelElementTypeNotFoundException {
		try {
			Object newElement = underTest.createInstance("Class", Arrays.asList("NewClass"));
			assertTrue(newElement instanceof IRPClass);
			var element = (IRPClass) newElement;
			assertEquals("NewClass", element.getName());
		} catch (EolModelElementTypeNotFoundException | EolNotInstantiableModelElementTypeException e) {
			fail("Should not throw exception", e);
		}
	}
	
	@Test
	void create_stereotype_instance_without_paramteres() {
		try {
			Object newElement = underTest.createInstance("Block");
			assertTrue(newElement instanceof IRPClass);
			var element = (IRPClass) newElement;
			assertTrue(element.getName().contains("class"));
			@SuppressWarnings("unchecked")
			List<IRPStereotype> stypes = ((IRPClass)newElement).getStereotypes().toList();
			assertTrue(stypes.stream().anyMatch(st -> st.getName().equals("Block")));
		} catch (EolModelElementTypeNotFoundException | EolNotInstantiableModelElementTypeException e) {
			fail("Should not throw exception", e);
		}
	}
	
	@Test
	void create_stereotype_instance_with_metaclass_paramteres() {
		try {
			Object newElement = underTest.createInstance("Realization", Arrays.asList("Class"));
			assertTrue(newElement instanceof IRPClass);
			var element = (IRPClass) newElement;
			assertTrue(element.getName().contains("class"));
			@SuppressWarnings("unchecked")
			List<IRPStereotype> stypes = ((IRPClass)newElement).getStereotypes().toList();
			assertTrue(stypes.stream().anyMatch(st -> st.getName().equals("Realization")));
		} catch (EolModelElementTypeNotFoundException | EolNotInstantiableModelElementTypeException e) {
			fail("Should not throw exception", e);
		}
	}
	
	@Test
	void create_stereotype_instance_with_metaclass_paramteres_fails_for_wrong_of() {
		assertThrows(
				EolNotInstantiableModelElementTypeException.class, 
				() -> underTest.createInstance("Realization", Arrays.asList("Package")));
	}
	
	@Test
	void create_stereotype_instance_with_metaclass_and_name_paramteres() {
		try {
			Object newElement = underTest.createInstance("Realization", Arrays.asList("Rel1", "Class"));
			assertTrue(newElement instanceof IRPClass);
			var element = (IRPClass) newElement;
			assertEquals("Rel1", element.getName());
			@SuppressWarnings("unchecked")
			List<IRPStereotype> stypes = ((IRPClass)newElement).getStereotypes().toList();
			assertTrue(stypes.stream().anyMatch(st -> st.getName().equals("Realization")));
		} catch (EolModelElementTypeNotFoundException | EolNotInstantiableModelElementTypeException e) {
			fail("Should not throw exception", e);
		}
	}
	
	
	@Test
	void get_type_of_fails_for_non_irp_model_element() throws EolModelElementTypeNotFoundException {
		assertThrows(
				IllegalArgumentException.class, 
				() ->  underTest.getTypeOf("String"));
	}
	
	@Test
	void get_type_of_matches_metaclass() throws EolModelElementTypeNotFoundException {
		var prj = (IRPModelElement) underTest.getElementById("GUID fe10d56a-baae-4305-9bd1-f8db5aff6190");
		assertEquals("IRPPackage", underTest.getTypeOf(prj));
	}
	
	@Test
	void get_type_of_matches_new_term_stereotype() throws EolModelElementTypeNotFoundException {
		var block = (IRPModelElement) underTest.getElementById("GUID 24885c0c-4ae9-4aa2-a03a-921ecade1f3c");
		assertEquals("IRPClass", underTest.getTypeOf(block));
	}
	
	@Test
	void get_typename_of_fails_for_non_irp_model_element() throws EolModelElementTypeNotFoundException {
		assertThrows(
				IllegalArgumentException.class, 
				() ->  underTest.getTypeNameOf("String"));
	}
	
	@Test
	void get_typename_of_matches_metaclass() throws EolModelElementTypeNotFoundException {
		var prj = (IRPModelElement) underTest.getElementById("GUID fe10d56a-baae-4305-9bd1-f8db5aff6190");
		assertEquals("Package", underTest.getTypeNameOf(prj));
	}
	
	@Test
	void get_typename_of_matches_new_term_stereotype() throws EolModelElementTypeNotFoundException {
		var block = (IRPModelElement) underTest.getElementById("GUID 24885c0c-4ae9-4aa2-a03a-921ecade1f3c");
		assertEquals("Block", underTest.getTypeNameOf(block));
	}
	
	@Test
	void get_fullyqualifiedtypename_of_fails_for_non_irp_model_element() throws EolModelElementTypeNotFoundException {
		assertThrows(
				IllegalArgumentException.class, 
				() ->  underTest.getFullyQualifiedTypeNameOf("String"));
	}
	
	@Test
	void get_fullyqualifiedtypename_of_matches_metaclass() throws EolModelElementTypeNotFoundException {
		var prj = (IRPModelElement) underTest.getElementById("GUID fe10d56a-baae-4305-9bd1-f8db5aff6190");
		assertEquals("Package", underTest.getFullyQualifiedTypeNameOf(prj));
	}
	
	@Test
	void get_fullyqualifiedtypename_of_matches_new_term_stereotype() throws EolModelElementTypeNotFoundException {
		var block = (IRPModelElement) underTest.getElementById("GUID 24885c0c-4ae9-4aa2-a03a-921ecade1f3c");
		assertEquals("Block", underTest.getFullyQualifiedTypeNameOf(block));
	}
	
	@Test
	void is_of_kind_fails_for_non_irp_model_element() {
		assertThrows(
				IllegalArgumentException.class, 
				() -> underTest.isOfKind("String", "Class"));
	}
	
	@Test
	void is_of_kind_fails_for_unknown_type() {
		var prj = (IRPModelElement) underTest.getElementById("GUID fe10d56a-baae-4305-9bd1-f8db5aff6190");
		assertThrows(
				EolModelElementTypeNotFoundException.class, 
				() -> underTest.isOfKind(prj, "Star"));
	}
	
	@Test
	void is_of_kind_fails_for_no_new_term_stereotype() {
		var prj = (IRPModelElement) underTest.getElementById("GUID fe10d56a-baae-4305-9bd1-f8db5aff6190");
		assertThrows(
				EolModelElementTypeNotFoundException.class, 
				() -> underTest.isOfKind(prj, "Usage"));
	}

	@Test
	void is_of_kind_false_for_wrong_metaclass() {
		var prj = (IRPModelElement) underTest.getElementById("GUID fe10d56a-baae-4305-9bd1-f8db5aff6190");
		try {
			assertFalse(underTest.isOfKind(prj, "Class"));
		} catch (EolModelElementTypeNotFoundException e) {
			fail("Should not throw exception", e);
		}
	}
	
	@Test
	void is_of_kind_false_for_wrong_stereotype() {
		var block = (IRPModelElement) underTest.getElementById("GUID 24885c0c-4ae9-4aa2-a03a-921ecade1f3c");
		try {
			assertFalse(underTest.isOfKind(block, "DataType"));
		} catch (EolModelElementTypeNotFoundException e) {
			fail("Should not throw exception", e);
		}
	}
	
	@Test
	void is_of_kind_true_for_correct_metaclass() {
		var prj = (IRPModelElement) underTest.getElementById("GUID fe10d56a-baae-4305-9bd1-f8db5aff6190");
		try {
			assertTrue(underTest.isOfKind(prj, "Package"));
		} catch (EolModelElementTypeNotFoundException e) {
			fail("Should not throw exception", e);
		}
	}
	
	@Test
	void is_of_kind_true_for_correct_stereotype() {
		var block = (IRPModelElement) underTest.getElementById("GUID 24885c0c-4ae9-4aa2-a03a-921ecade1f3c");
		try {
			assertTrue(underTest.isOfKind(block, "Block"));
		} catch (EolModelElementTypeNotFoundException e) {
			fail("Should not throw exception", e);
		}
	}
	
	@Test
	void is_of_type_fails_for_unknown_type() {
		var prj = (IRPModelElement) underTest.getElementById("GUID fe10d56a-baae-4305-9bd1-f8db5aff6190");
		assertThrows(
				EolModelElementTypeNotFoundException.class, 
				() -> underTest.isOfType(prj, "Star"));
	}
	
	@Test
	void is_of_type_fails_for_no_new_term_stereotype() {
		var prj = (IRPModelElement) underTest.getElementById("GUID fe10d56a-baae-4305-9bd1-f8db5aff6190");
		assertThrows(
				EolModelElementTypeNotFoundException.class, 
				() -> underTest.isOfType(prj, "Usage"));
	}

	@Test
	void is_of_type_false_for_wrong_metaclass() {
		var prj = (IRPModelElement) underTest.getElementById("GUID fe10d56a-baae-4305-9bd1-f8db5aff6190");
		try {
			assertFalse(underTest.isOfType(prj, "Class"));
		} catch (EolModelElementTypeNotFoundException e) {
			fail("Should not throw exception", e);
		}
	}
	
	@Test
	void is_of_type_false_for_wrong_stereotype() {
		var block = (IRPModelElement) underTest.getElementById("GUID 24885c0c-4ae9-4aa2-a03a-921ecade1f3c");
		try {
			assertFalse(underTest.isOfType(block, "DataType"));
		} catch (EolModelElementTypeNotFoundException e) {
			fail("Should not throw exception", e);
		}
	}
	
	@Test
	void is_of_type_true_for_correct_metaclass() {
		var prj = (IRPModelElement) underTest.getElementById("GUID fe10d56a-baae-4305-9bd1-f8db5aff6190");
		try {
			assertTrue(underTest.isOfType(prj, "Package"));
		} catch (EolModelElementTypeNotFoundException e) {
			fail("Should not throw exception", e);
		}
	}
	
	@Test
	void is_of_type_true_for_correct_stereotype() {
		var block = (IRPModelElement) underTest.getElementById("GUID 24885c0c-4ae9-4aa2-a03a-921ecade1f3c");
		try {
			assertTrue(underTest.isOfType(block, "Block"));
		} catch (EolModelElementTypeNotFoundException e) {
			fail("Should not throw exception", e);
		}
	}
	
	static private IModel underTest;
	
	static private StringProperties defaultProperties() {
		StringProperties properties = new StringProperties();
		properties.put(RhapsodyModel.PROPERTIES_INSTALLATION_DIRECTORY, System.getenv("RHAPSODY_PATH"));
		properties.put(RhapsodyModel.PROPERTIES_PROJECT_PATH, "resources/TollRoad.rpyx");
		properties.put(RhapsodyModel.PROPERTIES_MAIN_PACKAGE_NAME, "RoadMonitoringPkg");
		return properties;
	}
	
}
