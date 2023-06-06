
//Licensed Materials - Property of IBM 
//(c) Copyright IBM Corporation 2006, 2018. All Rights Reserved.

package com.telelogic.rhapsody.core;



public interface IRPPlugInWindow {
	/**
	 * Destroy window
	 * @throws RhapsodyRuntimeException
	 */
	public void destroyWindow();
	/**
	 * Get docking mode
	 * @throws RhapsodyRuntimeException
	 */
	public int getDocking();
	/**
	 * Get position string
	 * @throws RhapsodyRuntimeException
	 */
	public String getPosString();
	/**
	 * Get window handle
	 * @throws RhapsodyRuntimeException
	 */
	public long getWindowHandle();
	/**
	 * Set docking mode. 0=floating, 1=top, 2=left, 3=right, 4=bottom
	 * @throws RhapsodyRuntimeException
	 */
	public void setDocking(int nDockPos);
	/**
	 * Set position string
	 * @throws RhapsodyRuntimeException
	 */
	public void setPosString(String sPos);
	/**
	 * Set window title
	 * @throws RhapsodyRuntimeException
	 */
	public void setTitle(String sTitle);
	/**
	 * Show or hide window
	 * @throws RhapsodyRuntimeException
	 */
	public void showWindow(int nShow);
	/**
	 * get property interfaceName
	 * @throws RhapsodyRuntimeException
	 */
	public String getInterfaceName();


}

