package com.customer_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class CustomerAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerAppApplication.class, args);
	}

}
