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

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import org.eclipse.epsilon.eol.exceptions.EolIllegalPropertyException;
import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
import org.eclipse.epsilon.eol.execute.context.EolContext;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.telelogic.rhapsody.core.IRPApplication;
import com.telelogic.rhapsody.core.IRPProject;
import com.telelogic.rhapsody.core.RhapsodyAppServer;
import com.telelogic.rhapsody.core.RhapsodyRuntimeException;

import cas.mcmaster.epsilon.emc.IRPKey;
import cas.mcmaster.epsilon.emc.PropertyValue;
import cas.mcmaster.epsilon.emc.RhapsodyPropertyGetter;

public class RhapsodyPropertyGetterTests {
	
	
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
		propertyCache = Caffeine.newBuilder()
				.expireAfterWrite(10, TimeUnit.MINUTES)
			    .maximumSize(100)
			    .executor(Runnable::run)
			    .build();
		
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
	void has_property(String property, boolean expected) {
		var block = prj.findNestedElementRecursive("BlockWithTags", "Block");
		if (block == null) {
			fail("Block with name 'BlockWithTags' should exist");
		}
		var underTest = new RhapsodyPropertyGetter(propertyCache, app);
		assertEquals(expected, underTest.hasProperty(block, property, new EolContext()));
	}
	
	@Test
	void get_unknown_property_throws() {
		var block = prj.findNestedElementRecursive("Block1", "Block");
		if (block == null) {
			fail("Block with name 'Block1' should exist");
		}
		var underTest = new RhapsodyPropertyGetter(propertyCache, app);
		var context = new EolContext();
		assertThrows(EolIllegalPropertyException.class, () -> {
			underTest.invoke(block, "film", context);
		});
	}
	
	@ParameterizedTest
	@MethodSource
	void get_property_from_api(String property, Object expected) {
		var block = prj.findNestedElementRecursive("Block1", "Block");
		if (block == null) {
			fail("Block with name 'Block1' should exist");
		}
		var underTest = new RhapsodyPropertyGetter(propertyCache, app);
		try {
			var value = underTest.invoke(block, property, new EolContext());
			assertEquals(expected, value);
		} catch (EolRuntimeException e) {
			fail("Property should exist");
		}
	}
	
	@ParameterizedTest
	@MethodSource
	void get_property_from_tag(String tagName, Object expected) {
		var block = prj.findNestedElementRecursive("BlockWithTags", "Block");
		if (block == null) {
			fail("Block with name 'Test' should exist");
		}
		var underTest = new RhapsodyPropertyGetter(propertyCache, app);
		try {
			var value = underTest.invoke(block, tagName, new EolContext());
			if (value instanceof Collection<?>) {
				assertArrayEquals((Object[])expected, ((Collection<?>)value).toArray());
			} else {
				assertEquals(expected, value);
			}
		} catch (EolRuntimeException e) {
			fail("Property should exist");
		}
	}
	
	/**
	 * One getX, one isX, one hasX, one tag, one invalid
	 */
	static Stream<Arguments> has_property() {
		return Stream.of(
		        arguments("name", true),
		        arguments("readOnly", true),
		        arguments("hasNestedElements", true),
		        arguments("boolVal", true),
		        arguments("planet", false)
		    );
	}
	
	/**
	 * One getX, one isX, one hasX
	 */
	static Stream<Arguments> get_property_from_api() {
		return Stream.of(
		        arguments("name", "Block1"),
		        arguments("readOnly", 0),
		        arguments("hasNestedElements", 1)		        
		    );
	}
	
	static Stream<Arguments> get_property_from_tag() {
		var camera = prj.findNestedElementRecursive("Block1", "Block");
		var picture = prj.findNestedElementRecursive("Block2", "Block");
		return Stream.of(
		        arguments("boolVal", true),
		        arguments("floatOther", "wrongFloat"),
		        arguments("floatVal", 2.3f),
		        arguments("instanceVal", camera),
		        arguments("intOther", "wrongInt"),
		        arguments("intVal", 10),
		        arguments("multiInstanceVal", new Object[] {camera, picture}),
		        arguments("multiIntVal", new Integer[] {23, 35}),
		        arguments("multiStringVal", new String[] {"first", "second"}),
		        arguments("strVal", "strValue")
		        
		    );
	}
	
	static private IRPApplication app;
	static private IRPProject prj;
	static private Cache<IRPKey, PropertyValue> propertyCache;
	static private boolean rhapsodyWasActive;

}
