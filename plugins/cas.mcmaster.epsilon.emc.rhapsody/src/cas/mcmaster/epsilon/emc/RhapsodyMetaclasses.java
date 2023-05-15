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
		IRPProject prj) {
		this(Path.of(path), cachingEnabled, prj, new HashSet<>(), new HashSet<>());
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
				this.metaclasses(),
				this.stereotypes());
	}
	
	public Object getEnumerationValue(
		String enumeration,
		String label,
		String model) throws EolEnumerationValueNotFoundException {
		var irpEnum = (IRPType) this.prj.findNestedElementRecursive(enumeration, "Type");
		if (irpEnum == null) {
			LOG.error("Unable to find a Type that matches the enumeration name: {}", enumeration);
			throw new EolEnumerationValueNotFoundException(enumeration, label, model);
		}
		var enumLiterals = irpEnum.getEnumerationLiterals();
		for (int i=1; i <= enumLiterals.getCount(); i++) {
			var element = (IRPEnumerationLiteral) enumLiterals.getItem(i);
			if (element.getName().equals(label)) {
				return element.getValue();
			}
		}
		LOG.error("Found a Type that matches the enumeration {}, but none of its EnumerationLiterals matched the label: {}", enumeration, label);
		throw new EolEnumerationValueNotFoundException(enumeration,label,model);
	}
	

	/**
	 * If the type is if a known metaclass, it will get all element of that type.
	 * If not, it will check if the type is a known stereotype. If so, it will get all elements
	 * that have the stereotype attached.
	 * @param type the metaclass or stereotype to match
	 * @param model the model name
	 * @return a Collection of matching elements
	 * @throws EolModelElementTypeNotFoundException 
	 */
	public Collection<IRPModelElement> getAllOfType(String type, String model) throws EolModelElementTypeNotFoundException {
		HashSet<IRPModelElement> matching = new HashSet<>();
		if (this.metaclasses.contains(type)) {
			LOG.info("Type found in metaclasses, finding by metaclass");
			var contents = prj.getNestedElementsRecursive();
			for (int i=1; i <= contents.getCount(); i++) {
				var element = (IRPModelElement) contents.getItem(i);
				if (Objects.equals(type, element.getMetaClass())) {
					matching.add(element);
				}
			}
		} else {
			LOG.info("Type not found in metaclasses, finding by stereotypes");
			if (!stereotypes().contains(type)) {
				LOG.error("Type not found in stereotypes");
				throw new EolModelElementTypeNotFoundException(model, type);
			}
			var contents = prj.getNestedElementsRecursive();
			for (int i=1; i<=contents.getCount(); i++) {
				var element = (IRPModelElement)contents.getItem(i);
				var appliedStrtyps = element.getStereotypes();
				for (int j=1; j<=appliedStrtyps.getCount(); j++) {
					var strtyp =  (IRPStereotype) appliedStrtyps.getItem(j);
					if (strtyp != null && Objects.equals(type, strtyp.getName())) {
						matching.add(element);	
					}
				}
			}
		}
		return matching;
	}
	
	public Collection<String> getAllTypeNamesOf(Object instance) {
		Set<String> result = new HashSet<>();
		if (instance instanceof IRPModelElement) {
			var element = (IRPModelElement) instance;
			result.add(element.getMetaClass());
			var appliedStrtyps = element.getStereotypes();
			for (int j=1; j<=appliedStrtyps.getCount(); j++) {
				var strtyp =  (IRPStereotype) appliedStrtyps.getItem(j);
				if (strtyp != null) {
					result.add(strtyp.getName());	
				}
			}
		}
		return result;
	}
	
	public boolean hasType(String type) {
		return this.metaclasses.contains(type) || this.stereotypes().contains(type);
	}
	
	public boolean isInstantiable(String type) {
		return this.metaclasses.contains(type);
	}

	private static final Logger LOG = LogManager.getLogger(RhapsodyMetaclasses.class);
	
	private final Set<String> metaclasses;
	private final boolean cachingEnabled;
	private final Path path;
	private final IRPProject prj;

	private final Set<String> stereotypes;
	
	private RhapsodyMetaclasses(
		Path path,
		boolean cachingEnabled,
		IRPProject prj,
		Set<String> metaclasses,
		Set<String> stereotypes) {
		this.path = path;
		this.prj = prj;
		this.metaclasses = metaclasses;
		this.stereotypes = stereotypes;
		this.cachingEnabled = cachingEnabled;
	}
	
	/**
	 * Loads and returns the set of metaclasses supported by Rhapsody
	 * @return
	 */
	private Set<String> metaclasses() {
		String mcList = "";
		try(BufferedReader brTest = new BufferedReader(
				new FileReader(this.path.resolve("Doc/Metaclasses.txt").toFile()))) {
			mcList = brTest .readLine();	
		} catch (IOException e) {
			LOG.error("Unable to read the metaclasses file at given path: {}", this.path);
			throw new IllegalStateException("Unable to load Rhapsody's Metaclasses names", e);
		}
		return new HashSet<>(Arrays.asList(mcList.split(",")));
	}
	
	/**
	 * Get stereotypes present in project
	 * @return the set of stereotypes
	 */
	private Set<String> stereotypes() {
		if (this.stereotypes.isEmpty() || !this.cachingEnabled) {
			this.stereotypes.clear();
			IRPCollection sts = this.prj.getAllStereotypes();
			for(int i=1;i<sts.getCount();i++) {
				IRPStereotype stereotype = (IRPStereotype) sts.getItem(i);
				stereotypes.add(stereotype.getName()); 
			}	
		}
		return stereotypes;
	}

	

}
