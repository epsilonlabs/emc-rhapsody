
//Licensed Materials - Property of IBM 
//(c) Copyright IBM Corporation 2006, 2018. All Rights Reserved.

package com.telelogic.rhapsody.core;



public interface IRPTimingDiagram  extends IRPSequenceDiagram {
	/**
 	 * Checks whether the the timing diagram is an elaborated timing diagram.
 	 * @return indication of whether the diagram is an elaborated timing diagram. 1 means that the diagram is an elaborated timing diagram, 0 means that the diagram is a compact timing diagram.
 	 */
	public int getIsElaborated();
	/**
 	 * Specifies whether the diagram should be an elaborated timing diagram or a compact timing diagram.
 	 * @param isElaborated Use 1 to indicate that the diagram should be an elaborated timing diagram, 0 to indicate that the diagram should be a compact timing diagram. Note that the type of the timing diagram should not be changed after you have already added elements to the diagram.
 	 */
	public void setIsElaborated(int isElaborated);


}

