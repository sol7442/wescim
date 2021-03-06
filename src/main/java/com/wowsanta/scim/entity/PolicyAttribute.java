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
import com.wowsanta.scim.type.RestfulServiceType;

import lombok.Data;

@Entity
@Table(name = "WS_POLICY_ATTR")
@SCIM_ENTITY(
		schema 		= SCIM_EXT_SCHEMA_TYPES.POLICY_ATTR_SCHEMA_URI,
		name		= SCIM_EXT_SCHEMA_TYPES.POLICY_ATTR_SCHEMA,
		repository	= SCIM_REPOSITORY_TYPES.SCIM_USER_REPOSITORY,
		restful= {RestfulServiceType.READ})
@Data
public class PolicyAttribute {
	
	@Id
	@Column(name = "id", columnDefinition = "VARCHAR(64)")
	private String id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "policyId")
	private ScimPolicy policy;
	
	
	@Embedded
	private ScimAttributeOp attrOp;
}
