package test.wowsanta.mybatis;



import org.junit.Test;

import com.wowsanta.repository.Session;
import com.wowsanta.repository.SessionFactory;
import com.wowsanta.repository.mybatis.MyBatisConfiguation;
import com.wowsanta.repository.mybatis.MyBatisDataSource;
import com.wowsanta.scim.config.ConfigurationFactory;
import com.wowsanta.scim.config.ScimException;
import com.wowsata.scim.UserRepository;

public class MybatisTest {

	private String repository_config_file = "../config/repository.wowsanta.mybatis.json";
	
	
	@Test
	public void session_facory_test() {
		try {
			MyBatisConfiguation mybatis_config = ConfigurationFactory.load(MyBatisConfiguation.class, "../config/repository.wowsanta.mybatis.json");
			SessionFactory factory = mybatis_config.build();
			
			try (Session session = factory.openSession()){
				
				UserRepository repository = session.getRepository(UserRepository.class);
				int total_count = repository.getTotalCount();
				
				System.out.println("total_count : " +  total_count);
				
				
			}catch (Exception e) {
				e.printStackTrace();
			} 
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void svae_repository_config() throws ScimException {
		MyBatisConfiguation mybatis_config =  new MyBatisConfiguation();
		mybatis_config.setId("dev");
		mybatis_config.setDatasourceInfo("../config/datasource.wowsanta.mybatis.json");
		mybatis_config.setResourcePath("../mapper/wowsanta");
		ConfigurationFactory.save(mybatis_config, repository_config_file);
	}
	
	public void data_source_test() {
		try {
			
			MyBatisDataSource save_config = new MyBatisDataSource();//MyBatisDataSource.builder().
			save_config.setDriver("com.mysql.cj.jdbc.Driver");
			save_config.setUrl("jdbc:mysql://wession.com:3306/ws_scim_im?useUnicode=true&characterEncoding=utf8&serverTimezone=UTC");
			save_config.setUsername("root");
			save_config.setPassword("wession@12");
			ConfigurationFactory.save(save_config,"../config/datasource.wowsanta.mybatis.json");
			
			
			MyBatisDataSource load_config = ConfigurationFactory.load(MyBatisDataSource.class, "../config/datasource.wowsanta.mybatis.json");
			System.out.println(load_config.build());
			
			
			
//			RepositoryManager mgr = RepositoryManager.load("");
//			SessionFactory fty = mgr.getSessionFactory("");
//			try(Session sesion = fty.openSession()){
//				
//				UserRepository repository = sesion.getRepository(UserRepository.class);
//				repository.getTotalCount();
//
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
