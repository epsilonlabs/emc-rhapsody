
//Licensed Materials - Property of IBM 
//(c) Copyright IBM Corporation 2006, 2018. All Rights Reserved.

package com.telelogic.rhapsody.core;


 /**
  * The IRPStatechartDiagram interface represents statecharts in a Rhapsody model.
  */
public interface IRPStatechartDiagram  extends IRPDiagram {
	/**
 	 * Adds an And Line to the specified state.
 	 * @param sourceState the graphical element representing the state to which the And Line should be added
 	 * @param xStartPosition the x position at which the And Line should begin
 	 * @param yStartPosition the y position at which the And Line should begin
 	 * @param xEndPosition the x position at which the And Line should end
 	 * @param yEndPosition the y position at which the And Line should end
 	 * @return a collection of the new orthogonal states created
 	 * <pre>
 	 * {@code
 	 *		IRPApplication app = RhapsodyAppServer.getActiveRhapsodyApplication();
 	 * 	IRPProject prj = app.activeProject();
 	 * 	IRPPackage vehiclePackage = prj.addPackage("Vehicles");
 	 * 	IRPClass carClass = vehiclePackage.addClass("Car");
 	 * 	IRPStatechart carStatechart = carClass.addStatechart(); 
 	 * 	IRPState rootState = carStatechart.getRootState();
 	 * 	IRPState runningState = rootState.addState("Running");
 	 * 	IRPStatechartDiagram scDiagram = carStatechart.getStatechartDiagram();
 	 * 	IRPGraphNode runningStateNode = scDiagram.addNewNodeForElement(runningState, 100, 100, 400, 400);
 	 * 	IRPCollection stateNodesCreated = scDiagram.addAndLine(runningStateNode, 300, 100, 300, 500);
 	 * 	IRPGraphNode newStateNodeCreated = null;
 	 * 	for (int stateNodeCounter = 1; stateNodeCounter < stateNodesCreated.getCount()+1;stateNodeCounter++ ) {
 	 * 		newStateNodeCreated = (IRPGraphNode)stateNodesCreated.getItem(stateNodeCounter);
 	 * 		System.out.println(newStateNodeCreated.getModelObject().getName());
 	 * 	} 
 	 * }
 	 * </pre>
 	 */
	public IRPCollection addAndLine(IRPGraphNode sourceState, int xStartPosition, int yStartPosition, int xEndPosition, int yEndPosition);
	/**
 	 * Creates the graphical representation of the elements in the statechart. When you create a statechart with the API, the graphical representation is not created by default. This means that the first time you open the statechart in Rhapsody, you will be asked if the graphics should be created. You can create the graphical representation directly by calling createGraphics().
 	 */
	public void createGraphics();
	/**
 	 * Returns the IRPStatechart object underlying the statechart.
 	 * @return the IRPStatechart object underlying the statechart
 	 */
	public IRPStatechart getStatechart();


}

