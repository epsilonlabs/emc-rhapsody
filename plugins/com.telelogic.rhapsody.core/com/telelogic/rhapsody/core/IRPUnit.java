
//Licensed Materials - Property of IBM 
//(c) Copyright IBM Corporation 2006, 2018. All Rights Reserved.

package com.telelogic.rhapsody.core;


 /**
  * The IRPUnit interface represents model elements that can be saved as separate files.
  */
public interface IRPUnit  extends IRPModelElement {
	/**
 	 * Makes an editable copy of the unit in a different project.
 	 * @param parentInTarget the model element that will be the parent of the new unit in the target project
 	 * @return the editable unit that was created in the target project
 	 */
	public IRPUnit copyToAnotherProject(IRPModelElement parentInTarget);
	/**
 	 * Returns an indication of how the unit was added to the model. See {@link IRPApplication.AddToModel_Mode} for the possible values.
 	 * @return indication of how the unit was added to the model
 	 */
	public int getAddToModelMode();
	/**
 	 * Returns the header used by the Configuration Management tool for the unit.
 	 * @return the header used by the Configuration Management tool for the unit
 	 */
	public String getCMHeader();
	/**
 	 * Returns the configuration management state of the unit.
 	 * @return the configuration management state of the unit
 	 */
	public int getCMState();
	/**
 	 * Gets the name of the directory that contains the file used to store the unit. The string returned consists of the full path except for the name of the file itself.
 	 * @return the name of the directory that contains the file used to store the unit
 	 */
	public String getCurrentDirectory();
	/**
 	 * Gets the name of the file used to store the unit. The string returned consists only of the filename, not the entire path.
 	 * @return the name of the file used to store the unit
 	 */
	public String getFilename();
	/**
 	 * Checks whether the unit is going to be loaded the next time the model is loaded.
 	 * @return 1 if the unit is going to be loaded the next time the model is loaded, 0 if the unit is not going to be loaded
 	 */
	public int getIncludeInNextLoad();
	/**
 	 * Checks whether the unit is currently unloaded.
 	 * @return 1 if the unit is not currently loaded, 0 if it is currently loaded
 	 */
	public int getIsStub();
	/**
 	 * Gets the language of the unit.
 	 * @return the language of the unit
 	 */
	public String getLanguage();
	/**
 	 * Returns the time at which the file representing the unit was last modified.
 	 * @return the time at which the file representing the unit was last modified
 	 */
	public String getLastModifiedTime();
	/**
 	 * Returns a collection of any sub-elements of the unit that were saved as individual files.
 	 * @return any sub-elements of the unit that were saved as individual files
 	 */
	public IRPCollection getNestedSaveUnits();
	/**
 	 * Returns the number of sub-elements of the unit that were saved as individual files.
 	 * @return the number of sub-elements of the unit that were saved as individual files
 	 */
	public int getNestedSaveUnitsCount();
	/**
 	 * Returns a collection of any structure diagrams that are sub-elements of the unit. Used primarily for structure diagrams that belong to individual classes.
 	 * @return any structure diagrams that are sub-elements of the unit
 	 */
	public IRPCollection getStructureDiagrams();
	/**
 	 * Returns the path of the unit, including the filename.
 	 * @param bFullPath use 1 to specify that the full path should be returned, use 0 to specify that a relative path should be returned. For relative paths, the path returned is relative to the saved unit that owns this unit.
 	 * @return the path of the unit, including the filename
 	 * @throws RhapsodyRuntimeException
 	 */
	public String getUnitPath(int bFullPath);
	/**
 	 * Checks whether the file used to store the unit is read-only.
 	 * @return 1 if the file is read-only, 0 if the file is not read-only
 	 */
	public int isReadOnly();
	/**
 	 * Checks whether the unit was added to the model as a reference.
 	 * @return 1 if the unit was added to the model as a reference, 0 if it was not added as a reference
 	 */
	public int isReferenceUnit();
	/**
 	 * Checks whether the current IRPUnit object is saved in its own file. (Keep in mind that IRPUnit objects represent any element that can in theory be saved as a separate file, even if this is not the case for a specific element in your model.)
 	 * @return 1 if the unit is saved in its own file, 0 otherwise
 	 */
	public int isSeparateSaveUnit();
	/**
 	 * Loads the unit.
 	 * @param withSubs indication of whether the unit's subunits should be loaded as well (1 to load the subunits as well, 0 to load only the unit itself)
 	 * @return the unit that was loaded
 	 */
	public IRPUnit load(int withSubs);
	/**
 	 * Moves the unit to a different project, and adds a reference to it in the original project.
 	 * @param parentInTarget the model element that will be the parent of the new unit in the target project
 	 * @return the editable unit that was created in the target project
 	 */
	public IRPUnit moveToAnotherProjectLeaveAReference(IRPModelElement parentInTarget);
	/**
 	 * Creates a reference to the unit in a different project.
 	 * @param parentInTarget the model element that will be the parent of the reference (read-only) unit created in the target project
 	 * @return the reference (read-only) unit that was created in the target project
 	 */
	public IRPUnit referenceToAnotherProject(IRPModelElement parentInTarget);
	/**
 	 * Saves the unit.
 	 * @param withSubs indication of whether the unit's subunits should be saved as well (1 to save the subunits as well, 0 to save only the unit itself)
 	 */
	public void save(int withSubs);
	/**
 	 * Sets the Configuration Management tool header for the unit.
 	 * @param cMHeader the Configuration Management tool header to use for the unit
 	 */
	public void setCMHeader(String cMHeader);
	/**
 	 * Specifies the name that should be used for the file representing the unit. The string should only include the first part of the filename, Rhapsody handles the file extension. (Note that if you change the filename, the old file remains on disk.)
 	 * @param filename the name that should be used for the file representing the unit
 	 */
	public void setFilename(String filename);
	/**
 	 * Toggles whether the unit is going to be loaded the next time the model is loaded.
 	 * @param includeInNextLoad Use 1 to specify that the unit should be loaded the next time the model is loaded, 0 to specify that the unit should not be loaded
 	 */
	public void setIncludeInNextLoad(int includeInNextLoad);
	/**
 	 * Specifies the programming language that should be used when code is generated for the unit. This method can be used for mixed-language models.
 	 * @param newLanguage use one of the following strings: C++ or cpp, C, Java, Ada, C#
 	 * @param recursive use 1 if you want to set the language for all subunits of the element, otherwise use 0 
 	 * <pre> 
 	 * 		jeepUnit.setLanguage("cpp", 0);
 	 * </pre>
 	 */
	public void setLanguage(String newLanguage, int recursive);
	/**
 	 * Toggles the read-only status of the file used to store the unit.
 	 * @param pVal Use 1 to change the file to read-only, 0 to change the file to read/write
 	 */
	public void setReadOnly(int pVal);
	/**
 	 * Specifies whether the current IRPUnit object should be saved in its own file. (Keep in mind that IRPUnit objects represent any element that can in theory be saved as a separate file, even if this is not the case for a specific element in your model.)
 	 * @param pVal Use 1 to specify that the element should be saved in its own file. Use 0 to specify that the element should not be saved in its own file
 	 */
	public void setSeparateSaveUnit(int pVal);
	/**
 	 * Specifies the path that should be used to locate the unit when it is added to a model "By Reference".
 	 * @param newPath the path that should be used to locate the unit when it is added to a model "By Reference"
 	 */
	public void setUnitPath(String newPath);
	/**
 	 * Unloads the unit.
 	 */
	public void unload();


}

