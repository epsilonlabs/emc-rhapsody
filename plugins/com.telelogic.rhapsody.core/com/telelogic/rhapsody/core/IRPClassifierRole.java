
//Licensed Materials - Property of IBM 
//(c) Copyright IBM Corporation 2006, 2018. All Rights Reserved.

package com.telelogic.rhapsody.core;


/**
 * The IRPClassifierRole interface represents lifelines in sequence diagrams and "objects" (lifelines) in communication diagrams. 
 */
public interface IRPClassifierRole  extends IRPModelElement {
	/**
 	 * Returns the classifier (for example, class or actor) that the lifeline realizes.
 	 * @return the classifier that the lifeline realizes
 	 * @throws RhapsodyRuntimeException
 	 */
	public IRPClassifier getFormalClassifier();
	/**
 	 * For cases where a lifeline represents an object and not just a classifier, returns the object that is realized by the lifeline. If the method is called for a lifeline that does not realize an object, it returns null.
 	 * @return the object that is realized by the lifeline
 	 * @throws RhapsodyRuntimeException
 	 */
	public IRPInstance getFormalInstance();
	/**
 	 * Returns the sequence diagram referenced by the lifeline.
 	 * @return the sequence diagram referenced by the lifeline, returns null if there is no referenced diagram
 	 * @throws RhapsodyRuntimeException
 	 */
	public IRPSequenceDiagram getReferencedSequenceDiagram();
	/**
 	 * Returns a collection of all the lifelines in referenced sequence diagrams. This is done recursively so the collection includes all the lifelines in the decomposition hierarchy.
 	 * @return a collection of all the lifelines in referenced sequence diagrams
 	 * @throws RhapsodyRuntimeException
 	 */
	public IRPCollection getReferencingClassifierRolesRecursively();
	/**
 	 * Returns a string representing the type of the classifier role, for example, CLASS for elements of type IRPClass and ACTOR for elements of type IRPActor. For objects, the string returned is CLASS.
 	 * @return a string representing the type of the classifier role
 	 * @throws RhapsodyRuntimeException
 	 */
	public String getRoleType();
	/**
 	 * Sets the specified element as the classifier realized by the lifeline.
 	 * @param formalClassifier the model element that should be used as the classifier realized by the lifeline.
 	 * @throws RhapsodyRuntimeException
 	 */
	public void setFormalClassifier(IRPClassifier formalClassifier);
	/**
 	 * Sets the specified element as the object realized by the lifeline.
 	 * @param formalInstance the model element that should be used as the object realized by the lifeline.
 	 * @throws RhapsodyRuntimeException
 	 */
	public void setFormalInstance(IRPInstance formalInstance);
	/**
 	 * Sets the specified diagram to be the sequence diagram referenced by the lifeline.
 	 * @param referencedSequenceDiagram the diagram that should be used as the sequence diagram referenced by the lifeline
 	 * @throws RhapsodyRuntimeException
 	 */
	public void setReferencedSequenceDiagram(IRPSequenceDiagram referencedSequenceDiagram);


}

