package com.wowsanta.service;

public class ServiceUtil {
	public static String getServiceUrl(String entity_name, String params) {
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
