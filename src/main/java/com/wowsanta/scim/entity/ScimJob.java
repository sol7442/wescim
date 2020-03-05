package com.wowsanta.scim.entity;


import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.wowsanta.scim.annotation.ENTITY;
import com.wowsanta.scim.type.SCIM_ENTITY;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Entity
@Table(name = "WS_SCIM_JOB")
@ENTITY(name=SCIM_ENTITY.JOB)
@Data
@EqualsAndHashCode(callSuper = true)
public class ScimJob extends ScimResource{
	@Embedded
	private ScimMeta meta;
	
}
