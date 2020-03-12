package com.wowsanta.scim.entity;


import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.wowsanta.scim.service.RestfulService;

import lombok.Data;

@Data
public class EntityInfo {
	private transient Class<?> implClss;
	private transient String name;
	private String className;
	private String repository;
	private Map<String,RestfulService> services ;
	
	private Map<String, EntityInfo> domains;

	public Set<Entry<String, RestfulService>> getRestfulServiceSet(){
		if(services != null) {
			return services.entrySet();
		}
		return null;
	}
	public void addRestfulService(RestfulService service) {
		if(services == null) {
			services = new HashMap<>();
		}
		services.put(service.getName(),service);
	}
	
	public void addDomainEntity(String domain, EntityInfo info) {
		if(domains == null) {
			domains = new HashMap<>();
		}
		domains.put(domain, info);
	}
	public EntityInfo getDomainEntity(String domain) {
		if(domains == null) {
			domains = new HashMap<>();
		}
		return domains.get(domain);
	}
}

