package test.wowsanta.server;

import org.junit.Test;

import com.wowsanta.ProviderAgent;
import com.wowsanta.scim.ScimException;
import com.wowsanta.scim.config.Configuration;
import com.wowsanta.scim.config.ConfigurationBuilder;
import com.wowsanta.scim.obj.ScimObject;
import com.wowsanta.server.Server;
import com.wowsanta.server.ServerConfig;
import com.wowsanta.server.spark.SparkServer;

import lombok.Builder;

public class ServerTest {

	private String provider_config_file = "../config/dev.provider.json"; 
	private String server_config_5000_file   = "../config/dev.server.5000.json";
	private String service_config_common_file = "../config/service.common.json"; 
			

	@Test
	public void create_server_test() {
		
		try {
			
			SparkServer spakr_server = new SparkServer();//SparkServer.builder()
			spakr_server.setPort(5000);
			spakr_server.setMaxThread(10);
			spakr_server.setMinThread(2);
			spakr_server.setIdleTime(2000);
			
			Configuration server_config = spakr_server.save(server_config_5000_file);
			System.out.println(server_config);
			
			
			Server server = (Server) ConfigurationBuilder.load(server_config);
			System.out.println(server_config);
			server.initialize();
			
		} catch (ScimException e) {
			e.printStackTrace();
		}
	}
	
}
