
//Licensed Materials - Property of IBM 
//(c) Copyright IBM Corporation 2006, 2018. All Rights Reserved.

package com.telelogic.rhapsody.core;



public interface IRPGeneralization  extends IRPModelElement {
	/**
	 * get method baseClass
	 * @throws RhapsodyRuntimeException
	 */
	public IRPClassifier getBaseClass();
	/**
	 * get method derivedClass
	 * @throws RhapsodyRuntimeException
	 */
	public IRPClassifier getDerivedClass();
	/**
	 * get property extensionPoint
	 * @throws RhapsodyRuntimeException
	 */
	public String getExtensionPoint();
	/**
	 * get property is virtual
	 * @throws RhapsodyRuntimeException
	 */
	public int getIsVirtual();
	/**
	 * get property visibility
	 * @throws RhapsodyRuntimeException
	 */
	public String getVisibility();
	/**
	 * set method baseClass
	 * @throws RhapsodyRuntimeException
	 */
	public void setBaseClass(IRPClassifier baseClass);
	/**
	 * set method derivedClass
	 * @throws RhapsodyRuntimeException
	 */
	public void setDerivedClass(IRPClassifier derivedClass);
	/**
	 * set property extensionPoint
	 * @throws RhapsodyRuntimeException
	 */
	public void setExtensionPoint(String extensionPoint);
	/**
	 * set property is virtual
	 * @throws RhapsodyRuntimeException
	 */
	public void setIsVirtual(int isVirtual);
	/**
	 * set property visibility
	 * @throws RhapsodyRuntimeException
	 */
	public void setVisibility(String visibility);


}

