package com.wowsanta.server.service.impl;

import com.wowsanta.repository.Repository;
import com.wowsanta.repository.RepositoryManager;
import com.wowsanta.repository.Session;
import com.wowsanta.scim.ScimException;
import com.wowsanta.scim.entity.Resource;
import com.wowsanta.scim.json.JsonUtil;
import com.wowsanta.server.service.AbstractEntityRestfulService;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import spark.Request;
import spark.Response;

@Data
@EqualsAndHashCode(callSuper=false)
@Builder
public class EntityRestful_Read_Service extends AbstractEntityRestfulService {
	String name;
	String method;
	String url;

	@Override
	public Object handle(Request request, Response response) throws Exception {
		String result = "";
		try (Session session = RepositoryManager.getInstance().openSession()) {
			String read_id = request.params("id");
			if(read_id == null || read_id.equals("") ) {
				throw new ScimException("parameter is null");
			}
			
			Class<?> repository_class = getRepositoryClass();
			Repository<?> repository =  (Repository<?>) session.getRepository(repository_class);
			
			Resource res = repository.read(read_id);
			
			result = JsonUtil.toJson(res);
		}catch (Exception e) {
			result = e.getMessage();
			e.printStackTrace();
		}finally {
			System.out.println("---------");
		}
		return result;
	}

	private Class<?> getRepositoryClass() {
		Class<?> repository_class = null;
		try {
			repository_class = Class.forName(entity.getRepository());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return repository_class;
	}

}
