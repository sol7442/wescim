package test.wowsanta.mybatis;


import javax.sql.DataSource;

import org.junit.Test;
import com.wowsanta.repository.mybatis.MyBatisDataSource;
import com.wowsanta.scim.config.ConfigurationFactory;

public class MybatisTest {

	@Test
	public void test() {
		try {
			
			
//			MyBatisDataSource config = MyBatisDataSource.builder().
//									driver("com.mysql.cj.jdbc.Driver").
//									url("jdbc:mysql://wession.com:3306/ws_scim_im?useUnicode=true&characterEncoding=utf8&serverTimezone=UTC").
//									username("root").
//									password("wession@12").
//									build();
//			
//			System.out.println(config.build());
//			
//			ConfigurationFactory.save(config,"../config/datasource.wowsanta.mybatis.json");
			
//			
//			MyBatisDataSource load_config = ConfigurationFactory.load(MyBatisDataSource.class, "../config/datasource.wowsanta.mybatis.json");
//			
//			DataSource data_source = load_config.build();
//			System.out.println(data_source);
			
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
