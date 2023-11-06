package com.orbitalyards.landwar.mvc.view.app;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@CrossOrigin(value = "*")
public class Landing {
	
	@GetMapping(path = "/app/landing")
	public String userRegistration(Model model) {
		
		model.addAttribute("version", "LANDWAR-Net v0.0.1");
		
		return "landing";
	}
}
