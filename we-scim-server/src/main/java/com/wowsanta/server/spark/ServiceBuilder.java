package com.wowsanta.server.spark;

import java.util.ArrayList;
import java.util.List;

import com.wowsanta.scim.ScimException;
import com.wowsanta.scim.config.ConfigurationBuilder;

import lombok.Data;

@Data
public class ServiceBuilder extends ConfigurationBuilder {
	
	private String name;
	private transient String fileName;
	private List<SparkService> services;
	
	public static ServiceBuilder load(String fileName) throws ScimException {
		ServiceBuilder builder = load(ServiceBuilder.class,fileName);
		builder.setFileName(fileName);
		return builder;
	}
	public static SparkService build(String file) throws ScimException {
		return load(SparkService.class,file);
	}
	
	public void addService(SparkService service) {
		if(services == null) {
			services = new ArrayList<SparkService>();
		}
		services.add(service);
	}
	public void save() throws ScimException {
		save(this,fileName);
	}
}
