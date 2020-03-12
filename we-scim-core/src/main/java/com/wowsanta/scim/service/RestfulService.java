package com.wowsanta.scim.service;

import com.wowsanta.scim.entity.EntityInfo;

public interface RestfulService {
	public void setName(String name);
	public String getName();
	public void setMethod(String method);
	public String getMethod();
	public void setUrl(String url);
	public String getUrl();
	public void setEntity(EntityInfo value);
}
