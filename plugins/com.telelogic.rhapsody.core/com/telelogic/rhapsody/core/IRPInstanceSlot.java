
//Licensed Materials - Property of IBM 
//(c) Copyright IBM Corporation 2006, 2018. All Rights Reserved.

package com.telelogic.rhapsody.core;



public interface IRPInstanceSlot  extends IRPModelElement {
	/**
	 * method addElementValue
	 * @throws RhapsodyRuntimeException
	 */
	public IRPInstanceValue addElementValue(IRPModelElement val);
	/**
	 * method addStringValue
	 * @throws RhapsodyRuntimeException
	 */
	public IRPLiteralSpecification addStringValue(String val);
	/**
	 * get property slotProperty
	 * @throws RhapsodyRuntimeException
	 */
	public IRPModelElement getSlotProperty();
	/**
	 * get property values
	 * @throws RhapsodyRuntimeException
	 */
	public IRPCollection getValues();
	/**
	 * set property slotProperty
	 * @throws RhapsodyRuntimeException
	 */
	public void setSlotProperty(IRPModelElement slotProperty);


}

