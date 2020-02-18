package com.wowsanta.server.spark;

import com.wowsanta.server.Service;

import lombok.Data;

@Data
public class SparkService implements Service {
	private String method;
	private String path;
	private String className;
	private String tranName;
	
	
	@Override
	public void regist() {
		
	}
}
