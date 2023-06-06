
//Licensed Materials - Property of IBM 
//(c) Copyright IBM Corporation 2006, 2018. All Rights Reserved.

package com.telelogic.rhapsody.core;


 /**
  * The IRPSequenceDiagram interface represents sequence diagrams in a Rhapsody model.
  */
public interface IRPSequenceDiagram  extends IRPDiagram {
	/**
 	 * Returns the IRPCollaboration object underlying the sequence diagram.
 	 * @return the IRPCollaboration object underlying the sequence diagram
 	 */
	public IRPCollaboration getLogicalCollaboration();
	 /**
 	  * For internal use only.
 	  */
	public IRPCollection getRelatedUseCases();


}

