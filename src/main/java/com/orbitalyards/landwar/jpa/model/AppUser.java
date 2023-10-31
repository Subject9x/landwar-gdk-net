package com.orbitalyards.landwar.jpa.model;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.orbitalyards.landwar.jpa.model.ref.Role;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/***
 * WARN: I am not interested in storing PII  for this application, all users need to save is their username and hashcode.
 * The scope of this application is around editing / creating game stats for an open-source table top game that is FREE.
 * 
 * @author roohr
 */
@Entity
@Table(name = "APP_USER")
public class AppUser extends BaseEntity<AppUser>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6577760273796675212L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="USERNAME", nullable=false, updatable = true, insertable = true, length = 32)
	private String userName = "";
	
	@Column(name="PASSCODE", nullable=false, updatable = true, insertable = true, length = 64)
	private String passCode = "";
	
	@Column(name="LOGGED_IN", nullable=false, updatable = true, insertable = true)
	private boolean logIn = false;
	
	@Column(name = "LOG_IN_TIME", nullable=true, updatable = true, insertable = true)
	private Timestamp logInTime = new Timestamp(0);
	
	@OneToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH}, orphanRemoval = true, mappedBy = "appUser", fetch = FetchType.LAZY)
	private Set<UnitInfo> units = new HashSet<UnitInfo>();
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "appUser", fetch = FetchType.LAZY)
	private Set<ArmyList> armyLists = new HashSet<ArmyList>();
	
	@ManyToMany(cascade = {CascadeType.REFRESH}, mappedBy = "")
	private Set<Role> roles = new HashSet<Role>();

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

	public String getPassCode() {
		return passCode;
	}

	public void setPasscode(String passCode) {
		this.passCode = passCode;
	}
	
	public boolean getLogIn() {
		return logIn;
	}

	public void setLogIn(boolean logIn) {
		this.logIn = logIn;
	}

	public Timestamp getLogInTime() {
		return logInTime;
	}

	public void setLogInTime(Timestamp logInTime) {
		this.logInTime = logInTime;
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
		return getClass().hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AppUser other = (AppUser) obj;
		return Objects.equals(armyLists, other.armyLists) && Objects.equals(id, other.id) && logIn == other.logIn
				&& Objects.equals(logInTime, other.logInTime) && Objects.equals(passCode, other.passCode)
				&& Objects.equals(roles, other.roles) 
				&& Objects.equals(units, other.units)
				&& Objects.equals(userName, other.userName);
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", passCode=" + passCode + ", logIn=" + logIn
				+ ", logInTime=" + logInTime 
				+ ", units=" + units 
				+ ", armyLists=" + armyLists + ", roles=" + roles
				+ "]";
	}
}
