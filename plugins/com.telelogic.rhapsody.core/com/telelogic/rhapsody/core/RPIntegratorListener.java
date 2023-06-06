
//Licensed Materials - Property of IBM 
//(c) Copyright IBM Corporation 2006, 2018. All Rights Reserved.

package com.telelogic.rhapsody.core;



public abstract class RPIntegratorListener {

	private long m_nNativeDispEvent;
	
	// constructor
	public RPIntegratorListener(){
		super();
		m_nNativeDispEvent = 0;	
	}
	
	public boolean connect(IRPIntegrator connectionPoint) {
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
	 * Called after import model is completed
	 * @throws RhapsodyRuntimeException
	 */
	public abstract void afterImportModel(IRPModelElement rootElement);
	/**
	 * Gets the id of the listener
	 * @throws RhapsodyRuntimeException
	 */
	public abstract String getId();
	/**
	 * Get the application's name that Rhapsody integrates with
	 * @throws RhapsodyRuntimeException
	 */
	public abstract String subscribedTo();


}

