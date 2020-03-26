package com.wowsanta.scim.service;


import com.wowsanta.scim.entity.EntityInfo;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(exclude = "entity")
public class RestfulService {
	public String name;
	public String method;
	public String url;
	public transient EntityInfo entity;
}
