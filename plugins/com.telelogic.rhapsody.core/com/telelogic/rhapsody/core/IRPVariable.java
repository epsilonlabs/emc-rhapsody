
//Licensed Materials - Property of IBM 
//(c) Copyright IBM Corporation 2006, 2018. All Rights Reserved.

package com.telelogic.rhapsody.core;


/**
 * The IRPVariable interface represents the characteristics shared by model elements such as attributes, variables, and arguments.
 */
public interface IRPVariable  extends IRPUnit {
	/**
 	 * For tags with multiplicity greater than 1, this method can be used to add a model element as an additional value.
 	 * @param newDefaultVal the model element to add as an additional value
 	 * @return the value that was added
 	 */
	public IRPInstanceValue addElementDefaultValue(IRPModelElement newDefaultVal);
	/**
 	 * For tags with multiplicity greater than 1, this method can be used to add a string as an additional value.
 	 * @param newDefaultVal the string to add as an additional value
 	 * @return the value that was added
 	 */
	public IRPLiteralSpecification addStringDefaultValue(String newDefaultVal);
	/**
 	 * Returns the type declaration if an on-the-fly type was used for the element rather than an existing type.
 	 * @return the type declaration if an on-the-fly type was used for the element
 	 */
	public String getDeclaration();
	/**
 	 * Returns the default value that was set for the variable.
 	 * @return the default value of the variable
 	 */
	public String getDefaultValue();
	/**
 	 * Returns the type of the variable.
 	 * @return the type of the variable
 	 */
	public IRPClassifier getType();
	/**
 	 * Returns a collection of the initial values that were declared for elements where the multiplicity is greater than one. Note that the type of the objects contained in the returned collection depends upon the the type of element on which this method was called (there are a number of element types that inherit from IRPVariable). When the method is called on a tag, the objects in the returned collection are of type IRPContextSpecification. These objects are created for a tag when you call the method IRPModelElement.setTagContextValue.
 	 * @return the initial values that were declared for elements where the multiplicity is greater than one
 	 * @see IRPModelElement#setTagContextValue
 	 */
	public IRPCollection getValueSpecifications();
	/**
 	 * Specifies an "on-the-fly" declaration for the type of the element instead of using an existing type. Note that the string that you provide will be used as the declaration for the type even if if matches an existing type. For example, if you call this method with the argument "int", it will create an on-the-fly declaration consisting of "int". Use the method setTypeDeclaration if you want Rhapsody to first check whether there is an existing type with that name.
 	 * @param declaration the on-the-fly declaration to use for the type of the element
 	 */
	public void setDeclaration(String declaration);
	/**
 	 * Sets a new default value for the variable.
 	 * @param defaultValue the default value to use for the variable
 	 */
	public void setDefaultValue(String defaultValue);
	/**
 	 * Sets the type of the variable.
 	 * @param type the type to use for the variable
 	 */
	public void setType(IRPClassifier type);
	/**
 	 * Specifies an "on-the-fly" declaration for the type of the element but first checks whether there is an existing type that matches the string provided as an argument. If there is such a type, it will be used as the type of the model element. Note that this method is slower than the method setDeclaration because it first carries out a search. So if you definitely want to use an on-the-fly declaration, use the method setDeclaration instead.
 	 * @param newVal the type to use for the type of the element
 	 */
	public void setTypeDeclaration(String newVal);


}

