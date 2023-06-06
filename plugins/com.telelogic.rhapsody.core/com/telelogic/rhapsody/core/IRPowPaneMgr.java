
//Licensed Materials - Property of IBM 
//(c) Copyright IBM Corporation 2006, 2018. All Rights Reserved.

package com.telelogic.rhapsody.core;



public interface IRPowPaneMgr {
	/**
	 * method AddTabNotify
	 * @throws RhapsodyRuntimeException
	 */
	public void addTabNotify(int nType, int nSubType, String sObjID, String sTitle);
	/**
	 * method CloseTabNotify
	 * @throws RhapsodyRuntimeException
	 */
	public void closeTabNotify(String sObjID);
	/**
	 * get property interfaceName
	 * @throws RhapsodyRuntimeException
	 */
	public String getInterfaceName();
	/**
	 * get list listener
	 * @throws RhapsodyRuntimeException
	 */
	public IRPowListListener getOWListListener(String sObjID);
	/**
	 * get text listener
	 * @throws RhapsodyRuntimeException
	 */
	public IRPowTextListener getOWTextListener(String sObjID);


}

