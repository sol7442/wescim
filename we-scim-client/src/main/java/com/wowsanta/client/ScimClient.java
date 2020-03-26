package com.wowsanta.client;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;

import com.wowsanta.scim.ScimException;
import com.wowsanta.scim.entity.EntityInfo;
import com.wowsanta.scim.entity.ScimResource;
import com.wowsanta.scim.message.SCIMListResponse;
import com.wowsanta.scim.message.SCIMSearchRequest;
import com.wowsanta.scim.service.RestfulService;
import com.wowsanta.scim.service.ScimService;
import com.wowsanta.service.ServiceUtil;
import com.wowsanta.util.log.LOGGER;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(exclude= {"client"})
public class ScimClient implements ScimService<ScimResource>  {
	private CloseableHttpClient client;
	
	private EntityInfo entity;
	private String domain;
	private String url;
	
	
	@Override
	public void create(ScimResource resource) throws ScimException{
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public int size(String filter) throws ScimException{
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public ScimResource read(String id) throws ScimException {
		ScimResource resource = null;
		
		String call_url = null;
		try {
			call_url = getUrl("read",id);
			String result = "";
			
			HttpGet get = new HttpGet(call_url);
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			LOGGER.process.info("CALL : {} ", call_url );
		}
		return resource;
	}
	private String getUrl(String domain, String method, String id) {
		EntityInfo domain_entity = entity.getDomainEntity(domain);
		if(domain_entity == null) {
			domain_entity = entity; 
		}
		String entity_name = domain_entity.getName();
		String service_url = ServiceUtil.getServiceUrl(entity_name, id);
		return this.url + "" + service_url;
	}
	private String getUrl(String method, String id) {
		return getUrl(this.domain,method,id);
	}
	@Override
	public void update(ScimResource resource) throws ScimException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void delete(ScimResource resource) throws ScimException{
		// TODO Auto-generated method stub
		
	}
	@Override
	public SCIMListResponse<ScimResource> search(SCIMSearchRequest request) throws ScimException{
		// TODO Auto-generated method stub
		return null;
	}
	
}
