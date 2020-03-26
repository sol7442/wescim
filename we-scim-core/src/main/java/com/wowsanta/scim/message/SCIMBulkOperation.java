package com.wowsanta.scim.message;

import com.wowsanta.scim.entity.ScimResource;

import lombok.Data;

@Data
public class SCIMBulkOperation<T extends ScimResource>{
	private String bulkId;
	private String method;
	private String path;
	private T data;
	private String location;
	private String status;
}
