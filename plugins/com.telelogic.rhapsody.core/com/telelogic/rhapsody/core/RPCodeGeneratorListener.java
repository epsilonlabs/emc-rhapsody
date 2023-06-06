
//Licensed Materials - Property of IBM 
//(c) Copyright IBM Corporation 2006, 2018. All Rights Reserved.

package com.telelogic.rhapsody.core;



public abstract class RPCodeGeneratorListener {

	private long m_nNativeDispEvent;
	
	// constructor
	public RPCodeGeneratorListener(){
		super();
		m_nNativeDispEvent = 0;	
	}
	
	public boolean connect(IRPCodeGenerator connectionPoint) {
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
	 * Gets the id of the listener
	 * @throws RhapsodyRuntimeException
	 */
	public abstract String getId();
	/**
	 * Called after code generation is completed
	 * @throws RhapsodyRuntimeException
	 */
	public abstract void onCodeGenerationCompleted();


}

