
//Licensed Materials - Property of IBM 
//(c) Copyright IBM Corporation 2006, 2018. All Rights Reserved.

package com.telelogic.rhapsody.core;



public interface IRPGraphNode  extends IRPGraphElement {
	/**
	 * method bringToFront
	 * @throws RhapsodyRuntimeException
	 */
	public void bringToFront();
	/**
	 * get property isPanelWidget
	 * @throws RhapsodyRuntimeException
	 */
	public int getIsPanelWidget();
	/**
	 * get property panelWidgetInstancePath
	 * @throws RhapsodyRuntimeException
	 */
	public IRPCollection getPanelWidgetInstancePath();
	/**
	 * method hideAllPorts
	 * @throws RhapsodyRuntimeException
	 */
	public void hideAllPorts();
	/**
	 * method sendToBack
	 * @throws RhapsodyRuntimeException
	 */
	public void sendToBack();
	/**
	 * set property panelWidgetInstancePath
	 * @throws RhapsodyRuntimeException
	 */
	public void setPanelWidgetInstancePath(IRPCollection panelWidgetInstancePath);
	/**
	 * method showAllPorts
	 * @throws RhapsodyRuntimeException
	 */
	public void showAllPorts();


}

