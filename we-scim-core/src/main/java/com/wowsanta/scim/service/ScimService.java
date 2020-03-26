package com.wowsanta.scim.service;

import com.wowsanta.scim.ScimException;
import com.wowsanta.scim.entity.Resource;
import com.wowsanta.scim.message.SCIMListResponse;
import com.wowsanta.scim.message.SCIMSearchRequest;

public interface ScimService <T extends Resource> {
	public int size(String filter) throws ScimException;
	public void create(T resource) throws ScimException;
	public T read(String id) throws ScimException;
	public void update(T resource) throws ScimException;
	public void delete(T resource) throws ScimException;
	public SCIMListResponse<T> search(SCIMSearchRequest request) throws ScimException;
}
