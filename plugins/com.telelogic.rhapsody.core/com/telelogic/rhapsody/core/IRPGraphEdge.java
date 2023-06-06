
//Licensed Materials - Property of IBM 
//(c) Copyright IBM Corporation 2006, 2018. All Rights Reserved.

package com.telelogic.rhapsody.core;



public interface IRPGraphEdge  extends IRPGraphElement {
	/**
	 * method embedFlow
	 * @throws RhapsodyRuntimeException
	 */
	public IRPGraphEdge embedFlow(IRPFlow flow);
	/**
	 * method embedNewFlow
	 * @throws RhapsodyRuntimeException
	 */
	public IRPGraphEdge embedNewFlow();
	/**
	 * method getContainingArrow
	 * @throws RhapsodyRuntimeException
	 */
	public IRPGraphEdge getContainingArrow();
	/**
	 * get property source
	 * @throws RhapsodyRuntimeException
	 */
	public IRPGraphElement getSource();
	/**
	 * get property target
	 * @throws RhapsodyRuntimeException
	 */
	public IRPGraphElement getTarget();


}

