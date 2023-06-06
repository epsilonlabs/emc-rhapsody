
//Licensed Materials - Property of IBM 
//(c) Copyright IBM Corporation 2006, 2018. All Rights Reserved.

package com.telelogic.rhapsody.core;


/**
 * The IRPInterfaceItem interface represents the features shared by operations, events, and event receptions in Rhapsody models.
 */
public interface IRPInterfaceItem  extends IRPClassifier {
	/**
 	 * Adds a new argument to the end of the argument list. The method takes only a single argument - the name to use for the argument. The type of the argument is set by default to "int". To change the type of the argument, use the method IRPArgument.setType.
 	 * @param newVal the name to use for the new argument
 	 * @return the argument that was created
 	 */
	public IRPArgument addArgument(String newVal);
	/**
 	 * Adds a new argument at the specified position in the argument list. Like the addArgument method, the type of the argument is set by default to "int". To change the type of the argument, use the method IRPArgument.setType.
 	 * @param newVal the name to use for the new argument
 	 * @param pos the position in the argument list where the new argument should be placed (1 signifies the first argument in the list)
 	 * @return the argument that was created
 	 */
	public IRPArgument addArgumentBeforePosition(String newVal, int pos);
	/**
 	 * Returns a collection of all the arguments for the operation (collection of IRPArgument objects).
 	 * @return all the arguments for the operation
 	 */
	public IRPCollection getArguments();
	/**
 	 * Returns the signature of the operation.
 	 * @return the signature of the operation
 	 */
	public String getSignature();
	/**
 	 * Returns the signature of the operation without the argument names.
 	 * @return the signature of the operation without the argument names
 	 */
	public String getSignatureNoArgNames();
	/**
 	 * Returns the signature of the operation without the argument types.
 	 * @return the signature of the operation without the argument types
 	 */
	public String getSignatureNoArgTypes();
	/**
 	 * Compares the signature of the operation with the signature of the operation that was provided as an argument. This method is useful if you are moving an operation from one class to another because Rhapsody will throw an exception if an operation with the identical signature already exists in the class.
 	 * @param Item the operation whose signature should be compared to the signature of the current operation
 	 * @return 1 if the two signatures are identical, 0 otherwise
 	 */
	public int matchOnSignature(IRPInterfaceItem Item);


}

