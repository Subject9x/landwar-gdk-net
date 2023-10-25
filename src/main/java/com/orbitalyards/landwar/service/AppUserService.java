package com.orbitalyards.landwar.service;

import java.util.List;

import com.orbitalyards.landwar.mvc.model.body.AppResponse;

public interface AppUserService {
	
	public AppResponse registerUser(String userName, String userCode) ;
	
	public AppResponse loginUser(String userName, String userCode);
	
	public AppResponse logoutUser(String userName, String userCode);
	
	public AppResponse deleteUser(String userName, String userCode);
	
	public AppResponse adminDeleteUser(String userName, String adminUser, String adminCode);
	
	public AppResponse updateUser(String userName, String userCode);
	
	public AppResponse updateUserRole( String userName, String userCode, List<String> roles);
	
}
