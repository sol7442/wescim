package com.wowsanta.scim.entity;


import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ForeignKey;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.wowsanta.scim.SCIM_EXT_SCHEMA_TYPES;
import com.wowsanta.scim.SCIM_REPOSITORY_TYPES;
import com.wowsanta.scim.annotation.SCIM_ENTITY;
import com.wowsanta.entity.ScimEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Entity
@Table(name = "WS_SCIM_ORG")
@SCIM_ENTITY(
		schema 		= SCIM_EXT_SCHEMA_TYPES.SCIM_ORG_SCHEMA,
		name		= SCIM_EXT_SCHEMA_TYPES.SCIM_ORG_SCHEMA,
		repository  = SCIM_REPOSITORY_TYPES.SCIM_ORG_REPOSITORY)
@Data
@EqualsAndHashCode(callSuper = true)
public class ScimOrg extends ScimEntity{
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent", foreignKey = @ForeignKey(name="FK_ORG_PARENT"))
	private ScimOrg parent;
	
	@Embedded
	private ScimMeta meta;
	
	public ScimOrg() {}
	public ScimOrg(String id) {
		setId(id);
	}
}
