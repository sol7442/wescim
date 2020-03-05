package com.wowsanta.scim.entity;


import java.util.ArrayList;
import java.util.List;

import com.wowsanta.scim.type.RestfulServiceType;

import lombok.Data;

@Data
public class Entity {
	private transient Class<?> implClss;
	private String className;
	private String repository;
	private List<RestfulServiceType> restful = new ArrayList<RestfulServiceType>();
	@Data
	public static class Key {
		private final String domain;
		private final String name;
		public Key(String name, String domain) {
			this.name 	= name;
			this.domain = domain;
		}
	}

	public void addRestfulService(RestfulServiceType type) {
		restful.add(type);
	}
}
