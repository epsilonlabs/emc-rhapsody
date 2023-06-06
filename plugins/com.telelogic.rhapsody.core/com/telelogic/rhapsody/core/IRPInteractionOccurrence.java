
//Licensed Materials - Property of IBM 
//(c) Copyright IBM Corporation 2006, 2018. All Rights Reserved.

package com.telelogic.rhapsody.core;



public interface IRPInteractionOccurrence  extends IRPModelElement {
	/**
	 * get property messagePoints
	 * @throws RhapsodyRuntimeException
	 */
	public IRPCollection getMessagePoints();
	/**
	 * get property referenceSequenceDiagram
	 * @throws RhapsodyRuntimeException
	 */
	public IRPSequenceDiagram getReferenceSequenceDiagram();
	/**
	 * set property referenceSequenceDiagram
	 * @throws RhapsodyRuntimeException
	 */
	public void setReferenceSequenceDiagram(IRPSequenceDiagram referenceSequenceDiagram);


}

