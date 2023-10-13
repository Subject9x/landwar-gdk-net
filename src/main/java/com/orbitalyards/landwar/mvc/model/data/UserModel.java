package com.orbitalyards.landwar.mvc.model.data;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonSetter;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

/***
 * DTO class for user lifecycle operations
 * @author roohr
 */
@JsonRootName(value = "userModel")
public class UserModel {

	@JsonProperty(defaultValue = "")
	@NotEmpty
	@Size(min = 6, message = "UserName must be minimum 6 characters.")
	private String userName;
	
	@JsonProperty(defaultValue = "")
	@NotEmpty
	@Size(min = 1, message = "UserCode was empty or null.")
	private String userCode;
	
	@JsonProperty(defaultValue = "false")
	private boolean loggedIn;
	
	@JsonProperty(defaultValue = "")
	private String error;
	
	public UserModel() {}
	
	public UserModel(String userName, String userCode, boolean loggedIn) {
		this.userName = userName;
		this.userCode = userCode;
		this.loggedIn = loggedIn;
	}

	@JsonGetter(value = "")
	public String getUserName() {
		return userName;
	}

	@JsonSetter(value = "")
	public void setUserName(String userName) {
		this.userName = userName;
	}

	@JsonGetter(value = "")
	public String getUserCode() {
		return userCode;
	}

	@JsonSetter(value = "")
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	@JsonGetter(value = "")
	public boolean getLoggedIn() {
		return loggedIn;
	}

	@JsonSetter(value = "")
	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	@JsonGetter(value = "")
	public String getError() {
		return error;
	}

	@JsonSetter(value = "")
	public void setError(String error) {
		this.error = error;
	}
}
