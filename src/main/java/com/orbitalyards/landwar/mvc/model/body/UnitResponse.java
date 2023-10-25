package com.orbitalyards.landwar.mvc.model.body;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.orbitalyards.landwar.mvc.model.data.UnitInfoEntry;
import com.orbitalyards.landwar.mvc.model.data.UserModel;

@JsonRootName(value = "response")
public final class UnitResponse extends AppResponse {

	@JsonProperty
	private List<UnitInfoEntry> units = new ArrayList<UnitInfoEntry>();
	
	@JsonProperty
	private UserModel user;
	
	public UnitResponse() {}
	
	@JsonIgnore
	private UnitResponse(Builder b) {
		setUnits(b.getUnits());
		setMsg(b.getMsg());
		setStatusCode(b.getStatusCode());
		setHttpStatus(b.getHttpStatus());
		setUser(b.getUser());
	}

	public static class Builder{
		
		private List<UnitInfoEntry> units = new ArrayList<UnitInfoEntry>();
		private String msg;
		private int statusCode = 0;
		private UserModel user = null;
		private HttpStatus httpStatus;
		
		public Builder() {}
		
		public Builder setUnits(List<UnitInfoEntry> units) {
			this.units = units;
			return this;
		}
		public Builder setMsg(String msg) {
			this.msg = msg;
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

		public Builder setUser(UserModel user) {
			this.user = user;
			return this;
		}
		
		public UnitResponse build(){
			return new UnitResponse(this);
		}
		
		public List<UnitInfoEntry> getUnits() {
			return units;
		}

		public String getMsg() {
			return msg;
		}
		public int getStatusCode() {
			return statusCode;
		}
		public HttpStatus getHttpStatus() {
			return httpStatus;
		}

		public UserModel getUser() {
			return user;
		}
	}
	
	public List<UnitInfoEntry> getUnits() {
		return units;
	}

	public void setUnits(List<UnitInfoEntry> units) {
		this.units = units;
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
	
	
	@Override
	public int hashCode() {
		return Objects.hash(httpStatus, msg, statusCode, units, user);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UnitResponse other = (UnitResponse) obj;
		return httpStatus == other.httpStatus && Objects.equals(msg, other.msg) && statusCode == other.statusCode
				&& Objects.equals(units, other.units) && Objects.equals(user, other.user);
	}

	@Override
	public String toString() {
		return "UnitResponse [units=" + units + ", msg=" + msg + ", statusCode=" + statusCode + ", user=" + user
				+ ", httpStatus=" + httpStatus + "]";
	}
}
