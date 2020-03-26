package com.wowsanta.scim.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.wowsanta.scim.SCIM_EXT_SCHEMA_TYPES;
import com.wowsanta.scim.annotation.SCIM_ENTITY;
import com.wowsanta.scim.type.RestfulServiceType;

import lombok.Data;

@Entity
@Table(name = "WS_RESOURCE_HISTORY")
@SCIM_ENTITY(
		schema 		= SCIM_EXT_SCHEMA_TYPES.RESOURCE_HISTORY_SCHEMA_URI,
		name		= SCIM_EXT_SCHEMA_TYPES.RESOURCE_HISTORY_SCHEMA,
		restful= {RestfulServiceType.READ})
@Data
public class ResourceHistory implements ScimResource {
	
	@Id
	@GeneratedValue(generator="UUID")
	@GenericGenerator(name="UUID", strategy="com.wowsanta.repository.hibernate.UUIDGenerator")
	@Column(name = "id",columnDefinition = "VARCHAR(64)")
	private String id;
	
	
	
	@Column(name = "resId", updatable = false, nullable = false, columnDefinition = "VARCHAR(64)")
	private String resId;
	
	@Column(name = "resType", updatable = false, nullable = false, columnDefinition = "VARCHAR(64)")
	private String resType;
	
	@Column(name = "changed", columnDefinition = "VARCHAR(1024)")
	private String changed;
	
	@Column(name="writeTime")
	protected Date writeTime;
	

}
