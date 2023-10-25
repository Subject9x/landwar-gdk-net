package com.orbitalyards.landwar.mvc.controller.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.orbitalyards.landwar.mvc.controller.AppController;
import com.orbitalyards.landwar.mvc.model.body.AppResponse;
import com.orbitalyards.landwar.mvc.model.data.UserModel;
import com.orbitalyards.landwar.service.impl.AppUserServiceImpl;

@Controller()
@RequestMapping("/user")
@CrossOrigin(value = "*")
public class UserController extends AppController{

	private static Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private AppUserServiceImpl appUserService;
	
	@GetMapping(path = "/create", produces = "application/json")
	public ResponseEntity<AppResponse> userCreate(@RequestBody UserModel requestUser){
		
		logger.debug(requestUser.getUserName());
		
		AppResponse response = validateUserInput(requestUser.getUserName(), requestUser.getUserCode());
		
		if(response == null) {
			response = appUserService.registerUser(requestUser.getUserName(), requestUser.getUserCode());
		}
		
		return new ResponseEntity<AppResponse>(response, response.getHttpStatus());
	}
	
	@PatchMapping(path = "/delete", produces="application/json")
	public ResponseEntity<AppResponse> userDelete(@RequestBody UserModel requestUser) throws Exception{
		logger.debug(requestUser.getUserName());

		AppResponse response = validateUserInput(requestUser.getUserName(), requestUser.getUserCode());
		
		if(response == null) {
			response = appUserService.deleteUser(requestUser.getUserName(), requestUser.getUserCode());
		}

		return new ResponseEntity<AppResponse>(response, response.getHttpStatus());
	}
	
	@PostMapping(path = "/login", produces = "application/json")
	public ResponseEntity<AppResponse> userLogin(@RequestBody UserModel requestUser) throws Exception{
		logger.debug(requestUser.getUserName());
		
		AppResponse response = validateUserInput(requestUser.getUserName(), requestUser.getUserCode());
		
		if(response == null) {
			response = appUserService.loginUser(requestUser.getUserName(), requestUser.getUserCode());
		}
		
		return new ResponseEntity<AppResponse>(response, response.getHttpStatus());
	}
	
	@PatchMapping(path = "/logout")
	public ResponseEntity<AppResponse> userLogout(@RequestBody UserModel requestUser) throws Exception{
		logger.debug(requestUser.getUserName());
		
		AppResponse response = validateUserInput(requestUser.getUserName(), requestUser.getUserCode());
		
		if(response == null) {
			response = appUserService.logoutUser(requestUser.getUserName(), requestUser.getUserCode());
		}

		return new ResponseEntity<AppResponse>(response, response.getHttpStatus());
	}
}
