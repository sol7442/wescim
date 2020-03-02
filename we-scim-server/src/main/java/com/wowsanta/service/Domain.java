package com.wowsanta.service;

import lombok.Data;

@Data
public class Domain {
	private String type; // resource, provider, consumer or source - provider - target
	private String mode; // rest,direct 
	private String url;
}
