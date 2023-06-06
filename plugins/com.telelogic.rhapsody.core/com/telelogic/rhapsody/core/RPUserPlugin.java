
//Licensed Materials - Property of IBM 
//© Copyright IBM Corporation 2006, 2008. All Rights Reserved.
package com.telelogic.rhapsody.core;

public abstract class RPUserPlugin {

	//	called when the plug-in is loaded
	public abstract void RhpPluginInit(final IRPApplication rpyApplication);

	//called when the plug-in menu item under the "Tools" menu is selected
	public abstract void RhpPluginInvokeItem();

	//invoked using CLI command -cmd=call <pluginName> <args>
	public void RhpPluginInvokeItem(String str){
		RhpPluginInvokeItem();
	}
	
	//called when the plug-in popup menu (if applicable) is selected
	public abstract void OnMenuItemSelect(String menuItem);
	
	//called when the plug-in popup trigger (if applicable) fired
	public abstract void OnTrigger(String trigger);

	//called when the project is closed - if true is returned the plugin will be unloaded
	public abstract boolean RhpPluginCleanup();

	//called when Rhapsody exits
	public abstract void RhpPluginFinalCleanup();
}

