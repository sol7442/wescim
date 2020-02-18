package com.wowsanta;

import com.wowsanta.scim.LOGGER;
import com.wowsanta.scim.config.ConfigurationFactory;
import com.wowsanta.scim.config.ScimException;
import com.wowsanta.server.Server;
import com.wowsanta.server.Service;
import com.wowsanta.server.spark.SparkServer;
import com.wowsanta.server.spark.SparkServiceConfig;

import lombok.Data;

@Data
public class ProviderServer  implements Server{

	private transient Server sparkServer;
	private String serverConfig;
	private String adminConfig;
	private String providerConfig;
	private String repositoryConfig;
	
	public static void main(String[] args) {
		try {
			
			String config_file 	= System.getProperty("server.config");
			ProviderServer provider = ConfigurationFactory.load(ProviderServer.class, config_file);
			provider.initialize();
			provider.start();
			
		} catch (ScimException e) {
			e.printStackTrace();
		}
	}


	@Override
	public void initialize() {
		try {
			sparkServer = ConfigurationFactory.load(SparkServer.class, serverConfig);
			SparkServiceConfig admin_config 	= ConfigurationFactory.load(SparkServiceConfig.class, adminConfig);
			SparkServiceConfig provider_config 	= ConfigurationFactory.load(SparkServiceConfig.class, providerConfig);
			
			LOGGER.system.info("REGIST SERVICE : {}",admin_config.getName());
			for (Service service : admin_config.getServiceList()) {
				service.regist();
			}
			LOGGER.system.info("REGIST SERVICE : {}",admin_config.getName());
			for (Service service : provider_config.getServiceList()) {
				service.regist();
			}
			
			// load repository //
			
			// load scheduler  //
			
			sparkServer.initialize();
		} catch (ScimException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void start() {
		sparkServer.start();
	}

	@Override
	public void stop() {
		
	}

}
