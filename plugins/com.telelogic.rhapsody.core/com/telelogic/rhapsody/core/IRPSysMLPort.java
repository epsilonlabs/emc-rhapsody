
//Licensed Materials - Property of IBM 
//(c) Copyright IBM Corporation 2006, 2018. All Rights Reserved.

package com.telelogic.rhapsody.core;


/**
 * The IRPSysMLPort interface represents flowport elements in Rhapsody models.
 */
public interface IRPSysMLPort  extends IRPInstance {
	/**
 	 * This method is used to create a link between flowports on two parts.
 	 * @param fromPart the "from" part for the link
 	 * @param toPart the "to" part for the link
 	 * @param assoc use Null for this argument (it is not relevant for links between flowports) 
 	 * @param toPort the "to" port for the link
 	 * @param newOwner the package that should be the owner of the link created
 	 * @return the link that was created
 	 * @throws RhapsodyRuntimeException
 	 */
	public IRPLink addLink(IRPInstance fromPart, IRPInstance toPart, IRPRelation assoc, IRPSysMLPort toPort, IRPPackage newOwner);
	/**
 	 * Checks whether the flowport was specified as conjugated.
 	 * @return 1 if the flowport was specified as conjugated, 0 otherwise
 	 * @throws RhapsodyRuntimeException
 	 */
	public int getIsReversed();
	/**
 	 * Returns the direction that was specified for the flowport.
 	 * @return the direction that was specified for the flowport - will be one of the following values: "In", "Out", "InOut" 
 	 * @throws RhapsodyRuntimeException
 	 */
	public String getPortDirection();
	/**
 	 * Returns the type that was specified for the flowport.
 	 * @return the type that was specified for the flowport
 	 * @throws RhapsodyRuntimeException
 	 */
	public IRPClassifier getType();
	/**
 	 * Specifies whether the flowport should be conjugated
 	 * @param isReversed use 1 to specify that the flowport should be conjugated, 0 otherwise
 	 * @throws RhapsodyRuntimeException
 	 */
	public void setIsReversed(int isReversed);
	/**
 	 * Sets the direction of the flowport.
 	 * @param portDirection the direction to use for the flowport. The valid values are "In", "Out", and "InOut".
 	 * @throws RhapsodyRuntimeException
 	 */
	public void setPortDirection(String portDirection);
	/**
 	 * Sets the type for the flowport.
 	 * @param type the type to use for the flowport
 	 * @throws RhapsodyRuntimeException
 	 */
	public void setType(IRPClassifier type);


}

