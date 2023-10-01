package com.orbitalyards.landwar.jpa.model.map;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity(name = "userRole")
@Table(name = "USER_ROLES")
public class UserRoleMap {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "ROLE_ID", unique = false, nullable = false, insertable = true, updatable = true)
	private Long roleId;
	
	public UserRoleMap() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, roleId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserRoleMap other = (UserRoleMap) obj;
		return Objects.equals(id, other.id) && Objects.equals(roleId, other.roleId);
	}
}
