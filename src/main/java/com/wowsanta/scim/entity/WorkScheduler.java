package com.wowsanta.scim.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.wowsanta.scim.SCIM_EXT_SCHEMA_TYPES;
import com.wowsanta.scim.SCIM_REPOSITORY_TYPES;
import com.wowsanta.scim.annotation.SCIM_ENTITY;
import com.wowsanta.scim.entity.SCIM_Resource;

import lombok.Data;

@Entity
@Table(name = "WS_WORK_SCHEDULER")
@SCIM_ENTITY(
		name		= SCIM_EXT_SCHEMA_TYPES.WORK_SCHEDULER_SCHEMA,
		schema 		= SCIM_EXT_SCHEMA_TYPES.WORK_SCHEDULER_SCHEMA_URI,
		repository	= SCIM_REPOSITORY_TYPES.WORK_SCHEDULER_REPOSITORY)
@Data
public class WorkScheduler implements SCIM_Resource{
	@Id
	@Column(name = "id", columnDefinition = "VARCHAR(64)")
	private String id;
	
	@Column(name = "name", columnDefinition = "VARCHAR(64)")
	protected String name;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "system")
	private ScimSystem system;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "job")
	private WorkJob job;
	
}
