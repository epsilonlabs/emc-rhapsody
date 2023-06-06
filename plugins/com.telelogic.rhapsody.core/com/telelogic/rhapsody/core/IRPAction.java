
//Licensed Materials - Property of IBM 
//(c) Copyright IBM Corporation 2006, 2018. All Rights Reserved.

package com.telelogic.rhapsody.core;


 /**
  * The IRPAction interface represents the action defined for a transition in a statechart. 
  */
public interface IRPAction  extends IRPModelElement {
	/**
 	 * Gets the code defined as the action for the transition.
 	 * @return the code defined as the action for the transition. 
 	 */
	public String getBody();
	/**
 	 * Used to specify the code that serves as the action for the transition.
 	 * @param body The code that should be used as the action for the transition.
 	 */
	public void setBody(String body);


}

