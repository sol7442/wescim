package com.wowsanta.server.service.impl;


import com.wowsanta.scim.entity.EntityInfo;
import com.wowsanta.service.EntityRestful_Service;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import spark.Request;
import spark.Response;
import spark.Route;

@Data
@EqualsAndHashCode(callSuper=false)
public class EntityRestful_Search_Service extends EntityRestful_Service implements Route{
	public EntityRestful_Search_Service(EntityInfo entity) {
		this.entity = entity ;
	}

	@Override
	public Object handle(Request request, Response response) throws Exception {
		return EntityRestful_Search_Service.class.getName();
	}

}
