
//Licensed Materials - Property of IBM 
//(c) Copyright IBM Corporation 2006, 2018. All Rights Reserved.

package com.telelogic.rhapsody.core;


 /**
  * The IRPModelElement interface represents an element in a Rhapsody model, and its methods reflect the behavior shared by the various types of model elements. The specific types of elements in a model are derived from this interface.
  */
public interface IRPModelElement {
	/**
 	 * Creates an association class using the specified IRPRelation elements. Can only be called on the elements that can contain association classes - packages and classes. To add an ordinary association, use the method IRPClassifier.addRelationTo.
 	 * @param end1 the IRPRelation element at one end of the association
 	 * @param end2 the IRPRelation element at the second end of the association
 	 * @param name the name to use for the new association class
 	 * @return the association class that was created
 	 */
	public IRPAssociationClass addAssociation(IRPRelation end1, IRPRelation end2, String name);
	/**
 	 * Adds a dependency from the model element to the model element specified by the parameters. The method searches the model recursively until it finds an element that matches the name and metaclass specified. Since your model may contain multiple elements with the same name and type in different packages, the preferred way to add a dependency is to use the method addDependencyTo, which takes a specific model element as an argument.
 	 * @param dependsOnName the name of the model element on which this model element depends
 	 * @param dependsOnType the type (metaclass) of the model element on which this model element depends. The strings to use for this parameter should be taken from the file metaclasses.txt in the Doc directory of the Rhapsody installation.
 	 * @return the new dependency that was created
 	 */
	public IRPDependency addDependency(String dependsOnName, String dependsOnType);
	/**
 	 * Creates a dependency between the two specified elements. In most cases, you can use the method IRPModelElement.addDependencyTo to add a new dependency. However, in cases where you want to create a dependency between two read-only elements, you can use addDependencyBetween to create the new dependency and assign ownership of the dependency to a third model element.
 	 * @param dependent the model element that is dependent on the other model element
 	 * @param dependsOn the model element that the first element depends upon
 	 * @return the new dependency that was created
 	 * @throws RhapsodyRuntimeException
 	 */
	public IRPDependency addDependencyBetween(IRPModelElement dependent, IRPModelElement dependsOn);
	/**
 	 * Adds a dependency upon another model element.
 	 * @param element the model element that this element depends upon
 	 * @return the dependency created
 	 */
	public IRPDependency addDependencyTo(IRPModelElement element);
	/**
 	 * Creates a link between this model element and the model element specified as an argument. The types of elements that can be connected with a link by using this method are the same types of elements that can be joined by a link in the Rhapsody diagram editors. In addition to specifying the other model element that should be connected by this link, you must specify the association that the link should represent, or, alternatively, the two ports that should be used for the link. If you provide the two ports as arguments, you should use Null for the association argument. Similarly, if you specify an association, you should use Null for the two port arguments. Note that if you are not specifying the two ports, you must provide an association as an argument even if there is only one relevant association.
 	 * @param toElement the model element that the link should connect to
 	 * @param assoc the association that the link should represent
 	 * @param fromPort the "from" port for the link 
 	 * @param toPort the "to" port for the link 
 	 * @return the link created
 	 */
	public IRPLink addLinkToElement(IRPModelElement toElement, IRPRelation assoc, IRPModelElement fromPort, IRPModelElement toPort);
	/**
 	 * Adds a new model element to the current element, for example, adding a class to a package.
 	 * @param metaType the type of element to add. The string to use is the name of the appropriate metaclass. The list of metaclass names that can be used for this argument can be found in the file metaclasses.txt in the Doc directory of your Rhapsody installation.
 	 * @param name the name to use for the new element
 	 * @return the new element that was created
 	 * <pre>
 	 * {@code
 	 * static IRPApplication app = RhapsodyAppServer.getActiveRhapsodyApplication();
 	 * IRPProject prj = app.openProject("d:\\temp\\_sample_code\\Class_Tricks.rpy");
 	 * IRPPackage cameraPackage = prj.addPackage("Cameras");
 	 * cameraPackage.addNewAggr("Stereotype", "s1");
 	 * }
 	 * </pre>
 	 */
	public IRPModelElement addNewAggr(String metaType, String name);
	/**
 	 * Adds a new property to the model element and assigns a value to it. Note that this method does not have a user interface equivalent in the Features window.
 	 * @param propertyKey the name of the property to add. The syntax to use for this parameter is Subject.Metaclass.Property, for example, CG.Class.NewProperty
 	 * @param propertyType the property type. The strings that can be used for this parameter are: "Enum","Bool","String","Color","Int","Double","Font","File","Path", and "MultiLine". If you want to add a property of type Enum, you can specify the possible values using the following syntax for this parameter: "Enum,wood,plastic,metal".
 	 * @param propertyValue the value to assign to the new property. For boolean properties, use "True" or "False". 
 	 */
	public void addProperty(String propertyKey, String propertyType, String propertyValue);
	/**
	 * method addRedefines
	 * @throws RhapsodyRuntimeException
	 */
	public void addRedefines(IRPModelElement newRedefine);
	/**
 	 * For Design Manager projects, used to create a dependency from a model element to a remote element. This method corresponds to the "link to remote requirement" option in the user interface. In order to have the remote element available as a model element to use with this method, you must first call the IRPProject.getRemoteResourcePackages() method. (For remote requirements that are not yet linked to any elements in the model, you must also call the IRPPackage.populateRemoteRequirements() method.) Note that while the first parameter can be any object of type IRPModelElement, at the moment you can only add dependencies to remote requirements.
 	 * @param element the remote element to which a dependency should be created
 	 * @param linkType - one of the link types available with the requirement tool that you are using. For example, for DOORS Next, the possible types are "Derives From", "Refines", "Satisfies", and "Trace".
 	 * @return the new dependency that was created
 	 */
	public IRPDependency addRemoteDependencyTo(IRPModelElement element, String linkType);
	/**
 	 * Applies the specified stereotype to the model element.
 	 * @param stereotype the stereotype to apply to the model element 
 	 */
	public void addSpecificStereotype(IRPStereotype stereotype);
	/**
 	 * Applies the specified stereotype to the model element if the project contains a stereotype with the name specified and applicable to the metaclass specified. If the project does not yet contain such a stereotype, this method creates the stereotype in the package that owns the model element, and applies the new stereotype to the model element.
 	 * @param name the name of the stereotype to apply (or create and apply)
 	 * @param metaType the metaclass that the stereotype is applicable to
 	 * @return the stereotype applied (or created and applied) to the model element
 	 */
	public IRPStereotype addStereotype(String name, String metaType);
	/**
 	 * Makes the current model element a template instantiation of the specified template.
 	 * @param newVal the template to use for the instantiation
 	 */
	public void becomeTemplateInstantiationOf(IRPModelElement newVal);
	/**
 	 * Changes the model element to the type of element specified by the parameter provided. This corresponds to the "Change to" option that is included in the pop-up menu for model elements in the browser. An element that is not a "new term" can be changed to any of the "new terms" that are based on it. An element that is a "new term" can be changed to the model element that it is based on or to any of the other "new terms" that are based on that base element. Note that when you use this method, you must always use a variable to store the model element that is returned. This is necessary because the original element is destroyed, so you will have problems if you try to access the original element after this method is called.
 	 * @param metaClass the metaclass of the element that this element should be changed to. The strings to use for this parameter should be taken from the file metaclasses.txt in the Doc directory of the Rhapsody installation.
 	 * @return the new model element that was created.
 	 */
	public IRPModelElement changeTo(String metaClass);
	/**
 	 * Clones a model element.
 	 * @param name the name to use for the new element
 	 * @param newOwner the model element that should be the owner of the new element
 	 * @return the new model element that was created
 	 */
	public IRPModelElement clone(String name, IRPModelElement newOwner);
	/**
 	 * Creates an OSLC link between the element and the element represented by the specified URL. Links to ETM test cases and EWM work items are created on the relevant remote server and therefore require a login before new links can be created. In such cases, you can call the method IRPPackage.loginToRemoteArtifactServer before calling the method createOSLCLink. If the login method was not called, Rhapsody will open the login window as part of the link creation process.
 	 * @param type one of the OSLC link types that can be created. Must be one of the typed defined in IRPModelElement.OSLCLink.Types. Note that the "Derives From" link type can only be used when creating a link to a requirement from DOORS Next.
 	 * @param purl the URL for the target element. The URL should not include the "context" parameter.
 	 * @throws RhapsodyRuntimeException
 	 */
	public void createOSLCLink(String type, String purl);
	/**
 	 * Deletes the specified dependency from the model.
 	 * @param dependency the dependency to be deleted
 	 */
	public void deleteDependency(IRPDependency dependency);
	/**
 	 * Deletes the current model element from the model.
 	 */
	public void deleteFromProject();
	/**
 	 * Deletes the specified OSLC link from the model.
 	 * @param type the link type of the OSLC link that is to be deleted. Must be one of the typed defined in {@link IRPModelElement.OSLCLink.Types}. You can also use "*" to represent all of the types.
 	 * @param purl the URL for the link's target element. You can use "*" as the value of the parameter in order to delete all links of the specified type.
 	 * @throws RhapsodyRuntimeException
 	 */
	public void deleteOSLCLink(String type, String purl);
	/**
 	 * Returns error message for last method called. If the last method completed successfully, then this method returns an empty string. To get the correct error message for a method, errorMessage() must be called immediately after the method is called.
 	 * @return the error message for the last method called
 	 */
	public String errorMessage();
	/**
 	 * Searches for the specified model element in the specified path under the current model element.
 	 * <pre> 
 	 * // this code gets the class Webcam in the package SpecializedCameras which is a subpackage of the top-level package Cameras
 	 * IRPProject currentProject = app.activeProject();
 	 * IRPClass classToFind = (IRPClass)currentProject.findElementsByFullName("Webcam in Cameras::SpecializedCameras", "Class");
 	 * System.out.println(classToFind.getFullPathName());
 	 * </pre> 
 	 * @param name the name of the element to search for and the relative path to the element starting at the current element. This argument can use the format "Class in Package::Subpackage" or the format "Package::Subpackage::Class", for example, findElementsByFullName("Cameras::SpecializedCameras::Webcam", "Class")
 	 * @param metaClass the metaclass of the element you are looking for. The strings to use for this parameter should be taken from the file metaclasses.txt in the Doc directory of the Rhapsody installation.
 	 * @return the model element that was specified
 	 */
	public IRPModelElement findElementsByFullName(String name, String metaClass);
	/**
 	 * Searches for the specified model element. This method only searches the first level of elements below the current element. To search all of the levels below the current element, use the method findNestedElementRecursive.
 	 * @param name the name of the element to search for
 	 * @param metaClass the metaclass of the element you are looking for. The strings to use for this parameter should be taken from the file metaclasses.txt in the Doc directory of the Rhapsody installation.
 	 * @return the model element that was specified. Note that the element is always returned as an object of type IRPModelElement. So you will usually have to use casting, for example, <br>IRPPackage packageToUse = (IRPPackage)prj.findNestedElement("GreeterPackage", "Package");
 	 */
	public IRPModelElement findNestedElement(String name, String metaClass);
	/**
 	 * Searches recursively for the specified model element. This method searches all of the levels below the current element. To search only the first level of elements below the current element, use the method findNestedElement.
 	 * @param name the name of the element to search for
 	 * @param metaClass the metaclass of the element you are looking for. The strings to use for this parameter should be taken from the file metaclasses.txt in the Doc directory of the Rhapsody installation.
 	 * @return the model element that was specified. Note that the element is always returned as an object of type IRPModelElement. So you will usually have to use casting, for example, <br>IRPPackage packageToUse = (IRPPackage)prj.findNestedElementRecursive("GreeterPackage", "Package");
 	 */
	public IRPModelElement findNestedElementRecursive(String name, String metaClass);
	/**
 	 * Returns a collection of all the element's tags.
 	 * @return a collection of IRPTag objects representing the element's tags
 	 */
	public IRPCollection getAllTags();
	/**
 	 * Returns all of the element's annotations. This includes comments, constraints, and requirements.
 	 * @return all of the element's annotations: comments, constraints, and requirements
 	 */
	public IRPCollection getAnnotations();
	/**
 	 * Returns a collection of all the association classes directly beneath this model element. This method is only relevant for packages and classifiers.
 	 * @return all of the association classes directly beneath this model element
 	 */
	public IRPCollection getAssociationClasses();
	/**
 	 * Returns the GUID of the model element as an array of bytes, as opposed to the method getGUID, which returns the GUID as a string.
 	 * @return the GUID of the model element as an array of bytes
 	 */
	public byte[] getBinaryID();
	/**
 	 * Returns all of the element's constraints.
 	 * @return all of the element's constraints
 	 */
	public IRPCollection getConstraints();
	/**
 	 * For internal use only.
 	 */
	public IRPCollection getConstraintsByHim();
	/**
 	 * Returns a collection of all the element's controlled files.
 	 * @return a collection of IRPControlledFile objects representing the element's controlled files
 	 */
	public IRPCollection getControlledFiles();
	/**
 	 * Returns the name of the decoration style currently associated with the model element.
 	 * @return the decoration style currently associated with the model element
 	 */
	public String getDecorationStyle();
	/**
 	 * Returns all of the element's dependencies.
 	 * @return all of the element's dependencies
 	 */
	public IRPCollection getDependencies();
	/**
 	 * Returns the description defined for the element.
 	 * @return the description for the element
 	 */
	public String getDescription();
	/**
 	 * Returns HTML representation of the element description.
 	 * @return HTML representation of the element description
 	 */
	public String getDescriptionHTML();
	/**
 	 * Returns the description defined for the element in plain text format.
 	 * @return the description for the element in plain text format
 	 */
	public String getDescriptionPlainText();
	/**
 	 * Returns the description defined for the element in RTF format.
 	 * @return the description for the element in RTF format
 	 */
	public String getDescriptionRTF();
	/**
 	 * Returns the label of the model element.
 	 * @return the label of the model element
 	 */
	public String getDisplayName();
	/**
 	 * Returns the label of the model element as an RTF string.
 	 * @return the label of the model element as an RTF string.
 	 */
	public String getDisplayNameRTF();
	/**
 	 * Returns error message for last method called. If the last method completed successfully, then this method returns an empty string. To get the correct error message for a method, errorMessage() must be called immediately after the method is called.
 	 * @return the error message for the last method called
 	 */
	public String getErrorMessage();
	/**
 	 * Returns the full path name of the model element. The format of the string returned is package::subpackage::class.
 	 * <pre> 
 	 *  // this code prints the full path name for each class in the Cameras package, including the classes in subpackages
 	 *	System.out.println("======================================================================");
 	 *	IRPCollection allClassesInCamerasPackage = cameraPackage.getNestedElementsByMetaClass("Class", 1);
 	 *	int numberOfClasses = allClassesInCamerasPackage.getCount();
 	 *	// note that when using getItem to get an item from an IRPCollection object, the index starts at 1, not 0
 	 *	IRPModelElement elementInCollection;
 	 *	for(int i = 1; i < numberOfClasses+1 ; i++) {
 	 *		elementInCollection = (IRPModelElement)allClassesInCamerasPackage.getItem(i);
 	 * 	System.out.println(elementInCollection.getFullPathName());
 	 * }
 	 * </pre>
 	 * @return the full path name of the model element. The format of the string returned is package::subpackage::class.
 	 */
	public String getFullPathName();
	/**
 	 * Retrieves the full path name of the element as a string in the following format: (class) in (package).
 	 * @return the full path name of the element in the format: (class) in (package)
 	 */
	public String getFullPathNameIn();
	/**
 	 * Returns the GUID of the model element. In situations where you may have to carry out multiple searches for the same element, you can use the getGUID method to get the GUID of the element once, and then use the method IRPProject.findElementByGUID which performs a quicker search than the other "find" methods provided.
 	 * @return the GUID of the model element
 	 */
	public String getGUID();
	/**
 	 * Returns a collection of all the hyperlinks associated with the element.
 	 * @return a collection of IRPHyperLink objects representing the hyperlinks associated with the element
 	 */
	public IRPCollection getHyperLinks();
	/**
 	 * Returns the full path of the graphic file used to represent elements of this type in the browser, for example, D:\programs\rhapsody80\Share\PredefinedPictures\Icons\RhapsodyIcons_72.gif.
 	 * @return the full path of the graphic file used to represent elements of this type in the browser
 	 */
	public String getIconFileName();
	/**
 	 * Returns the name of the API interface corresponding to the current element, for example, IRPClass for a class element, IRPOperation for an operation element.
 	 * @return the name of the API interface corresponding to the current element
 	 */
	public String getInterfaceName();
	/**
 	 * Checks whether the element is an "external" element - corresponds to the value of the property UseAsExternal.
 	 * @return 1 if the element is an "external" element, 0 otherwise
 	 */
	public int getIsExternal();
	/**
 	 * Indicates whether the model element is based on the metaclass provided as a parameter.
 	 * @param metaClass The name of the metaclass to check for. The strings to use for this parameter should be taken from the file metaclasses.txt in the Doc directory of the Rhapsody installation.
 	 * @return indication of whether the model element is based on the metaclass specified. 1 means that the model element is based on the metaclass specified, 0 means it is not based on that metaclass.
 	 */
	public int getIsOfMetaClass(String metaClass);
	/**
 	 * Checks whether the model element is configured to have its label displayed instead of its name whenever it is included in a diagram. This behavior is controlled by the General::Graphics::ShowLabels property.
 	 * @return 1 if the element is configured to have its label displayed instead of its name in diagrams, 0 otherwise
 	 */
	public int getIsShowDisplayName();
	/**
 	 * Checks if the element is an element that can't be resolved by Rhapsody.
 	 * @return indication of whether the element is an unresolved element - 0 if the element can be resolved, 1 if the element is unresolved 
 	 */
	public int getIsUnresolved();
	/**
 	 * Returns a collection of the tags that were created locally for this model element.
 	 * @return the tags that were created locally for the model element (collection of IRPTag elements)
 	 */
	public IRPCollection getLocalTags();
	/**
 	 * Returns the "main" diagram for the element. This operation is valid only for packages, classes, actors, use cases, objects, and interfaces.
 	 * @return the "main" diagram for the element
 	 */
	public IRPDiagram getMainDiagram();
	/**
 	 * Gets the name of the metaclass on which the model element is based. Note that if the element is based on a New Term stereotype, the string returned here will be the metaclass on which it is based. To get the name of the New Term stereotype, use the method IRPModelElement.getUserDefinedMetaClass.
 	 * @return the name of the metaclasses on which the model element is based. The string returned will be one of the the metaclass names listed in the file metaclasses.txt in the Doc directory of the Rhapsody installation. For example, for an object of type IRPStereotype, the string "Stereotype" will be returned. 
 	 */
	public String getMetaClass();
	/**
 	 * Returns the name of the element.
 	 * @return the name of the element
 	 */
	public String getName();
	/**
 	 * Gets a collection of all the model elements that are directly under the current element. Note that if you call this method on a package, the returned collection will not include functions, global variables, or global objects contained in the package because these are actually contained in a class called TopLevel. To get the functions, global variables, or global objects contained in a package, use the following IRPPackage methods: getGlobalFunctions(), getGlobalVariables(), and getGlobalObjects().
 	 * @return a collection of IRPModelElement objects representing all the model elements that are directly under the current element 
 	 * <pre>
 	 * {@code
 	 * IRPProject prj = app.openProject("l:\\temp\\_sample_code\\Unit_Tricks.rpy");
 	 * IRPPackage vehiclePackage = prj.addPackage("Vehicles");
 	 * vehiclePackage.addClass("Car");
 	 * vehiclePackage.addClass("Jeep");
 	 * vehiclePackage.addClass("Convertible");
 	 * prj.save();
 	 * IRPCollection elementsInVehiclesPackage = vehiclePackage.getNestedElements();
 	 * IRPModelElement elementInCollection;
 	 * System.out.println("The Vehicles package contains:");
 	 * for (int i = 1; i <= elementsInVehiclesPackage.getCount(); i++) {
 	 * 	elementInCollection = (IRPModelElement)elementsInVehiclesPackage.getItem(i);
 	 * 	System.out.println("\t" + elementInCollection.getName());
 	 * }
 	 * }
 	 * </pre>
 	 */
	public IRPCollection getNestedElements();
	/**
 	 * Retrieves all of the model elements of the specified type below the current element. The second argument can be used to specify whether the retrieval should be recursive.
 	 * <pre> 
 	 *  // this code retrieves all the classes in the Cameras package (including classes in subpackages), and prints their type and name
 	 * 		IRPCollection allClassesInCamerasPackage = cameraPackage.getNestedElementsByMetaClass("Class", 1);
 	 * 		int numberOfClasses = allClassesInCamerasPackage.getCount();
 	 * 		// note that when using getItem to get an item from an IRPCollection object, the index starts at 1, not 0
 	 * 		IRPModelElement elementInCollection;
 	 * 		for(int i = 1; i < numberOfClasses+1 ; i++) {
 	 * 			elementInCollection = (IRPModelElement)allClassesInCamerasPackage.getItem(i);
 	 * 			System.out.println(elementInCollection.getMetaClass() + ": " + elementInCollection.getDisplayName());
 	 * 		}
 	 * </pre>
 	 * @param metaClass the type of elements that you want to retrieve. The strings to use for this parameter should be taken from the file metaclasses.txt in the Doc directory of the Rhapsody installation.
 	 * @param recursive Use 1 to specify that the retrieval should be recursive. Use 0 if you only want to retrieve the relevant elements from the first level below the current element.
 	 * @return a collection of the model elements of the specified type below the current element
 	 */
	public IRPCollection getNestedElementsByMetaClass(String metaClass, int recursive);
	/**
 	 * Returns a collection that consists of the current element and all of the model elements below it.
 	 * <pre> 
 	 * // this code retrieves all the items in the Cameras package, and prints their type and name
 	 * IRPCollection allItemsInCameraPackage = cameraPackage.getNestedElementsRecursive();
 	 * int numberOfElements = allItemsInCameraPackage.getCount();
 	 * // note that when using getItem to get an item from an IRPCollection object, the index starts at 1, not 0
 	 * IRPModelElement elementInCollection;
 	 * for(int i = 1; i < numberOfElements+1 ; i++) {
 	 * 	elementInCollection = (IRPModelElement)allItemsInCameraPackage.getItem(i);
 	 * 	System.out.println(elementInCollection.getMetaClass() + ": " + elementInCollection.getDisplayName());
 	 * }
 	 * </pre>
 	 * @return a collection consisting of the current element and all of the model elements below it
 	 */
	public IRPCollection getNestedElementsRecursive();
	/**
 	 * If a "new term" stereotype has been applied to the element, returns the stereotype.
 	 * @return the "new term" stereotype that was applied to the element
 	 */
	public IRPStereotype getNewTermStereotype();
	/**
 	 * Returns a collection of all the element's OSLC links. Each item in the collection is a string that uses the following format: "Type=&lt;&lt;link type&gt;&gt;(newline)URL=&lt;&lt;linked item URL&gt;&gt;".
 	 * @return all of the element's OSLC links
 	 * @throws RhapsodyRuntimeException
 	 */
	public IRPCollection getOSLCLinks();
	/**
 	 * If the element is an instantiation of a template, this method returns the template that it instantiates.
 	 * @return the template that this model element instantiates
 	 */
	public IRPModelElement getOfTemplate();
	/**
 	 * Returns the full path of the graphic file that is used as an overlay on this specific model element, on top of the regular icon that represent elements of this type in the browser.
 	 * @return the full path of the graphic file that is used as an overlay on this specific model element, on top of the regular icon that represent elements of this type in the browser
 	 */
	public String getOverlayIconFileName();
	/**
 	 * Returns a collection of all the properties whose value was overridden for this model element. The collection consists of strings that use the format subject:metaclass:property:value.
 	 * @param recursive use 1 to specify that the method should return all properties overridden for the element - from the level of the element itself all the way up to the project level, use 0 to specify that the method should only return the properties that were overridden at the level of the element itself
 	 * @return the properties whose value was overridden for this model element
 	 */
	public IRPCollection getOverriddenProperties(int recursive);
	/**
	 * method getOverriddenPropertiesByPattern
	 * @throws RhapsodyRuntimeException
	 */
	public IRPCollection getOverriddenPropertiesByPattern(String pattern, int localyOverridenOnly, int withDefaultValues);
	/**
 	 * Returns all of the dependencies that are owned by the element.
 	 * @return all of the dependencies that are owned by this element
 	 * @throws RhapsodyRuntimeException
 	 */
	public IRPCollection getOwnedDependencies();
	/**
 	 * Returns the model element that owns this model element.
 	 * @return the model element that owns this model element
 	 */
	public IRPModelElement getOwner();
	/**
 	 * Returns the project that the current element belongs to.
 	 * @return the project the current element belongs to
 	 */
	public IRPProject getProject();
	/**
 	 * Returns the value of the specified property for the model element.
 	 * @param propertyKey the property whose value should be returned. The syntax to use for this parameter is Subject.Metaclass.Property, for example, CG.Class.ActiveThreadName
 	 * @return the value of the specified property. If a value has not been set specifically for this element, the default value is returned (the value propagated from a higher level)
 	 */
	public String getPropertyValue(String propertyKey);
	/**
 	 * Returns the value of the specified property for the model element, taking into account the collection of tokens specified and the collection of token values specified. For more information on using tokens in property values, see "Conditional Properties" in the Rhapsody help.
 	 * @param propertyKey the property whose value should be returned. The syntax to use for this parameter is Subject.Metaclass.Property, for example, CG.Class.ActiveThreadName
 	 * @param formalKey the collection of tokens to take into account (collection of strings)
 	 * @param actualValues the collection of token values to take into account (collection of strings)
 	 * @return the value of the specified property, taking into account the tokens and token values specified. If a value has not been set specifically for this element, the default value is returned (the value propagated from a higher level)
 	 */
	public String getPropertyValueConditional(String propertyKey, IRPCollection formalKey, IRPCollection actualValues);
	/**
 	 * Returns the value of the specified property for the model element, if the default value was overridden, taking into account the collection of tokens specified and the collection of token values specified. For more information on using tokens in property values, see "Conditional Properties" in the Rhapsody help. If a value has not been set explicitly for the model element, the method will not return the default value (like the getPropertyValueConditional method does). Rather, it will throw an exception.
 	 * @param propertyKey the property whose value should be returned. The syntax to use for this parameter is Subject.Metaclass.Property, for example, CG.Class.ActiveThreadName
 	 * @param formalKey the collection of tokens to take into account (collection of strings)
 	 * @param actualValues the collection of token values to take into account (collection of strings)
 	 * @return the value that was explicitly set for the model element for the specified property, taking into account the tokens and token values specified
 	 */
	public String getPropertyValueConditionalExplicit(String propertyKey, IRPCollection formalKey, IRPCollection actualValues);
	/**
 	 * Returns the value of the specified property for the model element if the default value was overridden. If a value has not been set explicitly for the model element, it will not return the default value (like the getPropertyValue method does). Rather, it will throw an exception.
 	 * @param propertyKey the property whose value should be returned. The syntax to use for this parameter is Subject.Metaclass.Property, for example, CG.Class.ActiveThreadName
 	 * @return the value that was explicitly set for the model element for the specified property
 	 */
	public String getPropertyValueExplicit(String propertyKey);
	/**
	 * method getRedefines
	 * @throws RhapsodyRuntimeException
	 */
	public IRPCollection getRedefines();
	/**
 	 * Returns a collection of all the model elements that point to this model element.
 	 * @return all the model elements that point to this model element
 	 */
	public IRPCollection getReferences();
	/**
 	 * For Rhapsody Model Manager projects, returns a collection of all the dependencies that the model element has on remote artifacts.
 	 * @return all the dependencies that the model element has on remote artifacts
 	 */
	public IRPCollection getRemoteDependencies();
	/**
 	 * For elements that are remote resources, returns the URI of the resource.
 	 * @return the URI of the remote resource. If the method is called for an element that is not a remote resource, an empty string is returned.
 	 */
	public String getRemoteURI();
	/**
 	 * Returns the ID used by DOORS to refer to this requirement.
 	 * @return the ID used by DOORS to refer to this requirement
 	 */
	public int getRequirementTraceabilityHandle();
	/**
 	 * Returns the Rhapsody Model Manager url for the model element.
 	 * @return the Rhapsody Model Manager url for the model element
 	 * @throws RhapsodyRuntimeException
 	 */
	public String getRmmUrl();
	/**
 	 * Returns the unit that the model element is saved in.
 	 * @return the unit that the element is saved in
 	 */
	public IRPUnit getSaveUnit();
	/**
 	 * @deprecated Since Rhapsody now allows multiple stereotypes to be applied to a model element, the getStereotypes() method should be used instead.
 	 */
	public IRPStereotype getStereotype();
	/**
 	 * Returns a collection of the stereotypes that have been applied to the element.
 	 * @return the stereotypes that have been applied to the element
 	 */
	public IRPCollection getStereotypes();
	/**
 	 * Returns the tag specified. This method can be used for both local tags and global tags.
 	 * @param name the name of the tag to return
 	 * @return the tag specified
 	 */
	public IRPTag getTag(String name);
	/**
 	 * For model elements that are templates, returns the template parameters.
 	 * @return the parameters of the template
 	 */
	public IRPCollection getTemplateParameters();
	/**
 	 * For model elements that are template instantiations, returns an object that contains the template instantiation parameters.
 	 * @return object that contains the template instantiation parameters
 	 */
	public IRPTemplateInstantiation getTi();
	/**
 	 * Returns the HTML that would be used to display the tooltip for the element in the user interface.
 	 * @return the HTML that would be used to display the tooltip for the element in the user interface
 	 */
	public String getToolTipHTML();
	/**
 	 * Gets the name of the New Term on which the model element is based.
 	 * @return the name of the New Term on which the model element is based. The string returned will be the name of the New Term stereotype that you defined. To get the name of the metaclass on which the New Term is based, use the method IRPModelElement.getMetaClass(). 
 	 */
	public String getUserDefinedMetaClass();
	/**
 	 * Checks whether the model element contains other elements.
 	 * @return 1 if the model element contains other elements, 0 otherwise
 	 */
	public int hasNestedElements();
	/**
 	 * Checks whether the model element is bound to a panel diagram widget.
 	 * @return 1 if the element is bound to a panel diagram widget, 0 otherwise
 	 */
	public int hasPanelWidget();
	/**
 	 * Locates the element in the Rhapsody browser, and highlights the element in the diagram where it appears. Note that the element will be highlighted in the diagram only if it is the kind of element that can appear in only one diagram, for example, a state.
 	 */
	public void highLightElement();
	/**
 	 * Checks whether the model element is a template.
 	 * @return 1 if the element is a template, 0 otherwise
 	 */
	public int isATemplate();
	/**
 	 * Checks whether the description for the element is in RTF format.
 	 * @return 1 if the description is in RTF format, 0 otherwise
 	 */
	public int isDescriptionRTF();
	/**
 	 * Checks whether the label of the element is in RTF format.
 	 * @return 1 if the label is in RTF format, 0 otherwise
 	 */
	public int isDisplayNameRTF();
	/**
 	 * Checks if the element was modified since the model was last saved.
 	 * @return 1 if the element was modified since the model was last saved, 0 if the element was not modified
 	 */
	public int isModified();
	/**
 	 * Checks whether the model element is a remote resource such as a DOORS/DOORS Next requirement.
 	 * @return 1 if the element is a remote resource, 0 if not
 	 */
	public int isRemote();
	/**
 	 * Locates the model element in the Rhapsody browser.
 	 * @return returns 1 if the element was located in the browser
 	 */
	public int locateInBrowser();
	/**
 	 * @deprecated Support for Design Manager was removed from Rhapsody in release 8.4.
 	 */
 	@Deprecated
	public void lockOnDesignManager();
	/**
 	 * Displays the information for the element in the Features window. Depending on the value of the parameter provided, opens a new Features window or uses an already-open Features window.
 	 * @param newDialog Use 1 to specify that the element information should be displayed in a new Features window. Use 0 to specify that the information should be displayed in a Features window that is already open or in a new window if there is no open Features window.
 	 */
	public void openFeaturesDialog(int newDialog);
	/**
 	 * Removes the value that was set for the specified property. This is equivalent to the "un-override" option in the Features window.
 	 * @param propertyKey the property whose value should be removed. The syntax to use for this parameter is Subject.Metaclass.Property, for example, CG.Class.ActiveThreadName
 	 */
	public void removeProperty(String propertyKey);
	/**
	 * method removeRedefines
	 * @throws RhapsodyRuntimeException
	 */
	public void removeRedefines(IRPModelElement removedRedefine);
	/**
 	 * Removes the specified stereotype from the element.
 	 * @param stereotype the stereotype to be removed from the element
 	 */
	public void removeStereotype(IRPStereotype stereotype);
	/**
 	 * Used to specify the decoration style that should now be associated with the model element.
 	 * @param newVal The decoration style that should now be associated with the model element. The value of the parameter must be one of the strings included in the value of the property Format::Decoration::StyleNames.
 	 */
	public void setDecorationStyle(String newVal);
	/**
 	 * Sets the specified string as the description of the element.
 	 * @param description the string to use as the description of the element
 	 */
	public void setDescription(String description);
	/**
 	 * Specifies an RTF string to use as the description for the element, and a collection of elements to which hyperlinks should be created.
 	 * @param rtfText the string to use for the element description - must be in RTF format
 	 * @param targets the collection of elements for which hyperlinks should be created
 	 */
	public void setDescriptionAndHyperlinks(String rtfText, IRPCollection targets);
	/**
 	 * Not implemented - should not be used.
 	 */
	public void setDescriptionHTML(String descriptionHTML);
	/**
 	 * Specifies the RTF string to use for the description of the model element.
 	 * @param descriptionRTF the RTF string to use for the description of the model element
 	 */
	public void setDescriptionRTF(String descriptionRTF);
	/**
 	 * Specifies the text to use for the label of the model element.
 	 * @param displayName the text to use for the label of the model element
 	 */
	public void setDisplayName(String displayName);
	/**
 	 * Specifies the RTF string to use for the label of the model element.
 	 * @param newVal the RTF string to use for the label of the model element
 	 */
	public void setDisplayNameRTF(String newVal);
	/**
 	 * Sets a new GUID for the model element.
 	 * @param gUID the new GUID that should be used for the model element
 	 */
	public void setGUID(String gUID);
	/**
 	 * Specifies whether the label of the element should be displayed instead of the element name whenever the element is used in a diagram. This method changes the value of the General::Graphics::ShowLabels property.
 	 * @param isShowDisplayName use 1 if you want the label of the element displayed, use 0 if you want the name of the element displayed
 	 */
	public void setIsShowDisplayName(int isShowDisplayName);
	/**
 	 * Specifies the "main" diagram for the element. This operation is valid only for packages, classes, actors, use cases, objects, and interfaces.
 	 * @param mainDiagram the diagram to use as the "main" diagram for the element
 	 */
	public void setMainDiagram(IRPDiagram mainDiagram);
	/**
 	 * Sets the specified string as the name of the element.
 	 * @param name the string to use as the name of the element
 	 */
	public void setName(String name);
	/**
 	 * Makes the current model element a template instantiation of the specified template.
 	 * @param ofTemplate the template to use for the instantiation
 	 */
	public void setOfTemplate(IRPModelElement ofTemplate);
	/**
 	 * Specifies the model element that should be the owner of this element.
 	 * @param owner the model element that should be the owner of this element
 	 */
	public void setOwner(IRPModelElement owner);
	/**
 	 * Sets the value of a property for the model element.
 	 * @param propertyKey the property whose value should be set. The syntax to use for this parameter is Subject.Metaclass.Property, for example, CG.Class.ActiveThreadName.
 	 * @param propertyValue the new value to use for the property. For boolean properties, use "True" or "False". 
 	 */
	public void setPropertyValue(String propertyKey, String propertyValue);
	/**
 	 * Sets a new ID to be used to reference this requirement
 	 * @param requirementTraceabilityHandle the new ID that should be used to reference this requirement
 	 */
	public void setRequirementTraceabilityHandle(int requirementTraceabilityHandle);
	/**
 	 * @deprecated This method was relevant when Rhapsody allowed only a single stereotype to be applied to a model element. To apply a stereotype to an element, use {@link #addSpecificStereotype} or {@link #addStereotype}. To remove a stereotype that was applied to an element, use {@link #removeStereotype}.
 	 */
 	@Deprecated
	public void setStereotype(IRPStereotype stereotype);
	/**
 	 * Applies the specified tag to the model element, and sets the value of the tag to a specific instance of another model element.
 	 * @param tag the tag to apply to the model element
 	 * @param elements collection of model elements representing the full path to the element. This collection is used to set the value of the tag to the full path of the target element. The collection must consist of objects of type IRPModelElement.
 	 * @param multiplicities collection of the relevant indices for each of the model elements in the first collection (the "elements" parameter). This makes it possible to point to a specific instance of the target model element when multiplicity is greater than one. The collection must consist of integers provided as strings.
 	 * @return the tag created for the model element
 	 */
	public IRPTag setTagContextValue(IRPTag tag, IRPCollection elements, IRPCollection multiplicities);
	/**
 	 * Applies a tag whose type is a model element to the current element with the value specified. If the tag has already been applied to the current element, the method can be used to modify the value of the tag.
 	 * @param tag the tag to apply to the element
 	 * @param val the value to use for the tag applied
 	 * @return the tag created for the model element
 	 */
	public IRPTag setTagElementValue(IRPTag tag, IRPModelElement val);
	/**
 	 * Applies the specified tag to the model element with the value specified. If the tag has already been applied to the model element, the method can be used to modify the value of the tag.
 	 * @param tag the tag to apply to the element
 	 * @param val the value to use for the tag applied
 	 * @return the tag created for the model element
 	 */
	public IRPTag setTagValue(IRPTag tag, String val);
	/**
 	 * For internal use only.
 	 */
	public void setTi(IRPTemplateInstantiation ti);
	/**
 	 * After changes are made to a template, this method can be called on each instantiation of the template in order to update the instantiation to match the changes that were made to the template.
 	 */
	public void synchronizeTemplateInstantiation();
	/**
 	 * @deprecated Support for Design Manager was removed from Rhapsody in release 8.4.
 	 */
 	@Deprecated
	public void unlockOnDesignManager();
	
	/** Constant values used with elements of this type **/
	public static final class OSLCLink
	{
		/**
 * This class contains values that specify OSLC Types
		**/
		public static final class Types
		{
			/**
OSLC link type: External 
			**/
			public static final	 String		EXTERNAL		= "http://jazz.net/ns/dm/linktypes#external";
			/**
OSLC link type: Refine
			**/
			public static final	 String		REFINE		= "http://jazz.net/ns/dm/linktypes#refine";
			/**
OSLC link type: Derives 
			**/
			public static final	 String		DERIVES		= "http://jazz.net/ns/dm/linktypes#derives";
			/**
OSLC Link Type: Satisfy
			**/
			public static final	 String		SATISFY		= "http://jazz.net/ns/dm/linktypes#satisfy";
			/**
OSLC Link Type: Trace
			**/
			public static final	 String		TRACE		= "http://jazz.net/ns/dm/linktypes#trace";
			/**
OSLC link type: Elaborates 
			**/
			public static final	 String		ELABORATES		= "http://open-services.net/ns/cm#relatedArchitectureElement";
			/**
OSLC link type: Validated By 
			**/
			public static final	 String		VALIDATEDBY		= "http://jazz.net/ns/qm/rqm#validatesArchitectureElement";
		}
	}



}

