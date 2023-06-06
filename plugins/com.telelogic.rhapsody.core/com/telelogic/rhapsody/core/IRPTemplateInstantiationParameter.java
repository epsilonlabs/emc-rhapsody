
//Licensed Materials - Property of IBM 
//(c) Copyright IBM Corporation 2006, 2018. All Rights Reserved.

package com.telelogic.rhapsody.core;



public interface IRPTemplateInstantiationParameter  extends IRPModelElement {
	/**
	 * get property declaration
	 * @throws RhapsodyRuntimeException
	 */
	public String getArgValue();
	/**
	 * get property type
	 * @throws RhapsodyRuntimeException
	 */
	public IRPClassifier getType();
	/**
	 * set property declaration
	 * @throws RhapsodyRuntimeException
	 */
	public void setArgValue(String argValue);
	/**
	 * set property type
	 * @throws RhapsodyRuntimeException
	 */
	public void setType(IRPClassifier type);


}

