
//Licensed Materials - Property of IBM 
//(c) Copyright IBM Corporation 2006, 2018. All Rights Reserved.

package com.telelogic.rhapsody.core;


 /**
  * The IRPSwimlane interface represents swimlanes in an activity diagram.
  */
public interface IRPSwimlane  extends IRPModelElement {
	/**
 	 * For internal use only.
 	 */
	public IRPSwimlane addSwimlane(String name);
	/**
 	 * Returns a collection of the elements contained in the swimlane.
 	 * @return the elements contained in the swimlane
 	 * @throws RhapsodyRuntimeException
 	 */
	public IRPCollection getContents();
	/**
 	 * Returns the model element that the swimlane represents.
 	 * @return the model element that the swimlane represents
 	 */
	public IRPModelElement getRepresents();
	/**
 	 * Returns a collection of the swimlanes that are nested under this swimlane.
 	 * @return the swimlanes nested under this swimlane
 	 * @throws RhapsodyRuntimeException
 	 */
	public IRPCollection getSwimlanes();
	/**
 	 * Specifies the model element that the swimlane is to represent.
 	 * @param represents the model element that the swimlane is to represent
 	 * @throws RhapsodyRuntimeException
 	 */
	public void setRepresents(IRPModelElement represents);


}

