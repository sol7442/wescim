package com.wowsanta.scim.service;

import com.wowsanta.scim.entity.EntityInfo;

import lombok.Data;

@Data
public class RestfulService {
	public String name;
	public String method;
	public String url;
}
