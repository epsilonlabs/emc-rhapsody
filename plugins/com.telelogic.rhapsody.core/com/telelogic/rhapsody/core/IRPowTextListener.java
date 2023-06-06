
//Licensed Materials - Property of IBM 
//(c) Copyright IBM Corporation 2006, 2018. All Rights Reserved.

package com.telelogic.rhapsody.core;



public interface IRPowTextListener {
	/**
	 * method DblClickNotify
	 * @throws RhapsodyRuntimeException
	 */
	public void dblClickNotify(int nLine, String szLine);
	/**
	 * method SetObjID
	 * @throws RhapsodyRuntimeException
	 */
	public void setObjID(String bstrObjID);
	/**
	 * get property interfaceName
	 * @throws RhapsodyRuntimeException
	 */
	public String getInterfaceName();


}

