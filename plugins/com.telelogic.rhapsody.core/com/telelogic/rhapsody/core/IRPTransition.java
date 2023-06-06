
//Licensed Materials - Property of IBM 
//(c) Copyright IBM Corporation 2006, 2018. All Rights Reserved.

package com.telelogic.rhapsody.core;


 /**
  * The IRPTransition interface represents transitions in a statechart.
  */
public interface IRPTransition  extends IRPModelElement {
	/**
 	 * For transitions inherited from a base statechart, returns the base transition from which this transition is derived.
 	 * @return the base transition from which this transition is derived
 	 * @throws RhapsodyRuntimeException
 	 */
	public IRPTransition getInheritsFrom();
	/**
 	 * Checks whether the transition is a new transition added to the derived statechart, or a transition inherited from the base statechart.
 	 * @return 1 if the transition is a new transition added to the derived statechart, 0 if the transition is inherited from the base statechart
 	 * @throws RhapsodyRuntimeException
 	 */
	public int getIsOverridden();
	/**
 	 * Returns the action that was set for the transition.
 	 * @return the action for the transition
 	 * @throws RhapsodyRuntimeException
 	 */
	public IRPAction getItsAction();
	/**
 	 * Returns the guard that was set for the transition.
 	 * @return the guard for the transition
 	 * @throws RhapsodyRuntimeException
 	 */
	public IRPGuard getItsGuard();
	/**
 	 * Returns the trigger, guard, and action for the transition, as a single string, as it appears in the label for the transition in the statechart, for example, IgnitionEvent[gear == 0]/runStarter().
 	 * @return string consisting of the trigger, guard, and action for the transition
 	 * @throws RhapsodyRuntimeException
 	 */
	public String getItsLabel();
	/**
 	 * Returns the state that is the source of the transition.
 	 * @return the state that is the source of the transition
 	 * @throws RhapsodyRuntimeException
 	 */
	public IRPStateVertex getItsSource();
	/**
 	 * Returns the statechart that the transition belongs to.
 	 * @return the statechart that the transition belongs to
 	 * @throws RhapsodyRuntimeException
 	 */
	public IRPStatechart getItsStatechart();
	/**
 	 * Returns the state that is the target of the transition.
 	 * @return the state that is the target of the transition
 	 * @throws RhapsodyRuntimeException
 	 */
	public IRPStateVertex getItsTarget();
	/**
 	 * Returns the trigger that was set for the transition.
 	 * @return the trigger for the transition
 	 * @throws RhapsodyRuntimeException
 	 */
	public IRPTrigger getItsTrigger();
	/**
 	 * For default transitions, returns the state where the transition originates. If called on a non-default transition, returns null.
 	 * @return the state where the transition originates (for default transitions)
 	 * @throws RhapsodyRuntimeException
 	 */
	public IRPState getOfState();
	/**
 	 * Checks whether this is the default transition of the statechart.
 	 * @return 1 if the transition is the default transition, 0 otherwise
 	 * @throws RhapsodyRuntimeException
 	 */
	public int isDefaultTransition();
	/**
 	 * Checks whether the transition is an internal transition in a state.
 	 * @return 1 if the transition is an internal transition, 0 otherwise
 	 * @throws RhapsodyRuntimeException
 	 */
	public int isStaticReaction();
	/**
	 * method itsCompoundSource
	 * @throws RhapsodyRuntimeException
	 */
	public IRPCollection itsCompoundSource();
	/**
 	 * For internal use only.
 	 */
	public void overrideInheritance();
	/**
 	 * Restores inheritance from the base statechart for the three components that make up the transition label: trigger, guard, and action.
 	 * @return the transition on which the method was called
 	 * @throws RhapsodyRuntimeException
 	 */
	public IRPTransition resetLabelInheritance();
	/**
 	 * Sets the action for the transition.
 	 * @param action the action to use for the transition, for example, "runStarter()"
 	 * @return the action that was created
 	 * @throws RhapsodyRuntimeException
 	 */
	public IRPAction setItsAction(String action);
	/**
 	 * Sets the guard for the transition.
 	 * @param guard the guard to use for the transition, for example, "gear == 0"
 	 * @return the guard that was created
 	 * @throws RhapsodyRuntimeException
 	 */
	public IRPGuard setItsGuard(String guard);
	/**
 	 * Sets the trigger, guard, and action for the transition.
 	 * @param trigger the trigger to use for the transition - can be an event or triggered operation. If you use a string that does not match an existing event or triggered operation, a new event with that name is created.
 	 * @param guard the guard to use for the transition, for example, "gear == 0"
 	 * @param action the action to use for the transition, for example, "runStarter()"
 	 * @throws RhapsodyRuntimeException
 	 */
	public void setItsLabel(String trigger, String guard, String action);
	/**
 	 * Sets the source of the transition. Note that this method can only be used before the method createGraphics is called. Once the graphics have been created, you cannot use setItsSource to change the source of the transition.
 	 * @param itsSource the state that should be used as the source of the transition.
 	 * @throws RhapsodyRuntimeException
 	 */
	public void setItsSource(IRPStateVertex itsSource);
	/**
 	 * For internal use only.
 	 */
	public void setItsStatechart(IRPStatechart itsStatechart);
	/**
 	 * Sets the target of the transition. Note that this method can only be used before the method createGraphics is called. Once the graphics have been created, you cannot use setItsTarget to change the target of the transition.
 	 * @param itsTarget the state that should be used as the target of the transition.
 	 * @throws RhapsodyRuntimeException
 	 */
	public void setItsTarget(IRPStateVertex itsTarget);
	/**
 	 * Sets the trigger for the transition.
 	 * @param trigger the trigger to use for the transition - can be an event or triggered operation. If you use a string that does not match an existing event or triggered operation, a new event with that name is created.
 	 * @return the trigger that was created
 	 * @throws RhapsodyRuntimeException
 	 */
	public IRPTrigger setItsTrigger(String trigger);
	/**
 	 * For internal use only.
 	 */
	public void unoverrideInheritance();


}

