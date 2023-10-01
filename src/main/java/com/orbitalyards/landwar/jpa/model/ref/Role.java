package com.orbitalyards.landwar.jpa.model.ref;

import com.orbitalyards.landwar.jpa.model.BaseModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "USER_ROLE_REF")
public class Role extends BaseModel{
	
	@Id
	@Column(name = "ID", unique = true, nullable = false, insertable = true, updatable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "ROLE", unique = true, nullable = false, insertable = true, updatable = true)
	private String role;
	
	public Role() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	
}
