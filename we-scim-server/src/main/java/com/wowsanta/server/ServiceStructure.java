package com.wowsanta.server;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import com.wowsanta.scim.annotation.AnnotationHandler;
import com.wowsanta.scim.config.Configuration;
import com.wowsanta.scim.config.DomainKey;
import com.wowsanta.scim.entity.EntityInfo;
import com.wowsanta.util.file.FileFinder;


public class ServiceStructure {
	
	public transient static final String CLASSES 	= "CLASSES";
	public transient static final String DOMAIN  	= "DOMAIN";
	public transient static final String REPOSITORY = "REPOSITORY";
	
	private HashMap<String, EntityInfo> entitis = new HashMap<>();;
	private HashMap<DomainKey,Configuration> repsitories = new HashMap<>();;
	
	private Properties property;
	
	//private transient List<AnnotationHandler> annotationHandlers = new ArrayList<AnnotationHandler>();
	
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
	public EntityInfo getEntity(String key) {
		return entitis.get(key);
	}
	public void addEntity(EntityInfo entity) {
		entitis.put(entity.getName(), entity);
	}
	public Set<Entry<String, EntityInfo>> getEntitySet(){
		return this.entitis.entrySet();
	}
	public void addRepository(DomainKey key, Configuration config) {
		this.repsitories.put(key, config);
	}

}

