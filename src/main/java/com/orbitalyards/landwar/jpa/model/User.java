package com.orbitalyards.landwar.jpa.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.orbitalyards.landwar.jpa.model.ref.Role;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
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
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6577760273796675212L;

	@Column(name="USERNAME", nullable=false, updatable = true, insertable = true, length = 32)
	private String userName;
	
	@Column(name="USER_ID", nullable=false, updatable = false, insertable = true, length = 64)
	private String userId;
	
	@OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.REMOVE}, fetch = FetchType.LAZY, orphanRemoval = true)
	private Set<UnitInfo> units = new HashSet<UnitInfo>();
	
	@OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.REMOVE}, fetch = FetchType.LAZY, orphanRemoval = true)
	private Set<ArmyList> armyLists = new HashSet<ArmyList>();
	
	@ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.EAGER)
	private Set<Role> roles = new HashSet<Role>();
	
	public User() {}
	
	@PrePersist
	public void userPrePersist() {
		setUserId(String.valueOf(userHashCode()));
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
	
	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
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
	
	public int userHashCode() {
		int hash = 7;
		
		hash = 31 * hash + (userName == null ? 0 : userName.hashCode());
		hash = 31 * hash + (String.valueOf(System.currentTimeMillis()).hashCode());
		
		return hash;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(getId(), userId, userName, units, armyLists, roles);
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
		return Objects.equals(getId(), other.getId()) && Objects.equals(userId, other.userId)
				&& Objects.equals(userName, other.userName) && Objects.equals(units, other.units)
				&& Objects.equals(armyLists, other.armyLists) && Objects.equals(roles, other.roles);
	}

	@Override
	public String toString() {
		return "User [id=" + getId() + ", userName=" + userName + ", userId=" + userId + ", units=" + units + ", armyLists="
				+ armyLists + ", roles=" + roles + "]";
	}
}
