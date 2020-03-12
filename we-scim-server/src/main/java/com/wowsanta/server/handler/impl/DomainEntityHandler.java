package com.wowsanta.server.handler.impl;

import java.io.File;

import com.wowsanta.scim.annotation.AnnotationHandler;
import com.wowsanta.scim.annotation.Constants;
import com.wowsanta.scim.annotation.DOMAIN_ENTITY;
import com.wowsanta.scim.annotation.SCIM_ENTITY;
import com.wowsanta.scim.config.DomainKey;
import com.wowsanta.scim.entity.EntityInfo;
import com.wowsanta.scim.type.RestfulServiceType;
import com.wowsanta.server.ServiceStructure;
import com.wowsanta.server.service.impl.EntityRestful_Create_Service;
import com.wowsanta.server.service.impl.EntityRestful_Delete_Service;
import com.wowsanta.server.service.impl.EntityRestful_Read_Service;
import com.wowsanta.server.service.impl.EntityRestful_Update_Service;
import com.wowsanta.util.log.LOGGER;

import oracle.net.aso.c;
import spark.route.HttpMethod;

public class DomainEntityHandler extends AnnotationHandler {
	
	ServiceStructure structure = null;
	public DomainEntityHandler(ServiceStructure structure) {
		this.structure = structure;
	}
	@Override
	public boolean visit(File root, File file) {
		String class_name = createClassName(root,file);
		try {
			Class<?> clazz = Class.forName(class_name);
			DOMAIN_ENTITY annotation = (DOMAIN_ENTITY) clazz.getAnnotation(DOMAIN_ENTITY.class);
			
			if(annotation != null) {
				EntityInfo entity = structure.getEntity(annotation.name());
				System.out.println(entity.getName()  + "<<<<<<<<<");
				EntityInfo domain_entity = new EntityInfo();
				domain_entity.setName(annotation.name());
				domain_entity.setClassName(class_name);
				domain_entity.setImplClss(clazz);
				entity.addDomainEntity(annotation.domain(),domain_entity);
				
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
				.url(getServiceUrl(entity, ""))
				.build());
	}

	private void make_deleteService(EntityInfo entity) {
		entity.addRestfulService(EntityRestful_Delete_Service.builder()
				.name("delete")
				.method(HttpMethod.delete.toString())
				.url(getServiceUrl(entity, ":id"))
				.build());
	}

	private void make_updateService(EntityInfo entity) {
		entity.addRestfulService(EntityRestful_Update_Service.builder()
				.name("update")
				.method(HttpMethod.patch.toString())
				.url(getServiceUrl(entity, ":id"))
				.build());
	}

	private void make_readService(EntityInfo entity) {
		entity.addRestfulService(EntityRestful_Read_Service.builder()
				.name("read")
				.method(HttpMethod.get.toString())
				.url(getServiceUrl(entity, ":id"))
				.build());
	}

	private void make_createService(EntityInfo entity) {
		entity.addRestfulService(EntityRestful_Create_Service.builder()
				.name("create")
				.method(HttpMethod.post.toString())
				.url(getServiceUrl(entity, null))
				.build());
	}
	
	private String getRealRepository(String repository) {
		String real_repository = (String)ServiceStructure.getInstance().getProperty(ServiceStructure.REPOSITORY,Constants.DEFATUL_REPOSITORY);
		if(repository.endsWith(Constants.DEFATUL_REPOSITORY)) {
			return real_repository;
		}
		return real_repository;
	}
	
	public String getServiceUrl(EntityInfo entity, String params) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("/");
		buffer.append(entity.getName().replace("_", "/"));
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
