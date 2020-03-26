package com.wowsanta.entity;


import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import com.wowsanta.scim.entity.ScimResource;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
@MappedSuperclass
public abstract class ScimEntity implements ScimResource {
	@Id
	@Column(name = "id", columnDefinition = "VARCHAR(64)")
	protected String id;
	
	@Column(name = "name", columnDefinition = "VARCHAR(64)")
	protected String name;
	
}
