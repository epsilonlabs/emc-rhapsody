
//Licensed Materials - Property of IBM 
//(c) Copyright IBM Corporation 2006, 2018. All Rights Reserved.

package com.telelogic.rhapsody.core;


 /**
  * The IRPRequirement interface represents requirements in a Rhapsody model.
  */
public interface IRPRequirement  extends IRPAnnotation {
	/**
 	 * Returns the ID that was set for the requirement.
 	 * @return the ID for the requirement
 	 */
	public String getRequirementID();
	/**
 	 * Sets the ID for the requirement.
 	 * @param requirementID the ID to use for the requirement
 	 */
	public void setRequirementID(String requirementID);


}

