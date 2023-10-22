package com.orbitalyards.landwar.mvc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.orbitalyards.landwar.mvc.model.body.UserResponse;
import com.orbitalyards.landwar.mvc.model.data.UserModel;
import com.orbitalyards.landwar.service.impl.AppUserServiceImpl;

@Controller()
@RequestMapping("/user")
@CrossOrigin(value = "*")
public class UserController {

	private static Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private AppUserServiceImpl appUserService;
	
	@GetMapping(path = "/create", produces = "application/json")
	public ResponseEntity<UserResponse> userCreate(@RequestBody UserModel requestUser){
		
		logger.debug(requestUser.getUserName());
		
		UserResponse response = appUserService.registerUser(requestUser.getUserName(), requestUser.getUserCode());
		
		if(response.getError()) {
			return new ResponseEntity<UserResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<UserResponse>(response, HttpStatus.OK);
	}
	
	@PatchMapping(path = "/delete", produces="application/json")
	public ResponseEntity<UserResponse> userDelete(@RequestBody UserModel requestUser) throws Exception{
		logger.debug(requestUser.getUserName());

		UserResponse userResponse = appUserService.deleteUser(requestUser.getUserName(), requestUser.getUserCode());
		
		if(userResponse.getError()) {
			return new ResponseEntity<UserResponse>(userResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<UserResponse>(userResponse, HttpStatus.OK);
	}
	
	@PostMapping(path = "/login", produces = "application/json")
	public ResponseEntity<UserResponse> userLogin(@RequestBody UserModel requestUser) throws Exception{
		logger.debug(requestUser.getUserName());
		
		UserResponse userResponse = appUserService.loginUser(requestUser.getUserName(), requestUser.getUserCode());
		
		if(userResponse.getError()) {
			return new ResponseEntity<UserResponse>(userResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<UserResponse>(userResponse, HttpStatus.OK);
	}
	
	@PatchMapping(path = "/logout")
	public ResponseEntity<UserResponse> userLogout(@RequestBody UserModel requestUser) throws Exception{
		logger.debug(requestUser.getUserName());
		
		UserResponse userResponse = appUserService.logoutUser(requestUser.getUserName(), requestUser.getUserCode());
		
		if(userResponse.getError()) {

			return new ResponseEntity<UserResponse>(userResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<UserResponse>(userResponse, HttpStatus.OK);
	}
}
