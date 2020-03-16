package com.wowsanta.service;


import com.wowsanta.entity.ScimResource;
import com.wowsanta.repository.Repository;
import com.wowsanta.repository.Session;
import com.wowsanta.scim.ScimException;
import com.wowsanta.scim.entity.EntityInfo;
import com.wowsanta.scim.service.RestfulService;

import lombok.Data;

@Data
public class EntityRestful_Service implements RestfulService{
	
	public String name;
	public String method;
	public String url;
	public EntityInfo entity;
	
	@SuppressWarnings("unchecked")
	public Repository<ScimResource> getRepoisitory(Session session) throws ScimException{
		Repository<ScimResource> repository = null;
		try {
			Class<?> repository_class = Class.forName(entity.getRepository());
			repository =  (Repository<ScimResource>) session.getRepository(repository_class);
		} catch (ClassNotFoundException e) {
			throw new ScimException(e);
		}
		
		return repository;
	}
}
