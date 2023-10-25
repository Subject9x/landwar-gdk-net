package com.orbitalyards.landwar.mvc.controller;

import org.springframework.http.HttpStatus;

import com.orbitalyards.landwar.mvc.model.body.AppResponse;
import com.orbitalyards.landwar.mvc.model.body.UserResponse;
import com.orbitalyards.landwar.mvc.model.data.UserModel;
import com.orbitalyards.landwar.service.exception.UserServiceException;

public class AppController {

	public AppResponse validateUserInput(String userName, String userCode){
		if(userName == null || userName.isEmpty() || userName.isBlank()) {
			UserModel err = new UserModel(userName);
			
			UserServiceException userError = new UserServiceException(UserServiceException.errors.EMPTY_USERNAME.msg());
			UserResponse resp = new UserResponse.Builder()
					.setHttpStatus(HttpStatus.BAD_REQUEST)
					.setStatusCode(HttpStatus.BAD_REQUEST.value())
					.setMsg(userError.getMessage())
					.setUserException(userError)
					.setUser(err)
					.build();
			return resp;
		}
		
		if(userCode == null || userCode.isEmpty() || userCode.isBlank()) {
			UserModel err = new UserModel(userName);
			UserServiceException userError = new UserServiceException(UserServiceException.errors.EMPTY_USERCODE.msg());
			UserResponse resp = new UserResponse.Builder()
					.setHttpStatus(HttpStatus.BAD_REQUEST)
					.setStatusCode(HttpStatus.BAD_REQUEST.value())
					.setMsg(userError.getMessage())
					.setUserException(userError)
					.setUser(err)
					.build();
			return resp;
		}
		//no new is good news.
		return null;
	}
	
}
