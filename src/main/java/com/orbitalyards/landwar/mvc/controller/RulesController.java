package com.orbitalyards.landwar.mvc.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@CrossOrigin("*")
/***
 * Defines request routes for returning all rules PDF as their existing html files.
 * 
 * I am using thymeleaf views so that I can leverage link/href resolving via thymeleaf and its the fastest way
 * to simply print raw html to pdf. Script segment at the bottom shows this.
 * @author roohr
 */
public class RulesController {

	@Value( "${landwar.app.version}" )
	private String appVersion;
	
	@GetMapping(path = "/rules/pdf/core", produces = "application/html")
	public String getRulesCore(Model model) throws Exception{
		
		model.addAttribute("version", "LANDWAR-Net v" + appVersion);
		
		return "/fragments/pages/rules/rulebook_core";
	}
	
	@GetMapping(path = "/rules/pdf/quick", produces = "application/html")
	public String getRulesQuickplay(Model model) throws Exception{
		
		model.addAttribute("version", "LANDWAR-Net v" + appVersion);
		
		return "/fragments/pages/rules/rulebook_quickplay";
	}
	
	@GetMapping(path = "/rules/pdf/scenario", produces = "application/html")
	public String getRulesScenario(Model model) throws Exception{
		
		model.addAttribute("version", "LANDWAR-Net v" + appVersion);
		
		return "/fragments/pages/rules/rulebook_scenarios_basic";
	}
	
	@GetMapping(path = "/rules/pdf/unitBuild", produces = "application/html")
	public String getUnitBuildRules(Model model) throws Exception{
		
		model.addAttribute("version", "LANDWAR-Net v" + appVersion);
		
		return "/fragments/pages/rules/rulebook_core_unit_cost";
	}
}
