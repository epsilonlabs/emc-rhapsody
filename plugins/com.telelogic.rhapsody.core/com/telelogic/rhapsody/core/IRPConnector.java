
//Licensed Materials - Property of IBM 
//(c) Copyright IBM Corporation 2006, 2018. All Rights Reserved.

package com.telelogic.rhapsody.core;


 /**
  * The IRPConnector interface represents the characteristics shared by the various types of "connector" elements that can be included in a statechart, such as condition connectors, history connectors, join sync bar connectors, and fork sync bar connectors.
  */
public interface IRPConnector  extends IRPStateVertex {
	/**
 	 * Creates a default transition leading to this connector, within the state specified.
 	 * @param from the state for which the default transition should be created
 	 * @return the default transition that was created
 	 */
	public IRPTransition createDefaultTransition(IRPState from);
	/**
 	 * Returns the type of the connector: Condition, Diagram, EnterExit, Fork, History, Join, Junction, Termination, InPin, OutPin, or InOutPin.
 	 * @return the type of the connector
 	 */
	public String getConnectorType();
	/**
 	 * Returns a collection of the transitions coming into the connector.
 	 * @return the transitions coming into the connector (a collection of IRPTransition elements)
 	 */
	public IRPCollection getDerivedInEdges();
	/**
 	 * Returns the transition exiting the connector.
 	 * @return the transition exiting the connector
 	 */
	public IRPTransition getDerivedOutEdge();
	/**
 	 * For connectors in a swimlane, returns the swimlane that contains the connector.
 	 * @return the swimlane that contains the connector
 	 */
	public IRPSwimlane getItsSwimlane();
	/**
 	 * For history connectors, returns the state that the history connector belongs to. This is the state for which the history connector maintains historical state information.
 	 * @return the state that this history connector belongs to
 	 */
	public IRPState getOfState();
	/**
 	 * Checks whether the connector is a condition connector.
 	 * @return 1 if the connector is a condition connector, 0 otherwise
 	 */
	public int isConditionConnector();
	/**
 	 * Checks whether the connector is a diagram connector.
 	 * @return 1 if the connector is a diagram connector, 0 otherwise
 	 */
	public int isDiagramConnector();
	/**
 	 * Checks whether the connector is a fork sync bar connector.
 	 * @return 1 if the connector is a fork sync bar connector, 0 otherwise
 	 */
	public int isForkConnector();
	/**
 	 * Checks whether the connector is a history connector.
 	 * @return 1 if the connector is a history connector, 0 otherwise
 	 */
	public int isHistoryConnector();
	/**
 	 * Checks whether the connector is a join sync bar connector.
 	 * @return 1 if the connector is a join sync bar connector, 0 otherwise
 	 */
	public int isJoinConnector();
	/**
 	 * Checks whether the connector is a junction connector.
 	 * @return 1 if the connector is a junction connector, 0 otherwise
 	 */
	public int isJunctionConnector();
	/**
 	 * Checks whether the connector is an EnterExit point. (Prior to version 6.0 of Rhapsody, EnterExit points were known as stub connectors.)
 	 * @return 1 if the connector is an EnterExit point, 0 otherwise
 	 */
	public int isStubConnector();
	/**
 	 * Checks whether the connector is a termination connector.
 	 * @return 1 if the connector is a termination connector, 0 otherwise
 	 */
	public int isTerminationConnector();
	/**
 	 * Specifies the swimlane that should contain this connector.
 	 * @param pVal the swimlane that should contain this connector
 	 */
	public void setItsSwimlane(IRPSwimlane pVal);
	/**
 	 * For history connectors, specifies the state for which the connector should maintain historical state information.
 	 * @param OfState the state for which the connector should maintain historical state information
 	 */
	public void setOfState(IRPState OfState);


}

