
//Licensed Materials - Property of IBM 
//(c) Copyright IBM Corporation 2006, 2018. All Rights Reserved.

package com.telelogic.rhapsody.core;



public interface IRPInstance  extends IRPRelation {
	/**
	 * method addRelationToTheWhole
	 * @throws RhapsodyRuntimeException
	 */
	public IRPRelation addRelationToTheWhole(String relName);
	/**
 	 * Returns a collection of all the model elements that are directly under the object. This method should be used instead of the inherited method getNestedElements because the latter does not return a complete list in the case of implicit objects.
 	 * @return collection of all the model elements that are directly under the object
 	 * @throws RhapsodyRuntimeException
 	 */
	public IRPCollection getAllNestedElements();
	/**
	 * method getAttributeValue
	 * @throws RhapsodyRuntimeException
	 */
	public String getAttributeValue(String attName);
	/**
	 * method getInLinks
	 * @throws RhapsodyRuntimeException
	 */
	public IRPCollection getInLinks();
	/**
	 * get property instantiatedBy
	 * @throws RhapsodyRuntimeException
	 */
	public IRPOperation getInstantiatedBy();
	/**
	 * method getListOfInitializerArguments
	 * @throws RhapsodyRuntimeException
	 */
	public IRPCollection getListOfInitializerArguments();
	/**
	 * method getOutLinks
	 * @throws RhapsodyRuntimeException
	 */
	public IRPCollection getOutLinks();
	/**
	 * method setAttributeValue
	 * @throws RhapsodyRuntimeException
	 */
	public void setAttributeValue(String attName, String attValue);
	/**
	 * method setExplicit
	 * @throws RhapsodyRuntimeException
	 */
	public void setExplicit();
	/**
	 * method setImplicit
	 * @throws RhapsodyRuntimeException
	 */
	public void setImplicit();
	/**
	 * method setInitializerArgumentValue
	 * @throws RhapsodyRuntimeException
	 */
	public void setInitializerArgumentValue(String argName, String argValue);
	/**
	 * set property instantiatedBy
	 * @throws RhapsodyRuntimeException
	 */
	public void setInstantiatedBy(IRPOperation instantiatedBy);
	/**
 	 * Updates the views on the Rhapsody Model Manager server for all the diagrams contained in the instance.
 	 * @param enforceUpdate Use 0 to specify that a view should be updated only if changes that affect the diagram were made since the last update. Use 1 to specify that views should be updated regardless of whether or not changes that affect the diagram were made since the last update.
 	 * @return the number of views that were updated on the server. If no diagrams require an update, 0 is returned. If the update attempt failed, -1 is returned.
 	 * @throws RhapsodyRuntimeException
 	 */
	public int updateContainedDiagramsOnServer(int enforceUpdate);


}

