package com.wowsanta.scim.entity;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.wowsanta.scim.SCIM_EXT_SCHEMA_TYPES;
import com.wowsanta.scim.SCIM_REPOSITORY_TYPES;
import com.wowsanta.scim.annotation.SCIM_ENTITY;
import com.wowsanta.scim.entity.ScimResource;

import lombok.Data;

@Entity
@Table(name = "WS_WORK_TASK")
@SCIM_ENTITY(
		name		= SCIM_EXT_SCHEMA_TYPES.WORK_TASK_SCHEMA,
		schema 		= SCIM_EXT_SCHEMA_TYPES.WORK_TASK_SCHEMA_URI,
		repository	= SCIM_REPOSITORY_TYPES.WORK_TASK_REPOSITORY)
@Data
public class WorkTask implements ScimResource{
	
	public enum Type{
		CREATE,UPDATE,DELETE
	}
	
	public enum STATUS{
		REGISTRY,WORKING,SUCCESS,FAILED
	}
	
	@Id	
	@GeneratedValue(generator="UUID")
	@GenericGenerator(name="UUID", strategy="com.wowsanta.repository.hibernate.UUIDGenerator")
	@Column(name = "id",columnDefinition = "VARCHAR(64)")
	private String id;
	
	
	@Column(name = "resId")
	private String resId;
	
	@Column(name = "resType")
	private String resType;
	
	
	@Column(name = "workType")
	@Enumerated(EnumType.STRING)
	protected Type type;
	
	@Column(name = "status")
	@Enumerated(EnumType.ORDINAL)
	protected STATUS status;
}
