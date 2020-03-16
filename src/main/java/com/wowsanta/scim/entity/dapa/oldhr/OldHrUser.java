package com.wowsanta.scim.entity.dapa.oldhr;

import javax.persistence.Embedded;

import com.wowsanta.scim.annotation.DOMAIN_ENTITY;
import com.wowsanta.scim.type.SCIM_ENTITY;
import com.wowsanta.scim.type.SCIM_SCHEMA_TYPES;

import lombok.Data;

@Data
@DOMAIN_ENTITY(name=SCIM_SCHEMA_TYPES.SCIM_USER_SCHEMA,domain="dapa.oldhr.dev")
public class OldHrUser extends OldHrResource {
	@Embedded
	private OldHrMeta meta;
}
