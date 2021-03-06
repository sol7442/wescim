package com.wowsanta.scim.entity;


import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.wowsanta.scim.SCIM_EXT_SCHEMA_TYPES;
import com.wowsanta.scim.SCIM_REPOSITORY_TYPES;
import com.wowsanta.scim.annotation.SCIM_ENTITY;

import lombok.Data;

@Data
@Entity
@Table(name = "WS_SYSTEM_USER")
@SCIM_ENTITY(
		name		= SCIM_EXT_SCHEMA_TYPES.SYSTEM_USER_SCHEMA,
		schema 		= SCIM_EXT_SCHEMA_TYPES.SYSTEM_USER_SCHEMA_URI,
		repository	= SCIM_REPOSITORY_TYPES.SYSTEM_USER_REPOSITORY)
public class SystemUser implements ScimResource {
	
	@Id
	@Column(name = "id", columnDefinition = "VARCHAR(64)")
	protected String id;// external id
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "systemId")
	private ScimSystem system;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "userId")
	private ScimUser user;
	
	@Embedded
	private SystemMeta meta;
	
}
