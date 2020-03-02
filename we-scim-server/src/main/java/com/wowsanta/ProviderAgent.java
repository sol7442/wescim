package com.wowsanta;

import java.util.Map.Entry;
import java.io.File;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import com.google.gson.annotations.SerializedName;
import com.wowsanta.scim.LOGGER;
import com.wowsanta.scim.ScimException;
import com.wowsanta.scim.annotation.ENTITY;
import com.wowsanta.scim.config.Configuration;
import com.wowsanta.scim.config.ConfigurationBuilder;
import com.wowsanta.scim.type.SCIM_ENTITY;
import com.wowsanta.server.handler.AnnotationHandler;
import com.wowsanta.server.handler.impl.EntityHandler;
import com.wowsanta.service.ServiceStructure;
import com.wowsanta.service.ServiceStructure.ServiceStructureBuilder;

import lombok.Data;

@Data
public class ProviderAgent {

	@SerializedName(value = "server")
	private Configuration serverConfig;
	
	@SerializedName(value = "service")
	private Configuration serviceConfig;
	
	@SerializedName(value = "repository")
	private Configuration repositoryConfig;
	
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
//			ProviderAgent provider = ConfigurationFactory.load(ProviderAgent.class, config_file);
//			provider.initialize();
//			provider.start();
			
		} catch (Exception e) {
			LOGGER.error("{} : ",e.getMessage(),e);
		}
	}

	public void build() throws ScimException {
		ServiceStructure.builder()
				.setProperty(settings)
				.addAnnotationHandler(new EntityHandler())
				.build();
		
		
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
