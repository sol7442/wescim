package com.wowsanta;

import java.util.Map.Entry;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import com.google.gson.annotations.SerializedName;
import com.wowsanta.repository.RepositoryConfig;
import com.wowsanta.repository.RepositoryManager;
import com.wowsanta.scim.ScimException;
import com.wowsanta.scim.annotation.AnnotationHandler;
import com.wowsanta.scim.annotation.ENTITY;
import com.wowsanta.scim.config.Configuration;
import com.wowsanta.scim.config.ConfigurationBuilder;
import com.wowsanta.scim.config.Domain;
import com.wowsanta.scim.config.ServiceStructure;
import com.wowsanta.scim.config.ServiceStructure.ServiceStructureBuilder;
import com.wowsanta.scim.type.SCIM_ENTITY;
import com.wowsanta.server.handler.impl.EntityHandler;
import com.wowsanta.util.log.LOGGER;

import lombok.Data;

@Data
public class ProviderAgent {

	@SerializedName(value = "server")
	private Configuration serverConfig;
	
	
	private Map<Domain.Key,Configuration> services;
	private Map<Domain.Key,Configuration> repositoris;

	
	@SerializedName(value = "fiter")
	private Configuration filterConfig;
	
	
	private Properties settings;
	
	private String classDirectory;
	private List<String> distLibFiles;
	
	public Configuration save(String file_name) throws ScimException {
		return ConfigurationBuilder.save(this, file_name);
	}
	
	public static void main(String[] args) {
		try {
			String config_file 	= System.getProperty("server.config");
		} catch (Exception e) {
			LOGGER.error("{} : ",e.getMessage(),e);
		}
	}

	public void addRepository(Domain.Key key, Configuration config) {
		if(repositoris == null) {
			repositoris = new HashMap<Domain.Key, Configuration>();
		}
		repositoris.put(key, config);
	}
	
	
	public void build() throws ScimException {
		ServiceStructureBuilder builder = ServiceStructure.builder()
				.setProperty(settings)
				.addAnnotationHandler(new EntityHandler());
		builder.build();
		
		RepositoryManager repositoryManager = RepositoryManager.getInstance();
		Set<Entry<Domain.Key, Configuration>> repository_set = repositoris.entrySet();
		for (Entry<Domain.Key, Configuration> entry : repository_set) {
			RepositoryConfig configuration = (RepositoryConfig) ConfigurationBuilder.load(entry.getValue());
			
			repositoryManager.addRepository(entry.getKey(),configuration);
		}
		
		
		
		
		LOGGER.system.info("SYSTEM BUILD ---- : " + ServiceStructure.getInstance());
	}

	public void initialize() throws ScimException {
		
		
		
		
		
//		try {
//			
//			
//			
//			
//			
////			if(services == null || repositoris == null || filters == null) {
////				throw new ScimException("CONFIGURATION NOT FOUND :" + toString() );
////			}
////			
////			Set<Entry<String,String>> service_entris  = services.entrySet();
////			for (Entry<String, String> entry : service_entris) {
////				
////				ServiceBuilder builder = ServiceBuilder.load(entry.getValue());
////				System.out.println(builder.getServices());
////				
////				//server.registerService(entry.getKey(),entry.getValue());
////			}
//			
////			Set<Entry<String,String>> repository_entris  = repositoris.entrySet();
////			for (Entry<String, String> entry : repository_entris) {
////				System.out.println("repository_entris ---");
////			}
////			
////			
////			Set<Entry<String,String>> filter_entris  = filters.entrySet();
////			for (Entry<String, String> entry : filter_entris) {
////				System.out.println("filter_entrisy ---");
////			}
//			
//		} catch (ScimException e) {
//			e.printStackTrace();
//		}
	}
//	
//	public void addService(ServiceBuilder builder) {
//		if(services == null) {
//			services = new HashMap<String, String>();
//		}
//		
//		services.put(builder.getName(),builder.getFileName());
//	}
}
