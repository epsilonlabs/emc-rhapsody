
//Licensed Materials - Property of IBM 
//(c) Copyright IBM Corporation 2006, 2018. All Rights Reserved.

package com.telelogic.rhapsody.core;


 /**
  * The IRPMatrixView interface represents Matrix View elements in Rhapsody models. 
  */
public interface IRPMatrixView  extends IRPUnit {
	/**
 	 * Returns the model elements contained in the specified cell.
 	 * @param row the number of the row that the cell is in - row count begins at zero
 	 * @param column the number of the column that the cell is in - column count begins at zero
 	 * @return the model elements contained in the specified cell
 	 * @throws RhapsodyRuntimeException
 	 */
	public IRPCollection getCellElements(int row, int column);
	/**
 	 * Returns the text contained in the specified cell.
 	 * @param row the number of the row that the cell is in - row count begins at zero
 	 * @param column the number of the column that the cell is in - column count begins at zero
 	 * @return the text contained in the specified cell
 	 * @throws RhapsodyRuntimeException
 	 */
	public String getCellString(int row, int column);
	/**
 	 * Returns the number of columns in the matrix.
 	 * @return the number of columns in the matrix
 	 * @throws RhapsodyRuntimeException
 	 */
	public int getColumnCount();
	/**
 	 * Retrieves the content of the matrix in the specified format. The value of the parameter should be one of the values defined in the class IRPMatrixView.ContentFormat. Note that when you call this method, the matrix is also displayed in Rhapsody.
 	 * @param format one of the formats defined in the class IRPMatrixView.ContentFormat, for example, IRPMatrixView.ContentFormat.CSV
 	 * @return the content of the matrix in the specified format
 	 */
	public String getContent(String format);
	/**
	 * method GetFromScope
	 * @throws RhapsodyRuntimeException
	 */
	public IRPCollection getFromScope();
	/**
 	 * Returns the content of the matrix as HTML. The content returned begins and ends with the "table" tag. Note that when this method is called, the matrix is opened in Rhapsody before the HTML is returned.
 	 * @return the content of the matrix as HTML
 	 */
	public String getHTMLContent();
	/**
	 * method GetImageCollection
	 * @throws RhapsodyRuntimeException
	 */
	public IRPCollection getImageCollection(String sFolder, String sFilename, String sExtension);
	/**
	 * method GetItsMatrixLayout
	 * @throws RhapsodyRuntimeException
	 */
	public IRPMatrixLayout getItsMatrixLayout();
	/**
 	 * Returns the number of rows in the matrix.
 	 * @return the number of rows in the matrix
 	 * @throws RhapsodyRuntimeException
 	 */
	public int getRowCount();
	/**
	 * method GetToScope
	 * @throws RhapsodyRuntimeException
	 */
	public IRPCollection getToScope();
	/**
 	 * Specifies the "from" scope to use for this matrix view.
 	 * @param pCollection the "from" scope to use for this matrix view. Note that the parameter is a Rhapsody collection, but at the moment, only the first value in the collection is used for the "from" scope.
 	 */
	public void setFromScope(IRPCollection pCollection);
	/**
 	 * Specifies the matrix layout to use for this matrix view.
 	 * @param pVal the matrix layout to use for this matrix view
 	 */
	public void setItsMatrixLayout(IRPMatrixLayout pVal);
	/**
 	 * Specifies the "to" scope to use for this matrix view.
 	 * @param pCollection the "to" scope to use for this matrix view. Note that the parameter is a Rhapsody collection, but at the moment, only the first value in the collection is used for the "to" scope.
 	 */
	public void setToScope(IRPCollection pCollection);
	/**
 	 * Updates the view for the matrix on the Rhapsody Model Manager server.
 	 * @param enforceUpdate Use 0 to specify that the view should be updated only if changes that affect the matrix were made since the last update. Use 1 to specify that the view should be updated regardless of whether or not changes that affect the matrix were made since the last update.
 	 * @return 1 if the view for the matrix was updated on the server. If the matrix does not require an update, 0 is returned. If the update attempt failed, -1 is returned.
 	 * @throws RhapsodyRuntimeException
 	 */
	public int updateViewOnServer(int enforceUpdate);
	/**
	 * get property includeDescendantsFromScope
	 * @throws RhapsodyRuntimeException
	 */
	public int getIncludeDescendantsFromScope();
	/**
	 * get property includeDescendantsToScope
	 * @throws RhapsodyRuntimeException
	 */
	public int getIncludeDescendantsToScope();
	/**
	 * method open
	 * @throws RhapsodyRuntimeException
	 */
	public void open();
	/**
	 * set property includeDescendantsFromScope
	 * @throws RhapsodyRuntimeException
	 */
	public void setIncludeDescendantsFromScope(int includeDescendantsFromScope);
	/**
	 * set property includeDescendantsToScope
	 * @throws RhapsodyRuntimeException
	 */
	public void setIncludeDescendantsToScope(int includeDescendantsToScope);
	
	/** Constant values used with elements of this type **/
	/**
	 * This class contains values that specify export format
	**/
	public static final class ContentFormat
	{
		/**
		 * Export in HTML format.
		 * Exported only string representations.
		**/
		public static final	 String		HTML		= "HTML";
		/**
		 * Export in XML format.
		 * For each model element, its GUID is exported as well.
		**/
		public static final	 String		XML		= "XML";
		/**
		 * Export in Comma Separated Value (CSV) format.
		 * Exported only string representations.
		**/
		public static final	 String		CSV		= "CSV";
	}



}

