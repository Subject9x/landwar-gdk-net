package com.orbitalyards.landwar.mvc.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(value = "*")
public class UserController {

	/*
	 * REST OPERATIONS
	 * 
	 * createNewUSer
	 *	-> UserModel from JSON
	 *	<- string - status / error msg
	 * 
	 * deleteUser
	 * 	-> UserModel.code from json
	 *  <- string - status / error msg
	 * 
	 * loginUser
	 *	-> UserModel from JSON
	 *  <- UserModel updated with loggedIn
	 * 
	 * logoutUser
	 * 	-> UserModel from JSON
	 *  <- UserModel updated with loggedIn
	 */
	
}
