package com.orbitalyards.landwar.mvc.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RulesController {

	@GetMapping(path = "/pdf/rules/core", produces = "application/pdf")
	public ResponseEntity<String> getRulesCore() throws Exception{
		
		
		return new ResponseEntity<String>("foob", HttpStatus.OK);
	}
	
}
