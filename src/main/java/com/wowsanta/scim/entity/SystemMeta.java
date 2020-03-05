package com.wowsanta.scim.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Embeddable
public class SystemMeta extends ScimMeta {
	public enum STATUS{
		READY,DOING,SUCCESS,FAIL
	}
	
	@Column(name="provisionedTime")
	protected Date provisioned;

	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	protected STATUS status;
}
