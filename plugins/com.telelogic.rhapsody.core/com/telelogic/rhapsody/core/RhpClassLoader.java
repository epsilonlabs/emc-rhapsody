package com.telelogic.rhapsody.core;

import java.net.URL;
import java.net.URLClassLoader;

public class RhpClassLoader extends URLClassLoader {

	private String localLibPath;
	public RhpClassLoader(URL[] urls) {
		super(urls);
		Thread.currentThread().setContextClassLoader(this);
	}
	@Override
	protected String findLibrary(String libName) {
		if (libName == "rhapsody")
			return localLibPath;
		return super.findLibrary(libName);
	}
	public void setLocalLibPath(String path){
		localLibPath = path;
	}
}
