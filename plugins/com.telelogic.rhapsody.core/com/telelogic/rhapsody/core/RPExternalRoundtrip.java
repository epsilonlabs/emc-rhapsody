
//Licensed Materials - Property of IBM 
//(c) Copyright IBM Corporation 2006, 2018. All Rights Reserved.

package com.telelogic.rhapsody.core;



public abstract class RPExternalRoundtrip {

	private long m_nNativeDispEvent;
	
	// constructor
	public RPExternalRoundtrip(){
		super();
		m_nNativeDispEvent = 0;	
	}
	
	public boolean connect(IRPExternalRoundtripInvoker connectionPoint) {
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
	 * method CreateCodeModel
	 * @throws RhapsodyRuntimeException
	 */
	public abstract IRPModelElement createCodeModel(IRPCollection roundTrippedFileList);
	/**
	 * method OkToAddAggregate
	 * @throws RhapsodyRuntimeException
	 */
	public abstract int okToAddAggregate(IRPModelElement code_aggregate, IRPModelElement model_parent);
	/**
	 * method OkToMakeAction
	 * @throws RhapsodyRuntimeException
	 */
	public abstract int okToMakeAction(IRPModelElement model_object, String action);
	/**
	 * method ShouldAddAggregate
	 * @throws RhapsodyRuntimeException
	 */
	public abstract int shouldAddAggregate(IRPModelElement code_aggregate, IRPModelElement model_parent);
	/**
	 * method ShouldMergeAggregate
	 * @throws RhapsodyRuntimeException
	 */
	public abstract int shouldMergeAggregate(IRPModelElement model_aggregate, IRPModelElement model_parent, IRPModelElement code_aggregate, IRPModelElement code_parent);
	/**
	 * method ShouldMergeAssociation
	 * @throws RhapsodyRuntimeException
	 */
	public abstract int shouldMergeAssociation(String assoc_name, IRPModelElement model_assoc, IRPModelElement code_assoc, IRPModelElement model_object, IRPModelElement code_object);
	/**
	 * method ShouldMergeAttribute
	 * @throws RhapsodyRuntimeException
	 */
	public abstract int shouldMergeAttribute(String attribute_name, String model_value, String code_value, IRPModelElement model_object, IRPModelElement code_object, String value);
	/**
	 * method ShouldRemoveAggregate
	 * @throws RhapsodyRuntimeException
	 */
	public abstract int shouldRemoveAggregate(IRPModelElement model_aggregate, IRPModelElement model_parent, IRPModelElement code_parent);
	/**
	 * property isModelChanged
	 * @throws RhapsodyRuntimeException
	 */
	public abstract int isModelChanged();


}

