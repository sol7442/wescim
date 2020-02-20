package test.wowsanta.server;

import org.junit.Test;

import com.wowsanta.ProviderAgent;
import com.wowsanta.scim.config.Configuration;
import com.wowsanta.scim.config.ScimException;
import com.wowsanta.server.spark.SparkServer;

public class ServerConfigTest {

	private String provider_config_file = "../config/dev.provider.json"; 
	private String server_config_5000_file   = "../config/dev.server.5000.json";
			
	
	@Test
	public void load_provider_test() {
		try {
			ProviderAgent provider = ProviderAgent.load(ProviderAgent.class, provider_config_file);
			System.out.println("-ProviderAgent-load----------------------------------");
			System.out.println(provider.toJson(true));
		
		} catch (ScimException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void create_provider_test() {
		try {
			ProviderAgent provider = ProviderAgent.builder().build();
			
			provider.setServer(load_server(server_config_5000_file));
			
			provider.save(provider_config_file);
			
			System.out.println("-ProviderAgent-create----------------------------------");
			System.out.println(provider.toJson(true));
			
			//server.save("../config/dev.provider.json");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//@Test
	public void create_server_test() {
		
		try {
			SparkServer server = SparkServer.builder().build();
			server.setName("5000");
			server.add(SparkServer.PORT,5000);
			server.add(SparkServer.MAX_THREAD,10);
			server.add(SparkServer.MIN_THREAD,2);
			server.add(SparkServer.IDLE_TIMES,2000);
			
			System.out.println("-SparkServer-create----------------------------------");
			System.out.println(server.toJson(true));
			
			server.save(server_config_5000_file);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}
	private Configuration load_server(String file_name) {
		try {
			return Configuration.load(Configuration.class, file_name);
		} catch (ScimException e) {
			e.printStackTrace();
		}
		return null;
	}
}
