
//Licensed Materials - Property of IBM 
//(c) Copyright IBM Corporation 2006, 2018. All Rights Reserved.

package com.telelogic.rhapsody.core;


 /**
  * The IRPTableView interface represents Table View elements in Rhapsody models. 
  */
public interface IRPTableView  extends IRPUnit {
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
 	 * Returns the number of columns in the table.
 	 * @return the number of columns in the table
 	 * @throws RhapsodyRuntimeException
 	 */
	public int getColumnCount();
	/**
 	 * Retrieves the content of the table in the specified format. The value of the parameter should be one of the values defined in the class IRPTableView.ContentFormat. Note that when you call this method, the table is also displayed in Rhapsody.
 	 * @param format one of the formats defined in the class IRPTableView.ContentFormat, for example, IRPTableView.ContentFormat.CSV
 	 * @return the content of the table in the specified format
 	 */
	public String getContent(String format);
	/**
 	 * Returns the content of the table as HTML. The content returned begins and ends with the "table" tag. Note that when this method is called, the table is opened in Rhapsody before the HTML is returned.
 	 * @return the content of the table as HTML
 	 */
	public String getHTMLContent();
	/**
	 * method GetImageCollection
	 * @throws RhapsodyRuntimeException
	 */
	public IRPCollection getImageCollection(String sFolder, String sFilename, String sExtension);
	/**
	 * method GetItsTableLayout
	 * @throws RhapsodyRuntimeException
	 */
	public IRPTableLayout getItsTableLayout();
	/**
 	 * Returns the number of rows in the table.
 	 * @return the number of rows in the table
 	 * @throws RhapsodyRuntimeException
 	 */
	public int getRowCount();
	/**
	 * method GetScope
	 * @throws RhapsodyRuntimeException
	 */
	public IRPCollection getScope();
	/**
 	 * Checks whether the scope of the table view was defined as including the "owner" of the table view.
 	 * @return 1 if the scope of the table view was defined as including the "owner", 0 otherwise
 	 * @throws RhapsodyRuntimeException
 	 */
	public int getUseOwnerScope();
	/**
 	 * Specifies the table layout to use for this table view.
 	 * @param pVal the table layout to use for this table view
 	 */
	public void setItsTableLayout(IRPTableLayout pVal);
	/**
 	 * Specifies the scope to use for this table view.
 	 * @param pCollection the scope to use for this table view. Note that the parameter is a Rhapsody collection, but at the moment, only the first value in the collection is used for the scope.
 	 */
	public void setScope(IRPCollection pCollection);
	/**
 	 * Specifies whether the the scope of the table view should include the element that owns the table view.
 	 * @param pVal use 1 to have the scope of the table view include the owner, use 0 to clear the setting 
 	 * @throws RhapsodyRuntimeException
 	 */
	public void setUseOwnerScope(int pVal);
	/**
 	 * Updates the view for the table on the Rhapsody Model Manager server.
 	 * @param enforceUpdate Use 0 to specify that the view should be updated only if changes that affect the table were made since the last update. Use 1 to specify that the view should be updated regardless of whether or not changes that affect the table were made since the last update.
 	 * @return 1 if the view for the table was updated on the server. If the table does not require an update, 0 is returned. If the update attempt failed, -1 is returned.
 	 * @throws RhapsodyRuntimeException
 	 */
	public int updateViewOnServer(int enforceUpdate);
	/**
	 * get property includeDescendants
	 * @throws RhapsodyRuntimeException
	 */
	public int getIncludeDescendants();
	/**
	 * method open
	 * @throws RhapsodyRuntimeException
	 */
	public void open();
	/**
	 * set property includeDescendants
	 * @throws RhapsodyRuntimeException
	 */
	public void setIncludeDescendants(int includeDescendants);
	
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

