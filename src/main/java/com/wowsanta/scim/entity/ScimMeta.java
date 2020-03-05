package com.wowsanta.scim.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class ScimMeta {
	@Column(name="createTime", nullable=false, updatable= false)
	protected Date created;

	@Column(name="modifyTime", nullable=false, updatable= true)
	protected Date lastModified;
	
	@Column(name="expireTime")
	protected Date expire;

	@Column(name="active")
	protected boolean active;
}
