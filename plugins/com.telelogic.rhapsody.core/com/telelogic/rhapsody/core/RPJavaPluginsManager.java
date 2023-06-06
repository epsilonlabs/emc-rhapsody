
//Licensed Materials - Property of IBM 
//(c) Copyright IBM Corporation 2006, 2018. All Rights Reserved.

package com.telelogic.rhapsody.core;



public abstract class RPJavaPluginsManager {

	private long m_nNativeDispEvent;
	
	// constructor
	public RPJavaPluginsManager(){
		super();
		m_nNativeDispEvent = 0;	
	}
	
	public boolean connect(IRPJavaPlugins connectionPoint) {
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
	 * Calls a method of a plugins main class with two strings arguments
	 * @throws RhapsodyRuntimeException
	 */
	public abstract String call2StringPluginMethod(String PluginClassName, String methodName, String argument, String exargument);
	/**
	 * Calls a method of a plugins main class with ModelElement and collection
	 * @throws RhapsodyRuntimeException
	 */
	public abstract boolean callElementCollectionPluginMethod(String PluginClassName, String methodName, IRPModelElement element, IRPCollection collection);
	/**
	 * Calls a method of a plugins main class
	 * @throws RhapsodyRuntimeException
	 */
	public abstract boolean callPluginMethod(String PluginClassName, String methodName, IRPCollection args);
	/**
	 * Calls a method of a plugins main class with string in/out
	 * @throws RhapsodyRuntimeException
	 */
	public abstract String callStringPluginMethod(String PluginClassName, String methodName, String argument);
	/**
	 * Gets the id of the listener
	 * @throws RhapsodyRuntimeException
	 */
	public abstract String getId();
	/**
	 * Check if a method exists on a plugin
	 * @throws RhapsodyRuntimeException
	 */
	public abstract boolean hasPluginWithMethod(String PluginClassName, String methodName);
	/**
	 * Check if a method with arguments exists on a plugin
	 * @throws RhapsodyRuntimeException
	 */
	public abstract boolean hasPluginWithMethodArgs(String PluginClassName, String methodName, String arguments);
	/**
	 * Loads the Java plugin main class
	 * @throws RhapsodyRuntimeException
	 */
	public abstract boolean loadPlugin(String PluginClassName, IRPCollection classURLS, IRPCollection libURLS);
	/**
	 * Unload plugin
	 * @throws RhapsodyRuntimeException
	 */
	public abstract boolean unloadPlugin(String PluginClassName, int finalCall);


}

