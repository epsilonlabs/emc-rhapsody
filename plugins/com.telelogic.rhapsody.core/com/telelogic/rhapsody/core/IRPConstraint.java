
//Licensed Materials - Property of IBM 
//(c) Copyright IBM Corporation 2006, 2018. All Rights Reserved.

package com.telelogic.rhapsody.core;


 /**
  * The IRPConstraint interface represents constraints in a Rhapsody model.
  */
public interface IRPConstraint  extends IRPAnnotation {
	/**
 	 * Returns all of the model elements affected by this constraint.
 	 * @return a collection of all the model elements affected by this constraint.
 	 */
	public IRPCollection getConstraintsByMe();


}

