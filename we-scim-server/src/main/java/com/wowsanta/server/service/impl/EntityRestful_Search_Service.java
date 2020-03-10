package com.wowsanta.server.service.impl;

import com.wowsanta.server.service.AbstractEntityRestfulService;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import spark.Request;
import spark.Response;

@Data
@EqualsAndHashCode(callSuper=false)
@Builder
public class EntityRestful_Search_Service extends AbstractEntityRestfulService {
	String name;
	String method;
	String url;

	@Override
	public Object handle(Request request, Response response) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
