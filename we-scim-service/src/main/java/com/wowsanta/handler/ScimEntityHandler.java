package com.wowsanta.handler;

import java.io.File;

import com.wowsanta.scim.annotation.AnnotationHandler;
import com.wowsanta.scim.annotation.SCIM_ENTITY;
import com.wowsanta.scim.entity.EntityInfo;
import com.wowsanta.scim.service.RestfulService;
import com.wowsanta.scim.type.RestfulServiceType;
import com.wowsanta.service.EntityRestful_Service;
import com.wowsanta.service.ServiceStructure;
import com.wowsanta.util.log.LOGGER;


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
				if(entity == null) {
					entity = new EntityInfo();
					structure.addEntity(annotation.name(), entity);
				}
				entity.setName(annotation.name());
				entity.setSchema(annotation.schema());
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
		RestfulService service = new EntityRestful_Service();
		service.setName("search");
		service.setMethod("post");
		service.setUrl(getServiceUrl(entity.getName(), ""));
		
		entity.addRestfulService(service);
		
	}

	private void make_deleteService(EntityInfo entity) {
		RestfulService service = new EntityRestful_Service();
		service.setName("delete");
		service.setMethod("delete");
		service.setUrl(getServiceUrl(entity.getName(), ":id"));
		
		entity.addRestfulService(service);
	}

	private void make_updateService(EntityInfo entity) {
		RestfulService service = new EntityRestful_Service();
		service.setName("update");
		service.setMethod("patch");
		service.setUrl(getServiceUrl(entity.getName(), ":id"));
		
		entity.addRestfulService(service);
	}

	private void make_readService(EntityInfo entity) {
		RestfulService service = new EntityRestful_Service();
		service.setName("read");
		service.setMethod("get");
		service.setUrl(getServiceUrl(entity.getName(), ":id"));
		
		entity.addRestfulService(service);
	}

	private void make_createService(EntityInfo entity) {
		RestfulService service = new EntityRestful_Service();
		service.setName("create");
		service.setMethod("post");
		service.setUrl(getServiceUrl(entity.getName(),null));
		
		entity.addRestfulService(service);
	}
	
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

}
