
//Licensed Materials - Property of IBM 
//(c) Copyright IBM Corporation 2006, 2018. All Rights Reserved.

package com.telelogic.rhapsody.core;


/**
 * The IRPEvent interface represents events in Rhapsody models.
 */
public interface IRPEvent  extends IRPInterfaceItem {
	/**
	 * get property baseEvent
	 * @throws RhapsodyRuntimeException
	 */
	public IRPEvent getBaseEvent();
	/**
	 * get property baseEvent
	 * @throws RhapsodyRuntimeException
	 */
	public IRPEvent getSuperEvent();
	/**
	 * set property baseEvent
	 * @throws RhapsodyRuntimeException
	 */
	public void setBaseEvent(IRPEvent baseEvent);
	/**
	 * set property baseEvent
	 * @throws RhapsodyRuntimeException
	 */
	public void setSuperEvent(IRPEvent superEvent);


}

