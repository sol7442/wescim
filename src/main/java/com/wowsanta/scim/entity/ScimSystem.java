package com.wowsanta.scim.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.wowsanta.scim.SCIM_EXT_SCHEMA_TYPES;
import com.wowsanta.scim.SCIM_REPOSITORY_TYPES;
import com.wowsanta.scim.annotation.SCIM_ENTITY;

import lombok.Data;

@Data
@Entity
@Table(name = "WS_SCIM_SYSTEM")
@SCIM_ENTITY(
		name		= SCIM_EXT_SCHEMA_TYPES.SCIM_SYSTEM_SCHEMA,
		schema 		= SCIM_EXT_SCHEMA_TYPES.SCIM_SYSTEM_SCHEMA_URI,
		repository  = SCIM_REPOSITORY_TYPES.SCIM_SYSTEM_REPOSITORY)
public class ScimSystem implements ScimResource {
	
	public enum Type{
		PROVIDER,SOUREC,TARGET
	}
	
	@Id
	@Column(name = "id", columnDefinition = "VARCHAR(64)")
	protected String id;
	
	@Column(name = "name", columnDefinition = "VARCHAR(64)")
	protected String name;
	
	@Column(name = "type")
	@Enumerated(EnumType.STRING)
	protected Type type;
	
	@ManyToMany(fetch=FetchType.EAGER, cascade= {CascadeType.MERGE,CascadeType.PERSIST})
    @JoinTable(
    		name="WS_SYSTEM_POLICY",
    		joinColumns= {@JoinColumn(name="systemId")},
    		inverseJoinColumns= { @JoinColumn(name="policyId")}
    		)		
    private Set<ScimPolicy> policies = new HashSet<ScimPolicy>();
	
	@OneToMany(mappedBy = "system", fetch = FetchType.LAZY, cascade= {CascadeType.ALL})
	private Set<WorkScheduler> schdeulers = new HashSet<WorkScheduler>();
}
