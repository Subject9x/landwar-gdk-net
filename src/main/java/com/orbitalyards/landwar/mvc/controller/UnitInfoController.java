package com.orbitalyards.landwar.mvc.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.orbitalyards.landwar.mvc.model.data.UnitInfoEntry;

@RestController
public class UnitInfoController {

//	@Autowired
//	private UnitInfoDaoService unitInfoDaoService;
	
	@GetMapping(value="/units/",  produces="application/json")
	public String getSingleUnit() {
		
		return "";
	}
	
	@GetMapping(value="/units/multi")
	public String getMultipleUnit() {
		
		return "";
	}
	
	@ResponseBody
	@PutMapping(value = "/unit/save", consumes = "application/json")
	public String saveUnit(@RequestBody UnitInfoEntry unitInfo) throws Exception {
		if(unitInfo == null) {
			throw new Exception();
		}
		
		return "hork";
	}
	
}
