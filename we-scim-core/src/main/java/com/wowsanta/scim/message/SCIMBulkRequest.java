package com.wowsanta.scim.message;

import java.util.List;
import lombok.Data;

@Data
public class SCIMBulkRequest {
	private int failOnErrors;
	private String requestId;
	private List<SCIMBulkOperation<?>> operations;
}
