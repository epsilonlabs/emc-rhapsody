
//Licensed Materials - Property of IBM 
//(c) Copyright IBM Corporation 2006, 2018. All Rights Reserved.

package com.telelogic.rhapsody.core;


 /**
  * The IRPDiagram interface contains the methods shared by all the interfaces that represent specific types of diagrams. 
  */
public interface IRPDiagram  extends IRPUnit {
	/**
 	 * Adds a free shape of the type specified, using the x coordinates and y coordinates provided.
 	 * @param metaType the type of shape to add. The possible values for this parameter are: "Polyline", "Polygon", "Rectangle", "Polycurve", "Closed Curve", "Ellipse".
 	 * @param xPoints collection of integers representing the x coordinates for the shape
 	 * @param yPoints collection of integers representing the y coordinates for the shape
 	 * @return the new shape that was created
 	 */
	public IRPGraphElement addFreeShapeByType(String metaType, IRPCollection xPoints, IRPCollection yPoints);
	/**
 	 * Adds an image to the diagram, using the specified file, starting point, width, and height.
 	 * @param filename the full path to the image
 	 * @param xPosition the x coordinate for the top left corner of the image, in pixels
 	 * @param yPosition the y coordinate for the top left corner of the image, in pixels
 	 * @param nWidth the width of the image, in pixels
 	 * @param nHeight the height of the image, in pixels
 	 * @return the new image element that was created
 	 */
	public IRPGraphElement addImage(String filename, int xPosition, int yPosition, int nWidth, int nHeight);
	/**
 	 * Adds a connector element of the specified type to the diagram, using the source and target elements specified. Note that this method can only be used for connector elements that only have graphical representations and are not actual elements in the model. "Ordinary" connector elements are added to a diagram by carrying out two steps: 1) adding the new element to your model 2) adding a graphical representation of the element to the diagram using the method IRPDiagram.addNewEdgeForElement.
 	 * @param metaType the type of connector element to add to the diagram. The strings that can be used for this parameter are: "anchor", "compRealization", "Containment Arrow", and "communication path".
 	 * @param src the graphical element that is the source for the connector
 	 * @param xSrcPosition the distance, in pixels, from the the left edge of the diagram to a point within the source graphical element
 	 * @param ySrcPosition the distance, in pixels, from the the top edge of the diagram to a point within the source graphical element
 	 * @param trg the graphical element that is the target for the connector
 	 * @param xTrgPosition the distance, in pixels, from the the left edge of the diagram to a point within the target graphical element
 	 * @param yTrgPosition the distance, in pixels, from the the top edge of the diagram to a point within the target graphical element
 	 * @return  the graphical element that was added to the diagram
 	 * @see IRPDiagram#addNewEdgeForElement
 	 */
	public IRPGraphEdge addNewEdgeByType(String metaType, IRPGraphElement src, int xSrcPosition, int ySrcPosition, IRPGraphElement trg, int xTrgPosition, int yTrgPosition);
	/**
 	 * Adds a connector graphical element to the diagram to represent the specified model element.
 	 * @param element the model element to add to the diagram.
 	 * @param src the graphical element that is the source for the connector
 	 * @param xSrcPosition the distance, in pixels, from the the left edge of the diagram to a point within the source graphical element 
 	 * @param ySrcPosition the distance, in pixels, from the the top edge of the diagram to a point within the source graphical element 
 	 * @param trg the graphical element that is the target for the connector
 	 * @param xTrgPosition the distance, in pixels, from the the left edge of the diagram to a point within the target graphical element 
 	 * @param yTrgPosition the distance, in pixels, from the the top edge of the diagram to a point within the target graphical element 
 	 * @return the connector graphical element that was added to the diagram
 	 */
	public IRPGraphEdge addNewEdgeForElement(IRPModelElement element, IRPGraphNode src, int xSrcPosition, int ySrcPosition, IRPGraphNode trg, int xTrgPosition, int yTrgPosition);
	/**
 	 * Adds a diagram element of the specified type to the diagram, using the position and dimensions specified. Note that this method can only be used for diagram elements that only have graphical representations and are not actual elements in the model. "Ordinary" model elements are added to a diagram by carrying out two steps: 1) adding the new element to your model 2) adding a graphical representation of the element to the diagram using the method IRPDiagram.addNewNodeForElement.
 	 * @param metaType the type of element to add to the diagram. The strings that can be used for this parameter are: "OrState"(for And Line), "Interaction Operand", "Swimlane" (for swimlane divider), "System Border", "PartitionLine", "SDActionBlock" (for action block in sequence diagram), "Note"; panel diagram elements: "Knob", "Gauge", "Meter", "LevelIndicator", "MatrixDisplay", "DigitalDisplay", "Led", "OnOffSwitch", "PushButton", "ButtonArray", "TextBox", "Slider"; free shapes: "Polyline", "Ploygon", "Rectangle", "Polycurve", "Closed Curve","Ellipse","Image".
 	 * @param xPosition the position of the left edge of the graphical object, in pixels, relative to the left edge of the diagram
 	 * @param yPosition the position of the top edge of the graphical object, in pixels, relative to the top edge of the diagram
 	 * @param nWidth the width of the graphical object
 	 * @param nHeight the height of the graphical object
 	 * @return the graphical element that was added to the diagram
 	 * @see IRPDiagram#addNewNodeForElement
 	 */
	public IRPGraphNode addNewNodeByType(String metaType, int xPosition, int yPosition, int nWidth, int nHeight);
	/**
 	 * Adds a graphical element to the diagram to represent the specified model element. For connector elements, use the method addNewEdgeForElement.
 	 * @param element the model element to add to the diagram.
 	 * @param xPosition the position of the left edge of the graphical object, in pixels, relative to the left edge of the diagram
 	 * @param yPosition the position of the top edge of the graphical object, in pixels, relative to the top edge of the diagram
 	 * @param nWidth the width of the graphical object
 	 * @param nHeight the height of the graphical object
 	 * @return the graphical element that was added to the diagram
 	 */
	public IRPGraphNode addNewNodeForElement(IRPModelElement element, int xPosition, int yPosition, int nWidth, int nHeight);
	/**
 	 * Adds a text box using the specified text, starting point, width, and height.
 	 * @param text the text that should be displayed
 	 * @param xPosition the x coordinate for the top left corner of the box, in pixels
 	 * @param yPosition the y coordinate for the top left corner of the box, in pixels
 	 * @param nWidth the width of the text box, in pixels
 	 * @param nHeight the height of the text box, in pixels
 	 * @return the new text box that was created
 	 */
	public IRPGraphElement addTextBox(String text, int xPosition, int yPosition, int nWidth, int nHeight);
	/**
 	 * Creates a diagram view based on this diagram.
 	 * @param owner the element that will be the owner of the diagram view
 	 * @param customViews collection of the custom views that should be applied to the new diagram view (custom views are IRPPackage objects)
 	 * @return the diagram view that was created
 	 * @throws RhapsodyRuntimeException
 	 */
	public IRPDiagram createDiagramView(IRPModelElement owner, IRPCollection customViews);
	/**
 	 * Gets the custom views that were applied to this diagram view.
 	 * @return the custom views that were applied to this diagram view
 	 * @throws RhapsodyRuntimeException
 	 */
	public IRPCollection getCustomViews();
	/**
 	 * For diagram views, gets the diagram on which the diagram view is based.
 	 * @return the diagram on which this diagram view is based
 	 * @throws RhapsodyRuntimeException
 	 */
	public IRPDiagram getDiagramViewOf();
	/**
 	 * Gets the diagram views that are based on this diagram.
 	 * @return the diagram views that are based on this diagram
 	 * @throws RhapsodyRuntimeException
 	 */
	public IRPCollection getDiagramViews();
	/**
 	 * Checks whether the diagram is a diagram view
 	 * @return 1 if the diagram is a diagram view, 0 otherwise
 	 * @throws RhapsodyRuntimeException
 	 */
	public int isDiagramView();
	/**
 	 * Used internally by Rhapsody to display diagrams within Eclipse (when using the Rhapsody-Eclipse platform integration).
 	 */
	public IRPAXViewCtrl openDiagramView();
	/**
 	 * Improves the graphic layout of ports on each of the specified graphic elements. Corresponds to the Rearrange Ports option in the GUI.
 	 * @param pGraphNodes the graphic elements whose ports should be rearranged
 	 * @throws RhapsodyRuntimeException
 	 */
	public void rearrangePorts(IRPCollection pGraphNodes);
	/**
 	 * Specifies which custom views should be applied to this diagram view.
 	 * @param customViews collection of custom views that should be applied to this diagram view
 	 * @throws RhapsodyRuntimeException
 	 */
	public void setCustomViews(IRPCollection customViews);
	/**
 	 * Updates the view for the diagram on the Rhapsody Model Manager server.
 	 * @param enforceUpdate Use 0 to specify that the view should be updated only if changes that affect the diagram were made since the last update. Use 1 to specify that the view should be updated regardless of whether or not changes that affect the diagram were made since the last update.
 	 * @return 1 if the view for the diagram was updated on the server. If the diagram does not require an update, 0 is returned. If the update attempt failed, -1 is returned.
 	 * @throws RhapsodyRuntimeException
 	 */
	public int updateViewOnServer(int enforceUpdate);
	/**
 	 * Closes the diagram.
 	 */
	public void closeDiagram();
	/**
 	 * Adds connectors to the diagram to reflect the existing relations between the specified elements.
 	 * @param graphElements the elements whose relations should be reflected on the diagram
 	 * @param selectedToAll Use 0 if you just want to display the relations between the specified elements. Use 1 if you would also like to display any existing relations between the specified elements and other elements on the diagram.
 	 */
	public void completeRelations(IRPCollection graphElements, int selectedToAll);
	/**
 	 * Returns the graphical elements that represent the specified model element in the diagram. In cases where the same model element appears multiple times in a single diagram, the collection returned will contain more than one graphical element.
 	 * @param modelElement the model element in the diagram whose graphical elements should be returned 
 	 * @return the graphical elements that represent the specified model element in the diagram
 	 */
	public IRPCollection getCorrespondingGraphicElements(IRPModelElement modelElement);
	/**
 	 * Returns a collection of all the model elements in the diagram.
 	 * @return collection of all the model elements in the diagram
 	 */
	public IRPCollection getElementsInDiagram();
	/**
 	 * Returns a collection of all the graphical elements in the diagram.
 	 * @return collection of IRPGraphElement objects, representing all the graphical elements in the diagram.
 	 */
	public IRPCollection getGraphicalElements();
	/**
 	 * Returns the time at which the visual representation of the diagram was last changed. This takes into account not only the information stored in the diagram element itself, but also information from other elements that is reflected on the diagram, for example, changes to an attribute of a class that is included in the diagram.
 	 * @return the time at which the visual representation of the diagram was last changed
 	 */
	public String getLastVisualizationModifiedTime();
	/**
 	 * Saves the diagram as an emf format file, using the path and filename provided as a parameter.
 	 * @param filename the full path to use for saving the file
 	 */
	public void getPicture(String filename);
	/**
 	 * Saves the diagram in the specified graphic format, breaking the diagram into a number of files if necessary. The need to break the diagram into a number of files is based on the value of the property General:Graphics:ExportedDiagramScale. If the property is set to a value other than FitToOnePage, more than one file will be created. In addition, this method can be used to retrieve diagram element information that can be used to create an HTML image map.
 	 * @param firstFileName the name to use for the file created. If more than one file is created, the filenames used will be based on the following convention: firstFileNameZ_X_Y, where Z is the number of the created file, X is the number of the page along the X vector, and Y is the number of the page along the Y vector.
 	 * @param imageFormat the graphic format in which the diagram should be saved. This can be one of the following: EMF, BMP, JPEG, JPG, TIFF.
 	 * @param getImageMaps use this argument to indicate whether the method should also provide a collection of IRPImageMap objects that can be used to construct an HTML image map for the diagram. (Use 1 if you want this information, else use 0.)
 	 * @param diagrammap The collection to use to store the IRPImageMap objects containing the required information for constructing an HTML image map
 	 * @return collection that contains the names of the files that were created
 	 */
	public IRPCollection getPictureAs(String firstFileName, String imageFormat, int getImageMaps, IRPCollection diagrammap);
	/**
 	 * Saves the diagram as an emf format file, breaking the diagram into a number of such files if necessary. The need to break the diagram into a number of files is based on the value of the property General:Graphics:ExportedDiagramScale. If the property is set to a value other than FitToOnePage, more than one file will be created.
 	 * @param firstFileName the name to use for the first file created. If more than one file is created, the filenames used will be based on the following convention: firstFileNameZ_X_Y, where Z is the number of the created file, X is the number of the page along the X vector, and Y is the number of the page along the Y vector.
 	 * @return collection that contains the names of the files that were created
 	 */
	public IRPCollection getPictureAsDividedMetafiles(String firstFileName);
	/**
	 * method getPictureEx
	 * @throws RhapsodyRuntimeException
	 */
	public void getPictureEx(String filename, String exportScale, int smartZoom);
	/**
 	 * Saves the diagram as an emf format file, breaking the diagram into a number of files if necessary. The need to break the diagram into a number of files is based on the value of the property General:Graphics:ExportedDiagramScale. If the property is set to a value other than FitToOnePage, more than one file will be created. In addition, this method retrieves diagram element information that can be used to create an HTML image map.
 	 * @param firstFileName the name to use for the file created. If more than one file is created, the filenames used will be based on the following convention: firstFileNameZ_X_Y, where Z is the number of the created file, X is the number of the page along the X vector, and Y is the number of the page along the Y vector.
 	 * @param diagrammap The collection to use to store the IRPImageMap objects containing the required information for constructing an HTML image map
 	 * @return collection that contains the names of the files that were created
 	 */
	public IRPCollection getPicturesWithImageMap(String firstFileName, IRPCollection diagrammap);
	/**
	 * method isOpen
	 * @throws RhapsodyRuntimeException
	 */
	public int isOpen();
	/**
 	 * Checks whether the diagram frame is currently visible.
 	 * @return 1 if the diagram frame is currently visible, 0 if it is not visible
 	 */
	public int isShowDiagramFrame();
	/**
 	 * Opens the diagram.
 	 */
	public void openDiagram();
	/**
 	 * Populates the diagram with the elements and types of relations specified.
 	 * @param elementsToPopulate the elements (nodes) to add to the diagram
 	 * @param relationsTypes the types of relations that should be drawn on the diagram. You can use the string AllRelations to display all types, or use any combination of the following strings: Composition, Association, Link, Dependency, Inheritance, Anchor, InformationFlow   
 	 * @param createContent the elements that should be included in addition to those specified. This argument can take any of the following strings: among, from, to, fromto. If you use "among", only the elements you specified will be included. If you use one of the other strings, the diagram will also include elements that the selected elements are related to
 	* <pre>
 	* {@code
 	* IRPApplication app = RhapsodyAppServer.getActiveRhapsodyApplication();
 	* IRPProject project = app.activeProject();
 	* IRPCollection packages = project.getPackages();
 	* IRPCollection relTypes = app.createNewCollection();
 	* relTypes.setSize(3);
 	* relTypes.setString(1, "Composition");
 	* relTypes.setString(2, "Association");
 	* relTypes.setString(3, "Dependency");
 	* IRPObjectModelDiagram diagram2 = project.addObjectModelDiagram("PopulateTest1");
 	* diagram2.populateDiagram(packages, relTypes, "fromto"); 
 	* }
 	* </pre>
 	*/
	public void populateDiagram(IRPCollection elementsToPopulate, IRPCollection relationsTypes, String createContent);
	/**
 	 * Removes the specified graphic elements from the diagram.
 	 * @param elementsToRemove a collection of IRPGraphElement objects, representing the graphic elements that should be removed from the diagram
 	 */
	public void removeGraphElements(IRPCollection elementsToRemove);
	/**
 	 * Shows/hides the diagram frame.
 	 * @param bShow use 1 to show the diagram frame, 0 to hide the frame.
 	 */
	public void setShowDiagramFrame(int bShow);


}

