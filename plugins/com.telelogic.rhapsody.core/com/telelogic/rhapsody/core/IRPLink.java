
//Licensed Materials - Property of IBM 
//(c) Copyright IBM Corporation 2006, 2018. All Rights Reserved.

package com.telelogic.rhapsody.core;


/**
 * The IRPLink interface represents links in Rhapsody models.
 */
public interface IRPLink  extends IRPUnit {
	/**
	 * get property end1Multiplicity
	 * @throws RhapsodyRuntimeException
	 */
	public String getEnd1Multiplicity();
	/**
	 * get property end1Name
	 * @throws RhapsodyRuntimeException
	 */
	public String getEnd1Name();
	/**
	 * get property end2Multiplicity
	 * @throws RhapsodyRuntimeException
	 */
	public String getEnd2Multiplicity();
	/**
	 * get property end2Name
	 * @throws RhapsodyRuntimeException
	 */
	public String getEnd2Name();
	/**
	 * get property from
	 * @throws RhapsodyRuntimeException
	 */
	public IRPInstance getFrom();
	/**
	 * get property fromElement
	 * @throws RhapsodyRuntimeException
	 */
	public IRPModelElement getFromElement();
	/**
	 * get property fromPort
	 * @throws RhapsodyRuntimeException
	 */
	public IRPPort getFromPort();
	/**
	 * get property fromSysMLPort
	 * @throws RhapsodyRuntimeException
	 */
	public IRPSysMLPort getFromSysMLPort();
	/**
	 * get property instantiates
	 * @throws RhapsodyRuntimeException
	 */
	public IRPRelation getInstantiates();
	/**
	 * get property other
	 * @throws RhapsodyRuntimeException
	 */
	public IRPLink getOther();
	/**
 	 * Returns the target of a link. When a link is connected to an object directly or via a port on the object, the method returns the "to" object. When a link is connected to a port on a class, the method returns the "to" port.
 	 * @return the target of the link 
 	 */
	public IRPInstance getTo();
	/**
	 * get property toElement
	 * @throws RhapsodyRuntimeException
	 */
	public IRPModelElement getToElement();
	/**
 	 * Returns the port through which a link reaches a target object. When a link is connected to a port on an object, the method returns the port on the "to" object. When a link is connected to a port on a class, or is connected to an object directly, the method returns null.
 	 * @return the port through which the link reaches its target object 
 	 */
	public IRPPort getToPort();
	/**
	 * get property toSysMLPort
	 * @throws RhapsodyRuntimeException
	 */
	public IRPSysMLPort getToSysMLPort();
	/**
	 * set property end1Multiplicity
	 * @throws RhapsodyRuntimeException
	 */
	public void setEnd1Multiplicity(String end1Multiplicity);
	/**
	 * set property end1Name
	 * @throws RhapsodyRuntimeException
	 */
	public void setEnd1Name(String end1Name);
	/**
	 * set property end2Multiplicity
	 * @throws RhapsodyRuntimeException
	 */
	public void setEnd2Multiplicity(String end2Multiplicity);
	/**
	 * set property end2Name
	 * @throws RhapsodyRuntimeException
	 */
	public void setEnd2Name(String end2Name);
	/**
	 * method setInstantiates
	 * @throws RhapsodyRuntimeException
	 */
	public void setInstantiates(IRPRelation pVal);


}

