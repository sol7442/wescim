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
import com.wowsanta.scim.entity.ScimPos;
import com.wowsanta.scim.repository.PosRepository;

public class MybatisPosTest {
	private String repository_config_file_1 = "../config/default.repository.wowsanta.mybatis.json";

	private String test_pos1 = "pos1";
	private String test_pos2 = "pos2";
	private String test_pos3 = "pos3";
	private String test_pos4 = "pos4";

	private static SessionFactory session_factory = null;

	@Before
	public void load() {
		if (session_factory == null) {
			MyBatisConfiguration mybatis_config;
			try {
				mybatis_config = ConfigurationBuilder.load(MyBatisConfiguration.class, repository_config_file_1);
				session_factory = mybatis_config.build(null);
			} catch (ScimException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("factory was loaded");
		}
	}

	@Test
	public void delete_pos_test() {
		ScimPos org = read_pos(test_pos3);
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

				PosRepository repository = session.getRepository(PosRepository.class);
				repository.update(org);
				session.commit();

				System.out.println("delete pos : " + org);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("org not exit : " + org);
		}

	}

	@Test
	public void create_pos_test() {
		create_pos(test_pos1, 1);
		create_pos(test_pos2, 2);
		create_pos(test_pos3, 3);
		create_pos(test_pos4, 4);
	}

	private void create_pos(String test_pos, int rank) {
		ScimPos new_pos = read_pos(test_pos);
		if (new_pos == null) {
			try (Session session = session_factory.openSession()) {
				new_pos = new ScimPos();
				ScimMeta meta = new ScimMeta();
				meta.setActive(true);
				meta.setCreated(new Date());
				meta.setLastModified(new Date());

				new_pos.setId(test_pos);
				new_pos.setName(test_pos);
				new_pos.setMeta(meta);
				new_pos.setRank(rank);

				PosRepository repository = session.getRepository(PosRepository.class);
				repository.create(new_pos);

				System.out.println("create pos : " + new_pos);

				session.commit();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("already exit : " + new_pos);
		}
	}

	private ScimPos read_pos(String pos_name) {
		if (pos_name == null) {
			return null;
		}

		ScimPos pos = null;
		try (Session session = session_factory.openSession()) {
			PosRepository repository = session.getRepository(PosRepository.class);
			pos = repository.read(pos_name);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pos;
	}

	@Test
	public void update_test() {
		ScimPos pos = read_pos(test_pos2);
		if (pos != null) {
			try (Session session = session_factory.openSession()) {
				pos.getMeta().setLastModified(new Date());

				PosRepository repository = session.getRepository(PosRepository.class);
				repository.update(pos);
				session.commit();

				System.out.println("update pos : " + pos);

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("pos not exit : " + pos);
		}
	}

	@Test
	public void read_test() {
		System.out.println("pos : " + read_pos(test_pos1));
	}
}
