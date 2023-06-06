
//Licensed Materials - Property of IBM 
//(c) Copyright IBM Corporation 2006, 2018. All Rights Reserved.

package com.telelogic.rhapsody.core;



public abstract class RPExternalCodeGenerator {

	private long m_nNativeDispEvent;
	
	// constructor
	public RPExternalCodeGenerator(){
		super();
		m_nNativeDispEvent = 0;	
	}
	
	public boolean connect(IRPExternalCodeGeneratorInvoker connectionPoint) {
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
	 * method Abort
	 * @throws RhapsodyRuntimeException
	 */
	public abstract void abort();
	/**
	 * method Exit
	 * @throws RhapsodyRuntimeException
	 */
	public abstract void exit();
	/**
	 * method GetMainFileName
	 * @throws RhapsodyRuntimeException
	 */
	public abstract String getMainFileName(IRPModelElement configuration, int pathType, int withExtensions);
	/**
	 * method GetTargetfileName
	 * @throws RhapsodyRuntimeException
	 */
	public abstract String getTargetfileName(IRPModelElement configuration, int pathType, int withExtension);
	/**
	 * method WhoAmI
	 * @throws RhapsodyRuntimeException
	 */
	public abstract String whoAmI();
	/**
	 * method Generate
	 * @throws RhapsodyRuntimeException
	 */
	public abstract void generate(IRPModelElement activeConfiguration, IRPCollection classifiersCollection, IRPCollection filesCollection, int generateMainFile, int generateMakefile);
	/**
	 * method GetFileName
	 * @throws RhapsodyRuntimeException
	 */
	public abstract String getFileName(IRPModelElement modelElement, IRPModelElement configuration, int pathType, int withExtensions);
	/**
	 * method GetMakefileName
	 * @throws RhapsodyRuntimeException
	 */
	public abstract String getMakefileName(IRPModelElement configuration, int pathType, int withExtension);


}

