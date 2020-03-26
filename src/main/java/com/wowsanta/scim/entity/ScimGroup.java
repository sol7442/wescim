package com.wowsanta.scim.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.wowsanta.scim.SCIM_EXT_SCHEMA_TYPES;
import com.wowsanta.scim.SCIM_REPOSITORY_TYPES;
import com.wowsanta.scim.annotation.SCIM_ENTITY;
import com.wowsanta.scim.type.RestfulServiceType;
import com.wowsanta.entity.ScimEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "WS_SCIM_GROUP")
@Data
@EqualsAndHashCode(callSuper = true)

@SCIM_ENTITY(
		schema 		= SCIM_EXT_SCHEMA_TYPES.SCIM_GROUP_SCHEMA_URI,
		name		= SCIM_EXT_SCHEMA_TYPES.SCIM_GROUP_SCHEMA,
		repository  = SCIM_REPOSITORY_TYPES.SCIM_GROUP_REPOSITORY)
public class ScimGroup extends ScimEntity {
	@Embedded
	private ScimMeta meta;
	
    @ManyToMany(fetch=FetchType.EAGER, cascade= {CascadeType.MERGE,CascadeType.PERSIST})
    @JoinTable(
    		name="WS_GROUP_USER",
    		joinColumns= {@JoinColumn(name="groupId")},
    		inverseJoinColumns= { @JoinColumn(name="userId")}
    		)
    private Set<ScimUser> users = new HashSet<ScimUser>();
}
