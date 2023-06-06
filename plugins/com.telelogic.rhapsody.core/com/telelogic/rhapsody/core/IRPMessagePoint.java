
//Licensed Materials - Property of IBM 
//(c) Copyright IBM Corporation 2006, 2018. All Rights Reserved.

package com.telelogic.rhapsody.core;



public interface IRPMessagePoint  extends IRPModelElement {
	/**
	 * method getClassifierRole
	 * @throws RhapsodyRuntimeException
	 */
	public IRPClassifierRole getClassifierRole();
	/**
	 * get property interactionOccurrence
	 * @throws RhapsodyRuntimeException
	 */
	public IRPInteractionOccurrence getInteractionOccurrence();
	/**
	 * get property interactionOperator
	 * @throws RhapsodyRuntimeException
	 */
	public IRPInteractionOperator getInteractionOperator();
	/**
	 * get property message
	 * @throws RhapsodyRuntimeException
	 */
	public IRPMessage getMessage();
	/**
	 * get property type
	 * @throws RhapsodyRuntimeException
	 */
	public String getType();


}

