package com.wowsanta.repository;

import java.util.Set;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.wowsanta.scim.LOGGER;
import com.wowsanta.scim.config.ConfigurationFactory;
import com.wowsanta.scim.config.ConfigurationFactory;
import com.wowsanta.scim.config.ScimException;




public class RepositoryManager extends ConfigurationFactory{
	private String defaultName = "default";
	private Map<String,RepositoryConfig> repositoris = new HashMap<String, RepositoryConfig>();
	private transient Map<String,SessionFactory> factoryMap = new HashMap<String, SessionFactory>();
	
	
	private transient static RepositoryManager instance = null;
	
	public void addRepositoryConfig(RepositoryConfig config ) {
		repositoris.put(config.getName(), config);
	}
	public static RepositoryManager getInstance() {
		if(instance == null) {
			instance = new RepositoryManager();
		}
		return instance;
	}
	
	public void build() {
		Set<Entry<String,RepositoryConfig>> entry_set = repositoris.entrySet();
		for (Entry<String, RepositoryConfig> entry : entry_set) {
			String repository_name = entry.getKey();
			ConfigurationFactory repository_config = (ConfigurationFactory) entry.getValue();
			
			try {
				
				RepositoryConfig repository_factoyr_config = (RepositoryConfig) repository_config.load();
				System.out.println(repository_name + " : " + repository_factoyr_config.getName());
				if(repository_name.equals(repository_factoyr_config.getName())) {
					factoryMap.put(repository_name,repository_factoyr_config.build());
				}
			} catch (ScimException e) {
				LOGGER.error("{}", e.getMessage(), e);
			}
		}
	}
	
	public SessionFactory getSessionFactory(String name) {
		SessionFactory factory = this.factoryMap.get(name);
		if(factory == null) {
			factory = this.factoryMap.get(defaultName);
			if(factory == null) {
				LOGGER.error("SESSION FACOTRY NOT FOUND : {} ", name);
			}
		}
		return factory;
	}
	public static RepositoryManager load(String file_name) throws ScimException {
		instance = load(RepositoryManager.class, file_name);
		return instance;
	}
	
	
}
