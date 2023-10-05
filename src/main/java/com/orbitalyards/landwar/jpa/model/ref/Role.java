package com.orbitalyards.landwar.jpa.model.ref;

import java.util.Objects;

import com.orbitalyards.landwar.jpa.model.BaseModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity(name = "userRole")
@Table(name = "USER_ROLE_REF")
public class Role extends BaseModel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8861047465525228386L;
	@Column(name = "ROLE", unique = true, nullable = false, insertable = true, updatable = true)
	private String role;
	
	public Role() {}
	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId(), role);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Role other = (Role) obj;
		return Objects.equals(getId(), other.getId()) && Objects.equals(role, other.role);
	}
	
	
}
