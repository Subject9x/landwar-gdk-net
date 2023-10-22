package com.orbitalyards.landwar.service;

import java.util.List;

import com.orbitalyards.landwar.mvc.model.body.UserResponse;
import com.orbitalyards.landwar.mvc.model.data.UserModel;
import com.orbitalyards.landwar.service.exception.UserServiceException;

public interface AppUserService {
	
	public UserResponse registerUser(String userName, String userCode) ;
	
	public UserResponse loginUser(String userName, String userCode);
	
	public UserResponse logoutUser(String userName, String userCode);
	
	public UserResponse deleteUser(String userName, String userCode);
	
	public UserResponse adminDeleteUser(String userName, String adminUser, String adminCode);
	
	public String updateUser(UserModel userModel) throws Exception, UserServiceException ;
	
	public UserResponse updateUserRole( String userName, String userCode, List<String> roles);
	
}
