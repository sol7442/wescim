package com.wowsanta.scim.entity;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.wowsanta.scim.annotation.ENTITY;
import com.wowsanta.scim.type.SCIM_ENTITY;

import lombok.Data;

@Entity
@Table(name = "WS_SCIM_USER")
@ENTITY(name=SCIM_ENTITY.USER)
@Data
public class ScimUser extends ScimResource {
	@Embedded
	private ScimMeta meta;
}
