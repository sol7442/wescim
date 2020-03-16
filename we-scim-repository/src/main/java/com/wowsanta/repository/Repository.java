package com.wowsanta.repository;

import com.wowsanta.scim.entity.SCIM_Resource;
import com.wowsanta.scim.message.SCIMListResponse;
import com.wowsanta.scim.message.SCIMSearchRequest;

public interface Repository <T extends SCIM_Resource> {
	public int size(String filter);
	public void create(T resource);
	public T read(String id);
	public void update(T resource);
	public void delete(T resource);
	public SCIMListResponse<T> search(SCIMSearchRequest request);
}
