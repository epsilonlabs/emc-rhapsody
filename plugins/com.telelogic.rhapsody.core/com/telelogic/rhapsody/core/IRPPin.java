
//Licensed Materials - Property of IBM 
//(c) Copyright IBM Corporation 2006, 2018. All Rights Reserved.

package com.telelogic.rhapsody.core;


 /**
  * The IRPPin interface represents action pins added to actions, or activity parameters added to action blocks, in an activity diagram. To add an action pin to an action, use IRPState.addConnector, for example: <code>action1.addConnector("InPin")</code>, <code>action1.addConnector("OutPin")</code>, or <code>action1.addConnector("InOutPin")</code>.
  */
public interface IRPPin  extends IRPConnector {
	/**
 	 * Checks whether the element is an activity parameter or an action pin.
 	 * @return 1 if the element is an activity parameter, 0 if the element is an action pin.
 	 */
	public int getIsParameter();
	/**
 	 * Returns the direction of the pin/parameter: In, Out, or InOut.
 	 * @return the direction of the pin/parameter
 	 */
	public String getPinDirection();
	/**
 	 * Returns the type of the value held by the pin/parameter.
 	 * @return the type of the value held by the pin/parameter
 	 */
	public IRPClassifier getPinType();
	/**
 	 * Specifies whether the element should be an activity parameter or an action pin.
 	 * @param isParameter use 1 if you want the element to be an activity parameter, use 0 if you want the element to be an action pin 
 	 */
	public void setIsParameter(int isParameter);
	/**
 	 * Specifies the direction of the pin/parameter.
 	 * @param pinDirection the direction that should be used for the pin/parameter. The valid strings for this parameter are: In, Out, and InOut
 	 */
	public void setPinDirection(String pinDirection);
	/**
 	 * Specifies the type to use for the value held by the pin/parameter.
 	 * @param pinType the type to use for the value held by the pin/parameter 
 	 */
	public void setPinType(IRPClassifier pinType);


}

