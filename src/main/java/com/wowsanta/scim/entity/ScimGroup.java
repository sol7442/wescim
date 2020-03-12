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


import com.wowsanta.scim.annotation.SCIM_ENTITY;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "WS_SCIM_GROUP")
@SCIM_ENTITY(name="Scim_Group")
@Data
@EqualsAndHashCode(callSuper = true)
public class ScimGroup extends ScimResource {
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
