
//Licensed Materials - Property of IBM 
//(c) Copyright IBM Corporation 2006, 2018. All Rights Reserved.

package com.telelogic.rhapsody.core;


/**
 * The IRPHyperLink interface represents hyperlinks in Rhapsody models.
 */
public interface IRPHyperLink  extends IRPDependency {
	/**
 	 * Returns  the text that is displayed for the hyperlink.
 	 * @return the text that is displayed for the hyperlink
 	 * @throws RhapsodyRuntimeException
 	 */
	public String getTextToDisplay();
	/**
 	 * Returns the type of text that is displayed for the hyperlink.
 	 * @return the type of text that is displayed for the hyperlink. Will be one of the constants defined in the class {@link HYPNameType}.
 	 * @throws RhapsodyRuntimeException
 	 */
	public char getTextToDisplayType();
	/**
 	 * @deprecated Use {@link #getTextToDisplayType()} and {@link #getTextToDisplay} instead.
 	 */
 	@Deprecated
	public void getDisplayOption(char pVal, String pDisplayName);
	/**
 	 * Returns the target model element if the hyperlink points to a model element.
 	 * @return the model element that the hyperlink points to
 	 */
	public IRPModelElement getTarget();
	/**
 	 * Returns the target URL if the hyperlink points to a URL.
 	 * @return the URL that the hyperlink points to
 	 */
	public String getURL();
	/**
 	 * Sets the text to display for the the hyperlink.
 	 * @param newTextToDisplayType the type of text to display for the hyperlink. Use one of the constants defined in the class {@link HYPNameType}.
 	 * @param newTextToDisplay the text to display for the hyperlink if you specified RP_HYP_FREETEXT as the type of text to display. If you specified one of the other types of text, such as RP_HYP_NAMETEXT, the value of this parameter is ignored.
 	 * @throws RhapsodyRuntimeException
 	 */
	public void setDisplayOption(char newTextToDisplayType, String newTextToDisplay);
	/**
 	 * Sets the specified model element to be the target of the hyperlink.
 	 * @param target the model element that should be used as the target of the hyperlink
 	 */
	public void setTarget(IRPModelElement target);
	/**
 	 * Sets the specified URL to be the target of the hyperlink.
 	 * @param uRL the URL that should be used as the target of the hyperlink
 	 */
	public void setURL(String uRL);


}

