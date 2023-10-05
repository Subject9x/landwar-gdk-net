package com.orbitalyards.landwar.mvc.model.data;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonSetter;

/***
 * DTO class for user lifecycle operations
 * @author roohr
 */
@JsonRootName(value = "userModel")
public class UserModel {

	@JsonProperty(defaultValue = "")
	private String userName;
	
	@JsonProperty(defaultValue = "")
	private String userCode;
	
	@JsonProperty(defaultValue = "false")
	private boolean loggedIn;
	
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

	@JsonGetter(value = "false")
	public boolean isLoggedIn() {
		return loggedIn;
	}

	@JsonSetter(value = "false")
	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}
}
