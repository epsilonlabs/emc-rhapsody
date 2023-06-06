
//Licensed Materials - Property of IBM 
//(c) Copyright IBM Corporation 2006, 2018. All Rights Reserved.

package com.telelogic.rhapsody.core;


/**
 * The IRPInstanceValue interface is used in contexts where a single model element must be stored.
 */
public interface IRPInstanceValue  extends IRPValueSpecification {
	/**
 	 * Returns the stored value.
 	 * @return the stored value
 	 */
	public IRPModelElement getValue();
	/**
 	 * Sets the value to store.
 	 * @param value the model element to store as the value
 	 */
	public void setValue(IRPModelElement value);


}

