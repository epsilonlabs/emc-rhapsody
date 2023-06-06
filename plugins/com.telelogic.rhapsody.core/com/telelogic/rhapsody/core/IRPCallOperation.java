
//Licensed Materials - Property of IBM 
//(c) Copyright IBM Corporation 2006, 2018. All Rights Reserved.

package com.telelogic.rhapsody.core;


 /**
  * The IRPCallOperation interface represents call operation elements in activity diagrams.
  */
public interface IRPCallOperation  extends IRPState {
	/**
 	 * Returns the operation specified for this call operation element.
 	 * @return the operation specified for this call operation element
 	 */
	public IRPInterfaceItem getOperation();
	/**
 	 * Returns the target specified for this call operation element.
 	 * @return the target specified for this call operation element
 	 */
	public IRPRelation getTarget();
	/**
 	 * Specifies the operation to use for this call operation element.
 	 * @param operation the operation to use for this call operation element
 	 */
	public void setOperation(IRPInterfaceItem operation);
	/**
 	 * Specifies the target to use for this call operation element.
 	 * @param target the target to use for this call operation element
 	 */
	public void setTarget(IRPRelation target);


}

