package com.wowsanta.repository;

import java.util.HashMap;
import java.util.Map;

import com.wowsanta.scim.ScimException;
import com.wowsanta.scim.config.DomainKey;


public class RepositoryManager {
	private static RepositoryManager instance = null;
	private DomainKey default_repository = null;
	private transient Map<DomainKey,SessionFactory> sessionFacotries = new HashMap<>();
	
	
	public static RepositoryManager getInstance() {
		if(instance == null) {
			instance = new RepositoryManager();
		}
		return instance;
	}

	public void setDefault(String domain, String name) {
		this.default_repository = new DomainKey(domain, name);
	}
	public void addRepository(DomainKey key, SessionFactory factory) throws ScimException {
		sessionFacotries.put(key, factory);
	}

	public Session openSession() throws ScimException{
		SessionFactory factory = sessionFacotries.get(default_repository);
		if(factory != null) {
			return factory.openSession();
		}else {
			throw new ScimException("DEFAULT SESSION FACTORY NOT FOUND : " + default_repository) ;
		}
	}
	public Session openSession(DomainKey factory_key) throws ScimException{
		SessionFactory factory = sessionFacotries.get(factory_key);
		if(factory != null) {
			return factory.openSession();
		}else {
			throw new ScimException("SESSION FACTORY NOT FOUND : " + factory_key) ;
		}
	}
}
