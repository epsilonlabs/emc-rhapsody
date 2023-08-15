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
	void enumeration_value_fails_for_type_that_is_not_enum() {
		EolEnumerationValueNotFoundException thrown = Assertions.assertThrows(
				EolEnumerationValueNotFoundException.class,
				() -> {
					underTest.getEnumerationValue("Speed", "SOME_VALUE");
				});
		assertTrue(thrown.getMessage().contains("Cannot find enumeration literal Speed#SOME_VALUE in model"));
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
	
	/**
	 * @bug https://ihub.cas.mcmaster.ca/hoyosroh/mde-workbench/-/issues/1
	 */
	@Test
	void get_all_by_type_for_type_reception() {
		try {
			var receptions = underTest.getAllOfType("Reception");
			assertEquals(1, receptions.size());
		} catch (EolModelElementTypeNotFoundException e) {
			fail("Should not throw exception", e);
		}
	}
	
	@Test
	void get_all_by_type_for_new_term_stereotype() {
		try {
			var blocks = underTest.getAllOfType("Block");
			assertEquals(6, blocks.size());
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
	void get_all_by_kind_for_typ() {
		try {
			var packages = underTest.getAllOfKind("Package");
			assertEquals(19, packages.size());
		} catch (EolModelElementTypeNotFoundException e) {
			fail("Should not throw exception", e);
		}
	}
	
	
	@Test
	void get_all_by_kind_for_new_term_stereotype() {
		try {
			var blocks = underTest.getAllOfKind("Block");
			assertEquals(6, blocks.size());
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
		var ele = (IRPModelElement) underTest.getElementById("GUID 9d852c72-9a2a-4e2e-8649-222f80d796d6");
		if (ele == null) {
			fail("Element by id must exist");
		}
		assertEquals("IRPPackage", underTest.getTypeOf(ele));
	}
	
	@Test
	void get_type_of_matches_new_term_stereotype() throws EolModelElementTypeNotFoundException {
		var ele = (IRPModelElement) underTest.getElementById("GUID 78f445b6-f602-4fa4-abc7-619306770217");
		if (ele == null) {
			fail("Element by id must exist");
		}
		assertEquals("IRPClass", underTest.getTypeOf(ele));
	}
	
	@Test
	void get_typename_of_fails_for_non_irp_model_element() throws EolModelElementTypeNotFoundException {
		assertThrows(
				IllegalArgumentException.class, 
				() ->  underTest.getTypeNameOf("String"));
	}
	
	@Test
	void get_typename_of_matches_metaclass() throws EolModelElementTypeNotFoundException {
		var ele = (IRPModelElement) underTest.getElementById("GUID 9d852c72-9a2a-4e2e-8649-222f80d796d6");
		if (ele == null) {
			fail("Element by id must exist");
		}
		assertEquals("Package", underTest.getTypeNameOf(ele));
	}
	
	@Test
	void get_typename_of_matches_new_term_stereotype() throws EolModelElementTypeNotFoundException {
		var ele = (IRPModelElement) underTest.getElementById("GUID 4247c7c8-d0f2-499f-bfe3-a9dd964f78ba");
		if (ele == null) {
			fail("Element by id must exist");
		}
		assertEquals("Block", underTest.getTypeNameOf(ele));
	}
	
	@Test
	void get_fullyqualifiedtypename_of_fails_for_non_irp_model_element() throws EolModelElementTypeNotFoundException {
		assertThrows(
				IllegalArgumentException.class, 
				() ->  underTest.getFullyQualifiedTypeNameOf("String"));
	}
	
	@Test
	void get_fullyqualifiedtypename_of_matches_metaclass() throws EolModelElementTypeNotFoundException {
		var ele = (IRPModelElement) underTest.getElementById("GUID 9d852c72-9a2a-4e2e-8649-222f80d796d6");
		if (ele == null) {
			fail("Element by id must exist");
		}
		assertEquals("Package", underTest.getFullyQualifiedTypeNameOf(ele));
	}
	
	@Test
	void get_fullyqualifiedtypename_of_matches_new_term_stereotype() throws EolModelElementTypeNotFoundException {
		var ele = (IRPModelElement) underTest.getElementById("GUID 4247c7c8-d0f2-499f-bfe3-a9dd964f78ba");
		if (ele == null) {
			fail("Element by id must exist");
		}
		assertEquals("Block", underTest.getFullyQualifiedTypeNameOf(ele));
	}
	
	@Test
	void is_of_kind_fails_for_non_irp_model_element() {
		assertThrows(
				IllegalArgumentException.class, 
				() -> underTest.isOfKind("String", "Class"));
	}
	
	@Test
	void is_of_kind_fails_for_unknown_type() {
		var ele = (IRPModelElement) underTest.getElementById("GUID 9d852c72-9a2a-4e2e-8649-222f80d796d6");
		if (ele == null) {
			fail("Element by id must exist");
		}
		assertThrows(
				EolModelElementTypeNotFoundException.class, 
				() -> underTest.isOfKind(ele, "Star"));
	}
	
	@Test
	void is_of_kind_fails_for_no_new_term_stereotype() {
		var ele = (IRPModelElement) underTest.getElementById("GUID 9d852c72-9a2a-4e2e-8649-222f80d796d6");
		if (ele == null) {
			fail("Element by id must exist");
		}
		assertThrows(
				EolModelElementTypeNotFoundException.class, 
				() -> underTest.isOfKind(ele, "Usage"));
	}

	@Test
	void is_of_kind_false_for_wrong_metaclass() {
		var ele = (IRPModelElement) underTest.getElementById("GUID 9d852c72-9a2a-4e2e-8649-222f80d796d6");
		if (ele == null) {
			fail("Element by id must exist");
		}
		try {
			assertFalse(underTest.isOfKind(ele, "Class"));
		} catch (EolModelElementTypeNotFoundException e) {
			fail("Should not throw exception", e);
		}
	}
	
	@Test
	void is_of_kind_false_for_wrong_stereotype() {
		var ele = (IRPModelElement) underTest.getElementById("GUID 4247c7c8-d0f2-499f-bfe3-a9dd964f78ba");
		if (ele == null) {
			fail("Element by id must exist");
		}
		try {
			assertFalse(underTest.isOfKind(ele, "DataType"));
		} catch (EolModelElementTypeNotFoundException e) {
			fail("Should not throw exception", e);
		}
	}
	
	@Test
	void is_of_kind_true_for_correct_metaclass() {
		var ele = (IRPModelElement) underTest.getElementById("GUID 9d852c72-9a2a-4e2e-8649-222f80d796d6");
		if (ele == null) {
			fail("Element by id must exist");
		}
		try {
			assertTrue(underTest.isOfKind(ele, "Package"));
		} catch (EolModelElementTypeNotFoundException e) {
			fail("Should not throw exception", e);
		}
	}
	
	@Test
	void is_of_kind_true_for_correct_stereotype() {
		var ele = (IRPModelElement) underTest.getElementById("GUID 4247c7c8-d0f2-499f-bfe3-a9dd964f78ba");
		if (ele == null) {
			fail("Element by id must exist");
		}
		try {
			assertTrue(underTest.isOfKind(ele, "Block"));
		} catch (EolModelElementTypeNotFoundException e) {
			fail("Should not throw exception", e);
		}
	}
	
	@Test
	void is_of_type_fails_for_unknown_type() {
		var ele = (IRPModelElement) underTest.getElementById("GUID 9d852c72-9a2a-4e2e-8649-222f80d796d6");
		if (ele == null) {
			fail("Element by id must exist");
		}
		assertThrows(
				EolModelElementTypeNotFoundException.class, 
				() -> underTest.isOfType(ele, "Star"));
	}
	
	@Test
	void is_of_type_fails_for_no_new_term_stereotype() {
		var ele = (IRPModelElement) underTest.getElementById("GUID 9d852c72-9a2a-4e2e-8649-222f80d796d6");
		if (ele == null) {
			fail("Element by id must exist");
		}
		assertThrows(
				EolModelElementTypeNotFoundException.class, 
				() -> underTest.isOfType(ele, "Usage"));
	}

	@Test
	void is_of_type_false_for_wrong_metaclass() {
		var ele = (IRPModelElement) underTest.getElementById("GUID 9d852c72-9a2a-4e2e-8649-222f80d796d6");
		if (ele == null) {
			fail("Element by id must exist");
		}
		try {
			assertFalse(underTest.isOfType(ele, "Class"));
		} catch (EolModelElementTypeNotFoundException e) {
			fail("Should not throw exception", e);
		}
	}
	
	@Test
	void is_of_type_false_for_wrong_stereotype() {
		var ele = (IRPModelElement) underTest.getElementById("GUID 4247c7c8-d0f2-499f-bfe3-a9dd964f78ba");
		if (ele == null) {
			fail("Element by id must exist");
		}
		try {
			assertFalse(underTest.isOfType(ele, "DataType"));
		} catch (EolModelElementTypeNotFoundException e) {
			fail("Should not throw exception", e);
		}
	}
	
	@Test
	void is_of_type_true_for_correct_metaclass() {
		var ele = (IRPModelElement) underTest.getElementById("GUID 9d852c72-9a2a-4e2e-8649-222f80d796d6");
		if (ele == null) {
			fail("Element by id must exist");
		}
		try {
			assertTrue(underTest.isOfType(ele, "Package"));
		} catch (EolModelElementTypeNotFoundException e) {
			fail("Should not throw exception", e);
		}
	}
	
	@Test
	void is_of_type_true_for_correct_stereotype() {
		var ele = (IRPModelElement) underTest.getElementById("GUID 4247c7c8-d0f2-499f-bfe3-a9dd964f78ba");
		if (ele == null) {
			fail("Element by id must exist");
		}
		try {
			assertTrue(underTest.isOfType(ele, "Block"));
		} catch (EolModelElementTypeNotFoundException e) {
			fail("Should not throw exception", e);
		}
	}
	
	static private IModel underTest;
	
	static private StringProperties defaultProperties() {
		StringProperties properties = new StringProperties();
		properties.put(RhapsodyModel.PROPERTY_INSTALLATION_DIRECTORY, System.getenv("RHAPSODY_PATH"));
		properties.put(RhapsodyModel.PROPERTY_PROJECT_PATH, "resources/TestModelA/TestModelA.rpyx");
		properties.put(RhapsodyModel.PROPERTY_MAIN_PACKAGE_NAME, "TestingPkg");
		return properties;
	}
	
}
