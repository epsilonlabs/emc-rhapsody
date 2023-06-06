
//Licensed Materials - Property of IBM 
//(c) Copyright IBM Corporation 2006, 2018. All Rights Reserved.

package com.telelogic.rhapsody.core;


/**
 * The IRPAssociationRole interface represents the association roles that link objects in communication diagrams.
 */
public interface IRPAssociationRole  extends IRPModelElement {
	/**
 	 * Returns a collection of the classifier roles that are linked by the association role.
 	 * @return the classifier roles that are linked by the association role
 	 */
	public IRPCollection getClassifierRoles();
	/**
 	 * Returns a collection of IRPRelation objects, representing the association ends of the association role.
 	 * @return the association ends of the association role
 	 */
	public IRPCollection getFormalRelations();
	/**
 	 * For internal use only.
 	 */
	public String getRoleType();


}

