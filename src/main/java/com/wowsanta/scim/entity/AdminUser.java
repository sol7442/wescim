package com.wowsanta.scim.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.wowsanta.scim.annotation.ENTITY;
import com.wowsanta.scim.entity.Resource;

import lombok.Data;

@Entity
@Table(name = "WS_ADMIN_USER")
@ENTITY(name="ADMIN")
@Data
public class AdminUser implements Resource{
	@Id
	@Column(name = "id", columnDefinition = "VARCHAR(64)")
	private String id;
	
	@Column(name = "name", columnDefinition = "VARCHAR(64)")
	protected String name;
	
	@Column(name = "passwd", columnDefinition = "VARCHAR(64)")
	protected String passwd;
}
