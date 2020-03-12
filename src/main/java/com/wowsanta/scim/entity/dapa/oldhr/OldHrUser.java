package com.wowsanta.scim.entity.dapa.oldhr;

import javax.persistence.Embedded;

import com.wowsanta.scim.annotation.DOMAIN_ENTITY;
import com.wowsanta.scim.type.SCIM_ENTITY;

import lombok.Data;

@Data
@DOMAIN_ENTITY(name="Scim_User",domain="dapa.oldhr.dev")
public class OldHrUser extends OldHrResource {
	@Embedded
	private OldHrMeta meta;
}
