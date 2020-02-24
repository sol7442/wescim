package com.wowsanta;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import com.wowsanta.scim.LOGGER;
import com.wowsanta.scim.config.ConfigurationBuilder;
import com.wowsanta.scim.config.ScimException;
import com.wowsanta.server.spark.ServiceBuilder;
import com.wowsanta.server.spark.SparkServer;

import lombok.Data;

@Data
public class ProviderAgent extends ConfigurationBuilder {

	private SparkServer server;
	private Map<String,String> services;
	private Map<String,String> repositoris;
	private Map<String,String> filters;
	
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

	public static ProviderAgent build(String file) throws ScimException{
		return load(ProviderAgent.class,file);
	}
	
	public void initialize() throws ScimException {
		try {
//			if(services == null || repositoris == null || filters == null) {
//				throw new ScimException("CONFIGURATION NOT FOUND :" + toString() );
//			}
			
			Set<Entry<String,String>> service_entris  = services.entrySet();
			for (Entry<String, String> entry : service_entris) {
				
				ServiceBuilder builder = ServiceBuilder.load(entry.getValue());
				System.out.println(builder.getServices());
				
				//server.registerService(entry.getKey(),entry.getValue());
			}
			
//			Set<Entry<String,String>> repository_entris  = repositoris.entrySet();
//			for (Entry<String, String> entry : repository_entris) {
//				System.out.println("repository_entris ---");
//			}
//			
//			
//			Set<Entry<String,String>> filter_entris  = filters.entrySet();
//			for (Entry<String, String> entry : filter_entris) {
//				System.out.println("filter_entrisy ---");
//			}
			
		} catch (ScimException e) {
			e.printStackTrace();
		}
	}
	
	public void addService(ServiceBuilder builder) {
		if(services == null) {
			services = new HashMap<String, String>();
		}
		
		services.put(builder.getName(),builder.getFileName());
	}
}
