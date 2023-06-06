
//Licensed Materials - Property of IBM 
//(c) Copyright IBM Corporation 2006, 2018. All Rights Reserved.

package com.telelogic.rhapsody.core;



public interface IRPUseCase  extends IRPClassifier {
	/**
	 * method addDescribingDiagram
	 * @throws RhapsodyRuntimeException
	 */
	public void addDescribingDiagram(IRPDiagram diagram);
	/**
 	 * Adds a new event reception, using the specified event.
 	 * @param name the name to use for the new event reception
 	 * @param event the event that should be associated with the new event reception
 	 * @return the event reception that was created
 	 * @throws RhapsodyRuntimeException
 	 */
	public IRPEventReception addEventReceptionWithEvent(String name, IRPEvent event);
	/**
	 * method addExtensionPoint
	 * @throws RhapsodyRuntimeException
	 */
	public void addExtensionPoint(String entryPoint);
	/**
	 * method deleteDescribingDiagram
	 * @throws RhapsodyRuntimeException
	 */
	public void deleteDescribingDiagram(IRPDiagram diagram);
	/**
	 * method deleteEntryPoint
	 * @throws RhapsodyRuntimeException
	 */
	public void deleteEntryPoint(String entryPoint);
	/**
	 * method deleteExtensionPoint
	 * @throws RhapsodyRuntimeException
	 */
	public void deleteExtensionPoint(String entryPoint);
	/**
	 * method findEntryPoint
	 * @throws RhapsodyRuntimeException
	 */
	public String findEntryPoint(IRPGeneralization gen);
	/**
	 * method findExtensionPoint
	 * @throws RhapsodyRuntimeException
	 */
	public String findExtensionPoint(IRPGeneralization gen);
	/**
	 * method getDescribingDiagram
	 * @throws RhapsodyRuntimeException
	 */
	public IRPDiagram getDescribingDiagram(String name);
	/**
	 * get property describingDiagrams
	 * @throws RhapsodyRuntimeException
	 */
	public IRPCollection getDescribingDiagrams();
	/**
	 * get property entryPoints
	 * @throws RhapsodyRuntimeException
	 */
	public IRPCollection getEntryPoints();
	/**
	 * get property extensionPoints
	 * @throws RhapsodyRuntimeException
	 */
	public IRPCollection getExtensionPoints();
	/**
	 * get property isBehaviorOverriden
	 * @throws RhapsodyRuntimeException
	 */
	public int getIsBehaviorOverriden();
	/**
	 * set property isBehaviorOverriden
	 * @throws RhapsodyRuntimeException
	 */
	public void setIsBehaviorOverriden(int isBehaviorOverriden);
	/**
 	 * Updates the views on the Rhapsody Model Manager server for all the diagrams contained in the use case.
 	 * @param enforceUpdate Use 0 to specify that a view should be updated only if changes that affect the diagram were made since the last update. Use 1 to specify that views should be updated regardless of whether or not changes that affect the diagram were made since the last update.
 	 * @return the number of views that were updated on the server. If no diagrams require an update, 0 is returned. If the update attempt failed, -1 is returned.
 	 * @throws RhapsodyRuntimeException
 	 */
	public int updateContainedDiagramsOnServer(int enforceUpdate);


}

