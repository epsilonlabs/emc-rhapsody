
//Licensed Materials - Property of IBM 
//(c) Copyright IBM Corporation 2006, 2018. All Rights Reserved.

package com.telelogic.rhapsody.core;


 /**
  * The IRPState interface represents states in a statechart.
  */
public interface IRPState  extends IRPStateVertex {
	/**
 	 * Adds an ActivityFinal element to an Activity. This method should be called on the root state of the diagram, which you can get by calling IRPStatechart.getRootState().
 	 * @return the ActivityFinal element that was created
 	 */
	public IRPState addActivityFinal();
	/**
 	 * Adds a connector element of the specified type to the state.
 	 * @param type the type of connector that should be added - the valid values for this parameter are: Condition, Fork, History, Join, Termination, InPin, OutPin, InOutPin
 	 * @return the connector element that was created
 	 */
	public IRPConnector addConnector(String type);
	/**
	 * method addInternalTransition
	 * @throws RhapsodyRuntimeException
	 */
	public IRPTransition addInternalTransition(IRPInterfaceItem trigger);
	/**
 	 * Adds a new substate to this state. If you want to add a new top-level state to your statechart, you can call this method on the root state of the statechart, which you can get by calling IRPStatechart.getRootState(). 
 	 * @param name the name to use for the new state
 	 * @return the state created
 	 */
	public IRPState addState(String name);
	/**
 	 * Adds an internal transition to the state.
 	 * @param trigger the trigger to use for the internal transition
 	 * @return the internal transition that was created
 	 */
	public IRPTransition addStaticReaction(IRPInterfaceItem trigger);
	/**
 	 * Adds a termination state to a statechart. This method should be called on the root state of the statechart, which you can get by calling IRPStatechart.getRootState().
 	 * @return the termination state that was created
 	 */
	public IRPState addTerminationState();
	/**
 	 * Creates a default transition to this state from the state specified with the parameter.
 	 * @param from the source of the default transition, for example, the root state
 	 * @return the default transition that was created
 	 * <pre>
 	 * {@code
 	 * IRPStatechart coffeeMachineStatechart = coffeeMachine.addStatechart();
 	 * IRPState rootState = coffeeMachineStatechart.getRootState();
 	 * IRPState heatingState = rootState.addState("Heating");
 	 * heatingState.createDefaultTransition(rootState);
 	 * }
 	 * </pre>
 	 */
	public IRPTransition createDefaultTransition(IRPState from);
	/**
 	 * @deprecated Use {@link #createSubStatechart()} instead.
 	 */
 	@Deprecated
	public IRPStatechart createNestedStatechart();
	/**
 	 * Creates a sub-statechart for the state.
 	 * @return the new statechart that was created
 	 * @throws RhapsodyRuntimeException
 	 */
	public IRPStatechart createSubStatechart();
	/**
 	 * Deletes the specified connector element.
 	 * @param connector the connector element that should be deleted
 	 */
	public void deleteConnector(IRPConnector connector);
	/**
	 * method deleteInternalTransition
	 * @throws RhapsodyRuntimeException
	 */
	public void deleteInternalTransition(IRPTransition pVal);
	/**
 	 * Deletes the specified internal transition.
 	 * @param pVal the internal transition that should be deleted
 	 */
	public void deleteStaticReaction(IRPTransition pVal);
	/**
 	 * Returns the default transition within the state.
 	 * @return the default transition within the state
 	 */
	public IRPTransition getDefaultTransition();
	/**
 	 * Returns the entry action that was defined for the state.
 	 * @return the entry action that was defined for the state
 	 */
	public String getEntryAction();
	/**
 	 * Returns the exit action that was defined for the state.
 	 * @return the exit action that was defined for the state
 	 */
	public String getExitAction();
	/**
 	 * Returns the full name of the state within the statechart, including information about its hierarchical position within the statechart. For example, if your statechart includes a state called Listening within a top-level state called On, the full name would be ROOT.On.Listening.
 	 * @return the full name of the state within the statechart
 	 */
	public String getFullNameInStatechart();
	/**
 	 * Returns the corresponding state from the statechart of the class that this class is derived from.
 	 * @return the corresponding state from the statechart of the class that this class is derived from
 	 */
	public IRPState getInheritsFrom();
	/**
 	 * Returns a collection of the state's internal transitions.
 	 * @return the state's internal transitions
 	 * <pre>
 	 * {@code
 	 * IRPStatechart cameraStatechart = cameraClass.addStatechart();
 	 * IRPEvent trig_for_internal = cameraPackage.addEvent("trigger_internal");
 	 * IRPEvent trig_for_internal2 = cameraPackage.addEvent("trigger_internal2");
 	 * IRPState stateOne = cameraStatechart.getRootState().addState("state_one");
 	 * stateOne.addInternalTransition(trig_for_internal);
 	 * stateOne.addInternalTransition(trig_for_internal2);
 	 * // now, get and print out the state's internal transitions
 	 * IRPCollection allInternalTransitions = stateOne.getInternalTransitions();
 	 * IRPTransition currentTransition;
 	 * int numberOfInternalTransitions = allInternalTransitions.getCount();
 	 * for(int i = 1; i < numberOfInternalTransitions+1 ; i++) {
 	 * 	currentTransition = (IRPTransition)allInternalTransitions.getItem(i);
 	 * 	System.out.println(currentTransition.getDisplayName());
 	 * }
 	 * }
 	 * </pre>
 	 */
	public IRPCollection getInternalTransitions();
	/**
 	 * Checks whether there is still an inheritance relationship between this state and the corresponding state from the statechart of the class that this class is derived from.
 	 * @return 1 if the inheritance relationship is overridden, 0 if there is an inheritance relationship
 	 */
	public int getIsOverridden();
	/**
 	 * Checks whether this element is a call behavior element. Note that the Rhapsody API does not provide a method to change an existing IRPState element to a call behavior element. The only way to create a call behavior element is to call the method IRPFlowchart.addCallBehavior or IRPFlowchart.addReferenceActivity.
 	 * @return 1 if the element is a call behavior element, 0 if it is not
 	 */
	public int getIsReferenceActivity();
	/**
 	 * Returns the statechart that this state belongs to.
 	 * @return the statechart that this state belongs to
 	 */
	public IRPStatechart getItsStatechart();
	/**
 	 * Returns the swimlane that the action is located in.
 	 * @return the swimlane that the action is located in
 	 */
	public IRPSwimlane getItsSwimlane();
	/**
 	 * Returns a collection of all the substates of the current state and all the first-level substates of those states, meaning down to the second level.
 	 * @return a collection of all the substates of the current state and all the first-level substates of those states
 	 */
	public IRPCollection getLogicalStates();
	/**
 	 * Returns the state's sub-statechart.
 	 * @return the state's sub-statechart
 	 */
	public IRPStatechart getNestedStatechart();
	/**
 	 * For call behavior elements, returns the activity that is referenced.
 	 * @return the activity that is referenced
 	 */
	public IRPModelElement getReferenceToActivity();
	/**
 	 * Returns the Send Action element associated with the state. In the context of the API, a Send Action element is an object of type IRPState for which the state type was set to "EventState" using the setStateType method. In order to manipulate a Send Action element, for example, to set the event for the Send Action, you must first get the Second Action element using the method getSendAction.
 	 * @return the Send Action element associated with the state
 	 * <pre>
 	 * {@code
 	 * IRPState sendActionState = testfc.getRootState().addState("send_action");
 	 * sendActionState.setStateType("EventState");
 	 * IRPSendAction sendActionElement = sendActionState.getSendAction();
 	 * IRPEvent eventA = cameraPackage.addEvent("event_A");
 	 * sendActionElement.setEvent(eventA);
 	 * }
 	 * </pre>
 	 */
	public IRPSendAction getSendAction();
	/**
 	 * Returns the type of the state, for example, an And state or a Termination state. For the full list of state types, see the documentation for the operation setStateType.
 	 * @return the state's type
 	 */
	public String getStateType();
	/**
 	 * Returns a collection of the state's internal transitions.
 	 * @return the state's internal transitions
 	 */
	public IRPCollection getStaticReactions();
	/**
 	 * Returns a collection of all the first-level elements contained in this state - this includes both node elements and connector elements. The method does not return elements nested within these first-level elements.
 	 * @return a collection of all the first-level elements contained in this state
 	 */
	public IRPCollection getSubStateVertices();
	/**
 	 * Returns a collection of the substates contained in this state. Note that this will not work if a state contains a sub-statechart. In such a case, you would have to use code that resembles the following:
 	 * <pre> 
 	 * IRPState parentState = (IRPState)currentProject.findNestedElementRecursive("busy", "State");
 	 * IRPState topLevelStateInSubchart = (IRPState)(parentState.getNestedStatechart().getRootState().getSubStates().getItem(1));
 	 * IRPCollection substates = topLevelStateInSubchart.getSubStates();
 	 * </pre>
 	 * @return the substates contained in this state (collection of IRPState objects)
 	 */
	public IRPCollection getSubStates();
	/**
	 * method getTheEntryAction
	 * @throws RhapsodyRuntimeException
	 */
	public IRPAction getTheEntryAction();
	/**
	 * method getTheExitAction
	 * @throws RhapsodyRuntimeException
	 */
	public IRPAction getTheExitAction();
	/**
 	 * Checks whether the state contains one or more And Lines.
 	 * @return 1 if the state contains one or more And Lines, 0 otherwise.
 	 */
	public int isAnd();
	/**
 	 * Checks whether the state is a compound state, meaning a state that contains one or more substates.
 	 * @return 1 if the state is a compound state, 0 otherwise
 	 */
	public int isCompound();
	/**
 	 * Checks whether the state is a leaf state, meaning a state that does not contain any substates.
 	 * @return 1 if the state is a leaf state, 0 if the state contains one or more substates.
 	 */
	public int isLeaf();
	/**
 	 * Checks whether the state is the root state of the statechart.
 	 * @return 1 if the state is the root state of the statechart, 0 otherwise
 	 */
	public int isRoot();
	/**
 	 * Checks whether the state is a Send Action element.
 	 * @return 1 if it is a Send Action element, 0 otherwise
 	 */
	public int isSendActionState();
	/**
 	 * Breaks the inheritance relationship between this state and the corresponding state from the statechart of the class that this class is derived from.
 	 */
	public void overrideInheritance();
	/**
 	 * Restores the inheritance relationship between this state and the corresponding state from the statechart of the class that this class is derived from, for the entry action.
 	 * @return the state on which the method was called (sic)
 	 */
	public IRPState resetEntryActionInheritance();
	/**
 	 * Restores the inheritance relationship between this state and the corresponding state from the statechart of the class that this class is derived from, for the exit action.
 	 * @return the state on which the method was called (sic)
 	 */
	public IRPState resetExitActionInheritance();
	/**
 	 * Sets the entry action for the state.
 	 * @param entryAction the code to use for the state's entry action
 	 */
	public void setEntryAction(String entryAction);
	/**
 	 * Sets the exit action for the state.
 	 * @param exitAction the code to use for the state's exit action
 	 */
	public void setExitAction(String exitAction);
	/**
	 * method setInternalTransition
	 * @throws RhapsodyRuntimeException
	 */
	public void setInternalTransition(String trigVal, String guardVal, String actionVal);
	/**
 	 * Specifies the swimlane that the action should be in
 	 * @param itsSwimlane the swimlane that the action should be in 
 	 */
	public void setItsSwimlane(IRPSwimlane itsSwimlane);
	/**
 	 * For call behavior elements, sets the activity that is referenced by the element.
 	 * @param referenceToActivity the activity that should be referenced by the call behavior element
 	 */
	public void setReferenceToActivity(IRPModelElement referenceToActivity);
	/**
 	 * Specifies the type of the state
 	 * @param stateType the type of the state. The valid strings for this parameter are: "And", "Or" (for a state that is not an "And" state), "LocalTermination" (for Termination State), "Block" (for Action Block), "Action", "SubActivity", "EventState" (for Send Action), and "FlowFinal"
 	 */
	public void setStateType(String stateType);
	/**
 	 * Adds a new internal transition to the state.
 	 * @param trigVal the trigger to set for the internal transition
 	 * @param guardVal the guard to set for the internal transition
 	 * @param actionVal the action to set for the internal transition
 	 */
	public void setStaticReaction(String trigVal, String guardVal, String actionVal);
	/**
 	 * Restores the inheritance relationship between this state and the corresponding state from the statechart of the class that this class is derived from. This method is used to restore the relationship that was severed with the method overrideInheritance().
 	 */
	public void unoverrideInheritance();


}

