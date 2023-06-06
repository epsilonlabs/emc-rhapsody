
//Licensed Materials - Property of IBM 
//(c) Copyright IBM Corporation 2006, 2018. All Rights Reserved.

package com.telelogic.rhapsody.core;


/**
 * The IRPProject interface represents Rhapsody projects.
 */
public interface IRPProject  extends IRPPackage {
	/**
 	 * For internal use only.
 	 */
	public void gatewayExportToXML(String filename, String params);
	/**
 	 * For internal use only.
 	 */
	public void gatewayExportToXML2(String filename, String params, IRPProject proj);
	/**
 	 * Generates a ReporterPLUS report for the model. (When this method is used to generate a report, the Rhapsody model is saved before the report is generated.)
 	 * <pre> 
 	 * // Sample code:
 	 * IRPProject currentProject = app.activeProject();
 	 * currentProject.generateReport("", "C:\Rhapsody\reporterplus\Templates\Class.tpl", "html", "C:\testreport.html", 0, 0);
 	 * </pre> 
 	 * @param modelscope the name of the package for which the report should be generated. If empty, a report is generated for the entire model. (This is similar to the "scope" command-line option for ReporterPLUS.)
 	 * @param templatename the name of the template to use. If empty, then the ReporterPLUS report generation wizard will be launched and it will display the name of the last template used.
 	 * @param docType the type of output to generate (doc, html, ppt, txt). If empty, the ReporterPLUS report generation wizard will be launched and it will display the last output type used.
 	 * @param filename the filename to use for the generated report. If empty, the ReporterPLUS report generation wizard will be displayed and it will display the filename of the last generated report.
 	 * @param showDocument In general, the user will be asked if they want to view the report after generation only if they have requested this by selecting View &gt; Options &gt; Ask to open after generating report from the main menu in ReporterPLUS. However, if the user has specified silent generation mode using the parameter silentMode, this parameter can be used to request that the generated document be displayed. To display the report, set this parameter to 1, otherwise use 0. 
 	 * @param silentMode If the template name, document type, or output file name has not been specified using the appropriate parameter, the ReporterPLUS report generation wizard is displayed so the user can provide the missing information. This is the behavior if this parameter is set to 0. If you want to prevent the wizard from being launched in such cases, you can specify silent generation mode by setting this parameter to 1. If set to silent mode, no report will be generated if one or more of the above parameters was not provided. (The report generation status dialog is displayed regardless of the value of this parameter.)
 	 */
	public void generateReport(String modelscope, String templatename, String docType, String filename, int showDocument, int silentMode);
	/**
 	 * Adds a new Component to the project.
 	 * @param name the name to use for the new Component
 	 * @return the Component that was created
 	 */
	public IRPComponent addComponent(String name);
	/**
 	 * Applies the specified custom view to the model browser.
 	 * @param customView the custom view that should be applied to the browser
 	 * @throws RhapsodyRuntimeException
 	 */
	public void addCustomViewOnBrowser(IRPPackage customView);
	/**
 	 * Applies the specified custom view to the specified diagram.
 	 * @param diagram the diagram to which the custom view should be applied
 	 * @param customView the custom view that should be applied to the diagram
 	 * @throws RhapsodyRuntimeException
 	 */
	public void addCustomViewOnDiagram(IRPDiagram diagram, IRPPackage customView);
	/**
 	 * Adds a new package to the project.
 	 * @param name the name to use for the new package
 	 * @return the package that was created
 	 */
	public IRPPackage addPackage(String name);
	/**
 	 * Adds a new profile to the project.
 	 * @param name the name to use for the new profile
 	 * @return the profile that was created
 	 */
	public IRPProfile addProfile(String name);
	/**
 	 * For internal use only.
 	 */
	public void addSpellCheckerResult(String value);
	/**
 	 * Can be used to temporarily disable autosaving of the model regardless of the current value of the property General::Model::AutoSaveInterval.
 	 * @param allow Use 0 to turn off autosave, use 1 to turn autosave on
 	 */
	public void allowAutoSave(int allow);
	/**
 	 * For internal use only.
 	 */
	public void allowNonUniqueNames(int allow);
	/**
 	 * Applies the custom views applied to the browser to all diagrams as well.
 	 * @param newVal Use 1 to apply the custom views to all diagrams, 0 to remove the custom views from the diagrams
 	 * @throws RhapsodyRuntimeException
 	 */
	public void applyBrowserCustomViewsOnDiagrams(int newVal);
	/**
 	 * For internal use only.
 	 */
	public void applyRoundtripDiffMerge(int magicNumber, IRPProject shadowModel, IRPCollection filesToUpdate);
	/**
 	 * Makes this project the active project in Rhapsody. For use when you have multiple projects open in Rhapsody.
 	 */
	public void becomeActiveProject();
	/**
 	 * Checks the values of the event base IDs for all packages in the model, detects collisions between the IDs, and resolves any incorrect values and collisions.
 	 */
	public void checkEventsBaseIdsSolveCollisions();
	/**
 	 * Removes any unresolved elements from the model, starting at the level of the specified element and working downward.
 	 * @param rootElement the element below which Rhapsody should remove all unresolved elements
 	 */
	public void cleanUnresolvedElements(IRPModelElement rootElement);
	/**
 	 * Closes the project.
 	 */
	public void close();
	/**
 	 * Closes the tab in the Output window for the specified csv file.
 	 * @param fullCSVFileName the path of the csv file that should be closed
 	 */
	public void closeCSVFile(String fullCSVFileName);
	/**
 	 * Deletes the specified Component.
 	 * @param component the Component that should be deleted
 	 */
	public void deleteComponent(IRPComponent component);
	/**
 	 * Enables the project for Rhapsody Model Manager. The actions carried out are the same as those carried out when you choose Enable Rhapsody Model Manager from the popup menu for projects in Rhapsody.
 	 */
	public void enableRhapsodyModelManager();
	/**
 	 * For internal use only.
 	 */
	public void endTransactionOfNoCGInterest();
	/**
 	 * Returns the Component with the specified name.
 	 * @param name the name of the Component to return
 	 * @return the Component with the specified name
 	 */
	public IRPComponent findComponent(String name);
	/**
 	 * Retrieves a model element based on its binary ID. This operation can be used in conjunction with the operation IRPModelElement.getBinaryID, which returns the binary ID of the element. In some situations, findElementByBinaryID is faster than the operation IRPProject.findElementByGUID.
 	 * @param theID the binary ID for the model element that should be retrieved
 	 * @return the model element with the specified binary ID
 	 */
	public IRPModelElement findElementByBinaryID(byte[] theID);
	/**
 	 * Returns the top-level element in the specified Rhapsody unit file. For example, the top-level package is returned for an sbs file, and the class element is returned for a cls file.
 	 * @param theFolderName the full path of the folder that contains the unit file
 	 * @param theFileName the filename for the unit file
 	 * @return the top-level element in the specified Rhapsody unit file
 	 * @throws RhapsodyRuntimeException
 	 */
	public IRPModelElement findElementByFileName(String theFolderName, String theFileName);
	/**
 	 * Retrieves a model element based on its GUID.
 	 * @param theGUID the GUID for the model element that should be retrieved
 	 * @return the model element with the specified GUID
 	 */
	public IRPModelElement findElementByGUID(String theGUID);
	/**
 	 * Returns a collection of all the model elements that have an OSLC link of the specified type to the specified target element.
 	 * @param type the link type of the OSLC link. Must be one of the typed defined in {@link IRPModelElement.OSLCLink.Types}. You can also use "*" to represent all of the types.
 	 * @param purl the URL for the link's target element. You can use "*" to find all the model elements that have any link of the specified type.
 	 * @return collection of the model elements that have an OSLC link of the specified type to the specified target element
 	 * @throws RhapsodyRuntimeException
 	 */
	public IRPCollection findElementsWithOSLCLink(String type, String purl);
	/**
 	 * Returns the active component.
 	 * @return the active component
 	 */
	public IRPComponent getActiveComponent();
	/**
 	 * Returns the active configuration.
 	 * @return the active configuration
 	 */
	public IRPConfiguration getActiveConfiguration();
	/**
 	 * Returns a collection of the custom views currently applied to the browser.
 	 * @return the custom views currently applied to the browser
 	 * @throws RhapsodyRuntimeException
 	 */
	public IRPCollection getActiveCustomViewsOnBrowser();
	/**
 	 * Returns a collection of the custom views currently applied to the specified diagram.
 	 * @param diagram the diagram whose custom views should be returned
 	 * @return the custom views currently applied to the specified diagram
 	 * @throws RhapsodyRuntimeException
 	 */
	public IRPCollection getActiveCustomViewsOnDiagram(IRPDiagram diagram);
	/**
 	 * Returns a collection of all the stereotypes in the project.
 	 * @return all the stereotypes in the project
 	 */
	public IRPCollection getAllStereotypes();
	/**
 	 * Returns the package that contains the simplified model.
 	 * @return the package that contains the simplified model
 	 */
	public IRPPackage getCgSimplifiedModelPackage();
	/**
 	 * Returns a collection of filenames for the code files that will be generated for the current active component if you select the "regenerate" option. Note that this does not refer to which files were actually generated the last time that code generation was carried out.
 	 * @return collection of filenames for the code files that will be generated for the current active component if you select the "regenerate" option
 	 */
	public IRPCollection getCodeGeneratedFiles();
	/**
 	 * Returns a collection of all the components in the project.
 	 * @return all the components in the project
 	 */
	public IRPCollection getComponents();
	/**
 	 * Returns the project's default directory scheme with regard to packages. "Flat" means that all new units are saved in a single directory. "PackageAsDirectory" means that a new directory is created for each package in the model. This setting is controlled by the property General::Model::DefaultDirectoryScheme.
 	 * @return the project's default directory scheme with regard to packages - Flat or PackageAsDirectory
 	 */
	public String getDefaultDirectoryScheme();
	/**
 	 * Creates a new IRPCollaboration object that can be used to create a sequence diagram.
 	 * @return the IRPCollaboration object that was created
 	 */
	public IRPCollaboration getNewCollaboration();
	/**
	 * method getNewProgressBar
	 * @throws RhapsodyRuntimeException
	 */
	public IRPProgressBar getNewProgressBar(int amount, String name);
	/**
 	 * Checks whether plugins will be notified when model elements are modified.
 	 * @return 1 if plugins are to be notified when model elements are modified, 0 otherwise.
 	 * @throws RhapsodyRuntimeException
 	 */
	public int getNotifyPluginOnElementsChanged();
	/**
 	 * Returns a collection of all the profiles in the project.
 	 * @return all the profiles in the project
 	 */
	public IRPCollection getProfiles();
	/**
 	 * For Model Manager and Design Manager projects, returns the packages of remote resources (the "Remote Resource Packages"). The collection returned consists or IRPPackage objects. You can then cycle through the individual packages to access the individual remote resources.
 	 * @return the packages of remote resources
 	 */
	public IRPCollection getRemoteResourcePackages();
	/**
 	 * Returns all of the requirements that have the specified ID. This refers to the ID field in the Features dialog for requirements. For models where you know that only one requirement can have a given ID, you can use the second parameter to specify that only the first matching requirement should be returned - this option can be useful in large models.
 	 * @param requirementID the ID to use for the search
 	 * @param returnFirstFoundOnly use 1 if you want the method to return only the first matching requirement, 0 if you want the method to return all matching requirements
 	 * @return all of the requirements that have the specified ID
 	 * @throws RhapsodyRuntimeException
 	 */
	public IRPCollection getRequirementsByID(String requirementID, int returnFirstFoundOnly);
	/**
 	 * For internal use only.
 	 */
	public IRPProject getRoundtripShadowModel(int magicNumber);
	/**
 	 * Highlights in the Rhapsody browser the model element associated with the specified line of code.
 	 * <pre>
 	 * {@code
 	 * IRPProject prj = app.openProject("d:\\temp\\_sample_code\\First_Project.rpy");
 	 * prj.highlightFromCode("d:\\temp\\_sample_code\\DefaultComponent\\DefaultConfig\\class_0.h", 42);
 	 * }
 	 * </pre>
 	 * @param filename the absolute path for the relevant source file
 	 * @param lineNumber the line number in the file
 	 * @return the model element associated with the specified line of code
 	 * @throws RhapsodyRuntimeException
 	 */
	public IRPModelElement highlightFromCode(String filename, int lineNumber);
	/**
 	 * Imports the specified Rose package. Beginning in release 8.1.4, this method is no longer supported.
 	 * @param projectName the Rose project from which the package should be imported (path that includes the name of the *.mdl file)
 	 * @param packageName the name of the Rose package to import
 	 * @param logFileName the file to use for logging the import process
 	 */
	public void importPackageFromRose(String projectName, String packageName, String logFileName);
	/**
 	 * Imports the specified Rose project. Beginning in release 8.1.4, this method is no longer supported.
 	 * @param projectName the Rose project to import (path that includes the name of the *.mdl file)
 	 * @param logFileName the file to use for logging the import process
 	 */
	public void importProjectFromRose(String projectName, String logFileName);
	/**
 	 * Checks whether the project is an actively-managed Design Manager project.
 	 * @return 1 if the project is an actively-managed DM project, 0 otherwise
 	 * @throws RhapsodyRuntimeException
 	 */
	public int isActivelyManaged();
	/**
 	 * Checks whether any part of the project has been modified and the project needs to be saved.
 	 * @return 1 if any part of the project has been modified, 0 if no changes have been made
 	 */
	public int isModifiedRecursive();
	/**
 	 * For projects that use Rhapsody's integration with Eclipse or Visual Studio, you can use the locateInIDE method to have the IDE highlight a specific line in a specific source file.
 	 * @param config the Rhapsody configuration (of type Eclipse or Visual Studio configuration) that contains the generated source file
 	 * @param filename the name of the file that should be opened in the IDE
 	 * @param lineNumber the line number of the line that should be highlighted
 	 * @throws RhapsodyRuntimeException
 	 */
	public void locateInIDE(IRPConfiguration config, String filename, int lineNumber);
	/**
 	 * For projects that contain imported Design Manager links, this method recreates the links as Rhapsody Model Manager links. Before calling this method, verify that the relevant OSLC links have been imported into the model. If not, log-in to the DM server with IRPApplication.loginToDesignManagerWithUsername, and then call the method IRPApplication.importDesignManagerModel. You also must verify that the model has been enabled for Rhapsody Model Manager. If not, you can call the method IRPProject.enableRhapsodyModelManager. If the relevant Rhapsody Model Manager project area is configuration-managed, set the global configuration for your Rhapsody project before migrating the links by calling the method IRPProject.setGlobalConfiguration.
 	 */
	public void migrateDesignManagerLinks();
	/**
 	 * @deprecated Support for Design Manager was removed from Rhapsody in release 8.4.
 	 */
 	@Deprecated
	public void moveToDesignManager(String userName, String password, String serverURL, String projectAreaName, String streamName);
	/**
 	 * @deprecated Support for Design Manager was removed from Rhapsody in release 8.4.
 	 */
 	@Deprecated
	public void moveToDesignManagerAfterLogin(String serverURL, String projectAreaName, String streamName);
	/**
 	 * Displays the content of the specified csv file in a new tab in the Output window.
 	 * @param fullCSVFileName the path of the csv file that should be displayed
 	 * @param reserved this parameter has no effect, you can use any integer
 	 */
	public void openCSVFile(String fullCSVFileName, int reserved);
	/**
 	 * If you are using Rhapsody's default numbering scheme for event IDs, then a certain amount of IDs are reserved for each package. As a result, there are situations where the IDs used for events in a given package may not be continuous. In cases like this, you can use the method recalculateEventsBaseIds to have the event ID numbering recalculated so that event IDs are continuous for all events within each package in the project.
 	 */
	public void recalculateEventsBaseIds();
	/**
 	 * Reloads the content of the specified csv file in a tab in the Output window.
 	 * @param fullCSVFileName the path of the csv file that should be reloaded
 	 */
	public void reloadCSVFile(String fullCSVFileName);
	/**
 	 * Removes the project from the Rhapsody workspace.
 	 * @return 1 if the project was removed successfully, 0 otherwise
 	 * @throws RhapsodyRuntimeException
 	 */
	public int remove();
	/**
 	 * Removes the specified custom view from the model browser.
 	 * @param customView the custom view that should be removed from the browser
 	 * @throws RhapsodyRuntimeException
 	 */
	public void removeCustomViewOnBrowser(IRPPackage customView);
	/**
 	 * Removes the specified custom view from the specified diagram.
 	 * @param diagram the diagram that the custom view should be removed from
 	 * @param customView the custom view that should be removed from the diagram
 	 * @throws RhapsodyRuntimeException
 	 */
	public void removeCustomViewOnDiagram(IRPDiagram diagram, IRPPackage customView);
	/**
 	 * Saves the project.
 	 */
	public void save();
	/**
 	 * Saves the project using the specified path.
 	 * @param filename the path to use for saving the project
 	 */
	public void saveAs(String filename);
	/**
 	 * Saves the project, using the format of a previous version of Rhapsody.
 	 * @param filename the path to use for saving the project
 	 * @param prevVersion the Rhapsody version whose format you want to use for saving the project. The valid strings for this parameter are those that are used in the Save As dialog in the user interface, for example, "7.6.1".
 	 */
	public void saveAsPrevVersion(String filename, String prevVersion);
	/**
 	 * Sets the specified component as the active component for the project. Note that there are two versions of this method. The first takes a String parameter, which is the name of the component that should be made the active component. The second version takes an object of type IRPComponent, which is the component that should be made the active component.
 	 * @param activeComponent the name of the component that should be set as the active component for the project. The string should represent the location of the component in the project hierarchy, using a double colon (::) as the delimiter, for example, Default::subpackage_1::component_a::subcomponent_b. If the component is not contained in a package or within another component, you can just use the name of the component.
 	 */
	public void setActiveComponent(String name);
	/**
 	 * Sets the specified component as the active component for the project. Note that there are two versions of this method. The first takes a String parameter, which is the name of the component that should be made the active component. The second version takes an object of type IRPComponent, which is the component that should be made the active component.
 	 * @param activeComponent the name of the component that should be set as the active component for the project. The string should represent the location of the component in the project hierarchy, using a double colon (::) as the delimiter, for example, Default::subpackage_1::component_a::subcomponent_b. If the component is not contained in a package or within another component, you can just use the name of the component.
 	 */
	public void setActiveComponent(IRPComponent activeComponent);
	/**
 	 * Sets the specified configuration to be the active configuration of the project. The configuration must belong to the active component. Note that there are two versions of this method. The first takes a String parameter, which is the name of the configuration that should be made the active configuration. The second version takes an object of type IRPConfiguration, which is the configuration that should be made the active configuration.
 	 * @param activeConfiguration the name of the configuration to set as the active configuration
 	 */
	public void setActiveConfiguration(String name);
	/**
 	 * Sets the specified configuration to be the active configuration of the project. The configuration must belong to the active component. Note that there are two versions of this method. The first takes a String parameter, which is the name of the configuration that should be made the active configuration. The second version takes an object of type IRPConfiguration, which is the configuration that should be made the active configuration.
 	 * @param activeConfiguration the name of the configuration to set as the active configuration
 	 */
	public void setActiveConfiguration(IRPConfiguration activeConfiguration);
	/**
 	 * Set's the project's default directory scheme with regard to packages. This setting is controlled by the property General::Model::DefaultDirectoryScheme.
 	 * @param defaultDirectoryScheme the default directory scheme to use for packages in the model. The valid values for this parameter are: "Flat" - all new units are saved in a single directory, and "PackageAsDirectory" - a new directory is created for each package in the model.
 	 */
	public void setDefaultDirectoryScheme(String defaultDirectoryScheme);
	/**
 	 * Specifies the global configuration that should be used for the project.
 	 * @param GCUri the URI of the global configuration that should be used
 	 * @param name the name of the global configuration that should be used
 	 * @return 1 if the global configuration information was set correctly, 0 otherwise
 	 */
	public int setGlobalConfiguration(String GCUri, String name);
	/**
 	 * For plugins that use the callback API, you must call the method setNotifyPluginOnElementsChanged if you want the plugin to be notified when model elements are modified.
 	 * @param val Use 1 to specify that the plugin should be notified when an element is modified. Use 0 to specify that the plugin should not be notified when elements are modified.
 	 * @throws RhapsodyRuntimeException
 	 */
	public void setNotifyPluginOnElementsChanged(int val);
	/**
 	 * Changes the specified object to an explicit object. As a result, a class is added to the model with the name [object name]_Class. This method corresponds to the Expose Class option in the pop-up menu of the Rhapsody model browser.
 	 * @param pInstance the object that should be changed to explicit
 	 */
	public void setObjectExplicit(IRPInstance pInstance);
	/**
 	 * Changes the specified object to an implicit object.
 	 * @param pInstance the object that should be changed to implicit
 	 */
	public void setObjectImplicit(IRPInstance pInstance);
	/**
 	 * This method can be used to specify that all of the stereotypes in the model should be cached to allow quicker retrieval. This can be beneficial when working with profiles that contain a very large number of stereotypes.
 	 * @param useUniqueStereotypeAndRefCache use 1 to turn on stereotype caching, use 0 to turn off stereotype caching
 	 * @throws RhapsodyRuntimeException
 	 */
	public void setUseUniqueStereotypeAndRefCache(int useUniqueStereotypeAndRefCache);
	/**
 	 * The method setWaitDialogWatchdogValue provides a mechanism that allows an external process to inform Rhapsody that the process has ended or crashed. Call this method with any non-blank value to notify Rhapsody that the process is running. Rhapsody then displays a message dialog indicating that it is waiting for the process to complete. The user plugin must continue calling this method repeatedly to indicate that it has not finished. The interval for calling the function must be less than two minutes. If the method is not called for two minutes, Rhapsody assumes the process has crashed, and it closes the dialog and lets Rhapsody continue. When the process has completed, call the method again with an empty string as the argument. This informs Rhapsody that the process is done.
 	 * @param value use non-blank string to indicate to Rhapsody that the external process is still running, use blank string to indicate to Rhapsody that the process has completed
 	 * @throws RhapsodyRuntimeException
 	 */
	public void setWaitDialogWatchdogValue(String value);
	/**
 	 * For internal use only.
 	 */
	public void startTransactionOfNoCGInterest();


}

