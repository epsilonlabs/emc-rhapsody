
//Licensed Materials - Property of IBM 
//(c) Copyright IBM Corporation 2006, 2018. All Rights Reserved.

package com.telelogic.rhapsody.core;



public interface IRPRhapsodyServer {
	/**
	 * getApplication
	 * @throws RhapsodyRuntimeException
	 */
	public IRPApplication getApplication();
	/**
	 * getHiddenApplication
	 * @throws RhapsodyRuntimeException
	 */
	public IRPApplication getHiddenApplication();
	/**
	 * get property interfaceName
	 * @throws RhapsodyRuntimeException
	 */
	public String getInterfaceName();
	/**
	 * getUninitializedApplication
	 * @throws RhapsodyRuntimeException
	 */
	public IRPApplication getUninitializedApplication();
	/**
	 * initializeApplication
	 * @throws RhapsodyRuntimeException
	 */
	public void initializeApplication(IRPApplication pVal);


}

