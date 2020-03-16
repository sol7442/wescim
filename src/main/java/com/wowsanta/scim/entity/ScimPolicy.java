package com.wowsanta.scim.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.wowsanta.scim.SCIM_EXT_SCHEMA_TYPES;
import com.wowsanta.scim.SCIM_REPOSITORY_TYPES;
import com.wowsanta.scim.annotation.SCIM_ENTITY;
import com.wowsanta.scim.entity.SCIM_Resource;

import lombok.Data;

@Entity
@Table(name = "WS_SCIM_POLICY")
@SCIM_ENTITY(
		name		= SCIM_EXT_SCHEMA_TYPES.SCIM_POLICY_SCHEMA,	
		schema 		= SCIM_EXT_SCHEMA_TYPES.SCIM_POLICY_SCHEMA_URI,		
		repository  = SCIM_REPOSITORY_TYPES.SCIM_POLICY_REPOSITORY)
@Data
public class ScimPolicy implements SCIM_Resource{
	@Id
	@Column(name = "id", columnDefinition = "VARCHAR(64)")
	private String id;
	
	@Column(name = "name", columnDefinition = "VARCHAR(64)")
	protected String name;
	
	@ManyToMany(fetch=FetchType.EAGER, cascade= {CascadeType.MERGE,CascadeType.PERSIST})
    @JoinTable(
    		name="WS_SYSTEM_POLICY",
    		joinColumns= {@JoinColumn(name="policyId")},
    		inverseJoinColumns= { @JoinColumn(name="systemId")}
    		)
	private Set<ScimSystem> systems = new HashSet<ScimSystem>();
}
