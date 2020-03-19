package test.wowsanta.client;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;
import java.util.Map.Entry;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.junit.Before;
import org.junit.Test;

import com.wowsanta.client.ClientBuilder;
import com.wowsanta.handler.DomainEntityHandler;
import com.wowsanta.handler.ScimEntityHandler;
import com.wowsanta.handler.ServiceHandler;
import com.wowsanta.repository.RepositoryConfig;
import com.wowsanta.repository.RepositoryManager;
import com.wowsanta.repository.SessionFactory;
import com.wowsanta.scim.ScimException;
import com.wowsanta.scim.config.Configuration;
import com.wowsanta.scim.config.ConfigurationBuilder;
import com.wowsanta.scim.config.DomainKey;
import com.wowsanta.service.ServiceStructure;
import com.wowsanta.service.ServiceStructureBuilder;

public class CilentReadTest {

	@Before
	public void initialize() {
		try {
			Properties settings = new Properties();
			
			
//			RepositoryManager repositoryManager = RepositoryManager.getInstance();
//			repositoryManager.setDefault(settings.getProperty("DOMAIN"),settings.getProperty("REPOSITORY"));
//			for (Entry<DomainKey, Configuration> entry : repository_set) {
//				RepositoryConfig configuration = (RepositoryConfig) ConfigurationBuilder.load(entry.getValue());
//				SessionFactory session_factory = configuration.build(ServiceStructure.getInstance().getEntitySet());
//				repositoryManager.addRepository(entry.getKey(),session_factory);
//				ServiceStructure.getInstance().addRepository(entry.getKey(), entry.getValue());
//			}
			
			
			ServiceStructureBuilder builder = ServiceStructureBuilder.builder()
					.setClassPath("../bin")
					.addAnnotationHandler(new ScimEntityHandler(ServiceStructure.getInstance()))
					.addAnnotationHandler(new DomainEntityHandler(ServiceStructure.getInstance()))
					.addAnnotationHandler(new ServiceHandler(ServiceStructure.getInstance()));
			builder.build();
			
			ClientBuilder.getInstance().initialize();
		} catch (ScimException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void read_user2_test() {
		
		
	}
	@Test
	public void read_user1_test() {
		
		String result = "";
		try(
			CloseableHttpClient client = ClientBuilder.getInstance().build().getClient();
		){
			HttpGet get = new HttpGet("http://127.0.0.1:5000/Scim/Users/1234");
			get.addHeader("Content-Type", "application/json;UTF-8");
			get.addHeader("Accept-Charset", "UTF-8");
			
			CloseableHttpResponse response = null;
			try{
				response = client.execute(get);
				
				StringBuilder buffer = new StringBuilder();
				int http_res_code = response.getStatusLine().getStatusCode();
				
				System.out.println("res code : " +  http_res_code);
				// read result---
				// read error ---
				HttpEntity entity = response.getEntity();
				try(InputStream content = entity.getContent()){
					String line;
					BufferedReader reader = new BufferedReader(new InputStreamReader(content));
					while ((line = reader.readLine()) != null) {
						buffer.append(line);
					}
					result = buffer.toString();
					
					reader.close();
					System.out.println(result);
				}catch (Exception e) {
					e.printStackTrace();
				}
				
			}catch (Exception e) {
				e.printStackTrace();
			}finally {
				HttpClientUtils.closeQuietly(response);
			};
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			System.out.println(result);
		}
	}
}
