package test.wowsanta.mybatis;



import org.junit.Test;

import com.wowsanta.repository.DataSource;
import com.wowsanta.repository.hibernate.HibernateConfiguration;
import com.wowsanta.scim.ScimException;
import com.wowsanta.scim.config.Configuration;
import com.wowsanta.scim.config.ConfigurationBuilder;

public class HibernateTest {

	private String repository_config_file_1 = "../config/default.repository.wowsanta.hibernate.json";
	
	@Test
	public void create_test() {
		try {
			HibernateConfiguration hibernate_config = new HibernateConfiguration();
			DataSource data_info = new DataSource();
			data_info.setUrl("jdbc:mysql://wession.com:3306/ws_scim_v1?useUnicode=true&serverTimezone=UTC");
			data_info.setDriver("com.mysql.cj.jdbc.Driver");
			data_info.setUser("root");
			data_info.setPassword("wession@12");
			
			hibernate_config.setDataInfo(data_info);
			hibernate_config.setDialect("org.hibernate.dialect.MySQL5InnoDBDialect");
			hibernate_config.setFormatsql("true");
			hibernate_config.setShowsql("true");
			hibernate_config.setHbm2ddlauto("update");
			
			System.out.println(ConfigurationBuilder.toJson(hibernate_config));
			Configuration config =  ConfigurationBuilder.save(hibernate_config, repository_config_file_1);
			System.out.println(ConfigurationBuilder.toJson(config));
			
		} catch (ScimException e) {
			e.printStackTrace();
		};
		
	}
}
