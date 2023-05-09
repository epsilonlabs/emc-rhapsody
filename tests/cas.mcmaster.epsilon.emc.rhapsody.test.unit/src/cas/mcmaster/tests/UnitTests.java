package cas.mcmaster.tests;
import static org.junit.jupiter.api.Assertions.*;

import org.eclipse.epsilon.eol.exceptions.models.EolModelElementTypeNotFoundException;
import org.eclipse.epsilon.eol.exceptions.models.EolModelLoadingException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cas.mcmaster.epsilon.emc.RhapsodyModel;


public class UnitTests {
	private RhapsodyModel api;
	
	@BeforeEach
	void setUp() throws EolModelLoadingException {
		api = new RhapsodyModel();
		api.load();
	}
	
	@AfterEach
	void close() {
		api.dispose();
	}
	
	@Test
	void testOpenProject() {
		assertEquals(api.getName(), "Test_Project");
	}
	
	@Test
	void testEditProject() {
		api.addPackage("Jeff");
		assertTrue(api.getCurrentPackage().getName().equals("Jeff"));
		assertTrue(api.checkIfChanged());
	}
	
	@Test
	void testSetPackage() {
		for (int i = 0; i < 10; i++) {
			api.addPackage("P" + Integer.toString(i));
		}
		Boolean test1 = api.setPackage("P0");
		assertTrue(test1);
		assertEquals(api.getCurrentPackage().getName(), "P0");
		Boolean test2 = api.setPackage("A");
		assertFalse(test2);
	}
	
	@Test
	void testAddType() {
		api.addPackage("T0");
		api.addType("type_0");
		assertEquals(api.getCurrentPackage().findType("type_0").getName(), "type_0");
		
	}
	
	@Test
	void testPropertyManipulation() {
		api.addPackage("T0");
		api.addType("type_0");
		api.addProperty("type_0", "test");
		String test1 = api.readProperty("type_0", "CG.Type.test");
		assertEquals(test1,"True");
		api.setProperty("type_0", "CG.Type.test", "False");
		String test2 = api.getCurrentPackage().findType("type_0").getPropertyValueExplicit("CG.Type.test");
		assertEquals(test2,"False");
	}
	
	@Test
	void testGetAllElementofType() throws EolModelElementTypeNotFoundException {
		for (int i = 0; i < 10; i++) {
			api.addPackage("T" + Integer.toString(i));
			api.addType("type");
		}
		Object[] test = api.getAllOfType("Type").toArray();
		assertEquals(test.length, 44);
	}
}
