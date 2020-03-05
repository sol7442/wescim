package test.wowsanta.repository;

import java.util.Calendar;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import com.wowsanta.repository.Session;
import com.wowsanta.repository.SessionFactory;
import com.wowsanta.repository.mybatis.MyBatisConfiguration;
import com.wowsanta.scim.ScimException;
import com.wowsanta.scim.config.ConfigurationBuilder;
import com.wowsanta.scim.entity.ScimMeta;
import com.wowsanta.scim.entity.ScimUser;
import com.wowsanta.scim.repository.UserRepository;


public class MybatisUserTest {
	private String repository_config_file_1 = "../config/default.repository.wowsanta.mybatis.json";

	private String test_user_id = "tester";
	
	private static SessionFactory session_factory = null;
	@Before
	public void load() {
		if(session_factory == null){
			MyBatisConfiguration mybatis_config;
			try {
				mybatis_config = ConfigurationBuilder.load(MyBatisConfiguration.class, repository_config_file_1);
				session_factory = mybatis_config.build();
			} catch (ScimException e) {
				e.printStackTrace();
			}
		}else {
			System.out.println("factory was loaded");
		}
	}
	
	@Test
	public void delete_user_test() {
		ScimUser user = read_user();
		if(user != null) {
			try(Session session = session_factory.openSession()){
				
				Date today = new Date();
				Calendar c = Calendar.getInstance(); 
				c.setTime(today); 
				c.add(Calendar.DATE, 10);
				Date expried = c.getTime();
				
				user.getMeta().setLastModified(today);
				user.getMeta().setExpire(expried);
				user.getMeta().setActive(false);
				
				UserRepository repository = session.getRepository(UserRepository.class);
				repository.update(user);
				session.commit();
				
				System.out.println("delete user : " + user);
			}catch (Exception e) {
				e.printStackTrace();
			}	
		}else {
			System.out.println("user not exit : " + user);
		}
		
	}
	
	@Test
	public void create_user_test() {
		ScimUser user = read_user();
		if(user == null) {
			try(Session session = session_factory.openSession()){
				user = new ScimUser();
				ScimMeta meta = new ScimMeta();
				meta.setActive(true);
				meta.setCreated(new Date());
				meta.setLastModified(new Date());
				
				user.setId(test_user_id);
				user.setName(test_user_id);
				user.setMeta(meta);
				
				UserRepository repository = session.getRepository(UserRepository.class);
				repository.create(user);
				
				System.out.println("create user : " + user);
				
				session.commit();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}else {
			System.out.println("already exit : " + user);
		}
		
	}
	
	@Test
	public void update_test() {
		ScimUser user = read_user();
		if(user != null) {
			try(Session session = session_factory.openSession()){
				user.getMeta().setLastModified(new Date());

				UserRepository repository = session.getRepository(UserRepository.class);
				repository.update(user);
				session.commit();
				
				System.out.println("update user : " + user);
				
			}catch (Exception e) {
				e.printStackTrace();
			}
		}else {
			System.out.println("user not exit : " + user);
		}
	}
	
	@Test
	public void read_test() {
		System.out.println("user : " + read_user());
	}
	
	private ScimUser read_user() {
		ScimUser user = null;
		try(Session session = session_factory.openSession()){
			UserRepository repository = session.getRepository(UserRepository.class);
			user = repository.read(test_user_id);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}
}
