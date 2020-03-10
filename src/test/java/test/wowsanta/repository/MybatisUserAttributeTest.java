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
import com.wowsanta.scim.entity.ScimAttribute;
import com.wowsanta.scim.entity.ScimJob;
import com.wowsanta.scim.entity.ScimMeta;
import com.wowsanta.scim.entity.ScimOrg;
import com.wowsanta.scim.entity.ScimPos;
import com.wowsanta.scim.entity.ScimUser;
import com.wowsanta.scim.repository.JobRepository;
import com.wowsanta.scim.repository.OrgRepository;
import com.wowsanta.scim.repository.PosRepository;
import com.wowsanta.scim.repository.UserRepository;


public class MybatisUserAttributeTest {
	private String repository_config_file_1 = "../config/default.repository.wowsanta.mybatis.json";

	private String test_user_id = "tester";
	private String test_org2 	= "child2";
	private String test_pos2 	= "pos2";
	private String test_job3 	= "job3";
	
	
	private static SessionFactory session_factory = null;
	@Before
	public void load() {
		if(session_factory == null){
			MyBatisConfiguration mybatis_config;
			try {
				mybatis_config = ConfigurationBuilder.load(MyBatisConfiguration.class, repository_config_file_1);
				session_factory = mybatis_config.build(null);
			} catch (ScimException e) {
				e.printStackTrace();
			}
		}else {
			System.out.println("factory was loaded");
		}
	}
	

	@Test
	public void update_test() {
		
		try(Session session = session_factory.openSession()){
			UserRepository user_repository = session.getRepository(UserRepository.class);
			OrgRepository  org_repository  = session.getRepository(OrgRepository.class);
			PosRepository  pos_repository  = session.getRepository(PosRepository.class);
			JobRepository  job_repository  = session.getRepository(JobRepository.class);
			
			ScimUser user = user_repository.read(test_user_id);
			ScimOrg  org  = org_repository.read(test_org2);
			ScimPos  pos  = pos_repository.read(test_pos2);
			ScimJob  job  = job_repository.read(test_job3);
			
			
			System.out.println("user :  " +  user );
			System.out.println("org :  " +  org );
			System.out.println("pos :  " +  pos );
			System.out.println("job :  " +  job );
			
			
			if(user != null) {
				user.setAttr(new ScimAttribute(org,pos,job));
				user.getMeta().setLastModified(new Date());

				user_repository.update(user);
			}
			
			session.commit();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
