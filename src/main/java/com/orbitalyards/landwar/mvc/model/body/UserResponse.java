package com.orbitalyards.landwar.mvc.model.body;

import java.util.Objects;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.orbitalyards.landwar.mvc.model.data.UserModel;
import com.orbitalyards.landwar.service.AppUserService;
import com.orbitalyards.landwar.service.exception.UserServiceException;

/**
 * Wrapper object for getting information out of {@linkplain AppUserService} implementations.
 * Is JSON serializable with annotations.
 * @author roohr
 */
@JsonRootName(value = "response")
public final class UserResponse extends AppResponse {

	@JsonProperty(defaultValue = "")
	private UserModel user = null;
	
	@JsonIgnore
	private UserServiceException userException;
	
	public UserResponse() {}
	
	@JsonIgnore
	private UserResponse(Builder b) {
		setUser(b.getUser());
		setMsg(b.getMsg());
		setUserException(b.getUserException());
		setHttpStatus(b.getHttpStatus());
		setStatusCode(b.getStatusCode());
	}
	
	public UserModel getUser() {
		return user;
	}
	
	public void setUser(UserModel user) {
		this.user = user;
	}

	@Override
	@JsonGetter(value = "msg")
	public String getMsg() {
		return msg;
	}

	@Override
	@JsonSetter(value = "msg")
	public void setMsg(String msg) {
		this.msg = msg;
	}
	

	@Override
	@JsonGetter(value = "statusCode")
	public int getStatusCode() {
		return statusCode;
	}

	@Override
	@JsonSetter(value = "statusCode")
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
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
		private UserServiceException userException = null;
		private HttpStatus httpStatus = HttpStatus.OK;
		private int statusCode = 0;
		
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
		
		public Builder setUserException(UserServiceException userException) {
			this.userException = userException;
			return this;
		}

		public Builder setStatusCode(int statusCode) {
			this.statusCode = statusCode;
			return this;
		}

		public Builder setHttpStatus(HttpStatus httpStatus) {
			this.httpStatus = httpStatus;
			return this;
		}
		
		public UserModel getUser() {
			return user;
		}

		public String getMsg() {
			return msg;
		}

		public UserServiceException getUserException() {
			return userException;
		}

		public HttpStatus getHttpStatus() {
			return httpStatus;
		}
		
		public int getStatusCode() {
			return statusCode;
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(httpStatus, msg, statusCode, user, userException);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserResponse other = (UserResponse) obj;
		return httpStatus == other.httpStatus && Objects.equals(msg, other.msg) && statusCode == other.statusCode
				&& Objects.equals(user, other.user) && Objects.equals(userException, other.userException);
	}

	@Override
	public String toString() {
		return "UserResponse [user=" + user + ", msg=" + msg + ", statusCode=" + statusCode + ", userException="
				+ userException + ", httpStatus=" + httpStatus + "]";
	}
}
