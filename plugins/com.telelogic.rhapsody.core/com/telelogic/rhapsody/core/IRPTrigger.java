
//Licensed Materials - Property of IBM 
//(c) Copyright IBM Corporation 2006, 2018. All Rights Reserved.

package com.telelogic.rhapsody.core;


 /**
  * The IRPTrigger interface represents the trigger of a transition in a statechart.
  */
public interface IRPTrigger  extends IRPModelElement {
	/**
	 * get property body
	 * @throws RhapsodyRuntimeException
	 */
	public String getBody();
	/**
	 * method getItsOperation
	 * @throws RhapsodyRuntimeException
	 */
	public IRPInterfaceItem getItsOperation();
	/**
	 * method isOperation
	 * @throws RhapsodyRuntimeException
	 */
	public int isOperation();
	/**
	 * method isTimeout
	 * @throws RhapsodyRuntimeException
	 */
	public int isTimeout();
	/**
	 * set property body
	 * @throws RhapsodyRuntimeException
	 */
	public void setBody(String body);


}

