package com.wowsanta.server.handler.impl;

import java.io.File;

import com.wowsanta.scim.annotation.AnnotationHandler;
import com.wowsanta.scim.annotation.ENTITY;
import com.wowsanta.scim.annotation.SERVICE;
import com.wowsanta.scim.config.Domain;
import com.wowsanta.scim.entity.EntityInfo;
import com.wowsanta.scim.type.RestfulServiceType;
import com.wowsanta.server.ServiceStructure;
import com.wowsanta.server.service.AbstractEntityRestfulService;
import com.wowsanta.server.service.impl.EntityRestful_Create_Service;
import com.wowsanta.util.log.LOGGER;

import spark.route.HttpMethod;

public class ServiceHandler extends AnnotationHandler{

	@Override
	public boolean visit(File root, File file) {
		String class_name = createClassName(root,file);
		try {
			Class<?> clazz = Class.forName(class_name);
			SERVICE service_annotation = (SERVICE) clazz.getAnnotation(SERVICE.class);
			if(service_annotation != null) {
				ServiceStructure structure = ServiceStructure.getInstance();
				EntityInfo entity = structure.getEntity(service_annotation.entity());
				if(entity == null) {
					entity = new EntityInfo();
					entity.setName(service_annotation.entity());
				}
				
				AbstractEntityRestfulService service = (AbstractEntityRestfulService) clazz.newInstance();
				service.setName(service_annotation.name());
				service.setMethod(service_annotation.method());
				service.setUrl(service_annotation.url());
				entity.addRestfulService(service);
				
				structure.addEntity(entity);
				
				LOGGER.system.debug("regist service : {} ", service);
			}
			
		} catch (ClassNotFoundException e) {
			LOGGER.system.debug("CLASS NOT FOUND : {} ", class_name);
		} catch (InstantiationException e) {
			LOGGER.error(e);
		} catch (IllegalAccessException e) {
			LOGGER.error(e);
		}
		
		return false;
	}

}
