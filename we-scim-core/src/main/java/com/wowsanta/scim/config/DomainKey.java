package com.wowsanta.scim.config;

import lombok.Data;

@Data
public class DomainKey {
	private String domain;
	private String name;

	public DomainKey(String domain, String name) {
		this.domain = domain;
		this.name = name;
	}
}
