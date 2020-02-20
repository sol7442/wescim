package com.wowsanta;

import com.wowsanta.repository.RepositoryManager;
import com.wowsanta.scim.LOGGER;
import com.wowsanta.scim.config.Configuration;
import com.wowsanta.scim.config.ConfigurationFactory;
import com.wowsanta.scim.config.ScimException;
import com.wowsanta.server.Server;
import com.wowsanta.server.Service;
import com.wowsanta.server.spark.SparkServer;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProviderAgent extends Configuration{

	private Configuration server;
	private Configuration service;
	private Configuration repository;
	private Configuration engine;
	
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

	
	
	
}
