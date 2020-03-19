package com.wowsanta.server.spark;

import com.wowsanta.scim.service.RestfulService;
import com.wowsanta.server.service.impl.EntityRestful_Create_Service;
import com.wowsanta.server.service.impl.EntityRestful_Delete_Service;
import com.wowsanta.server.service.impl.EntityRestful_Read_Service;
import com.wowsanta.server.service.impl.EntityRestful_Search_Service;
import com.wowsanta.server.service.impl.EntityRestful_Update_Service;
import com.wowsanta.service.EntityRestful_Service;

import spark.Route;

public class SparkServiceBuilder {

	public Route build_route(RestfulService value) {
		System.out.println(">>>" + value.getName());
		switch (value.getName()) {
		case "read":
			return new EntityRestful_Read_Service((EntityRestful_Service) value);
		case "create":
			return new EntityRestful_Create_Service((EntityRestful_Service) value);
		case "update":
			return new EntityRestful_Update_Service((EntityRestful_Service) value);
		case "delete":
			return new EntityRestful_Delete_Service((EntityRestful_Service) value);
		case "search":
			return new EntityRestful_Search_Service((EntityRestful_Service) value);
		default:
			return (Route) value;
		}
	}

}
