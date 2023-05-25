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
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
import org.eclipse.epsilon.eol.execute.context.EolContext;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.telelogic.rhapsody.core.IRPApplication;
import com.telelogic.rhapsody.core.IRPClass;
import com.telelogic.rhapsody.core.IRPModelElement;
import com.telelogic.rhapsody.core.IRPProject;
import com.telelogic.rhapsody.core.IRPTag;
import com.telelogic.rhapsody.core.RhapsodyAppServer;
import com.telelogic.rhapsody.core.RhapsodyRuntimeException;

import cas.mcmaster.epsilon.emc.RhapsodyPropertySetter;

public class RhapsodyPropertySetterTests {
	
	@BeforeAll
	static void load() {
		try {
			app = RhapsodyAppServer.getActiveRhapsodyApplication();
			rhapsodyWasActive = true;
		} catch (RhapsodyRuntimeException e) {
			app = RhapsodyAppServer.createRhapsodyApplication();
		}
		Path fullPath = Paths.get("resources/TollRoad.rpyx").toAbsolutePath();
		prj = app.openProject(fullPath.toString());
		
	}
	
	@AfterAll
	static void unload() {
//		if (prj != null) {
//			prj.close();					
//		}
		if(!rhapsodyWasActive && (app != null)) {
			app.quit();
		}
	}
	
	@Test
	void set_property_from_api() {
		var block = (IRPClass) prj.findNestedElementRecursive("Ambulance", "Vehicle");
		if (block == null) {
			fail("Vehicle with name 'Ambulance' should exist");
		}
		var underTest = new RhapsodyPropertySetter();
		var context = new EolContext();
		try {
			underTest.invoke(block, "name", "Ambulancia", context);
			assertEquals("Ambulancia", block.getName());
			underTest.invoke(block, "name", "Ambulancia", context);
			assertEquals("Ambulancia", block.getName());
		} catch (EolRuntimeException e) {
			fail("Property should exist");
		} finally {
			block.setName("Ambulance");
		}	
	}
	
//	@ParameterizedTest
//	@MethodSource
//	void get_property_from_tag(String tagName, Object expected) {
//		var block = prj.findNestedElementRecursive("Test", "Block");
//		if (block == null) {
//			fail("Block with name 'Test' should exist");
//		}
//		var underTest = new RhapsodyPropertyGetter();
//		var context = new EolContext();
//		try {
//			var value = underTest.invoke(block, tagName, context);
//			assertEquals(expected, value);
//		} catch (EolRuntimeException e) {
//			fail("Property 'name' should exist");
//		}
//	}
//	
//	static Stream<Arguments> get_property_from_tag() {
//		var camera = prj.findNestedElementRecursive("Camera", "Block");
//		return Stream.of(
//		        arguments("boolVal", true),
//		        arguments("floatOther", "wrongFloat"),
//		        arguments("floatVal", 2.3f),
//		        arguments("intOther", "wrongInt"),
//		        arguments("intVal", 10),
//		        arguments("instanceVal", camera),
//		        arguments("strVal", "strValue")
//		        
//		    );
//	}
	
	static private IRPApplication app;
	static private IRPProject prj;
	static private boolean rhapsodyWasActive;

}
