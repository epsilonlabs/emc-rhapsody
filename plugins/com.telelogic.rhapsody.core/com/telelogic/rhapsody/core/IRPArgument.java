
//Licensed Materials - Property of IBM 
//(c) Copyright IBM Corporation 2006, 2018. All Rights Reserved.

package com.telelogic.rhapsody.core;


 /**
  * The IRPArgument interface represents an argument of an operation or an event.
  */
public interface IRPArgument  extends IRPVariable {
	/**
 	 * Returns the direction of the argument (In, Out, or InOut).
 	 * @return the direction of the argument
 	 */
	public String getArgumentDirection();
	/**
 	 * Sets the direction of the argument.
 	 * @param argumentDirection the direction to use for the argument. The valid strings are In, Out, and InOut.
 	 */
	public void setArgumentDirection(String argumentDirection);


}

