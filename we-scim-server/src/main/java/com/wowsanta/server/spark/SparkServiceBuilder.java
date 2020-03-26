package com.wowsanta.server.spark;

import com.wowsanta.scim.entity.EntityInfo;
import com.wowsanta.scim.service.RestfulService;
import com.wowsanta.server.service.impl.EntityRestful_Create_Service;
import com.wowsanta.server.service.impl.EntityRestful_Delete_Service;
import com.wowsanta.server.service.impl.EntityRestful_Read_Service;
import com.wowsanta.server.service.impl.EntityRestful_Search_Service;
import com.wowsanta.server.service.impl.EntityRestful_Update_Service;
import com.wowsanta.service.EntityRestful_Service;

import spark.Route;

public class SparkServiceBuilder {

	public Route build(RestfulService service, EntityInfo entity) {
		System.out.println(">>>" + service.getName());
		switch (service.getName()) {
		case "read":
			return new EntityRestful_Read_Service(entity);
		case "create":
			return new EntityRestful_Create_Service(entity);
		case "update":
			return new EntityRestful_Update_Service(entity);
		case "delete":
			return new EntityRestful_Delete_Service(entity);
		case "search":
			return new EntityRestful_Search_Service(entity);
		default:
			service.setEntity(entity);
			return (Route) service;
		}
	}

}
