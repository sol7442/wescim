package com.wowsanta.scim.config;

import lombok.Data;

@Data
public class Configuration{
	private String fileName;
	private String implClss;
	
	public Configuration(String file_name, Class<?> classOf) {
		this.fileName = file_name;
		this.implClss = classOf.getName();
	}
}
