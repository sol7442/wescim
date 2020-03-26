package com.wowsanta.scim.entity;


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
@Table(name = "WS_SCIM_JOB")
@SCIM_ENTITY(
		schema 		= SCIM_EXT_SCHEMA_TYPES.SCIM_JOB_SCHEMA_URI,
		name		= SCIM_EXT_SCHEMA_TYPES.SCIM_JOB_SCHEMA,
		repository  = SCIM_REPOSITORY_TYPES.SCIM_JOB_REPOSITORY)
@Data
@EqualsAndHashCode(callSuper = true)
public class ScimJob extends ScimEntity{
	@Embedded
	private ScimMeta meta;
	
}
