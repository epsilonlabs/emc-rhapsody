
//Licensed Materials - Property of IBM 
//(c) Copyright IBM Corporation 2006, 2018. All Rights Reserved.

package com.telelogic.rhapsody.core;



public abstract class RPApplicationListener {

	private long m_nNativeDispEvent;
	
	// constructor
	public RPApplicationListener(){
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
	 * Called after element is added
	 * @throws RhapsodyRuntimeException
	 */
	public abstract boolean afterAddElement(IRPModelElement pModelElement);
	/**
	 * Called after project is closed
	 * @throws RhapsodyRuntimeException
	 */
	public abstract boolean afterProjectClose(String bstrProjectName);
	/**
	 * Called before project is closed
	 * @throws RhapsodyRuntimeException
	 */
	public abstract boolean beforeProjectClose(IRPProject pProject);
	/**
	 * Gets the id of the listener
	 * @throws RhapsodyRuntimeException
	 */
	public abstract String getId();
	/**
	 * Called when diagram is opened
	 * @throws RhapsodyRuntimeException
	 */
	public abstract boolean onDiagramOpen(IRPDiagram pDiagram);
	/**
	 * Called on double click
	 * @throws RhapsodyRuntimeException
	 */
	public abstract boolean onDoubleClick(IRPModelElement pModelElement);
	/**
	 * Called when element features dialog is opened
	 * @throws RhapsodyRuntimeException
	 */
	public abstract boolean onFeaturesOpen(IRPModelElement pModelElement);

	public boolean activeProjectAboutToChange(IRPProject project)
	{
		return false;
	}
	public boolean activeProjectHasChanged(IRPProject project)
	{
		return false;
	}
	public boolean afterApplicationClosed()
	{
		return false;
	}
	public boolean afterDeleteElement(String elementGUID)
	{
		return false;
	}
	public boolean afterProjectOpen(IRPProject project)
	{
		return false;
	}
	public boolean afterProjectSaved(IRPProject project)
	{
		return false;
	}
	public boolean beforeApplicationClosed()
	{
		return false;
	}
	public boolean beforeDeleteElement(IRPModelElement modelElement)
	{
		return false;
	}
	public boolean beforeProjectOpen(String projectPath)
	{
		return false;
	}
	public boolean beforeProjectSaved(IRPProject project)
	{
		return false;
	}
	public boolean onElementsChanged(String elementsGUIDs)
	{
		return false;
	}
	public boolean onPerspectiveChange(String oldPerspective, String newPerspective)
	{
		return false;
	}
	public boolean onSelectionChanged()
	{
		return false;
	}



}

