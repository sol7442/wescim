package test.wowsanta.server;

import java.lang.reflect.Type;
import java.util.Map;
import java.util.Properties;

import org.junit.Test;

import com.google.gson.reflect.TypeToken;
import com.wowsanta.ProviderAgent;
import com.wowsanta.repository.hibernate.HibernateConfiguration;
import com.wowsanta.repository.mybatis.MyBatisConfiguration;
import com.wowsanta.scim.ScimException;
import com.wowsanta.scim.config.Configuration;
import com.wowsanta.scim.config.ConfigurationBuilder;
import com.wowsanta.scim.config.DomainKey;
import com.wowsanta.scim.json.DomainKeyTypeAdapter;
import com.wowsanta.scim.json.RestfulServiceAdapter;
import com.wowsanta.scim.service.RestfulService;
import com.wowsanta.server.ServiceStructure;
import com.wowsanta.server.spark.SparkServer;


public class ProviderTest {

	private String provider_config_file = "./config/dev.provider.json"; 
	private String server_config_5000_file   = "./config/dev.server.5000.json";
	
	private String repository_config_file_1 = "./config/default.repository.wowsanta.mybatis.json";
	private String repository_config_file_2 = "./config/default.repository.wowsanta.hibernate.json";
	private String repository_config_file_3 = "./config/default.repository.local_tibero.hibernate.json";
			

	@Test
	public void load_provider_test() {
		try {
			
			Type type = new TypeToken<Map<DomainKey, Configuration>>(){}.getType();
			ConfigurationBuilder.builder.registerTypeAdapter(type, new DomainKeyTypeAdapter());
			
			Type type2 = new TypeToken<RestfulService>(){}.getType();
			ConfigurationBuilder.builder.registerTypeAdapter(type2, new RestfulServiceAdapter());
			
			ProviderAgent provider = ConfigurationBuilder.load(ProviderAgent.class, provider_config_file);
			
			provider.build();
			provider.initialize();
			provider.start();
			
		} catch (ScimException e) {
			e.printStackTrace();
		}
	}
	//@Test
	public void create_provider_test() {
		try {
			
			Type type = new TypeToken<Map<DomainKey, Configuration>>(){}.getType();
			ConfigurationBuilder.builder.registerTypeAdapter(type, new DomainKeyTypeAdapter());
			
			ProviderAgent provider = new ProviderAgent();
			SparkServer server =  ConfigurationBuilder.load(SparkServer.class,server_config_5000_file);
			provider.setServerConfig(server.save(server_config_5000_file));
			
			Properties prop = new Properties();
			prop.put(ServiceStructure.DOMAIN, "local.dev.scim");
			prop.put(ServiceStructure.REPOSITORY, "hibernate");
			prop.put(ServiceStructure.CLASSES,"./we-scim-service/bin;./bin;./bin/main");
			
			
			provider.setSettings(prop);

			
			Configuration mybatis_config = new Configuration(repository_config_file_1, MyBatisConfiguration.class);
			Configuration hibernate_config = new Configuration(repository_config_file_2, HibernateConfiguration.class);
			Configuration hibernate_tibero_config = new Configuration(repository_config_file_3, HibernateConfiguration.class);
			
			provider.addRepository(new DomainKey("local.dev.scim","mybatis"),mybatis_config);
			provider.addRepository(new DomainKey("local.dev.scim","hibernate"),hibernate_config);
			
			provider.build();
			provider.initialize();
			
			System.out.println("[service structure]------------------");
			System.out.println(ConfigurationBuilder.toJson(ServiceStructure.getInstance()));
			
			ConfigurationBuilder.save(provider, provider_config_file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
