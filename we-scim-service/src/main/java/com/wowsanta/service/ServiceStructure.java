package com.wowsanta.service;

import java.io.File;
import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import com.wowsanta.repository.SessionFactory;
import com.wowsanta.scim.annotation.AnnotationHandler;
import com.wowsanta.scim.annotation.DOMAIN_ENTITY;
import com.wowsanta.scim.annotation.REPOSITORY;
import com.wowsanta.scim.annotation.SCIM_ENTITY;
import com.wowsanta.scim.annotation.SERVICE;
import com.wowsanta.scim.entity.EntityInfo;
import com.wowsanta.scim.service.RestfulService;
import com.wowsanta.scim.type.RestfulServiceType;
import com.wowsanta.util.log.LOGGER;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper=false)
public class ServiceStructure extends AnnotationHandler {
	
	private Properties property;
	private Map<String, EntityInfo> entitis = new HashMap<>();;
	//private Map<DomainKey,Configuration> repsitories = new HashMap<>();;
	private Map<String,SessionFactory> sessionFactories = new HashMap<>();;
	private Map<String, String> domains = new HashMap<>();;
	
	private transient static ServiceStructure instance = null;
	
	public static ServiceStructure getInstance() {
		if(instance == null) {
			instance = new ServiceStructure();
		}
		return instance;
	}
	
	public Object getProperty(String key, String defulat) {
		if(this.property != null) {
			if(this.property.get(key) != null) {
				return this.property.get(key);
			}
			return defulat;
		}
		return defulat;
	}
	
	public void addEntity(String name, EntityInfo entity) {
		this.entitis.put(name, entity);
	}
	public synchronized EntityInfo getEntity(String name) {
		return entitis.get(name);
	}
	public Set<Entry<String, EntityInfo>> getEntitySet(){
		return this.entitis.entrySet();
	}
	public void addSessionFactory(String name,SessionFactory factory) {
		this.sessionFactories.put(name,factory);
	}

	@Override
	public boolean visit(File root, File file) {
		String class_name = createClassName(root,file);
		try {
			Class<?> clazz = Class.forName(class_name);
			Annotation[] annotations = clazz.getAnnotations();
			for (Annotation annotation : annotations) {
				if (annotation instanceof SCIM_ENTITY) {
					addScimEntity((SCIM_ENTITY) annotation,clazz);
				}else if (annotation instanceof REPOSITORY) {
					addDomainEntity((DOMAIN_ENTITY) annotation,clazz);
				}else if (annotation instanceof REPOSITORY) {
					addRepository((REPOSITORY) annotation);
				}else if (annotation instanceof SERVICE) {
					addService((SERVICE) annotation,clazz);
				}
			}
		}catch (ClassNotFoundException e) {
			LOGGER.error("CLASS NOT FOUND : {} ", class_name);
		} catch (InstantiationException e) {
			LOGGER.error("CLASS Instantiation Failed : {} ", class_name);
		} catch (IllegalAccessException e) {
			LOGGER.error(e);
		}
		return false;
	}
	private void addRepository(REPOSITORY annotation) {
		// TODO Auto-generated method stub
		
	}

	private void addService(SERVICE annotation, Class<?> clazz) throws InstantiationException, IllegalAccessException {
		EntityInfo entity = entitis.get(annotation.entity());
		if(entity == null) {
			entity = new EntityInfo();
			entitis.put(annotation.entity(), entity);
		}
		
		EntityRestful_Service service = (EntityRestful_Service) clazz.newInstance();
		service.setName(annotation.name());
		service.setMethod(annotation.method());
		service.setUrl(annotation.url());
		service.setEntity(entity);
		
		entity.addRestfulService(service);
		
	}
	private void addDomainEntity(DOMAIN_ENTITY annotation, Class<?> clazz) {
		EntityInfo entity = entitis.get(annotation.name());
		if(entity == null) {
			entity = new EntityInfo();
			entitis.put(annotation.name(), entity);
		}
		
		EntityInfo domain_entity = new EntityInfo();
		domain_entity.setName(annotation.name());
		domain_entity.setClassName(clazz.getName());
		domain_entity.setImplClss(clazz);
		entity.addDomainEntity(annotation.domain(),domain_entity);
		
	}
	private void addScimEntity(SCIM_ENTITY annotation,Class<?> clazz) {
		EntityInfo entity = entitis.get(annotation.name());
		if(entity == null) {
			entity = new EntityInfo();
			entitis.put(annotation.name(), entity);
		}
		
		entity.setName(annotation.name());
		entity.setSchema(annotation.schema());
		entity.setClassName(clazz.getName());
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
	
	private void make_searchService(EntityInfo entity) {
		RestfulService service = new EntityRestful_Service();
		service.setName("search");
		service.setMethod("post");
		service.setUrl(ServiceUtil.getServiceUrl(entity.getName(), ""));
		//service.setEntity(entity);
		entity.addRestfulService(service);
	}

	private void make_deleteService(EntityInfo entity) {
		RestfulService service = new EntityRestful_Service();
		service.setName("delete");
		service.setMethod("delete");
		service.setUrl(ServiceUtil.getServiceUrl(entity.getName(), ":id"));
		//service.setEntity(entity);
		entity.addRestfulService(service);
	}

	private void make_updateService(EntityInfo entity) {
		RestfulService service = new EntityRestful_Service();
		service.setName("update");
		service.setMethod("patch");
		service.setUrl(ServiceUtil.getServiceUrl(entity.getName(), ":id"));
		//service.setEntity(entity);
		entity.addRestfulService(service);
	}

	private void make_readService(EntityInfo entity) {
		RestfulService service = new EntityRestful_Service();
		service.setName("read");
		service.setMethod("get");
		service.setUrl(ServiceUtil.getServiceUrl(entity.getName(), ":id"));
		//service.setEntity(entity);
		entity.addRestfulService(service);
	}

	private void make_createService(EntityInfo entity) {
		RestfulService service = new EntityRestful_Service();
		service.setName("create");
		service.setMethod("post");
		service.setUrl(ServiceUtil.getServiceUrl(entity.getName(),null));
		//service.setEntity(entity);
		entity.addRestfulService(service);
	}

}

