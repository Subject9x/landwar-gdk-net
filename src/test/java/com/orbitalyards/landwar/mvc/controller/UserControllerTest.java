package com.orbitalyards.landwar.mvc.controller;


import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.orbitalyards.landwar.LandwarGdkNetApplication;
import com.orbitalyards.landwar.jpa.dao.JPATestRig;
import com.orbitalyards.landwar.jpa.repository.UnitInfoRepository;
import com.orbitalyards.landwar.jpa.repository.UserRepository;
import com.orbitalyards.landwar.jpa.repository.UserRoleRepository;
import com.orbitalyards.landwar.service.AppUserService;
import com.orbitalyards.landwar.service.impl.AppUserServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = LandwarGdkNetApplication.class)
public class UserControllerTest extends JPATestRig{
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private UserRepository userRepository;
	
	@MockBean
	private UserRoleRepository userRoleRepository;
	
	@MockBean
	private UnitInfoRepository unitInfoRepository;
	
	@Autowired
	private AppUserService svcAppUser;
	
	@Test
	public void testUserLogin() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.get(""))
			.andExpect(MockMvcResultMatchers.status().isOk())
		;
	}
	
	@Test
	public void testUserCreate() {

//	    mvc.perform(get("/api/employees")
//	      .contentType(MediaType.APPLICATION_JSON))
//	      .andExpect(status().isOk())
//	      .andExpect(jsonPath("$", hasSize(1)))
//	      .andExpect(jsonPath("$[0].name", is(alex.getName())));
	    
	    try {
			mockMvc.perform(MockMvcRequestBuilders.get("/user/create")
					.param("userName", "doogle")
					.param("userCode", "Passw0rd!"))
				.andExpect(MockMvcResultMatchers.status().isOk());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	@TestConfiguration
	static class TestAppUserServiceImpl{
		public AppUserService svcAppUser() {
			return new AppUserServiceImpl();
		}
	}
}
