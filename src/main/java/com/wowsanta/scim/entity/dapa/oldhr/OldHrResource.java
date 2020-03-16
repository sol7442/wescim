package com.wowsanta.scim.entity.dapa.oldhr;


import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import com.wowsanta.scim.entity.SCIM_Resource;

import lombok.Data;

@Data
@MappedSuperclass
public abstract class OldHrResource implements SCIM_Resource {
	@Id
	@Column(name = "id", columnDefinition = "VARCHAR(64)")
	protected String id;
	
	@Column(name = "name", columnDefinition = "VARCHAR(64)")
	protected String name;
	
}
