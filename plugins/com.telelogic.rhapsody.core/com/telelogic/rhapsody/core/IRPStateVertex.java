
//Licensed Materials - Property of IBM 
//(c) Copyright IBM Corporation 2006, 2018. All Rights Reserved.

package com.telelogic.rhapsody.core;


 /**
  * The IRPStateVertex interface represents the characteristics that are shared by various statechart elements such as states, join/fork connectors, and condition connectors.
  */
public interface IRPStateVertex  extends IRPModelElement {
	/**
 	 * Adds a control flow or object flow from this element to the specified element.
 	 * @param type the type of flow to create - the valid strings that can be used are ControlFlow and ObjectFlow
 	 * @param to the target element for the new flow
 	 * @return the flow created
 	 */
	public IRPTransition addFlow(String type, IRPStateVertex to);
	/**
 	 * Adds a transition from this element to the specified element.
 	 * @param to the target element for the new transition
 	 * @return the transition created
 	 */
	public IRPTransition addTransition(IRPStateVertex to);
	/**
 	 * Deletes the specified transition.
 	 * @param transition the transition to delete
 	 */
	public void deleteTransition(IRPTransition transition);
	/**
 	 * Returns all of the transitions that enter the element. Note that if there are any internal transitions defined, they will also be included in the collection that is returned. If you want to identify which transitions are internal, you can use the method IRPTransition.isStaticReaction().
 	 * @return all the transitions that enter the element (collection of IRPTransition elements).
 	 */
	public IRPCollection getInTransitions();
	/**
 	 * Returns all of the transitions that exit the element. Note that if there are any internal transitions defined, they will also be included in the collection that is returned. If you want to identify which transitions are internal, you can use the method IRPTransition.isStaticReaction().
 	 * @return all the transitions that exit the element (collection of IRPTransition elements).
 	 */
	public IRPCollection getOutTransitions();
	/**
 	 * Returns the element's parent. If the element is not contained in a specific state, the root state of the diagram is returned.
 	 * @return the element's parent
 	 */
	public IRPState getParent();
	/**
 	 * Sets the parent state of the element.
 	 * @param parent the state that should serve as the parent of the element
 	 */
	public void setParent(IRPState parent);


}

