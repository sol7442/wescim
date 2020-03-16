package com.wowsanta.server.service.impl;


import com.wowsanta.service.EntityRestful_Service;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import spark.Request;
import spark.Response;
import spark.Route;

@Data
@EqualsAndHashCode(callSuper=false)
public class EntityRestful_Update_Service implements Route{
	private final EntityRestful_Service service;
	public EntityRestful_Update_Service(EntityRestful_Service service) {
		this.service = service ;
	}

	@Override
	public Object handle(Request request, Response response) throws Exception {
		// TODO Auto-generated method stub
		return EntityRestful_Update_Service.class.getName();
	}

}
