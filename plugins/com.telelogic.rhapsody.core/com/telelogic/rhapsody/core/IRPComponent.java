
//Licensed Materials - Property of IBM 
//(c) Copyright IBM Corporation 2006, 2018. All Rights Reserved.

package com.telelogic.rhapsody.core;


 /**
  * The IRPComponent interface represents a code generation component defined in a Rhapsody model.
  */
public interface IRPComponent  extends IRPUnit {
	/**
 	 * Adds a new configuration to the component.
 	 * @param name the name to use for the new configuration
 	 * @return the configuration that was created
 	 */
	public IRPConfiguration addConfiguration(String name);
	/**
 	 * Adds a new File to the component.
 	 * @param name the name to use for the new File
 	 * @return the File that was created
 	 */
	public IRPFile addFile(String name);
	/**
 	 * Adds a new Folder to the component.
 	 * @param name the name to use for the new Folder
 	 * @return the Folder that was created
 	 */
	public IRPFile addFolder(String name);
	/**
 	 * Adds a new nested component to the component.
 	 * @param name the name to use for the new component
 	 * @return the component that was created
 	 */
	public IRPComponent addNestedComponent(String name);
	/**
 	 * Adds the specified model element to the scope of the component.
 	 * @param pVal the model element that should be added to the scope of the component
 	 */
	public void addScopeElement(IRPModelElement pVal);
	/**
	 * method addScopeElementWithoutAggregates
	 * @throws RhapsodyRuntimeException
	 */
	public void addScopeElementWithoutAggregates(IRPModelElement pVal);
	/**
	 * method addToScope
	 * @throws RhapsodyRuntimeException
	 */
	public void addToScope(IRPFile file, IRPCollection classes, IRPCollection packages);
	/**
 	 * Adds all the elements in the model to the scope of the component.
 	 */
	public void allElementsInScope();
	/**
 	 * Deletes the specified configuration.
 	 * @param configuration the configuration that should be deleted
 	 */
	public void deleteConfiguration(IRPConfiguration configuration);
	/**
 	 * Deletes the specified File.
 	 * @param file the File that should be deleted
 	 */
	public void deleteFile(IRPFile file);
	/**
 	 * Returns the configuration with the specified name.
 	 * @param name the name of the configuration to return
 	 * @return the configuration with the specified name
 	 */
	public IRPConfiguration findConfiguration(String name);
	/**
 	 * Returns the additional sources defined for the component.
 	 * @return the additional sources defined for the component
 	 */
	public String getAdditionalSources();
	/**
 	 * Returns the build type of the component - Library, Executable, or Analysis.
 	 * @return the build type of the component - Library, Executable, or Analysis
 	 */
	public String getBuildType();
	/**
	 * method getConfigByDependency
	 * @throws RhapsodyRuntimeException
	 */
	public IRPConfiguration getConfigByDependency(IRPDependency o);
	/**
 	 * Returns a collection of all the configurations in the component.
 	 * @return all the configurations in the component
 	 */
	public IRPCollection getConfigurations();
	/**
	 * method getFile
	 * @throws RhapsodyRuntimeException
	 */
	public IRPFile getFile(IRPClassifier c, int spec);
	/**
	 * method getFileName
	 * @throws RhapsodyRuntimeException
	 */
	public String getFileName(IRPClassifier c, int spec, int withExt);
	/**
 	 * Returns a collection of all the Files in the component.
 	 * @return all the Files in the component
 	 */
	public IRPCollection getFiles();
	/**
 	 * Returns the include path defined for the component.
 	 * @return the include path defined for the component
 	 */
	public String getIncludePath();
	/**
	 * get property libraries
	 * @throws RhapsodyRuntimeException
	 */
	public String getLibraries();
	/**
	 * method getModelElementFileName
	 * @throws RhapsodyRuntimeException
	 */
	public String getModelElementFileName(IRPModelElement c, int spec, int withExt);
	/**
 	 * Returns a collection of all the nested components in the component.
 	 * @return all the nested components in the component
 	 */
	public IRPCollection getNestedComponents();
	/**
	 * method getPackageFile
	 * @throws RhapsodyRuntimeException
	 */
	public IRPFile getPackageFile(IRPPackage c, int spec);
	/**
 	 * Returns a collection of all the panel diagrams in the component.
 	 * @return all the panel diagrams in the component
 	 */
	public IRPCollection getPanelDiagrams();
	/**
	 * get property path
	 * @throws RhapsodyRuntimeException
	 */
	public String getPath(int fullPath);
	/**
	 * method getPossibleVariants
	 * @throws RhapsodyRuntimeException
	 */
	public IRPCollection getPossibleVariants(IRPModelElement variationPoint);
	/**
 	 * Checks whether the scope of the component has been set to include all elements or only specific elements. This corresponds to the All Elements and Selected Elements radio buttons on the Scope tab of the Features dialog for components.
 	 * @return 1 if the scope has been set to include only specific elements, 0 if the scope has been set to include all elements
 	 */
	public int getScopeBySelectedElements();
	/**
 	 * Returns a collection of all the model elements in the scope of the component.
 	 * @return all the model elements in the scope of the component
 	 */
	public IRPCollection getScopeElements();
	/**
	 * method getScopeElementsByCategory
	 * @throws RhapsodyRuntimeException
	 */
	public IRPCollection getScopeElementsByCategory(String category);
	/**
 	 * Returns the standard headers defined for the component.
 	 * @return the standard headers defined for the component
 	 */
	public String getStandardHeaders();
	/**
	 * method getVariant
	 * @throws RhapsodyRuntimeException
	 */
	public IRPModelElement getVariant(IRPModelElement variationPoint);
	/**
 	 * Returns a collection of the variation points that are included in the scope of the component. The collection consists of objects of type IRPClass.
 	 * @return a collection of the variation points that are included in the scope of the component
 	 */
	public IRPCollection getVariationPoints();
	/**
	 * method isDirectoryPerModelComponent
	 * @throws RhapsodyRuntimeException
	 */
	public int isDirectoryPerModelComponent(IRPModelElement o);
	/**
 	 * Removes the specified model element from the scope of the component.
 	 * @param pVal the model element that should be removed from the scope of the component
 	 */
	public void removeScopeElement(IRPModelElement pVal);
	/**
 	 * Specifies the additional sources to use for the component.
 	 * @param additionalSources the additional sources to use for the component
 	 */
	public void setAdditionalSources(String additionalSources);
	/**
 	 * Specifies the build type for the component.
 	 * @param buildType the build type that should be used for the component. The valid strings for this parameter are: Executable, Library, and Analysis.
 	 */
	public void setBuildType(String buildType);
	/**
 	 * Specifies the include path to use for the component.
 	 * @param includePath the include path to use for the component
 	 */
	public void setIncludePath(String includePath);
	/**
	 * set property libraries
	 * @throws RhapsodyRuntimeException
	 */
	public void setLibraries(String libraries);
	/**
	 * method setPath
	 * @throws RhapsodyRuntimeException
	 */
	public void setPath(String path);
	/**
	 * set toggle the scope between selected and all-elements
	 * @throws RhapsodyRuntimeException
	 */
	public void setScopeBySelectedElements(int scopeBySelectedElements);
	/**
 	 * Specifies the standard headers for the component.
 	 * @param standardHeaders a string consisting of a comma-separated list of the files that should be used as standard headers for the component
 	 */
	public void setStandardHeaders(String standardHeaders);
	/**
	 * method setVariant
	 * @throws RhapsodyRuntimeException
	 */
	public void setVariant(IRPModelElement variationPoint, IRPModelElement pVariant);
	/**
 	 * Updates the views on the Rhapsody Model Manager server for all the diagrams contained in the component.
 	 * @param enforceUpdate Use 0 to specify that a view should be updated only if changes that affect the diagram were made since the last update. Use 1 to specify that views should be updated regardless of whether or not changes that affect the diagram were made since the last update.
 	 * @return the number of views that were updated on the server. If no diagrams require an update, 0 is returned. If the update attempt failed, -1 is returned.
 	 * @throws RhapsodyRuntimeException
 	 */
	public int updateContainedDiagramsOnServer(int enforceUpdate);


}

