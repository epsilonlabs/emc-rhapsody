
//Licensed Materials - Property of IBM 
//(c) Copyright IBM Corporation 2006, 2018. All Rights Reserved.

package com.telelogic.rhapsody.core;



public abstract class RPExternalIDEManager {

	private long m_nNativeDispEvent;
	
	// constructor
	public RPExternalIDEManager(){
		super();
		m_nNativeDispEvent = 0;	
	}
	
	public boolean connect(IRPExternalIDERegistry connectionPoint) {
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
	 * Activate view
	 * @throws RhapsodyRuntimeException
	 */
	public abstract void activateView(IRPAXViewCtrl RhapsodyView);
	/**
	 * Create a Progress Task
	 * @throws RhapsodyRuntimeException
	 */
	public abstract int createProgressTask(int nGroupNumber, int nTaskNumber, String sTaskName, int nTaskLength, int bCanCancel);
	/**
	 * Finish a Progress Task
	 * @throws RhapsodyRuntimeException
	 */
	public abstract void finishProgressTask(int nGroupNumber, int nTaskNumber);
	/**
	 * method GetActiveView
	 * @throws RhapsodyRuntimeException
	 */
	public abstract IRPAXViewCtrl getActiveView();
	/**
	 * Check if a Progress Task is canceled
	 * @throws RhapsodyRuntimeException
	 */
	public abstract int isProgressTaskCanceled(int nGroupNumber, int nTaskNumber);
	/**
	 * method OnIDETextMessage
	 * @throws RhapsodyRuntimeException
	 */
	public abstract void onIDETextMessage(String message);
	/**
	 * method OnSearchRequest
	 * @throws RhapsodyRuntimeException
	 */
	public abstract void onInvokeSearch(IRPModelElement lookinElement);
	/**
	 * method OnNotifyMessage
	 * @throws RhapsodyRuntimeException
	 */
	public abstract void onNotifyMessage(String messageType, IRPCollection pMessageInitialization, IRPCollection pMessageResult);
	/**
	 * method OnShowInUnitView
	 * @throws RhapsodyRuntimeException
	 */
	public abstract void onShowInUnitView(IRPModelElement modelElement);
	/**
	 * Opens
	 * @throws RhapsodyRuntimeException
	 */
	public abstract void openFile(String filename);
	/**
	 * Opens file and selects line
	 * @throws RhapsodyRuntimeException
	 */
	public abstract void openFileAndSelectLine(String filename, int line);
	/**
	 * method OpenHotFeatures
	 * @throws RhapsodyRuntimeException
	 */
	public abstract void openHotFeatures();
	/**
	 * method OpenNewFeatures
	 * @throws RhapsodyRuntimeException
	 */
	public abstract void openNewFeatures(IRPModelElement element);
	/**
	 * Display YES
OCancel message box with check-box to remmember the chosen reply
	 * @throws RhapsodyRuntimeException
	 */
	public abstract String openYesNoCancelQuestion(String dialogTitle, String message, String toggleMessage, int toggleState);
	/**
	 * Indicate a Progress Task step performed
	 * @throws RhapsodyRuntimeException
	 */
	public abstract void progressTaskStep(int nGroupNumber, int nTaskNumber, int a_nStepsDone);
	/**
	 * Refresh Rhapsody project/workspace contents
	 * @throws RhapsodyRuntimeException
	 */
	public abstract void refreshRequest();
	/**
	 * Set a Progress Task subtask's name
	 * @throws RhapsodyRuntimeException
	 */
	public abstract void setProcessSubTaskName(int nGroupNumber, int nTaskNumber, String sSubTaskName);
	/**
	 * ShowBrowser
	 * @throws RhapsodyRuntimeException
	 */
	public abstract void showBrowser(int showOrHide);
	/**
	 * Display message in status bar
	 * @throws RhapsodyRuntimeException
	 */
	public abstract void showStatusBarMessage(String message);
	/**
	 * Closes diagram if opened
	 * @throws RhapsodyRuntimeException
	 */
	public abstract void closeDiagram(IRPDiagram diagram);
	/**
	 * method OpenDiagram
	 * @throws RhapsodyRuntimeException
	 */
	public abstract void openDiagram(IRPDiagram diagram);


}

