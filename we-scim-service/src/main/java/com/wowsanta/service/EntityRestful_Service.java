package com.wowsanta.service;


import com.wowsanta.entity.ScimEntity;
import com.wowsanta.repository.Repository;
import com.wowsanta.repository.Session;
import com.wowsanta.scim.ScimException;
import com.wowsanta.scim.service.RestfulService;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class EntityRestful_Service extends RestfulService{
	
	@SuppressWarnings("unchecked")
	public Repository<ScimEntity> getRepoisitory(Session session) throws ScimException{
		Repository<ScimEntity> repository = null;
		try {
			Class<?> repository_class = Class.forName(entity.getRepository());
			repository =  (Repository<ScimEntity>) session.getRepository(repository_class);
		} catch (ClassNotFoundException e) {
			throw new ScimException(e);
		}
		
		return repository;
	}
}
