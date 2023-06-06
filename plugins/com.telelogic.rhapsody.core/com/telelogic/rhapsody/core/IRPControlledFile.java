
//Licensed Materials - Property of IBM 
//(c) Copyright IBM Corporation 2006, 2018. All Rights Reserved.

package com.telelogic.rhapsody.core;


 /**
  * The IRPControlledFile interface represents a controlled file in a Rhapsody model. To access an element's controlled files, use the method IRPModelElement.getControlledFiles().
  */
public interface IRPControlledFile  extends IRPUnit {
	/**
 	 * Returns the full path of the controlled file.
 	 * @return the full path of the controlled file
 	 */
	public String getFullPathFileName();
	/**
 	 * Opens the controlled file, using the associated program.
 	 */
	public void open();
	/**
 	 * Specifies a different file to associate with the Controlled File element. Note that this must be a file that already exists in the project directory.
 	 * @param filename the file to associate with the Controlled File element - must be a file that already exists in the project directory
 	 */
	public void setTarget(String filename);


}

