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
import org.springframework.web.bind.annotation.RequestParam;

import com.orbitalyards.landwar.mvc.controller.AppController;
import com.orbitalyards.landwar.mvc.model.body.AppResponse;
import com.orbitalyards.landwar.mvc.model.body.UnitResponse;
import com.orbitalyards.landwar.mvc.model.data.UserModel;
import com.orbitalyards.landwar.service.UnitInfoService;

@Controller()
@RequestMapping("/unit")
@CrossOrigin(value = "*")
public class UnitInfoController extends AppController{

	private static Logger logger = LoggerFactory.getLogger(UnitInfoController.class);
	
	@Autowired
	private UnitInfoService unitInfoService;
	
	@GetMapping(path = "/fetch", produces = "application/json")
	public ResponseEntity<AppResponse> retrieveUnitInfo(@RequestBody UserModel user, @RequestParam int count, @RequestParam String sortBy) throws Exception{
		
		AppResponse response = validateUserInput(user.getUserName(), user.getUserCode());
		
		if(response == null) {
			response = unitInfoService.getUnitsByUser(user.getUserName(), user.getUserCode(), count, sortBy);	
		}
		
		return new ResponseEntity<AppResponse>(response, response.getHttpStatus());
	}
	
	@PostMapping(path = "/create", produces = "application/json", consumes = "application/json")
	public ResponseEntity<AppResponse> createUnits(@RequestBody UnitResponse unitSubmit) throws Exception{
		
		AppResponse response = unitInfoService.createUnits(unitSubmit);
		
		return new ResponseEntity<AppResponse>(response, response.getHttpStatus());
	}
	
	@PatchMapping(path = "/update", produces = "application/json")
	public ResponseEntity<AppResponse> updateUnitInfo(@RequestBody UnitResponse unitUpload) throws Exception{
		
		AppResponse response = unitInfoService.updateUnit(unitUpload);
		
		return new ResponseEntity<AppResponse>(response, response.getHttpStatus());
	}
	
	@PatchMapping(path = "/delete", produces = "application/json")
	public ResponseEntity<AppResponse> deleteUnitInfo(@RequestBody UnitResponse unitUpload) throws Exception{
		
		AppResponse response = unitInfoService.deleteUnits(unitUpload);
		
		return new ResponseEntity<AppResponse>(response, response.getHttpStatus());
	}
}
