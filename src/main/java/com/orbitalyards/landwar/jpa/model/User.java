package com.orbitalyards.landwar.jpa.model;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

/***
 * WARN: I am not interested in storing PII  for this application, all users need to save is their username and hashcode.
 * The scope of this application is around editing / creating game stats for an open-source table top game that is FREE.
 * 
 * @author roohr
 */
public class User extends BaseModel{

	@Id
	@Column(name="id", nullable = false, updatable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="username", nullable=false, updatable = true, insertable = true, length = 32)
	private String userName;
	
	@Column(name="userId", nullable=false, updatable = false, insertable = true, length = 64)
	private String userId;
	
	@ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private Set<UserRole> roles;
	
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
}
