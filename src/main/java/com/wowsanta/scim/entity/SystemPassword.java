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

import com.wowsanta.scim.annotation.SCIM_ENTITY;

import lombok.Data;

@Data
@Entity
@Table(name = "WS_SYSTEM_PASSWORD")
@SCIM_ENTITY(name="System_Password")
public class SystemPassword implements Resource {
	
	@Embeddable
	@Data
	public static class PK implements Serializable {
		private static final long serialVersionUID = 1L;
		private String userId;
		private String systemId;
	}
	
	@EmbeddedId
	private PK id;
	
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
	
}
