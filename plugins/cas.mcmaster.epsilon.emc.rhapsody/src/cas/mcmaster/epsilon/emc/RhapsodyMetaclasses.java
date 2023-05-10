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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.epsilon.eol.exceptions.models.EolEnumerationValueNotFoundException;

import com.telelogic.rhapsody.core.IRPCollection;
import com.telelogic.rhapsody.core.IRPEnumerationLiteral;
import com.telelogic.rhapsody.core.IRPModelElement;
import com.telelogic.rhapsody.core.IRPProject;
import com.telelogic.rhapsody.core.IRPStereotype;
import com.telelogic.rhapsody.core.IRPType;

/**
 * The Class RhapsodyMetaclasses loads the supported metaclasses from the Rhapsody installation 
 */
public class RhapsodyMetaclasses {
	
	public RhapsodyMetaclasses(
		String path,
		IRPProject prj) {
		this(Path.of(path), prj, new ArrayList<>(), new ArrayList<>());
	}
	
	/**
	 * Load the type information.
	 * @return a loaded version of this instance
	 * @throws IllegalStateException if the type information can't be loaded 
	 */
	public RhapsodyMetaclasses load() {
		String mcList = "";
		try(BufferedReader brTest = new BufferedReader(
				new FileReader(this.path.resolve("Doc/Metaclasses.txt").toFile()))) {
			mcList = brTest .readLine();	
		} catch (IOException e) {
			LOG.error("Unable to read the metaclasses file at given path: {}", this.path);
			throw new IllegalStateException("Unable to load Rhapsody's Metaclasses names", e);
		}
		List<String> stereotypes = new ArrayList<>();
		IRPCollection sts = this.prj.getAllStereotypes();
		for(int i=1;i<sts.getCount();i++) {
			IRPStereotype stereotype = (IRPStereotype) sts.getItem(i);
			stereotypes.add(stereotype.getName()); 
		}
		return new RhapsodyMetaclasses(
				this.path,
				this.prj,
				Arrays.asList(mcList.split(",")),
				stereotypes);
	}
	
	public Object getEnumerationValue(
		String enumeration,
		String label,
		String model) throws EolEnumerationValueNotFoundException {
		var irpEnum = (IRPType) this.prj.findNestedElementRecursive(enumeration, "Type");
		if (irpEnum == null) {
			throw new EolEnumerationValueNotFoundException(enumeration,label,model);
		}
		var enumLiterals = irpEnum.getEnumerationLiterals();
		for (int i=1; i <= enumLiterals.getCount(); i++) {
			var element = (IRPEnumerationLiteral) enumLiterals.getItem(i);
			if (element.getName().equals(label)) {
				return element.getValue();
			}
		}
		throw new EolEnumerationValueNotFoundException(enumeration,label,model);
	}

	private static final Logger LOG = LogManager.getLogger(RhapsodyMetaclasses.class);
	
	
	private final List<String> metaclasses;
	/** Stereotypes can't be used for getAllOfKind */
	private final List<String> stereotypes;
	private final Path path;
	private final IRPProject prj;
	
	
	private RhapsodyMetaclasses(
		Path path,
		IRPProject prj,
		List<String> metaclasses,
		List<String> stereotypes) {
		this.path = path;
		this.metaclasses = metaclasses;
		this.stereotypes = stereotypes;
		this.prj = prj;
	}
	
	
	
	
	/*
	 * private Collection<IRPModelElement> getAllOfStereotype() { for(Object
	 * o1:prj.getPackages().toList()) { IRPPackage pkg = (IRPPackage)o1;
	 * //System.out.println(pkg.getName());
	 * 
	 * for(Object o2:pkg.getGlobalObjects().toList()) { IRPInstance obj =
	 * (IRPInstance)o2; //System.out.println(obj.getName());
	 * 
	 * for(Object o3:obj.getStereotypes().toList()) { IRPStereotype streo =
	 * (IRPStereotype)o3; if(streo.getName().equals("pack1Stereo"))
	 * System.out.println(obj.getName());
	 * 
	 * } } }
	 * 
	 * }
	 */
	
}
