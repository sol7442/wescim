package com.wowsanta.scim.entity;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wowsanta.scim.config.DomainKey;
import com.wowsanta.scim.service.RestfulService;

import lombok.Data;

@Data
public class EntityInfo {
	private transient Class<?> implClss;
	private String name;
	private String domain;
	private String className;
	private String repository;
	
	private transient DomainKey key;
	
	private Map<String,String> services = new HashMap<String, String>();
	private transient List<RestfulService> restful = new ArrayList<RestfulService>();

	public void addRestfulService(RestfulService service) {
		restful.add(service);
		services.put(service.getName(),service.getUrl());
	}
}

