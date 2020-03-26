package com.wowsanta.scim.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.wowsanta.scim.SCIM_EXT_SCHEMA_TYPES;
import com.wowsanta.scim.SCIM_REPOSITORY_TYPES;
import com.wowsanta.scim.annotation.SCIM_ENTITY;
import com.wowsanta.scim.entity.ScimResource;

import lombok.Data;

@Entity
@Table(name = "WS_WORK_JOB")
@SCIM_ENTITY(
		name		= SCIM_EXT_SCHEMA_TYPES.WORK_JOB_SCHEMA,
		schema 		= SCIM_EXT_SCHEMA_TYPES.WORK_JOB_SCHEMA_URI,
		repository	= SCIM_REPOSITORY_TYPES.WORK_JOB_REPOSITORY)
@Data
public class WorkJob implements ScimResource{
	@Id
	@Column(name = "id", columnDefinition = "VARCHAR(64)")
	private String id;
	
	@Column(name = "name", columnDefinition = "VARCHAR(64)")
	protected String name;
	
	@OneToMany(mappedBy = "job", fetch = FetchType.LAZY, cascade= {CascadeType.ALL})
	private Set<WorkScheduler> schdeulers = new HashSet<WorkScheduler>();
}
