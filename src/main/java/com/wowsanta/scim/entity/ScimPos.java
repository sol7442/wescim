package com.wowsanta.scim.entity;


import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.wowsanta.scim.annotation.SCIM_ENTITY;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Entity
@Table(name = "WS_SCIM_POS")
@SCIM_ENTITY(name="Scim_Pos")
@Data
@EqualsAndHashCode(callSuper = true)
public class ScimPos extends ScimResource{
	@Embedded
	private ScimMeta meta;
	
	@Column(name = "rank")
	private int rank;
}
