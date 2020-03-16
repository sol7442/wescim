package test.wowsanta.client;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.junit.Before;
import org.junit.Test;

import com.wowsanta.client.ClientBuilder;
import com.wowsanta.client.ScimClient;
import com.wowsanta.scim.ScimException;
import com.wowsanta.scim.entity.ScimUser;

public class CilentReadTest {

	@Before
	public void initialize() {
		try {
			ClientBuilder.getInstance().initialize();
		} catch (ScimException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void read_user2_test() {
		
		ScimClient client = ClientBuilder.getInstance().build();
		
		try {
			ScimUser user_1234 = client.read("1244", ScimUser.class);
			
			
			System.out.println(user_1234);
		} catch (ScimException e) {
			e.printStackTrace();
		}
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
