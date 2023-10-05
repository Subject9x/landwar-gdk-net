package com.orbitalyards.landwar.mvc.controller;


import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.orbitalyards.landwar.mvc.model.data.UnitInfoEntry;

@RunWith(SpringRunner.class)
@WebMvcTest(UnitInfoController.class)
@EnableAutoConfiguration
public class UnitInfoControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
//	@Autowired
//	private UnitInfoEntryDTO unitInfoEntryDTO;
	

	
	@Test
	public void testUserLogin() throws Exception{
//		
//		UnitInfoEntry unitEntry = new UnitInfoEntry();
//		
//		unitEntry.setArmor(4);
//		unitEntry.setDesc("A TEST UNIT");
//		unitEntry.setDmgMelee(3);
//		unitEntry.setDmgRange(6);
//		unitEntry.setEvade(0);
//		unitEntry.setMove(6);
//		unitEntry.setPointsCost(34);
//		unitEntry.setRange(12);
//		unitEntry.setSize(4);
//		unitEntry.setUnitName("DORPY");
		
//		String json = unitInfoEntryDTO.toJsonFromModel(unitEntry);
		
//	      MockHttpServletRequestBuilder builder =
//	              MockMvcRequestBuilders.put("/unit/save")
//	                                    .contentType(MediaType.APPLICATION_JSON)
//	                                    .accept(MediaType.APPLICATION_JSON)
//	                                    .characterEncoding("UTF-8")
//	                                    .content(json);
//	      mockMvc.perform(builder)
//	                  .andExpect(MockMvcResultMatchers.status()
//	                                                  .isOk())
//	                  .andExpect(MockMvcResultMatchers.content()
//	                                                  .string("Article created."))
//	                  .andDo(MockMvcResultHandlers.print());
	}
}