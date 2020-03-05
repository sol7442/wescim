package com.wowsanta.scim.entity.dapa.oldhr;


import javax.persistence.Embedded;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ForeignKey;
import javax.persistence.ManyToOne;

import com.wowsanta.scim.annotation.ENTITY;
import com.wowsanta.scim.type.SCIM_ENTITY;

import lombok.Data;


@ENTITY(name=SCIM_ENTITY.ORG,domain="dapa.oldhr.dev")
@Data
public class OldHrOrg extends OldHrResource{
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent", foreignKey = @ForeignKey(name="FK_ORG_PARENT"))
	private OldHrOrg parent;
	
	@Embedded
	private OldHrMeta meta;
}
