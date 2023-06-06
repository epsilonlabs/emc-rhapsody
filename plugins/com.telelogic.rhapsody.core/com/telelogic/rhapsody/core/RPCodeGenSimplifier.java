
//Licensed Materials - Property of IBM 
//(c) Copyright IBM Corporation 2006, 2018. All Rights Reserved.

package com.telelogic.rhapsody.core;



public abstract class RPCodeGenSimplifier {

	private long m_nNativeDispEvent;
	
	// constructor
	public RPCodeGenSimplifier(){
		super();
		m_nNativeDispEvent = 0;	
	}
	
	public boolean connect(IRPCodeGenSimplifiersRegistry connectionPoint) {
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
	 * before all simplifications
	 * @throws RhapsodyRuntimeException
	 */
	public abstract void beginSimplification();
	/**
	 * abort the simplification
	 * @throws RhapsodyRuntimeException
	 */
	public abstract void doAbort();
	/**
	 * exit and allow Rhapsody to exit
	 * @throws RhapsodyRuntimeException
	 */
	public abstract void doExit();
	/**
	 * after all simplifications
	 * @throws RhapsodyRuntimeException
	 */
	public abstract void endSimplification();
	/**
	 * post element simplification
	 * @throws RhapsodyRuntimeException
	 */
	public abstract void postSimplify(IRPModelElement userElement, IRPModelElement mainSimplifiedElement, String simplificationRequested);
	/**
	 * simplify the user element
	 * @throws RhapsodyRuntimeException
	 */
	public abstract void simplify(IRPModelElement userElement, IRPModelElement simplifiedElementOwner, String simplificationRequested);


}

