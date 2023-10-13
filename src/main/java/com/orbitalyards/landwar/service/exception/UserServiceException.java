package com.orbitalyards.landwar.service.exception;

import com.orbitalyards.landwar.service.AppUserService;

/***
 * Decorated Exception with some useful enums around 
 * the {@linkplain AppUserService}
 * 
 * @author: roohr
 */
public class UserServiceException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3699492044555001225L;
	
	public static enum errors{
		
		EMPTY_USERNAME("userName was null or empty."),
		EMPTY_USERCODE("userCode was null or empty."),
		EXIST_USERNAME("userName already exists."),
		JPA_GENERAL("general problem processing request."),
		USER_BAD_CODE("usercode doesn't match."),
		USER_ROLE_MATCH("user does not have role permission.");
		
		
		private String msg;
		
		private errors(String msg) {
			this.msg = msg;
		}
		
		public String msg() {
			return this.msg;
		}
	}
	
	
	public UserServiceException() {}
	
	public UserServiceException(String exception) {
		super(exception);
	}
	
}
