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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.epsilon.common.util.StringProperties;
import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
import org.eclipse.epsilon.eol.exceptions.models.EolEnumerationValueNotFoundException;
import org.eclipse.epsilon.eol.exceptions.models.EolModelElementTypeNotFoundException;
import org.eclipse.epsilon.eol.exceptions.models.EolModelLoadingException;
import org.eclipse.epsilon.eol.exceptions.models.EolNotInstantiableModelElementTypeException;
import org.eclipse.epsilon.eol.execute.introspection.IPropertyGetter;
import org.eclipse.epsilon.eol.execute.introspection.IPropertySetter;
import org.eclipse.epsilon.eol.models.IModel;
import org.eclipse.epsilon.eol.models.IRelativePathResolver;
import org.eclipse.epsilon.eol.models.Model;

import com.telelogic.rhapsody.core.IRPApplication;
import com.telelogic.rhapsody.core.IRPEnumerationLiteral;
import com.telelogic.rhapsody.core.IRPModelElement;
import com.telelogic.rhapsody.core.IRPPackage;
import com.telelogic.rhapsody.core.IRPProject;
import com.telelogic.rhapsody.core.RhapsodyAppServer;
import com.telelogic.rhapsody.core.RhapsodyRuntimeException;

/**
 * 
 * Since the model is a live connection to the Rhapsody Application, this implementation
 * is not cached.
 * <p>
 * The Rhapsody Aplication can be running or not. If not running, a new instance will be opened
 * and closed when the model is disposed. If running, the application will not be closed.
 * <p>
 * <b>Note: </b> Some actions may trigger an UI prompt from the Rhapsody API, execution will
 * be blocked until the dialog is closed by the user. For example, when loading a project
 * with invalid extension or path.
 * <p>
 * The model must be loaded via one of the load methods that accepts a {@link StringProperties} 
 * parameter. The following properties are supported:
 * <ul>
 * 	<li> {@link RhapsodyModel#PROPERTIES_INSTALLATION_DIRECTORY}: the path to the Rhapsody
 * 		installation. Should point to the top folder of the specific version, e.g. 
 * 		'C:\Program Files\IBM\Rhapsody\9.0.1'
 * 	<li> {@link RhapsodyModel#PROPERTIES_PROJECT_PATH}:  (optional) the path to the Rhapsody project
 * 		 to use. If absent, the current project opened in Rhapsody will be used (if available).
 *  <li> {@link RhapsodyModel#PROPERTIES_PROJECT_PATH}: (optional) the model name, defaults to 'Rhapsody'
 * </ul>
 * 
 * @author Justin Dang - Initial Version
 * @author Horacio Hoyos Rodriguez - Refactoring and complete implementation
 *
 */
public class RhapsodyModel extends Model implements IModel {
	
	public static final String PROPERTIES_PROJECT_PATH = "path";
	public static final String PROPERTIES_INSTALLATION_DIRECTORY = "install_dir";
	public static final String PROPERTIES_MODEL_NAME = "name";

	private static final Logger LOG = LogManager.getLogger(RhapsodyModel.class);
	
	private IRPApplication app;
	private IRPProject prj;
	private IRPPackage localpackage;
	private boolean usingActivePrj = false;
	private boolean rhapsodyWasActive = false;
	
	
	public RhapsodyModel() {
		propertyGetter = new RhapsodyPropertyGetter(this);
		propertySetter = new RhapsodyPropertySetter(this);
	}
	
	@Override
	public void load() throws EolModelLoadingException {
		LOG.error("Rhapsody models need a properties file that includes a path to the Rhapsody installation");
		throw new EolModelLoadingException(new UnsupportedOperationException("Rhapsody models can't be loaded without a properties file"), this);
	}

	// TODO Read the file metaclasses.txt in the Doc directory of the Rhapsody installation.
	@Override
	public void load(
		StringProperties properties,
		IRelativePathResolver relativePathResolver) throws EolModelLoadingException {
		setName(properties.getProperty(PROPERTY_NAME, "Rhapsody"));
		if (!properties.hasProperty(PROPERTIES_INSTALLATION_DIRECTORY)) {
			LOG.error("No path to the Rhapsody installation provided");
			throw new EolModelLoadingException(new IllegalArgumentException("No path to the Rhapsody installation provided"), this);
		}
		try {
			this.app = connectToRhapsody();
		} catch (UnsatisfiedLinkError e) {
			LOG.error("Rhapsody DLL not found in java library path");
			throw new EolModelLoadingException(new IllegalStateException("Error in project setup"), this);
		} catch (RhapsodyRuntimeException e) {
			LOG.error("Rhapsody not running");
			throw new EolModelLoadingException(new IllegalStateException("Rhapsody not running"), this);
		}
		if(!properties.hasProperty(PROPERTIES_PROJECT_PATH)) {
			LOG.info("No model path provided, loading active project");
			this.prj = this.app.activeProject();
			if (this.prj == null) {
				LOG.error("No active project on Rhapsody");
				throw new EolModelLoadingException(new IllegalStateException("No active project on Rhapsody"), this);
			}
			this.usingActivePrj = true;
		} else {
			String path = properties.getProperty(PROPERTIES_PROJECT_PATH);
			String fullpath = relativePathResolver.resolve(path);
			LOG.info("Loading project from: {}", fullpath);
			this.prj = this.app.openProject(fullpath);
			if (this.prj == null) {
				LOG.error("Invalid project name and/or path");
				throw new EolModelLoadingException(new IllegalArgumentException("Invalid project name and/or path"), this);
			}
		}
		LOG.info("Current project is: {}", this.prj.getName());
		setReadOnLoad(properties.getBooleanProperty(PROPERTY_READONLOAD, true));
		setStoredOnDisposal(properties.getBooleanProperty(PROPERTY_STOREONDISPOSAL, false));
	}
	
	
	private IRPApplication connectToRhapsody() throws RhapsodyRuntimeException {
		IRPApplication result;
		try {
			result = RhapsodyAppServer.getActiveRhapsodyApplication();
			this.rhapsodyWasActive = true;
		} catch (RhapsodyRuntimeException e) {
			result = RhapsodyAppServer.createRhapsodyApplication();
		}
		return result;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public List<String> getAliases() {
		aliases.add(name);
		return aliases;
	}

	@Override
	public Object getEnumerationValue(String enumeration, String label) throws EolEnumerationValueNotFoundException {
		var contents = prj.getNestedElementsRecursive();
		for (int i = 1; i <= contents.getCount(); i++) {
			var element = (IRPModelElement)contents.getItem(i);
			if (element!=null && element.getMetaClass().equals("EnumerationLiteral") && element.getName().equals(enumeration)) {
				var enumerationLiteral = (IRPEnumerationLiteral) element;
				return enumerationLiteral.getValue();
			}
		}
		throw new EolEnumerationValueNotFoundException(enumeration,label,name);
	}

	@Override
	public Collection<?> allContents() {
		return prj.getNestedElementsRecursive().toList();
	}

	@Override
	public Collection<?> getAllOfType(String type) throws EolModelElementTypeNotFoundException {
		var contents = prj.getNestedElementsRecursive();
		var matching = app.createNewCollection();
		for (int i = 1; i <= contents.getCount(); i++) {
			var element = (IRPModelElement)contents.getItem(i);
			if (element!=null && type.equals(element.getMetaClass()))
				matching.addItem(element);
		}
		return matching.toList();
	}

	@Override
	public Collection<?> getAllOfKind(String kind) throws EolModelElementTypeNotFoundException {
		var contents = prj.getNestedElementsRecursive();
		var matching = app.createNewCollection();
		for (int i = 1; i <= contents.getCount(); i++) {
			var element = (IRPModelElement)contents.getItem(i);
			if (element!=null && element.getIsOfMetaClass(kind)==1 )
				matching.addItem(element);
		}
		return matching.toList();
	}

	@Override
	public Object getTypeOf(Object instance) {
		var element = (IRPModelElement) instance;
		return element.getInterfaceName();
	}

	@Override
	public String getTypeNameOf(Object instance) {
		var element = (IRPModelElement) instance;
		return element.getInterfaceName();
	}

	@Override
	public String getFullyQualifiedTypeNameOf(Object instance) {
		return getTypeNameOf(instance);
	}

	@Override
	public Object createInstance(String type)
			throws EolModelElementTypeNotFoundException, EolNotInstantiableModelElementTypeException {
		if (!hasType(type)){
			throw new EolModelElementTypeNotFoundException(prj.getName(), type);
		} else if (!isInstantiable(type)) {
			throw new EolNotInstantiableModelElementTypeException(prj.getName(), type);
		}
		if (localpackage==null)
			localpackage = prj.addPackage("New_Instances");
		if (type.equals("Type"))
			return localpackage.addType("default_name");
		else if (type.equals("Class"))
			return localpackage.addClass("default_name");
		else if (type.equals("Event"))
			return localpackage.addEvent("default_name");
		else if (type.equals("Package"))
			return localpackage.addNestedPackage("default_name");
		else if (type.equals("Component"))
			return prj.addComponent("default_name");
		else
			throw new EolNotInstantiableModelElementTypeException(prj.getName(), type);
	}

	@Override
	public Object createInstance(String type, Collection<Object> parameters)
			throws EolModelElementTypeNotFoundException, EolNotInstantiableModelElementTypeException {
		return createInstance(type);
	}

	@Override
	public Object getElementById(String id) {
		return prj.findElementByGUID(id);
	}

	@Override
	public String getElementId(Object instance) {
		var element = (IRPModelElement) instance;
		return element.getGUID();
	}

	@Override
	public void setElementId(Object instance, String newId) {
		var element = (IRPModelElement) instance;
		element.setGUID(newId);
	}

	@Override
	public void deleteElement(Object instance) throws EolRuntimeException {
		var element = (IRPModelElement) instance;
		element.deleteFromProject();
	}

	@Override
	public boolean isOfKind(Object instance, String type) throws EolModelElementTypeNotFoundException {
		var element = (IRPModelElement) instance;
		return element.getIsOfMetaClass(type)==1;
	}

	@Override
	public boolean isOfType(Object instance, String type) throws EolModelElementTypeNotFoundException {
		var element = (IRPModelElement) instance;
		return element.getIsOfMetaClass(type)==1;
	}

	@Override
	public boolean owns(Object instance) {
		if (!isModelElement(instance))
			return false;
		var contents = prj.getNestedElementsRecursive();
		for (int i = 1; i <= contents.getCount(); i++) {
			var element = (IRPModelElement)contents.getItem(i);
			if (element!=null && element==instance)
				return true;
		}
		return false;
	}

	@Override
	public boolean knowsAboutProperty(Object instance, String property) {
		if (!isModelElement(instance))
			return false;
		var modelElement = (IRPModelElement) instance;
		try {
			modelElement.getPropertyValue(property);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean isPropertySet(Object instance, String property) throws EolRuntimeException {
		return getPropertyGetter().invoke(instance, property, null) != null;
	}

	@Override
	public boolean isInstantiable(String type) {
		ArrayList<String> instantiable = new ArrayList<String>(List.of("Type","Class","Package","Component","Event"));
		return instantiable.contains(type);
	}

	@Override
	public boolean isModelElement(Object instance) {
		return (instance instanceof IRPModelElement);
	}

	@Override
	public boolean hasType(String type) {
 		boolean hasType = false;
		for (Object object: this.allContents()) {
			IRPModelElement element = (IRPModelElement) object;
			if (element.getMetaClass().equals(type))
				hasType = true;
		}
		return hasType;
	}

	@Override
	public boolean store(String location) {
		prj.saveAs(location);
		return true;
	}

	@Override
	public boolean store() {
		prj.save();
		return true;
	}

	@Override
	public void dispose() {
		if (this.storeOnDisposal) {
			this.store();
		}
		if (this.prj != null) {
			if (!this.usingActivePrj) {
				this.prj.close();					
			}
		}
		if(!this.rhapsodyWasActive && (this.app != null)) {
			this.app.quit();
		}
		// terminate the Rhapsody session
		RhapsodyAppServer.CloseSession();
	}

	@Override
	public IPropertyGetter getPropertyGetter() {
		return propertyGetter;
	}

	@Override
	public IPropertySetter getPropertySetter() {
		return propertySetter;
	}

	@Override
	public boolean isStoredOnDisposal() {
		return storeOnDisposal;
	}

	@Override
	public void setStoredOnDisposal(boolean storedOnDisposal) {
		storeOnDisposal = storedOnDisposal;
	}

	@Override
	public boolean isReadOnLoad() {
		return readOnLoad;
	}

	@Override
	public void setReadOnLoad(boolean readOnLoad) {
		this.readOnLoad = readOnLoad;
	}


	
	/*
	 * public Boolean checkIfChanged() { return prj.isModifiedRecursive()==1; }
	 * 
	 * public void addProperty(String object_name, String property) {
	 * IRPModelElement object = localpackage.findType(object_name);
	 * object.addProperty("CG.Type." + property, "Bool", "True"); }
	 * 
	 * public void addPackage(String name) { IRPPackage pack = prj.addPackage(name);
	 * localpackage = pack; package_list.add(pack); }
	 * 
	 * public Boolean setPackage(String name) { for (IRPPackage pack: package_list)
	 * { if (name.equals(pack.getName())){ localpackage = pack; return true; } }
	 * return false; }
	 * 
	 * public void addType(String name) { localpackage.addType(name); }
	 * 
	 * public String readProperty(String object_name, String property) {
	 * IRPModelElement object = localpackage.findType(object_name); return
	 * object.getPropertyValue(property); }
	 * 
	 * public void setProperty(String object_name, String property, String value) {
	 * IRPModelElement object = localpackage.findType(object_name);
	 * object.setPropertyValue(property, value); }
	 * 
	 * public String getCurrentProjectName() { return prj.getName(); }
	 * 
	 * public IRPPackage getCurrentPackage() { return localpackage; }
	 */

}
