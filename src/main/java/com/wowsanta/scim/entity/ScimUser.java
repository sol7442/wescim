package com.wowsanta.scim.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.wowsanta.scim.annotation.ENTITY;
import com.wowsanta.scim.type.SCIM_ENTITY;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Table(name = "WS_SCIM_USER")
@ENTITY(name="Scim_User")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true, exclude = {"meta", "groups"} )
public class ScimUser extends ScimResource {
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
