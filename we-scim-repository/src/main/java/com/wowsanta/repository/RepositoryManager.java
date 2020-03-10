package com.wowsanta.repository;

import java.util.HashMap;
import java.util.Map;

import com.wowsanta.scim.ScimException;
import com.wowsanta.scim.config.DomainKey;


public class RepositoryManager {
	private static RepositoryManager instance = null;
	
	private transient Map<DomainKey,SessionFactory> sessionFacotries = new HashMap<>();
	public static RepositoryManager getInstance() {
		if(instance == null) {
			instance = new RepositoryManager();
		}
		return instance;
	}

	public void addRepository(DomainKey key, SessionFactory factory) throws ScimException {
		sessionFacotries.put(key, factory);
	}
	
}
