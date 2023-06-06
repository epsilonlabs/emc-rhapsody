
//Licensed Materials - Property of IBM 
//(c) Copyright IBM Corporation 2006, 2018. All Rights Reserved.

package com.telelogic.rhapsody.core;


/**
 * The IRPAssociationClass interface represents association classes in Rhapsody models. 
 */
public interface IRPAssociationClass  extends IRPClass {
	/**
 	 * Gets the relation represented by the first end of the association class.
 	 * @return the relation represented by the first end of the association class
 	 */
	public IRPRelation getEnd1();
	/**
 	 * Gets the relation represented by the second end of the association class.
 	 * @return the relation represented by the second end of the association class
 	 */
	public IRPRelation getEnd2();
	/**
 	 * Checks whether the element is an association class or an association element.
 	 * @return 1 if the element is an association class, 0 if it is an association element
 	 */
	public int getIsClass();
	/**
 	 * Specifies whether the element should be an association class or an association element.
 	 * @param isClass Use 1 to specify that the element should be an association class. Use 0 to specify that the element should be an association element.
 	 */
	public void setIsClass(int isClass);


}

