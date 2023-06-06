
//Licensed Materials - Property of IBM 
//(c) Copyright IBM Corporation 2006, 2018. All Rights Reserved.

package com.telelogic.rhapsody.core;


 /**
  * The IRPActor interface represents actors in Rhapsody models.
  */
public interface IRPActor  extends IRPClassifier {
	/**
 	 * Adds a new event reception, using the specified event.
 	 * @param name the name to use for the new event reception
 	 * @param event the event that should be associated with the new event reception
 	 * @return the event reception that was created
 	 * @throws RhapsodyRuntimeException
 	 */
	public IRPEventReception addEventReceptionWithEvent(String name, IRPEvent event);
	/**
 	 * Checks whether an actor does not inherit the behavior defined in the statechart of its base class. When you create a statechart for an actor, by default it inherits the behavior defined in the statechart of its base class. However, Rhapsody allows you to specify that the actor should not inherit this behavior. This operation checks whether this option has been exercised for the current actor.
 	 * @return indication of whether or not the actor inherits the behavior specified in the statechart of its base class. 1 means that it does not inherit this behavior, 0 means that it does inherit the behavior defined in the statechart of the base class.
 	 */
	public int getIsBehaviorOverriden();
	/**
 	 * Specifies whether an actor should inherit the behavior defined in the statechart of its base class. When you create a statechart for an actor, by default it inherits the behavior defined in the statechart of its base class. However, Rhapsody allows you to specify that the actor should not inherit this behavior.
 	 * @param isBehaviorOverriden use 1 if you do not want the actor to inherit the behavior defined in the statechart of its base class. Use 0 if you want the actor to inherit this behavior.
 	 */
	public void setIsBehaviorOverriden(int isBehaviorOverriden);
	/**
 	 * Updates the views on the Rhapsody Model Manager server for all the diagrams contained in the actor.
 	 * @param enforceUpdate Use 0 to specify that a view should be updated only if changes that affect the diagram were made since the last update. Use 1 to specify that views should be updated regardless of whether or not changes that affect the diagram were made since the last update.
 	 * @return the number of views that were updated on the server. If no diagrams require an update, 0 is returned. If the update attempt failed, -1 is returned.
 	 * @throws RhapsodyRuntimeException
 	 */
	public int updateContainedDiagramsOnServer(int enforceUpdate);


}

