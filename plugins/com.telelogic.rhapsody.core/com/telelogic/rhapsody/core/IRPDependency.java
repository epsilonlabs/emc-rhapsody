
//Licensed Materials - Property of IBM 
//(c) Copyright IBM Corporation 2006, 2018. All Rights Reserved.

package com.telelogic.rhapsody.core;


 /**
  * The IRPDependency interface represents dependencies in a Rhapsody model.
  */
public interface IRPDependency  extends IRPModelElement {
	/**
 	 * Returns the source element in the dependency relation, meaning the element that depends on the other element.
 	 * @return the source element in the dependency relation
 	 */
	public IRPModelElement getDependent();
	/**
 	 * Returns the target element in the dependency relation, meaning the element on which the first element depends.
 	 * @return the target element in the dependency relation
 	 */
	public IRPModelElement getDependsOn();
	/**
 	 * Checks whether the dependency represents an OSLC link that has not yet been migrated to Rhapsody Model Manager.
 	 * @return 1 if the dependency represents an OSLC link that has not yet been migrated, 0 otherwise
 	 */
	public int isNeedToMigrate();
	/**
 	 * Sets the source element in the dependency relation, meaning the element that depends on the other element.
 	 * @param dependent the model element that should be used as the source element in the dependency relation 
 	 */
	public void setDependent(IRPModelElement dependent);
	/**
 	 * Sets the target element in the dependency relation, meaning the element on which the first element depends
 	 * @param dependsOn the model element that should be used as the target element in the dependency relation
 	 */
	public void setDependsOn(IRPModelElement dependsOn);
	/**
 	 * For dependencies on remote artifacts, sets the type of the link.
 	 * @param linkType the type of link. The value should be one of the values defined in {@link IRPModelElement.OSLCLink.Types}
 	 * @throws RhapsodyRuntimeException
 	 */
	public void setLinkType(String linkType);
	/**
 	 * Specifies a new owner for the dependency, without changing the dependent model element. Note that if you call the method IRPModelElement.setOwner on a dependency, it will change both the owner and the dependent element.
 	 * @param newOwner the model element that should be the owner of the dependency
 	 * @throws RhapsodyRuntimeException
 	 */
	public void setOwnerWithoutChangingDependent(IRPModelElement newOwner);


}

