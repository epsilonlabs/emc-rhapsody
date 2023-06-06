
//Licensed Materials - Property of IBM 
//(c) Copyright IBM Corporation 2006, 2018. All Rights Reserved.

package com.telelogic.rhapsody.core;


/**
 * The IRPSelection interface contains methods for cutting, copying, pasting, and deleting graphic elements on diagrams. 
 */
public interface IRPSelection {
	/**
 	 * Checks whether the current selection can be copied.
 	 * @return 1 if the current selection can be copied, 0 otherwise
 	 * @throws RhapsodyRuntimeException
 	 */
	public int canCopy();
	/**
 	 * Checks whether the current selection can be cut.
 	 * @return 1 if the current selection can be cut, 0 otherwise
 	 * @throws RhapsodyRuntimeException
 	 */
	public int canCut();
	/**
 	 * Checks whether the current selection can be deleted.
 	 * @return 1 if the current selection can be deleted, 0 otherwise
 	 * @throws RhapsodyRuntimeException
 	 */
	public int canDelete();
	/**
 	 * Checks whether the item in the clipboard can be pasted to the diagram that has the focus.
 	 * @return 1 if the item in the clipboard can be pasted to the diagram that has the focus, 0 otherwise
 	 * @throws RhapsodyRuntimeException
 	 */
	public int canPaste();
	/**
 	 * Copies the currently selected graphic element.
 	 * @return 1 if the copy operation was successful, 0 otherwise
 	 * @throws RhapsodyRuntimeException
 	 * <pre>
 	 * {@code
 	 * // code sample for copying and pasting graphic element on diagram
 	 * IRPApplication app = RhapsodyAppServer.getActiveRhapsodyApplication();
 	 * IRPProject activeProject = app.activeProject();
 	 * IRPObjectModelDiagram sourceDiagram = activeProject.addObjectModelDiagram("sourceDiagram");
 	 * IRPObjectModelDiagram targetDiagram = activeProject.addObjectModelDiagram("targetDiagram");
 	 * 
 	 * IRPPackage sourcePackage = activeProject.addPackage("SourcePackage");
 	 * IRPClass classToCopy = sourcePackage.addClass("ClassToCopy");
 	 * 
 	 * sourceDiagram.openDiagram();
 	 * IRPGraphNode nodeForClassToCopy = sourceDiagram.addNewNodeForElement(classToCopy, 30, 30, 200, 200);
 	 * activeProject.save();
 	 * 
 	 * IRPCollection elementsToSelect = app.createNewCollection();
 	 * elementsToSelect.addGraphicalItem(nodeForClassToCopy);
 	 * app.selectGraphElements(elementsToSelect);		
 	 * IRPSelection selectedItem = app.getSelection();
 	 * 
 	 * app.clearOutputWindow("Log");
 	 * 
 	 * if (selectedItem.canCopy()==1) {
 	 * 	app.writeToOutputWindow("Log", "can be copied\n");
 	 * 	selectedItem.copySelected();
 	 * }
 	 * 	
 	 * targetDiagram.openDiagram();
 	 * 		
 	 * if (selectedItem.canPaste()==1) {
 	 * 	app.writeToOutputWindow("Log", "can be pasted to diagram with focus\n");
 	 * 	selectedItem.pasteSelected();
 	 * }
 	 * }
 	 * </pre>
 	 */
	public int copySelected();
	/**
 	 * Cuts the currently selected graphic element.
 	 * @return 1 if the cut operation was successful, 0 otherwise
 	 * @throws RhapsodyRuntimeException
 	 */
	public int cutSelected();
	/**
 	 * Deletes the currently selected graphic element.
 	 * @return 1 if the delete operation was successful, 0 otherwise
 	 * @throws RhapsodyRuntimeException
 	 */
	public int deleteSelected();
	/**
 	 * Returns the name of the API interface corresponding to the object it is called on, for example, IRPClass for a class element, IRPOperation for an operation element.
 	 * @return the name of the API interface corresponding to the object it is called on
 	 * @throws RhapsodyRuntimeException
 	 */
	public String getInterfaceName();
	/**
 	 * Pastes the item in the clipboard to the diagram that has the focus.
 	 * @return 1 if the paste operation was successful, 0 otherwise
 	 * @throws RhapsodyRuntimeException
 	 */
	public int pasteSelected();


}

