package com.wowsanta.server.spark;

import java.util.ArrayList;
import java.util.List;

import com.wowsanta.server.Filter;
import com.wowsanta.service.Service;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SparkService implements Service {
	private String method;
	private String path;
	private String className;
	private String tranName;
	
	private transient List<Filter> filter = new ArrayList<Filter>();
	
	@Override
	public void registFilter(Filter filter) {
		
	}
}
