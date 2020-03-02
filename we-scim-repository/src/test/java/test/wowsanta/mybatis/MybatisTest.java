package test.wowsanta.mybatis;



import org.junit.Test;

import com.wowsanta.repository.RepositoryManager;
import com.wowsanta.repository.Session;
import com.wowsanta.repository.SessionFactory;
import com.wowsanta.repository.mybatis.MyBatisConfiguation;
import com.wowsanta.repository.mybatis.MyBatisDataSource;
import com.wowsanta.scim.ScimException;
import com.wowsanta.scim.config.ConfigurationBuilder;
import com.wowsata.scim.UserRepository;

public class MybatisTest {

	private String repository_manager_file = "../config/manager.repository.wowsanta.mybatis.json";
	private String repository_config_file_1 = "../config/default.repository.wowsanta.mybatis.json";
	private String repository_config_file_2 = "../config/raonsso.repository.wowsanta.mybatis.json";
	
	
	@Test
	public void repository_manager_load_save_test() {
		repository_manager_load_test();
		repository_manager_save_test();
		
	}
	public void repository_manager_save_test() {
		try {
			RepositoryManager.getInstance().setName("develope");
			RepositoryManager.getInstance().save(repository_manager_file);
		} catch (ScimException e) {
			e.printStackTrace();
		};
	}
	//@Test
	public void repository_manager_load_test() {
		
		try {
			RepositoryManager repository_manager = RepositoryManager.load(repository_manager_file);
			repository_manager.build();
			
			System.out.println("--------------------------------");
			System.out.println(repository_manager.toJson(true));
			System.out.println("--------------------------------");
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	//@Test
	public void repository_manager_make_test() {
		
		RepositoryManager repository_manager = RepositoryManager.getInstance();
		try {
			MyBatisConfiguation config_1 = session_facory_load(repository_config_file_1);
			MyBatisConfiguation config_2 = session_facory_load(repository_config_file_2);
			
			System.out.println(config_1.toJson(true));
			System.out.println("--------------------------------");
			System.out.println(config_2.toJson(true));

			repository_manager.addRepositoryConfig(config_1);
			repository_manager.addRepositoryConfig(config_2);
			
			repository_manager.save(repository_manager_file);
		} catch (ScimException e) {
			e.printStackTrace();
		}
	}
	
	
	public MyBatisConfiguation session_facory_load(String file_name) {
		if(file_name == null) {
			file_name = repository_config_file_1;
		}
		
		
		MyBatisConfiguation mybatis_config = null;
		try {
			
			mybatis_config =  MyBatisConfiguation.load(MyBatisConfiguation.class, file_name) ; /// ConfigurationFactory.load(MyBatisConfiguation.class, repository_config_file);
			mybatis_config.setImplClass(MyBatisConfiguation.class.getName());
			mybatis_config.setFileName(file_name);
			
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
		}finally {
			try {
				if(mybatis_config != null) {
					mybatis_config.save(repository_config_file_1);
				}
			} catch (ScimException e) {
				e.printStackTrace();
			}
		}
		
		return mybatis_config;
	}

//	private void svae_repository_config() throws ScimException {
//		MyBatisConfiguation mybatis_config =  new MyBatisConfiguation();
//		mybatis_config.setId("dev");
//		mybatis_config.setDatasourceInfo("../config/datasource.wowsanta.mybatis.json");
//		mybatis_config.setResourcePath("../mapper/wowsanta");
//		ConfigurationFactory.save(mybatis_config, repository_config_file);
//	}
	
	public void data_source_test() {
		try {
			
			MyBatisDataSource save_config = new MyBatisDataSource();//MyBatisDataSource.builder().
			save_config.setDriver("com.mysql.cj.jdbc.Driver");
			save_config.setUrl("jdbc:mysql://wession.com:3306/ws_scim_im?useUnicode=true&characterEncoding=utf8&serverTimezone=UTC");
			save_config.setUsername("root");
			save_config.setPassword("wession@12");
			//ConfigurationFactory.save(save_config,"../config/datasource.wowsanta.mybatis.json");
			
			
			MyBatisDataSource load_config = ConfigurationBuilder.load(MyBatisDataSource.class, "../config/datasource.wowsanta.mybatis.json");
//			System.out.println(load_config.build());
			
			
			
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
