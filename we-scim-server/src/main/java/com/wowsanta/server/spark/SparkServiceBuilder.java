package com.wowsanta.server.spark;

import com.wowsanta.scim.service.RestfulService;

import spark.Route;

public class SparkServiceBuilder {

	public Route build_route(RestfulService value) {
		switch (value.getName()) {
		case "read":
			
			break;

		default:
			break;
		}
		
		
		return null;
	}

}
