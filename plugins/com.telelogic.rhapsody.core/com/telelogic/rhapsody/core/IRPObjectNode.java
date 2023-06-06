
//Licensed Materials - Property of IBM 
//(c) Copyright IBM Corporation 2006, 2018. All Rights Reserved.

package com.telelogic.rhapsody.core;


 /**
  * The IRPObjectNode interface represents Object Node elements in activity diagrams.
  */
public interface IRPObjectNode  extends IRPState {
	/**
 	 * Adds the specified state to the list of "In State" states for the object node.
 	 * @param val the state to add to the list of "In State" states.
 	 */
	public void addInState(IRPModelElement val);
	/**
 	 * @deprecated Use getInStateList() instead.
 	 */
	public String getInState();
	/**
 	 * Returns a collection of the "In State" states for the object node.
 	 * @return the "In State" states defined for the object node
 	 */
	public IRPCollection getInStateList();
	/**
 	 * Returns the class/type that this object node represents.
 	 * @return the class/type that this object node represents
 	 */
	public IRPModelElement getRepresents();
	/**
 	 * Removes the specified state from the list of "In State" states for the object node.
 	 * @param val the state to remove from the list 
 	 */
	public void removeInState(IRPModelElement val);
	/**
 	 * @deprecated Use addInState instead.
 	 */
	public void setInState(String inState);
	/**
 	 * Specifies the class/type that this object node should represent.
 	 * @param represents the class/type that this object node should represent 
 	 */
	public void setRepresents(IRPModelElement represents);


}

