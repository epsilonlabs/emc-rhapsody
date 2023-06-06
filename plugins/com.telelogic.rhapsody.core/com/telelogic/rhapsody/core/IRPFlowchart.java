
//Licensed Materials - Property of IBM 
//(c) Copyright IBM Corporation 2006, 2018. All Rights Reserved.

package com.telelogic.rhapsody.core;


 /**
  * The IRPFlowchart interface represents activities in Rhapsody models.
  */
public interface IRPFlowchart  extends IRPStatechart {
	/**
 	 * Adds a new Accept Event Action element to the activity.
 	 * @param name the name to use for the new Accept Event Action element
 	 * @param parent the diagram element to which the new Accept Event Action element should be added. If the Accept Event Action element is being added to an Action Block, this parameter should be the Action Block. Otherwise, it should be the root state of the diagram (which is obtained by calling IRPStatechart.getRootState()).
 	 * @return the Accept Event Action element that was created
 	 */
	public IRPAcceptEventAction addAcceptEventAction(String name, IRPState parent);
	/**
 	 * Adds a new Accept Time Event element to the activity.
 	 * @param name the name to use for the new Accept Time Event element
 	 * @param parent the diagram element to which the new Accept Time Event element should be added. If the Accept Time Event element is being added to an Action Block, this parameter should be the Action Block. Otherwise, it should be the root state of the diagram (which is obtained by calling IRPStatechart.getRootState()).
 	 * @return the Accept Time Event element that was created
 	 */
	public IRPAcceptTimeEvent addAcceptTimeEvent(String name, IRPState parent);
	/**
 	 * Adds an activity parameter to the frame of the activity
 	 * @param name the name to use for the new activity parameter
 	 * @return the activity parameter element that was created
 	 */
	public IRPPin addActivityParameter(String name);
	/**
 	 * Adds a new Call Behavior element to the activity.
 	 * @param referenced the activity that the new Call Behavior element should invoke
 	 * @return the Call Behavior element that was created
 	 */
	public IRPState addCallBehavior(IRPModelElement referenced);
	/**
 	 * Adds a new Call Operation element to the activity.
 	 * @param name the name to use for the new Call Operation element
 	 * @param parent the diagram element to which the new Call Operation element should be added. If the Call Operation element is being added to an Action Block, this parameter should be the Action Block. Otherwise, it should be the root state of the diagram (which is obtained by calling IRPStatechart.getRootState()).
 	 * @return the Call Operation element that was created
 	 */
	public IRPCallOperation addCallOperation(String name, IRPState parent);
	/**
 	 * Adds a new Object Node element to the activity.
 	 * @param name the name to use for the new Object Node element
 	 * @param parent the diagram element to which the new Object Node element should be added. If the Object Node element is being added to an Action Block, this parameter should be the Action Block. Otherwise, it should be the root state of the diagram (which is obtained by calling IRPStatechart.getRootState()).
 	 * @return the Object Node element that was created
 	 */
	public IRPObjectNode addObjectNode(String name, IRPState parent);
	/**
 	 * Adds a new Call Behavior element to the activity. Performs same action as the addCallBehavior method.
 	 * @param referenced the activity that the new Call Behavior element should invoke
 	 * @return the Call Behavior element that was created
 	 */
	public IRPState addReferenceActivity(IRPModelElement referenced);
	/**
 	 * Adds a new swimlane to the activity.
 	 * @param name the name to use for the new swimlane
 	 * @return the swimlane that was created
 	 */
	public IRPSwimlane addSwimlane(String name);
	/**
 	 * Returns the IRPActivityDiagram object associated with the activity.
 	 * @return the IRPActivityDiagram object associated with the activity
 	 */
	public IRPActivityDiagram getFlowchartDiagram();
	/**
 	 * Checks whether the activity is defined as analysis-only, meaning that it is used only for modeling purposes and code is not generated for the activity.
 	 * @return 1 if the activity is defined as analysis-only, 0 otherwise
 	 */
	public int getIsAnalysisOnly();
	/**
 	 * @deprecated Use IRPModelElement.getOwner instead.
 	 */
	public IRPOperation getItsOwner();
	/**
 	 * Returns a collection of all the swimlanes in the activity.
 	 * @return collection of IRPSwimlane objects
 	 */
	public IRPCollection getSwimlanes();
	/**
 	 * Specifies whether the activity should be defined as analysis-only.
 	 * @param isAnalysisOnly Use 1 to specify that the activity should be defined as analysis-only. Use 0 to specify that the activity should not be defined as analysis-only.
 	 */
	public void setIsAnalysisOnly(int isAnalysisOnly);
	/**
 	 * @deprecated Use IRPModelElement.setOwner instead.
 	 */
	public void setItsOwner(IRPOperation itsOwner);


}

