
//Licensed Materials - Property of IBM 
//(c) Copyright IBM Corporation 2006, 2018. All Rights Reserved.

package com.telelogic.rhapsody.core;



public interface IRPSearchResult {
	/**
	 * get property interfaceName
	 * @throws RhapsodyRuntimeException
	 */
	public String getInterfaceName();
	/**
	 * get property matchedField
	 * @throws RhapsodyRuntimeException
	 */
	public String getMatchedField();
	/**
	 * get property matchedFields
	 * @throws RhapsodyRuntimeException
	 */
	public IRPCollection getMatchedFields();
	/**
	 * get property matchedObject
	 * @throws RhapsodyRuntimeException
	 */
	public IRPModelElement getMatchedObject();
	/**
	 * get property name
	 * @throws RhapsodyRuntimeException
	 */
	public String getName();


}

