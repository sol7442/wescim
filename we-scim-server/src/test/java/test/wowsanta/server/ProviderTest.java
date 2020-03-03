package test.wowsanta.server;

import java.util.Properties;

import org.junit.Test;

import com.wowsanta.ProviderAgent;
import com.wowsanta.repository.hibernate.HibernateConfiguration;
import com.wowsanta.repository.mybatis.MyBatisConfiguration;
import com.wowsanta.scim.ScimException;
import com.wowsanta.scim.config.Configuration;
import com.wowsanta.scim.config.ConfigurationBuilder;
import com.wowsanta.scim.config.Domain;
import com.wowsanta.scim.config.ServiceStructure;
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
	
	private String repository_config_file_1 = "../config/default.repository.wowsanta.mybatis.json";
	private String repository_config_file_2 = "../config/default.repository.wowsanta.hibernate.json";
			

	@Test
	public void create_provider_test() {
		try {
			ProviderAgent provider = new ProviderAgent();
			SparkServer server =  ConfigurationBuilder.load(SparkServer.class,server_config_5000_file);
			provider.setServerConfig(server.save(server_config_5000_file));
			
			Properties prop = new Properties();
			prop.put(ServiceStructure.DOMAIN, "local.dev.scim");
			prop.put(ServiceStructure.REPOSITORY, "hibernate");
			prop.put(ServiceStructure.CLASSES,"../we-scim-service/bin/main");
			
			
			provider.setSettings(prop);
			provider.save(provider_config_file)	;
			
			Configuration mybatis_config = new Configuration(repository_config_file_1, MyBatisConfiguration.class);
			Configuration hibernate_config = new Configuration(repository_config_file_2, HibernateConfiguration.class);
			
			provider.addRepository(new Domain.Key("local.dev.scim","mybatis"),mybatis_config);
			provider.addRepository(new Domain.Key("local.dev.scim","hibernate"),hibernate_config);
			
			provider.build();
			provider.initialize();
			
			System.out.println("[service structure]------------------");
			System.out.println(ConfigurationBuilder.toJson(ServiceStructure.getInstance()));
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
