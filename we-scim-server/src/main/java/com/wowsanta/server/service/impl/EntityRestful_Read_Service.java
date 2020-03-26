package com.wowsanta.server.service.impl;

import com.wowsanta.entity.ScimEntity;
import com.wowsanta.repository.Repository;
import com.wowsanta.repository.RepositoryManager;
import com.wowsanta.repository.Session;
import com.wowsanta.scim.ScimException;
import com.wowsanta.scim.entity.EntityInfo;
import com.wowsanta.scim.entity.ScimResource;
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
public class EntityRestful_Read_Service extends EntityRestful_Service implements Route {
	public EntityRestful_Read_Service(EntityInfo entity) {
		this.entity = entity ;
	}
	@Override
	public Object handle(Request request, Response response) throws Exception {
		String result = "";
		try (Session session = RepositoryManager.getInstance().openSession()) {
			String read_id = request.params("id");
			if(read_id == null || read_id.equals("") ) {
				throw new ScimException("parameter is null");
			}
			
			Repository<ScimEntity> repository = getRepoisitory(session);
			ScimResource res = repository.read(read_id);
			
			result = JsonUtil.toJson(res);
		}catch (Exception e) {
			result = e.getMessage();
			e.printStackTrace();
		}finally {
			System.out.println("---------");
		}
		return result;
	}
}
