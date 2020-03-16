package com.wowsanta.scim.entity.dapa.oldhr;


import javax.persistence.Embedded;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ForeignKey;
import javax.persistence.ManyToOne;

import com.wowsanta.scim.annotation.DOMAIN_ENTITY;
import com.wowsanta.scim.type.SCIM_SCHEMA_TYPES;

import lombok.Data;


@Data
@DOMAIN_ENTITY(name=SCIM_SCHEMA_TYPES.SCIM_ORG_SCHEMA,domain="dapa.oldhr.dev")
public class OldHrOrg extends OldHrResource{
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent", foreignKey = @ForeignKey(name="FK_ORG_PARENT"))
	private OldHrOrg parent;
	
	@Embedded
	private OldHrMeta meta;
}
