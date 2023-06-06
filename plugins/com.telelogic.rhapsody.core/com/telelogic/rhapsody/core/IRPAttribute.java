
//Licensed Materials - Property of IBM 
//(c) Copyright IBM Corporation 2006, 2018. All Rights Reserved.

package com.telelogic.rhapsody.core;


 /**
  * The IRPAttribute interface represents attributes of a class, and global variables. To create a new attribute, use the method IRPClassifier.addAttribute. To create a new variable, use the method IRPPackage.addGlobalVariable.
  */
public interface IRPAttribute  extends IRPVariable {
	/**
 	 * Checks whether the attribute was defined as constant.
 	 * @return 1 if the attribute was defined as constant, 0 otherwise
 	 */
	public int getIsConstant();
	/**
 	 * For attributes with multiplicity greater than one, checks whether the order of the items was specified as significant.
 	 * @return 1 if the attribute was defined as ordered, 0 otherwise
 	 */
	public int getIsOrdered();
	/**
 	 * Checks whether the attribute was defined as a pointer.
 	 * @return 1 if the attribute was defined as pointer, 0 otherwise
 	 */
	public int getIsReference();
	/**
 	 * Checks whether the attribute was defined as static.
 	 * @return 1 if the attribute was defined as static, 0 otherwise
 	 */
	public int getIsStatic();
	/**
 	 * Gets the multiplicity specified for the attribute.
 	 * @return the multiplicity specified for the attribute
 	 */
	public String getMultiplicity();
	/**
 	 * Gets the visibility specified for the attribute.
 	 * @return the visibility specified for the attribute
 	 */
	public String getVisibility();
	/**
 	 * Specifies whether an attribute should be defined as constant.
 	 * @param isConstant Use 1 to specify that the attribute should be defined as constant. Use 0 to specify that the attribute should not be defined as constant.
 	 */
	public void setIsConstant(int isConstant);
	/**
 	 * For attributes with multiplicity greater than one, this method is used to specify whether the attribute should be defined as ordered, meaning that the order of the items is significant.
 	 * @param isOrdered Use 1 to specify that the attribute should be defined as ordered. Use 0 to specify that the attribute should not be defined as ordered.
 	 */
	public void setIsOrdered(int isOrdered);
	/**
 	 * Specifies whether an attribute should be defined as a pointer.
 	 * @param isReference Use 1 to specify that the attribute should be defined as a pointer. Use 0 to specify that the attribute should not be defined as a pointer.
 	 */
	public void setIsReference(int isReference);
	/**
 	 * Specifies whether an attribute should be defined as static.
 	 * @param isStatic Use 1 to specify that the attribute should be defined as static. Use 0 to specify that the attribute should not be defined as static.
 	 */
	public void setIsStatic(int isStatic);
	/**
 	 * Specifies the multiplicity for the attribute.
 	 * @param multiplicity the multiplicity to use for the attribute. You can use strings such as "1" or "14" to specify a specific number, or you can use one of the values listed in the Features dialog for attributes: "0,1", "*", or "1..*". If you are setting the multiplicity to a value greater than one, use the setIsOrdered method to specify whether the order of the items is significant.
 	 */
	public void setMultiplicity(String multiplicity);
	/**
 	 * Specifies the visibility of the operation.
 	 * @param visibility the visibility to use for the operation. The possible values are "public", "private", and "protected". For C# projects, you can also use the value "project" for internal operations, and the value "projectOrProtected" for protected internal operations
 	 */
	public void setVisibility(String visibility);


}

