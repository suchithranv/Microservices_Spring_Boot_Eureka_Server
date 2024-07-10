package com.customer_app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.modelmapper.ModelMapper;

@Configuration
public class CustomerAppConfig {
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
