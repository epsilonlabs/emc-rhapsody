
//Licensed Materials - Property of IBM 
//(c) Copyright IBM Corporation 2006, 2018. All Rights Reserved.

package com.telelogic.rhapsody.core;



public abstract class RPRTCListener {

	private long m_nNativeDispEvent;
	
	// constructor
	public RPRTCListener(){
		super();
		m_nNativeDispEvent = 0;	
	}
	
	public boolean connect(IRPApplication connectionPoint) {
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
	 * Called on request to execute a Command
	 * @throws RhapsodyRuntimeException
	 */
	public abstract String executeCommand(IRPUnit pUnit, String command, String parameters);
	/**
	 * Called after save is done in Rhapsody
	 * @throws RhapsodyRuntimeException
	 */
	public abstract boolean afterSave();
	/**
	 * Called after unit is saved in Rhapsody
	 * @throws RhapsodyRuntimeException
	 */
	public abstract boolean afterUnitSave(IRPUnit pUnit);
	/**
	 * Called on request to get unit's UUID
	 * @throws RhapsodyRuntimeException
	 */
	public abstract String getFileUUID(String strUnits);
	/**
	 * Gets the id of the listener
	 * @throws RhapsodyRuntimeException
	 */
	public abstract String getId();
	/**
	 * Called on request to get unit's status
	 * @throws RhapsodyRuntimeException
	 */
	public abstract int onGetStatus(IRPUnit pUnit);
	/**
	 * Called on request to locate unit in pending changes view
	 * @throws RhapsodyRuntimeException
	 */
	public abstract boolean onLocateInPendingChanges(IRPUnit pUnit);
	/**
	 * Called on request to locate unit in repository files' view
	 * @throws RhapsodyRuntimeException
	 */
	public abstract boolean onLocateInRepositoryFiles(IRPUnit pUnit);
	/**
	 * Called on request to lock unit
	 * @throws RhapsodyRuntimeException
	 */
	public abstract boolean onLock(IRPUnit pUnit);
	/**
	 * Called to refresh status cache in RTC
	 * @throws RhapsodyRuntimeException
	 */
	public abstract boolean onRefreshStatus();
	/**
	 * Called on request to show CM revisions' history of a unit
	 * @throws RhapsodyRuntimeException
	 */
	public abstract boolean onShowHistory(IRPUnit pUnit);
	/**
	 * Called on request to un-lock unit
	 * @throws RhapsodyRuntimeException
	 */
	public abstract boolean onUnlock(IRPUnit pUnit);


}

