package com.orbitalyards.landwar.mvc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	public ResponseEntity<UserModel> userCreate(@RequestParam String userName, @RequestParam String userCode){
		
		logger.debug(userName);
		UserModel newUser = null;
		
		try {
			newUser = appUserService.registerUser(userName, userCode);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<UserModel>(newUser, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(newUser == null) {
			newUser = new UserModel();
			newUser.setError("problem creating account.");
			return new ResponseEntity<UserModel>(newUser, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	
		return new ResponseEntity<UserModel>(newUser, HttpStatus.OK);
	}
	
	@PatchMapping(path = "/delete")
	public String userDelete(@RequestParam String userName) throws Exception{
		logger.debug(userName);
		
		appUserService.deleteUser(userName);
		
		return "";
	}
	
	@PostMapping(path = "/login", produces = "application/json")
	public ResponseEntity<UserModel> userLogin(@RequestParam String userName, @RequestParam String usercode) throws Exception{
		logger.debug(userName);
		UserModel newUser = null;
		
		
		
		return null;
	}
	
	@PatchMapping(path = "/logout")
	public String userLogout() throws Exception{
		
		return "";
	}
}
