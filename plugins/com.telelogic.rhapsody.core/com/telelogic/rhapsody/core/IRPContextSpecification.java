
//Licensed Materials - Property of IBM 
//(c) Copyright IBM Corporation 2006, 2018. All Rights Reserved.

package com.telelogic.rhapsody.core;


/**
 * The IRPContextSpecification interface represents the exact context of an object in a hierarchy. The context consists of two collections: 1) a collection of strings representing the model elements that constitute the full path to the element 2) a collection of the relevant indices for each of the model elements in the first collection. This makes it possible to point to a specific instance of the target model element when multiplicity is greater than one. The collection must consist of integers provided as strings.
 */
public interface IRPContextSpecification  extends IRPValueSpecification {
	/**
 	 * Returns the collection of the relevant indices for each of the model elements in the "value" collection. The collection consists of integers provided as strings.
 	 * @return the collection of the relevant indices for each of the model elements in the "value" collection
 	 */
	public IRPCollection getMultiplicities();
	/**
 	 * Returns the collection of strings that represents the model elements that constitute the full path to the element.
 	 * @return the collection of strings that represents the model elements that constitute the full path to the element
 	 */
	public IRPCollection getValue();
	/**
 	 * Specifies the collection of indices to use for the model elements in the "value" collection. . The collection must consist of integers provided as strings.
 	 * @param multiplicities the collection of indices to use for the model elements in the "value" collection
 	 */
	public void setMultiplicities(IRPCollection multiplicities);
	/**
 	 * Specifies the collection of strings that represents the model elements that constitute the full path to the element.
 	 * @param value the collection of strings to use to represent the model elements that constitute the full path to the element
 	 */
	public void setValue(IRPCollection value);


}

