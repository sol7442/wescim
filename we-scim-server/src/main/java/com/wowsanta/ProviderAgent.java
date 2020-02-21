package com.wowsanta;

import com.google.gson.JsonObject;
import com.wowsanta.repository.RepositoryManager;
import com.wowsanta.scim.LOGGER;
import com.wowsanta.scim.config.Configuration;
import com.wowsanta.scim.config.ConfigurationFactory;
import com.wowsanta.scim.config.ScimException;
import com.wowsanta.scim.obj.ScimObject;
import com.wowsanta.server.Server;
import com.wowsanta.server.Service;
import com.wowsanta.server.spark.SparkServer;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProviderAgent extends ScimObject{

	//private Server server;
	private ScimObject server;
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

	@Override
	public ProviderAgent build() {
		// TODO Auto-generated method stub
		return this;
	}

	public void initialize() throws ScimException {
		Server server_inst = (Server) server;//.build();
		server_inst.initialize();
	}
	
}
