package com.wowsanta.repository;

import com.wowsanta.scim.entity.Resource;
import com.wowsanta.scim.message.SCIMListResponse;
import com.wowsanta.scim.message.SCIMSearchRequest;

public interface Repository <T extends Resource> {
	public void create(T resource);
	public T read(String id);
	public void update(T resource);
	public void delete(T resource);
	public SCIMListResponse<T> search(SCIMSearchRequest request);
}
