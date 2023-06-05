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
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.function.Function;
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
import com.telelogic.rhapsody.core.IRPOperation;
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
	
	@ParameterizedTest
	@MethodSource
	void set_property_for_api(String property, Object value, Function<IRPOperation, Object> getter) {
		var operation = (IRPOperation) prj.findNestedElementRecursive("run", "Operation");
		if (operation == null) {
			fail("Operation with name 'run' should exist");
		}
		var underTest = new RhapsodyPropertySetter();
		var context = new EolContext();
		var prevVal = getter.apply(operation);
		assertNotEquals(prevVal, value, "Test value should be different than current value");
		try {
			underTest.invoke(operation, property, value, context);
			assertEquals(value, getter.apply(operation));
		} catch (EolRuntimeException e) {
			fail("Property should exist");
		}
	}
	
	static Stream<Arguments> set_property_for_api() {
		return Stream.of(
				arguments("visibility", "protected", nameGetter()),
				arguments("isConst", 1, abstractGetter())
				);
	}
	
	static Function<IRPOperation, Object> nameGetter() {
		return (e) -> e.getVisibility();
	}
	
	static Function<IRPOperation, Object> abstractGetter() {
		return (e) -> e.getIsConst();
	}
	
	@ParameterizedTest
	@MethodSource
	void set_property_for_tag_primitive(String property, Object value) {
		var block = prj.findNestedElementRecursive("BlockWithTags", "Block");
		if (block == null) {
			fail("Block with name 'Test' should exist");
		}
		var underTest = new RhapsodyPropertySetter();
		var context = new EolContext();
		IRPTag tag = block.getTag(property);
		var prevVal = tag.getValue();
		assertNotEquals(prevVal, value, "Test value should be different than current value");
		try {
			underTest.invoke(block, property, value, context);
			assertEquals(String.valueOf(value), tag.getValue());
		} catch (EolRuntimeException e) {
			fail("Tag should exist");
		}
	}
	
	static Stream<Arguments> set_property_for_tag_primitive() {
		return Stream.of(
				arguments("boolVal", "false"),
				arguments("floatVal", 4.5),
				arguments("intVal", 10),
				arguments("strVal", "newString")
				);
	}
	
	@Test
	void set_property_for_tag_context() {
		var block = prj.findNestedElementRecursive("BlockWithTags", "Block");
		var other = prj.findNestedElementRecursive("Block5", "Block");
		if (block == null) {
			fail("Block with name 'Test' should exist");
		}
		var underTest = new RhapsodyPropertySetter();
		var context = new EolContext();
		IRPTag tag = block.getTag("instanceVal");
		var prevVal = tag.getValue();
		assertNotEquals(prevVal, other.getName(), "Test value should be different than current value");
		try {
			underTest.invoke(block, "instanceVal", other, context);
			assertEquals(other.getName(), tag.getValue());
		} catch (EolRuntimeException e) {
			fail("Tag should exist");
		}
	}
	
	@Test
	void set_property_for_tag_multival_primitive() {
		var block = prj.findNestedElementRecursive("BlockWithTags", "Block");
		if (block == null) {
			fail("Block with name 'Test' should exist");
		}
		var underTest = new RhapsodyPropertySetter();
		var context = new EolContext();
		Object value = Arrays.asList(10, 11);
		IRPTag tag = block.getTag("multiIntVal");
		var prevVal = tag.getValue();
		assertNotEquals(prevVal, "10, 11", "Test value should be different than current value");
		try {
			underTest.invoke(block, "multiIntVal", value, context);
			assertEquals("10, 11", tag.getValue());
		} catch (EolRuntimeException e) {
			fail("Tag should exist");
		}
	}
	
	@Test
	void set_property_for_tag_multival_context() {
		var block = prj.findNestedElementRecursive("BlockWithTags", "Block");
		if (block == null) {
			fail("Block with name 'Test' should exist");
		}
		var underTest = new RhapsodyPropertySetter();
		var context = new EolContext();
		var value = Arrays.asList(
				prj.findNestedElementRecursive("Block5", "Block"),
				prj.findNestedElementRecursive("Block4", "Block"));
		IRPTag tag = block.getTag("multiInstanceVal" );
		var prevVal = tag.getValue();
		assertNotEquals(prevVal, "Block5, Block4", "Test value should be different than current value");
		try {
			underTest.invoke(block, "multiInstanceVal", value, context);
			assertEquals("Block5, Block4", tag.getValue());
		} catch (EolRuntimeException e) {
			fail("Tag should exist");
		}
	}
	
	static private IRPApplication app;
	static private IRPProject prj;
	static private boolean rhapsodyWasActive;

}
