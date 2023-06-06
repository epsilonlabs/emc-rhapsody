
//Licensed Materials - Property of IBM 
//(c) Copyright IBM Corporation 2006, 2018. All Rights Reserved.

package com.telelogic.rhapsody.core;



public interface IRPMessage  extends IRPModelElement {
	/**
	 * method addSourceExecutionOccurrence
	 * @throws RhapsodyRuntimeException
	 */
	public IRPExecutionOccurrence addSourceExecutionOccurrence();
	/**
	 * method addTargetExecutionOccurrence
	 * @throws RhapsodyRuntimeException
	 */
	public IRPExecutionOccurrence addTargetExecutionOccurrence();
	/**
	 * get property actualParameterList
	 * @throws RhapsodyRuntimeException
	 */
	public IRPCollection getActualParameterList();
	/**
	 * get property communicationConnection
	 * @throws RhapsodyRuntimeException
	 */
	public IRPAssociationRole getCommunicationConnection();
	/**
	 * get property condition
	 * @throws RhapsodyRuntimeException
	 */
	public String getCondition();
	/**
 	 * Gets the text of the Duration Constraint.
 	 * @return the text of the Duration Constraint
 	 */
	public String getDurationConstraint();
	/**
 	 * Gets the text of the Duration Observation.
 	 * @return the text of the Duration Observation
 	 */
	public String getDurationObservation();
	/**
	 * get property flowPort
	 * @throws RhapsodyRuntimeException
	 */
	public IRPSysMLPort getFlowPort();
	/**
	 * get property formalInterfaceItem
	 * @throws RhapsodyRuntimeException
	 */
	public IRPInterfaceItem getFormalInterfaceItem();
	/**
 	 * Returns the model element associated with an action block, condition mark, timeout, or canceled timeout, in a sequence diagram.
 	 * @return the model element associated with an action block, condition mark, timeout, or canceled timeout, in a sequence diagram
 	 */
	public IRPModelElement getFormalType();
	/**
 	 * Gets the text of the Invariant field for the state invariant.
 	 * @return the text of the Invariant field
 	 */
	public String getInvariant();
	/**
	 * get property messageType
	 * @throws RhapsodyRuntimeException
	 */
	public String getMessageType();
	/**
	 * get property Port
	 * @throws RhapsodyRuntimeException
	 */
	public IRPPort getPort();
	/**
	 * get property returnValue
	 * @throws RhapsodyRuntimeException
	 */
	public String getReturnValue();
	/**
	 * get property sequenceNumber
	 * @throws RhapsodyRuntimeException
	 */
	public String getSequenceNumber();
	/**
	 * method getSignature
	 * @throws RhapsodyRuntimeException
	 */
	public String getSignature();
	/**
	 * get property source
	 * @throws RhapsodyRuntimeException
	 */
	public IRPClassifierRole getSource();
	/**
	 * get property sourceExecutionOccurrence
	 * @throws RhapsodyRuntimeException
	 */
	public IRPExecutionOccurrence getSourceExecutionOccurrence();
	/**
	 * get property target
	 * @throws RhapsodyRuntimeException
	 */
	public IRPClassifierRole getTarget();
	/**
	 * get property targetExecutionOccurrence
	 * @throws RhapsodyRuntimeException
	 */
	public IRPExecutionOccurrence getTargetExecutionOccurrence();
	/**
 	 * Gets the text for the Time Constraint that was applied to this state variant.
 	 * @return the text for the Time Constraint that was applied to this state variant
 	 */
	public String getTimeConstraint();
	/**
 	 * Gets the text of the Time Observation.
 	 * @return the text of the Time Observation
 	 */
	public String getTimeObservation();
	/**
	 * get property timerValue
	 * @throws RhapsodyRuntimeException
	 */
	public String getTimerValue();
	/**
	 * method reroute
	 * @throws RhapsodyRuntimeException
	 */
	public void reroute();
	/**
	 * method setActualParameterList
	 * @throws RhapsodyRuntimeException
	 */
	public void setActualParameterList(IRPCollection pVal);
	/**
 	 * Modifies the text of this Duration Constraint.
 	 * @param durationConstraint the text to use for the Duration Constraint
 	 */
	public void setDurationConstraint(String durationConstraint);
	/**
 	 * Modifies the text of this Duration Observation.
 	 * @param durationObservation the text to use for the Duration Observation
 	 */
	public void setDurationObservation(String durationObservation);
	/**
	 * set property flowPort
	 * @throws RhapsodyRuntimeException
	 */
	public void setFlowPort(IRPSysMLPort flowPort);
	/**
 	 * Sets the realization of a message.
 	 * @param newVal the operation or other IRPInterfaceItem object to use for the realization of the message
 	 * @throws RhapsodyRuntimeException
 	 */
	public void setFormalInterfaceItem(IRPInterfaceItem newVal);
	/**
 	 * Used to specify the model element that should be associated with an action block, condition mark, timeout, or canceled timeout, in a sequence diagram.
 	 * @param formalType the model element that should be associated with this sequence diagram element
 	 */
	public void setFormalType(IRPModelElement formalType);
	/**
 	 * Modifies the text of the Invariant field for the state invariant.
 	 * @param invariant the text to use for the Invariant field
 	 */
	public void setInvariant(String invariant);
	/**
	 * set property Port
	 * @throws RhapsodyRuntimeException
	 */
	public void setPort(IRPPort port);
	/**
	 * set property returnValue
	 * @throws RhapsodyRuntimeException
	 */
	public void setReturnValue(String returnValue);
	/**
 	 * Modifies the text of this Time Constraint.
 	 * @param timeConstraint the text to use for this Time Constraint
 	 */
	public void setTimeConstraint(String timeConstraint);
	/**
 	 * Modifies the text of this Time Observation.
 	 * @param timeObservation the text to use for the Time Observation
 	 */
	public void setTimeObservation(String timeObservation);
	/**
	 * set property timerValue
	 * @throws RhapsodyRuntimeException
	 */
	public void setTimerValue(String timerValue);


}

