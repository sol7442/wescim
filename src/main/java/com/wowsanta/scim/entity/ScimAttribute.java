package com.wowsanta.scim.entity;


import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Embeddable
public class ScimAttribute {
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "org")
	private ScimOrg org;

	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "pos")
	private ScimPos pos;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "job")
	private ScimJob job;
	
	public ScimAttribute(ScimOrg org, ScimPos pos, ScimJob job) {
		this.org = org;
		this.pos = pos;
		this.job = job;
	}
}
