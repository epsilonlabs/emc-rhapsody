
//Licensed Materials - Property of IBM 
//(c) Copyright IBM Corporation 2006, 2018. All Rights Reserved.

package com.telelogic.rhapsody.core;


/**
 * The IRPStatechart interface represents the statechart elements underlying a statechart. The statechart itself is represented by the IRPStatechartDiagram interface. You can create an IRPStatechart object with the method IRPClass.addStatechart(). 
 */
public interface IRPStatechart  extends IRPClass {
	/**
 	 * Adds a free shape of the type specified, using the x coordinates and y coordinates provided.
 	 * @param metaType the type of shape to add. The possible values for this parameter are: "Polyline", "Polygon", "Rectangle", "Polycurve", "Closed Curve", "Ellipse".
 	 * @param xPoints collection of integers representing the x coordinates for the shape
 	 * @param yPoints collection of integers representing the y coordinates for the shape
 	 * @return the new shape that was created
 	 */
	public IRPGraphElement addFreeShapeByType(String metaType, IRPCollection xPoints, IRPCollection yPoints);
	/**
 	 * Adds an image to the statechart, using the specified file, starting point, width, and height.
 	 * @param filename the full path to the image
 	 * @param xPosition the x coordinate for the top left corner of the image, in pixels
 	 * @param yPosition the y coordinate for the top left corner of the image, in pixels
 	 * @param nWidth the width of the image, in pixels
 	 * @param nHeight the height of the image, in pixels
 	 * @return the new image element that was created
 	 */
	public IRPGraphElement addImage(String filename, int xPosition, int yPosition, int nWidth, int nHeight);
	/**
 	 * Adds a connector element of the specified type to the statechart, using the source and target elements specified. Note that this method can only be used for connector elements that only have graphical representations and are not actual elements in the model. "Ordinary" connector elements are added to a statechart by carrying out two steps: 1) adding the new element to your model 2) adding a graphical representation of the element to the statechart using the method IRPStatechart.addNewEdgeForElement.
 	 * @param metaType the type of connector element to add to the statechart. The strings that can be used for this parameter are: "anchor", "compRealization", "Containment Arrow", and "communication path".
 	 * @param src the graphical element that is the source for the connector
 	 * @param xSrcPosition the distance, in pixels, from the the left edge of the diagram to a point within the source graphical element
 	 * @param ySrcPosition the distance, in pixels, from the the top edge of the diagram to a point within the source graphical element
 	 * @param trg the graphical element that is the target for the connector
 	 * @param xTrgPosition the distance, in pixels, from the the left edge of the diagram to a point within the target graphical element
 	 * @param yTrgPosition the distance, in pixels, from the the top edge of the diagram to a point within the target graphical element
 	 * @return  the graphical element that was added to the statechart
 	 * @see IRPStatechart#addNewEdgeForElement
 	 */
	public IRPGraphEdge addNewEdgeByType(String metaType, IRPGraphElement src, int xSrcPosition, int ySrcPosition, IRPGraphElement trg, int xTrgPosition, int yTrgPosition);
	/**
 	 * Adds a connector graphical element to the statechart to represent the specified model element.
 	 * @param element the model element to add to the statechart.
 	 * @param src the graphical element that is the source for the connector
 	 * @param xSrcPosition the distance, in pixels, from the the left edge of the diagram to a point within the source graphical element 
 	 * @param ySrcPosition the distance, in pixels, from the the top edge of the diagram to a point within the source graphical element 
 	 * @param trg the graphical element that is the target for the connector
 	 * @param xTrgPosition the distance, in pixels, from the the left edge of the diagram to a point within the target graphical element 
 	 * @param yTrgPosition the distance, in pixels, from the the top edge of the diagram to a point within the target graphical element 
 	 * @return the connector graphical element that was added to the statechart
 	 */
	public IRPGraphEdge addNewEdgeForElement(IRPModelElement element, IRPGraphNode src, int xSrcPosition, int ySrcPosition, IRPGraphNode trg, int xTrgPosition, int yTrgPosition);
	/**
 	 * Adds a statechart element of the specified type to the statechart, using the position and dimensions specified. Note that this method can only be used for statechart elements that only have graphical representations and are not actual elements in the model. "Ordinary" model elements are added to a statechart by carrying out two steps: 1) adding the new element to your model 2) adding a graphical representation of the element to the statechart using the method IRPStatechart.addNewNodeForElement.
 	 * @param metaType the type of element to add to the diagram. The strings that can be used for this parameter are: "OrState"(for And Line), "Note"; panel diagram elements: "Knob", "Gauge", "Meter", "LevelIndicator", "MatrixDisplay", "DigitalDisplay", "Led", "OnOffSwitch", "PushButton", "ButtonArray", "TextBox", "Slider"; free shapes: "Polyline", "Ploygon", "Rectangle", "Polycurve", "Closed Curve","Ellipse","Image".
 	 * @param xPosition the position of the left edge of the graphical object, in pixels, relative to the left edge of the diagram
 	 * @param yPosition the position of the top edge of the graphical object, in pixels, relative to the top edge of the diagram
 	 * @param nWidth the width of the graphical object
 	 * @param nHeight the height of the graphical object
 	 * @return the graphical element that was added to the statechart
 	 * @see IRPStatechart#addNewNodeForElement
 	 */
	public IRPGraphNode addNewNodeByType(String metaType, int xPosition, int yPosition, int nWidth, int nHeight);
	/**
 	 * Adds a graphical element to the statechart to represent the specified model element. For connector elements, use the method addNewEdgeForElement.
 	 * @param element the model element to add to the statechart.
 	 * @param xPosition the position of the left edge of the graphical object, in pixels, relative to the left edge of the diagram
 	 * @param yPosition the position of the top edge of the graphical object, in pixels, relative to the top edge of the diagram
 	 * @param nWidth the width of the graphical object
 	 * @param nHeight the height of the graphical object
 	 * @return the graphical element that was added to the statechart
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
 	 * Used internally by Rhapsody to display diagrams within Eclipse (when using the Rhapsody-Eclipse platform integration).
 	 */
	public IRPAXViewCtrl openDiagramView();
	/**
 	 * Adds a new Accept Event Action element to the statechart.
 	 * @param name the name to use for the new Accept Event Action element
 	 * @param parent the diagram element to which the new Accept Event Action element should be added. If the element is being added to the main canvas of the diagram, this should be the root state of the diagram (which is obtained by calling IRPStatechart.getRootState()).
 	 * @return the Accept Event Action element that was created
 	 * @throws RhapsodyRuntimeException
 	 */
	public IRPAcceptEventAction addNewAcceptEventAction(String name, IRPState parent);
	/**
 	 * Adds a new Accept Time Event element to the statechart.
 	 * @param name the name to use for the new Accept Time Event element
 	 * @param parent the diagram element to which the new Accept Time Event element should be added.  If the element is being added to the main canvas of the diagram, this should be the root state of the diagram (which is obtained by calling IRPStatechart.getRootState()).
 	 * @return the Accept Time Event element that was created
 	 * @throws RhapsodyRuntimeException
 	 */
	public IRPAcceptTimeEvent addNewAcceptTimeEvent(String name, IRPState parent);
	/**
 	 * Closes the statechart.
 	 */
	public void closeDiagram();
	/**
 	 * Creates the graphical representation of the elements in the statechart. When you create a statechart with the API, the graphical representation is not created by default. This means that the first time you open the statechart in Rhapsody, you will be asked if the graphics should be created. You can create the graphical representation directly by calling createGraphics().
 	 */
	public void createGraphics();
	/**
 	 * Deletes the specified state from the statechart.
 	 * @param state the state to delete
 	 */
	public void deleteState(IRPState state);
	/**
 	 * Checks whether the specified IRPInterfaceItem element serves as the trigger of a transition in the statechart.
 	 * @param Item the IRPInterfaceItem element to check
 	 * @return 1 if the specified element serves as the trigger of a transition in the statechart, 0 otherwise
 	 */
	public int findTrigger(IRPInterfaceItem Item);
	/**
 	 * Returns a collection of all the triggers in the statechart
 	 * @return all of the triggers in the statechart
 	 */
	public IRPCollection getAllTriggers();
	/**
 	 * Returns a collection of all of the elements in the statechart.
 	 * @return all of the elements in the statechart
 	 */
	public IRPCollection getElementsInDiagram();
	/**
 	 * Returns a collection of all the graphical elements in the statechart.
 	 * @return collection of IRPGraphElement objects, representing all the graphical elements in the statechart.
 	 */
	public IRPCollection getGraphicalElements();
	/**
 	 * Returns the statechart of the base class of this class. 
 	 * @return the statechart of the base class of this class
 	 */
	public IRPStatechart getInheritsFrom();
	/**
 	 * Checks whether the statechart is the main behavior for the class. Rhapsody allows you to define multiple statecharts and activities. One of these is defined as the "main" behavior, which is executed and can then reference other statecharts and activities. 
 	 * @return 1 if the statechart is the main behavior, 0 otherwise
 	 */
	public int getIsMainBehavior();
	/**
 	 * Checks whether the inheritance relationship between this statechart and the statechart of the base class was overridden.
 	 * @return 1 if the inheritance relationship between the statecharts was overridden, 0 if the relationship still exists.
 	 */
	public int getIsOverridden();
	/**
 	 * Returns the class that the statechart is associated with.
 	 * @return the class that the statechart is associated with
 	 */
	public IRPClassifier getItsClass();
	/**
 	 * Saves the statechart as an emf format file, using the path and filename provided as a parameter.
 	 * @param filename the full path to use for saving the file
 	 */
	public void getPicture(String filename);
	/**
 	 * Saves the statechart in the specified graphic format, breaking the diagram into a number of files if necessary. The need to break the diagram into a number of files is based on the value of the property General:Graphics:ExportedDiagramScale. If the property is set to a value other than FitToOnePage, more than one file will be created. In addition, this method can be used to retrieve diagram element information that can be used to create an HTML image map.
 	 * @param firstFileName the name to use for the file created. If more than one file is created, the filenames used will be based on the following convention: firstFileNameZ_X_Y, where Z is the number of the created file, X is the number of the page along the X vector, and Y is the number of the page along the Y vector.
 	 * @param imageFormat the graphic format in which the diagram should be saved. This can be one of the following: EMF, BMP, JPEG, JPG, TIFF.
 	 * @param getImageMaps use this argument to indicate whether the method should also provide a collection of IRPImageMap objects that can be used to construct an HTML image map for the diagram. (Use 1 if you want this information, else use 0.)
 	 * @param diagrammap The collection to use to store the IRPImageMap objects containing the required information for constructing an HTML image map
 	 * @return collection that contains the names of the files that were created
 	 */
	public IRPCollection getPictureAs(String firstFileName, String imageFormat, int getImageMaps, IRPCollection diagrammap);
	/**
 	 * Saves the statechart as an emf format file, breaking the diagram into a number of such files if necessary. The need to break the diagram into a number of files is based on the value of the property General:Graphics:ExportedDiagramScale. If the property is set to a value other than FitToOnePage, more than one file will be created.
 	 * @param firstFileName the name to use for the first file created. If more than one file is created, the filenames used will be based on the following convention: firstFileNameZ_X_Y, where Z is the number of the created file, X is the number of the page along the X vector, and Y is the number of the page along the Y vector.
 	 * @return collection that contains the names of the files that were created
 	 */
	public IRPCollection getPictureAsDividedMetafiles(String firstFileName);
	/**
 	 * Saves the statechart as an emf format file, breaking the diagram into a number of files if necessary. The need to break the diagram into a number of files is based on the value of the property General:Graphics:ExportedDiagramScale. If the property is set to a value other than FitToOnePage, more than one file will be created. In addition, this method retrieves diagram element information that can be used to create an HTML image map.
 	 * @param firstFileName the name to use for the file created. If more than one file is created, the filenames used will be based on the following convention: firstFileNameZ_X_Y, where Z is the number of the created file, X is the number of the page along the X vector, and Y is the number of the page along the Y vector.
 	 * @param diagrammap The collection to use to store the IRPImageMap objects containing the required information for constructing an HTML image map
 	 * @return collection that contains the names of the files that were created
 	 */
	public IRPCollection getPicturesWithImageMap(String firstFileName, IRPCollection diagrammap);
	/**
 	 * Returns the root state of the statechart. To create a top-level state in a statechart, you add it to the root state.
 	 * @return the root state of the statechart
 	 */
	public IRPState getRootState();
	/**
 	 * Returns the IRPStatechartDiagram object associated with the statechart.
 	 * @return the IRPStatechartDiagram object associated with the statechart
 	 */
	public IRPStatechartDiagram getStatechartDiagram();
	/**
 	 * Breaks the inheritance relationship between this statechart and the statechart of the base class.
 	 */
	public void overrideInheritance();
	/**
 	 * Populates the statechart with the elements and types of relations specified.
 	 * @param elementsToPopulate the elements (nodes) to add to the diagram
 	 * @param relationsTypes the types of relations that should be drawn on the diagram. You can use the string AllRelations to display all types, or use any combination of the following strings: Composition, Association, Link, Dependency, Inheritance, Anchor, InformationFlow   
 	 * @param createContent the elements that should be included in addition to those specified. This argument can take any of the following strings: among, from, to, fromto. If you use "among", only the elements you specified will be included. If you use one of the other strings, the diagram will also include elements that the selected elements are related to
 	*/
	public void populateDiagram(IRPCollection elementsToPopulate, IRPCollection relationsTypes, String createContent);
	/**
 	 * Specifies that this statechart should be the main behavior for the class. Rhapsody allows you to define multiple statecharts and activities. One of these is defined as the "main" behavior, which is executed and can then reference other statecharts and activities. 
 	 */
	public void setAsMainBehavior();
	/**
 	 * Shows/hides the diagram frame.
 	 * @param bShow use 1 to show the diagram frame, 0 to hide the frame.
 	 */
	public void setShowDiagramFrame(int bShow);
	/**
 	 * Restores the inheritance relationship between this statechart and the statechart of the base class.
 	 */
	public void unoverrideInheritance();


}

