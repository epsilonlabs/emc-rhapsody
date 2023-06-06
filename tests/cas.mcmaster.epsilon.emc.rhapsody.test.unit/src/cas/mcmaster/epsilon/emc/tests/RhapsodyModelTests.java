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
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.eclipse.epsilon.common.util.StringProperties;
import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
import org.eclipse.epsilon.eol.exceptions.models.EolModelElementTypeNotFoundException;
import org.eclipse.epsilon.eol.exceptions.models.EolModelLoadingException;
import org.eclipse.epsilon.eol.exceptions.models.EolNotInstantiableModelElementTypeException;
import org.eclipse.epsilon.eol.models.IModel;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import com.telelogic.rhapsody.core.IRPModelElement;
import com.telelogic.rhapsody.core.RhapsodyAppServer;

import cas.mcmaster.epsilon.emc.RhapsodyModel;

public class RhapsodyModelTests {
	
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
	
	@ParameterizedTest
	@ValueSource(strings = {"24885c0c-4ae9-4aa2-a03a-921ecade1f3c", "00005c0c-4ae9-4aa2-a03a-921ecade1f3c"})
	public void get_element_by_id_wrong_id(String id) {
		assertNull(underTest.getElementById(id));
	}
	
	@ParameterizedTest
	@CsvSource({
		"'GUID 9d852c72-9a2a-4e2e-8649-222f80d796d6',Package",
		"'GUID 4247c7c8-d0f2-499f-bfe3-a9dd964f78bac',Block"})
	public void get_element_by_id(String id, String metaclass) {
		Object element = underTest.getElementById(id);
		assertNotNull(element);
		assertEquals(metaclass, underTest.getTypeNameOf(element));
	}
	
	@Test
	void get_element_id() throws EolModelElementTypeNotFoundException {
		var element = (IRPModelElement) underTest.getElementById("GUID 9d852c72-9a2a-4e2e-8649-222f80d796d6");
		if (element == null) {
			fail("Element with given id should exist");
		}
		assertEquals("GUID 9d852c72-9a2a-4e2e-8649-222f80d796d6", underTest.getElementId(element));
	}
	
	@Test
	void get_element_id_for_non_irp_model_element() {
		assertThrows(
				IllegalArgumentException.class, 
				() -> underTest.getElementId("String"));
	}
	
	@Test
	void set_element_id() {
		var element = (IRPModelElement) underTest.getElementById("GUID 9d852c72-9a2a-4e2e-8649-222f80d796d6");
		underTest.setElementId(element, "GUID fe10d56a-baae-4305-9bd1-f8db5aff6191");
		if (element == null) {
			fail("Element with given id should exist");
		}
		assertEquals("GUID fe10d56a-baae-4305-9bd1-f8db5aff6191", underTest.getElementId(element));		
	}
	
	@ParameterizedTest
	@ValueSource(strings = {
			"GUID d4a8e313-16ae-43b5-95d5-c1fcf766c0b",
			"00005c0c-4ae9-4aa2-a03a-921ecade1f3c",
			"absdh",
			"GUID 24885c0c-4ae9-4aa2-a03a-921ecade1f3m"
			})
	void set_element_id_invalid_id(String id) {
		var element = (IRPModelElement) underTest.getElementById("GUID 24885c0c-4ae9-4aa2-a03a-921ecade1f3c");
		assertThrows(
				IllegalArgumentException.class, 
				() -> underTest.setElementId(element, id));
	}
	
	@Test
	void owns_is_true_for_element_in_model() {
		var ele = (IRPModelElement) underTest.getElementById("GUID 4247c7c8-d0f2-499f-bfe3-a9dd964f78bac");
		if (ele == null) {
			fail("Element by id must exist");
		}
		assertTrue(underTest.owns(ele));
	}
	
	@Test
	void owns_is_false_for_non_irp_element() {
		assertFalse(underTest.owns("String"));
	}
	
	@Test
	void owns_is_false_for_element_in_other_model() {
		var app = RhapsodyAppServer.createRhapsodyApplication();
		Path fullPath = Paths.get("resources/TestModelB/TestModelB.rpyx").toAbsolutePath();
		var prj = app.openProject(fullPath.toString());
		// GenericDigitalCamera
		var camera = prj.findNestedElementRecursive("BlockA", "Class");
		assertFalse(underTest.owns(camera));
		app.quit();
	}
	
	@ParameterizedTest
	@ValueSource(strings = {"owner", "panelWidget", "modified"})
	void knows_about_property_for_known(String property) {
		var element = (IRPModelElement) underTest.getElementById("GUID 4247c7c8-d0f2-499f-bfe3-a9dd964f78bac");
		assertTrue(underTest.knowsAboutProperty(element, property));
	}
	
	@ParameterizedTest
	@ValueSource(strings = {"owners", "panelWidgets", "modifiedAt"})
	void knows_about_property_for_unknown(String property) {
		var element = (IRPModelElement) underTest.getElementById("GUID 4247c7c8-d0f2-499f-bfe3-a9dd964f78bac");
		assertFalse(underTest.knowsAboutProperty(element, property));
	}
	
	@ParameterizedTest
	@CsvSource({
		"owner,true",
		"panelWidget,true",
		"modified,true"})
	void is_property_set_for_known(String property, String expected) {
		var element = (IRPModelElement) underTest.getElementById("GUID 4247c7c8-d0f2-499f-bfe3-a9dd964f78bac");
		try {
			assertEquals(Boolean.valueOf(expected), underTest.isPropertySet(element, property));
		} catch (EolRuntimeException e) {
			fail("Should not throw");
		}
	}
	
	@Test
	void deletes_element() throws EolModelElementTypeNotFoundException {
		IRPModelElement newClass = null;
		try {
			newClass = (IRPModelElement) underTest.createInstance("Class", Arrays.asList("NewClass"));
		} catch (EolModelElementTypeNotFoundException | EolNotInstantiableModelElementTypeException e) {
			fail("Should not throw exception", e);
		}
		String id = newClass.getGUID();
		Object byId = underTest.getElementById(id);
		assertNotNull(byId);
		try {
			underTest.deleteElement(byId);
		} catch (EolRuntimeException e) {
			fail("Should not throw exception", e);
		}
		byId = underTest.getElementById(id);
		assertNull(byId);
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
