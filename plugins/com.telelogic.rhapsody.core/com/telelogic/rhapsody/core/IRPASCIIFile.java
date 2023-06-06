
//Licensed Materials - Property of IBM 
//(c) Copyright IBM Corporation 2006, 2018. All Rights Reserved.

package com.telelogic.rhapsody.core;



public interface IRPASCIIFile {
	/**
	 * close file
	 * @throws RhapsodyRuntimeException
	 */
	public void close();
	/**
	 * get property interfaceName
	 * @throws RhapsodyRuntimeException
	 */
	public String getInterfaceName();
	/**
	 * open file
	 * @throws RhapsodyRuntimeException
	 */
	public void open(String filename);
	/**
	 * write to file
	 * @throws RhapsodyRuntimeException
	 */
	public void write(String data);


}

