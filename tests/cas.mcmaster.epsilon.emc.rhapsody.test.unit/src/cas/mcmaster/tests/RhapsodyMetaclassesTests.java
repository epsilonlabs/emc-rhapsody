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

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.eclipse.epsilon.eol.exceptions.models.EolEnumerationValueNotFoundException;
import org.eclipse.epsilon.eol.exceptions.models.EolModelElementTypeNotFoundException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import com.telelogic.rhapsody.core.IRPApplication;
import com.telelogic.rhapsody.core.IRPModelElement;
import com.telelogic.rhapsody.core.IRPProject;
import com.telelogic.rhapsody.core.RhapsodyAppServer;
import com.telelogic.rhapsody.core.RhapsodyRuntimeException;

import cas.mcmaster.epsilon.emc.RhapsodyMetaclasses;

public class RhapsodyMetaclassesTests {
	
	@BeforeAll
	static void load() {
		try {
			app = RhapsodyAppServer.getActiveRhapsodyApplication();
			rhapsodyWasActive = true;
		} catch (RhapsodyRuntimeException e) {
			app = RhapsodyAppServer.createRhapsodyApplication();
		}
		Path fullPath = Paths.get("resources/TestModelA/TestModelA.rpyx").toAbsolutePath();
		prj = app.openProject(fullPath.toString());
		
	}
	
	@AfterAll
	static void unload() {
		if (prj != null) {
			prj.close();					
		}
		if(!rhapsodyWasActive && (app != null)) {
			app.quit();
		}
	}
	
	@Test
	void enumeration_value_fails_for_invalid_name() {
		var underTest = underTest();
		EolEnumerationValueNotFoundException thrown = Assertions.assertThrows(
				EolEnumerationValueNotFoundException.class,
				() -> {
					underTest.getEnumerationValue("TestEnumWrong", "SOME_VALUE");
				});
		assertTrue(thrown.getMessage().contains("Cannot find enumeration literal TestEnumWrong#SOME_VALUE in model TollRoad"));
	}
	
	@Test
	void enumeration_value_fails_for_invalid_literal() {
		var underTest = underTest();
		EolEnumerationValueNotFoundException thrown = Assertions.assertThrows(
				EolEnumerationValueNotFoundException.class,
				() -> {
					underTest.getEnumerationValue("TestEnum", "SOME_VALUE");
				});
		assertTrue(thrown.getMessage().contains("Cannot find enumeration literal TestEnum#SOME_VALUE in model TollRoad"));
	}
	
	@Test
	void get_enumeration_value_matches() {
		var underTest = underTest();
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
		var underTest = underTest();
		EolModelElementTypeNotFoundException thrown = assertThrows(
				EolModelElementTypeNotFoundException.class,
				() -> underTest.getAllOfType("car"));
		assertTrue(thrown.getMessage().contains( "Cannot find meta-class 'car' in model 'TollRoad'"));
	}
	
	@Test
	void get_all_by_type_fails_if_no_new_term_stereotype() {
		var underTest = underTest();
		EolModelElementTypeNotFoundException thrown = assertThrows(
				EolModelElementTypeNotFoundException.class,
				() -> underTest.getAllOfType("Usage"));
		assertTrue(thrown.getMessage().contains( "Cannot find meta-class 'Usage' in model 'TollRoad'"));
	}
	
	@Test
	void get_all_by_type_for_type() {
		var underTest = underTest();
		try {
			var packages = underTest.getAllOfType("Package");
			assertEquals(14, packages.size());
		} catch (EolModelElementTypeNotFoundException e) {
			fail("Should not throw exception", e);
		}
	}
	
	@Test
	void get_all_by_type_for_stereotype() {
		var underTest = underTest();
		try {
			var blocks = underTest.getAllOfType("Block");
			assertEquals(6, blocks.size());
		} catch (EolModelElementTypeNotFoundException e) {
			fail("Should not throw exception", e);
		}
	}
	
	@Test
	void get_all_by_kind_fails_if_unknown_stereotype_or_type() {
		var underTest = underTest();
		EolModelElementTypeNotFoundException thrown = assertThrows(
				EolModelElementTypeNotFoundException.class,
				() -> underTest.getAllOfKind("car"));
		assertTrue(thrown.getMessage().contains( "Cannot find meta-class 'car' in model 'TollRoad'"));
	}
	
	@Test
	void get_all_by_type_kind_if_no_new_term_stereotype() {
		var underTest = underTest();
		EolModelElementTypeNotFoundException thrown = assertThrows(
				EolModelElementTypeNotFoundException.class,
				() -> underTest.getAllOfKind("Usage"));
		assertTrue(thrown.getMessage().contains( "Cannot find meta-class 'Usage' in model 'TollRoad'"));
	}
	
	@Test
	void get_all_by_kind_for_type() {
		var underTest = underTest();
		try {
			var packages = underTest.getAllOfKind("Package");
			assertEquals(19, packages.size());
		} catch (EolModelElementTypeNotFoundException e) {
			fail("Should not throw exception", e);
		}
	}
	
	@Test
	void get_all_by_kind_for_stereotype() {
		var underTest = underTest();
		try {
			var blocks = underTest.getAllOfKind("Block");
			assertEquals(6, blocks.size());
		} catch (EolModelElementTypeNotFoundException e) {
			fail("Should not throw exception", e);
		}
	}

	@Test
	void get_all_type_names_for_class_base() {
		var underTest = underTest();
		IRPModelElement operation = prj.findNestedElementRecursive("call", "Operation");
		if (operation == null) {
			fail("Operation with name 'takePicture' should exist");
		}
		var typeNames = underTest.getAllTypeNamesOf(operation);
		assertEquals(1, typeNames.size());
		assertTrue(typeNames.contains("Operation"));
	}
	
	@Test
	void get_all_type_names_for_new_term_stereotype() {
		var underTest = underTest();
		var block = prj.findNestedElementRecursive("Block1", "Block");
		if (block == null) {
			fail("Block with name 'Block1' should exist");
		}
		var typeNames = underTest.getAllTypeNamesOf(block);
		assertEquals(2, typeNames.size());
		assertTrue(typeNames.contains("Class"));
		assertTrue(typeNames.contains("Block"));
	}
	
	@Test
	void get_all_type_names_for_no_new_term_stereotype() {
		var underTest = underTest();
		var dep = prj.findNestedElementRecursive("Block3", "Dependency");
		if (dep == null) {
			fail("Dependency with name 'Block3' should exist");
		}
		// Block3 has a Usage (no new term) stereotype
		var typeNames = underTest.getAllTypeNamesOf(dep);
		assertEquals(1, typeNames.size());
		assertTrue(typeNames.contains("Dependency"));
	}
	
	@Test
	void has_type_is_false_for_unknown_metaclass() {
		var underTest = underTest();
		assertFalse(underTest.hasType("car"));
	}
	
	@Test
	void has_type_is_false_for_no_new_term_stereotype() {
		var underTest = underTest();
		assertFalse(underTest.hasType("ModifiedTo"));
	}
	
	@ParameterizedTest
	@MethodSource("validMetaclasses")
	void has_type_is_true_for_class(String metaclass) {
		var underTest = underTest();
		assertTrue(underTest.hasType(metaclass));
	}
	
	@Test
	void has_type_is_true_for_new_term_stereotype() {
		var underTest = underTest();
		assertTrue(underTest.hasType("Block"));
	}
	
	@Test
	void is_instantiable_is_false_for_unknown() {
		var underTest = underTest();
		assertFalse(underTest.isInstantiable("car"));
	}
	
	@ParameterizedTest
	@MethodSource("validMetaclasses")
	void is_instantiable_is_true_for_known_class() {
		var underTest = underTest();
		assertTrue(underTest.isInstantiable("Operation"));
	}
	
	void is_instantiable_is_true_for_new_term_stereotype() {
		var underTest = underTest();
		assertTrue(underTest.isInstantiable("Interface"));
	}
	
	@Test
	void is_instantiable_is_false_for_no_new_term_stereotype() {
		var underTest = underTest();
		assertFalse(underTest.isInstantiable("ImportedProfile"));
	}
	
	private RhapsodyMetaclasses underTest() {
		return new RhapsodyMetaclasses(
				System.getenv("RHAPSODY_PATH"),
				false, prj, "TollRoad").load();
	}
	
	static private IRPApplication app;
	static private IRPProject prj;
	static private boolean rhapsodyWasActive;
	static private String[] validMetaclasses;
	
	private static String[] validMetaclasses() {
		if (validMetaclasses == null) {
			String mcList;
			try(BufferedReader brTest = new BufferedReader(
					new FileReader(Paths.get(System.getenv("RHAPSODY_PATH"),"Doc/Metaclasses.txt").toFile()))) {
				mcList = brTest .readLine();	
			} catch (IOException e) {
				throw new IllegalStateException("Unable to load Rhapsody's Metaclasses names", e);
			}
			validMetaclasses = mcList.split(",");
		}
		return validMetaclasses;

	}
}
