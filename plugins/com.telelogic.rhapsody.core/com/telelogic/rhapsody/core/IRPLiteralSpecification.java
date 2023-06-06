
//Licensed Materials - Property of IBM 
//(c) Copyright IBM Corporation 2006, 2018. All Rights Reserved.

package com.telelogic.rhapsody.core;


/**
 * The IRPLiteralSpecification interface is used in contexts where a single value must be stored.
 */
public interface IRPLiteralSpecification  extends IRPValueSpecification {
	/**
 	 * Returns the stored value.
 	 * @return the stored value
 	 */
	public String getValue();
	/**
 	 * Sets the value to store.
 	 * @param value the value to store
 	 */
	public void setValue(String value);


}

