
//Licensed Materials - Property of IBM 
//(c) Copyright IBM Corporation 2006, 2018. All Rights Reserved.

package com.telelogic.rhapsody.core;


/**
 * The IRPSendAction interface represents Send Action elements in an activity or statechart. To add a SendAction element, use addState to add a new state, and then call the method setStateType on the state your created, using "EventState" as the argument, for example:
 * <pre>
 * {@code
 * IRPState sendActionState = activity_1.getRootState().addState("send_action");
 * sendActionState.setStateType("EventState");
 * }
 * </pre>
 * After creating the send action state, you get the send action element as follows:
 * <pre>
 * {@code
 * IRPSendAction sendActionElement = sendActionState.getSendAction();
 * IRPEvent eventA = cameraPackage.addEvent("event_A");
 * sendActionElement.setEvent(eventA);
 * }
 * </pre>
 */
public interface IRPSendAction  extends IRPAction {
	/**
 	 * Provides an argument value for an argument of the event associated with the Send Action element.
 	 * @param value the value to use for the argument, expressed as a string
 	 * @param position the position of the argument in the argument list (starts at 1)
 	 */
	public void addArgumentValue(String value, int position);
	/**
 	 * Returns a collection of the argument values that were set for the event associated with the Send Action element. The collection consists of strings representing the argument values.
 	 * @return the argument values that were set for the event associated with the Send Action element
 	 */
	public IRPCollection getArgVals();
	/**
 	 * Gets the event sent by the Send Action element.
 	 * @return the event sent by the Send Action element
 	 */
	public IRPEvent getEvent();
	/**
 	 * Returns the IRPInterfaceItem element that is invoked by the Send Action element.
 	 * @return the IRPInterfaceItem element that is invoked by the Send Action element
 	 */
	public IRPInterfaceItem getInvokedOperation();
	/**
 	 * Gets the event target of the Send Action element.
 	 * @return the target of the Send Action element
 	 */
	public IRPModelElement getTarget();
	/**
 	 * Specifies the event sent by the Send Action element.
 	 * @param event the event that should be sent by the Send Action element
 	 */
	public void setEvent(IRPEvent event);
	/**
	 * set property invokedOperation
	 * @throws RhapsodyRuntimeException
	 */
	public void setInvokedOperation(IRPInterfaceItem invokedOperation);
	/**
 	 * Sets the specified model element to be the target of the Send Action element.
 	 * @param target the model element that should be used as the target of the Send Action element
 	 */
	public void setTarget(IRPModelElement target);


}

