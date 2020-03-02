package com.wowsanta.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import com.wowsanta.server.handler.AnnotationHandler;
import com.wowsanta.util.file.FileFinder;

public class ServiceStructure {
	
	private HashMap<String,Domain> domains;
	private HashMap<String,Entity> entitis;
	private HashMap<String,Reposiory> repsitories;
	
	
	private List<AnnotationHandler> annotationHandlers = new ArrayList<AnnotationHandler>();
	private static ServiceStructure instance = null;
	
	public static ServiceStructure getInstance() {
		if(instance == null) {
			instance = new ServiceStructure();
		}
		return instance;
	}
	public static class ServiceStructureBuilder{
		private FileFinder classFileFinder = new FileFinder();
		private Properties property;
		
		
		
		public void build() {
			classFileFinder.run();
			
			
			
		}
		public ServiceStructureBuilder setProperty(Properties settings) {
			property = settings;
			
			classFileFinder.addDirectory(property.getProperty("classes"));
			
			return this;
		}
		public ServiceStructureBuilder addAnnotationHandler(AnnotationHandler annotationHandler) {
			classFileFinder.addHandler(annotationHandler);
			return this;
		}

		
	}
	public static ServiceStructureBuilder builder() {
		return new ServiceStructureBuilder();
	}
	
}

