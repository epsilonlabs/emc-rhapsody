
//Licensed Materials - Property of IBM 
//(c) Copyright IBM Corporation 2006, 2018. All Rights Reserved.

package com.telelogic.rhapsody.core;



public interface IRPNode  extends IRPClassifier {
	/**
	 * method addComponentInstance
	 * @throws RhapsodyRuntimeException
	 */
	public IRPComponentInstance addComponentInstance(String name);
	/**
	 * method deleteComponentInstance
	 * @throws RhapsodyRuntimeException
	 */
	public void deleteComponentInstance(String name);
	/**
	 * method findComponentInstance
	 * @throws RhapsodyRuntimeException
	 */
	public IRPComponentInstance findComponentInstance(String name);
	/**
	 * get property CPUtype
	 * @throws RhapsodyRuntimeException
	 */
	public String getCPUtype();
	/**
	 * get property componentInstances
	 * @throws RhapsodyRuntimeException
	 */
	public IRPCollection getComponentInstances();
	/**
	 * set property CPUtype
	 * @throws RhapsodyRuntimeException
	 */
	public void setCPUtype(String cPUtype);


}

