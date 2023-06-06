
//Licensed Materials - Property of IBM 
//(c) Copyright IBM Corporation 2006, 2018. All Rights Reserved.

package com.telelogic.rhapsody.core;



public interface IRPFlow  extends IRPModelElement {
	/**
	 * method addConveyed
	 * @throws RhapsodyRuntimeException
	 */
	public void addConveyed(IRPModelElement pElement);
	/**
	 * get property conveyed
	 * @throws RhapsodyRuntimeException
	 */
	public IRPCollection getConveyed();
	/**
	 * get property direction
	 * @throws RhapsodyRuntimeException
	 */
	public String getDirection();
	/**
	 * get property end1
	 * @throws RhapsodyRuntimeException
	 */
	public IRPModelElement getEnd1();
	/**
	 * get property end1Port
	 * @throws RhapsodyRuntimeException
	 */
	public IRPPort getEnd1Port();
	/**
	 * get property end1SysMLPort
	 * @throws RhapsodyRuntimeException
	 */
	public IRPSysMLPort getEnd1SysMLPort();
	/**
	 * get property end2
	 * @throws RhapsodyRuntimeException
	 */
	public IRPModelElement getEnd2();
	/**
	 * get property end2Port
	 * @throws RhapsodyRuntimeException
	 */
	public IRPPort getEnd2Port();
	/**
	 * get property end2SysMLPort
	 * @throws RhapsodyRuntimeException
	 */
	public IRPSysMLPort getEnd2SysMLPort();
	/**
	 * method removeConveyed
	 * @throws RhapsodyRuntimeException
	 */
	public void removeConveyed(IRPModelElement pElement);
	/**
 	 * Specifies the direction to use for the flow.
 	 * @param direction can be one of the following values: "toEnd1", "toEnd2", "bidirectional"
 	 * @throws RhapsodyRuntimeException
 	 */
	public void setDirection(String direction);
	/**
	 * set property end1
	 * @throws RhapsodyRuntimeException
	 */
	public void setEnd1(IRPModelElement end1);
	/**
	 * method setEnd1ViaPort
	 * @throws RhapsodyRuntimeException
	 */
	public void setEnd1ViaPort(IRPInstance pInstance, IRPPort pPort);
	/**
	 * method setEnd1ViaSysMLPort
	 * @throws RhapsodyRuntimeException
	 */
	public void setEnd1ViaSysMLPort(IRPInstance pInstance, IRPSysMLPort pSysMLPort);
	/**
	 * set property end2
	 * @throws RhapsodyRuntimeException
	 */
	public void setEnd2(IRPModelElement end2);
	/**
	 * method setEnd2ViaPort
	 * @throws RhapsodyRuntimeException
	 */
	public void setEnd2ViaPort(IRPInstance pInstance, IRPPort pPort);
	/**
	 * method setEnd2ViaSysMLPort
	 * @throws RhapsodyRuntimeException
	 */
	public void setEnd2ViaSysMLPort(IRPInstance pInstance, IRPSysMLPort pSysMLPort);


}

