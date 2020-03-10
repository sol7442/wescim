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

import com.wowsanta.scim.annotation.ENTITY;
import com.wowsanta.scim.entity.Resource;

import lombok.Data;

@Entity
@Table(name = "WS_SCIM_POLICY")
@ENTITY(name="Scim_Policy")
@Data
public class ScimPolicy implements Resource{
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
