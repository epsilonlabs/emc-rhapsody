
//Licensed Materials - Property of IBM 
//(c) Copyright IBM Corporation 2006, 2018. All Rights Reserved.

package com.telelogic.rhapsody.core;



public interface IRPRoundTrip {
	/**
	 * get property interfaceName
	 * @throws RhapsodyRuntimeException
	 */
	public String getInterfaceName();
	/**
	 * roundtrip file
	 * @throws RhapsodyRuntimeException
	 */
	public IRPCollection roundtripFile(String filename, int reGenerateFile);


}

