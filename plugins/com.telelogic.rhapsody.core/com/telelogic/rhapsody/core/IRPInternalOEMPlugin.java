
//Licensed Materials - Property of IBM 
//(c) Copyright IBM Corporation 2006, 2018. All Rights Reserved.

package com.telelogic.rhapsody.core;



public interface IRPInternalOEMPlugin {
	/**
	 * Notify the Plugin upon ActiveProjectAboutToChange
	 * @throws RhapsodyRuntimeException
	 */
	public int activeProjectAboutToChange();
	/**
	 * Notify the Plugin upon ActiveProjectHasChanged
	 * @throws RhapsodyRuntimeException
	 */
	public int activeProjectHasChanged();
	/**
	 * Selects a given menu item
	 * @throws RhapsodyRuntimeException
	 */
	public String onMenuItemSelect(String menuItem);
	/**
 	 * For internal use only.
 	 */
	public String onMenuItemSelectWithParameters(String menuItem, String parameters);
	/**
	 * Notify the Plugin upon RhapPluginAnimationStopped
	 * @throws RhapsodyRuntimeException
	 */
	public int rhapPluginAnimationStopped();
	/**
	 * Notify the Plugin upon RhpPluginAnimationStarted
	 * @throws RhapsodyRuntimeException
	 */
	public int rhpPluginAnimationStarted();
	/**
	 * Performs cleanup of the Plugin
	 * @throws RhapsodyRuntimeException
	 */
	public int rhpPluginCleanup();
	/**
	 * Notify the Plugin to executes a command
	 * @throws RhapsodyRuntimeException
	 */
	public void rhpPluginDoCommand(String theCommand);
	/**
	 * Performs final cleanup of the plugin
	 * @throws RhapsodyRuntimeException
	 */
	public int rhpPluginFinalCleanup();
	/**
	 * Initializes the plugin
	 * @throws RhapsodyRuntimeException
	 */
	public int rhpPluginInit();
	/**
	 * Invoke an item of the Plugin
	 * @throws RhapsodyRuntimeException
	 */
	public int rhpPluginInvokeItem();
	/**
	 * Notify the Plugin upon build done
	 * @throws RhapsodyRuntimeException
	 */
	public void rhpPluginOnIDEBuildDone(String buildStatus);
	/**
	 * Sets the IRPApplication of the plugin
	 * @throws RhapsodyRuntimeException
	 */
	public int rhpPluginSetApplication(IRPApplication pRPApp);
	/**
	 * Notify the Plugin upon Rhapsody save
	 * @throws RhapsodyRuntimeException
	 */
	public int rhpSavingProject();


}

