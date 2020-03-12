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

import com.wowsanta.scim.annotation.SCIM_ENTITY;
import com.wowsanta.scim.entity.Resource;

import lombok.Data;

@Entity
@Table(name = "WS_WORK_JOB")
@SCIM_ENTITY(name="Work_Job")
@Data
public class WorkJob implements Resource{
	@Id
	@Column(name = "id", columnDefinition = "VARCHAR(64)")
	private String id;
	
	@Column(name = "name", columnDefinition = "VARCHAR(64)")
	protected String name;
	
	@OneToMany(mappedBy = "job", fetch = FetchType.LAZY, cascade= {CascadeType.ALL})
	private Set<WorkScheduler> schdeulers = new HashSet<WorkScheduler>();
}
