package com.orbitalyards.landwar.service;

import java.util.List;

import com.orbitalyards.landwar.mvc.model.data.UserModel;
import com.orbitalyards.landwar.service.exception.UserServiceException;

public interface AppUserService {
	
	public UserModel registerUser(String userName, String userCode) throws Exception, UserServiceException ;
	
	public boolean loginUser(String userName, String userCode) throws Exception, UserServiceException;
	
	public boolean logoutUser(String userName, String userCode) throws Exception, UserServiceException;
	
	public void deleteUser(String userName, String userCode) throws Exception, UserServiceException ;
	
	public void adminDeleteUser(String userName, String adminUser, String adminCode) throws Exception, UserServiceException;
	
	public String updateUser(UserModel userModel) throws Exception, UserServiceException ;
	
	public void updateUserRole( String userName, String userCode, List<String> roles) throws Exception, UserServiceException;
	
}
