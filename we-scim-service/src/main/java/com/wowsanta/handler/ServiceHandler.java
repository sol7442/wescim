package com.wowsanta.handler;

import java.io.File;

import com.wowsanta.scim.annotation.AnnotationHandler;
import com.wowsanta.scim.annotation.SERVICE;
import com.wowsanta.scim.entity.EntityInfo;
import com.wowsanta.service.EntityRestful_Service;
import com.wowsanta.service.ServiceStructure;
import com.wowsanta.util.log.LOGGER;


public class ServiceHandler extends AnnotationHandler{
	ServiceStructure structure;
	public ServiceHandler(ServiceStructure structure) {
		this.structure = structure;
	}
	@Override
	public boolean visit(File root, File file) {
		String class_name = createClassName(root,file);
		try {
			Class<?> clazz = Class.forName(class_name);
			SERVICE annotation = (SERVICE) clazz.getAnnotation(SERVICE.class);
			if(annotation != null) {
				
				EntityInfo entity = structure.getEntity(annotation.entity());
				if(entity == null) {
					entity = new EntityInfo();
					structure.addEntity(annotation.entity(), entity);
				}
				
				
				EntityRestful_Service service = (EntityRestful_Service) clazz.newInstance();
				service.setName(annotation.name());
				service.setMethod(annotation.method());
				service.setUrl(annotation.url());
				entity.addRestfulService(service);
				
				//structure.addEntity(entity);
				
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
