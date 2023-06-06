
//Licensed Materials - Property of IBM 
//(c) Copyright IBM Corporation 2006, 2018. All Rights Reserved.

package com.telelogic.rhapsody.core;



public interface IRPInteractionOperator  extends IRPModelElement {
	/**
	 * get property interactionOperands
	 * @throws RhapsodyRuntimeException
	 */
	public IRPCollection getInteractionOperands();
	/**
	 * get property interactionType
	 * @throws RhapsodyRuntimeException
	 */
	public String getInteractionType();
	/**
	 * set property interactionType
	 * @throws RhapsodyRuntimeException
	 */
	public void setInteractionType(String interactionType);


}

