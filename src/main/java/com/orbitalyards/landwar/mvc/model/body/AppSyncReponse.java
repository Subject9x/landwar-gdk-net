package com.orbitalyards.landwar.mvc.model.body;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.orbitalyards.landwar.mvc.model.data.UnitInfoEntry;
import com.orbitalyards.landwar.mvc.model.data.UserModel;

@JsonRootName("response")
public class AppSyncReponse extends AppResponse {


	@JsonProperty(defaultValue = "[]")
	private List<UnitInfoEntry> update = new ArrayList<UnitInfoEntry>();
	
	@JsonProperty(defaultValue = "[]")
	private List<Long> delete = new ArrayList<Long>();
	
	@JsonProperty(defaultValue = "[]")
	private List<UnitInfoEntry> create = new ArrayList<UnitInfoEntry>();
	
	@JsonProperty
	private UserModel user;
	
	public AppSyncReponse() {}
	
	@JsonIgnore
	private AppSyncReponse(Builder b) {
		setCreate(b.getCreate());
		setDelete(b.getDelete());
		setUpdate(b.getUpdate());
		setMsg(b.getMsg());
		setStatusCode(b.getStatusCode());
		setHttpStatus(b.getHttpStatus());
		setUser(b.getUser());
	}

	public static class Builder{
		
		private List<UnitInfoEntry> update = new ArrayList<UnitInfoEntry>();
		private List<Long> delete = new ArrayList<Long>();
		private List<UnitInfoEntry> create = new ArrayList<UnitInfoEntry>();
		private String msg;
		private int statusCode = 0;
		private UserModel user = null;
		private HttpStatus httpStatus;
		
		public Builder() {}
		
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

		public Builder setCreate(List<UnitInfoEntry> create) {
			this.create = create;
			return this;
		}

		public Builder setDelete(List<Long> delete) {
			this.delete = delete;
			return this;
		}

		public Builder setUpdate(List<UnitInfoEntry> update) {
			this.update = update;
			return this;
		}
		
		public AppSyncReponse build(){
			return new AppSyncReponse(this);
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
		
		public List<UnitInfoEntry> getUpdate() {
			return update;
		}

		public List<Long> getDelete() {
			return delete;
		}

		public List<UnitInfoEntry> getCreate() {
			return create;
		}
	}
	
	public List<UnitInfoEntry> getUpdate() {
		return update;
	}

	public void setUpdate(List<UnitInfoEntry> update) {
		this.update = update;
	}

	public List<Long> getDelete() {
		return delete;
	}

	public void setDelete(List<Long> delete) {
		this.delete = delete;
	}

	public List<UnitInfoEntry> getCreate() {
		return create;
	}

	public void setCreate(List<UnitInfoEntry> create) {
		this.create = create;
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
	
	
}
