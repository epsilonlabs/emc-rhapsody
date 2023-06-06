
//Licensed Materials - Property of IBM 
//(c) Copyright IBM Corporation 2006, 2018. All Rights Reserved.

package com.telelogic.rhapsody.core;


/**
 * IRPSearchManager is used to carry out a search in a Rhapsody model.
 */
public interface IRPSearchManager {
	/**
 	 * Creates a search query object.
 	 * @return the search query object that was created
 	 * @throws RhapsodyRuntimeException
 	 */
	public IRPSearchQuery createSearchQuery();
	/**
 	 * Searches the model using the specified search query.
 	 * @param pSearchQuery the search query to use to search the model
 	 * @return collection of the model elements returned by the search
 	 * @throws RhapsodyRuntimeException
 	 */
	public IRPCollection search(IRPSearchQuery pSearchQuery);
	/**
 	 * Searches the model using the specified search query, and shows the results in the Search tab of the Output window.
 	 * @param pSearchQuery the search query to use to search the model
 	 * @throws RhapsodyRuntimeException
 	 */
	public void searchAndShowResults(IRPSearchQuery pSearchQuery);
	/**
 	 * Searches the model asynchronously, allowing you to continue working in Rhapsody. The method is used in conjunction with classes that are derived from the RPSearchListener class. The class includes the following methods that can be used to respond to the progress of the search: searchStarted, onNewSearchResult, and searchEnded.
 	 * @param pSearchQuery the search query to use to search the model
 	 * @throws RhapsodyRuntimeException
 	 * <pre>
 	 * {@code
 	 * IRPApplication app = RhapsodyAppServer.getActiveRhapsodyApplication();
 	 * IRPSearchManager mgr = app.getSearchManager();
 	 * IRPSearchQuery query = mgr.createSearchQuery();
 	 * query.addFilterElementType("Block");
 	 * MySearchListener listener = new MySearchListener();
 	 * listener.connect(mgr);
 	 * mgr.searchAsync(query);
 	 * public class MySearchListener extends RPSearchListener { 
 	 * 	 {@literal @}Override
 	 *	public boolean onNewSearchResult(IRPSearchResult pSearchResult) {
 	 * 		System.out.println(pSearchResult.getMatchedField());
 	 * 		System.out.println(pSearchResult.getMatchedObject().getName());
 	 *		return false;
 	 * 	}
 	 * 	// have to provide implementation of other abstract methods as well
 	 * }
 	 * }
 	 * </pre>
 	 */
	public void searchAsync(IRPSearchQuery pSearchQuery);
	/**
 	 * Returns the name of the API interface corresponding to the current element, for example, IRPClass for a class element, IRPOperation for an operation element.
 	 * @return the name of the API interface corresponding to the current element
 	 * @throws RhapsodyRuntimeException
 	 */
	public String getInterfaceName();


}

