
//Licensed Materials - Property of IBM 
//(c) Copyright IBM Corporation 2006, 2018. All Rights Reserved.

package com.telelogic.rhapsody.core;


 /**
  * The IRPInteractionOperand interface represents interaction operands in Rhapsody models.
  */
public interface IRPInteractionOperand  extends IRPCollaboration {
	/**
 	 * Returns a collection of all the messages contained in the interaction operand.
 	 * @return all the messages contained in the interaction operand
 	 */
	public IRPCollection getContainedMessages();
	/**
 	 * Returns the constraint (guard condition) that was defined for the interaction operand.
 	 * @return the constraint (guard condition) that was defined for the interaction operand
 	 */
	public String getInteractionConstraint();
	/**
 	 * Sets the constraint (guard condition) for the interaction operand.
 	 * @param interactionConstraint the constraint (guard condition) to use for the interaction operand, for example, "x = 5"
 	 */
	public void setInteractionConstraint(String interactionConstraint);


}

