package com.wowsanta.scim.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import com.wowsanta.scim.annotation.AnnotationHandler;
import com.wowsanta.scim.entity.Entity;
import com.wowsanta.scim.type.RestfulServiceType;
import com.wowsanta.util.file.FileFinder;

import lombok.Data;

public class ServiceStructure {
	
	public transient static final String CLASSES 	= "CLASSES";
	public transient static final String DOMAIN  	= "DOMAIN";
	public transient static final String REPOSITORY = "REPOSITORY";
	
	private HashMap<Domain.Key,Entity> entitis;
	private HashMap<Domain.Key,Configuration> repsitories;
	
	private Properties property;
	private transient List<AnnotationHandler> annotationHandlers = new ArrayList<AnnotationHandler>();
	
	private transient static ServiceStructure instance = null;
	
	public static ServiceStructure getInstance() {
		if(instance == null) {
			instance = new ServiceStructure();
		}
		return instance;
	}
	public static class ServiceStructureBuilder{
		private FileFinder classFileFinder = new FileFinder();
		
		public void build() {
			classFileFinder.run();
			
		}
		public ServiceStructureBuilder setProperty(Properties settings) {
			String[] class_path_array = settings.getProperty(CLASSES).split(";");
			for (String class_path : class_path_array) {
				classFileFinder.addDirectory(class_path);	
			}
			
			ServiceStructure.getInstance().property = settings;
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
	
	public Object getProperty(String key, String defulat) {
		if(this.property != null) {
			return this.property.get(key);
		}
		return defulat;
	}
	public void addEntity(Domain.Key key, Entity entity) {
		if(entitis == null) {
			entitis = new HashMap<>();
		}
		entitis.put(key, entity);
	}
	public Set<Entry<Domain.Key, Entity>> getEntitySet(){
		if(entitis == null) {
			entitis = new HashMap<>();
		}
		return this.entitis.entrySet();
	}
	public void addRepository(Domain.Key key, Configuration config) {
		if(this.repsitories == null) {
			this.repsitories = new HashMap<>();
		}
		this.repsitories.put(key, config);
	}

}

