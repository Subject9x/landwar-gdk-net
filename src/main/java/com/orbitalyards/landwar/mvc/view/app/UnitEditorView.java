package com.orbitalyards.landwar.mvc.view.app;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.orbitalyards.landwar.mvc.model.UnitDataSubmit;
import com.orbitalyards.landwar.mvc.model.data.UnitInfoEntry;
import com.orbitalyards.landwar.mvc.model.dto.UnitInfoEntryDTO;

import jakarta.annotation.PostConstruct;

@Controller
@CrossOrigin(value = "*")
public class UnitEditorView {
	
	@Value( "${landwar.app.version}" )
	private String appVersion;
	
	private String appTitle;

	@Autowired
	private UnitInfoEntryDTO unitInfoEntryDTO;
	
	@PostConstruct
	public void postInit() {
		appTitle = "LANDWAR-Net v" + appVersion;
	}
	
	@GetMapping(path = "/app/unit/editor/view", produces="application/html")
	public String viewHomePage(Model model) throws Exception{
		
		model.addAttribute("version", appTitle);
		model.addAttribute("UnitDataSubmit", new UnitDataSubmit());
		
		return "fragments/pages/unitEdit/unitBuilderSheet";
	}
	
	@PostMapping(path = "/app/unit/editor/print/pdf", produces="application/html")
	public String printPDFUnits(@ModelAttribute UnitDataSubmit unitDataSubmit, Model model) throws Exception{
		
		model.addAttribute("version", appTitle);
		List<UnitInfoEntry> units = unitInfoEntryDTO.toModelFromJsonAsList(unitDataSubmit.getData());
		
		model.addAttribute("units", units);
		
		return "fragments/pages/unitCardGen/unitCardSheet";
	}
}
