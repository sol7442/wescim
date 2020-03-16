package com.wowsanta.scim.message;

import com.wowsanta.scim.entity.SCIM_Resource;

import lombok.Data;

@Data
public class SCIMBulkOperation<T extends SCIM_Resource>{
	private String bulkId;
	private String method;
	private String path;
	private T data;
	private String location;
	private String status;
}
