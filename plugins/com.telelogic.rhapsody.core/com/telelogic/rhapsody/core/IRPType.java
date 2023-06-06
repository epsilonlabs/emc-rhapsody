
//Licensed Materials - Property of IBM 
//(c) Copyright IBM Corporation 2006, 2018. All Rights Reserved.

package com.telelogic.rhapsody.core;



public interface IRPType  extends IRPClassifier {
	/**
	 * method addEnumerationLiteral
	 * @throws RhapsodyRuntimeException
	 */
	public IRPEnumerationLiteral addEnumerationLiteral(String name);
	/**
	 * method deleteEnumerationLiteral
	 * @throws RhapsodyRuntimeException
	 */
	public void deleteEnumerationLiteral(IRPEnumerationLiteral literal);
	/**
	 * get property declaration
	 * @throws RhapsodyRuntimeException
	 */
	public String getDeclaration();
	/**
	 * get property enumerationLiterals
	 * @throws RhapsodyRuntimeException
	 */
	public IRPCollection getEnumerationLiterals();
	/**
	 * get property isPredefined
	 * @throws RhapsodyRuntimeException
	 */
	public int getIsPredefined();
	/**
	 * get property isTypedef
	 * @throws RhapsodyRuntimeException
	 */
	public int getIsTypedef();
	/**
	 * get property isTypedefConstant
	 * @throws RhapsodyRuntimeException
	 */
	public int getIsTypedefConstant();
	/**
	 * get property isTypedefOrdered
	 * @throws RhapsodyRuntimeException
	 */
	public int getIsTypedefOrdered();
	/**
	 * get property isTypedefReference
	 * @throws RhapsodyRuntimeException
	 */
	public int getIsTypedefReference();
	/**
	 * get property kind
	 * @throws RhapsodyRuntimeException
	 */
	public String getKind();
	/**
	 * get property typedefBaseType
	 * @throws RhapsodyRuntimeException
	 */
	public IRPClassifier getTypedefBaseType();
	/**
	 * get property typedefMultiplicity
	 * @throws RhapsodyRuntimeException
	 */
	public String getTypedefMultiplicity();
	/**
	 * method isArray
	 * @throws RhapsodyRuntimeException
	 */
	public int isArray();
	/**
 	 * For types whose "kind" was set to Language, parses the declaration to see if the type is actually an enum.
 	 * @return 1 if the type is an enum, 0 otherwise
 	 * @throws RhapsodyRuntimeException
 	 */
	public int isEnum();
	/**
	 * method isEqualTo
	 * @throws RhapsodyRuntimeException
	 */
	public int isEqualTo();
	/**
	 * method isImplicit
	 * @throws RhapsodyRuntimeException
	 */
	public int isImplicit();
	/**
 	 * Checks whether the "kind" of the type is Enumeration.
 	 * @return 1 if the "kind" of the type is Enumeration, 0 otherwise
 	 * @throws RhapsodyRuntimeException
 	 */
	public int isKindEnumeration();
	/**
 	 * Checks whether the "kind" of the type was set to Language.
 	 * @return 1 if the "kind" of the type is Language, 0 otherwise
 	 * @throws RhapsodyRuntimeException
 	 */
	public int isKindLanguage();
	/**
 	 * Checks whether the "kind" of the type is Structure.
 	 * @return 1 if the "kind" of the type is Structure, 0 otherwise
 	 * @throws RhapsodyRuntimeException
 	 */
	public int isKindStruct();
	/**
 	 * Checks whether the "kind" of the type is Typedef.
 	 * @return 1 if the "kind" of the type is Typedef, 0 otherwise
 	 * @throws RhapsodyRuntimeException
 	 */
	public int isKindTypedef();
	/**
 	 * Checks whether the "kind" of the type is Union.
 	 * @return 1 if the "kind" of the type is Union, 0 otherwise
 	 * @throws RhapsodyRuntimeException
 	 */
	public int isKindUnion();
	/**
	 * method isPointer
	 * @throws RhapsodyRuntimeException
	 */
	public int isPointer();
	/**
	 * method isPointerToPointer
	 * @throws RhapsodyRuntimeException
	 */
	public int isPointerToPointer();
	/**
	 * method isReference
	 * @throws RhapsodyRuntimeException
	 */
	public int isReference();
	/**
	 * method isReferenceToPointer
	 * @throws RhapsodyRuntimeException
	 */
	public int isReferenceToPointer();
	/**
 	 * For types whose "kind" was set to Language, parses the declaration to see if the type is actually a struct.
 	 * @return 1 if the type is a struct, 0 otherwise
 	 * @throws RhapsodyRuntimeException
 	 */
	public int isStruct();
	/**
	 * method isTemplate
	 * @throws RhapsodyRuntimeException
	 */
	public int isTemplate();
	/**
 	 * For types whose "kind" was set to Language, parses the declaration to see if the type is actually a union.
 	 * @return 1 if the type is a union, 0 otherwise
 	 * @throws RhapsodyRuntimeException
 	 */
	public int isUnion();
	/**
	 * set property declaration
	 * @throws RhapsodyRuntimeException
	 */
	public void setDeclaration(String declaration);
	/**
	 * set property isTypedefConstant
	 * @throws RhapsodyRuntimeException
	 */
	public void setIsTypedefConstant(int isTypedefConstant);
	/**
	 * set property isTypedefOrdered
	 * @throws RhapsodyRuntimeException
	 */
	public void setIsTypedefOrdered(int isTypedefOrdered);
	/**
	 * set property isTypedefReference
	 * @throws RhapsodyRuntimeException
	 */
	public void setIsTypedefReference(int isTypedefReference);
	/**
	 * set property kind
	 * @throws RhapsodyRuntimeException
	 */
	public void setKind(String kind);
	/**
	 * set property typedefBaseType
	 * @throws RhapsodyRuntimeException
	 */
	public void setTypedefBaseType(IRPClassifier typedefBaseType);
	/**
	 * set property typedefMultiplicity
	 * @throws RhapsodyRuntimeException
	 */
	public void setTypedefMultiplicity(String typedefMultiplicity);


}

