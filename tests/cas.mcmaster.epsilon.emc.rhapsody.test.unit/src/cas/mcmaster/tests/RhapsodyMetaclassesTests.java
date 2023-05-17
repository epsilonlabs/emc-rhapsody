package cas.mcmaster.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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
		prj = app.openProject("resources/TollRoad.rpyx");
		
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
					underTest.getEnumerationValue("TestEnumWrong", "SOME_VALUE", prj.getName());
				});
		assertTrue(thrown.getMessage().contains("Cannot find enumeration literal TestEnumWrong#SOME_VALUE in model TollRoad"));
	}
	
	@Test
	void enumeration_value_fails_for_invalid_literal() {
		var underTest = underTest();
		EolEnumerationValueNotFoundException thrown = Assertions.assertThrows(
				EolEnumerationValueNotFoundException.class,
				() -> {
					underTest.getEnumerationValue("TestEnum", "SOME_VALUE", prj.getName());
				});
		assertTrue(thrown.getMessage().contains("Cannot find enumeration literal TestEnum#SOME_VALUE in model TollRoad"));
	}
	
	@Test
	void get_enumeration_value_matches() {
		var underTest = underTest();
		try {
			var value = (String) underTest.getEnumerationValue("TestEnum", "TEST_1", prj.getName());
			assertEquals("1", value);
			value = (String) underTest.getEnumerationValue("TestEnum", "TEST_2", prj.getName());
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
				() -> underTest.getAllOfType("car", prj.getName()));
		assertTrue(thrown.getMessage().contains( "Cannot find meta-class 'car' in model 'TollRoad'"));
	}
	
	@Test
	void get_all_by_type_fails_if_no_new_term_stereotype() {
		var underTest = underTest();
		EolModelElementTypeNotFoundException thrown = assertThrows(
				EolModelElementTypeNotFoundException.class,
				() -> underTest.getAllOfType("Usage", prj.getName()));
		assertTrue(thrown.getMessage().contains( "Cannot find meta-class 'Usage' in model 'TollRoad'"));
	}
	
	@Test
	void get_all_by_type_for_type() {
		var underTest = underTest();
		try {
			var packages = underTest.getAllOfType("Package", prj.getName());
			assertEquals(14, packages.size());
		} catch (EolModelElementTypeNotFoundException e) {
			fail("Should not throw exception", e);
		}
	}
	
	@Test
	void get_all_by_type_for_stereotype() {
		var underTest = underTest();
		try {
			var blocks = underTest.getAllOfType("Block", prj.getName());
			assertEquals(5, blocks.size());
		} catch (EolModelElementTypeNotFoundException e) {
			fail("Should not throw exception", e);
		}
	}

	@Test
	void get_all_type_names_for_class_base() {
		var underTest = underTest();
		IRPModelElement operation = prj.findNestedElementRecursive("takePicture", "Operation");
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
		var block = prj.findNestedElementRecursive("Camera", "Block");
		if (block == null) {
			fail("Block with name 'Camera' should exist");
		}
		var typeNames = underTest.getAllTypeNamesOf(block);
		assertEquals(2, typeNames.size());
		assertTrue(typeNames.contains("Class"));
		assertTrue(typeNames.contains("Block"));
	}
	
	@Test
	void get_all_type_names_for_no_new_term_stereotype() {
		var underTest = underTest();
		var dep = prj.findNestedElementRecursive("TimeServices", "Dependency");
		if (dep == null) {
			fail("Dependency with name 'TimeServices' should exist");
		}
		// TimeServices has a Usage (no new term) stereotype
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
				false, prj).load();
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
