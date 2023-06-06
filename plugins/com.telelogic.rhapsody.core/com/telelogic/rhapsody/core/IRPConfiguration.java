
//Licensed Materials - Property of IBM 
//(c) Copyright IBM Corporation 2006, 2018. All Rights Reserved.

package com.telelogic.rhapsody.core;


 /**
  * The IRPConfiguration interface represents a code generation configuration within a specific component.
  */
public interface IRPConfiguration  extends IRPModelElement {
	/**
	 * method addInitialInstance
	 * @throws RhapsodyRuntimeException
	 */
	public void addInitialInstance(IRPModelElement newVal);
	/**
	 * method addPackageToInstrumentationScope
	 * @throws RhapsodyRuntimeException
	 */
	public void addPackageToInstrumentationScope(IRPPackage pVal);
	/**
	 * method addToInstrumentationScope
	 * @throws RhapsodyRuntimeException
	 */
	public void addToInstrumentationScope(IRPClassifier pVal);
	/**
	 * method deleteInitialInstance
	 * @throws RhapsodyRuntimeException
	 */
	public void deleteInitialInstance(IRPModelElement newVal);
	/**
	 * get property additionalSources
	 * @throws RhapsodyRuntimeException
	 */
	public String getAdditionalSources();
	/**
 	 * Checks whether the instrumentation mode selected for the configuration applies to all elements or just selected elements. This corresponds to the Instrumentation Scope options in the Advanced Instrumentation Settings dialog for configurations.
 	 * @return 1 if the instrumentation mode applies to all elements, 0 otherwise
 	 */
	public int getAllElementsInInstrumentationScope();
	/**
	 * get property buildSet
	 * @throws RhapsodyRuntimeException
	 */
	public String getBuildSet();
	/**
	 * get property compilerSwitches
	 * @throws RhapsodyRuntimeException
	 */
	public String getCompilerSwitches();
	/**
	 * method getDirectory
	 * @throws RhapsodyRuntimeException
	 */
	public String getDirectory(int fullpath, String nawName);
	/**
	 * method getExecutableName
	 * @throws RhapsodyRuntimeException
	 */
	public String getExecutableName();
	/**
	 * get property generateCodeForActors
	 * @throws RhapsodyRuntimeException
	 */
	public int getGenerateCodeForActors();
	/**
	 * get property includePath
	 * @throws RhapsodyRuntimeException
	 */
	public String getIncludePath();
	/**
	 * get method initialInstances
	 * @throws RhapsodyRuntimeException
	 */
	public IRPCollection getInitialInstances();
	/**
	 * get property initializationCode
	 * @throws RhapsodyRuntimeException
	 */
	public String getInitializationCode();
	/**
	 * get property instrumentationScope
	 * @throws RhapsodyRuntimeException
	 */
	public IRPCollection getInstrumentationScope();
	/**
	 * get property instrumentationType
	 * @throws RhapsodyRuntimeException
	 */
	public String getInstrumentationType();
	/**
	 * method getItsComponent
	 * @throws RhapsodyRuntimeException
	 */
	public IRPComponent getItsComponent();
	/**
	 * get property libraries
	 * @throws RhapsodyRuntimeException
	 */
	public String getLibraries();
	/**
	 * get property linkSwitches
	 * @throws RhapsodyRuntimeException
	 */
	public String getLinkSwitches();
	/**
	 * method getMainName
	 * @throws RhapsodyRuntimeException
	 */
	public String getMainName();
	/**
	 * method getMakefileName
	 * @throws RhapsodyRuntimeException
	 */
	public String getMakefileName(int fullpath);
	/**
	 * get property path
	 * @throws RhapsodyRuntimeException
	 */
	public String getPath(int fullPath);
	/**
	 * get property scopeType
	 * @throws RhapsodyRuntimeException
	 */
	public String getScopeType();
	/**
	 * get property standardHeaders
	 * @throws RhapsodyRuntimeException
	 */
	public String getStandardHeaders();
	/**
 	 * Returns the statechart implementation specified for the configuration - reusable or flat.
 	 * @return the statechart implementation specified for the configuration
 	 * @throws RhapsodyRuntimeException
 	 */
	public String getStatechartImplementation();
	/**
	 * method getTargetName
	 * @throws RhapsodyRuntimeException
	 */
	public String getTargetName(int fullpath);
	/**
 	 * Returns the time model specified for the configuration - real or simulated.
 	 * @return the time model specified for the configuration
 	 * @throws RhapsodyRuntimeException
 	 */
	public String getTimeModel();
	/**
	 * method needsCodeGeneration checks is code generation is needed
	 * @throws RhapsodyRuntimeException
	 */
	public int needsCodeGeneration();
	/**
	 * method removeFromInstrumentationScope
	 * @throws RhapsodyRuntimeException
	 */
	public void removeFromInstrumentationScope(IRPClassifier pVal);
	/**
	 * method removePackageFromInstrumentationScope
	 * @throws RhapsodyRuntimeException
	 */
	public void removePackageFromInstrumentationScope(IRPPackage pVal);
	/**
	 * set property additionalSources
	 * @throws RhapsodyRuntimeException
	 */
	public void setAdditionalSources(String additionalSources);
	/**
	 * set property allElementsInInstrumentationScope
	 * @throws RhapsodyRuntimeException
	 */
	public void setAllElementsInInstrumentationScope(int allElementsInInstrumentationScope);
	/**
	 * set property buildSet
	 * @throws RhapsodyRuntimeException
	 */
	public void setBuildSet(String buildSet);
	/**
	 * set property compilerSwitches
	 * @throws RhapsodyRuntimeException
	 */
	public void setCompilerSwitches(String compilerSwitches);
	/**
	 * method setDirectory
	 * @throws RhapsodyRuntimeException
	 */
	public void setDirectory(int fullpath, String newName);
	/**
	 * set property generateCodeForActors
	 * @throws RhapsodyRuntimeException
	 */
	public void setGenerateCodeForActors(int generateCodeForActors);
	/**
	 * set property includePath
	 * @throws RhapsodyRuntimeException
	 */
	public void setIncludePath(String includePath);
	/**
	 * set property initializationCode
	 * @throws RhapsodyRuntimeException
	 */
	public void setInitializationCode(String initializationCode);
	/**
	 * set property instrumentationType
	 * @throws RhapsodyRuntimeException
	 */
	public void setInstrumentationType(String instrumentationType);
	/**
	 * method setItsComponent
	 * @throws RhapsodyRuntimeException
	 */
	public void setItsComponent(IRPComponent newVal);
	/**
	 * set property libraries
	 * @throws RhapsodyRuntimeException
	 */
	public void setLibraries(String libraries);
	/**
	 * set property linkSwitches
	 * @throws RhapsodyRuntimeException
	 */
	public void setLinkSwitches(String linkSwitches);
	/**
	 * set property scopeType
	 * @throws RhapsodyRuntimeException
	 */
	public void setScopeType(String scopeType);
	/**
	 * set property standardHeaders
	 * @throws RhapsodyRuntimeException
	 */
	public void setStandardHeaders(String standardHeaders);
	/**
 	 * Specifies the statechart implementation to use for the configuration. The parameter value can be "reusable" or "flat". Note that the parameter must be lower-case.
 	 * @param statechartImplementation the statechart implementation to use for the configuration
 	 * @throws RhapsodyRuntimeException
 	 */
	public void setStatechartImplementation(String statechartImplementation);
	/**
 	 * Specifies the time model to use for the configuration. The parameter value can be "real" or "simulated". Note that the parameter must be lower-case.
 	 * @param timeModel the time model to use for the configuration
 	 * @throws RhapsodyRuntimeException
 	 */
	public void setTimeModel(String timeModel);


}

