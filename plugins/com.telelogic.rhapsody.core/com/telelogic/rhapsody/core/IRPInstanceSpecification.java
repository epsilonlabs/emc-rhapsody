
//Licensed Materials - Property of IBM 
//(c) Copyright IBM Corporation 2006, 2018. All Rights Reserved.

package com.telelogic.rhapsody.core;



public interface IRPInstanceSpecification  extends IRPModelElement {
	/**
 	 * Adds a new instance slot for the specified property of the classifier.
 	 * @param name the name to use for the new instance slot
 	 * @param slotProperty the property of the classifier that a slot should be created for
 	 * @return the instance slot that was created
 	 * @throws RhapsodyRuntimeException
 	 */
	public IRPInstanceSlot addInstanceSlot(String name, IRPModelElement slotProperty);
	/**
	 * get property classifier
	 * @throws RhapsodyRuntimeException
	 */
	public IRPClassifier getClassifier();
	/**
	 * get property instanceSlots
	 * @throws RhapsodyRuntimeException
	 */
	public IRPCollection getInstanceSlots();
	/**
 	 * Checks whether the instance specification is a root instance specification. A root instance specification is any instance specification that is not a nested instance specification.
 	 * @return 1 if the instance specification is a root instance specification, 0 otherwise
 	 * @throws RhapsodyRuntimeException
 	 */
	public int isRootInstanceSpecification();
	/**
	 * method populateSlots
	 * @throws RhapsodyRuntimeException
	 */
	public void populateSlots();
	/**
	 * set property classifier
	 * @throws RhapsodyRuntimeException
	 */
	public void setClassifier(IRPClassifier classifier);


}

