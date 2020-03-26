package com.wowsanta.scim.entity;


import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.wowsanta.scim.SCIM_EXT_SCHEMA_TYPES;
import com.wowsanta.scim.SCIM_REPOSITORY_TYPES;
import com.wowsanta.scim.annotation.SCIM_ENTITY;

import lombok.Data;

@Data
@Entity
@Table(name = "WS_SYSTEM_PASSWORD")
@SCIM_ENTITY(
		name		= SCIM_EXT_SCHEMA_TYPES.SYSTEM_PASSWORD_SCHEMA,
		schema 		= SCIM_EXT_SCHEMA_TYPES.SYSTEM_PASSWORD_SCHEMA_URI,
		repository	= SCIM_REPOSITORY_TYPES.SYSTEM_PASSWORD_REPOSITORY)
public class SystemPassword implements ScimResource {
	
	@Embeddable
	@Data
	public static class PK implements Serializable {
		private static final long serialVersionUID = 1L;
		private String userId;
		private String systemId;
		public String toString() {
			return userId + "@" +systemId; 
		}
	}
	
	@EmbeddedId
	private PK pk;
	
	@MapsId("userId")
	@ManyToOne
	@JoinColumn(name = "userId")
	private ScimUser user;
	
	@MapsId("systemId")
	@ManyToOne
	@JoinColumn(name = "systemId")
	private ScimSystem system;
	
	@Embedded
	private PasswordMeta meta;

	@Override
	public String getId() {
		return pk.toString();
	}
	
}
