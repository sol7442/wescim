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
import com.wowsanta.scim.entity.ScimJob;
import com.wowsanta.scim.repository.JobRepository;

public class MybatisJobTest {
	private String repository_config_file_1 = "../config/default.repository.wowsanta.mybatis.json";

	private String test_job1 = "job1";
	private String test_job2 = "job2";
	private String test_job3 = "job3";
	private String test_job4 = "job4";

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
	public void delete_job_test() {
		ScimJob job = read_job(test_job3);
		if (job != null) {
			try (Session session = session_factory.openSession()) {

				Date today = new Date();
				Calendar c = Calendar.getInstance();
				c.setTime(today);
				c.add(Calendar.DATE, 10);
				Date expried = c.getTime();

				job.getMeta().setLastModified(today);
				job.getMeta().setExpire(expried);
				job.getMeta().setActive(false);

				JobRepository repository = session.getRepository(JobRepository.class);
				repository.update(job);
				session.commit();

				System.out.println("delete job : " + job);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("job not exit : " + job);
		}

	}

	@Test
	public void create_job_test() {
		create_job(test_job1, 1);
		create_job(test_job2, 2);
		create_job(test_job3, 3);
		create_job(test_job4, 4);
	}

	private void create_job(String test_job, int rank) {
		ScimJob new_job = read_job(test_job);
		if (new_job == null) {
			try (Session session = session_factory.openSession()) {
				new_job = new ScimJob();
				ScimMeta meta = new ScimMeta();
				meta.setActive(true);
				meta.setCreated(new Date());
				meta.setLastModified(new Date());

				new_job.setId(test_job);
				new_job.setName(test_job);
				new_job.setMeta(meta);

				JobRepository repository = session.getRepository(JobRepository.class);
				repository.create(new_job);

				System.out.println("create job : " + new_job);

				session.commit();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("already exit : " + new_job);
		}
	}

	private ScimJob read_job(String job_name) {
		if (job_name == null) {
			return null;
		}

		ScimJob job = null;
		try (Session session = session_factory.openSession()) {
			JobRepository repository = session.getRepository(JobRepository.class);
			job = repository.read(job_name);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return job;
	}

	@Test
	public void update_test() {
		ScimJob job = read_job(test_job2);
		if (job != null) {
			try (Session session = session_factory.openSession()) {
				job.getMeta().setLastModified(new Date());

				JobRepository repository = session.getRepository(JobRepository.class);
				repository.update(job);
				session.commit();

				System.out.println("update job : " + job);

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("job not exit : " + job);
		}
	}

	@Test
	public void read_test() {
		System.out.println("job : " + read_job(test_job1));
	}
}
