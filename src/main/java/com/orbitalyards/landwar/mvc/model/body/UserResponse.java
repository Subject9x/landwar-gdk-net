package com.orbitalyards.landwar.mvc.model.body;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.orbitalyards.landwar.mvc.model.data.UserModel;
import com.orbitalyards.landwar.service.exception.UserServiceException;

/**
 * simple json object for generic controller responses.
 * @author roohr
 */
@JsonRootName(value = "userResp")
public class UserResponse {

	@JsonProperty(defaultValue = "")
	private UserModel user = null;
	
	@JsonProperty
	private String msg;
	
	@JsonProperty(defaultValue = "false")
	private boolean error = false;
	
	@JsonIgnore
	private UserServiceException userException;
	
	public UserResponse() {}
	
	@JsonIgnore
	private UserResponse(Builder b) {
		setUser(b.getUser());
		setMsg(b.getMsg());
		setError(b.getError());
		setUserException(b.getUserException());
	}
	

	public UserModel getUser() {
		return user;
	}

	public void setUser(UserModel user) {
		this.user = user;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public boolean getError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}

	public UserServiceException getUserException() {
		return userException;
	}

	public void setUserException(UserServiceException userException) {
		this.userException = userException;
	}
	
	
	public static class Builder{
		
		private UserModel user = null;
		private String msg = "";
		private boolean error = false;
		private UserServiceException userException = null;
		
		public Builder() {}
		
		public UserResponse build() {
			return new UserResponse(this);
		}
		
		public Builder setUser(UserModel user) {
			this.user = user;
			return this;
		}

		public Builder setMsg(String msg) {
			this.msg = msg;
			return this;
		}

		public Builder setError(boolean error) {
			this.error = error;
			return this;
		}
		
		public Builder setUserException(UserServiceException userException) {
			this.userException = userException;
			return this;
		}
		
		public UserModel getUser() {
			return user;
		}

		public String getMsg() {
			return msg;
		}

		public boolean getError() {
			return error;
		}

		public UserServiceException getUserException() {
			return userException;
		}
	}
}
