package com.wowsanta.scim.service;

import com.wowsanta.scim.annotation.SERVICE;
import com.wowsanta.server.service.AbstractEntityRestfulService;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import spark.Request;
import spark.Response;

@Data
@EqualsAndHashCode(callSuper=false)
@Builder
@SERVICE(name="get", entity="Policy_Attr",method = "valid", url="/Policy/Attrs/vaild")
public class PolicyAttributeVaildService extends AbstractEntityRestfulService {
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
