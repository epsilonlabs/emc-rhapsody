
//Licensed Materials - Property of IBM 
//(c) Copyright IBM Corporation 2006, 2018. All Rights Reserved.

package com.telelogic.rhapsody.core;



public interface IRPBaseExternalCodeGeneratorTool {
	/**
	 * method advanceCodeGenProgressBar
	 * @throws RhapsodyRuntimeException
	 */
	public void advanceCodeGenProgressBar();
	/**
	 * method shouldAbortCodeGeneration
	 * @throws RhapsodyRuntimeException
	 */
	public int shouldAbortCodeGeneration();
	/**
	 * method writeCodeGenMessage
	 * @throws RhapsodyRuntimeException
	 */
	public void writeCodeGenMessage(String msg);


}

