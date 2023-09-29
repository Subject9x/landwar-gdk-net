package com.orbitalyards.landwar.mvc.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UnitInfoController {

	//@Autowired
	
	
	@GetMapping(value="/units/",  produces="application/json")
	public String getSingleUnit() {
		
		return "";
	}
	
	@GetMapping(value="/units/multi")
	public String getMultipleUnit() {
		
		return "";
	}
	
	
}
