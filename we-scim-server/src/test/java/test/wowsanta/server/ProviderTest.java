package test.wowsanta.server;

import java.util.Properties;

import org.junit.Test;

import com.wowsanta.ProviderAgent;
import com.wowsanta.scim.ScimException;
import com.wowsanta.scim.config.Configuration;
import com.wowsanta.scim.config.ConfigurationBuilder;
import com.wowsanta.scim.obj.ScimObject;
import com.wowsanta.server.Server;
import com.wowsanta.server.ServerConfig;
import com.wowsanta.server.spark.ServiceBuilder;
import com.wowsanta.server.spark.SparkServer;
import com.wowsanta.server.spark.SparkService;

import lombok.Builder;

public class ProviderTest {

	private String provider_config_file = "../config/dev.provider.json"; 
	private String server_config_5000_file   = "../config/dev.server.5000.json";
	private String service_config_common_file = "../config/service.common.json"; 
			

	@Test
	public void create_provider_test() {
		try {
			ProviderAgent provider = new ProviderAgent();
			SparkServer server =  ConfigurationBuilder.load(SparkServer.class,server_config_5000_file);
			provider.setServerConfig(server.save(server_config_5000_file));
			provider.setClassDirectory("");
			
			
			Properties prop = new Properties();
			prop.put("classes","C:\\Work\\wow-git\\wescim\\we-scim-service\\bin\\main");
			provider.setSettings(prop);
			
			provider.save(provider_config_file)	;
			
			
			provider.build();
			provider.initialize();
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
