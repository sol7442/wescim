package com.wowsanta.scim.entity;


import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.wowsanta.scim.annotation.SCIM_ENTITY;

import lombok.Data;

@Data
@Entity
@Table(name = "WS_SYSTEM_USER")
@SCIM_ENTITY(name="System_User")
public class SystemUser implements Resource {
	
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
