//Licensed Materials - Property of IBM 
//© Copyright IBM Corporation 2006, 2008. All Rights Reserved.

package com.telelogic.rhapsody.core;

public abstract class RPExtendedRPClassesFactory {
	
	public static Object factoryMutex = new Object(); 
	
	public abstract Class getExtendedClass(String RPClassName);

}


