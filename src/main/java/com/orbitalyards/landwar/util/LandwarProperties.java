package com.orbitalyards.landwar.util;

import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:landwar.properties")
@ConfigurationPropertiesScan
@ConfigurationProperties(prefix = "landwar")
public class LandwarProperties {

	private Map<String, String> app;

	public Map<String, String> getApp() {
		return app;
	}

	public void setApp(Map<String, String> app) {
		this.app = app;
	}
}
