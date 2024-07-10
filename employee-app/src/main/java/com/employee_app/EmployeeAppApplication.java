package com.employee_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.reactive.config.EnableWebFlux;

//it will scan classes under same package and class with @FeignClient will be proxied
//it scans your feign clients and generate an implementation during the run time


@SpringBootApplication
@EnableFeignClients 
@EnableDiscoveryClient
public class EmployeeAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeAppApplication.class, args);
	}

}
