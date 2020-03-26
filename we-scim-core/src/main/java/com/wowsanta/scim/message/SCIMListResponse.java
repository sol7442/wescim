package com.wowsanta.scim.message;

import java.util.List;

import com.wowsanta.scim.entity.Resource;

import lombok.Data;

@Data
public class SCIMListResponse<T extends Resource>{
	private int totalResults;
	private int itemsPerPage;
	private int startIndex;
	private List<T> resources;
}
