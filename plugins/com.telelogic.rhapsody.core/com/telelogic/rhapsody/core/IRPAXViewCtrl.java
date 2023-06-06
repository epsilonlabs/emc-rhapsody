
//Licensed Materials - Property of IBM 
//(c) Copyright IBM Corporation 2006, 2018. All Rights Reserved.

package com.telelogic.rhapsody.core;



public interface IRPAXViewCtrl {
	/**
	 * Execute command by command id
	 * @throws RhapsodyRuntimeException
	 */
	public void doCommand(long commandID);
	/**
	 * Execute command
	 * @throws RhapsodyRuntimeException
	 */
	public void executeCommand(String commandType, IRPCollection pCommandInitialization, IRPCollection pCommandResult);
	/**
	 * get property interfaceName
	 * @throws RhapsodyRuntimeException
	 */
	public String getInterfaceName();


}

