
//Licensed Materials - Property of IBM 
//(c) Copyright IBM Corporation 2006, 2018. All Rights Reserved.

package com.telelogic.rhapsody.core;



public interface IRPProgressBar {
	/**
	 * get property interfaceName
	 * @throws RhapsodyRuntimeException
	 */
	public String getInterfaceName();
	/**
	 * method reset
	 * @throws RhapsodyRuntimeException
	 */
	public void reset();
	/**
	 * method tick
	 * @throws RhapsodyRuntimeException
	 */
	public void tick(int amount);


}

