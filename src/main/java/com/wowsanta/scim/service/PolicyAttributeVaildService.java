package com.wowsanta.scim.service;

import com.wowsanta.scim.annotation.SERVICE;
import com.wowsanta.scim.type.SCIM_SCHEMA_TYPES;
import com.wowsanta.service.EntityRestful_Service;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import spark.Request;
import spark.Response;
import spark.Route;

@Data
@EqualsAndHashCode(callSuper=false)
@Builder
@SERVICE(name="valid", entity=SCIM_SCHEMA_TYPES.POLICY_ATTR_SCHEMA,method = "get", url="/Policy/Attrs/vaild")
public class PolicyAttributeVaildService extends EntityRestful_Service implements Route{
	String name;
	String method;
	String url;
	public PolicyAttributeVaildService() {};
	public PolicyAttributeVaildService(String name,String method,String url) {
		this.name = name;
		this.method = method;
		this.url = url;
	};
	@Override
	public Object handle(Request request, Response response) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
