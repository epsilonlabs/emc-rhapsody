
//Licensed Materials - Property of IBM 
//(c) Copyright IBM Corporation 2006, 2018. All Rights Reserved.

package com.telelogic.rhapsody.core;



public abstract class RPExternalCheck {

	private long m_nNativeDispEvent;
	
	// constructor
	public RPExternalCheck(){
		super();
		m_nNativeDispEvent = 0;	
	}
	
	public boolean connect(IRPExternalCheckRegistry connectionPoint) {
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
	 * Called by Rhapsody to execute the check. Return a list of elements to highlight if check fails (or empty list if the check is OK)
	 * @throws RhapsodyRuntimeException
	 */
	public abstract boolean check(IRPModelElement ElementToCheck, IRPCollection FailedElements);
	/**
	 * Return true if this is a check for completeness  or false if this is a check for correctness
	 * @throws RhapsodyRuntimeException
	 */
	public abstract boolean getCompleteness();
	/**
	 * Return the domain of the check which can be user defined or one from predefined list of <Common>, <Statechart> or <Class Model>. (For RIC <Object Model>)
	 * @throws RhapsodyRuntimeException
	 */
	public abstract String getDomain();
	/**
	 * Return a comma separated list of metaclasses or new terms - Rhapsody will call check for all elements in scope of check that are of the metaclass type in the list
	 * @throws RhapsodyRuntimeException
	 */
	public abstract String getMetaclasses();
	/**
	 * Return the name of the check (also used as its error message)
	 * @throws RhapsodyRuntimeException
	 */
	public abstract String getName();
	/**
	 * Return the Severity of the check which one from predefined list of <Error>, <Warning>, <Info>
	 * @throws RhapsodyRuntimeException
	 */
	public abstract String getSeverity();
	/**
	 * Return true if this check should be automatically called before code generation
	 * @throws RhapsodyRuntimeException
	 */
	public abstract boolean getShouldCallFromCG();
	/**
	 * exit and allow Rhapsody to exit
	 * @throws RhapsodyRuntimeException
	 */
	public abstract void doExit();


}

