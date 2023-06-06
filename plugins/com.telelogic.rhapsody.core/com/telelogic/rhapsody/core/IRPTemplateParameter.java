
//Licensed Materials - Property of IBM 
//(c) Copyright IBM Corporation 2006, 2018. All Rights Reserved.

package com.telelogic.rhapsody.core;


/**
 * The IRPTemplateParameter interface represents parameters of a template in Rhapsody models.
 */
public interface IRPTemplateParameter  extends IRPVariable {
	/**
 	 * Returns the type of the template parameter.
 	 * @return the type of the template parameter
 	 */
	public String getParameterKind();
	/**
 	 * For internal use only.
 	 */
	public IRPModelElement getRepresentative();
	/**
 	 * Sets the type of the parameter to "class".
 	 */
	public void setClassType();
	/**
 	 * Used to specify the type of the template parameter.
 	 * @param parameterKind the type to use for the template parameter
 	 */
	public void setParameterKind(String parameterKind);
	/**
 	 * For internal use only.
 	 */
	public void setRepresentative(IRPModelElement representative);


}

