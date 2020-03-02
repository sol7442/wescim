package com.wowsanta.scim.config;

import lombok.Data;

public class Domain {
	public enum MODE{
		SERVICE,DIRECT,EAI
	}
	
	public enum TYPE{
		RESOURCE,PROVIDER,CONSUMER
	}

	public String name;
	public boolean used;
	public MODE mode;
	public TYPE type;
	public String file;
	
	@Data
	public static class Key{
		private String domain;
		private String name;
		
		public Key(String domain, String name) {
			this.domain = domain;
			this.name   = name;
		}
	}
}
