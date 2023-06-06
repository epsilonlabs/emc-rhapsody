
//Licensed Materials - Property of IBM 
//(c) Copyright IBM Corporation 2006, 2018. All Rights Reserved.

package com.telelogic.rhapsody.core;


 /**
  * The IRPAnnotation interface represents the different types of annotations you can add to your model - notes, comments, constraints, and requirements.
  */
public interface IRPAnnotation  extends IRPUnit {
	/**
 	 * Adds an anchor from the annotation to the specified model element.
 	 * @param target the model element the annotation should be anchored to
 	 */
	public void addAnchor(IRPModelElement target);
	/**
 	 * Gets the list of model elements that are anchored to the annotation.
 	 * @return the model elements that are anchored to the annotation.
 	 */
	public IRPCollection getAnchoredByMe();
	/**
 	 * Gets the text of the specification for the annotation.
 	 * @return the text of the specification for the annotation
 	 */
	public String getBody();
	/**
 	 * Gets the text of the specification for the annotation.
 	 * @return the text of the specification for the annotation
 	 */
	public String getSpecification();
	/**
 	 * Returns the specification of the annotation in RTF format.
 	 * @return the specification of the annotation in RTF format
 	 */
	public String getSpecificationRTF();
	/**
 	 * Checks whether the specification is in RTF format
 	 * @return 1 if the specification is in RTF format, 0 otherwise
 	 */
	public int isSpecificationRTF();
	/**
 	 * Removes the anchor to the specified model element.
 	 * @param target the model element for which the anchor should be removed
 	 */
	public void removeAnchor(IRPModelElement target);
	/**
 	 * Adds a specification to the annotation.
 	 * @param body the text to use as the specification
 	 */
	public void setBody(String body);
	/**
 	 * Adds a specification to the annotation.
 	 * @param specification the text to use as the specification
 	 */
	public void setSpecification(String specification);
	/**
 	 * Specifies RTF string to use for the specification of the annotation.
 	 * @param specificationRTF the RTF string to use for the specification of the annotation
 	 */
	public void setSpecificationRTF(String specificationRTF);


}

