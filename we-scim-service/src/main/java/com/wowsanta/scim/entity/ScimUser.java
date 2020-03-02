package com.wowsanta.scim.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.wowsanta.scim.annotation.ENTITY;
import com.wowsanta.scim.type.SCIM_ENTITY;

@Entity
@Table(name = "WS_SCIM_USER")
@ENTITY(name=SCIM_ENTITY.USER)
public class ScimUser extends ScimResource {
	
}
