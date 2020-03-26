package com.wowsanta.repository;

import com.wowsanta.scim.entity.ScimResource;
import com.wowsanta.scim.message.SCIMListResponse;
import com.wowsanta.scim.message.SCIMSearchRequest;

public interface Repository <T extends ScimResource> {
	public int size(String filter);
	public void create(T resource);
	public T read(String id);
	public void update(T resource);
	public void delete(T resource);
	public SCIMListResponse<T> search(SCIMSearchRequest request);
}
