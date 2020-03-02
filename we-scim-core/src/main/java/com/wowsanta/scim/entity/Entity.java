package com.wowsanta.scim.entity;


import lombok.Data;

@Data
public class Entity {
	private transient Class<?> implClss;
	private String className;
	private String repository;
	
	@Data
	public static class Key {
		private final String domain;
		private final String name;
		public Key(String name, String domain) {
			this.name 	= name;
			this.domain = domain;
		}
	}
}
