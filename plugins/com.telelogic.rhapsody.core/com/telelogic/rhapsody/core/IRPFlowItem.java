
//Licensed Materials - Property of IBM 
//(c) Copyright IBM Corporation 2006, 2018. All Rights Reserved.

package com.telelogic.rhapsody.core;


 /**
  * The IRPFlowItem interface represents item flows in Rhapsody models.
  */
public interface IRPFlowItem  extends IRPClassifier {
	/**
 	 * Adds an element to the collection of information elements that are represented by the item flow.
 	 * @param pElement the element to add to the collection
 	 */
	public void addRepresented(IRPModelElement pElement);
	/**
 	 * Returns a collection of all the information elements that are represented by the item flow.
 	 * @return all the information elements that are represented by the item flow
 	 */
	public IRPCollection getRepresented();
	/**
 	 * Removes the specified element from the collection of information elements that are represented by the item flow.
 	 * @param pElement the element that should be removed from the collection
 	 */
	public void removeRepresented(IRPModelElement pElement);


}

