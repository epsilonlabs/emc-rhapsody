
//Licensed Materials - Property of IBM 
//(c) Copyright IBM Corporation 2006, 2018. All Rights Reserved.

package com.telelogic.rhapsody.core;



public interface IRPExternalIDERegistry {
	/**
	 * Initiate Progress Task execution
	 * @throws RhapsodyRuntimeException
	 */
	public void progressTaskAsynchCallback(int nGroupNumber, int nTaskNumber);
	/**
	 * Initiate Progress Task execution
	 * @throws RhapsodyRuntimeException
	 */
	public void progressTaskAsynchEliminate(int nGroupNumber, int nTaskNumber);
	/**
	 * method SendIDETextMessage
	 * @throws RhapsodyRuntimeException
	 */
	public void sendIDETextMessage(String message);
	/**
	 * get property interfaceName
	 * @throws RhapsodyRuntimeException
	 */
	public String getInterfaceName();


}

