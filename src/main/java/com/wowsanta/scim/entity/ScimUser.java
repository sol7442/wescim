package com.wowsanta.scim.entity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.wowsanta.scim.SCIM_EXT_SCHEMA_TYPES;
import com.wowsanta.scim.SCIM_REPOSITORY_TYPES;
import com.wowsanta.scim.annotation.SCIM_ENTITY;
import com.wowsanta.entity.ScimEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Table(name = "WS_SCIM_USER")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true, exclude = {"meta", "groups"} )
@SCIM_ENTITY(
		name		= SCIM_EXT_SCHEMA_TYPES.SCIM_USER_SCHEMA,
		schema 		= SCIM_EXT_SCHEMA_TYPES.SCIM_USER_SCHEMA_URI,
		repository	= SCIM_REPOSITORY_TYPES.SCIM_USER_REPOSITORY)
public class ScimUser extends ScimEntity {
	private String[] schema = {SCIM_EXT_SCHEMA_TYPES.SCIM_USER_SCHEMA_URI};
	
	@Embedded
	private ScimMeta meta;
	
	@Embedded
	private ScimAttribute attr;
	
	@ManyToMany(fetch=FetchType.LAZY, cascade= {CascadeType.MERGE,CascadeType.PERSIST})
    @JoinTable(
    		name="WS_GROUP_USER",
    		joinColumns= {@JoinColumn(name="userId")},
    		inverseJoinColumns= { @JoinColumn(name="groupId")}
    		)
    private Set<ScimGroup> groups = new HashSet<ScimGroup>();
}
