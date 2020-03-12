package com.wowsanta.scim.entity.dapa.oldhr;


import javax.persistence.Embedded;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ForeignKey;
import javax.persistence.ManyToOne;

import com.wowsanta.scim.annotation.DOMAIN_ENTITY;

import lombok.Data;


@Data
@DOMAIN_ENTITY(name="Scim_Org",domain="dapa.oldhr.dev")
public class OldHrOrg extends OldHrResource{
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent", foreignKey = @ForeignKey(name="FK_ORG_PARENT"))
	private OldHrOrg parent;
	
	@Embedded
	private OldHrMeta meta;
}
