package com.orbitalyards.landwar.mvc.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ArmyListController {

	@GetMapping(value = "army/", produces = "application/json")
	public String getArmyList() {
		return "";
	}
	
	@GetMapping(value = "army/lists/", produces = "application/json")
	public String getArmyListMulti() {
		
		return "";
	}
}
