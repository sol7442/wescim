package com.wowsanta.client;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;

import com.wowsanta.scim.ScimException;
import com.wowsanta.scim.entity.SCIM_Resource;

public class ScimClient  {
	private CloseableHttpClient client;
	public ScimClient(CloseableHttpClient client) {
		this.client = client;
	}
	public CloseableHttpClient getClient() {
		return this.client;
	}
	public <R extends SCIM_Resource> R read(String id, Class<R> resource) throws ScimException{
		try {
			HttpGet get = new HttpGet("http://127.0.0.1:5000/Scim/Users/1234");
			
			
		}catch (Exception e) {
			
		}finally {
			
		}
		return null;
	}
	
	public void create(SCIM_Resource res) throws ScimException {
		
	}
}
