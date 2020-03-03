package com.wowsanta.server.handler.impl;

import java.io.File;

import com.wowsanta.scim.annotation.AnnotationHandler;
import com.wowsanta.scim.annotation.Constants;
import com.wowsanta.scim.annotation.ENTITY;
import com.wowsanta.scim.config.Domain;
import com.wowsanta.scim.config.ServiceStructure;
import com.wowsanta.scim.entity.Entity;
import com.wowsanta.util.log.LOGGER;

public class EntityHandler extends AnnotationHandler {

	@Override
	public boolean visit(File root, File file) {
		String class_name = createClassName(root,file);
		try {
			Class<?> clazz = Class.forName(class_name);
			ENTITY entity_annotation = (ENTITY) clazz.getAnnotation(ENTITY.class);
			if(entity_annotation != null) {
				System.out.println(" : " +  entity_annotation.domain());
				System.out.println(" : " +  entity_annotation.name());
				
				
				ServiceStructure structure = ServiceStructure.getInstance();
				
				String repository 	= getRealRepository(entity_annotation.repository());
				String domain 		= getRealDomain(entity_annotation.domain());
				String name   		= entity_annotation.name();
				
				Domain.Key key = new Domain.Key(domain,name);
				
				Entity entity = new Entity();
				entity.setClassName(class_name);
				entity.setImplClss(clazz);
				entity.setRepository(repository);
				
				structure.addEntity(key,entity);
			}
			
		} catch (ClassNotFoundException e) {
			LOGGER.system.debug("CLASS NOT FOUND : {} ", class_name);
		}
		
		return false;
	}
	
	private String getRealRepository(String repository) {
		String real_repository = (String)ServiceStructure.getInstance().getProperty(ServiceStructure.REPOSITORY,Constants.DEFATUL_REPOSITORY);
		if(repository.endsWith(Constants.DEFATUL_REPOSITORY)) {
			return real_repository;
		}
		return real_repository;
	}

	private String getRealDomain(String entity_domain) {
		String real_dommain = (String)ServiceStructure.getInstance().getProperty(ServiceStructure.DOMAIN,Constants.DEFATUL_DOMAIN);
		if(entity_domain.endsWith(Constants.DEFATUL_DOMAIN)) {
			return real_dommain;
		}
		return entity_domain;
	}

	protected String createClassName(File root, File file) {
		StringBuffer sb = new StringBuffer();
        String fileName = file.getName();
        sb.append(fileName.substring(0, fileName.lastIndexOf(".class")));
        file = file.getParentFile();
        while (file != null && !file.equals(root)) {
            sb.insert(0, '.').insert(0, file.getName());
            file = file.getParentFile();
        }
        return sb.toString();
	}
}
