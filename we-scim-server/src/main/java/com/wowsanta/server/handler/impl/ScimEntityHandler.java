package com.wowsanta.server.handler.impl;

import java.io.File;

import com.wowsanta.scim.annotation.AnnotationHandler;
import com.wowsanta.scim.annotation.Constants;
import com.wowsanta.scim.annotation.SCIM_ENTITY;
import com.wowsanta.scim.entity.EntityInfo;
import com.wowsanta.scim.type.RestfulServiceType;
import com.wowsanta.server.ServiceStructure;
import com.wowsanta.server.service.impl.EntityRestful_Create_Service;
import com.wowsanta.server.service.impl.EntityRestful_Delete_Service;
import com.wowsanta.server.service.impl.EntityRestful_Read_Service;
import com.wowsanta.server.service.impl.EntityRestful_Update_Service;
import com.wowsanta.util.log.LOGGER;

import spark.route.HttpMethod;

public class ScimEntityHandler extends AnnotationHandler {
	
	ServiceStructure structure = null;
	public ScimEntityHandler(ServiceStructure structure) {
		this.structure = structure;
	}
	@Override
	public boolean visit(File root, File file) {
		String class_name = createClassName(root,file);
		try {
			Class<?> clazz = Class.forName(class_name);
			SCIM_ENTITY annotation = (SCIM_ENTITY) clazz.getAnnotation(SCIM_ENTITY.class);
			if(annotation != null) {
				EntityInfo entity = structure.getEntity(annotation.name());
				System.out.println(entity.getName()  + "<<<<<<<<<");
				entity.setClassName(class_name);
				entity.setImplClss(clazz);
				entity.setRepository(annotation.repository());
				for (RestfulServiceType restful_type : annotation.restful()) {
					switch (restful_type) {
					case ALL:
						make_createService(entity);
						make_readService(entity);
						make_updateService(entity);
						make_deleteService(entity);
						make_searchService(entity);
						break;
					case CREATE:
						make_createService(entity);
						break;
					case READ:
						make_readService(entity);
						break;
					case UPDATE:
						make_updateService(entity);
						break;
					case DELETE:
						make_deleteService(entity);
						break;
					case SEARCH:
						make_searchService(entity);
						break;
					default:
						break;
					}
				}
			}
			
		} catch (ClassNotFoundException e) {
			LOGGER.system.debug("CLASS NOT FOUND : {} ", class_name);
		}
		
		return false;
	}

	private void make_searchService(EntityInfo entity) {
		entity.addRestfulService(EntityRestful_Delete_Service.builder()
				.name("search")
				.method(HttpMethod.post.toString())
				.url(getServiceUrl(entity.getName(), ""))
				.build());
	}

	private void make_deleteService(EntityInfo entity) {
		entity.addRestfulService(EntityRestful_Delete_Service.builder()
				.name("delete")
				.method(HttpMethod.delete.toString())
				.url(getServiceUrl(entity.getName(), ":id"))
				.build());
	}

	private void make_updateService(EntityInfo entity) {
		entity.addRestfulService(EntityRestful_Update_Service.builder()
				.name("update")
				.method(HttpMethod.patch.toString())
				.url(getServiceUrl(entity.getName(), ":id"))
				.build());
	}

	private void make_readService(EntityInfo entity) {
		entity.addRestfulService(EntityRestful_Read_Service.builder()
				.name("read")
				.method(HttpMethod.get.toString())
				.url(getServiceUrl(entity.getName(), ":id"))
				.build());
	}

	private void make_createService(EntityInfo entity) {
		entity.addRestfulService(EntityRestful_Create_Service.builder()
				.name("create")
				.method(HttpMethod.post.toString())
				.url(getServiceUrl(entity.getName(), null))
				.build());
	}
	
//	private String getRealRepository(String repository) {
//		String real_repository = (String)ServiceStructure.getInstance().getProperty(ServiceStructure.REPOSITORY,Constants.DEFATUL_REPOSITORY);
//		if(repository.endsWith(Constants.DEFATUL_REPOSITORY)) {
//			return real_repository;
//		}
//		return real_repository;
//	}
	
	public String getServiceUrl(String entity_name, String params) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("/");
		buffer.append(entity_name.replace("_", "/"));
		buffer.append("s");
		
		if(params != null) {
			buffer.append("/").append(params);
		}

		return buffer.toString();
	}

	private String getRealDomain(String entity_domain) {
		String real_dommain = (String)ServiceStructure.getInstance().getProperty(ServiceStructure.DOMAIN,Constants.DEFATUL_DOMAIN);
		if(entity_domain.endsWith(Constants.DEFATUL_DOMAIN)) {
			return real_dommain;
		}
		return entity_domain;
	}
}
