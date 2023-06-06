
//Licensed Materials - Property of IBM 
//(c) Copyright IBM Corporation 2006, 2018. All Rights Reserved.

package com.telelogic.rhapsody.core;


 /**
  * The IRPStereotype interface represents sterotypes in Rhapsody models.
  */
public interface IRPStereotype  extends IRPClassifier {
	/**
 	 * Adds a metaclass to the list of metaclasses that the stereotype can be applied to.
 	 * @param metaClass the metaclass to add. Note that this string parameter can only contain the name of one metaclass. Adding multiple metaclasses requires multiple calls of this method.
 	 */
	public void addMetaClass(String metaClass);
	/**
 	 * Gets the full path for the image file that is associated with this stereotype.
 	 * @return the full path for the image file that is associated with this stereotype
 	 */
	public String getIcon();
	/**
 	 * Checks whether the stereotype is a "new term" stereotype. For more information about "new terms", see the help for customizing Rhapsody.
 	 * @return indication of whether the stereotype is a "new term". 1 means that the stereotype is a "new term", 0 means that the stereotype is not a "new term".
 	 */
	public int getIsNewTerm();
	/**
 	 * Gets the names of the metaclasses that the stereotype can be applied to.
 	 * @return the names of the metaclasses that the stereotype can be applied to. If there is more than one such metaclass, the string returned will consist of a comma-separated list of the names.
 	 */
	public String getOfMetaClass();
	/**
 	 * Removes a metaclass from the list of metaclasses that the stereotype can be applied to.
 	 * @param metaClass the metaclass to remove. Note that this string parameter can only contain the name of one metaclass. Removing multiple metaclasses requires multiple calls of this method.
 	 */
	public void removeMetaClass(String metaClass);
	/**
 	 * Used to change a stereotype to a "new term" stereotype, or change a "new term" stereotype to an ordinary stereotype. For more information about "new terms", see the help for customizing Rhapsody.
 	 * @param isNewTerm Use 1 to change the stereotype to a "new term" stereotype. Use 0 to change a "new term" stereotype to an ordinary stereotype.
 	 */
	public void setIsNewTerm(int isNewTerm);


}

