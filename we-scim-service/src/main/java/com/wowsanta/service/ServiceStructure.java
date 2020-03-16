package com.wowsanta.service;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import com.wowsanta.repository.RepositoryConfig;
import com.wowsanta.repository.RepositoryManager;
import com.wowsanta.repository.SessionFactory;
import com.wowsanta.scim.ScimException;
import com.wowsanta.scim.annotation.AnnotationHandler;
import com.wowsanta.scim.config.Configuration;
import com.wowsanta.scim.config.ConfigurationBuilder;
import com.wowsanta.scim.config.DomainKey;
import com.wowsanta.scim.entity.EntityInfo;
import com.wowsanta.util.file.FileFinder;

import lombok.Data;
import oracle.net.aso.d;


@Data
public class ServiceStructure {
	
	public transient static final String CLASSES 	= "CLASSES";
	public transient static final String DOMAIN  	= "DOMAIN";
	public transient static final String REPOSITORY = "REPOSITORY";
	
	private Properties property;
	private HashMap<String, EntityInfo> entitis = new HashMap<>();;
	private HashMap<DomainKey,Configuration> repsitories = new HashMap<>();;
	
	
	private transient static ServiceStructure instance = null;
	
	public static ServiceStructure getInstance() {
		if(instance == null) {
			instance = new ServiceStructure();
		}
		return instance;
	}
	
	public Object getProperty(String key, String defulat) {
		if(this.property != null) {
			if(this.property.get(key) != null) {
				return this.property.get(key);
			}
			return defulat;
		}
		return defulat;
	}
	
	public void addEntity(String name, EntityInfo entity) {
		this.entitis.put(name, entity);
	}
	public synchronized EntityInfo getEntity(String name) {
		return entitis.get(name);
	}
	public Set<Entry<String, EntityInfo>> getEntitySet(){
		return this.entitis.entrySet();
	}
	public void addRepository(DomainKey key, Configuration config) {
		this.repsitories.put(key, config);
	}



}

