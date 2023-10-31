package com.orbitalyards.landwar.jpa.model.ref;

import java.util.Objects;

import com.orbitalyards.landwar.jpa.model.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity(name = "userRole")
@Table(name = "USER_ROLE_REF")
public class Role extends BaseEntity<Role>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8861047465525228386L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "ROLE", unique = true, nullable = false, insertable = true, updatable = true)
	private String role = "";
	
	public Role() {}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, role);
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
		return Objects.equals(id, other.id) && Objects.equals(role, other.role);
	}
}
