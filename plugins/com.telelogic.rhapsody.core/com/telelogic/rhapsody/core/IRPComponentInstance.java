
//Licensed Materials - Property of IBM 
//(c) Copyright IBM Corporation 2006, 2018. All Rights Reserved.

package com.telelogic.rhapsody.core;



public interface IRPComponentInstance  extends IRPModelElement {
	/**
	 * get property componentType
	 * @throws RhapsodyRuntimeException
	 */
	public IRPComponent getComponentType();
	/**
	 * get property node
	 * @throws RhapsodyRuntimeException
	 */
	public IRPNode getNode();
	/**
	 * set property componentType
	 * @throws RhapsodyRuntimeException
	 */
	public void setComponentType(IRPComponent componentType);


}

