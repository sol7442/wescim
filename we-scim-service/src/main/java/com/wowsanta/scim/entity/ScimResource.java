package com.wowsanta.scim.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.Data;

@Data
@MappedSuperclass
public abstract class ScimResource implements Resource {
	@Id
	@Column(name = "id", columnDefinition = "VARCHAR(64)")
	protected String id;
	
	@Column(name = "name", columnDefinition = "VARCHAR(64)")
	protected String name;
	
}
