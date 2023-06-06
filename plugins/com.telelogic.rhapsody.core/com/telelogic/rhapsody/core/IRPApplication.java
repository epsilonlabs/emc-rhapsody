
//Licensed Materials - Property of IBM 
//(c) Copyright IBM Corporation 2006, 2018. All Rights Reserved.

package com.telelogic.rhapsody.core;


/**
 * The IRPApplication interface represents the Rhapsody application, and its methods reflect many of the commands that you can access from the Rhapsody menu bar.
 */
public interface IRPApplication {
	/**
	 * method execute command
	 * @throws RhapsodyRuntimeException
	 */
	public void executeCommand(String commandType, IRPCollection pCommandInitialization, IRPCollection pCommandResult);
	/**
	 * PlugIn window factory
	 * @throws RhapsodyRuntimeException
	 */
	public IRPPlugInWindow getPlugInWindow(int nPlugInID, int nWindowID, int nCreateNew);
	/**
	 * Open ActivexView
	 * @throws RhapsodyRuntimeException
	 */
	public IRPAXViewCtrl openActiveXView(String viewType, IRPCollection pViewInitialization, IRPCollection pExtra);
	/**
	 * method OpenDiagramView
	 * @throws RhapsodyRuntimeException
	 */
	public IRPAXViewCtrl openDiagramView(IRPDiagram diagram);
	/**
	 * checkin license
	 * @throws RhapsodyRuntimeException
	 */
	public void rhpCheckinLicense(String feature);
	/**
	 * checkout license
	 * @throws RhapsodyRuntimeException
	 */
	public String rhpCheckoutLicense(String feature);
	/**
 	 * Returns an IRPProject object representing the project currently open in Rhapsody
 	 * @return IRPProject object that represents the project currently open in Rhapsody
 	 */
	public IRPProject activeProject();
	/**
	 * addProfileToModel
	 * @throws RhapsodyRuntimeException
	 */
	public IRPUnit addProfileToModel(String profileName);
	/**
 	 * Adds the currently selected item to the Favorites list.
 	 */
	public void addSelectedToFavorites();
	/**
	 * add To Model
	 * @throws RhapsodyRuntimeException
	 */
	public void addToModel(String filename, int withDescendant);
	/**
	 * add To Model by reference
	 * @throws RhapsodyRuntimeException
	 */
	public void addToModelByReference(String filename);
	/**
 	 * Adds a unit to the model.
 	 * @param filename the full path to the file to add to the model
 	 * @param addToModelMode how the unit should be added to the model - see {@link IRPApplication.AddToModel_Mode} for the available values
 	 * @param addSubUnits use 1 if you want to also add the sub-units of the unit, 0 otherwise (this parameter is ignored if the addToModelMode parameter equals IRPApplication.AddToModel_Mode.AS_UNIT_WITHOUT_COPY)
 	 * @param addDependents use 1 if you want to also add the units that elements in the specified unit are dependent upon, 0 otherwise (this parameter is ignored if the addToModelMode parameter equals IRPApplication.AddToModel_Mode.AS_UNIT_WITHOUT_COPY)
 	 */
	public void addToModelEx(String filename, int addToModelMode, int addSubUnits, int addDependents);
	/**
	 * add To Model From URL
	 * @throws RhapsodyRuntimeException
	 */
	public void addToModelFromURL(String purl);
	/**
 	 * Adds one or more units to the model.
 	 * @param fileNamesList collection of full paths for the units that are to be added.
 	 * @param addToModelMode how the unit should be added to the model - see {@link IRPApplication.AddToModel_Mode} for the available values
 	 * @param addSubUnits use 1 if you want to also add the sub-units of the unit, 0 otherwise (this parameter is ignored if the addToModelMode parameter equals IRPApplication.AddToModel_Mode.AS_UNIT_WITHOUT_COPY)
 	 * @param addDependents use 1 if you want to also add the units that elements in the specified unit are dependent upon, 0 otherwise (this parameter is ignored if the addToModelMode parameter equals IRPApplication.AddToModel_Mode.AS_UNIT_WITHOUT_COPY)
 	 * @return collection of the GUIDs of the units that were added to the model. To get a model element from its GUID, you can use the method {@link IRPProject#findElementByGUID}.
 	 */
	public IRPCollection addUnitsToModel(IRPCollection fileNamesList, int addToModelMode, int addSubUnits, int addDependents);
	/**
	 * allowBrowserRefresh
	 * @throws RhapsodyRuntimeException
	 */
	public void allowBrowserRefresh(int shouldRefresh);
	/**
	 * allowGERefresh
	 * @throws RhapsodyRuntimeException
	 */
	public void allowGERefresh(int shouldRefresh);
	/**
	 * Called to apply a NewTerms Profile to the active project
	 * @throws RhapsodyRuntimeException
	 */
	public void applyNewTermsProfile(String profileName);
	/**
	 * archive Check Out
	 * @throws RhapsodyRuntimeException
	 */
	public void arcCheckOut(String filename, String label, int isLocked, int isRecursive);
	/**
	 * bring window to top
	 * @throws RhapsodyRuntimeException
	 */
	public void bringWindowToTop();
	/**
 	 * Builds an application using the active component and configuration. Use IRPProject.setActiveComponent and IRPProject.setActiveConfiguration to change the active component and configuration if necessary before calling the build method.
 	 */
	public void build();
	/**
	 * buildEntireProject
	 * @throws RhapsodyRuntimeException
	 */
	public void buildEntireProject();
	/**
	 * buildWithDependencies
	 * @throws RhapsodyRuntimeException
	 */
	public void buildWithDependencies();
	/**
	 * Check if Redo action is available
	 * @throws RhapsodyRuntimeException
	 */
	public int canRedo();
	/**
	 * Check if Undo action is available
	 * @throws RhapsodyRuntimeException
	 */
	public int canUndo();
	/**
	 * check In
	 * @throws RhapsodyRuntimeException
	 */
	public void checkIn(String unitName, String label, int isLocked, int isRecursive, String description);
	/**
	 * check model
	 * @throws RhapsodyRuntimeException
	 */
	public void checkModel();
	/**
	 * check Out
	 * @throws RhapsodyRuntimeException
	 */
	public void checkOut(String unitName, String label, int isLocked, int isRecursive);
	/**
	 * clean
	 * @throws RhapsodyRuntimeException
	 */
	public void clean();
	/**
	 * clear output window
	 * @throws RhapsodyRuntimeException
	 */
	public void clearOutputWindow(String title);
	/**
	 * Close All Animated Sequence diagrams without save
	 * @throws RhapsodyRuntimeException
	 */
	public void closeAllAnimatedSequenceDiagrams(int withSave);
	/**
 	 * Compares the two sequence diagrams specified as parameters. Corresponds to the Sequence Diagram Compare option in the Tools menu.
 	 * @param leftDiagram the first diagram to use for the comparison
 	 * @param rightDiagram the second diagram to use for the comparison
 	 */
	public void compareSequenceDiagram(IRPSequenceDiagram leftDiagram, IRPSequenceDiagram rightDiagram);
	/**
	 * connect To Archive
	 * @throws RhapsodyRuntimeException
	 */
	public void connectToArchive(String archivePath);
	/**
 	 * @deprecated Support for Design Manager was removed from Rhapsody in release 8.4.
 	 */
 	@Deprecated
	public void connectToImportedModel(String serverURL, String projectAreaName, String streamName);
	/**
	 * connectToTarget
	 * @throws RhapsodyRuntimeException
	 */
	public void connectToTarget(String targetName);
	/**
	 * Create a new project and insert it into current workspace
	 * @throws RhapsodyRuntimeException
	 */
	public int createAndInsertProject(String projectLocation, String projectName);
	/**
 	 * @deprecated Support for Design Manager was removed from Rhapsody in release 8.4.
 	 */
 	@Deprecated
	public void createDomainFromProfile(IRPProfile profileArg, String serverURL);
	/**
 	 * creates a new Rhapsody collection object
 	 * @return IRPCollection object that represents the collection that was created. After creating a collection, you can add items to it by calling IRPCollection.addItem or by calling IRPCollection.setSize and then IRPCollection.setModelElement.
 	 */
	public IRPCollection createNewCollection();
	/**
 	 * Creates a new Rhapsody project
 	 * @param projectLocation the directory where the project should be saved, for example, "l:\\temp\\_sample_code"
 	 * @param projectName the name to use for the project. This will be the name used for the .rpy file.
 	 */
	public void createNewProject(String projectLocation, String projectName);
	/**
 	 * @deprecated Support for Design Manager was removed from Rhapsody in release 8.4.
 	 */
 	@Deprecated
	public void dMRefreshRecursive(IRPUnit pUnit);
	/**
 	 * @deprecated Support for Design Manager was removed from Rhapsody in release 8.4.
 	 */
 	@Deprecated
	public void dMSyncAndRefresh(IRPProject projectArg, int sync, int refresh);
	/**
	 * For debug - check communication in
	 * @throws RhapsodyRuntimeException
	 */
	public void dbgCheckComIn(short i);
	/**
	 * For debug - check communication out
	 * @throws RhapsodyRuntimeException
	 */
	public short dbgCheckComOut();
	/**
	 * Add Rhapsody unit to current project
	 * @throws RhapsodyRuntimeException
	 */
	public void deferredAddToModel(String filename, int withDescendants, String origPrjId, int eraseDir);
	/**
	 * Delete specified project from current workspace
	 * @throws RhapsodyRuntimeException
	 */
	public int deleteProjectFromList(String projectName);
	/**
	 * disconnectFromTarget
	 * @throws RhapsodyRuntimeException
	 */
	public void disconnectFromTarget();
	/**
	 * downloadToTarget
	 * @throws RhapsodyRuntimeException
	 */
	public void downloadToTarget(String filename);
	/**
	 * end undo transaction
	 * @throws RhapsodyRuntimeException
	 */
	public void endUndoTransaction();
	/**
	 * enter Animation Command
	 * @throws RhapsodyRuntimeException
	 */
	public void enterAnimationCommand(String command);
	/**
 	 * Returns error message for last method called. If the last method completed successfully, then this method returns an empty string. To get the correct error message for a method, errorMessage() must be called immediately after the method is called.
 	 * @return the error message for the last method called
 	 */
	public String errorMessage();
	/**
	 * Execute command line
	 * @throws RhapsodyRuntimeException
	 */
	public int executeCommandLine(String commandLine);
	/**
 	 * Carries out model transformations in AUTOSAR projects that use one of the AR_BMT profiles for code generation. For more information on model transformation, see the help topic titled "Generating code with the Transformation Manager".
 	 * @param transformationSequence comma-separated list of defined transformers
 	 * @param showTransformedModelPackage use 1 to retain the last transformed model, 0 otherwise
 	 * @throws RhapsodyRuntimeException
 	 */
	public void executeTransformationSequence(String transformationSequence, int showTransformedModelPackage);
	/**
	 * expand environment-variable keywords in the provided string
	 * @throws RhapsodyRuntimeException
	 */
	public String expandStringKeywords(String theString);
	/**
	 * Get Rhapsody fixpack
	 * @throws RhapsodyRuntimeException
	 */
	public String fixpack();
	/**
	 * Force output to system console
	 * @throws RhapsodyRuntimeException
	 */
	public void forceOutput2Console(int val);
	/**
	 * forceRoundtrip
	 * @throws RhapsodyRuntimeException
	 */
	public void forceRoundtrip();
	/**
	 * method forceRoundtripElements
	 * @throws RhapsodyRuntimeException
	 */
	public void forceRoundtripElements(IRPCollection elements);
	/**
 	 * Generates code for the entire project, using the active component and configuration. Use IRPProject.setActiveComponent and IRPProject.setActiveConfiguration to change the active component and configuration if necessary before calling the generate method.
 	 */
	public void generate();
	/**
	 * method generateElements
	 * @throws RhapsodyRuntimeException
	 */
	public void generateElements(IRPCollection elements);
	/**
	 * generateEntireProject
	 * @throws RhapsodyRuntimeException
	 */
	public void generateEntireProject();
	/**
	 * Generate Main and Make Files
	 * @throws RhapsodyRuntimeException
	 */
	public void generateMainAndMakeFiles();
	/**
	 * generateWithDependencies
	 * @throws RhapsodyRuntimeException
	 */
	public void generateWithDependencies();
	/**
	 * getApplicationConnectionString
	 * @throws RhapsodyRuntimeException
	 */
	public String getApplicationConnectionString();
	/**
	 * Get application name
	 * @throws RhapsodyRuntimeException
	 */
	public void getApplicationName(String applicationName, String productRCPName);
	/**
 	 * Gets the full path of the Rhapsody installation folder.
 	 * @return the full path of the Rhapsody installation folder
 	 */
	public String getApplicationRoot();
	/**
	 * getApplicationStatus
	 * @throws RhapsodyRuntimeException
	 */
	public int getApplicationStatus();
	/**
	 * get property BuildNo
	 * @throws RhapsodyRuntimeException
	 */
	public String getBuildNo();
	/**
	 * get the code generation simplifiers registry
	 * @throws RhapsodyRuntimeException
	 */
	public IRPCodeGenSimplifiersRegistry getCodeGenSimplifiersRegistry();
	/**
 	 * @deprecated Support for Design Manager was removed from Rhapsody in release 8.4.
 	 */
 	@Deprecated
	public int getDMBoolProperty(String pKey);
	/**
 	 * @deprecated Support for Design Manager was removed from Rhapsody in release 8.4.
 	 */
 	@Deprecated
	public String getDMModelWorkspaceFolder();
	/**
 	 * @deprecated Support for Design Manager was removed from Rhapsody in release 8.4.
 	 */
 	@Deprecated
	public String getDMProperty(String pKey);
	/**
	 * for internal use
	 * @throws RhapsodyRuntimeException
	 */
	public IRPDiagSynthAPI getDiagSynthAPI(String clientName);
	/**
	 * get diagram of selected element
	 * @throws RhapsodyRuntimeException
	 */
	public IRPDiagram getDiagramOfSelectedElement();
	/**
 	 * Returns error message for last method called. If the last method completed successfully, then this method returns an empty string. To get the correct error message for a method, errorMessage() must be called immediately after the method is called.
 	 * @return the error message for the last method called
 	 */
	public String getErrorMessage();
	/**
	 * getExecutableFolder
	 * @throws RhapsodyRuntimeException
	 */
	public String getExecutableFolder();
	/**
	 * get the External Checker registry
	 * @throws RhapsodyRuntimeException
	 */
	public IRPExternalCheckRegistry getExternalCheckerRegistry();
	/**
	 * get the External IDE registry
	 * @throws RhapsodyRuntimeException
	 */
	public IRPExternalIDERegistry getExternalIDERegistry(String clientID);
	/**
	 * getExternalRoundtripInvoker
	 * @throws RhapsodyRuntimeException
	 */
	public IRPExternalRoundtripInvoker getExternalRoundtripInvoker();
	/**
	 * getIniFileParameterValue
	 * @throws RhapsodyRuntimeException
	 */
	public String getIniFileParameterValue(String sectionName, String paramName);
	/**
	 * get property interfaceName
	 * @throws RhapsodyRuntimeException
	 */
	public String getInterfaceName();
	/**
	 * get property isHiddenUI
	 * @throws RhapsodyRuntimeException
	 */
	public int getIsHiddenUI();
	/**
	 * get property isLoadOnDemand
	 * @throws RhapsodyRuntimeException
	 */
	public int getIsLoadOnDemand();
	/**
	 * get property Language
	 * @throws RhapsodyRuntimeException
	 */
	public String getLanguage();
	/**
	 * get list of factory properties
	 * @throws RhapsodyRuntimeException
	 */
	public IRPCollection getListOfFactoryProperties();
	/**
	 * get list of selected elements
	 * @throws RhapsodyRuntimeException
	 */
	public IRPCollection getListOfSelectedElements();
	/**
	 * get list of site properties
	 * @throws RhapsodyRuntimeException
	 */
	public IRPCollection getListOfSiteProperties();
	/**
 	 * Returns the locale for the version of Rhapsody running.
 	 * @return the locale of the version of Rhapsody running, for example, EN for the English version or JA for the Japanese version
 	 */
	public String getLocaleName();
	/**
 	 * Gets the full path of the folder used for the Rhapsody log files.
 	 * @return the full path of the folder used for the Rhapsody log files
 	 */
	public String getLogRoot();
	/**
	 * Find model element from source code
	 * @throws RhapsodyRuntimeException
	 */
	public IRPModelElement getModelElementFromSource(String sourceData, int isSourceDataIsfileName, int lineNumber);
	/**
	 * get property OMROOT
	 * @throws RhapsodyRuntimeException
	 */
	public String getOMROOT();
	/**
 	 * For internal use only.
 	 */
	public IRPowPaneMgr getOWPaneMgr(String clientID);
	/**
 	 * Returns the text displayed in the output window.
 	 * @return the text displayed in the output window
 	 */
	public String getOutputWindowText();
	/**
	 * get property projects
	 * @throws RhapsodyRuntimeException
	 */
	public IRPCollection getProjects();
	/**
	 * getRhapsodyHandleErrorFunction
	 * @throws RhapsodyRuntimeException
	 */
	public long getRhapsodyHandleErrorFunction();
	/**
	 * getRhapsodyHandleErrorFunctionLong
	 * @throws RhapsodyRuntimeException
	 */
	public long getRhapsodyHandleErrorFunctionLong();
	/**
	 * get Rhapsody search manager
	 * @throws RhapsodyRuntimeException
	 */
	public IRPSearchManager getSearchManager();
	/**
	 * get selected element
	 * @throws RhapsodyRuntimeException
	 */
	public IRPModelElement getSelectedElement();
	/**
	 * get selected graph elements
	 * @throws RhapsodyRuntimeException
	 */
	public IRPCollection getSelectedGraphElements();
	/**
 	 * Gets the currently-selected graphic elements.
 	 * @return the currently-selected graphic elements
 	 * @throws RhapsodyRuntimeException
 	 */
	public IRPSelection getSelection();
	/**
	 * get property SerialNo
	 * @throws RhapsodyRuntimeException
	 */
	public String getSerialNo();
	/**
	 * get codegeneration interface
	 * @throws RhapsodyRuntimeException
	 */
	public IRPCodeGenerator getTheCodeGeneratorInterface();
	/**
	 * get external code generator invoker
	 * @throws RhapsodyRuntimeException
	 */
	public IRPExternalCodeGeneratorInvoker getTheExternalCodeGeneratorInvoker();
	/**
	 * get integrator interface
	 * @throws RhapsodyRuntimeException
	 */
	public IRPIntegrator getTheIntegratorInterface();
	/**
	 * getTheJavaPluginsInterface
	 * @throws RhapsodyRuntimeException
	 */
	public IRPJavaPlugins getTheJavaPluginsInterface();
	/**
	 * get roundtrip interface
	 * @throws RhapsodyRuntimeException
	 */
	public IRPRoundTrip getTheRoundtripInterface();
	/**
	 * get property ToolSet
	 * @throws RhapsodyRuntimeException
	 */
	public String getToolSet();
	/**
 	 * Gets the full path of the folder where the Rhapsody data files were installed.
 	 * @return the full path of the folder where the Rhapsody data files were installed
 	 */
	public String getUserDataRoot();
	/**
 	 * Gets the full path of the UserShare folder of the Rhapsody installation.
 	 * @return the full path of the UserShare folder of the Rhapsody installation
 	 */
	public String getUserOMROOT();
	/**
	 * highlight element
	 * @throws RhapsodyRuntimeException
	 */
	public void highLightElement(IRPModelElement val);
	/**
	 * highlight by handle
	 * @throws RhapsodyRuntimeException
	 */
	public void highlightByHandle(String strHandle);
	/**
	 * import Classes
	 * @throws RhapsodyRuntimeException
	 */
	public void importClasses();
	/**
 	 * Imports a Rhapsody Design Manager model into a new Rhapsody project. After the model has been imported, the IRPProject.enableRhapsodyModelManager method can be called to enable the new project for Rhapsody Model Manager.
 	 * @param serverURL the URL of the server that hosts the Rhapsody Design Manager model that is to be imported
 	 * @param projectAreaName the name of the project area that contains the project that is to be imported
 	 * @param streamName the name of the stream from which the project should be taken
 	 * @param modelName the name of the project that is to be imported
 	 * @param saveasDirectory the directory where the new Rhapsody project should be saved. If you are only importing OSLC links, this argument should be null (since an existing Rhapsody project must already be open).
 	 * @param includeLinks 1 if the OSLC links should also be imported, 0 if the Design Manager model should be imported without the OSLC links
 	 * @return the new Rhapsody project that was created. If the saveasDirectory argument was null, the project that is currently open is returned.
 	 */
	public IRPProject importDesignManagerModel(String serverURL, String projectAreaName, String streamName, String modelName, String saveasDirectory, int includeLinks);
	/**
 	 * Imports a Simulink model into a Rhapsody model.
 	 * @param simulinkBlock the SimulinkBlock element that you created in your model (Object with SimulinkBlock stereotype applied to it)
 	 * @param matlabExePath the full path to the Matlab executable
 	 * @param simMdlFile the full path to the Simulink model file
 	 * @param simSrcFiles the full path for each of the .cpp files generated for the Simulink model (except for ert_main.cpp). If there is one then more source file, the paths should be separated by a semi-colon.
 	 * @param sampleTime the interval (in milliseconds) at which Rhapsody should activate the Simulink engine
 	 */
	public void importSyncSimulinkBlock2(IRPModelElement simulinkBlock, String matlabExePath, String simMdlFile, String simSrcFiles, String sampleTime);
	/**
	 * import tlb
	 * @throws RhapsodyRuntimeException
	 */
	public void importTlb(String pPath);
	/**
	 * Insert existing project into current workspace
	 * @throws RhapsodyRuntimeException
	 */
	public IRPProject insertProject(String filename);
	/**
 	 * @deprecated Support for Design Manager was removed from Rhapsody in release 8.4.
 	 */
 	@Deprecated
	public IRPProject insertProjectFromDesignManager(String userName, String passwd, String serverURL, String projectAreaName, String streamName, String modelName);
	/**
	 * invoke Plugins Method
	 * @throws RhapsodyRuntimeException
	 */
	public void invokePluginsMethod(String methodName);
	/**
 	 * Opens the Rhapsody Report Generator wizard.
 	 */
	public void invokeRPE();
	/**
	 * Is RhapsodyCL
	 * @throws RhapsodyRuntimeException
	 */
	public int isRhapsodyCL();
	/**
	 * Check if specified extension corresponds to any Rhapsody unit type
	 * @throws RhapsodyRuntimeException
	 */
	public int isRhapsodyFileType(String extension);
	/**
 	 * Used to log in to a Design Manager server.
 	 * @param serverURL the URL of the Design Manager server
 	 * @param alias the alias to use to log in
 	 * @return 1 if the log-in attempt was successful, 0 otherwise
 	 * @throws RhapsodyRuntimeException
 	 */
	public int loginToDesignManagerWithAlias(String serverURL, String alias);
	/**
 	 * Used to log in to a Design Manager server.
 	 * @param serverURL the URL of the Design Manager server
 	 * @param certificateLocation the location of the certificate
 	 * @param password the password to use to log in
 	 * @return 1 if the log-in attempt was successful, 0 otherwise
 	 * @throws RhapsodyRuntimeException
 	 */
	public int loginToDesignManagerWithCertificate(String serverURL, String certificateLocation, String password);
	/**
 	 * Used to log in to a Design Manager server.
 	 * @param serverURL the URL of the Design Manager server
 	 * @param userName the username to use to log in
 	 * @param password the password to use to log in
 	 * @return 1 if the log-in attempt was successful, 0 otherwise
 	 * @throws RhapsodyRuntimeException
 	 */
	public int loginToDesignManagerWithUsername(String serverURL, String userName, String password);
	/**
	 * make
	 * @throws RhapsodyRuntimeException
	 */
	public void make();
	/**
	 * mergeElements
	 * @throws RhapsodyRuntimeException
	 */
	public void mergeElements(IRPModelElement left, IRPModelElement right);
	/**
 	 * @deprecated Support for Design Manager was removed from Rhapsody in release 8.4.
 	 */
 	@Deprecated
	public IRPProject newProjectOnDesignManager(String userName, String password, String serverURL, String projectAreaName, String streamName, String modelName);
	/**
	 * File change notification
	 * @throws RhapsodyRuntimeException
	 */
	public void notifyFileChanged(String filename, int contentChanged);
	/**
	 * Open Advanced Search and Replace dialog
	 * @throws RhapsodyRuntimeException
	 */
	public void openAdvancedSearchAndReplaceDialog();
	/**
	 * method openFileList
	 * @throws RhapsodyRuntimeException
	 */
	public void openFileList(String filename);
	/**
 	 * Opens an existing Rhapsody project
 	 * @param filename the name of the .rpy file, including the full path, for example, "l:\\temp\\_sample_code\\Class_Tricks.rpy"
 	 * @return IRPProject object that represents the Rhapsody project
 	 */
	public IRPProject openProject(String filename);
	/**
 	 * @deprecated Support for Design Manager was removed from Rhapsody in release 8.4.
 	 */
 	@Deprecated
	public IRPProject openProjectFromDesignManager(String userName, String password, String serverURL, String projectAreaName, String streamName, String modelName);
	/**
 	 * @deprecated Support for Design Manager was removed from Rhapsody in release 8.4.
 	 */
 	@Deprecated
	public IRPProject openProjectFromDesignManagerAfterLogin(String serverURL, String projectAreaName, String streamName, String modelName);
	/**
	 * open Project From URL
	 * @throws RhapsodyRuntimeException
	 */
	public void openProjectFromURL(String purl);
	/**
	 * open project with last session
	 * @throws RhapsodyRuntimeException
	 */
	public IRPProject openProjectWithLastSession(String filename);
	/**
	 * open project without subunits
	 * @throws RhapsodyRuntimeException
	 */
	public IRPProject openProjectWithoutSubUnits(String filename);
	/**
	 * quit application
	 * @throws RhapsodyRuntimeException
	 */
	public void quit();
	/**
	 * rebuild
	 * @throws RhapsodyRuntimeException
	 */
	public void rebuild();
	/**
	 * rebuildEntireProject
	 * @throws RhapsodyRuntimeException
	 */
	public void rebuildEntireProject();
	/**
	 * rebuildWithDependencies
	 * @throws RhapsodyRuntimeException
	 */
	public void rebuildWithDependencies();
	/**
	 * Perform Redo
	 * @throws RhapsodyRuntimeException
	 */
	public int redo();
	/**
 	 * Changes the name of the currently-selected operation and updates any references to the operation accordingly. Corresponds to the Refactor option in the pop-up menu for operations.
 	 * @param newName the new name to use for the operation
 	 */
	public void refactorSelectedOperation(String newName);
	/**
	 * refresh all views
	 * @throws RhapsodyRuntimeException
	 */
	public void refreshAllViews();
	/**
	 * regenerate
	 * @throws RhapsodyRuntimeException
	 */
	public void regenerate();
	/**
	 * method regenerateElements
	 * @throws RhapsodyRuntimeException
	 */
	public void regenerateElements(IRPCollection elements);
	/**
	 * regenerateEntireProject
	 * @throws RhapsodyRuntimeException
	 */
	public void regenerateEntireProject();
	/**
	 * regenerateWithDependencies
	 * @throws RhapsodyRuntimeException
	 */
	public void regenerateWithDependencies();
	/**
	 * register COM client
	 * @throws RhapsodyRuntimeException
	 */
	public void registerCOMClient(int processID, String clientFilename, int magicNumber);
	/**
	 * report
	 * @throws RhapsodyRuntimeException
	 */
	public void report(String format, String outputFileName);
	/**
	 * roundtrip
	 * @throws RhapsodyRuntimeException
	 */
	public void roundtrip();
	/**
	 * method roundtripElements
	 * @throws RhapsodyRuntimeException
	 */
	public void roundtripElements(IRPCollection elements);
	/**
 	 * Runs the application that was built for the project
 	 */
	public void runApplication();
	/**
	 * runHelper
	 * @throws RhapsodyRuntimeException
	 */
	public void runHelper(String helperName);
	/**
	 * runHelperWithParameters
	 * @throws RhapsodyRuntimeException
	 */
	public void runHelperWithParameters(String helperName, String params);
	/**
	 * method saveAll
	 * @throws RhapsodyRuntimeException
	 */
	public void saveAll();
	/**
 	 * Selects multiple elements in the most recently opened diagram.
 	 * @param graphElements collection of the graphical elements that should be selected
 	 */
	public void selectGraphElements(IRPCollection graphElements);
	/**
 	 * Selects multiple items in the model browser.
 	 * @param modelElements collection of the model elements that should be selected
 	 */
	public void selectModelElements(IRPCollection modelElements);
	/**
	 * setApplicationStatus
	 * @throws RhapsodyRuntimeException
	 */
	public void setApplicationStatus(int nStatus);
	/**
	 * set Component
	 * @throws RhapsodyRuntimeException
	 */
	public void setComponent(String component);
	/**
	 * set Configuration
	 * @throws RhapsodyRuntimeException
	 */
	public void setConfiguration(String configuration);
	/**
 	 * @deprecated Support for Design Manager was removed from Rhapsody in release 8.4.
 	 */
 	@Deprecated
	public void setDMBoolProperty(String pKey, int val);
	/**
 	 * @deprecated Support for Design Manager was removed from Rhapsody in release 8.4.
 	 */
 	@Deprecated
	public void setDMProperty(String pKey, String val);
	/**
	 * set property hiddenUI
	 * @throws RhapsodyRuntimeException
	 */
	public void setHiddenUI(boolean pVal);
	/**
	 * set property isLoadOnDemand
	 * @throws RhapsodyRuntimeException
	 */
	public void setIsLoadOnDemand(int isLoadOnDemand);
	/**
	 * set property Language
	 * @throws RhapsodyRuntimeException
	 */
	public void setLanguage(String language);
	/**
	 * set log file
	 * @throws RhapsodyRuntimeException
	 */
	public void setLog(String logFile);
	/**
	 * set property ToolSet
	 * @throws RhapsodyRuntimeException
	 */
	public void setToolSet(String toolSet);
	/**
	 * setUpdateRecentFileList
	 * @throws RhapsodyRuntimeException
	 */
	public void setUpdateRecentFileList(int shouldUpdate);
	/**
	 * start undo transaction
	 * @throws RhapsodyRuntimeException
	 */
	public void startUndoTransaction();
	/**
	 * syncBuild
	 * @throws RhapsodyRuntimeException
	 */
	public void syncBuild();
	/**
	 * Terminate the Application
	 * @throws RhapsodyRuntimeException
	 */
	public void terminateApplication();
	/**
	 * Perform Undo
	 * @throws RhapsodyRuntimeException
	 */
	public int undo();
	/**
	 * unloadFromTarget
	 * @throws RhapsodyRuntimeException
	 */
	public void unloadFromTarget();
	/**
	 * unregister COM client
	 * @throws RhapsodyRuntimeException
	 */
	public void unregisterCOMClient(int processID, String clientFilename, int magicNumber);
	/**
 	 * Updates the Rhapsody Model Manager data contained in the model to reflect all the information stored by the latest version of Rhapsody. When the method is called, it updates the RMM data for the project that is currently set as the active project. It is necessary to call this method if you want to start using EWM's distributed source control feature for a project that was created with a Rhapsody release earlier than 9.0.1. The first time an RMM model is opened in a newer version of Rhapsody, users are asked if they want Rhapsody to automatically update the RMM data. You can call the method updateRmmDataToNewVersion if you don't want users to encounter this message. 
 	 * @throws RhapsodyRuntimeException
 	 */
	public void updateRmmDataToNewVersion();
	/**
	 * Get Rhapsody version
	 * @throws RhapsodyRuntimeException
	 */
	public String version();
	/**
	 * Get Rhapsody versionNumberLong
	 * @throws RhapsodyRuntimeException
	 */
	public String versionNumberLong();
	/**
 	 * Writes text to Rhapsody's Output window.
 	 * @param title the name of the tab to which the text should be written. The possible values are Log, Build, Configuration Management, Animation.
 	 * @param outputStr the text to display in the Output window
 	 */
	public void writeToOutputWindow(String title, String outputStr);
	
	/** Constant values used with elements of this type **/
	/**
This class holds constant values to be used with addToModelEx method.
	**/
	public static final class AddToModel_Mode
	{
		/**
The unit should be added to the model and its file should be copied to the project directory.

		**/
		public static final	 int		AS_UNIT_WITH_COPY		= 1;
		/**
The unit should be added to the model as an editable unit, but its file should not be copied to the project directory.
		**/
		public static final	 int		AS_UNIT_WITHOUT_COPY		= 2;
		/**
A reference to the unit should be added to the model (unit cannot be modified).
		**/
		public static final	 int		AS_REFERENCE		= 0;
	}



}

