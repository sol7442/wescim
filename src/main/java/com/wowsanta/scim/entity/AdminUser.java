package com.wowsanta.scim.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.wowsanta.scim.SCIM_EXT_SCHEMA_TYPES;
import com.wowsanta.scim.SCIM_REPOSITORY_TYPES;
import com.wowsanta.scim.annotation.SCIM_ENTITY;
import com.wowsanta.scim.entity.ScimResource;

import lombok.Data;

@Entity
@Table(name = "WS_ADMIN_USER")
@Data
@SCIM_ENTITY(
		name		= SCIM_EXT_SCHEMA_TYPES.ADMIN_USER_SCHEMA,
		schema 		= SCIM_EXT_SCHEMA_TYPES.ADMIN_USER_SCHEMA_URI,		
		repository	= SCIM_REPOSITORY_TYPES.ADMIN_USER_REPOSITORY)
public class AdminUser implements ScimResource{
	@Id
	@Column(name = "id", columnDefinition = "VARCHAR(64)")
	private String id;
	
	@Column(name = "name", columnDefinition = "VARCHAR(64)")
	protected String name;
	
	@Column(name = "passwd", columnDefinition = "VARCHAR(64)")
	protected String passwd;
}
