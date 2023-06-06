
//Licensed Materials - Property of IBM 
//(c) Copyright IBM Corporation 2006, 2018. All Rights Reserved.

package com.telelogic.rhapsody.core;


/**
 * The IRPClass interface represents classes in Rhapsody models.
 */
public interface IRPClass  extends IRPClassifier {
	/**
 	 * Adds a class to the current class.
 	 * @param name the name to use for the new class
 	 * @return the new class created
 	 */	
	public IRPClass addClass(String name);
	/**
 	 * Adds a constructor for the current class.
 	 * @param argumentsData the name and types of the arguments for the constructor. The string should use the format "name1,type1,name2,type2", for example "a,int,b,int". For a constructor that does not take arguments, use an empty string ("").
 	 * @return the constructor created
 	 */
	public IRPOperation addConstructor(String argumentsData);
	/**
 	 * Adds a destructor for the current class.
 	 * @return the destructor created
 	 */
 	
 	
	public IRPOperation addDestructor();
	/**
 	 * Adds an event reception to the current class. It is preferable that you use the operation IRPClass.addReception instead.
 	 * @param name the name to use for the new event reception
 	 * @return the event reception created
 	 */
	public IRPEventReception addEventReception(String name);
	/**
 	 * Adds a new event reception, using the specified event.
 	 * @param name the name to use for the new event reception
 	 * @param event the event that should be associated with the new event reception
 	 * @return the event reception that was created
 	 * @throws RhapsodyRuntimeException
 	 */
	public IRPEventReception addEventReceptionWithEvent(String name, IRPEvent event);
	/**
 	 * This method is used to create a link between two parts belonging to a class. In addition to specifying the two parts, you must specify the association that the link should represent, or, alternatively, the two ports that should be used for the link. If you provide the two ports as arguments, you should use Null for the association argument. Similarly, if you specify an association, you should use Null for the two port arguments. Note that if you are not specifying the two ports, you must provide an association as an argument even if there is only one relevant association.
 	 * @param fromPart the "from" part for the link
 	 * @param toPart the "to" part for the link
 	 * @param assoc the association that the link should represent
 	 * @param fromPort the "from" port for the link
 	 * @param toPort the "to" port for the link
 	 * @return the link created
 	 */
	public IRPLink addLink(IRPInstance fromPart, IRPInstance toPart, IRPRelation assoc, IRPPort fromPort, IRPPort toPort);
	/**
 	 * This method is used to create a delegation connector between a class and one of its parts. In addition to specifying the part to use, you must specify the association that the link should represent, or, alternatively, the two ports that should be used for the link. If you provide the two ports as arguments, you should use Null for the association argument. Similarly, if you specify an association, you should use Null for the two port arguments. Note that if you are not specifying the two ports, you must provide an association as an argument even if there is only one relevant association.
 	 * @param toPart the part that should be linked to
 	 * @param partPort the port to use on the part
 	 * @param classPort the port to use on the class
 	 * @param assoc the association that the link should represent
 	 * @return the link created
 	 */
	public IRPLink addLinkToPartViaPort(IRPInstance toPart, IRPInstance partPort, IRPInstance classPort, IRPRelation assoc);
	/**
 	 * Adds a reception to the current class.
 	 * @param name the name to use for the new reception
 	 * @return the reception created
 	 */
	public IRPEventReception addReception(String name);
		/**
 		 * Specifies a base class that the current class should inherit from.
 		 * @param superClass the name of the class that should serve as the base class
 		 */
	public void addSuperclass(IRPClass superClass);
	/**
 	 * Adds a new triggered operation to the current class.
 	 * @param name the name to use for the new triggered operation
 	 * @return the triggered operation created
 	 */
	public IRPOperation addTriggeredOperation(String name);
	/**
 	 * Adds a new type to the current class.
 	 * @param name the name to use for the new type
 	 * @return the type created
 	 */
	public IRPType addType(String name);
	/**
 	 * Deletes the specified class from the current class.
 	 * @param name the name of the class that should be deleted
 	 */
	public void deleteClass(String name);
	/**
 	 * Deletes the specified constructor from the current class.
 	 * @param constructor the constructor that should be deleted. Note that this parameter is of type IRPOperation.
 	 */
	public void deleteConstructor(IRPOperation constructor);
	/**
 	 * Deletes the destructor for the class.
 	 */
	public void deleteDestructor();
	/**
 	 * Deletes the specified event reception. It is preferable that you use the operation IRPClass.deleteReception instead.
 	 * @param pVal the reception that should be deleted
 	 */
	public void deleteEventReception(IRPEventReception pVal);
	/**
 	 * Deletes the specified reception from the current class.
 	 * @param pVal the reception that should be deleted
 	 */
	public void deleteReception(IRPEventReception pVal);
	/**
 	 * Removes the inheritance relationship with the specified base class.
 	 * @param superClass the base class of the current class.
 	 */
	public void deleteSuperclass(IRPClass superClass);
	/**
 	 * Deletes the specified type from the current class.
 	 * @param name the name of the type that should be deleted
 	 */
	public void deleteType(String name);
	/**
 	 * Checks whether the class is an abstract class.
 	 * @return indication of whether the class is abstract - 1 if the class is abstract, 0 if not
 	 */
	public int getIsAbstract();
	/**
 	 * Checks whether the class was defined as "active", meaning that during execution it runs on its own thread.
 	 * @return indication of whether the class was defined as "active". 1 means that the class is "active", 0 means that the class was defined as "sequential".
 	 */
	public int getIsActive();
	/**
 	 * Checks whether a class does not inherit the behavior defined in the statechart of its base class. When you create a statechart for a class, by default it inherits the behavior defined in the statechart of its base class. However, Rhapsody allows you to specify that the class should not inherit this behavior. This operation checks whether this option has been exercised for the current class.
 	 * @return indication of whether or not the class inherits the behavior specified in the statechart of its base class. 1 means that it does not inherit this behavior, 0 means that it does inherit the behavior defined in the statechart of the base class.
 	 */
	public int getIsBehaviorOverriden();
	/**
 	 * Checks whether the class is a composite class.
 	 * @return indication of whether the class is a composite class. 1 means that the class is a composite class, 0 means it is not.
 	 */
	public int getIsComposite();
	/**
 	 * Checks whether the class is a final class. Relevant only for Java classes.
 	 * @return indication of whether the class is a final class - 1 if the class is final, 0 if not
 	 */
	public int getIsFinal();
	/**
 	 * Checks whether the class is a reactive class, meaning that a statechart or an activity diagram has been created for the class so that it reacts to events.
 	 * @return indication of whether the class is a reactive class. 1 means that the class is a reactive class, 0 means it is not.
 	 */
	public int getIsReactive();
	/**
 	 * Specifies that the class should be abstract.
 	 * @param isAbstract use 1 to specify that the class should be an abstract class, use 0 to specify that it should not be abstract
 	 */
	public void setIsAbstract(int isAbstract);
	/**
 	 * Specifies that the class should be defined as "active", meaning that during execution it runs on its own thread.
 	 * @param isActive 1 means that the class will be defined as "active", 0 means that the class will be defined as "sequential"
 	 */
	public void setIsActive(int isActive);
	/**
 	 * Specifies whether a class should inherit the behavior defined in the statechart of its base class. When you create a statechart for a class, by default it inherits the behavior defined in the statechart of its base class. However, Rhapsody allows you to specify that the class should not inherit this behavior.
 	 * @param isBehaviorOverriden use 1 if you do not want the class to inherit the behavior defined in the statechart of its base class. Use 0 if you want the class to inherit this behavior.
 	 */
	public void setIsBehaviorOverriden(int isBehaviorOverriden);
	/**
 	 * Specifies that the class should be a final class. Relevant only for Java classes.
 	 * @param newVal use 1 to specify that the class should be a final class, use 0 to specify that it should not be final
 	 */
	public void setIsFinal(int newVal);
	/**
 	 * Updates the views on the Rhapsody Model Manager server for all the diagrams contained in the class.
 	 * @param enforceUpdate Use 0 to specify that a view should be updated only if changes that affect the diagram were made since the last update. Use 1 to specify that views should be updated regardless of whether or not changes that affect the diagram were made since the last update.
 	 * @return the number of views that were updated on the server. If no diagrams require an update, 0 is returned. If the update attempt failed, -1 is returned.
 	 * @throws RhapsodyRuntimeException
 	 */
	public int updateContainedDiagramsOnServer(int enforceUpdate);


}

