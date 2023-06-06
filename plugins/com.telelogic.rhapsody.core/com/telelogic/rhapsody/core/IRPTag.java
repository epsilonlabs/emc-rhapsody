
//Licensed Materials - Property of IBM 
//(c) Copyright IBM Corporation 2006, 2018. All Rights Reserved.

package com.telelogic.rhapsody.core;


 /**
  * The IRPTag interface represents tags in a Rhapsody model. To create a new tag, use the method IRPModelElement.addNewAggr.
  */
public interface IRPTag  extends IRPVariable {
	/**
 	 * Returns the base tag on which the local copy of the tag is based. This method is relevant for tags that belong to stereotypes and tags that belong to profiles, but not for tags that were added locally to a specific model element.
 	 * @return the base tag on which the local copy of the tag is based
 	 */
	public IRPTag getBase();
	/**
 	 * For tags whose source is a profile that was added to the project (as opposed to tags defined locally in the project), this method returns the profile in which the tag was defined.
 	 * @return the profile in which the tag was defined
 	 */
	public IRPProfile getFromProfile();
	/**
 	 * Returns the multiplicity that was specified for the tag.
 	 * @return the multiplicity that was specified for the tag
 	 */
	public String getMultiplicity();
	/**
 	 * Returns the name of the metaclass to which the tag is applicable. Relevant only for tags that belong to a profile.
 	 * @return the name of the metaclass to which the tag is applicable
 	 */
	public String getTagMetaClass();
	/**
 	 * Returns the value of the tag.
 	 * @return the value of the tag
 	 */
	public String getValue();
	/**
 	 * Specifies the multiplicity for the tag.
 	 * @param multiplicity the multiplicity to use for the tag. You can use strings such as "1" or "14" to specify a specific number, or you can use one of the values listed in the Features dialog for tags: "0,1", "*", or "1..*".
 	 */
	public void setMultiplicity(String multiplicity);
	/**
 	 * Sets the value of the tag to a specific instance of another model element.
 	 * @param elements collection of model elements representing the full path to the element. This collection is used to set the value of the tag to the full path of the target element. The collection must consist of objects of type IRPModelElement.
 	 * @param multiplicities collection of the relevant indices for each of the model elements in the first collection (the "elements" parameter). This makes it possible to point to a specific instance of the target model element when multiplicity is greater than one. The collection must consist of integers provided as strings.
 	 */
	public void setTagContextValue(IRPCollection elements, IRPCollection multiplicities);
	/**
 	 * Specifies the metaclass to which the tag should be applicable, for example, "Class". Relevant only for tags that belong to a profile.
 	 * @param tagMetaClass the metaclass to which the tag should be applicable
 	 */
	public void setTagMetaClass(String tagMetaClass);
	/**
 	 * Sets the value of the tag.
 	 * @param value the value to use for the tag
 	 */
	public void setValue(String value);


}

