
//Licensed Materials - Property of IBM 
//(c) Copyright IBM Corporation 2006, 2018. All Rights Reserved.

package com.telelogic.rhapsody.core;


 /**
  * The IRPActivityDiagram interface represents activity diagrams in Rhapsody models.
  */
public interface IRPActivityDiagram  extends IRPStatechartDiagram {
	/**
 	 * Decomposes the specified swimlane into two swimlanes.
 	 * @param graphSwimlane the graphic element representing the swimlane to decompose
 	 * @return the graphic elements representing the two new swimlanes
 	 * @throws RhapsodyRuntimeException
 	 */
	public IRPCollection decomposeSwimlane(IRPGraphElement graphSwimlane);
	/**
 	 * Returns the IRPFlowchart object underlying the activity diagram.
 	 * @return the IRPFlowchart object underlying the activity diagram
 	 */
	public IRPFlowchart getFlowchart();


}

