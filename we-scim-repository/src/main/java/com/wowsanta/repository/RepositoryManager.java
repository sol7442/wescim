package com.wowsanta.repository;

import java.util.HashMap;
import java.util.Map;

import com.wowsanta.scim.ScimException;
import com.wowsanta.scim.config.DomainKey;


public class RepositoryManager {
	private static RepositoryManager instance = null;
	private String defaultSessionFactory;
	//private transient Map<DomainKey,SessionFactory> sessionFacotries = new HashMap<>();
	private transient Map<String,SessionFactory> sessionFacotries = new HashMap<>();
	
	
	public static RepositoryManager getInstance() {
		if(instance == null) {
			instance = new RepositoryManager();
		}
		return instance;
	}

	public void setDefaultSessionFactory(String name) {
		this.defaultSessionFactory = name;
	}
	public void addRepository(String key, SessionFactory factory) throws ScimException {
		sessionFacotries.put(key, factory);
	}

	public Session openSession() throws ScimException{
		return openSession(defaultSessionFactory);
	}
	public Session openSession(String factory_name) throws ScimException{
		SessionFactory factory = sessionFacotries.get(factory_name);
		if(factory != null) {
			return factory.openSession();
		}else {
			throw new ScimException("SESSION FACTORY NOT FOUND : " + factory_name) ;
		}
	}
}
