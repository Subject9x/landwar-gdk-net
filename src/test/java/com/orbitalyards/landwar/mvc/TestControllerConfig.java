package com.orbitalyards.landwar.mvc;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import com.orbitalyards.landwar.service.AppUserService;
import com.orbitalyards.landwar.service.impl.AppUserServiceImpl;

@TestConfiguration
public class TestControllerConfig {

	@Bean
	public AppUserService appUserService() {
		return new AppUserServiceImpl();
	}
}
