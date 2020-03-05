package com.wowsanta.scim.entity.dapa.oldhr;

import javax.persistence.Embedded;

import com.wowsanta.scim.annotation.ENTITY;
import com.wowsanta.scim.type.SCIM_ENTITY;

import lombok.Data;

@ENTITY(name=SCIM_ENTITY.USER,domain="dapa.oldhr.dev")
@Data
public class OldHrUser extends OldHrResource {
	@Embedded
	private OldHrMeta meta;
}
