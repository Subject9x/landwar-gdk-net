package com.orbitalyards.landwar.jpa.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.orbitalyards.landwar.jpa.model.map.UserRoleMap;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/***
 * WARN: I am not interested in storing PII  for this application, all users need to save is their username and hashcode.
 * The scope of this application is around editing / creating game stats for an open-source table top game that is FREE.
 * 
 * @author roohr
 */
@Entity(name = "appUser")
@Table(name = "USERS")
public class User extends BaseModel{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="USERNAME", nullable=false, updatable = true, insertable = true, length = 32)
	private String userName;
	
	@Column(name="USER_ID", nullable=false, updatable = false, insertable = true, length = 64)
	private String userId;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	private Set<UnitInfo> units = new HashSet<UnitInfo>();
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	private Set<ArmyList> armyLists = new HashSet<ArmyList>();
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	private Set<UserRoleMap> roles = new HashSet<UserRoleMap>();
	
	public User() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public Set<UserRoleMap> getRoles() {
		return roles;
	}

	public void setRoles(Set<UserRoleMap> roles) {
		this.roles = roles;
	}

	public Set<UnitInfo> getUnits() {
		return units;
	}

	public void setUnits(Set<UnitInfo> units) {
		this.units = units;
	}

	public Set<ArmyList> getArmyLists() {
		return armyLists;
	}

	public void setArmyLists(Set<ArmyList> armyLists) {
		this.armyLists = armyLists;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id, userId, userName, units, armyLists, roles);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(id, other.id) && Objects.equals(userId, other.userId)
				&& Objects.equals(userName, other.userName) && Objects.equals(units, other.units)
				&& Objects.equals(armyLists, other.armyLists) && Objects.equals(roles, other.roles);
	}

	
}
