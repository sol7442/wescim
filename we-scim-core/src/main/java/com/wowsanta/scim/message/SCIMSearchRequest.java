package com.wowsanta.scim.message;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class SCIMSearchRequest {
	private List<String> attributes = new ArrayList<String>();
	private String filter ;
	private int startIndex;
	private int count;
}
