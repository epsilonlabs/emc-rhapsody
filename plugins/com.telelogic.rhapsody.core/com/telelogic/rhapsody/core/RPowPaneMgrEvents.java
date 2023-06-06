
//Licensed Materials - Property of IBM 
//(c) Copyright IBM Corporation 2006, 2018. All Rights Reserved.

package com.telelogic.rhapsody.core;



public abstract class RPowPaneMgrEvents {

	private long m_nNativeDispEvent;
	
	// constructor
	public RPowPaneMgrEvents(){
		super();
		m_nNativeDispEvent = 0;	
	}
	
	public boolean connect(IRPowPaneMgr connectionPoint) {
		if(m_nNativeDispEvent == 0)
		{
			m_nNativeDispEvent = advise(((RPInterface)connectionPoint).m_COMInterface);
			return (m_nNativeDispEvent != 0);
		}
		return false; //already connected
	}
	
	public boolean disconnect() {
		if(m_nNativeDispEvent != 0)
		{
			unadvise(m_nNativeDispEvent);
			m_nNativeDispEvent = 0;
			return true;
		}
		return false; //not connected
	}
	
	protected void finalize() throws Throwable {
		disconnect();
		super.finalize();
	}
	
	private native long advise(long connectionPointInterface);	
	
	private native void  unadvise(long nativeDispEvent);	

	/**
	 * method AddListElement
	 * @throws RhapsodyRuntimeException
	 */
	public abstract void addListElement(String sObjID, int nRow, int nCol, String sText);
	/**
	 * method AddPaneWnd
	 * @throws RhapsodyRuntimeException
	 */
	public abstract void addPaneWnd(int nType, int nSubType, String sTitle);
	/**
	 * method AddTextContent
	 * @throws RhapsodyRuntimeException
	 */
	public abstract void addTextContent(String sObjID, String sContent);
	/**
	 * method ClearListContent
	 * @throws RhapsodyRuntimeException
	 */
	public abstract void clearListContent(String sObjID);
	/**
	 * method ClearTextContent
	 * @throws RhapsodyRuntimeException
	 */
	public abstract void clearTextContent(String sObjID);
	/**
	 * method ClosePaneWnd
	 * @throws RhapsodyRuntimeException
	 */
	public abstract void closePaneWnd(String sObjID);
	/**
	 * method SetPaneWndTitle
	 * @throws RhapsodyRuntimeException
	 */
	public abstract void setPaneWndTitle(String sObjID, String sTitle);
	/**
	 * method ShowPaneWnd
	 * @throws RhapsodyRuntimeException
	 */
	public abstract void showPaneWnd(String sObjID);


}

