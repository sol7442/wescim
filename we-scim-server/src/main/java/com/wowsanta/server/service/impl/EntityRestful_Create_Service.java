package com.wowsanta.server.service.impl;

import java.lang.reflect.Type;

import com.wowsanta.entity.ScimEntity;
import com.wowsanta.repository.Repository;
import com.wowsanta.repository.RepositoryManager;
import com.wowsanta.repository.Session;
import com.wowsanta.scim.entity.EntityInfo;
import com.wowsanta.scim.json.JsonUtil;
import com.wowsanta.service.EntityRestful_Service;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import spark.Request;
import spark.Response;
import spark.Route;

@Data
@EqualsAndHashCode(callSuper=false)
public class EntityRestful_Create_Service extends EntityRestful_Service implements Route{
	//private final EntityRestful_Service service;
	public EntityRestful_Create_Service(EntityInfo entity) {
		this.entity = entity ;
	}
	@Override
	public Object handle(Request request, Response response) throws Exception {
		try (Session session = RepositoryManager.getInstance().openSession()) {
//			String json = request.body();
//			ScimEntity res = (ScimEntity) JsonUtil.parse(service.getEntity().getImplClss(), json);
//
//			
//			Repository<ScimEntity> repository = service.getRepoisitory(session);
//			repository.create(res);

			
			session.commit();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			
		}
		
		return EntityRestful_Create_Service.class.getName();
	}
}
