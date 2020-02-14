package com.wowsanta.repository;

import java.util.HashMap;
import java.util.Map;


public class RepositoryManager {
	private static RepositoryManager instance = null;

	
	private Map<String,SessionFactory> factoryMap = new HashMap<String, SessionFactory>();
	public static RepositoryManager getInstance() {
		if(instance == null) {
			instance = new RepositoryManager();
		}
		return instance;
	}
	
	
	public static RepositoryManager load(String config_file) throws RepositoryException{
		
		
		return instance;
	}
	
	
	public SessionFactory getSessionFactory(String name) {
		SessionFactory factory = this.factoryMap.get(name);
		if(factory == null) {
			
		}
		return factory;
	}
}
