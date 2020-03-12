package com.wowsanta.scim.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.wowsanta.scim.annotation.SCIM_ENTITY;
import com.wowsanta.scim.type.RestfulServiceType;

import lombok.Data;

@Entity
@Table(name = "WS_POLICY_GROUP")
@SCIM_ENTITY(name="Policy_Group", restful= {RestfulServiceType.READ})
@Data
public class PolicyGroup {
	
	@Id
	@Column(name = "id", columnDefinition = "VARCHAR(64)")
	private String id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "policyId")
	private ScimPolicy policy;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "groupId")
	private ScimGroup group;
}
