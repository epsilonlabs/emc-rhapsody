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

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

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
import org.eclipse.epsilon.eol.models.CachedModel;
import org.eclipse.epsilon.eol.models.IModel;
import org.eclipse.epsilon.eol.models.IRelativePathResolver;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.telelogic.rhapsody.core.IRPApplication;
import com.telelogic.rhapsody.core.IRPCollection;
import com.telelogic.rhapsody.core.IRPModelElement;
import com.telelogic.rhapsody.core.IRPPackage;
import com.telelogic.rhapsody.core.IRPProject;
import com.telelogic.rhapsody.core.IRPStereotype;
import com.telelogic.rhapsody.core.RhapsodyAppServer;
import com.telelogic.rhapsody.core.RhapsodyRuntimeException;

/**
 * This IModel implementation provides access to Rhapsody projects/models via the Rhapsody JAVA API.
 * The Rhapsody Application can be running or not. If not running, a new instance will be opened
 * and closed when the model is disposed. If running, the application will not be closed. If a 
 * project is not provided during load, the active project in Rhapsody will be used.
 * <p>
 * <b>Note: </b> Some actions may trigger an UI prompt from the Rhapsody API, execution will
 * be blocked until the dialog is closed by the user. For example, when loading a project
 * with invalid extension or path.
 * <p>
 * The model must be loaded via one of the load methods that accepts a {@link StringProperties} 
 * parameter. The following properties are supported:
 * <ul>
 * 	<li> {@link RhapsodyModel#PROPERTY_INSTALLATION_DIRECTORY}: the path to the Rhapsody
 * 		installation. Should point to the top folder of the specific version, e.g. 
 * 		'C:\Program Files\IBM\Rhapsody\9.0.1'
 * 	<li> {@link RhapsodyModel#PROPERTY_PROJECT_PATH}:  (optional) the path to the Rhapsody project
 * 		 to use. If absent, the current project opened in Rhapsody will be used (if available).
 *  <li> {@link RhapsodyModel#PROPERTY_MAIN_PACKAGE_NAME}: (optional) the main package name, defaults 
 *  		to the first package in the model. The main package is where new elements will be 
 *  		created.
 *  <li> {@link RhapsodyModel#PROPERTY_SOFT_DISPOSE}: (optional) if true, changes will not be saved 
 *  		when calling {@link #store()}. The model will be kept "opened" even after calling {@link #dispose()}.
 *  		This mode is useful for ANT workflows that benefit from not having to open a new Rhapsody 
 *  		connection and loading the model multiple times (e.g. EUnit). To dispose the model, 
 *  		the {@link #dispose()} method must be called twice.
 * </ul>
 * <p>
 * Type operations (e.g. allOfType, allofKind, createInstance, etc) rely on two sources of information.
 * i) The <code>metaclasses.txt</code> file that contains the list of supported metaclass (type) names,
 * and ii) the existing <code>Stereotype</code>s in the model. For the latter, only Stereotypes that
 * are defined as <b>new terms</b> will be considered for type related operations. 
 * 
 * @author Justin Dang - Initial Version
 * @author Horacio Hoyos Rodriguez - Refactoring and complete implementation
 *
 */
public class RhapsodyModel extends CachedModel<IRPModelElement> implements IModel {
	
	public static final String PROPERTY_PROJECT_PATH = "prj_path";
	public static final String PROPERTY_INSTALLATION_DIRECTORY = "install_dir";
	public static final String PROPERTY_MAIN_PACKAGE_NAME = "main_package";
	public static final String PROPERTY_SOFT_DISPOSE = "soft_dispose";
	public static final String PROPERTY_ROOT_ELEM = "root_elem";

	public RhapsodyModel() {
		// TODO Cache should be created during load to use user options.
		this.propertyCache = Caffeine.newBuilder()
				.expireAfterWrite(10, TimeUnit.MINUTES)
			    .maximumSize(10_000)
			    .build();
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
	public void load(
		StringProperties properties,
		IRelativePathResolver relativePathResolver) throws EolModelLoadingException {
		super.load(properties, relativePathResolver);
		if (!properties.hasProperty(PROPERTY_INSTALLATION_DIRECTORY)) {
			LOG.error("No path to the Rhapsody installation provided");
			throw new EolModelLoadingException(new IllegalArgumentException("No path to the Rhapsody installation provided"), this);
		}
		try {
			this.app = connectToRhapsody();
		} catch (UnsatisfiedLinkError e) {
			LOG.error("Rhapsody DLL not found in java library path");
			throw new EolModelLoadingException(new IllegalStateException("Error in project setup, Rhapsody DLL not found in java library path"), this);
		} catch (RhapsodyRuntimeException e) {
			LOG.error("Rhapsody not running");
			throw new EolModelLoadingException(new IllegalStateException("Rhapsody not running"), this);
		}
		
		this.softDispose = properties.getBooleanProperty(PROPERTY_SOFT_DISPOSE, false);
		if (this.softDispose) {
			LOG.info("Starting uno transaction");
			this.app.startUndoTransaction();
			this.canDispose = false;
		}
		
		if (properties.hasProperty(PROPERTY_PROJECT_PATH)) {
			String path = properties.getProperty(PROPERTY_PROJECT_PATH);
			Path fullPath = Paths.get(relativePathResolver.resolve(path)).toAbsolutePath();
			LOG.info("Loading project from: {}", fullPath);
			if (this.prj == null) {
				this.prj = this.app.openProject(fullPath.toString());
				if (this.prj == null) {
					LOG.error("Invalid project name and/or path");
					throw new EolModelLoadingException(new IllegalArgumentException("Invalid project name and/or path"), this);
				}
			}
		} else {
			LOG.info("No model path provided, loading active project");
			this.prj = this.app.activeProject();
			if (this.prj == null) {
				LOG.error("No active project on Rhapsody");
				throw new EolModelLoadingException(new IllegalStateException("No active project on Rhapsody"), this);
			}
			this.usingActivePrj = true;
		}
		if (properties.hasProperty(PROPERTY_MAIN_PACKAGE_NAME)) {
			String pkgName = properties.getProperty(PROPERTY_MAIN_PACKAGE_NAME);
			this.mainPackage = (IRPPackage) this.prj.findNestedElement(pkgName, "Package");
			if (this.mainPackage == null) {
				LOG.error("A package with name {} to use as main package was not found", pkgName);
				throw new EolModelLoadingException(new IllegalArgumentException("A package with name " + pkgName + " could not be found." ), this);
			}
		} else {
			IRPCollection allPkgs = this.prj.getPackages();
			if (allPkgs.getCount() == 0) {
				throw new EolModelLoadingException(new IllegalStateException("No packages found in project."), this);
			}
			this.mainPackage = (IRPPackage) allPkgs.getItem(1);
			LOG.info("Using package with name {} as main package.", this.mainPackage.getName());
		}
		// TODO If not cached, register the model as an RPApplicationListener so if the model
		// changes, we can clear the cache. https://www.ibm.com/docs/en/elms/esdr/8.4.0?topic=api-using-rpapplicationlistener-respond-events
		// but still use the cache for improved performance
		LOG.info("Current project is: {}", this.prj.getName());
		this.types = new RhapsodyMetaclasses(
				properties.getProperty(PROPERTY_INSTALLATION_DIRECTORY),
				properties.getBooleanProperty(PROPERTY_CACHED, false),
				this.prj,
				this.name
				)
			.load();
		clearCache();
		this.idPattern = Pattern.compile(ID_REGEX);
		this.propertyGetter = new RhapsodyPropertyGetter(this.propertyCache, this.app);
		this.propertySetter = new RhapsodyPropertySetter(this.propertyCache);
		
	}

	@Override
	public Object getEnumerationValue(
		String enumeration,
		String label) throws EolEnumerationValueNotFoundException {
		return this.types.getEnumerationValue(enumeration, label);
	}
	
	@Override
	public boolean hasType(String type) {
		return this.types.hasType(type);
	}
	
	@Override
	public boolean isInstantiable(String type) {
		return this.types.isInstantiable(type);
	}

	/**
	 * Creates the instance. The type can either be a metaclass or a stereotype. The provided 
	 * parameters can be used to to provide an element name and, for stereotypes, provide the 
	 * actual metaclass of the instance.
	 * <p>
	 * If the type is a metaclass, then the first element if the parameters collection will be used
	 * for the name. The name value will be the element's <code>toString()</code> value.  
	 * <p>
	 * If the type is a stereotype, then, if the parameters collection has only one element, it will
	 * be used as the name of the actual the metaclass for the instance. If two parameters are
	 * provided, then the first will be the element's name and the second the actual metaclass. 
	 *
	 * @param type the type of the new instance
	 * @param parameters instantiation parameters
	 * @return the object
	 * @throws EolModelElementTypeNotFoundException if the type/metaclass is not found in the model
	 * @throws EolNotInstantiableModelElementTypeException if the type is not instantiatable
	 */
	@Override
	public Object createInstance(String type, Collection<Object> parameters)
			throws EolModelElementTypeNotFoundException, EolNotInstantiableModelElementTypeException {
		if (!this.hasType(type)) {
			LOG.error("Unable to create instance for unknown type: {}");
			throw new EolModelElementTypeNotFoundException(this.getName(), type);
		}
		ElementFactory factory;
		String name = "";
		if (this.types.isMetaclass(type)) {
			LOG.info("Given type found in metaclasses.");
			Iterator<Object> it = parameters.iterator();
			if (it.hasNext()) {
				Object next = it.next();
				if (next != null) {
					name = next.toString();
				}
			}
			factory = new ElementFactory(type, name);
		} else {
			IRPStereotype sType = (IRPStereotype) this.prj.findNestedElementRecursive(type, "Stereotype");
			if (sType == null) {
				throw new IllegalStateException("The stereotype with name " + type + " should exist in the model.");
			}
			List<String> ofMetaClass = Arrays.asList(sType.getOfMetaClass().split(","));
			Iterator<Object> it = parameters.iterator();
			String metaClass = "";
			if (it.hasNext()) { // 1 element = metaclass
				Object next = it.next();
				if (next != null) {
					metaClass = next.toString();
				}
			}
			if (it.hasNext()) { // 2 elements = name, metaclass
				Object next = it.next();
				if (next != null) {
					name = metaClass;
					metaClass = next.toString();
				}
			}	
			if (!ofMetaClass.contains(metaClass)) {
					LOG.error("The provided metaclass for stereotype instantation is not in the "
							+ "list from getOfMetaClass() for stereotype {}", type);
					throw new EolNotInstantiableModelElementTypeException(this.getName(), type);
			}
			factory = new ElementFactory(metaClass, name, sType);
		}
		IRPModelElement instance = factory.create(this.mainPackage);
		if (isCachingEnabled()) {
			addToCache(type, instance);
		}
		return instance;
	}
	
	@Override
	public Object getTypeOf(Object instance) {
		if (instance == null) {
			LOG.error("Get type name of null");
			throw new IllegalArgumentException("Can't get type name of null");
		}
		if (isModelElement(instance)) {
			return ((IRPModelElement)instance).getInterfaceName();
		}
		LOG.error("Calling getTypeOf with an object that is not an IRPModelElement");
		throw new IllegalArgumentException("Instance is not a model element");
	}

	/**
	 * Gets the type name of.
	 *
	 * @param instance the instance
	 * @return the type name of the instance
	 */
	@Override
	public String getTypeNameOf(Object instance) {
		if (instance == null) {
			LOG.error("Get type name of null");
			throw new IllegalArgumentException("Can't get type name of null");
		}
		if (this.isModelElement(instance)) {
			IRPModelElement element = (IRPModelElement) instance;
			var newTerm = element.getUserDefinedMetaClass();
			if (newTerm != null) {
				return newTerm;
			}
			return element.getMetaClass();
		}
		LOG.error("Calling getTypeNameOf with an object that is not an IRPModelElement: {}", instance);
		throw new IllegalArgumentException("Instance is not a model element");
	}

	@Override
	public String getFullyQualifiedTypeNameOf(Object instance) {
		return getTypeNameOf(instance);
	}
	
	@Override
	public boolean isOfKind(Object instance, String type) throws EolModelElementTypeNotFoundException {
		if (instance == null) {
			LOG.warn("Checking the kind of null");
			return false;
		}
		if (this.isModelElement(instance)) {
			return this.getAllOfKind(type).contains(instance);
		}
		throw new IllegalArgumentException("Instance must be an IRPModelElement in order to check its kind");
	}

	@Override
	public boolean isOfType(Object instance, String type) throws EolModelElementTypeNotFoundException {
		if (instance == null) {
			LOG.warn("Checking the type of null");
			return false;
		}
		if (this.isModelElement(instance)) {
			return this.getAllOfType(type).contains(instance);
		}
		throw new IllegalArgumentException("Instance must be an IRPModelElement in order to check its type");
	}
	
	@Override
	public Object getElementById(String id) {
		if (!this.idPattern.matcher(id).matches()) {
			LOG.warn("Rhapsody ID have the format: GUID <UUID>. The supplied id: {} does not match this format.");
		};
		return prj.findElementByGUID(id);
	}

	@Override
	public String getElementId(Object instance) {
		if (instance == null) {
			LOG.error("Get id  of null");
			throw new IllegalArgumentException("Can't get ID of null");
		}
		if (this.isModelElement(instance)) {
			return ((IRPModelElement)instance).getGUID();
		}
		throw new IllegalArgumentException("Instance must be an IRPModelElement in order to get its ID");
	}

	@Override
	public void setElementId(Object instance, String newId) {
		if (instance == null) {
			LOG.error("Set id  of null");
			throw new IllegalArgumentException("Can't set ID of null");
		}
		if (this.isModelElement(instance)) {
			if (!this.idPattern.matcher(newId).matches()) {
				LOG.warn("Rhapsody IDs have the format: GUID xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx. The supplied id: {} does not match this format.");
				throw new IllegalArgumentException("Rhapsody IDs should start with the string 'GUID', "
						+ "followed by an UUID, e.g.: 'GUID xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx'. "
						+ "UUIDs are represented as 32 hexadecimal characers, displayed in five "
						+ "groups separated by hyphens, in the form 8-4-4-4-12");
			};
			var element = (IRPModelElement) instance;
			element.setGUID(newId);
		} else {
			throw new IllegalArgumentException("Instance must be an IRPModelElement in order to set its ID");
		}
	}
	
	@Override
	public boolean owns(Object instance) {
		if (!isModelElement(instance)) {
			return false;
		}
		var needle = (IRPModelElement)instance;
		return this.prj.equals(needle.getProject());
	}
	
	@Override
	public boolean isModelElement(Object instance) {
		return (instance instanceof IRPModelElement);
	}

	/**
	 * There is no documentation about properties of Rhapsody classes, so we will use reflection.
	 * Based on the Rhapsody API, we will try to find getX, isX and hasX, which are the method
	 * naming conventions we identified.
	 *
	 * @param instance the instance
	 * @param property the property
	 * @return true, if successful
	 */
	@Override
	public boolean knowsAboutProperty(Object instance, String property) {
		if (!isModelElement(instance)) {
			return false;
		}	
		return ((RhapsodyPropertyGetter) this.propertyGetter)
				.knowsAboutProperty((IRPModelElement) instance, property);
		
	}

	@Override
	public boolean isPropertySet(Object instance, String property) throws EolRuntimeException {
		// We assume all properties are set as there is no "eIsSet" equivalent in Rhapsody
		return true;
	}

	@Override
	public boolean store(String location) {
		prj.saveAs(location);
		return true;
	}

	@Override
	public boolean store() {
		if (this.softDispose) {
			return true;
		}
		prj.save();
		return true;
	}

	@Override
	public IPropertyGetter getPropertyGetter() {
		return propertyGetter;
	}

	@Override
	public IPropertySetter getPropertySetter() {
		return propertySetter;
	}
	
	public int appStatus() {
		try {
			return this.app.getApplicationStatus();
		} catch (RhapsodyRuntimeException ex) {
			return -1;
		}
	}
	
	@Override
	protected void loadModel() throws EolModelLoadingException {
		if (this.softDispose) {
			if (this.canDispose) {
				this.app.startUndoTransaction();
			}
			this.canDispose = false;
		} else {
			LOG.error("Rhapsody models need a properties file that includes a path to the Rhapsody installation");
			throw new EolModelLoadingException(new UnsupportedOperationException("Rhapsody models can't be loaded without a properties file"), this);
		}
	}

	@Override
	protected void disposeModel() {
		this.propertyCache.invalidateAll();
		if (this.softDispose && !this.canDispose) {
			this.canDispose = true;
		} else {
			LOG.info("Model in normal mode. Storing and closing.");
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
			RhapsodyAppServer.CloseSession();
		}
	}
	
	@Override
	protected Object getCacheKeyForType(String type) throws EolModelElementTypeNotFoundException {
		return type;
	}

	@Override
	protected Collection<IRPModelElement> allContentsFromModel() {
		@SuppressWarnings("unchecked")
		List<Object> x = prj.getNestedElementsRecursive().toList();
		return x.stream()
				.filter(IRPModelElement.class::isInstance)
				.map(IRPModelElement.class::cast)
				.collect(Collectors.toList());
	}

	@Override
	protected Collection<IRPModelElement> getAllOfTypeFromModel(String type)
			throws EolModelElementTypeNotFoundException {
		return this.types.getAllOfType(type);
	}

	@Override
	protected Collection<IRPModelElement> getAllOfKindFromModel(String kind)
			throws EolModelElementTypeNotFoundException {
		return this.types.getAllOfKind(kind);
	}

	@Override
	protected IRPModelElement createInstanceInModel(String type)
			throws EolModelElementTypeNotFoundException, EolNotInstantiableModelElementTypeException {
		if (!this.hasType(type)) {
			throw new EolModelElementTypeNotFoundException(this.getName(), type);
		}
		ElementFactory factory;
		if (this.types.isMetaclass(type)) {
			factory = new ElementFactory(type);
		} else {
			factory = new ElementFactory( (IRPStereotype) this.prj.findNestedElementRecursive(type, "Stereotype"));
		}
		return factory.create(this.mainPackage);
	}

	@Override
	protected boolean deleteElementInModel(Object instance) throws EolRuntimeException {
		if (!isModelElement(instance)) {
			return false;
		}
		try {
			((IRPModelElement)instance).deleteFromProject();	
		} catch(RhapsodyRuntimeException ex) {
			LOG.error("Unable to delete element from model", ex);
			throw new EolRuntimeException("Unable to delete the eleement", ex);
		}
		return true;
	}
	
	@Override
	protected Collection<String> getAllTypeNamesOf(Object instance) {
		return this.types.getAllTypeNamesOf(instance);
	}
	
	private static final Logger LOG = LogManager.getLogger(RhapsodyModel.class);
	private final String ID_REGEX = "^GUID\s[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}$";
	private final Cache<IRPKey, PropertyValue> propertyCache;
	
	private IRPApplication app;
	private IRPProject prj;
	private RhapsodyMetaclasses types;
	private IRPPackage mainPackage;
	private boolean softDispose;
	
	private Pattern idPattern;
	
	private boolean usingActivePrj = false;
	private boolean rhapsodyWasActive = false;
	private boolean canDispose = true;
	
	/**
	 * Factory for creating instances with classes or stereotypes
	 * 
	 * @author Horacio Hoyos Rodriguez
	 */
	private class ElementFactory {
		private final String name;
		private final String metaclass;
		private final IRPStereotype sType;
		
		public ElementFactory(String metaclass) {
			this(metaclass, "", null);
		}
		
		public ElementFactory(String metaclass, String name) {
			this(metaclass, name, null);
		}


		public ElementFactory(String metaclass, String name, IRPStereotype sType) {
			this.name = name;
			this.metaclass = metaclass;
			this.sType = sType;
		}
		
		public ElementFactory(IRPStereotype sType) {
			this.name = "";
			this.metaclass = sType.getOfMetaClass().split(",")[0];
			this.sType = sType;
		}
		
		public IRPModelElement create(IRPPackage pckg) {
			var result = pckg.addNewAggr(this.metaclass, this.name);
			if (this.sType != null) {
				result.addSpecificStereotype(this.sType);
			}
			return result;
		}
	}
	
	/**
	 * Use the {@link RhapsodyAppServer} to connect to the active Rhapsody or launch a new one.
	 * @return the Rhapsody application to use.
	 * @throws RhapsodyRuntimeException if the {@link RhapsodyAppServer} fails to find/create the {@link IRPApplication}
	 */
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
