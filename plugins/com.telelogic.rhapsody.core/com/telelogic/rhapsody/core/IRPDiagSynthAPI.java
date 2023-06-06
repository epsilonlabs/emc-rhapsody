
//Licensed Materials - Property of IBM 
//(c) Copyright IBM Corporation 2006, 2018. All Rights Reserved.

package com.telelogic.rhapsody.core;



public interface IRPDiagSynthAPI {
	/**
	 * DiagSynthAPI : add instance to sequence diagram
	 * @throws RhapsodyRuntimeException
	 */
	public long addInstance(long addedToSD, String instanceNavExp);
	/**
	 * DiagSynthAPI : add synth sequence diagarm to model
	 * @throws RhapsodyRuntimeException
	 */
	public int addSynthSDToModel2(IRPSequenceDiagram pMscOrig, long synthSD, int openSD);
	/**
	 * DiagSynthAPI : create sequence diagram
	 * @throws RhapsodyRuntimeException
	 */
	public long createSD2(IRPSequenceDiagram pMscOrig, String testedmscname);
	/**
	 * DiagSynthAPI : recieve sequence diagram message
	 * @throws RhapsodyRuntimeException
	 */
	public void receiveMessage(long pTestedSD, long pEventSent);
	/**
	 * DiagSynthAPI : remove synth sequence diagarm to model
	 * @throws RhapsodyRuntimeException
	 */
	public int removeSynthSDToModel2(IRPSequenceDiagram pMscOrig);
	/**
	 * DiagSynthAPI : send condition mark to instance
	 * @throws RhapsodyRuntimeException
	 */
	public long sDAddConditionMark(long pTestedSD, String instance, String text, String type);
	/**
	 * DiagSynthAPI : send sequence diagram message
	 * @throws RhapsodyRuntimeException
	 */
	public long sendMessage(long pTestedSD, String source, String target, String event, String operation, String type);
	/**
	 * get property interfaceName
	 * @throws RhapsodyRuntimeException
	 */
	public String getInterfaceName();


}

