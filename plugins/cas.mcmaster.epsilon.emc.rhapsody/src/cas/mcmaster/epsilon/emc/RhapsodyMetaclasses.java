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
package cas.mcmaster.epsilon.emc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.epsilon.eol.exceptions.models.EolEnumerationValueNotFoundException;
import org.eclipse.epsilon.eol.exceptions.models.EolModelElementTypeNotFoundException;

import com.telelogic.rhapsody.core.IRPCollection;
import com.telelogic.rhapsody.core.IRPEnumerationLiteral;
import com.telelogic.rhapsody.core.IRPModelElement;
import com.telelogic.rhapsody.core.IRPProject;
import com.telelogic.rhapsody.core.IRPStereotype;
import com.telelogic.rhapsody.core.IRPType;

/**
 * The Class RhapsodyMetaclasses loads the supported metaclasses from the Rhapsody installation 
 */
// TODO add a method so the stereotypes can be cleared if in "live" mode.
public class RhapsodyMetaclasses {
	
	public RhapsodyMetaclasses(
		String path,
		boolean cachingEnabled,
		IRPProject prj,
		String modelName) {
		this(Path.of(path), cachingEnabled, prj, modelName, new HashSet<>(), new HashSet<>());
	}
	
	/**
	 * Load the type information.
	 * @return a loaded version of this instance
	 * @throws IllegalStateException if the type information can't be loaded 
	 */
	public RhapsodyMetaclasses load() {
		return new RhapsodyMetaclasses(
				this.path,
				this.cachingEnabled,
				this.prj,
				this.modelName,
				this.metaclasses(),
				this.stereotypes());
	}
	
	/**
	 * Tries to find a SysML type with the enumerations <code>name</code> and if found, searches
	 * for an enumeration literal that matches the provided <code>literalName</code>.
	 * 
	 * @param name		the enumeration name
	 * @param literal	the literal name
	 * @return the value uf the enumeration literal, if found.
	 * @throws EolEnumerationValueNotFoundException if a type can't be found, or if the literal can't
	 * be found.
	 */
	public Object getEnumerationValue(
		String name,
		String literal) throws EolEnumerationValueNotFoundException {
		var irpEnum = (IRPType) this.prj.findNestedElementRecursive(name, "Type");
		if (irpEnum == null) {
			LOG.error("Unable to find a Type that matches the enumeration name: {}", name);
			throw new EolEnumerationValueNotFoundException(name, literal, this.modelName);
		}
		if (irpEnum.isKindEnumeration() == 0) {
			LOG.error("The Type that matches the enumeration name {} is not an enumeration", name);
			throw new EolEnumerationValueNotFoundException(name, literal, this.modelName);
		}
		var enumLiterals = irpEnum.getEnumerationLiterals();
		for (int i=1; i <= enumLiterals.getCount(); i++) {
			var element = (IRPEnumerationLiteral) enumLiterals.getItem(i);
			if (element.getName().equals(literal)) {
				return element.getValue();
			}
		}
		LOG.error("Found a Type that matches the enumeration {}, but none of its EnumerationLiterals matched the label: {}", name, literal);
		throw new EolEnumerationValueNotFoundException(name, literal, this.modelName);
	}
	

	/**
	 * If the type is if a known metaclass, it will get all element of that type.
	 * If not, it will check if the type is a known stereotype. If so, it will get all elements
	 * that have the stereotype as the UserDefinedMetaClass.
	 * 
	 * @param type the metaclass or stereotype to match
	 * @return a Collection of matching elements
	 * @throws EolModelElementTypeNotFoundException if the type is not a known metaclass or stereotype
	 */
	public Collection<IRPModelElement> getAllOfType(final String type) throws EolModelElementTypeNotFoundException {
		HashSet<IRPModelElement> matching = new HashSet<>();
		LOG.info("get All Of Type {}", type);
		if (this.metaclasses.contains(type)) {
			LOG.info("Type {} found in metaclasses", type);
			var actualType = type;
			// Bug 1. Metaclasses.txt lists Reception, but correct MetaClass is 'EventReception'
			if (Objects.equals("Reception", type)) {
				LOG.info("Type is Reception, using EventReception");
				actualType = "EventReception";
			}
			var contents = prj.getNestedElementsRecursive();
			for (int i=1; i <= contents.getCount(); i++) {
				var element = (IRPModelElement) contents.getItem(i);
				if (Objects.equals(actualType, element.getMetaClass())) {
					matching.add(element);
				}
			}
		} else if (stereotypes().contains(type)) {
			LOG.info("Type {} found in stereotypes", type);
			matching.addAll(this.getAllByStereotype(type));
		} else {
			LOG.error("Type {} is not in the list of valid metaclasses or a known stereotype", type);
			throw new EolModelElementTypeNotFoundException(this.modelName, type);	
		}
		return matching;
	}
	
	/**
	 * If the type is if a known metaclass, it will get all element of that type and its subtypes.
	 * If not, it will check if the type is a known stereotype. If so, it will get all elements
	 * that have the stereotype as the UserDefinedMetaClass.
	 * 
	 * @param type the metaclass or stereotype to match
	 * @return a Collection of matching elements
	 * @throws EolModelElementTypeNotFoundException if the type is not a known metaclass or stereotype
	 */
	public Collection<IRPModelElement> getAllOfKind(String kind) throws EolModelElementTypeNotFoundException {
		LOG.info("get All Of Kind {}", kind);
		HashSet<IRPModelElement> matching = new HashSet<>();
		if (this.metaclasses.contains(kind)) {
			LOG.info("Kind {} found in metaclasses", kind);
			var it = prj.getNestedElementsByMetaClass(kind, 1).toList().iterator();
			while(it.hasNext()) {
				IRPModelElement element = (IRPModelElement) it.next();
				matching.add(element);
			}
		} else if (stereotypes().contains(kind)) {
			LOG.info("Kind {} found in stereotypes", kind);
			matching.addAll(this.getAllByStereotype(kind));
		} else {
			throw new EolModelElementTypeNotFoundException(this.modelName, kind);	
		}
		return matching;
	}
	
	/**
	 * Returns the base metaclass name of the element and, id the element has a newTerm stereotype
	 * applied, it will return that name too.
	 * 
	 * @param instance the element
	 * @return a collection with all the known type names of the instance
	 */
	public Collection<String> getAllTypeNamesOf(Object instance) {
		Set<String> result = new HashSet<>();
		if (instance instanceof IRPModelElement) {
			var element = (IRPModelElement) instance;
			result.add(element.getMetaClass());
			String newTerm = element.getUserDefinedMetaClass();
			if (newTerm != null) {
				result.add(newTerm);
			}
		}
		return result;
	}
	
	/**
	 * Return true if the type is a known metaclass or stereotype.
	 * 
	 * @param type the type name to test
	 * @return true, if it is a known metaclass or stereotype
	 */
	public boolean hasType(String type) {
		return this.metaclasses.contains(type) || this.stereotypes().contains(type);
	}
	
	/**
	 * Check if the given type is instantiatable. All known types and stereotypes are instantiatable.
	 * 
	 * @param type the type name to check
	 * @return true, if the type is known
	 */
	public boolean isInstantiable(String type) {
		return this.hasType(type);
	}
	
	/**
	 * Checks if the type is a metaclass. This method can be used to distinguish between metaclasses
	 * and stereotypes
	 * 
	 * @param type the type to check
	 * @return true if the type is a metaclass
	 */
	public boolean isMetaclass(String type) {
		return this.metaclasses.contains(type);
	}

	private static final Logger LOG = LogManager.getLogger(RhapsodyMetaclasses.class);
	
	private final Set<String> metaclasses;
	private final boolean cachingEnabled;
	private final Path path;
	private final IRPProject prj;
	private final String modelName;
	private final Set<String> stereotypes;
	
	private RhapsodyMetaclasses(
		Path path,
		boolean cachingEnabled,
		IRPProject prj,
		String modelName,
		Set<String> metaclasses,
		Set<String> stereotypes) {
		this.path = path;
		this.prj = prj;
		this.modelName = modelName;
		this.metaclasses = metaclasses;
		this.stereotypes = stereotypes;
		this.cachingEnabled = cachingEnabled;
	}
	
	/**
	 * Loads and returns the set of metaclasses supported by Rhapsody
	 * @return a set of the metaclasses names
	 */
	private Set<String> metaclasses() {
		LOG.info("Loading metaclasses from Metaclasses.txt" );
		String mcList = "";
		try(BufferedReader brTest = new BufferedReader(
				new FileReader(this.path.resolve("Doc/metaclasses.txt").toFile()))) {
			mcList = brTest .readLine();	
		} catch (IOException e) {
			LOG.error("Unable to read the metaclasses file at given path: {}", this.path);
			throw new IllegalStateException("Unable to load Rhapsody's Metaclasses names", e);
		}
		return new HashSet<>(Arrays.asList(mcList.split(",")));
	}
	
	/**
	 * Get stereotypes present in project
	 * @return the set of stereotype names
	 */
	private Set<String> stereotypes() {
		if (this.stereotypes.isEmpty() || !this.cachingEnabled) {
			LOG.info("Loading stereotypes from model" );
			this.stereotypes.clear();
			IRPCollection sts = this.prj.getAllStereotypes();
			for(int i=1;i<sts.getCount();i++) {
				IRPStereotype stereotype = (IRPStereotype) sts.getItem(i);
				if (stereotype.getIsNewTerm() == 1) {
					stereotypes.add(stereotype.getName());
				}
				//System.out.println(stereotype.getName() + " newTerm?: " + stereotype.getIsNewTerm());
			}	
		}
		return stereotypes;
	}
	
	/**
	 * Get all the model elements that 
	 * @param stereotype
	 * @return
	 * @throws EolModelElementTypeNotFoundException
	 */
	private Collection<IRPModelElement> getAllByStereotype(String stereotype) throws EolModelElementTypeNotFoundException {
		HashSet<IRPModelElement> matching = new HashSet<>();
		var contents = prj.getNestedElementsRecursive();
		for (int i=1; i<=contents.getCount(); i++) {
			var element = (IRPModelElement)contents.getItem(i);
			var newTerm =  element.getUserDefinedMetaClass();
			if (newTerm != null && Objects.equals(stereotype, newTerm)) {
				matching.add(element);	
			}
		}
		return matching;
	}

}
