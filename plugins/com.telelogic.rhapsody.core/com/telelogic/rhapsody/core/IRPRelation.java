
//Licensed Materials - Property of IBM 
//(c) Copyright IBM Corporation 2006, 2018. All Rights Reserved.

package com.telelogic.rhapsody.core;


 /**
  * Represents a relationship between two classes.
  */
public interface IRPRelation  extends IRPUnit {
	/**
	 * method addQualifier
	 * @throws RhapsodyRuntimeException
	 */
	public void addQualifier(IRPModelElement pVal);
	/**
	 * method getAssociationClass
	 * @throws RhapsodyRuntimeException
	 */
	public IRPAssociationClass getAssociationClass();
	/**
	 * get property inverse
	 * @throws RhapsodyRuntimeException
	 */
	public IRPRelation getInverse();
	/**
	 * get property isNavigable
	 * @throws RhapsodyRuntimeException
	 */
	public int getIsNavigable();
	/**
	 * get property isSymmetric
	 * @throws RhapsodyRuntimeException
	 */
	public int getIsSymmetric();
	/**
	 * get property multiplicity
	 * @throws RhapsodyRuntimeException
	 */
	public String getMultiplicity();
	/**
	 * get property ObjectAsObjectType
	 * @throws RhapsodyRuntimeException
	 */
	public IRPClass getObjectAsObjectType();
	/**
	 * get property ofClass
	 * @throws RhapsodyRuntimeException
	 */
	public IRPClassifier getOfClass();
	/**
 	 * Gets the class that this class is related to via this relation.
 	 * @return the class that this class is related to via this relation
 	 */
	public IRPClassifier getOtherClass();
	/**
	 * get property qualifier
	 * @throws RhapsodyRuntimeException
	 */
	public String getQualifier();
	/**
 	 * For associations that use qualifiers, returns the type of the qualifier.
 	 * @return the type of the qualifier that is used for the association
 	 */
	public IRPClassifier getQualifierType();
	/**
	 * method getQualifiers
	 * @throws RhapsodyRuntimeException
	 */
	public IRPCollection getQualifiers();
	/**
	 * get property relationLabel
	 * @throws RhapsodyRuntimeException
	 */
	public String getRelationLabel();
	/**
	 * get property relationLinkName
	 * @throws RhapsodyRuntimeException
	 */
	public String getRelationLinkName();
	/**
	 * get property relationRoleName
	 * @throws RhapsodyRuntimeException
	 */
	public String getRelationRoleName();
	/**
	 * get property relationType
	 * @throws RhapsodyRuntimeException
	 */
	public String getRelationType();
	/**
	 * get property visibility
	 * @throws RhapsodyRuntimeException
	 */
	public String getVisibility();
	/**
	 * method isTypelessObject
	 * @throws RhapsodyRuntimeException
	 */
	public int isTypelessObject();
	/**
	 * method makeUnidirect
	 * @throws RhapsodyRuntimeException
	 */
	public void makeUnidirect();
	/**
	 * method removeQualifier
	 * @throws RhapsodyRuntimeException
	 */
	public void removeQualifier(IRPModelElement pVal);
	/**
	 * property setInverse
	 * @throws RhapsodyRuntimeException
	 */
	public void setInverse(String roleName, String linkType);
	/**
	 * set property isNavigable
	 * @throws RhapsodyRuntimeException
	 */
	public void setIsNavigable(int isNavigable);
	/**
	 * set property multiplicity
	 * @throws RhapsodyRuntimeException
	 */
	public void setMultiplicity(String multiplicity);
	/**
	 * set property ofClass
	 * @throws RhapsodyRuntimeException
	 */
	public void setOfClass(IRPClassifier ofClass);
	/**
	 * set property otherClass
	 * @throws RhapsodyRuntimeException
	 */
	public void setOtherClass(IRPClassifier otherClass);
	/**
	 * set property qualifier
	 * @throws RhapsodyRuntimeException
	 */
	public void setQualifier(String qualifier);
	/**
 	 * Sets the type to use for the qualifier for the association.
 	 * @param pVal the type to use for the qualifier for the association
 	 */
	public void setQualifierType(IRPClassifier pVal);
	/**
	 * set property relationLabel
	 * @throws RhapsodyRuntimeException
	 */
	public void setRelationLabel(String relationLabel);
	/**
	 * set property relationLinkName
	 * @throws RhapsodyRuntimeException
	 */
	public void setRelationLinkName(String relationLinkName);
	/**
	 * set property relationRoleName
	 * @throws RhapsodyRuntimeException
	 */
	public void setRelationRoleName(String relationRoleName);
	/**
	 * set property relationType
	 * @throws RhapsodyRuntimeException
	 */
	public void setRelationType(String relationType);


}

