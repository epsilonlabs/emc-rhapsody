
//Licensed Materials - Property of IBM 
//(c) Copyright IBM Corporation 2006, 2018. All Rights Reserved.

package com.telelogic.rhapsody.core;


 /**
  * The IRPFile interface represents a file or folder to be generated during code generation.
  */
public interface IRPFile  extends IRPUnit {
	/**
	 * method addElement
Choose from = undefFragment, textFragment, implFragment, specFragment, moduleFragment
	 * @throws RhapsodyRuntimeException
	 */
	public void addElement(IRPClassifier element, String fileFragmentType);
	/**
	 * method addModelElement
Choose from = undefFragment, textFragment, implFragment, specFragment, moduleFragment
	 * @throws RhapsodyRuntimeException
	 */
	public void addModelElement(IRPModelElement element, String fileFragmentType);
	/**
	 * method addPackageToScope
	 * @throws RhapsodyRuntimeException
	 */
	public void addPackageToScope(IRPPackage p);
	/**
	 * method addTextElement
	 * @throws RhapsodyRuntimeException
	 */
	public void addTextElement(String text);
	/**
	 * method addToScope
	 * @throws RhapsodyRuntimeException
	 */
	public void addToScope(IRPClassifier element);
	/**
	 * get property elements
	 * @throws RhapsodyRuntimeException
	 */
	public IRPCollection getElements();
	/**
	 * get property fileFragments
	 * @throws RhapsodyRuntimeException
	 */
	public IRPCollection getFileFragments();
	/**
	 * get property fileType
	 * @throws RhapsodyRuntimeException
	 */
	public String getFileType();
	/**
	 * get property files
	 * @throws RhapsodyRuntimeException
	 */
	public IRPCollection getFiles();
	/**
	 * method getImpName
	 * @throws RhapsodyRuntimeException
	 */
	public String getImpName(int includingPath);
	/**
	 * get property path
	 * @throws RhapsodyRuntimeException
	 */
	public String getPath(int fullPath);
	/**
	 * method getSpecName
	 * @throws RhapsodyRuntimeException
	 */
	public String getSpecName(int includingPath);
	/**
	 * method isEmpty
	 * @throws RhapsodyRuntimeException
	 */
	public int isEmpty();
	/**
	 * set property fileType
	 * @throws RhapsodyRuntimeException
	 */
	public void setFileType(String fileType);
	/**
	 * property setPath
	 * @throws RhapsodyRuntimeException
	 */
	public void setPath(String path);


}

