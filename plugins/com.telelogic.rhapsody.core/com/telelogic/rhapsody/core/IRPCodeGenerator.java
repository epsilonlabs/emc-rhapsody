
//Licensed Materials - Property of IBM 
//(c) Copyright IBM Corporation 2006, 2018. All Rights Reserved.

package com.telelogic.rhapsody.core;



public interface IRPCodeGenerator {
	/**
	 * method to get generated code file names
	 * @throws RhapsodyRuntimeException
	 */
	public IRPCollection getCodeAnnotations(IRPModelElement element, int bSpecFile);
	/**
	 * method to get generated code file names
	 * @throws RhapsodyRuntimeException
	 */
	public IRPCollection getGeneratedFileNames(IRPModelElement element);
	/**
	 * get property interfaceName
	 * @throws RhapsodyRuntimeException
	 */
	public String getInterfaceName();


}

