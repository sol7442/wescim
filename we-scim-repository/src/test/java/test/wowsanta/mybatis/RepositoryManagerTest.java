package test.wowsanta.mybatis;

import org.junit.Test;

import com.wowsanta.repository.RepositoryConfig;
import com.wowsanta.repository.RepositoryManager;
import com.wowsanta.repository.SessionFactory;
import com.wowsanta.repository.hibernate.HibernateConfiguration;
import com.wowsanta.repository.mybatis.MyBatisConfiguration;
import com.wowsanta.scim.ScimException;
import com.wowsanta.scim.config.Configuration;
import com.wowsanta.scim.config.ConfigurationBuilder;
import com.wowsanta.scim.config.Domain;
import com.wowsanta.scim.config.DomainKey;

public class RepositoryManagerTest {

	private String repository_config_file_1 = "../config/default.repository.wowsanta.mybatis.json";
	private String repository_config_file_2 = "../config/default.repository.wowsanta.hibernate.json";
	
	
	@Test
	public void load_test() {
		
		try {
			MyBatisConfiguration mybatis_config = MyBatisConfiguration.load(repository_config_file_1);
			HibernateConfiguration hibernate_config = HibernateConfiguration.load(repository_config_file_2);
			
			System.out.println(mybatis_config);
			System.out.println(hibernate_config);
			
			ConfigurationBuilder.save(mybatis_config, repository_config_file_1);
			
		} catch (ScimException e) {
			e.printStackTrace();
		}
	}
	@Test
	public void create_test() {
		RepositoryManager manager = RepositoryManager.getInstance();
		try {

			Configuration mybatis_config = new Configuration(repository_config_file_1, MyBatisConfiguration.class);
			Configuration hibernate_config = new Configuration(repository_config_file_2, HibernateConfiguration.class);
			
			SessionFactory mybatis_factory = ((RepositoryConfig) mybatis_config).build(null);
			SessionFactory hibernate_factory = ((RepositoryConfig) hibernate_config).build(null);
			
			
			
			manager.addRepository(new DomainKey("local.dev.scim","mybatis"),mybatis_factory);
			manager.addRepository(new DomainKey("local.dev.scim","hibernate"),hibernate_factory);
			
			System.out.println(ConfigurationBuilder.toJson(manager));
			
			
		} catch (ScimException e) {
			e.printStackTrace();
		}
	}
}
