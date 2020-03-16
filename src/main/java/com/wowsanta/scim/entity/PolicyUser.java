package com.wowsanta.scim.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.wowsanta.scim.SCIM_EXT_SCHEMA_TYPES;
import com.wowsanta.scim.annotation.SCIM_ENTITY;
import com.wowsanta.scim.type.RestfulServiceType;

import lombok.Data;

@Entity
@Data
@Table(name = "WS_POLICY_USER")
@SCIM_ENTITY(
		schema 		= SCIM_EXT_SCHEMA_TYPES.POLICY_GROUP_SCHEMA_URI,
		name		= SCIM_EXT_SCHEMA_TYPES.SCIM_USER_SCHEMA,
		restful= {RestfulServiceType.READ})
public class PolicyUser {
	
	@Id
	@Column(name = "id", columnDefinition = "VARCHAR(64)")
	private String id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "policyId")
	private ScimPolicy policy;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "userId")
	private ScimUser user;
}
