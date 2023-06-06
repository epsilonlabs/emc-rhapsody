
//Licensed Materials - Property of IBM 
//(c) Copyright IBM Corporation 2006, 2018. All Rights Reserved.

package com.telelogic.rhapsody.core;


/**
 * The IRPSearchQuery interface represents the search criteria objects that are used by IRPSearchManager to carry out searches.
 */
public interface IRPSearchQuery {
	/**
 	 * Adds the specified diagram to the list of views to be searched for the search text. Note that the list of views to search will be used only if you call the method IRPSearchQuery.setViewsToSearch, providing IRPSearchQuery.ViewsToSearch.DETAILED as the argument.
 	 * @param view the diagram to add to the list of views to search
 	 * @return the location of the new item in the list of views. Note that the order of the list can change when a view is added or removed, so the returned index can only be used if you have not made additional changes to the list since adding the item.
 	 * @throws RhapsodyRuntimeException
 	 */
	public int addDiagramToViewsList(IRPDiagram view);
	/**
 	 * Adds an element type to the list of element types that the search should be applied to. Note that the purpose of this method is to limit the search to certain element types. If you do not call this method at all, then the search will be applied to all model element types.
 	 * @param elementType element type to add to the list of element types to search. The strings to use for this parameter can be found in the file metaclasses.txt in the Doc directory of the Rhapsody installation. For this parameter, you can also use any "new terms" in your project.
 	 * @throws RhapsodyRuntimeException
 	 */
	public void addFilterElementType(String elementType);
	/**
 	 * Adds an element field to the list of element fields that the search should be applied to, for example, element name or element description. Note that the purpose of this method is to limit the search to certain element fields. If you do not call this method at all, then the search will be applied to all model element fields.
 	 * @param searchInField element field to add to the list of element fields to search. The value of this parameter should be one of the constants defined in the class {@link IRPSearchQuery.SearchInField}. For example, use IRPSearchQuery.SearchInField.NAME for the name of the model element, and IRPSearchQuery.SearchInField.DESCRIPTION for the description of the model element.
 	 */
	public void addFilterSearchInField(String searchInField);
	/**
 	 * Specifies that the search should be limited to model elements with a specific stereotype applied to them. Note that you can call this method multiple times to specify that the search should be limited to elements that have a certain group of stereotypes.
 	 * @param stereotype the stereotype to use as a search criterion. Use null if you want to search for model elements that do not have any stereotypes applied to them.
 	 * @throws RhapsodyRuntimeException
 	 */
	public void addFilterStereotype(IRPStereotype stereotype);
	/**
 	 * Adds a subquery to the list of subqueries specified for the search.
 	 * @param subQuery the subquery to add for the search
 	 * @param useWithNotOperator use 1 if you want the NOT operator to be used for the specified subquery, 0 otherwise
 	 * @throws RhapsodyRuntimeException
 	 */
	public void addFilterSubQuery(IRPTableLayout subQuery, int useWithNotOperator);
	/**
 	 * Adds the specified matrix to the list of views to be searched for the search text. Note that the list of views to search will be used only if you call the method IRPSearchQuery.setViewsToSearch, providing IRPSearchQuery.ViewsToSearch.DETAILED as the argument.
 	 * @param view the matrix to add to the list of views to search
 	 * @return the location of the new item in the list of views. Note that the order of the list can change when a view is added or removed, so the returned index can only be used if you have not made additional changes to the list since adding the item.
 	 * @throws RhapsodyRuntimeException
 	 */
	public int addMatrixToViewsList(IRPMatrixView view);
	/**
 	 * Adds an element to the scope for the search. You can call this method multiple times to include different parts of the model in a search.
 	 * @param scopeElement model element that represents a part of the model that should be searched, for example, a specific package
 	 * @throws RhapsodyRuntimeException
 	 */
	public void addSearchScope(IRPModelElement scopeElement);
	/**
 	 * Adds the specified table to the list of views to be searched for the search text. Note that the list of views to search will be used only if you call the method IRPSearchQuery.setViewsToSearch, providing IRPSearchQuery.ViewsToSearch.DETAILED as the argument.
 	 * @param view the table to add to the list of views to search
 	 * @return the location of the new item in the list of views. Note that the order of the list can change when a view is added or removed, so the returned index can only be used if you have not made additional changes to the list since adding the item.
 	 * @throws RhapsodyRuntimeException
 	 */
	public int addTableToViewsList(IRPTableView view);
	/**
 	 * Returns the element types that are to be searched for the search text. Note that this method will return element types only if you used the method addFilterElementType to limit the search to certain element types. If you did not call the method addFilterElementType, then the search is applied to all element types, and getFilterElementType will return an empty collection.
 	 * @return the element types that are to be searched
 	 * @throws RhapsodyRuntimeException
 	 */
	public IRPCollection getFilterElementTypes();
	/**
 	 * Checks whether the reference search criterion specified that the referenced elements included in the search criterion should also be displayed in the search results.
 	 * @return 1 if the reference search criterion specified that the referenced elements included in the search criterion should also be displayed in the search results, 0 otherwise
 	 * @throws RhapsodyRuntimeException
 	 */
	public int getFilterReferenceIncludeReferencedElementsInSearchResults();
	/**
 	 * Returns the model element name that was specified for the reference criterion that was defined.
 	 * @return the model element name that was specified for the reference criterion that was defined
 	 * @throws RhapsodyRuntimeException
 	 */
	public String getFilterReferenceNameOfReferencedElements();
	 /**
 	 * Returns the number of references that was specified as a search criterion.
 	 * @return the number of references that was specified as a search criterion
 	 * @throws RhapsodyRuntimeException
 	 */
	public int getFilterReferenceNumberOfReferences();
	/**
 	 * When the search criterion includes a specific number of references, this method returns a value that indicates whether the criterion was exactly that number of references, less than that number, or more than that number.
 	 * @return value that indicates whether the search criterion was an exact number of references, less than a specific number of references, or more than a specific number of references. The value returned will be one of the values defined in {@link IRPSearchQuery.References.QuantityOperator}.
 	 * @throws RhapsodyRuntimeException
 	 */
	public String getFilterReferenceQuantityOperator();
	/**
 	 * Returns the type of reference used in the search criterion, for example, aggregates or incoming relations.
 	 * @return the type of reference used in the search criterion - one of the values defined in {@link IRPSearchQuery.References.RelationKind}.
 	 * @throws RhapsodyRuntimeException
 	 */
	public String getFilterReferenceRelationKind();
	/**
 	 * Returns the stereotype that was specified for the reference criterion that was defined.
 	 * @return the stereotype that was specified for the reference criterion that was defined
 	 * @throws RhapsodyRuntimeException
 	 */
	public String getFilterReferenceStereotypeOfReferencedElements();
	/**
 	 * Returns the model element type that was specified for the reference criterion that was defined.
 	 * @return the model element type that was specified for the reference criterion that was defined.
 	 * @throws RhapsodyRuntimeException
 	 */
	public String getFilterReferenceTypeOfReferencedElements();
	/**
 	 * Returns the list of element fields that the search is to be applied to.
 	 * @return the list of element fields that the search is to be applied to. The collection returned will consist of constants defined in the class {@link IRPSearchQuery.SearchInField}. For example, IRPSearchQuery.SearchInField.NAME for the name of the model element, and IRPSearchQuery.SearchInField.DESCRIPTION for the description of the model element.
 	 * @throws RhapsodyRuntimeException
 	 */
	public IRPCollection getFilterSearchInFields();
	/**
 	 * Returns the names of the stereotypes that were specified as search criteria.
 	 * @return the names of the stereotypes that were specified as search criteria
 	 * @throws RhapsodyRuntimeException
 	 */
	public IRPCollection getFilterStereotypes();
	/**
 	 * Returns the subqueries that were specified for the search.
 	 * @return the subqueries that were specified for the search
 	 * @throws RhapsodyRuntimeException
 	 */
	public IRPCollection getFilterSubQueries();
	/**
 	 * Checks whether the NOT operator was specified for the specified subquery.
 	 * @param subQuery the subquery to be checked
 	 * @return 1 if the NOT operator was specified for the subquery, 0 otherwise
 	 * @throws RhapsodyRuntimeException
 	 */
	public int getFilterSubQueryUseWithNotOperator(IRPTableLayout subQuery);
	/**
 	 * Returns the type of search that was specified for the tag name and tag value search criteria - regular text, wildcard, regular expression, or empty string.
 	 * @return the type of search that was specified for the tag name and tag value search criteria - will be one of the constants defined in the class {@link SearchFindAsEnum}, for example SearchFindAsEnum.RP_SEARCH_WILDCARD for a wildcard search or SearchFindAsEnum.RP_SEARCH_REGEX for a regular expression search.
 	 * @throws RhapsodyRuntimeException
 	 */
	public char getFilterTagFindAs();
	/**
 	 * Checks whether an exact match was specified for the tag name and tag value search criteria, in terms of upper and lower case.
 	 * @return 1 if an exact match was specified for the tag criteria in terms of upper and lower case, 0 otherwise
 	 * @throws RhapsodyRuntimeException
 	 */
	public int getFilterTagMatchCase();
	/**
 	 * Checks whether a whole word match was specified for the tag name and tag value search criteria
 	 * @return 1 if whole word match was specified for the tag criteria, 0 otherwise
 	 * @throws RhapsodyRuntimeException
 	 */
	public int getFilterTagMatchWholeWord();
	/**
 	 * Returns the tag name specified as a criterion for the search
 	 * @return the tag name specified as a criterion for the search
 	 * @throws RhapsodyRuntimeException
 	 */
	public String getFilterTagName();
	/**
 	 * Returns the tag value specified as a criterion for the search
 	 * @return the tag value specified as a criterion for the search
 	 * @throws RhapsodyRuntimeException
 	 */
	public String getFilterTagValue();
	/**
 	 * Returns a collection of the model elements that constitute the scope for the search.
 	 * @return the model elements that constitute the scope for the search
 	 * @throws RhapsodyRuntimeException
 	 */
	public IRPCollection getSearchScopeElements();
	/**
 	 * Retrieves the specified item from the list of tables, matrices, and diagrams that are to be searched.
 	 * @param Index the index of the view to retrieve. Note that the index of the first view in the list is 0.
 	 * @return the specified item from the list of tables, matrices, and diagrams that are to be searched
 	 * @throws RhapsodyRuntimeException
 	 */
	public IRPModelElement getView(int Index);
	/**
 	 * Returns the number of views in the list of views that are to be searched.
 	 * @return the number of views in the list of views that are to be searched
 	 * @throws RhapsodyRuntimeException
 	 */
	public int getViewCount();
	/**
 	 * Loads the settings from the specified query into the search query object.
 	 * @param query the query element whose settings should be loaded into the search query object
 	 * @throws RhapsodyRuntimeException
 	 */
	public void loadFromQuery(IRPTableLayout query);
	/**
 	 * Removes any element type filters that you defined to limit the search to certain element types. After calling this method, the search will be applied to all model element types.
 	 * @throws RhapsodyRuntimeException
 	 */
	public void removeFilterElementTypes();
	/**
 	 * Removes reference search criterion that was defined for the search query.
 	 * @throws RhapsodyRuntimeException
 	 */
	public void removeFilterReferences();
	/**
 	 * Removes any element field filters that you defined to limit the search to certain element fields, for example, model element descriptions. After calling this method, the search will be applied to all model element fields.
 	 * @throws RhapsodyRuntimeException
 	 */
	public void removeFilterSearchInFields();
	/**
 	 * Removes any stereotype filter that was defined to limit the search to model elements that have certain stereotypes applied to them.
 	 * @throws RhapsodyRuntimeException
 	 */
	public void removeFilterStereotypes();
	/**
 	 * Removes the subquery criteria that were specified for the search.
 	 * @throws RhapsodyRuntimeException
 	 */
	public void removeFilterSubQueries();
	/**
 	 * Removes the specified subquery from the search.
 	 * @param subQuery the subquery that should be removed from the list of subqueries for the search
 	 * @throws RhapsodyRuntimeException
 	 */
	public int removeFilterSubQuery(IRPTableLayout subQuery);
	/**
 	 * Removes the tag name and tag value criteria that were defined for the search query.
 	 * @throws RhapsodyRuntimeException
 	 */
	public void removeFilterTag();
	/**
 	 * Removes the specified model element from the scope for the search.
 	 * @param scopeElement the model element that should be removed from the scope of the search
 	 * @throws RhapsodyRuntimeException
 	 */
	public int removeSearchScopeElement(IRPModelElement scopeElement);
	/**
 	 * Removes the specified view from the list of views to be searched for the search text. This method can be used in conjunction with getViewCount and getView to loop through the views in the list and remove a specific one.
 	 * @param Index the index of the view in the list of views to search
 	 * @throws RhapsodyRuntimeException
 	 */
	public void removeView(int Index);
	/**
 	 * Resets the search scope to include the entire project, or all projects if multiple projects are open.
 	 * @throws RhapsodyRuntimeException
 	 */
	public void resetSearchScope();
	/**
 	 * Saves the search query object that you defined as a query in your model.
 	 * @param queryOwner the model element under which the new query should be created
 	 * @return the new query element that was created
 	 * @throws RhapsodyRuntimeException
 	 */
	public IRPTableLayout saveAsQuery(IRPPackage queryOwner);
	/**
 	 * Sets criteria for the search based on an element's references.
 	 * @param quantityOperator if you are specifying a number of references as a criterion, use one of the values defined in {@link IRPSearchQuery.References.QuantityOperator} to specify whether the criterion should be exactly that number of references, less than that number, or more than that number
 	 * @param numberOfReferences the number of references that should be used as a search criterion
 	 * @param relationKind use one of the values defined in {@link IRPSearchQuery.References.RelationKind} to specify the type of references that are to be used as a search criterion, for example, aggregates or incoming relations
 	 * @param typeOfReferencedElements can be used to specify a model element type to further limit the reference criterion, for example, find model elements that have aggregates of type "Attribute". The strings to use for this parameter can be found in the file metaclasses.txt in the Doc directory of the Rhapsody installation. You can also use the names of any "new terms" in your project.
 	 * @param stereotypeOfReferencedElements use this parameter to specify that the reference criterion should be limited to references to elements that have a specific stereotype applied to them
 	 * @param nameOfReferencedElements use this parameter to specify that the reference criterion should be limited to references to elements with a specific name
 	 * @param includeReferencedElementsInSearchResults use 1 to specify that the referenced elements included in the search criterion should also be displayed in the search results, 0 otherwise. For example, if you searched for classes that have aggregates of type "Attribute" with the stereotype Web Managed applied to them, and you used 1 for this parameter, the results will list the classes found as well as their attributes that have the Web Managed stereotype.
 	 */
	public void setFilterReference(String quantityOperator, int numberOfReferences, String relationKind, String typeOfReferencedElements, String stereotypeOfReferencedElements, String nameOfReferencedElements, int includeReferencedElementsInSearchResults);
	/**
 	 * Sets tag name and tag value criteria for the search query.
 	 * @param tagName the text to use for the tag name criterion
 	 * @param tagValue the text to use for the tag value criterion
 	 * @param matchCase use 1 to require an exact match for the tag name and tag value search criteria, in terms of upper and lower case, use 0 otherwise
 	 * @param matchWholeWord use 1 to require a whole word match for the tag name and tag value search criteria, use 0 otherwise 
 	 * @param findAs use one of the constants defined in the class {@link SearchFindAsEnum} to indicate the type of search that should be used for the tag name and tag value search criteria. For example, use SearchFindAsEnum.RP_SEARCH_WILDCARD for a wildcard search or SearchFindAsEnum.RP_SEARCH_REGEX for a regular expression search. If you want to search for elements that have an empty string for a tag value, use SearchFindAsEnum.RP_SEARCH_EMPTY_ONLY.
 	 * @throws RhapsodyRuntimeException
 	 */
	public void setFilterTag(String tagName, String tagValue, int matchCase, int matchWholeWord, char findAs);
	/**
 	 * Returns indication of how the specified subqueries are to be combined in the search
 	 * @return indication of how the subqueries are to be combined in the search - will be one of the constants defined in the class {@link IRPSearchQuery.SubQueriesOperator}, for example IRPSearchQuery.SubQueriesOperator.AND.
 	 */
	public String getFilterSubQueriesOperator();
	/**
 	 * Checks whether the tag criterion set for a search is limited to only local tags
 	 * @return 1 if the tag criterion is limited to local tags only, 0 otherwise
 	 * @throws RhapsodyRuntimeException
 	 */
	public int getFilterTagLocalOnly();
	/**
 	 * Checks whether the search is limited to model elements that are saved units.
 	 * @return 1 if the search is limited to saved units, 0 otherwise
 	 * @throws RhapsodyRuntimeException
 	 */
	public int getFilterUnitsOnly();
	/**
 	 * Returns the method that was specified for handling unresolved elements in the search. The value returned will be one of the constants from the class {@link IRPSearchQuery.UnresolvedKind}.
 	 * @return the method that was specified for handling unresolved elements in the search
 	 */
	public String getFilterUnresolvedKind();
	/**
 	 * Checks whether the scope of the search is to include the descendants of the elements specified for the scope.
 	 * @return 1 if the scope of the search is to include the descendants of the elements specified for the scope, 0 otherwise
 	 * @throws RhapsodyRuntimeException
 	 */
	public int getIncludeDescendants();
	/**
 	 * Returns the name of the interface (IRPSearchQuery).
 	 * @return the name of the interface (IRPSearchQuery)
 	 * @throws RhapsodyRuntimeException
 	 */
	public String getInterfaceName();
	/**
 	 * Checks whether an exact match was specified for the query in terms of upper and lower case.
 	 * @return 1 if an exact match was specified in terms of upper and lower case, 0 otherwise
 	 * @throws RhapsodyRuntimeException
 	 */
	public int getMatchCase();
	/**
 	 * Checks whether the query is to return the model elements that match the criteria specified, or the model elements that do not match the criteria specified.
 	 * @return 1 if the query is to return the model elements that match the criteria specified, 0 if the query is to return the model elements that do not match the specified criteria
 	 * @throws RhapsodyRuntimeException
 	 */
	public int getMatchSpecifiedCriteria();
	/**
 	 * Checks whether a whole word match was specified for the search.
 	 * @return 1 if a whole word match was specified, 0 otherwise
 	 * @throws RhapsodyRuntimeException
 	 */
	public int getMatchWholeWord();
	/**
 	 * Returns the type of search that was specified for the search text - regular text, wildcard, regular expression, or empty string.
 	 * @return the type of search that was specified for the search text - will be one of the constants defined in the class {@link SearchFindAsEnum}, for example SearchFindAsEnum.RP_SEARCH_WILDCARD for a wildcard search or SearchFindAsEnum.RP_SEARCH_REGEX for a regular expression search.
 	 * @throws RhapsodyRuntimeException
 	 */
	public char getSearchFindAsOption();
	/**
 	 * @deprecated This method, used to return the scope specified for the search, was introduced when Rhapsody only allowed you to specify a single element as the scope. Now that Rhapsody allows you to specify a list of such elements, you should use the method {@link #getSearchScopeElements}.
 	 */
	public IRPModelElement getSearchScopeObject();
	/**
 	 * Returns the text that was specified as the text to search for.
 	 * @return the text to search for
 	 * @throws RhapsodyRuntimeException
 	 */
	public String getSearchText();
	/**
 	 * Checks whether the query specifies that the search results should also include model elements that were found by the search but are not referenced in any of the views that you specified.
 	 * @return 1 if the query specified that the search results should also include model elements that were found by the search but are not referenced in any of the views that you specified, 0 otherwise
 	 * @throws RhapsodyRuntimeException
 	 */
	public int getViewIncludeModelElements();
	/**
 	 * Returns indication of which views (diagrams, tables, and matrices) are supposed to be searched.
 	 * @return indication of which views are supposed to be searched - will be one of the constants defined in the class {@link IRPSearchQuery.ViewsToSearch}, for example IRPSearchQuery.ViewsToSearch.OPEN.
 	 */
	public String getViewsToSearch();
	/**
 	 * Specify how the various subqueries specified should be combined - as an AND operation or an OR operation
 	 * @param filterSubQueriesOperator use one of the constants defined in the class {@link IRPSearchQuery.SubQueriesOperator} to indicate how the specified subqueries should be combined, for example IRPSearchQuery.SubQueriesOperator.AND
 	 */
	public void setFilterSubQueriesOperator(String filterSubQueriesOperator);
	/**
 	 * Specifies whether the tag criterion for a search should be limited to only local tags.
 	 * @param filterTagLocalOnly use 1 to specify that the tag criterion should be limited to only local tags, 0 otherwise
 	 * @throws RhapsodyRuntimeException
 	 */
	public void setFilterTagLocalOnly(int filterTagLocalOnly);
	/**
 	 * Specifies whether the search should be limited to model elements that are saved units.
 	 * @param filterUnitsOnly use 1 to specify that the search should be limited to model elements that are saved units, 0 otherwise
 	 * @throws RhapsodyRuntimeException
 	 */
	public void setFilterUnitsOnly(int filterUnitsOnly);
	/**
 	 * Specifies how unresolved elements should be handled in the search.
 	 * @param filterUnresolvedKind how unresolved elements should be handled in the search. The value of the parameter should be one of the constants from the class {@link IRPSearchQuery.UnresolvedKind}.
 	 */
	public void setFilterUnresolvedKind(String filterUnresolvedKind);
	/**
 	 * Specifies whether the scope for the search should include the descendants of the elements specified for the scope, for example, the subpackages of a package that was added to the scope.
 	 * @param includeDescendants use 1 if you want the search scope to include the descendants of the specified elements, 0 otherwise
 	 * @throws RhapsodyRuntimeException
 	 */
	public void setIncludeDescendants(int includeDescendants);
	/**
 	 * Specifies whether the search should require an exact match in terms of upper and lower case.
 	 * @param matchCase use 1 to specify that an exact match is required in terms of upper and lower case, 0 otherwise
 	 * @throws RhapsodyRuntimeException
 	 */
	public void setMatchCase(int matchCase);
	/**
 	 * Specifies whether the query should return the model elements that match the criteria specified, or the model elements that do not match the criteria specified.
 	 * @param matchSpecifiedCriteria use 1 if you want the query to return the model elements that match the criteria specified, use 0 if you want the query to return the model elements that do not match the criteria specified
 	 * @throws RhapsodyRuntimeException
 	 */
	public void setMatchSpecifiedCriteria(int matchSpecifiedCriteria);
	/**
 	 * Specifies whether the search should require whole word matches.
 	 * @param matchWholeWord use 1 to specify that a whole word match is required, 0 otherwise
 	 * @throws RhapsodyRuntimeException
 	 */
	public void setMatchWholeWord(int matchWholeWord);
	/**
 	 * Sets the type of search that should be used for the search text - regular text, wildcard, regular expression, or empty string.
 	 * @param searchFindAsOption use one of the constants defined in the class {@link SearchFindAsEnum} to indicate the type of search that should be used for the search text. For example, use SearchFindAsEnum.RP_SEARCH_WILDCARD for a wildcard search or SearchFindAsEnum.RP_SEARCH_REGEX for a regular expression search. If you want to search for elements that have an empty string in certain fields, use SearchFindAsEnum.RP_SEARCH_EMPTY_ONLY.
 	 * @throws RhapsodyRuntimeException
 	 */
	public void setSearchFindAsOption(char searchFindAsOption);
	/**
 	 * @deprecated This method, used to set the scope for the search, was introduced when Rhapsody only allowed you to specify a single element as the scope. Now that Rhapsody allows you to specify a list of such elements, you should use the method {@link #addSearchScope}.
 	 */
	public void setSearchScopeObject(IRPModelElement searchScopeObject);
	/**
 	 * Specifies the text that should be searched for.
 	 * @param searchText the text that should be searched for
 	 * @throws RhapsodyRuntimeException
 	 */
	public void setSearchText(String searchText);
	/**
 	 * Specifies whether the search results should also include model elements that were found by the search but are not referenced in any of the views that you specified.
 	 * @param viewIncludeModelElements use 1 to specify that the search results should also include model elements that were found by the search but are not referenced in any of the views that you specified, 0 otherwise
 	 * @throws RhapsodyRuntimeException
 	 */
	public void setViewIncludeModelElements(int viewIncludeModelElements);
	/**
 	 * Specifies which views (tables, matrices, and diagrams) should be searched - all, none, all open, or just the views that were specified with the methods addDiagramToViewsList, addTableToViewsList, and addMatrixToViewsList.
 	 * @param viewsToSearch use one of the constants defined in the class {@link IRPSearchQuery.ViewsToSearch} to indicate which views should be searched, for example IRPSearchQuery.ViewsToSearch.ALL
 	 * @throws RhapsodyRuntimeException
 	 */
	public void setViewsToSearch(String viewsToSearch);
	
	/** Constant values used with elements of this type **/
	public static final class SearchInField
	{
		public static final	 String		OPERATION_BODIES		= "Operation bodies";
		public static final	 String		TRANSITION_LABEL		= "Transition label(action,guard,trigger)";
		public static final	 String		TAG_VALUE		= "Tag value";
		public static final	 String		TYPE_DECLARATIONS_AND_REFERENCES		= "Type declarations and references";
		public static final	 String		CONFIGURATION_INITIALIZATION		= "Configuration initialization";
		public static final	 String		MULTIPLICITY		= "Multiplicity";
		public static final	 String		LOCALLY_OVERRIDDEN_PROPERTY		= "Locally overridden property";
		public static final	 String		DESCRIPTIONS		= "Descriptions";
		public static final	 String		COMMENT_SPECIFICATION		= "Comment specification";
		public static final	 String		CONSTRAINT_SPECIFICATION		= "Constraint specification";
		public static final	 String		REQUIREMENT_SPECIFICATION		= "Requirement specification";
		public static final	 String		NOTES_AND_TEXT		= "Notes and Text";
		public static final	 String		LABEL		= "Label";
		public static final	 String		INITIAL_VALUE		= "Initial value(attribute,argument)";
		public static final	 String		ENUMERATION_LITERAL_VALUE		= "Enumerationliteral value";
		public static final	 String		REQUIREMENT_ID		= "Requirement ID";
		public static final	 String		NAME		= "Name";
		public static final	 String		TEXT_FRAGMENT		= "TextFragment";
		public static final	 String		STEREOTYPE		= "Stereotype";
		public static final	 String		GROUP_ELEMENT_NAME		= "<Element Name>";
		public static final	 String		GROUP_CODE		= "<User code (Operation bodies, actions, etc.)>";
		public static final	 String		GROUP_OTHER_TEXT		= "<Other text (Descreptions, label, specification, etc.)>";
		public static final	 String		GROUP_ALL		= "<All>";
	}
	public static final class UnresolvedKind
	{
		public static final	 String		IGNORE_UNRESOLVED		= "Ignore unresolved";
		public static final	 String		SHOW_UNRESOLVED		= "Show unresolved";
		public static final	 String		ONLY_UNRESOLVED_OR_UNLOADED		= "Only unresolved or unloaded";
	}
	public static final class SubQueriesOperator
	{
		public static final	 String		AND		= "And";
		public static final	 String		OR		= "Or";
	}
	public static final class References
	{
		public static final class RelationKind
		{
			public static final	 String		UNDEFINED_RELATION		= "Undefined relation";
			public static final	 String		INCOMING_RELATION		= "Incoming relation";
			public static final	 String		OUTGOING_RELATION		= "Outgoing relation";
			public static final	 String		AGGREGATE		= "Aggregate";
			public static final	 String		REFERENCE		= "Reference";
			public static final	 String		DIAGRAM_ELEMENT		= "Diagram element";
		}
		public static final class QuantityOperator
		{
			public static final	 String		MORE_THAN		= "More than";
			public static final	 String		LESS_THAN		= "Less than";
			public static final	 String		EXACTLY		= "Exactly";
		}
	}
	public static final class ViewsToSearch
	{
		public static final	 String		NONE		= "None";
		public static final	 String		OPEN		= "Open";
		public static final	 String		ALL		= "All";
		public static final	 String		DETAILED		= "Detailed";
	}



}

