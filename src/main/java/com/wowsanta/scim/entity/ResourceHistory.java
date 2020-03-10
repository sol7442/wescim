package com.wowsanta.scim.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.wowsanta.scim.annotation.ENTITY;

import lombok.Data;

@Entity
@Table(name = "WS_RESOURCE_HISTORY")
@ENTITY(name="Resource_History")
@Data
public class ResourceHistory implements Resource {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	private int id;
	
	
	@Column(name = "resId", updatable = false, nullable = false, columnDefinition = "VARCHAR(64)")
	private String resId;
	
	@Column(name = "resType", updatable = false, nullable = false, columnDefinition = "VARCHAR(64)")
	private String resType;
	
	@Column(name = "changed", columnDefinition = "VARCHAR(1024)")
	private String changed;
	
	@Column(name="writeTime")
	protected Date writeTime;
	

}
