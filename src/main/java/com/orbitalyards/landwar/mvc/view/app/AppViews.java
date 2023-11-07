package com.orbitalyards.landwar.mvc.view.app;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@CrossOrigin(value = "*")
public class AppViews {
	
	@Value( "${landwar.app.version}" )
	private String appVersion;
	
	@GetMapping(path = "/app/landing", produces="application/html")
	public String userRegistration(Model model) {
		
		model.addAttribute("version", "LANDWAR-Net " + appVersion);
		
		return "home";
	}
	
	
}
