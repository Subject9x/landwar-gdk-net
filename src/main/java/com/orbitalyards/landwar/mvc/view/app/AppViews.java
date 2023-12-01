package com.orbitalyards.landwar.mvc.view.app;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.annotation.PostConstruct;

@Controller
@CrossOrigin(value = "*")
public class AppViews {
	
	@Value( "${landwar.app.version}" )
	private String appVersion;
	
	private String appTitle;
	
	@PostConstruct
	public void postInit() {
		appTitle = "LANDWAR-Net v" + appVersion;
	}
	
	@GetMapping(path = "/app/landing", produces="application/html")
	public String viewHomePage(Model model) throws Exception{
		
		model.addAttribute("version", appTitle);
		return "home";
	}
	
	@GetMapping(path = "/app/bld/unit/view", produces = "application/html")
	public String viewUnitBuilder(Model model) throws Exception{
		
		model.addAttribute("version", appTitle);
		
		return "home";
	}
	
	
}
