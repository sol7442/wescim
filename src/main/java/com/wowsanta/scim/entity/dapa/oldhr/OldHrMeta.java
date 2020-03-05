package com.wowsanta.scim.entity.dapa.oldhr;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class OldHrMeta {
	@Column(name="createTime")
	protected Date created;

	@Column(name="modifyTime")
	protected Date lastModified;
	
	@Column(name="expireTime")
	protected Date expire;

	@Column(name="active")
	protected boolean active;
}
