package com.wowsanta.scim.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.wowsanta.scim.annotation.ENTITY;
import com.wowsanta.scim.entity.Resource;

import lombok.Data;

@Entity
@Table(name = "WS_WORK_TASK")
@ENTITY(name="Work_Task")
@Data
public class WorkTask implements Resource{
	
	public enum Type{
		CREATE,UPDATE,DELETE
	}
	
	public enum STATUS{
		REGISTRY,WORKING,SUCCESS,FAILED
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	private int id;
	
	
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
