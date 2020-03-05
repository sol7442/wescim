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
import com.wowsanta.scim.entity.ScimOrg;
import com.wowsanta.scim.repository.OrgRepository;

public class MybatisOrgTest {
	private String repository_config_file_1 = "../config/default.repository.wowsanta.mybatis.json";

	private String test_org_parent = "parent";
	private String test_org_child1 = "child1";
	private String test_org_child2 = "child2";
	private String test_org_child3 = "child3";

	private static SessionFactory session_factory = null;

	@Before
	public void load() {
		if (session_factory == null) {
			MyBatisConfiguration mybatis_config;
			try {
				mybatis_config = ConfigurationBuilder.load(MyBatisConfiguration.class, repository_config_file_1);
				session_factory = mybatis_config.build();
			} catch (ScimException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("factory was loaded");
		}
	}

	@Test
	public void delete_org_test() {
		ScimOrg org = read_org(test_org_child2);
		if (org != null) {
			try (Session session = session_factory.openSession()) {

				Date today = new Date();
				Calendar c = Calendar.getInstance();
				c.setTime(today);
				c.add(Calendar.DATE, 10);
				Date expried = c.getTime();

				org.getMeta().setLastModified(today);
				org.getMeta().setExpire(expried);
				org.getMeta().setActive(false);

				OrgRepository repository = session.getRepository(OrgRepository.class);
				repository.update(org);
				session.commit();

				System.out.println("delete org : " + org);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("org not exit : " + org);
		}

	}

	@Test
	public void create_org_test() {
		create_org(test_org_parent, null);
		create_org(test_org_child1, test_org_parent);
		create_org(test_org_child2, test_org_child1);
		create_org(test_org_child3, test_org_child1);
	}

	private void create_org(String test_org_name, String parent_name) {
		ScimOrg new_org = read_org(test_org_name);
		ScimOrg parent_org = read_org(parent_name);
		if (new_org == null) {
			try (Session session = session_factory.openSession()) {
				new_org = new ScimOrg();
				ScimMeta meta = new ScimMeta();
				meta.setActive(true);
				meta.setCreated(new Date());
				meta.setLastModified(new Date());

				new_org.setId(test_org_name);
				new_org.setName(test_org_name);
				new_org.setMeta(meta);
				new_org.setParent(parent_org);

				OrgRepository repository = session.getRepository(OrgRepository.class);
				repository.create(new_org);

				System.out.println("create org : " + new_org);

				session.commit();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("already exit : " + new_org);
		}
	}

	private ScimOrg read_org(String test_org_name) {
		if (test_org_name == null) {
			return null;
		}

		ScimOrg org = null;
		try (Session session = session_factory.openSession()) {
			OrgRepository repository = session.getRepository(OrgRepository.class);
			org = repository.read(test_org_name);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return org;
	}

	@Test
	public void update_test() {
		ScimOrg org = read_org(test_org_child1);
		if (org != null) {
			try (Session session = session_factory.openSession()) {
				org.getMeta().setLastModified(new Date());

				OrgRepository repository = session.getRepository(OrgRepository.class);
				repository.update(org);
				session.commit();

				System.out.println("update org : " + org);

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("org not exit : " + org);
		}
	}

	@Test
	public void read_test() {
		System.out.println("org : " + read_org(test_org_parent));
	}
}
