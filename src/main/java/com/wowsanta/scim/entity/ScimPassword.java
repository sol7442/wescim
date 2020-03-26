package com.wowsanta.scim.entity;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.wowsanta.scim.SCIM_EXT_SCHEMA_TYPES;
import com.wowsanta.scim.SCIM_REPOSITORY_TYPES;
import com.wowsanta.scim.annotation.SCIM_ENTITY;

import lombok.Data;

@Entity
@Table(name = "WS_SCIM_PASSWORD")
@SCIM_ENTITY(
		schema 		= SCIM_EXT_SCHEMA_TYPES.SCIM_PASSWORD_SCHEMA,
		name		= SCIM_EXT_SCHEMA_TYPES.SCIM_PASSWORD_SCHEMA,
		repository  = SCIM_REPOSITORY_TYPES.SCIM_PASSWORD_REPOSITORY)
@Data
public class ScimPassword implements ScimResource {
	
	@Id
	@Column(name = "Id", columnDefinition = "VARCHAR(64)")
	protected String id;// external id
	
	@ManyToOne
	@JoinColumn(name = "userId")
	@MapsId("userId")
	private ScimUser user;

	
	@Column(name = "password", columnDefinition = "VARCHAR(128)", nullable=false)
	protected String password;

}
