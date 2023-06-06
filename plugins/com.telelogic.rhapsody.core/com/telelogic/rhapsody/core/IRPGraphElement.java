
//Licensed Materials - Property of IBM 
//(c) Copyright IBM Corporation 2006, 2018. All Rights Reserved.

package com.telelogic.rhapsody.core;



public interface IRPGraphElement {
	/**
	 * method addProperty
	 * @throws RhapsodyRuntimeException
	 */
	public void addProperty(String propertyKey, String propertyType, String propertyValue);
	/**
	 * method applyDefaultFormat
	 * @throws RhapsodyRuntimeException
	 */
	public void applyDefaultFormat();
	/**
	 * method getAllGraphicalProperties
	 * @throws RhapsodyRuntimeException
	 */
	public IRPCollection getAllGraphicalProperties();
	/**
	 * method getAllProperties
	 * @throws RhapsodyRuntimeException
	 */
	public IRPCollection getAllProperties();
	/**
	 * get associatedImage
	 * @throws RhapsodyRuntimeException
	 */
	public String getAssociatedImage();
	/**
	 * method getDiagram
	 * @throws RhapsodyRuntimeException
	 */
	public IRPDiagram getDiagram();
	/**
	 * get property graphicalParent
	 * @throws RhapsodyRuntimeException
	 */
	public IRPGraphElement getGraphicalParent();
	/**
	 * method getGraphicalProperty
	 * @throws RhapsodyRuntimeException
	 */
	public IRPGraphicalProperty getGraphicalProperty(String name);
	/**
 	 * Returns the specified graphical property for a textual element associated with the graphic element. This method is intended for use with graphic elements that have more than one textual element associated with them. The textName parameter is used to indicate which of the textual elements you want the property for. The values that can be used for the textName parameter depend upon the type of graphic element, as follows:
 	 * <ul>
 	 * <li>For all graphic elements - "Name", "Stereotype"</li>
 	 * <li>For lines only - "Label"</li>
 	 * <li>For flows only - "Keyword", "Conveyed"</li>
 	 * <li>For parts and objects only - "Multiplicity"</li>
 	 * <li>For association ends and links only - "SourceRole", "TargetRole", "SourceMultiplicity", "TargetMultiplicity"</li>
 	 * <li>For association ends only - "SourceQualifier", "TargetQualifier"</li>
 	 * <li>For ports only - "ProvidedInterfaceLabel", "RequiredInterfaceLabel"</li>
 	 * </ul>
 	 * For graphic elements associated with no more than one textual element, use the method {@link #getGraphicalProperty(java.lang.String)}.
 	 * @param textName the specific textual element that you want the property for
 	 * @param name the name of the graphical property, for example, "TextFontName", "TextColor", "TextFontItalic", "TextFontSize", "TextFontBold"
 	 * @return the graphical property that was requested
 	 */
	public IRPGraphicalProperty getGraphicalPropertyOfText(String textName, String name);
	/**
 	 * Returns the image layout specified for the image linked to the graphic element. When using the Java version of the API, the value returned will be one of the constants defined in the class {@link IRPGraphElement.ImageLayout}. When using the COM version of the API, the value returned will be one of the following strings: "Image Only Show Name", "Image Only Without Name", "Structured", "Compartment".
 	 * @return the image layout specified for the image linked to the graphic element
 	 */
	public String getImageLayout();
	/**
	 * get property interfaceName
	 * @throws RhapsodyRuntimeException
	 */
	public String getInterfaceName();
	/**
	 * method getLocalProperties
	 * @throws RhapsodyRuntimeException
	 */
	public IRPCollection getLocalProperties();
	/**
	 * get property modelObject
	 * @throws RhapsodyRuntimeException
	 */
	public IRPModelElement getModelObject();
	/**
	 * method getPropertyValue
	 * @throws RhapsodyRuntimeException
	 */
	public String getPropertyValue(String propertyKey);
	/**
 	 * Returns the full path of the image that was linked to the graphic element.
 	 * @return the full path of the image linked to the graphic element
 	 */
	public String getSelectedImage();
	/**
	 * method removeProperty
	 * @throws RhapsodyRuntimeException
	 */
	public void removeProperty(String propertyKey);
	/**
	 * set associatedImage
	 * @throws RhapsodyRuntimeException
	 */
	public void setAssociatedImage(String associatedImage);
	/**
 	 * Sets a new value for a graphical property. Certain graphical properties are available only for specific types of elements. Therefore, before including calls to this method in your code, you should call IRPGraphElement.getAllGraphicalProperties, which returns a collection of IRPGraphicalProperty objects representing the graphical properties available for the element in question. Note that for the name parameter, you only have to provide the property name, not the entire hierarchy as is the case with the method setPropertyValue.
 	 * @param name the name of the graphical property to set
 	 * @param value the value to use for the specified property
 	 */
	public void setGraphicalProperty(String name, String value);
	/**
 	 * Sets a new value for a graphical property for the specified textual element associated with the graphic element. This method is intended for use with graphic elements that have more than one textual element associated with them. The textName parameter is used to indicate which of the textual elements you want to set the property for. The values that can be used for the textName parameter depend upon the type of graphic element, as follows:
 	 * <ul>
 	 * <li>For all graphic elements - "Name", "Stereotype"</li>
 	 * <li>For lines only - "Label"</li>
 	 * <li>For flows only - "Keyword", "Conveyed"</li>
 	 * <li>For parts and objects only - "Multiplicity"</li>
 	 * <li>For association ends and links only - "SourceRole", "TargetRole", "SourceMultiplicity", "TargetMultiplicity"</li>
 	 * <li>For association ends only - "SourceQualifier", "TargetQualifier"</li>
 	 * <li>For ports only - "ProvidedInterfaceLabel", "RequiredInterfaceLabel"</li>
 	 * </ul>
 	 * Certain graphical properties are available only for specific types of elements. Therefore, before including calls to this method in your code, you should call IRPGraphElement.getAllGraphicalProperties, which returns a collection of IRPGraphicalProperty objects representing the graphical properties available for the element in question. Note that for the name parameter, you only have to provide the property name, not the entire hierarchy as is the case with the method setPropertyValue.
 	 * For graphic elements associated with no more than one textual element, use the method {@link #setGraphicalProperty}.
 	 * @param textName the specific textual element that you want to set the property for
 	 * @param name the name of the graphical property to set
 	 * @param value the value to use for the specified property
 	 */
	public void setGraphicalPropertyOfText(String textName, String name, String value);
	/**
 	 * Used to specify the image layout that should be used for the image linked to the graphic element. When using the Java version of the API, the value of the parameter should be one of the constants defined in the class {@link IRPGraphElement.ImageLayout}. When using the COM version of the API, the value of the parameter should be one of the following strings: "Image Only Show Name", "Image Only Without Name", "Structured", "Compartment".
 	 * @param imageLayout the image layout that should be used for the image linked to the graphic element
 	 */
	public void setImageLayout(String imageLayout);
	/**
	 * method setPropertyValue
	 * @throws RhapsodyRuntimeException
	 */
	public void setPropertyValue(String propertyKey, String propertyValue);
	/**
 	 * Links the graphic element to the image represented by the path specified. To remove an existing link to an image without providing a new image, use an empty string for the parameter.
 	 * @param selectedImage the full path to the image that should be linked to the graphic element
 	 */
	public void setSelectedImage(String selectedImage);
	
	/** Constant values used with elements of this type **/
	/**
This class contains constant values for use with the method setImageLayout
	**/
	public static final class ImageLayout
	{
		/**
Show image in structured layout
		**/
		public static final	 String		STRUCTURED		= "Structured";
		/**
Set image layout as show image only without name
		**/
		public static final	 String		IMAGE_ONLY_WITHOUT_NAME		= "Image Only Without Name";
		/**
Set image layout as show image only with name
		**/
		public static final	 String		IMAGE_ONLY_SHOW_NAME		= "Image Only Show Name";
		/**
Set image layout in a compartment
		**/
		public static final	 String		COMPARTMENT		= "Compartment";
	}



}

