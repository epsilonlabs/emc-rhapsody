
//Licensed Materials - Property of IBM 
//(c) Copyright IBM Corporation 2006, 2018. All Rights Reserved.

package com.telelogic.rhapsody.core;


 /**
  * The IRPCollaborationDiagram interface represents collaboration diagrams in a Rhapsody model.
  */
public interface IRPCollaborationDiagram  extends IRPDiagram {
	/**
 	 * Returns the IRPCollaboration object underlying the collaboration diagram.
 	 * @return the IRPCollaboration object underlying the collaboration diagram
 	 */
	public IRPCollaboration getLogicalCollaboration();


}

