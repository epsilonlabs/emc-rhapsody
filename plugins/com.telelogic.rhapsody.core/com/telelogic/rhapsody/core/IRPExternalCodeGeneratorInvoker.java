
//Licensed Materials - Property of IBM 
//(c) Copyright IBM Corporation 2006, 2018. All Rights Reserved.

package com.telelogic.rhapsody.core;



public interface IRPExternalCodeGeneratorInvoker  extends IRPBaseExternalCodeGeneratorTool {
	/**
	 * get property interfaceName
	 * @throws RhapsodyRuntimeException
	 */
	public String getInterfaceName();
	/**
	 * method notifyGenerationDone
	 * @throws RhapsodyRuntimeException
	 */
	public void notifyGenerationDone();


}

