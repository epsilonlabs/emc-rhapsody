
//Licensed Materials - Property of IBM 
//(c) Copyright IBM Corporation 2006, 2018. All Rights Reserved.

package com.telelogic.rhapsody.core;



public interface IRPMatrixLayout  extends IRPUnit {
	/**
 	 * Returns a collection of the element types that were specified to be displayed in the cells of the matrix. The collection consists of strings (from the list of types displayed on the Cell Element Types tab of the Features window for matrix layouts).
 	 * @return the element types that were specified to be displayed in the cells of the matrix
 	 */
	public IRPCollection getCellElementTypes();
	/**
 	 * Returns a collection of the "from" element types specified to be displayed in the matrix. The collection consists of strings (from the list of types displayed on the From Element Types tab of the Features window for matrix layouts).
 	 * @return the "from" element types specified to be displayed in the matrix
 	 */
	public IRPCollection getFromElementTypes();
	/**
 	 * Returns the query that was specified to determine the "from" element types.
 	 * @return the query that was specified to determine the "from" element types for the matrix layout
 	 */
	public IRPTableLayout getFromElementTypesQueryToUse();
	/**
 	 * Checks whether a query or collection of element types was used to specify the "from" element types.
 	 * @return one of the constants contained in the class IRPMatrixLayout.QueryOrElementsList: QUERY if a query was used, ELEMENTS_LIST if a collection of element types was used.
 	 */
	public int getFromElementTypesUseQueryOrElementsList();
	/**
 	 * Returns a collection of the "to" element types specified to be displayed in the matrix. The collection consists of strings (from the list of types displayed on the To Element Types tab of the Features window for matrix layouts).
 	 * @return the "to" element types specified to be displayed in the matrix
 	 */
	public IRPCollection getToElementTypes();
	/**
 	 * Returns the query that was specified to determine the "to" element types.
 	 * @return the query that was specified to determine the "to" element types for the matrix layout
 	 */
	public IRPTableLayout getToElementTypesQueryToUse();
	/**
 	 * Checks whether a query or collection of element types was used to specify the "to" element types.
 	 * @return one of the constants contained in the class IRPMatrixLayout.QueryOrElementsList: QUERY if a query was used, ELEMENTS_LIST if a collection of element types was used.
 	 */
	public int getToElementTypesUseQueryOrElementsList();
	/**
 	 * Specifies the element types to display in the cells of the matrix. The parameter must be a collection of strings (from the list of types displayed on the Cell Element Types tab of the Features window for matrix layouts).
 	 * @param pCollection the element types to display in the cells of the matrix
 	 */
	public void setCellElementTypes(IRPCollection pCollection);
	/**
 	 * Specifies the "from" element types that should be displayed in the matrix. The parameter must be a collection of strings (from the list of element types displayed on the From Element Types tab of the Features window for matrix layouts).
 	 * @param pCollection the "from" element types that should be displayed in the matrix
 	 */
	public void setFromElementTypes(IRPCollection pCollection);
	/**
 	 * Specifies the query to use to determine the "from" element types for the matrix layout.
 	 * @param query the query to use to determine the "from" element types for the matrix layout. To clear a previous query, use null for the parameter.
 	 */
	public void setFromElementTypesQueryToUse(IRPTableLayout query);
	/**
 	 * Specifies whether a query or collection of element types should be used to determine the "from" element types for the matrix layout.
 	 * @param queryOrElementsList one of the constants contained in the class IRPMatrixLayout.QueryOrElementsList: QUERY if a query should be used, ELEMENTS_LIST if a collection of element types should be used.
 	 */
	public void setFromElementTypesUseQueryOrElementsList(int queryOrElementsList);
	/**
 	 * Specifies the "to" element types that should be displayed in the matrix. The parameter must be a collection of strings (from the list of types displayed on the To Element Types tab of the Features window for matrix layouts).
 	 * @param pCollection the "to" element types that should be displayed in the matrix
 	 */
	public void setToElementTypes(IRPCollection pCollection);
	/**
 	 * Specifies the query to use to determine the "to" element types for the matrix layout.
 	 * @param query the query to use to determine the "to" element types for the matrix layout. To clear a previous query, use null for the parameter.
 	 */
	public void setToElementTypesQueryToUse(IRPTableLayout query);
	/**
 	 * Specifies whether a query or collection of element types should be used to determine the "to" element types for the matrix layout.
 	 * @param queryOrElementsList one of the constants contained in the class IRPMatrixLayout.QueryOrElementsList: QUERY if a query should be used, ELEMENTS_LIST if a collection of element types should be used.
 	 */
	public void setToElementTypesUseQueryOrElementsList(int queryOrElementsList);
	
	/** Constant values used with elements of this type **/
	/**
This class contains constant values for use with the methods setFromElementTypesUseQueryOrElementsList and setToElementTypesUseQueryOrElementsList.
	**/
	public static final class QueryOrElementsList
	{
		/**
When QUERY is used as the parameter for the methods setFromElementTypesUseQueryOrElementsList and setToElementTypesUseQueryOrElementsList, it indicates that a query will be used to determine the "from" element types or "to" element types for the matrix.
		**/
		public static final	 int		QUERY		= 1;
		/**
When ELEMENTS_LIST is used as the parameter for the methods setFromElementTypesUseQueryOrElementsList and setToElementTypesUseQueryOrElementsList, it indicates that elements selected in the element types list will be used to specify the "from" element types or "to" element types for the matrix.
		**/
		public static final	 int		ELEMENTS_LIST		= 0;
	}



}

