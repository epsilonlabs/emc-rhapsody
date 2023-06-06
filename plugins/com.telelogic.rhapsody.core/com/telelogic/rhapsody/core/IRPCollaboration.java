
//Licensed Materials - Property of IBM 
//(c) Copyright IBM Corporation 2006, 2018. All Rights Reserved.

package com.telelogic.rhapsody.core;


 /**
  * The IRPCollaboration interface represents the capabilities included in sequence diagrams and communications diagrams. To get the IRPCollaboration object underlying a sequence diagram or a communication diagram, use the methods IRPSequenceDiagram.getLogicalCollaboration() and IRPCollaborationDiagram.getLogicalCollaboration().
  */
public interface IRPCollaboration  extends IRPModelElement {
	/**
 	 * Adds a new action block to the specified classifier.
 	 * @param classifier the classifier to which the action block should be added
 	 * @return the action block that was created
 	 */
	public IRPActionBlock addActionBlock(IRPClassifierRole classifier);
	/**
 	 * Adds a cancelled timeout to the specified instance line.
 	 * @param receiver the instance line that the cancelled timeout should be added to
 	 * @return the cancelled timeout that was added
 	 */
	public IRPMessage addCancelledTimeout(IRPClassifierRole receiver);
	/**
 	 * Adds an instance line to a sequence diagram.
 	 * @param newVal the name to use for the new instance line
 	 * @param cls the class that the instance line is based on
 	 * @return the instance line that was added
 	 */
	public IRPClassifierRole addClassifierRole(String newVal, IRPClassifier cls);
	/**
	 * method addClassifierRoleByName
	 * @throws RhapsodyRuntimeException
	 */
	public IRPClassifierRole addClassifierRoleByName(String newVal, String classFullPath);
	/**
	 * method addClassifierRoleForInstance
	 * @throws RhapsodyRuntimeException
	 */
	public IRPClassifierRole addClassifierRoleForInstance(IRPInstance inst);
	/**
 	 * Adds a condition mark to the specified instance line.
 	 * @param classifier the instance line to which the condition mark should be added
 	 * @return the condition mark that was created
 	 */
	public IRPMessage addConditionMark(IRPClassifierRole classifier);
	/**
 	 * Adds a Create Arrow to a sequence diagram.
 	 * @param interItem the constructor for the object to be created
 	 * @param actualParamList string representing the arguments to pass to the constructor. The string provided should be a comma-separated list of arguments
 	 * @param sender the instance line at which the Create Arrow begins
 	 * @param receiver the instance line at which the Create Arrow ends (representing the object to be created)  
 	 * @return the Create Arrow that was added to the diagram
 	 */
	public IRPMessage addCtor(IRPInterfaceItem interItem, String actualParamList, IRPClassifierRole sender, IRPClassifierRole receiver);
	/**
	 * method addDataFlow
	 * @throws RhapsodyRuntimeException
	 */
	public IRPMessage addDataFlow(IRPSysMLPort flowPort, String value, IRPClassifierRole sender, IRPClassifierRole receiver);
	/**
 	 * Adds a destruction event to the specified lifeline.
 	 * @param classifier the lifeline that the destruction event should be added to
 	 * @return the destruction event that was created
 	 */
	public IRPMessage addDestructionEvent(IRPClassifierRole classifier);
	/**
 	 * Adds a Destroy Arrow to a sequence diagram.
 	 * @param interItem the destructor for the object to be destroyed
 	 * @param actualParamList since destructors do not take arguments, use an empty string "" for this parameter
 	 * @param sender the instance line at which the Destroy Arrow begins
 	 * @param receiver the instance line at which the Destroy Arrow ends (representing the object to be destroyed)
 	 * @return the Destroy Arrow that was added to the diagram
 	 */
	public IRPMessage addDtor(IRPInterfaceItem interItem, String actualParamList, IRPClassifierRole sender, IRPClassifierRole receiver);
	/**
 	 * Adds a Duration Constraint to the specified state invariants.
 	 * @param durationConstraint the text to display above the new Duration Constraint
 	 * @param startState the state invariant at which the new Duration Constraint should begin
 	 * @param endState the state invariant at which the new Duration Constraint should end. If you want the Duration Constraint to cover only a single state invariant, specify the same state invariant for both the startState parameter and the endState parameter
 	 * @return the Duration Constraint that was created
 	 */
	public IRPMessage addDurationConstraint(String durationConstraint, IRPMessage startState, IRPMessage endState);
	/**
 	 * Adds a Duration Observation to the specified states invariants.
 	 * @param durationObservation the text to display above the new Duration Observation
 	 * @param startState the state invariant at which the new Duration Observation should begin
 	 * @param endState the state invariant at which the new Duration Observation should end. If you want the Duration Observation to cover only a single state invariant, specify the same state invariant for both the startState parameter and the endState parameter
 	 * @return the Duration Observation that was created
 	 */
	public IRPMessage addDurationObservation(String durationObservation, IRPMessage startState, IRPMessage endState);
	/**
 	 * Adds a Found Message to the specified lifeline.
 	 * @param receiver the lifeline that the Found Message should be added to
 	 * @return the Found Message that was created
 	 */
	public IRPMessage addFoundMessage(IRPClassifierRole receiver);
	/**
 	 * Adds an interaction occurrence.
 	 * @return the interaction occurrence that was created
 	 */
	public IRPInteractionOccurrence addInteractionOccurrence();
	/**
 	 * Adds an interaction operator to a sequence diagram.
 	 * @return the interaction operator that was added
 	 */
	public IRPInteractionOperator addInteractionOperator();
	/**
 	 * Adds a Lost Message to the specified lifeline.
 	 * @param sender the lifeline that the Lost Message should be added to
 	 * @return the Lost Message that was created
 	 */
	public IRPMessage addLostMessage(IRPClassifierRole sender);
	/**
 	 * Adds a message to a sequence diagram.
 	 * @param interItem the operation call represented by the message
 	 * @param actualParamList the arguments to pass to the operation. If the operation does not take any arguments, use an empty string "" for this parameter
 	 * @param sender the instance line sending the message
 	 * @param receiver the instance line receiving the message
 	 * @return the message that was added to the diagram
 	 */
	public IRPMessage addMessage(IRPInterfaceItem interItem, String actualParamList, IRPClassifierRole sender, IRPClassifierRole receiver);
	/**
	 * method addReplyMessage
	 * @throws RhapsodyRuntimeException
	 */
	public IRPMessage addReplyMessage(IRPInterfaceItem interItem, String actualParamList, IRPClassifierRole sender, IRPClassifierRole receiver);
	/**
 	 * Adds a State Invariant to the specified lifeline.
 	 * @param invariant the text to use for the Invariant field of the new State Invariant
 	 * @param classifier the lifeline that the State Invariant should be added to
 	 * @return the State Invariant that was created
 	 */
	public IRPMessage addStateInvariant(String invariant, IRPClassifierRole classifier);
	/**
 	 * Adds a System Border element to a sequence diagram.
 	 * @return the System Border element that was added
 	 */
	public IRPClassifierRole addSystemBorder();
	/**
 	 * Adds a Time Constraint to the specified state invariant.
 	 * @param timeConstraint the text to display for the new Time Constraint
 	 * @param state the state invariant to which the new Time Constraint should be added
 	 * @return the Time Constraint that was created
 	 */
	public IRPMessage addTimeConstraint(String timeConstraint, IRPMessage state);
	/**
 	 * Adds a Time Interval to the specified lifeline.
 	 * @param receiver the lifeline that the Time Interval should be added to
 	 * @return the Time Interval that was created
 	 */
	public IRPMessage addTimeInterval(IRPClassifierRole receiver);
	/**
 	 * Adds a Time Observation to the specified state invariant.
 	 * @param timeObservation the text to display for the new Time Observation
 	 * @param state the state invariant to which the new Time Observation should be added
 	 * @return the Time Observation that was created
 	 */
	public IRPMessage addTimeObservation(String timeObservation, IRPMessage state);
	/**
 	 * Adds a timeout to a sequence diagram.
 	 * @param interItem use null for this parameter
 	 * @param actualParamList duration of timeout in milliseconds
 	 * @param sender the instance line that the timeout should be added to
 	 * @param receiver use null for this parameter
 	 * @return the timeout created
 	 */
	public IRPMessage addTimeout(IRPInterfaceItem interItem, String actualParamList, IRPClassifierRole sender, IRPClassifierRole receiver);
	/**
 	 * Generates a sequence diagram from the content of the IRPCollaboration object.
 	 * @param newVal the name to give to the new diagram
 	 * @param owner the package to which the new diagram should belong
 	 * @return the sequence diagram created
 	 */
	public IRPSequenceDiagram generateSequence(String newVal, IRPPackage owner);
	/**
	 * get property activationCondition
	 * @throws RhapsodyRuntimeException
	 */
	public String getActivationCondition();
	/**
	 * get property activationMode
	 * @throws RhapsodyRuntimeException
	 */
	public String getActivationMode();
	/**
	 * method getActivator
	 * @throws RhapsodyRuntimeException
	 */
	public IRPMessage getActivator(IRPMessage msg);
	/**
	 * get property associations
	 * @throws RhapsodyRuntimeException
	 */
	public IRPCollection getAssociations();
	/**
 	 * Returns a collection of all the instance lines in the sequence diagram.
 	 * @return all the instance lines in the sequence diagram
 	 */
	public IRPCollection getClassifier();
	/**
	 * method getConcurrentGroup
	 * @throws RhapsodyRuntimeException
	 */
	public IRPCollection getConcurrentGroup(IRPMessage msg);
	/**
 	 * Returns a collection of all the Execution Occurrences in the diagram.
 	 * @return all the Execution Occurrences in the diagram
 	 */
	public IRPCollection getExecutionOccurrences();
	/**
 	 * Returns a collection of all the interaction occurrences in the sequence diagram.
 	 * @return all the interaction occurrences in the sequence diagram
 	 */
	public IRPCollection getInteractionOccurrences();
	/**
 	 * Returns a collection of all the interaction operators in the sequence diagram.
 	 * @return all the interaction operators in the sequence diagram
 	 */
	public IRPCollection getInteractionOperators();
	/**
 	 * Returns all the message points along the specified instance line.
 	 * @return a collection of IRPMessagePoint objects, representing all the message points along the specified instance line (in the correct order)
 	 */
	public IRPCollection getMessagePoints(IRPClassifierRole classifier);
	/**
 	 * Returns all the message points along the specified instance line.
 	 * @return a collection of IRPMessagePoint objects, representing all the message points along the specified instance line (in the correct order)
 	 */
	public IRPCollection getMessagePoints();
	/**
 	 * Returns a collection of all the messages in the sequence diagram.
 	 * @return all the messages in the sequence diagram
 	 */
	public IRPCollection getMessages();
	/**
	 * get property mode
	 * @throws RhapsodyRuntimeException
	 */
	public String getMode();
	/**
 	 * Returns the message that precedes the specified message.
 	 * @param msg the message whose predecessor has to be found
 	 * @return the message that precedes the specified message
 	 */
	public IRPMessage getPredecessor(IRPMessage msg);
	/**
 	 * Returns the message that follows the specified message.
 	 * @param msg the message whose successor has to be found
 	 * @return the message that follows the specified message
 	 */
	public IRPMessage getSuccessor(IRPMessage msg);


}

