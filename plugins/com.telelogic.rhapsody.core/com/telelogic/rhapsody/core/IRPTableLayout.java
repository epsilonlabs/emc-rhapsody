
//Licensed Materials - Property of IBM 
//(c) Copyright IBM Corporation 2006, 2018. All Rights Reserved.

package com.telelogic.rhapsody.core;



public interface IRPTableLayout  extends IRPUnit {
	/**
 	 * Adds a new column to the table layout.
 	 * @param type the column Type (equivalent to Type field in the UI) - the valid values for this parameter are the constants that are defined in the class {@link Column IRPTableLayout.Column}, for example, {@link Column#ANNOTATION_ATTRIBUTE IRPTableLayout.Column.ANNOTATION_ATTRIBUTE}.
 	 * @param Property the column Property (equivalent to Property field in the UI) - the valid values for this parameter are the constants defined in the classes nested beneath the class IRPTableLayout.Column. The nested class to use depends upon the value used for the "type" parameter.<BR/>
 	 * For example, if the value of the "type" parameter is {@link Column#ANNOTATION_ATTRIBUTE IRPTableLayout.Column.ANNOTATION_ATTRIBUTE}, the valid values for the "Property" parameter are the constants defined in the nested class {@link AnnotationAttribute IRPTableLayout.Column.AnnotationAttribute},
 	 * such as {@link AnnotationAttribute#ID IRPTableLayout.Column.AnnotationAttribute.ID} and {@link AnnotationAttribute#SPECIFICATION IRPTableLayout.Column.AnnotationAttribute.SPECIFICATION}.
 	 * <p>
 	 * However, if you specified TAG, TAG_EDIT, TAG_EDIT_STRICT, or USER_DEFINED_METHOD as the "type" parameter, you must provide a string that reflects the full path of the relevant tag or the name of the relevant plugin method.
 	 * <p>
 	 * For TAG, TAG_EDIT, and TAG_EDIT_STRICT, use the string that is returned when you call the method getFullPathName for the tag.
 	 * <p>
 	 * If you  specified USER_DEFINED_METHOD as the type, the Property parameter should take the form pluginName.method. (If you do not specify the plugin name, all plugins will be searched until a method with the specified name is found).
 	 * @param ColumnName the text to use as the heading for the column
 	 */
	public void addColumn(String type, String Property, String ColumnName);
	/**
 	 * Adds a new column to the table layout. Differs from the addColumn method in that it allows you to also specify a label from a context pattern and it returns the index of the new column added.
 	 * @param type the type to use for the column (one of the constants defined in the class {@link IRPTableLayout.Column}, for example, IRPTableLayout.Column.GENERAL_ATTRIBUTE)
 	 * @param Property the Property to use for the specified column. The values that can be used for this parameter are the constants defined in the classes nested under {@link IRPTableLayout.Column}, for example, IRPTableLayout.Column.GeneralAttribute.NAME. Note that the Property must match the column type. For example, if the type of the column was set to IRPTableLayout.Column.ANNOTATION_ATTRIBUTE, the available values for the Property of the column are the constants defined in the class IRPTableLayout.Column.AnnotationAttribute, such as IRPTableLayout.Column.AnnotationAttribute.ID and IRPTableLayout.Column.AnnotationAttribute.SPECIFICATION.
 	 * @param ColumnName the text to use as the heading for the column
 	 * @param Context a label from the context pattern that was defined. If you do not want to specify a context pattern label, use an empty string for this parameter.
 	 * @return the index of the new column that was created (index of first column is 0)
 	 * @throws RhapsodyRuntimeException
 	 */
	public int addColumnEx(String type, String Property, String ColumnName, String Context);
	/**
 	 * Checks whether the first column of the layout includes controls for collapsing and expanding rows that have the same value in the first column.
 	 * @return 1 if the first column includes collapse/expand controls, 0 otherwise
 	 */
	public int getCollapseFirstColumn();
	/**
 	 * Returns the context pattern label that was specified for the column.
 	 * @param Index the index of the column (index of first column is 0)
 	 * @return the context pattern label that was specified for the column
 	 * @throws RhapsodyRuntimeException
 	 */
	public String getColumnContext(int Index);
	/**
 	 * Returns the default width that was defined for the specified column.
 	 * @param Index the index of the column whose default width should be returned (index of first column is 0)
 	 * @return the default width defined for the specified column (in pixels)
 	 * @throws RhapsodyRuntimeException
 	 */
	public int getColumnDefaultWidth(int Index);
	/**
 	 * Checks whether the user-defined picker for the specified column includes the New option in its list.
 	 * @param Index the index of the column (index of first column is 0)
 	 * @return {@code 1} if the picker includes the New option, {@code 0} if it does not
 	 * @throws RhapsodyRuntimeException
 	 */
	public int getColumnImplementationAllowNew(int Index);
	/**
 	 * Checks whether the user-defined picker for the specified column includes the Select option in its list.
 	 * @param Index the index of the column (index of first column is 0)
 	 * @return {@code 1} if the picker includes the Select option, {@code 0} if it does not
 	 * @throws RhapsodyRuntimeException
 	 */
	public int getColumnImplementationAllowSelect(int Index);
	/**
 	 * Returns the type of information that is displayed in the column's cells - string, model element, or list of model elements. The value returned will be one of the constants defined in the class {@link IRPTableLayout.Column.ImplementationCellType}.
 	 * @param Index the index of the column (index of first column is 0)
 	 * @return the type of information that is displayed in the column's cells (one of the constants defined in the class IRPTableLayout.Column.ImplementationCellType, for example, IRPTableLayout.Column.ImplementationCellType.MODEL_ELEMENT)
 	 * @throws RhapsodyRuntimeException
 	 */
	public String getColumnImplementationCellType(int Index);
	/**
 	 * Returns the type of element information that is displayed when the cell value type is set to model element or list of model elements. The value returned will be one of the constants defined in the class {@link IRPTableLayout.Column.GeneralAttribute}.
 	 * @param Index the index of the column (index of first column is 0)
 	 * @return the type of element information that is displayed when the cell value type is set to model element or list of model elements (one of the constants defined in the class IRPTableLayout.Column.GeneralAttribute, for example, IRPTableLayout.Column.GeneralAttribute.NAME)
 	 * @throws RhapsodyRuntimeException
 	 */
	public String getColumnImplementationDisplayProperty(int Index);
	/**
 	 * Returns the Java code for the getter for the cells in the specified column.
 	 * @param Index the index of the column (index of first column is 0)
 	 * @return the Java code for the getter for the cells in the column
 	 * @throws RhapsodyRuntimeException
 	 */
	public String getColumnImplementationGetterCode(int Index);
	/**
 	 * For columns that use customized cell behavior, this method returns the list of imports specified for the column.
 	 * @param Index the index of the column (index of first column is 0)
 	 * @return comma-separated list of the imports specified for the column
 	 * @throws RhapsodyRuntimeException
 	 */
	public String getColumnImplementationImports(int Index);
	/**
 	 * Returns the Java code for the picker for the cells in the specified column.
 	 * @param Index the index of the column (index of first column is 0)
 	 * @return the Java code for the picker for the cells in the column
 	 * @throws RhapsodyRuntimeException
 	 */
	public String getColumnImplementationPickerCode(int Index);
	/**
 	 * Returns the Java code for the setter for the cells in the specified column.
 	 * @param Index the index of the column (index of first column is 0)
 	 * @return the Java code for the setter for the cells in the column
 	 * @throws RhapsodyRuntimeException
 	 */
	public String getColumnImplementationSetterCode(int Index);
	 /**
 	 * Returns the name of the specified column.
 	 * @param Index the index of the column whose name should be returned (index of first column is 0)
 	 * @return the name of the specified column
 	 * @throws RhapsodyRuntimeException
 	 */
	public String getColumnName(int Index);
	/**
 	 * Returns the Property of the specified column. Corresponds to the Property field on the Columns tab for table layouts. The value returned will be one of the constants defined in the classes nested under {@link IRPTableLayout.Column}, for example, IRPTableLayout.Column.GeneralAttribute.NAME.
 	 * @param Index the index of the column (index of first column is 0)
 	 * @return the Property of the specified column. Value returned will be one of the constants defined in the classes nested under IRPTableLayout.Column, for example, IRPTableLayout.Column.GeneralAttribute.NAME
 	 * @throws RhapsodyRuntimeException
 	 */
	public String getColumnProperty(int Index);
	/**
 	 * Returns the type of the specified table column. The value returned will be one of the constants defined in the class {@link IRPTableLayout.Column}.
 	 * @param Index the index of the column (index of first column is 0)
 	 * @return the type of the table column (one of the constants defined in the class IRPTableLayout.Column, for example, IRPTableLayout.Column.ANNOTATION_ATTRIBUTE)
 	 * @throws RhapsodyRuntimeException
 	 */
	public String getColumnType(int Index);
	/**
 	 * @return collection of columns
 	 * @throws RhapsodyRuntimeException
 	 */
	public IRPCollection getColumns();
	/**
 	 * Returns a collection of the element types that were specified to be displayed in the table. The collection consists of strings (from the list of types displayed on the ElementTypes tab of the Features window for table layouts).
 	 * @return the element types that were specified to be displayed in the table
 	 */
	public IRPCollection getElementTypes();
	/**
 	 * For "relation tables", returns a collection of the element types specified as the "from" element types. The collection consists of strings (from the list of types displayed on the From Element Types tab of the Features window for table layouts).
 	 * @return the types specified as the "from" element types for the table layout
 	 */
	public IRPCollection getFromElementTypes();
	/**
 	 * For "relation tables", returns the query that was specified to determine the "from" element types.
 	 * @return the query that was specified to determine the "from" element types for the table layout
 	 */
	public IRPTableLayout getFromElementTypesQueryToUse();
	/**
 	 * For "relation tables", checks whether a query or collection of element types was used to specify the "from" element types.
 	 * @return one of the constants contained in the class IRPTableLayout.QueryOrElementsList: QUERY if a query was used, ELEMENTS_LIST if a collection of element types was used.
 	 */
	public int getFromElementTypesUseQueryOrElementsList();
	/**
 	 * Checks whether the table was defined as a "relation table".
 	 * @return 1 if the table was defined as a "relation table", 0 otherwise
 	 */
	public int getRelationTable();
	/**
	 * method GetResultList
	 * @throws RhapsodyRuntimeException
	 */
	public IRPCollection getResultList(IRPModelElement scope);
	/**
 	 * For "relation tables", returns a collection of the element types specified as the "to" element types. The collection consists of strings (from the list of types displayed on the To Element Types tab of the Features window for table layouts).
 	 * @return the types specified as the "to" element types for the table layout
 	 */
	public IRPCollection getToElementTypes();
	/**
 	 * For "relation tables", returns the query that was specified to determine the "to" element types.
 	 * @return the query that was specified to determine the "to" element types for the table layout
 	 */
	public IRPTableLayout getToElementTypesQueryToUse();
	/**
 	 * For "relation tables", checks whether a query or collection of element types was used to specify the "to" element types.
 	 * @return one of the constants contained in the class IRPTableLayout.QueryOrElementsList: QUERY if a query was used, ELEMENTS_LIST if a collection of element types was used.
 	 */
	public int getToElementTypesUseQueryOrElementsList();
	/**
 	 * Removes the specified column from the table layout.
 	 * @param Index the index representing the position of the column in the table. The index for the fist column in the table is 0.
 	 */
	public void removeColumn(int Index);
	/**
 	 * Specifies whether or not the fist column should include controls for collapsing and expanding rows that have the same value in the first column.
 	 * @param collapse use 1 if the first column should include collapse/expand controls, 0 otherwise.
 	 */
	public void setCollapseFirstColumn(int collapse);
	/**
 	 * If you have defined a context pattern, this method can be used to specify a label from the context pattern, for the specified column.
 	 * @param Index the index of the column (index of first column is 0)
 	 * @param Context a label from the context pattern that was defined
 	 * @throws RhapsodyRuntimeException
 	 */
	public void setColumnContext(int Index, String Context);
	 /**
 	 * Sets the default width of the specified column. If a user double-clicks the column border after manually changing the width, the width will return to this value.
 	 * @param Index the index of the column whose default width should be set (index of first column is 0)
 	 * @param width the default width to use for the column (in pixels)
 	 * @throws RhapsodyRuntimeException
 	 */
	public void setColumnDefaultWidth(int Index, int width);
	/**
 	 * For columns that use customized cell behavior, this method can be used to include the New option in the list provided by the picker.
 	 * @param Index the index of the column (index of first column is 0)
 	 * @param value use {@code 1} if the New option should be included in the list, {@code 0} if it should not
 	 */
	public void setColumnImplementationAllowNew(int Index, int value);
	/**
 	 * For columns that use customized cell behavior, this method can be used to include the Select option in the list provided by the picker.
 	 * @param Index the index of the column (index of first column is 0)
 	 * @param value use {@code 1} if the Select option should be included in the list, {@code 0} if it should not
 	 * @throws RhapsodyRuntimeException
 	 */
	public void setColumnImplementationAllowSelect(int Index, int value);
	/**
 	 * For columns that use customized cell behavior, this method is used to specify the type of information that will be displayed in the column's cells - string, model element, or list of model elements.
 	 * @param Index the index of the column (index of first column is 0)
 	 * @param cellType the type of information that will be displayed in the column's cells. The valid values for this parameter are the constants that are defined in the class {@link IRPTableLayout.Column.ImplementationCellType}, for example IRPTableLayout.Column.ImplementationCellType.MODEL_ELEMENT.
 	 * @throws RhapsodyRuntimeException
 	 */
	public void setColumnImplementationCellType(int Index, String cellType);
	/**
 	 * For columns that use customized cell behavior, this method is used to specify the type of element information that should be displayed when the cell value type is set to model element or list of model elements, for example, the name or value of the element.
 	 * @param Index the index of the column (index of first column is 0)
 	 * @param propertyToDisplay the type of element information that should be displayed for the element or elements in the cell. The valid values for this parameter are the constants defined in the class {@link IRPTableLayout.Column.GeneralAttribute}, such as IRPTableLayout.Column.GeneralAttribute.NAME.
 	 * @throws RhapsodyRuntimeException
 	 */
	public void setColumnImplementationDisplayProperty(int Index, String propertyToDisplay);
	/**
 	 * For columns that use customized cell behavior, this method is used to specify the Java code for the getter for the cells in the column.
 	 * @param Index the index of the column (index of first column is 0)
 	 * @param code the Java code to use for the getter
 	 * @throws RhapsodyRuntimeException
 	 */
	public void setColumnImplementationGetterCode(int Index, String code);
	/**
 	 * For columns that use customized cell behavior, this method can be used to specify classes required by your code. Corresponds to the Imports field in the User Defined Implementation dialog. The list of imports should be comma-separated.
 	 * @param Index the index of the column (index of first column is 0)
 	 * @param imports a comma-separated list of classes to import
 	 * @throws RhapsodyRuntimeException
 	 */
	public void setColumnImplementationImports(int Index, String imports);
	/**
 	 * For columns that use customized cell behavior, this method is used to specify the Java code for the picker for the cells in the column.
 	 * @param Index the index of the column (index of first column is 0)
 	 * @param code the Java code to use for the picker
 	 * @throws RhapsodyRuntimeException
 	 */
	public void setColumnImplementationPickerCode(int Index, String code);
	/**
 	 * For columns that use customized cell behavior, this method is used to specify the Java code for the setter for the cells in the column.
 	 * @param Index the index of the column (index of first column is 0)
 	 * @param code the Java code to use for the setter
 	 * @throws RhapsodyRuntimeException
 	 */
	public void setColumnImplementationSetterCode(int Index, String code);
	 /**
 	 * Sets the name of the specified column.
 	 * @param Index the index of the column whose name should be set (index of first column is 0)
 	 * @param name the name to use for the column
 	 * @throws RhapsodyRuntimeException
 	 */
	public void setColumnName(int Index, String name);
	/**
 	 * Sets the Property of the specified column. Corresponds to the Property field on the Columns tab for table layouts.
 	 * @param Index the index of the column (index of first column is 0)
 	 * @param Property the Property to use for the specified column. The values that can be used for this parameter are the constants defined in the classes nested under {@link IRPTableLayout.Column}, for example, IRPTableLayout.Column.GeneralAttribute.NAME. Note that the Property must match the column type. For example, if the type of the column was set to IRPTableLayout.Column.ANNOTATION_ATTRIBUTE, the available values for the Property of the column are the constants defined in the class IRPTableLayout.Column.AnnotationAttribute, such as IRPTableLayout.Column.AnnotationAttribute.ID and IRPTableLayout.Column.AnnotationAttribute.SPECIFICATION.
 	 * @throws RhapsodyRuntimeException
 	 */
	public void setColumnProperty(int Index, String Property);
	/**
 	 * Sets the type of the specified table column. The type must be one of the constants defined in the class {@link IRPTableLayout.Column}.
 	 * @param Index the index of the column (index of first column is 0)
 	 * @param type the type to use for the column (one of the constants defined in the class IRPTableLayout.Column, for example, IRPTableLayout.Column.GENERAL_ATTRIBUTE)
 	 * @throws RhapsodyRuntimeException
 	 */
	public void setColumnType(int Index, String type);
	/**
 	 * Specifies the list of element types that should be displayed in the table. The parameter must be a collection of strings (from the list of types displayed on the ElementTypes tab of the Features window for table layouts).
 	 * @param elements the element types that should be displayed in the table
 	 */
	public void setElementTypes(IRPCollection elements);
	/**
 	 * For "relation tables", specifies the list of element types to use as the "from" element types. The parameter must be a collection of strings (from the list of types displayed on the From Element Types tab of the Features window for table layouts).
 	 * @param elements collection of element types to use as the "from" element types for the table layout
 	 */
	public void setFromElementTypes(IRPCollection elements);
	/**
 	 * For "relation tables", specifies the query to use to determine the "from" element types for the table layout.
 	 * @param query the query to use to determine the "from" element types for the table layout. To clear a previous query, use null for the parameter.
 	 */
	public void setFromElementTypesQueryToUse(IRPTableLayout query);
	/**
 	 * For "relation tables", specifies whether a query or collection of element types should be used to determine the "from" element types for the table layout.
 	 * @param queryOrElementsList one of the constants contained in the class IRPTableLayout.QueryOrElementsList: QUERY if a query should be used, ELEMENTS_LIST if a collection of element types should be used.
 	 */
	public void setFromElementTypesUseQueryOrElementsList(int queryOrElementsList);
	/**
 	 * Specifies whether the table should be defined as a "relation table".
 	 * @param relation use 1 if the table should be defined as a "relation table", 0 otherwise.
 	 */
	public void setRelationTable(int relation);
	/**
 	 * For "relation tables", specifies the list of element types to use as the "to" element types for the table layout. The parameter must be a collection of strings (from the list of types displayed on the To Element Types tab of the Features window for table layouts).
 	 * @param elements collection of element types to use as the "to" element types for the table layout
 	 */
	public void setToElementTypes(IRPCollection elements);
	/**
 	 * For "relation tables", specifies the query to use to determine the "to" element types for the table layout.
 	 * @param query the query to use to determine the "to" element types for the table layout. To clear a previous query, use null for the parameter.
 	 */
	public void setToElementTypesQueryToUse(IRPTableLayout query);
	/**
 	 * For "relation tables", specifies whether a query or collection of element types should be used to determine the "to" element types for the table layout.
 	 * @param queryOrElementsList one of the constants contained in the class IRPTableLayout.QueryOrElementsList: QUERY if a query should be used, ELEMENTS_LIST if a collection of element types should be used.
 	 */
	public void setToElementTypesUseQueryOrElementsList(int queryOrElementsList);
	 /**
 	 * Returns the number of columns in the table layout.
 	 * @return the number of columns in the table layout
 	 * @throws RhapsodyRuntimeException
 	 */
	public int getColumnCount();
	
	/** Constant values used with elements of this type **/
	/**
This class holds constant values to be used with addColumn method.
	**/
	public static final class Column
	{
		/**
Value used for Type parameter of addColumn method

		**/
		public static final	 String		GENERAL_ATTRIBUTE		= "General Attribute";
		/**
Value used for Type parameter of addColumn method

@deprecated
		**/
		public static final	 String		RELAION_ATTRIBUTE_FROM		= "Relation Attribute (From)";
		/**
Value used for Type parameter of addColumn method

@deprecated

		**/
		public static final	 String		RELAION_ATTRIBUTE_TO		= "Relation Attribute (To)";
		/**
Value used for Type parameter of addColumn method

		**/
		public static final	 String		REQUIREMENT_ATTRIBUTE		= "Requirement Attribute";
		/**
Value used for Type parameter of addColumn method
		**/
		public static final	 String		ANNOTATION_ATTRIBUTE		= "Annotation Attribute";
		/**
Value used for Type parameter of addColumn method

		**/
		public static final	 String		FLOW_ATTRIBUTE		= "Flow Attribute";
		/**
Value used for Type parameter of addColumn method

		**/
		public static final	 String		TAG		= "Tag";
		/**
Value used for Type parameter of addColumn method

		**/
		public static final	 String		TAG_EDIT		= "Tag";
		/**
Value used for Type parameter of addColumn method

		**/
		public static final	 String		TAG_EDIT_STRICT		= "Tag (Strict)";
		/**
Value used for Type parameter of addColumn method.

When using this value - for the "Type" parameter,
the "Property" parameter can be set by one of the values defined in IRPTableLayout.Column.DependsOn,
or by the name of Stereptype applicable to Dependency.

		**/
		public static final	 String		DEPENDS_ON		= "Depends On";
		/**
Value used for Type parameter of addColumn method.
When this value is used - the value for the property parameter can be set to the plugin method to be executed,
or to the contant defined in IRPTableLayout.Column.UserDefinedMethod.

		**/
		public static final	 String		USER_DEFINED_METHOD		= "User Defined Method";
		/**
Value used for Type parameter of addColumn method

		**/
		public static final	 String		RELATION_ATTRIBUTE_FROM		= "Relation Attribute (From)";
		/**
Value used for Type parameter of addColumn method

		**/
		public static final	 String		RELATION_ATTRIBUTE_TO		= "Relation Attribute (To)";
		/**
Value used for Type parameter of addColumn method
		**/
		public static final	 String		INSTANCE_SPECIFICATION_HIERARCHY		= "InstanceSpecificationHierarchy";
		/**
Value used for Type parameter of addColumn method
		**/
		public static final	 String		CONTEXT_PATTERN_HIERARCHY		= "ContextPatternHierarchy";
		/**
Contains values to be used for Property parameter of addColumn method, 
when AnnotationAttribute is selected for the Type parameter of addColumn method.
		**/
		public static final class AnnotationAttribute
		{
			/**
Value to be used for Property parameter of addColumn method, 
when AnnotationAttribute is selected for the Type parameter of addColumn method.

			**/
			public static final	 String		ID		= "ID";
			/**
Value to be used for Property parameter of addColumn method, 
when AnnotationAttribute is selected for the Type parameter of addColumn method.

			**/
			public static final	 String		SPECIFICATION		= "Specification";
		}
		/**
Contains values to be used for Property parameter of addColumn method, 
when FlowAttribute is selected for the Type parameter of addColumn method.

		**/
		public static final class FlowAttribute
		{
			/**
Value to be used for Property parameter of addColumn method, 
when FlowAttribute is selected for the Type parameter of addColumn method.

			**/
			public static final	 String		ITEM_FLOWS		= "Item Flows";
		}
		/**
Contains values to be used for Property parameter of addColumn method, 
when GeneralAttribute is selected for the Type parameter of addColumn method.

		**/
		public static final class GeneralAttribute
		{
			/**
Value to be used for Property parameter of addColumn method, 
when GeneralAttribute is selected for the Type parameter of addColumn method.
			**/
			public static final	 String		NAME		= "Name";
			/**
Value to be used for Property parameter of addColumn method, 
when GeneralAttribute is selected for the Type parameter of addColumn method.
			**/
			public static final	 String		LABEL		= "Label";
			/**
Value to be used for Property parameter of addColumn method, 
when GeneralAttribute is selected for the Type parameter of addColumn method.
			**/
			public static final	 String		OWNER		= "Owner";
			/**
Value to be used for Property parameter of addColumn method, 
when GeneralAttribute is selected for the Type parameter of addColumn method.
			**/
			public static final	 String		DESCRIPTION		= "Description";
			/**
Value to be used for Property parameter of addColumn method, 
when GeneralAttribute is selected for the Type parameter of addColumn method.
			**/
			public static final	 String		ELEMENT_TYPE		= "Element type";
			/**
Value to be used for Property parameter of addColumn method, 
when GeneralAttribute is selected for the Type parameter of addColumn method.
			**/
			public static final	 String		STEREOTYPES		= "Stereotypes";
			/**
Value to be used for Property parameter of addColumn method, 
when GeneralAttribute is selected for the Type parameter of addColumn method.

			**/
			public static final	 String		CLASSIFIER		= "Classifier";
			public static final	 String		FULL_PATH_NAME		= "Full path name";
			public static final	 String		VALUE		= "Value";
		}
		/**
Contains values to be used for Property parameter of addColumn method, 
when RelationAttributeFrom is selected for the Type parameter of addColumn method.

		**/
		public static final class RelationAttributeFrom
		{
			/**
Value to be used for Property parameter of addColumn method, 
when RelationAttributeFrom is selected for the Type parameter of addColumn method.
			**/
			public static final	 String		VIA_PORT		= "Via port";
			/**
Value to be used for Property parameter of addColumn method, 
when RelationAttributeFrom is selected for the Type parameter of addColumn method.
			**/
			public static final	 String		PORT_PROVIDED_INERFACE		= "Port provided interface";
			/**
Value to be used for Property parameter of addColumn method, 
when RelationAttributeFrom is selected for the Type parameter of addColumn method.
			**/
			public static final	 String		FROM_ELEMENT		= "From element";
			/**
Value to be used for Property parameter of addColumn method, 
when RelationAttributeFrom is selected for the Type parameter of addColumn method.

			**/
			public static final	 String		PORT_REQUIRED_INERFACE		= "Port required interface";
			/**
Value to be used for Property parameter of addColumn method, 
when RelationAttributeFrom is selected for the Type parameter of addColumn method.
			**/
			public static final	 String		PROVIDED_INERFACE_OPERATIONS		= "Provided interface operations";
			/**
Value to be used for Property parameter of addColumn method, 
when RelationAttributeFrom is selected for the Type parameter of addColumn method.
			**/
			public static final	 String		REQUIRED_INERFACE_OPERATIONS		= "Required interface operations";
		}
		/**
Contains values to be used for Property parameter of addColumn method, 
when RequirementAttribute is selected for the Type parameter of addColumn method.

		**/
		public static final class RequirementAttribute
		{
			/**
Value to be used for Property parameter of addColumn method, 
when RequirementAttribute is selected for the Type parameter of addColumn method.
			**/
			public static final	 String		ID		= "ID";
			/**
Value to be used for Property parameter of addColumn method, 
when RequirementAttribute is selected for the Type parameter of addColumn method.
			**/
			public static final	 String		SPECIFICATION		= "Specification";
			public static final	 String		LINK_TYPE		= "Link Type";
			public static final	 String		LINK_FROM		= "Link From";
			public static final	 String		LINK_FROM_FULLNAME		= "Link From FullName";
			public static final	 String		LINK_SUSPECT		= "Link Suspect";
		}
		/**
Contains values to be used for Property parameter of addColumn method, 
when RelationAttributeTo is selected for the Type parameter of addColumn method.

		**/
		public static final class RelationAttributeTo
		{
			/**
Value to be used for Property parameter of addColumn method, 
when RelationAttributeTo is selected for the Type parameter of addColumn method.
			**/
			public static final	 String		VIA_PORT		= "Via port";
			/**
Value to be used for Property parameter of addColumn method, 
when RelationAttributeTo is selected for the Type parameter of addColumn method.

			**/
			public static final	 String		PORT_PROVIDED_INERFACE		= "Port provided interface";
			/**
Value to be used for Property parameter of addColumn method, 
when RelationAttributeTo is selected for the Type parameter of addColumn method.
			**/
			public static final	 String		TO_ELEMENT		= "To element";
			/**
Value to be used for Property parameter of addColumn method, 
when RelationAttributeTo is selected for the Type parameter of addColumn method.
			**/
			public static final	 String		PORT_REQUIRED_INERFACE		= "Port required interface";
			/**
Value to be used for Property parameter of addColumn method, 
when RelationAttributeTo is selected for the Type parameter of addColumn method.
			**/
			public static final	 String		PROVIDED_INERFACE_OPERATIONS		= "Provided interface operations";
			/**
Value to be used for Property parameter of addColumn method, 
when RelationAttributeTo is selected for the Type parameter of addColumn method.
			**/
			public static final	 String		REQUIRED_INERFACE_OPERATIONS		= "Required interface operations";
		}
		public static final class ImplementationCellType
		{
			/**
Value to be used for cellType parameter of SetColumnImplementationCellType method.

			**/
			public static final	 String		STRING		= "String";
			/**
Value to be used for cellType parameter of SetColumnImplementationCellType method.
			**/
			public static final	 String		MODEL_ELEMENT		= "Model element";
			/**
Value to be used for cellType parameter of SetColumnImplementationCellType method.
			**/
			public static final	 String		LIST_OF_MODEL_ELEMENTS		= "List of model elements";
		}
		/**
Contains the pre-defined values to be used for Property parameter of addColumn method, 
when DependsOn is selected for the Type parameter of addColumn method.

Other legal values for this field are names of Streotypes applicable to Dependency.

		**/
		public static final class DependsOn
		{
			/**
Value to be used for Property parameter of addColumn method, 
when FlowAttribute is selected for the Type parameter of addColumn method.

			**/
			public static final	 String		DEPENDENCY		= "Dependency";
		}
		/**
		 * Contains values to be used for Property parameter of addColumn method, 
		 * when USER_DEFINED_METHOD is selected for the Type parameter of addColumn method.
		**/
		public static final class UserDefinedMethod
		{
			/**
			 * Use this value to declare that a dynamic java code was set to be executed for this column.
			**/
			public static final	 String		Implementation		= "Implementation...";
		}
	}
	/**
This class contains constant values for use with the methods setFromElementTypesUseQueryOrElementsList and setToElementTypesUseQueryOrElementsList.
	**/
	public static final class QueryOrElementsList
	{
		/**
When QUERY is used as the parameter for the methods setFromElementTypesUseQueryOrElementsList and setToElementTypesUseQueryOrElementsList, it indicates that a query is going to be used to determine the "from" element types or "to" element types for the relation table.

		**/
		public static final	 int		QUERY		= 1;
		/**
When ELEMENTS_LIST is used as the parameter for the methods setFromElementTypesUseQueryOrElementsList and setToElementTypesUseQueryOrElementsList, it indicates that elements selected in the element types list will be used to specify the "from" element types or "to" element types for the  relation table.
		**/
		public static final	 int		ELEMENTS_LIST		= 0;
	}



}

