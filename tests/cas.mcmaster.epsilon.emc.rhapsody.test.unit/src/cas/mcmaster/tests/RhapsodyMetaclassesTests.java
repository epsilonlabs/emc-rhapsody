package cas.mcmaster.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Collection;
import java.util.Iterator;

import org.eclipse.epsilon.eol.exceptions.models.EolEnumerationValueNotFoundException;
import org.eclipse.epsilon.eol.exceptions.models.EolModelElementTypeNotFoundException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.telelogic.rhapsody.core.IRPApplication;
import com.telelogic.rhapsody.core.IRPModelElement;
import com.telelogic.rhapsody.core.IRPOperation;
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
	static void close() {
		if (prj != null) {
			prj.close();					
		}
		if(!rhapsodyWasActive && (app != null)) {
			app.quit();
		}
	}
	
	@Test
	void enumeration_value_fails_for_invalid_name() {
		var underTest = new RhapsodyMetaclasses(
				"C:/Program Files/IBM\\Rhapsody/9.0.1",
				rhapsodyWasActive, prj).load();
		EolEnumerationValueNotFoundException thrown = Assertions.assertThrows(
				EolEnumerationValueNotFoundException.class,
				() -> {
					underTest.getEnumerationValue("TestEnumWrong", "SOME_VALUE", prj.getName());
				});
		assertTrue(thrown.getMessage().contains("Cannot find enumeration literal TestEnumWrong#SOME_VALUE in model TollRoad"));
	}
	
	@Test
	void enumeration_value_fails_for_invalid_literal() {
		var underTest = new RhapsodyMetaclasses(
				"C:/Program Files/IBM\\Rhapsody/9.0.1",
				rhapsodyWasActive, prj).load();
		EolEnumerationValueNotFoundException thrown = Assertions.assertThrows(
				EolEnumerationValueNotFoundException.class,
				() -> {
					underTest.getEnumerationValue("TestEnum", "SOME_VALUE", prj.getName());
				});
		assertTrue(thrown.getMessage().contains("Cannot find enumeration literal TestEnum#SOME_VALUE in model TollRoad"));
	}
	
	@Test
	void get_enumeration_value_matches() {
		var underTest = new RhapsodyMetaclasses(
				"C:/Program Files/IBM\\Rhapsody/9.0.1",
				rhapsodyWasActive, prj).load();
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
		var underTest = new RhapsodyMetaclasses(
				"C:/Program Files/IBM\\Rhapsody/9.0.1",
				rhapsodyWasActive, prj).load();
		EolModelElementTypeNotFoundException thrown = assertThrows(
				EolModelElementTypeNotFoundException.class,
				() -> underTest.getAllOfType("car", prj.getName()));
		assertTrue(thrown.getMessage().contains( "Cannot find meta-class 'car' in model 'TollRoad'"));
	}
	
	@Test
	void get_all_by_type_for_type() {
		var underTest = new RhapsodyMetaclasses(
				"C:/Program Files/IBM\\Rhapsody/9.0.1",
				rhapsodyWasActive, prj).load();
		try {
			var packages = underTest.getAllOfType("Package", prj.getName());
			assertEquals(14, packages.size());
		} catch (EolModelElementTypeNotFoundException e) {
			fail("Should not throw exception", e);
		}
	}
	
	@Test
	void get_all_by_type_for_stereotype() {
		var underTest = new RhapsodyMetaclasses(
				"C:/Program Files/IBM\\Rhapsody/9.0.1",
				rhapsodyWasActive, prj).load();
		try {
			var blocks = underTest.getAllOfType("Block", prj.getName());
			assertEquals(5, blocks.size());
		} catch (EolModelElementTypeNotFoundException e) {
			fail("Should not throw exception", e);
		}
	}

	
	@Test
	void get_all_type_names_for_class_base() {
		var underTest = new RhapsodyMetaclasses(
				"C:/Program Files/IBM\\Rhapsody/9.0.1",
				rhapsodyWasActive, prj).load();
		Collection<IRPModelElement> operations = null;
		try {
			operations = underTest.getAllOfType("Operation", prj.getName());
		} catch (EolModelElementTypeNotFoundException e) {
			fail("Should not throw exception", e);
		}
		Iterator<IRPModelElement> it = operations.iterator();
		if (it.hasNext()) {
			var op = it.next();
			var typeNames = underTest.getAllTypeNamesOf(op);
			assertEquals(1, typeNames.size());
		} else {
			fail("Model should have operations");
		}
	}
	
	@Test
	void get_all_type_names_for_applied_stereotype() {
		var underTest = new RhapsodyMetaclasses(
				"C:/Program Files/IBM\\Rhapsody/9.0.1",
				rhapsodyWasActive, prj).load();
		Collection<IRPModelElement> blocks = null;
		try {
			blocks = underTest.getAllOfType("Block", prj.getName());
		} catch (EolModelElementTypeNotFoundException e) {
			fail("Should not throw exception", e);
		}
		Iterator<IRPModelElement> it = blocks.iterator();
		if (it.hasNext()) {
			var block = it.next();
			var typeNames = underTest.getAllTypeNamesOf(block);
			assertEquals(2, typeNames.size());
			assertTrue(typeNames.contains("Class"));
			assertTrue(typeNames.contains("Block"));
		} else {
			fail("Model should have operations");
		}
	}

	
	static private IRPApplication app;
	static private IRPProject prj;
	static private boolean rhapsodyWasActive;
}
