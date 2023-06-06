//Licensed Materials - Property of IBM 
//© Copyright IBM Corporation 2006, 2008. All Rights Reserved.

package com.telelogic.rhapsody.core;

import java.util.List;

 /**
  * The IRPCollection interface contains methods used to store and manipulate collections of various types of elements that you may have in your Rational Rhapsody model. Collections of this type are used by methods that return multiple model elements and by certain methods that take a collection of model elements as an argument.
  */
public interface IRPCollection {
	/**
	 * Returns the number of items in a collection.
	 * @return the number of items in the collection
	 */
	public int getCount();

	/**
	 * Retrieves an item from a collection, using the index specified. Note that when using the getItem method, the index parameter is based on an index value of 1 for the first element (not 0).
	 * @param index the index of the item to be retrieved (index of first element is 1, not 0)
	 * @return the item with the index specified
	 * @throws RhapsodyRuntimeException
	 */	
	public Object getItem(int index);
	
	/**
	 * Adds a model element to a collection. This method adds items, one at a time, to the end of a collection. When adding multiple items, it may be more efficient to call setSize to set the new size of the collection and then call setModelElement to place elements in specific locations in the collection. 
	 * @param newVal the model element to add to the collection
	 * @throws RhapsodyRuntimeException
	 */
	public void addItem(IRPModelElement newVal);
		
	/**
	 * Adds a graphical element to a collection.
	 * @param newVal the graphical element to add to the collection
	 * @throws RhapsodyRuntimeException
	 */
	public void addGraphicalItem(IRPGraphElement newVal);

	/**
	 * Returns a java.util.List populated with the elements in the collection.
	 * @return java.util.List populated with the elements in the collection
	 * @throws RhapsodyRuntimeException
	 */	
	public List toList();

	/**
	 * Sets the size of a collection.
	 * @param size the new size that should be used for the collection
	 * @throws RhapsodyRuntimeException
	 */
	public void setSize(int size);

	/**
	 * Removes an element from a collection.
	 * @param index the index of the element that should be removed from the collection
	 * @throws RhapsodyRuntimeException
	 */
	public void remove(int index);

	/**
	 * Used to place a String in a specific place in a collection. Note that when using setString, the index parameter is based on an index value of 1 for the first element (not 0). The following code illustrates the use of this method with the populateDiagram method, which takes a number of arguments, one of which is a collection of Strings.
	 * <pre> 
	 * //The populateDiagram method takes 3 parameters, the first two being collections: a collection of model elements and a collection of strings
	 * IRPDiagram classDiagramToCreate = vehiclePackage.addObjectModelDiagram("Classes in Vehicles package");
	 * IRPCollection classesToAddToDiagram = vehiclePackage.getClasses();
	 * IRPCollection typesOfRelationsToShow = app.createNewCollection();
	 * typesOfRelationsToShow.setSize(2);
	 * typesOfRelationsToShow.setString(1, "Inheritance");
	 * typesOfRelationsToShow.setString(2, "Dependency");
	 * classDiagramToCreate.populateDiagram(classesToAddToDiagram, typesOfRelationsToShow, "fromto");
	 * </pre>
	 * @param index the index representing the place in the collection where the String should be placed (index of first element is 1, not 0)
	 * @param val the String to place in the collection
	 * @throws RhapsodyRuntimeException
	 */
	public void setString(int index, String val);

	/**
	 * Places an item in a specific place in a collection. Note that when using setModelElement, the index parameter is based on an index value of 1 for the first element (not 0).
	 * @param index the index representing the place in the collection where the item should be placed (index of first element is 1, not 0)
	 * @param val the item to place in the collection
	 * @throws RhapsodyRuntimeException
	 */
	public void setModelElement(int index, IRPModelElement val);

	/**
	 * Used to empty out a collection.
	 * @throws RhapsodyRuntimeException
	 */
	public void empty();
	
	/**
	 * Used to place an integer in a specific place in a collection. Note that when using setInteger, the index parameter is based on an index value of 1 for the first element (not 0).
	 * @param index the index representing the place in the collection where the integer should be placed (index of first element is 1, not 0)
	 * @param val the integer to place in the collection
	 */
	public void setInteger(int index, int val);

}

