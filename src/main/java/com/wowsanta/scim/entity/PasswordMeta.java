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
public class PasswordMeta {
	public enum STATUS{
		READY,DOING,SUCCESS,FAIL
	}
	
	@Column(name="sycnTime")
	protected Date synced;

	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	protected STATUS status;
}
