package test.wowsanta.server;

import org.junit.Test;

import com.wowsanta.ProviderAgent;
import com.wowsanta.scim.config.Configuration;
import com.wowsanta.scim.config.ConfigurationBuilder;
import com.wowsanta.scim.config.ScimException;
import com.wowsanta.scim.obj.ScimObject;
import com.wowsanta.server.Server;
import com.wowsanta.server.ServerConfig;
import com.wowsanta.server.spark.ServiceBuilder;
import com.wowsanta.server.spark.SparkServer;
import com.wowsanta.server.spark.SparkService;

import lombok.Builder;

public class ServerConfigTest {

	private String provider_config_file = "../config/dev.provider.json"; 
	private String server_config_5000_file   = "../config/dev.server.5000.json";
	private String service_config_common_file = "../config/service.common.json"; 
			

	@Test
	public void create_provider_test() {
		try {
			ProviderAgent provider = new ProviderAgent();
			System.out.println("-ProviderAgent-create----------------------------------");
			provider.setServer(ConfigurationBuilder.load(SparkServer.class, server_config_5000_file));
			provider.addService(ServiceBuilder.load(service_config_common_file));
			
			
			provider.initialize();
			
			System.out.println(ConfigurationBuilder.toJson(provider));
			ConfigurationBuilder.save(provider, provider_config_file);

			//provider = ConfigurationBuilder.load(ProviderAgent.class, provider_config_file);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void create_server_test() {
		
		try {
			SparkServer server = SparkServer.builder().build();
			server.setPort(5000);
			server.setMaxThread(10);
			server.setMinThread(2);
			server.setIdleTime(3000);

//			server.addService("common", "../config/service/common.json");
//			server.addService("admin", "../config/service/admin.json");
//			server.addService("scim", "../config/service/scim.json");
			
			System.out.println("-SparkServer-create----------------------------------");
			ConfigurationBuilder.save(server, server_config_5000_file);
			ConfigurationBuilder.load(SparkServer.class, server_config_5000_file);
			
			//System.out.println(ConfigurationBuilder.toJson(server));
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void create_service_test() {
		try {
			ServiceBuilder builder = new ServiceBuilder();
			builder.setName("common");
			builder.setFileName(service_config_common_file);
			
			builder.addService(
					SparkService.builder()
					.className("sampe.class")
					.method("method2")
					.build());
			
			builder.addService(
					SparkService.builder()
					.className("sampe2.class")
					.method("method")
					.build());
			
			builder.addService(
					SparkService.builder()
					.className("sampe3.class")
					.method("method1")
					.build());
			builder.save();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
