package com.wowsanta.scim.message;

import java.util.List;

import lombok.Data;

@Data
public class SCIMBulkResponse {
	private List<SCIMBulkOperation<?>> operations;
}
