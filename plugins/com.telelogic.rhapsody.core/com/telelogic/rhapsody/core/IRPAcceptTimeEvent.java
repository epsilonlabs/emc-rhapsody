
//Licensed Materials - Property of IBM 
//(c) Copyright IBM Corporation 2006, 2018. All Rights Reserved.

package com.telelogic.rhapsody.core;


 /**
  * The IRPAcceptTimeEvent interface represents Accept Time Event elements in activity diagrams and statecharts.
  */
public interface IRPAcceptTimeEvent  extends IRPState {
	/**
 	 * Returns the duration that was specified for this element.
 	 * @return the duration that was specified for this element
 	 */
	public String getDurationTime();
	/**
 	 * Specifies the duration that should be used for this element.
 	 * @param durationTime the duration that should be used for this element
 	 */
	public void setDurationTime(String durationTime);


}

