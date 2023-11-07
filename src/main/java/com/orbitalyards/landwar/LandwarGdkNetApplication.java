package com.orbitalyards.landwar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class LandwarGdkNetApplication {

	public static void main(String[] args) {
		System.setProperty("spring.devtools.restart.enabled", "false");//Avoid Breakpoint at “throw new SilentExitException()” in Eclipse + Spring Boot
		SpringApplication.run(LandwarGdkNetApplication.class, args);
	}
}
