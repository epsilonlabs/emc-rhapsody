
//Licensed Materials - Property of IBM 
//(c) Copyright IBM Corporation 2006, 2018. All Rights Reserved.

package com.telelogic.rhapsody.core;


 /**
  * Represents the features shared by elements such as classes, actors, use cases, and types.
  */
public interface IRPClassifier  extends IRPUnit {
	/**
 	 * Creates a new activity diagram.
 	 * @return the activity diagram that was created
 	 */
	public IRPFlowchart addActivityDiagram();
	/**
 	 * Adds a new attribute to the classifier.
 	 * @param name the name to use for the new attribute
 	 * @return the attribute that was created
 	 */
	public IRPAttribute addAttribute(String name);
	/**
 	 * Adds a new item flow to the classifier.
 	 * @param name the name to use for the new item flow
 	 * @return the item flow that was created
 	 */
	public IRPFlowItem addFlowItems(String name);
	/**
 	 * Adds a new flow to the classifier.
 	 * @param name the name to use for the new flow
 	 * @return the flow that was created
 	 */
	public IRPFlow addFlows(String name);
	/**
 	 * Adds a generalization relationship between the classifier and the classifier specified as a parameter. For example:
 	 * <pre> 
 	 *	convertibleClass.addGeneralization(carClass);
 	 * </pre>
 	 * @param pVal the classifier that should serve as the base classifier for this classifier
 	 */
	public void addGeneralization(IRPClassifier pVal);
	/**
 	 * Adds a new operation.
 	 * @param name the name to use for the new operation
 	 * @return the operation that was created
 	 */
	public IRPOperation addOperation(String name);
	/**
 	 * Adds a new association to the classifier.
 	 * @param otherClassName the name of the classifier that the current classifier should be associated with
 	 * @param otherClassPackageName that name of the package that contains the classifier that the current classifier should be associated with
 	 * @param roleName1 the role name to use for the association end near the other classifier
 	 * @param linkType1 used in conjunction with the parameter linkType2 to determine the type of association to create. The strings that can be used for this parameter are Association, Aggregation and Composition (parameter is case-sensitive). To create a simple association, use Association for each of the linkType parameters. To create an aggregation relationship, use Association for one of the linkType parameters and Aggregation for the other parameter. To create a composition relationship, use Association for one of the linkType parameters and use Composition for the other parameter.
 	 * @param multiplicity1 the multiplicity to use for the association end near the other classifier. You can use strings such as "1" or "14" to specify a specific number, or you can use one of the values listed in the Features dialog for attributes: "0,1", "*", or "1..*".
 	 * @param roleName2 the role name to use for the association end near the current classifier
 	 * @param linkType2 used in conjunction with the parameter linkType1 to determine the type of association to create. The strings that can be used for this parameter are Association, Aggregation and Composition (parameter is case-sensitive). To create a simple association, use Association for each of the linkType parameters. To create an aggregation relationship, use Association for one of the linkType parameters and Aggregation for the other parameter. To create a composition relationship, use Association for one of the linkType parameters and use Composition for the other parameter.
 	 * @param multiplicity2 the multiplicity to use for the association end near the current classifier. You can use strings such as "1" or "14" to specify a specific number, or you can use one of the values listed in the Features dialog for attributes: "0,1", "*", or "1..*".
 	 * @param linkName if you want to create an association class, use this parameter to specify the name of the class. If you do not want to create an association class, use an empty string as the value of this parameter.
 	 * @return the association that was created
 	 */
	public IRPRelation addRelation(String otherClassName, String otherClassPackageName, String roleName1, String linkType1, String multiplicity1, String roleName2, String linkType2, String multiplicity2, String linkName);
	/**
 	 * Adds a new association to the classifier.
 	 * @param otherClassifier the classifier that the current classifier should be associated with 
 	 * @param roleName1 the role name to use for the association end near the other classifier
 	 * @param linkType1 used in conjunction with the parameter linkType2 to determine the type of association to create. The strings that can be used for this parameter are Association, Aggregation and Composition (parameter is case-sensitive). To create a simple association, use Association for each of the linkType parameters. To create an aggregation relationship, use Association for one of the linkType parameters and Aggregation for the other parameter. To create a composition relationship, use Association for one of the linkType parameters and use Composition for the other parameter. 
 	 * @param multiplicity1 the multiplicity to use for the association end near the other classifier. You can use strings such as "1" or "14" to specify a specific number, or you can use one of the values listed in the Features dialog for attributes: "0,1", "*", or "1..*".
 	 * @param roleName2 the role name to use for the association end near the current classifier
 	 * @param linkType2 used in conjunction with the parameter linkType1 to determine the type of association to create. The strings that can be used for this parameter are Association, Aggregation and Composition (parameter is case-sensitive). To create a simple association, use Association for each of the linkType parameters. To create an aggregation relationship, use Association for one of the linkType parameters and Aggregation for the other parameter. To create a composition relationship, use Association for one of the linkType parameters and use Composition for the other parameter.
 	 * @param multiplicity2 the multiplicity to use for the association end near the current classifier. You can use strings such as "1" or "14" to specify a specific number, or you can use one of the values listed in the Features dialog for attributes: "0,1", "*", or "1..*".
 	 * @param linkName if you want to create an association class, use this parameter to specify the name of the class. If you do not want to create an association class, use an empty string as the value of this parameter.
 	 * @return the association that was created
 	 */
	public IRPRelation addRelationTo(IRPClassifier otherClassifier, String roleName1, String linkType1, String multiplicity1, String roleName2, String linkType2, String multiplicity2, String linkName);
	/**
 	 * Creates a new statechart.
 	 * @return the statechart that was created
 	 */
	public IRPStatechart addStatechart();
	/**
 	 * Adds a new directed association to the classifier.
 	 * @param otherClassName the name of the classifier that the current classifier should be associated with
 	 * @param otherClassPackageName that name of the package that contains the classifier that the current classifier should be associated with
 	 * @param roleName the role name to use for the association end
 	 * @param linkType used to determine the type of association to create. The strings that can be used for this parameter are Association, Aggregation and Composition (parameter is case-sensitive).
 	 * @param multiplicity the multiplicity to use for the association end. You can use strings such as "1" or "14" to specify a specific number, or you can use one of the values listed in the Features dialog for attributes: "0,1", "*", or "1..*".
 	 * @param linkName if you want to create an association class, use this parameter to specify the name of the class. If you do not want to create an association class, use an empty string as the value of this parameter.
 	 * @return the association that was created
 	 */
	public IRPRelation addUnidirectionalRelation(String otherClassName, String otherClassPackageName, String roleName, String linkType, String multiplicity, String linkName);
	/**
 	 * Adds a new directed association to the classifier.
 	 * @param otherClassifier the classifier that the current classifier should be associated with
 	 * @param roleName the role name to use for the association end
 	 * @param linkType used to determine the type of association to create. The strings that can be used for this parameter are Association, Aggregation and Composition (parameter is case-sensitive).
 	 * @param multiplicity the multiplicity to use for the association end. You can use strings such as "1" or "14" to specify a specific number, or you can use one of the values listed in the Features dialog for attributes: "0,1", "*", or "1..*".
 	 * @param linkName if you want to create an association class, use this parameter to specify the name of the class. If you do not want to create an association class, use an empty string as the value of this parameter.
 	 * @return the association that was created
 	 */
	public IRPRelation addUnidirectionalRelationTo(IRPClassifier otherClassifier, String roleName, String linkType, String multiplicity, String linkName);
	/**
 	 * Deletes the specified attribute.
 	 * @param attribute the attribute that should be deleted
 	 */
	public void deleteAttribute(IRPAttribute attribute);
	/**
 	 * Deletes the specified item flow.
 	 * @param pItem the item flow that should be deleted
 	 */
	public void deleteFlowItems(IRPFlowItem pItem);
	/**
 	 * Deletes the specified flow.
 	 * @param pFlow the flow that should be deleted
 	 */
	public void deleteFlows(IRPFlow pFlow);
	/**
 	 * Deletes the generalization relationship between the classifier and the classifier specified as a parameter.
 	 * @param superClass the classifier whose generalization relationship with this classifier should be deleted
 	 */
	public void deleteGeneralization(IRPClassifier superClass);
	/**
 	 * Deletes the specified operation.
 	 * @param operation the operation that should be deleted
 	 */
	public void deleteOperation(IRPOperation operation);
	/**
 	 * Deletes the specified relation.
 	 * @param relation the relation that should be deleted
 	 */
	public void deleteRelation(IRPRelation relation);
	/**
 	 * Returns the attribute with the name specified.
 	 * @param newVal the name of the attribute that should be returned
 	 * @return the attribute with the name specified
 	 */
	public IRPAttribute findAttribute(String newVal);
	/**
 	 * Returns the base classifier with the specified name.
 	 * @param newVal the name of the base classifier that should be returned
 	 * @return the base classifier with the specified name
 	 */
	public IRPClassifier findBaseClassifier(String newVal);
	/**
 	 * Returns the derived classifier with the specified name.
 	 * @param newVal the name of the derived classifier that should be returned
 	 * @return the derived classifier with the specified name
 	 */
	public IRPClassifier findDerivedClassifier(String newVal);
	/**
 	 * Returns the element representing the generalization relationship between this classifier and the classifier whose name was specified as a parameter.
 	 * @param newVal the name of the classifier whose generalization relationship should be returned
 	 * @return the element representing the generalization relationship between this classifier and the classifier whose name was specified as a parameter
 	 */
	public IRPGeneralization findGeneralization(String newVal);
	/**
 	 * Gets the operation or event reception that matches the signature provided.
 	 * @param signature the signature of the operation or event reception. The string you provide should consist of the operation name followed by parentheses containing a comma-delimited list of the types of the parameters, for example, "runEngine(int,int)".
 	 * @return the operation or event reception
 	 */
	public IRPInterfaceItem findInterfaceItem(String signature);
	/**
 	 * Searches for the nested classifier with the name specified. This method only searches the first level of elements below the current classifier. To search all of the levels below the current classifier, use the method findNestedClassifierRecursive.
 	 * @param newVal the name of the classifier to search for
 	 * @return the classifier with the name that was specified
 	 */
	public IRPClassifier findNestedClassifier(String newVal);
	/**
 	 * Searches recursively for the classifier with the name specified. This method searches all of the levels below the current classifier. To search only the first level of elements below the current classifier, use the method findNestedClassifier.
 	 * @param newVal the name of the classifier to search for
 	 * @return the classifier that was specified. Note that the classifier is returned as an object of type IRPModelElement. So you will usually want to use casting, for example: IRPClassifier classifierToSearchFor = (IRPClassifier)stillsCamera.findNestedClassifierRecursive("nested_1_next_level");
 	 */
	public IRPModelElement findNestedClassifierRecursive(String newVal);
	/**
 	 * Returns the association whose name was specified as a parameter.
 	 * @param newVal the name of the association that should be returned
 	 * @return the association whose name was specified as a parameter
 	 */
	public IRPRelation findRelation(String newVal);
	/**
 	 * Returns the trigger with the specified name in the classifier's statechart.
 	 * @param name the name of the trigger to find
 	 * @return the trigger with the specified name in the classifier's statechart
 	 */
	public IRPInterfaceItem findTrigger(String name);
	 /**
 	  * This method should no longer be used because Rhapsody now allows you to define more than one statechart and activity diagram for a class. Use the method getBehavioralDiagrams instead.
 	  */
	public IRPFlowchart getActivityDiagram();
	/**
 	 * Returns a collection of all the classifier's attributes.
 	 * @return all the classifier's attributes
 	 */
	public IRPCollection getAttributes();
	/**
 	 * Returns a collection of all the classifier's attributes, including those it inherits from its base classifiers.
 	 * @return all of the classifier's attributes, including those it inherits from its base classifiers
 	 */
	public IRPCollection getAttributesIncludingBases();
	/**
 	 * Returns a collection of the classifiers that server as base classifiers for this classifier.
 	 * @return all the classifiers that serve as base classifiers for this classifier
 	 */
	public IRPCollection getBaseClassifiers();
	/**
 	 * Returns a collection of all the statecharts and activities defined for the classifier. The collection that is returned consists of elements of type IRPStatechart.
 	 * @return all of the statecharts and activities defined for the classifier
 	 */
	public IRPCollection getBehavioralDiagrams();
	/**
 	 * Returns a collection of all the classifiers derived from this classifier.
 	 * @return all the classifiers derived from this classifier
 	 */
	public IRPCollection getDerivedClassifiers();
	/**
 	 * Returns a collection of all the classifier's item flows.
 	 * @return all of the classifier's item flows
 	 */
	public IRPCollection getFlowItems();
	/**
 	 * Returns a collection of the classifier's flows.
 	 * @return all of the classifier's flows
 	 */
	public IRPCollection getFlows();
	/**
 	 * Returns a collection of all the classifier's generalization relationships.
 	 * @return all of the classifier's generalization relationships
 	 */
	public IRPCollection getGeneralizations();
	/**
 	 * Returns a collection of the classifier's elements of type IRPInterfaceItem (such as operations, triggered operations, and event receptions).
 	 * @return all of the classifier's elements of type IRPInterfaceItem
 	 */
	public IRPCollection getInterfaceItems();
	/**
 	 * Returns a collection of the classifier's elements of type IRPInterfaceItem (such as operations, triggered operations, and event receptions), including those it inherits from its base classifier.
 	 * @return all of the classifier's elements of type IRPInterfaceItem, including those it inherits from its base classifier
 	 */
	public IRPCollection getInterfaceItemsIncludingBases();
	/**
 	 * Returns a collection of all the classifier's link relationships.
 	 * @return all of the classifier's link relationships
 	 */
	public IRPCollection getLinks();
	/**
 	 * Returns a collection of all the classifiers nested below the current classifier. Note that this method is not recursive - it only returns the classifiers at the level directly below the current classifier.
 	 * @return all of the classifiers nested below the current classifier
 	 */
	public IRPCollection getNestedClassifiers();
	/**
 	 * Returns a collection of all the classifier's operations.
 	 * @return all the classifier's operations
 	 */
	public IRPCollection getOperations();
	/**
 	 * Returns a collection of all the classifier's ports.
 	 * @return all of the classifier's ports
 	 */
	public IRPCollection getPorts();
	/**
 	 * Returns a collection of all the classifier's associations.
 	 * @return all of the classifier's associations
 	 */
	public IRPCollection getRelations();
	/**
 	 * Returns a collection of all the classifier's associations, including those it inherits from its base classifier.
 	 * @return all of the classifier's associations, including those it inherits from its base classifier
 	 */
	public IRPCollection getRelationsIncludingBases();
	/**
 	 * Returns a collection of the classifier's sequence diagrams.
 	 * @return all of the classifier's sequence diagrams
 	 */
	public IRPCollection getSequenceDiagrams();
	/**
 	 * Gets the source artifacts for the classifier.
 	 * @return the source artifacts for the classifier, as a collection of IRPFile objects
 	 */
	public IRPCollection getSourceArtifacts();
	 /**
 	  * This method should no longer be used because Rhapsody now allows you to define more than one statechart and activity diagram for a class. Use the method getBehavioralDiagrams instead.
 	  */
	public IRPStatechart getStatechart();


}

