
//Licensed Materials - Property of IBM 
//(c) Copyright IBM Corporation 2006, 2018. All Rights Reserved.

package com.telelogic.rhapsody.core;


 /**
  * The IRPAcceptEventAction interface represents Accept Event Action elements in a statechart or activity diagram. To create an Accept Event Action element, use the method IRPFlowchart.addAcceptEventAction.
  */
public interface IRPAcceptEventAction  extends IRPState {
	/**
 	 * Returns the event that the action waits for.
 	 * @return the event that the action waits for
 	 */
	public IRPEvent getEvent();
	/**
 	 * Specifies the event that the action should wait for.
 	 * @param event the event that the action should wait for
 	 */
	public void setEvent(IRPEvent event);


}

