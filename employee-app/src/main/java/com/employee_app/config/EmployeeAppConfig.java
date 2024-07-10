package com.employee_app.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmployeeAppConfig {
	
//	@Bean
//	public RestTemplate restTemplate() {
//		return new RestTemplate();
//	}

//    @Value("{addressservice.base.url}")
//    private String addressBaseUrl;
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	
//	@Bean
//	public WebClient webClient() {
//		return WebClient.builder().baseUrl(addressBaseUrl).build();
//	}
	
}




