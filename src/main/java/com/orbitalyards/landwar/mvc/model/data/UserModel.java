package com.orbitalyards.landwar.mvc.model.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

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
		
	public UserModel() {}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public boolean getLoggedIn() {
		return loggedIn;
	}
	
	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	@Override
	public String toString() {
		return "UserModel [userName=" + userName + ", userCode=" + userCode + ", loggedIn=" + loggedIn + "]";
	}
}
