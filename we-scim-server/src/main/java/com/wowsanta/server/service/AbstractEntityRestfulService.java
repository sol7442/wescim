package com.wowsanta.server.service;

import com.wowsanta.scim.entity.EntityInfo;
import com.wowsanta.scim.service.RestfulService;

import lombok.Data;
import spark.Route;

@Data
public abstract class AbstractEntityRestfulService implements RestfulService, Route{
	protected EntityInfo entity;
}
