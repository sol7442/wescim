package com.wowsanta.scim.entity;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.wowsanta.scim.annotation.ENTITY;

import lombok.Data;

@Entity
@Table(name = "WS_SCIM_PASSWORD")
@ENTITY(name="PASSWORD")
@Data
public class ScimPassword implements Resource {
	
	@Id
	@Column(name = "userId", columnDefinition = "VARCHAR(64)")
	protected String userId;// external id
	
	@ManyToOne
	@JoinColumn(name = "userId")
	@MapsId("userId")
	private ScimUser user;

	
	@Column(name = "password", columnDefinition = "VARCHAR(128)", nullable=false)
	protected String password;
}
