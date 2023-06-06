
//Licensed Materials - Property of IBM 
//(c) Copyright IBM Corporation 2006, 2018. All Rights Reserved.

package com.telelogic.rhapsody.core;



public interface IRPFileFragment  extends IRPModelElement {
	/**
	 * get property fragmentElement
	 * @throws RhapsodyRuntimeException
	 */
	public IRPModelElement getFragmentElement();
	/**
	 * get property fragmentText
	 * @throws RhapsodyRuntimeException
	 */
	public String getFragmentText();
	/**
	 * get property fragmentType
	 * @throws RhapsodyRuntimeException
	 */
	public String getFragmentType();
	/**
	 * method moveFragmentInOwner
	 * @throws RhapsodyRuntimeException
	 */
	public void moveFragmentInOwner(int up);
	/**
	 * set property fragmentText
	 * @throws RhapsodyRuntimeException
	 */
	public void setFragmentText(String fragmentText);


}

