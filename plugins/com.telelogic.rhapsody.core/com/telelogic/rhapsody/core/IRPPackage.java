
//Licensed Materials - Property of IBM 
//(c) Copyright IBM Corporation 2006, 2018. All Rights Reserved.

package com.telelogic.rhapsody.core;


/**
  * The IRPPackage interface represents packages in Rhapsody models.
  */
public interface IRPPackage  extends IRPUnit {
	/**
 	 * Adds a new activity diagram to the package.
 	 * @return the activity diagram that was created
 	 */
	public IRPFlowchart addActivityDiagram();
	/**
 	 * Adds a new actor to the package.
 	 * @param name the name to use for the new actor
 	 * @return the actor that was created
 	 */
	public IRPActor addActor(String name);
	/**
 	 * Adds a new class to the package.
 	 * @param name the name to use for the new class
 	 * @return the class that was created
 	 */
	public IRPClass addClass(String name);
	/**
 	 * Adds a new collaboration diagram to the package.
 	 * @param name the name to use for the new collaboration diagram
 	 * @return the collaboration diagram that was created
 	 */
	public IRPCollaborationDiagram addCollaborationDiagram(String name);
	/**
 	 * Adds a new component diagram to the package.
 	 * @param name the name to use for the new component diagram
 	 * @return the component diagram that was created
 	 */
	public IRPComponentDiagram addComponentDiagram(String name);
	/**
 	 * Adds a new deployment diagram to the package.
 	 * @param name the name to use for the new deployment diagram
 	 * @return the deployment diagram that was created
 	 */
	public IRPDeploymentDiagram addDeploymentDiagram(String name);
	/**
 	 * Adds a new event to the package.
 	 * @param name the name to use for the new event
 	 * @return the event that was created
 	 */
	public IRPEvent addEvent(String name);
	/**
 	 * Adds an item flow to the package.
 	 * @param name the name to use for the new item flow
 	 * @return the item flow created
 	 */
	public IRPFlowItem addFlowItems(String name);
	/**
 	 * Adds a flow to the package.
 	 * @param name the name to use for the new flow
 	 * @return the flow created
 	 */
	public IRPFlow addFlows(String name);
	/**
 	 * Adds a global function to the package.
 	 * @param name the name to use for the new function
 	 * @return the function created
 	 */
	public IRPOperation addGlobalFunction(String name);
	/**
 	 * Adds an Object to the package. This method is for adding instances of existing classes. To add an implicit object, use the method addImplicitObject.
 	 * @param name the name to use for the new object
 	 * @param otherClassName the name of the class that the new object should be an instance of
 	 * @param otherClassPackageName the name of the package that contains the class. You must specify this argument even if you are adding the object to the package that contains the class you are instantiating
 	 * @return the object that was created
 	 */
	public IRPRelation addGlobalObject(String name, String otherClassName, String otherClassPackageName);
	/**
 	 * Adds a global variable to the package.
 	 * @param name the name to use for the variable
 	 * @return the variable created
 	 */
	public IRPAttribute addGlobalVariable(String name);
	/**
 	 * Adds an implicit object to the package. This is relevant only for C and C++ models.
 	 * @param name the name to use for the new object
 	 * @return the object that was created
 	 */
	public IRPRelation addImplicitObject(String name);
	/**
 	 * Adds a new instance specification.
 	 * @param name the name to use for the new instance specification
 	 * @param classifier the classifier that the instance specification should instantiate
 	 * @return the instance specification that was created
 	 * @throws RhapsodyRuntimeException
 	 */
	public IRPInstanceSpecification addInstanceSpecification(String name, IRPClassifier classifier);
	/**
 	 * Creates a link between two objects in the package. In addition to specifying the two objects, you must specify the association that the link should represent, or, alternatively, the two ports that should be used for the link. If you provide the two ports as arguments, you should use Null for the association argument. Similarly, if you specify an association, you should use Null for the two port arguments. Note that if you are not specifying the two ports, you must provide an association as an argument even if there is only one relevant association.
 	 * @param fromPart the "from" object for the link
 	 * @param toPart the "to" object for the link
 	 * @param assoc the association that the link should represent
 	 * @param fromPort the "from" port for the link
 	 * @param toPort the "to" port for the link
 	 * @return the link created
 	 */
	public IRPLink addLink(IRPInstance fromPart, IRPInstance toPart, IRPRelation assoc, IRPPort fromPort, IRPPort toPort);
	/**
 	 * Creates a link between two objects. In addition to specifying the two objects, you must specify the association that the link should represent, or, alternatively, the two flow ports that should be used for the link. If you provide the two flow ports as arguments, you should use Null for the association argument. Similarly, if you specify an association, you should use Null for the two flow port arguments. Note that if you are not specifying the two flow ports, you must provide an association as an argument even if there is only one relevant association.
 	 * @param fromPart the "from" object for the link
 	 * @param toPart the "to" object for the link
 	 * @param assoc the association that the link should represent
 	 * @param fromPort the "from" flow port for the link
 	 * @param toPort the "to" flow port for the link
 	 * @return the link created
 	 * @throws RhapsodyRuntimeException
 	 */
	public IRPLink addLinkBetweenSYSMLPorts(IRPInstance fromPart, IRPInstance toPart, IRPRelation assoc, IRPSysMLPort fromPort, IRPSysMLPort toPort);
	/**
 	 * Adds a new File element to the package.
 	 * @param name the name to use for the new File
 	 * @return the File element that was created
 	 */
	public IRPModule addModule(String name);
	/**
 	 * Adds a nested package to the package.
 	 * @param name the name to use for the new package
 	 * @return the package created
 	 */
	public IRPPackage addNestedPackage(String name);
	/**
 	 * Adds a Node element to the package.
 	 * @param name the name to use for the new Node element
 	 * @return the Node element created
 	 */
	public IRPNode addNode(String name);
	/**
 	 * Adds a new object model diagram to the package.
 	 * @param name the name to use for the new object model diagram
 	 * @return the object model diagram that was created
 	 */
	public IRPObjectModelDiagram addObjectModelDiagram(String name);
	/**
 	 * Adds a new panel diagram to the package.
 	 * @param name the name to use for the new panel diagram
 	 * @return the panel diagram that was created
 	 */
	public IRPPanelDiagram addPanelDiagram(String name);
	/**
 	 * Adds a new sequence diagram to the package.
 	 * @param name the name to use for the new sequence diagram
 	 * @return the sequence diagram that was created
 	 */
	public IRPSequenceDiagram addSequenceDiagram(String name);
	/**
 	 * Adds a new statechart to the package.
 	 * @return the statechart that was created
 	 */
	public IRPStatechart addStatechart();
	/**
 	 * Adds a new timing diagram to the package.
 	 * @param name the name to use for the new timing diagram
 	 * @return the timing diagram that was created
 	 */
	public IRPTimingDiagram addTimingDiagram(String name);
	/**
 	 * Adds a new type to the package.
 	 * @param name the name to use for the new type
 	 * @return the type that was created
 	 */
	public IRPType addType(String name);
	/**
 	 * Adds a new use case to the package.
 	 * @param name the name to use for the new use case
 	 * @return the use case that was created
 	 */
	public IRPUseCase addUseCase(String name);
	/**
 	 * Adds a new use case diagram to the package.
 	 * @param name the name to use for the new use case diagram
 	 * @return the use case diagram that was created
 	 */
	public IRPUseCaseDiagram addUseCaseDiagram(String name);
	/**
 	 * Deletes the specified actor.
 	 * @param actor that actor that should be deleted
 	 */
	public void deleteActor(IRPActor actor);
	/**
 	 * Deletes the specified class.
 	 * @param theClass the class that should be deleted
 	 */
	public void deleteClass(IRPClass theClass);
	/**
 	 * Deletes the collaboration diagram with the specified name.
 	 * @param name the name of the collaboration diagram to delete
 	 */
	public void deleteCollaborationDiagram(String name);
	/**
 	 * Deletes the component diagram with the specified name.
 	 * @param name the name of the component diagram to delete
 	 */
	public void deleteComponentDiagram(String name);
	/**
 	 * Deletes the deployment diagram with the specified name.
 	 * @param name the name of the deployment diagram to delete
 	 */
	public void deleteDeploymentDiagram(String name);
	/**
 	 * Deletes the specified event.
 	 * @param event the event that should be deleted
 	 */
	public void deleteEvent(IRPEvent event);
	/**
 	 * Deletes the specified item flow.
 	 * @param pItem the item flow that should be deleted
 	 */
	public void deleteFlowItems(IRPFlowItem pItem);
	/**
 	 * Deletes the specified flow.
 	 * @param pFlow the flow that should be deleted
 	 */
	public void deleteFlows(IRPFlow pFlow);
	/**
 	 * Deletes the specified global function.
 	 * @param operation the global function that should be deleted
 	 */
	public void deleteGlobalFunction(IRPOperation operation);
	/**
 	 * Deletes the specified object.
 	 * @param relation the object that should be deleted
 	 */
	public void deleteGlobalObject(IRPRelation relation);
	/**
 	 * Deletes the specified global variable.
 	 * @param attribute the global variable that should be deleted
 	 */
	public void deleteGlobalVariable(IRPAttribute attribute);
	/**
 	 * Deletes the Node element with the specified name.
 	 * @param name the name of the node to delete
 	 */
	public void deleteNode(String name);
	/**
 	 * Deletes the object model diagram with the specified name.
 	 * @param name the name of the object model diagram to delete
 	 */
	public void deleteObjectModelDiagram(String name);
	/**
 	 * Deletes the package.
 	 */
	public void deletePackage();
	/**
 	 * Deletes the panel diagram with the specified name.
 	 * @param name the name of the panel diagram to delete
 	 */
	public void deletePanelDiagram(String name);
	/**
 	 * Deletes the sequence diagram with the specified name.
 	 * @param name the name of the sequence diagram to delete
 	 */
	public void deleteSequenceDiagram(String name);
	/**
 	 * Deletes the timing diagram with the specified name.
 	 * @param name the name of the timing diagram to delete
 	 */
	public void deleteTimingDiagram(String name);
	/**
 	 * Deletes the specified type.
 	 * @param type the type that should be deleted
 	 */
	public void deleteType(IRPType type);
	/**
 	 * Deletes the specified use case.
 	 * @param useCase the use case that should be deleted
 	 */
	public void deleteUseCase(IRPUseCase useCase);
	/**
 	 * Deletes the use case diagram with the specified name.
 	 * @param name the name of the use case diagram to delete
 	 */
	public void deleteUseCaseDiagram(String name);
	/**
 	 * Returns the actor with the specified name.
 	 * @param name the name of the actor to return
 	 * @return the actor with the name specified
 	 */
	public IRPActor findActor(String name);
	/**
 	 * Searches the package for a model element of the specified type with the specified name. Note that the search is carried out recursively if the package contains nested packages. In cases where there are multiple elements that meet the search criteria, the first such element encountered will be returned.
 	 * @param name the name of the element to find
 	 * @param metaClass the metaclass of the element to find
 	 * @return the first element found that satisfies the search criteria
 	 */
	public IRPModelElement findAllByName(String name, String metaClass);
	/**
 	 * Returns the class with the specified name.
 	 * @param name the name of the class to return
 	 * @return the class with the specified name
 	 */
	public IRPClass findClass(String name);
	/**
 	 * Returns the event with the specified name.
 	 * @param name the name of the event to return
 	 * @return the event with the specified name
 	 */
	public IRPEvent findEvent(String name);
	/**
 	 * Returns the global function with the specified name.
 	 * @param name the name of the function to return
 	 * @return the global function with the specified name
 	 */
	public IRPOperation findGlobalFunction(String name);
	/**
 	 * Returns the Object with the specified name.
 	 * @param name the name of the Object to return
 	 * @return the Object with the specified name
 	 */
	public IRPRelation findGlobalObject(String name);
	/**
 	 * Returns the global variable with the specified name.
 	 * @param name the name of the variable to return
 	 * @return the global variable with the specified name
 	 */
	public IRPAttribute findGlobalVariable(String name);
	/**
 	 * Returns the Node element with the specified name.
 	 * @param name the name of the node to return
 	 * @return the Node element with the specified name
 	 */
	public IRPNode findNode(String name);
	/**
 	 * Returns the type with the specified name.
 	 * @param name the name of the type to return
 	 * @return the type with the specified name
 	 */
	public IRPType findType(String name);
	/**
 	 * Returns a collection of the elements in the current package that are related to the specified model element. Note that the type of relations searched for depends upon the type of the element specified. For a more comprehensive list of references to the element, use the method IRPModelElement.getReferences(). (Keep in mind that getReferences() searches the entire model, not just the current package.)
 	 * @param objToFind the element whose references you want to find
 	 * @return the elements in the current package that are related to the specified model element
 	 */
	public IRPCollection findUsage(IRPModelElement objToFind);
	/**
 	 * Returns the use case with the specified name.
 	 * @param name the name of the use case to return
 	 * @return the use case with the specified name
 	 */
	public IRPUseCase findUseCase(String name);
	/**
 	 * Returns a collection of all the actors in the package.
 	 * @return all the actors in the package
 	 */
	public IRPCollection getActors();
	/**
 	 * Returns a collection of all the model elements that are directly under the current package, including functions, global variables, and global objects.
 	 * @return collection of all the model elements that are directly under the current package, including functions, global variables, and global objects
 	 */
	public IRPCollection getAllNestedElements();
	/**
 	 * Returns a collection of all the activity diagrams in the package. Note that this includes only the activity diagrams directly under the package, not diagrams belonging to classes in the package.
 	 * @return all the activity diagrams directly under the package
 	 */
	public IRPCollection getBehavioralDiagrams();
	/**
 	 * Returns a collection of all the classes in the package.
 	 * @return all the classes in the package
 	 */
	public IRPCollection getClasses();
	/**
 	 * Returns a collection of all the collaboration diagrams in the package.
 	 * @return all the collaboration diagrams in the package
 	 */
	public IRPCollection getCollaborationDiagrams();
	/**
 	 * Returns a collection of all the component diagrams in the package.
 	 * @return all the component diagrams in the package
 	 */
	public IRPCollection getComponentDiagrams();
	/**
 	 * Returns a collection of all the deployment diagrams in the package.
 	 * @return all the deployment diagrams in the package
 	 */
	public IRPCollection getDeploymentDiagrams();
	/**
 	 * Returns a collection of all the events in the package.
 	 * @return all the events in the package
 	 */
	public IRPCollection getEvents();
	/**
 	 * Returns the start number used for assigning IDs to events in the package. This value is controlled by the property EventsBaseID.
 	 * @return the start number used for assigning IDs to events in the package
 	 */
	public int getEventsBaseId();
	/**
 	 * Returns a collection of all the item flows in the package.
 	 * @return all the item flows in the package
 	 */
	public IRPCollection getFlowItems();
	/**
 	 * Returns a collection of all the flows in the package.
 	 * @return all the flows in the package
 	 */
	public IRPCollection getFlows();
	/**
 	 * Returns a collection of all the global functions in the package.
 	 * @return all the global functions in the package
 	 */
	public IRPCollection getGlobalFunctions();
	/**
 	 * Returns a collection of all the Objects in the package.
 	 * @return all the Objects in the package
 	 */
	public IRPCollection getGlobalObjects();
	/**
 	 * Returns a collection of all the global variables in the package.
 	 * @return all the global variables in the package
 	 */
	public IRPCollection getGlobalVariables();
	/**
 	 * Returns a collection of all the instance specifications in the package.
 	 * @return all the instance specifications in the package
 	 */
	public IRPCollection getInstanceSpecifications();
	/**
 	 * Returns a collection of all the Links in the package.
 	 * @return all the Links in the package
 	 */
	public IRPCollection getLinks();
	/**
 	 * Returns a collection of all the File elements in the package.
 	 * @return all the File elements in the package
 	 */
	public IRPCollection getModules();
	/**
	 * getNamespace
	 * @throws RhapsodyRuntimeException
	 */
	public String getNamespace();
	/**
 	 * Returns a collection of all the classifiers in the package.
 	 * @return all the classifiers in the package
 	 */
	public IRPCollection getNestedClassifiers();
	/**
 	 * Returns a collection of all the Components in the package.
 	 * @return all the Components in the package
 	 */
	public IRPCollection getNestedComponents();
	/**
 	 * Returns a collection of all the Node elements in the package.
 	 * @return all the Node elements in the package
 	 */
	public IRPCollection getNodes();
	/**
 	 * Returns a collection of all the object model diagrams in the package.
 	 * @return all the object model diagrams in the package
 	 */
	public IRPCollection getObjectModelDiagrams();
	/**
 	 * Returns a collection of all the nested packages in the package.
 	 * @return all the nested packages in the package
 	 */
	public IRPCollection getPackages();
	/**
 	 * Returns a collection of all the panel diagrams in the package.
 	 * @return all the panel diagrams in the package
 	 */
	public IRPCollection getPanelDiagrams();
	/**
 	 * Returns the mode that was selected for loading remote requirements in the collection.
 	 * @return the mode that was selected for loading remote requirements in the collection - will be one of the following values: "All", "Linked", "None"
 	 * @throws RhapsodyRuntimeException
 	 */
	public String getRemoteRequirementsPopulateMode();
	/**
 	 * Returns a collection of all the root instance specifications in the package. A root instance specification is any instance specification that is not a nested instance specification.
 	 * @return collection of all the root instance specifications in the package
 	 * @throws RhapsodyRuntimeException
 	 */
	public IRPCollection getRootInstanceSpecifications();
	/**
 	 * Checks whether the package is configured to be saved in a separate directory.
 	 * @return 1 if the package is configured to be saved in a separate directory, 0 otherwise
 	 */
	public int getSavedInSeperateDirectory();
	/**
 	 * Returns a collection of all the sequence diagrams in the package.
 	 * @return all the sequence diagrams in the package
 	 */
	public IRPCollection getSequenceDiagrams();
	/**
 	 * Gets the source artifacts for the package.
 	 * @return the source artifacts for the package, as a collection of IRPFile objects
 	 */
	public IRPCollection getSourceArtifacts();
	/**
 	 * Returns a collection of all the timing diagrams in the package.
 	 * @return all the timing diagrams in the package
 	 */
	public IRPCollection getTimingDiagrams();
	/**
 	 * Returns a collection of all the types in the package.
 	 * @return all the types in the package
 	 */
	public IRPCollection getTypes();
	/**
 	 * Returns a collection of all the use case diagrams in the package.
 	 * @return all the use case diagrams in the package
 	 */
	public IRPCollection getUseCaseDiagrams();
	/**
 	 * Returns a collection of all the use cases in the package.
 	 * @return all the use cases in the package
 	 */
	public IRPCollection getUseCases();
	/**
 	 * Returns a collection of all the user-defined stereotypes in the package.
 	 * @return all the user-defined stereotypes in the package
 	 */
	public IRPCollection getUserDefinedStereotypes();
	/**
 	 * For remote artifact packages, logs in to the server that contains the artifacts in the package. The behavior is the same as that of the Login to Server... option in the popup menu for remote artifact packages: If you have logged-in to the server during the current Rhapsody session, the saved credentials are used to log in. If you have not logged-in to the server during the current session, the standard login window is displayed. Links to ETM test cases and EWM work items are created on the relevant remote server and therefore require a login before new links can be created. In such cases, you can call the method loginToRemoteArtifactServer before calling the method IRPModelElement.createOSLCLink. If the login method was not called, Rhapsody will open the login window as part of the link creation process.
 	 */
	public void loginToRemoteArtifactServer();
	/**
 	 * For Design Manager projects, populates the package with the remote requirements that model elements do not yet have dependencies upon. This method corresponds to the "populate with all existing requirements" option that the UI provides for "Remote Resource" packages. Once these requirements have been added to the package, you can add dependencies to these requirements by using the "link to remote requirement" option.
 	 */
	public void populateRemoteRequirements();
	/**
 	 * If you are using Rhapsody's default numbering scheme for event IDs, then a certain amount of IDs are reserved for each package. As a result, there are situations where the IDs used for events in a given package may not be continuous. In cases like this, you can use the method reCalculateEventsBaseId to have the event ID numbering recalculated so that event IDs are continuous for all events in the package.
 	 * @return the new start number for event IDs in the package
 	 */
	public int reCalculateEventsBaseId();
	/**
 	 * For collections of remote requirements, you can use setRemoteRequirementsPopulateMode to specify which requirements in the collection should be loaded when you open the model - all the requirements, only the requirements that have OSLC links to model elements, or none of the requirements.
 	 * @param populateMode can be one of the following values: "All", "Linked", "None"
 	 * @throws RhapsodyRuntimeException
 	 */
	public void setRemoteRequirementsPopulateMode(String populateMode);
	/**
 	 * Specifies whether the package should be saved in a separate directory.
 	 * @param savedInSeperateDirectory Use 1 to specify that the package should be saved in a separate directory. Use 0 to specify that the package should not be saved in a separate directory.
 	 */
	public void setSavedInSeperateDirectory(int savedInSeperateDirectory);
	/**
 	 * Updates the views on the Rhapsody Model Manager server for all the diagrams contained in the package.
 	 * @param enforceUpdate Use 0 to specify that a view should be updated only if changes that affect the diagram were made since the last update. Use 1 to specify that views should be updated regardless of whether or not changes that affect the diagram were made since the last update.
 	 * @return the number of views that were updated on the server. If no diagrams require an update, 0 is returned. If the update attempt failed, -1 is returned.
 	 * @throws RhapsodyRuntimeException
 	 */
	public int updateContainedDiagramsOnServer(int enforceUpdate);
	/**
 	 * Updates the views on the Rhapsody Model Manager server for all the matrices contained in the package.
 	 * @param enforceUpdate Use 0 to specify that a view should be updated only if changes that affect the matrix were made since the last update. Use 1 to specify that views should be updated regardless of whether or not changes that affect the matrix were made since the last update.
 	 * @return the number of views that were updated on the server. If no matrices require an update, 0 is returned. If the update attempt failed, -1 is returned.
 	 * @throws RhapsodyRuntimeException
 	 */
	public int updateContainedMatricesOnServer(int enforceUpdate);
	/**
 	 * Updates the views on the Rhapsody Model Manager server for all the tables contained in the package.
 	 * @param enforceUpdate Use 0 to specify that a view should be updated only if changes that affect the table were made since the last update. Use 1 to specify that views should be updated regardless of whether or not changes that affect the table were made since the last update.
 	 * @return the number of views that were updated on the server. If no tables require an update, 0 is returned. If the update attempt failed, -1 is returned.
 	 * @throws RhapsodyRuntimeException
 	 */
	public int updateContainedTablesOnServer(int enforceUpdate);


}

