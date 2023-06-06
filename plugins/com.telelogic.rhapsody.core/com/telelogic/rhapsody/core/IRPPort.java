
//Licensed Materials - Property of IBM 
//(c) Copyright IBM Corporation 2006, 2018. All Rights Reserved.

package com.telelogic.rhapsody.core;


 /**
  * The IRPPort interface represents ports in Rhapsody models.
  */
public interface IRPPort  extends IRPInstance {
	/**
	 * method addRProvidedInterface
	 * @throws RhapsodyRuntimeException
	 */
	public void addProvidedInterface(IRPClass newVal);
	/**
	 * method addRequiredInterface
	 * @throws RhapsodyRuntimeException
	 */
	public void addRequiredInterface(IRPClass newVal);
	/**
	 * get property isBehavioral
	 * @throws RhapsodyRuntimeException
	 */
	public int getIsBehavioral();
	/**
	 * get property isReversed
	 * @throws RhapsodyRuntimeException
	 */
	public int getIsReversed();
	/**
 	 * Returns the contract defined for the port.
 	 * @return the contract defined for the port
 	 * @throws RhapsodyRuntimeException
 	 */
	public IRPClass getPortContract();
	/**
	 * get property providedInterfaces
	 * @throws RhapsodyRuntimeException
	 */
	public IRPCollection getProvidedInterfaces();
	/**
	 * get property requiredInterfaces
	 * @throws RhapsodyRuntimeException
	 */
	public IRPCollection getRequiredInterfaces();
	/**
	 * method removeProvidedInterface
	 * @throws RhapsodyRuntimeException
	 */
	public void removeProvidedInterface(IRPClass newVal);
	/**
	 * method removeRequiredInterface
	 * @throws RhapsodyRuntimeException
	 */
	public void removeRequiredInterface(IRPClass newVal);
	/**
	 * set property isBehavioral
	 * @throws RhapsodyRuntimeException
	 */
	public void setIsBehavioral(int isBehavioral);
	/**
	 * set property isReversed
	 * @throws RhapsodyRuntimeException
	 */
	public void setIsReversed(int isReversed);
	/**
 	 * Used to specify the contract for the port.
 	 * @param portContract the contract to use for the port
 	 */
	public void setPortContract(IRPClass portContract);

	/**
	 * This function exists for backward compatability.
	 * Use getPortContract instead
	 **/
	public IRPClass getContract();
	/**
	 * This function exists for backward compatability.
	 * Use setPortContract instead
	 **/
	public void setContract(IRPClass contract);

}

