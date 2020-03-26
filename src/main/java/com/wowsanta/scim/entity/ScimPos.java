package com.wowsanta.scim.entity;


import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.wowsanta.scim.SCIM_EXT_SCHEMA_TYPES;
import com.wowsanta.scim.SCIM_REPOSITORY_TYPES;
import com.wowsanta.scim.annotation.SCIM_ENTITY;
import com.wowsanta.entity.ScimEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Entity
@Table(name = "WS_SCIM_POS")
@SCIM_ENTITY(
		schema 		= SCIM_EXT_SCHEMA_TYPES.SCIM_POS_SCHEMA,
		name		= SCIM_EXT_SCHEMA_TYPES.SCIM_POS_SCHEMA,
		repository  = SCIM_REPOSITORY_TYPES.SCIM_POS_REPOSITORY)
@Data
@EqualsAndHashCode(callSuper = true)
public class ScimPos extends ScimEntity{
	@Embedded
	private ScimMeta meta;
	
	@Column(name = "rank")
	private int rank;
}
