package com.wowsanta.scim.entity;


import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
@MappedSuperclass
public abstract class ScimResource implements Resource {
	@Id
	@Column(name = "id", columnDefinition = "VARCHAR(64)")
	protected String id;
	
	@Column(name = "name", columnDefinition = "VARCHAR(64)")
	protected String name;
	
}
