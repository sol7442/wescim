package com.wowsanta.scim.entity;


import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ForeignKey;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.wowsanta.scim.annotation.ENTITY;
import com.wowsanta.scim.type.SCIM_ENTITY;

import lombok.Data;


@Entity
@Table(name = "WS_SCIM_ORG")
@ENTITY(name=SCIM_ENTITY.ORG)
@Data
public class ScimOrg extends ScimResource{
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent", foreignKey = @ForeignKey(name="FK_ORG_PARENT"))
	private ScimOrg parent;
	
	@Embedded
	private ScimMeta meta;
}
