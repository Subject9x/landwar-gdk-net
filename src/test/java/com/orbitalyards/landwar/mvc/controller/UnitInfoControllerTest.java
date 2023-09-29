package com.orbitalyards.landwar.mvc.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@WebMvcTest(UnitInfoController.class)
public class UnitInfoControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void testUserLogin() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.get(""))
			.andExpect(MockMvcResultMatchers.status().isOk())
		;
	}
}