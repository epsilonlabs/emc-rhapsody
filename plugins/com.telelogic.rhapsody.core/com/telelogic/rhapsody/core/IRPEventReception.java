
//Licensed Materials - Property of IBM 
//(c) Copyright IBM Corporation 2006, 2018. All Rights Reserved.

package com.telelogic.rhapsody.core;



public interface IRPEventReception  extends IRPInterfaceItem {
	/**
	 * method getEvent
	 * @throws RhapsodyRuntimeException
	 */
	public IRPEvent getEvent();
	/**
	 * method setEvent
	 * @throws RhapsodyRuntimeException
	 */
	public void setEvent(IRPEvent pVal);


}

