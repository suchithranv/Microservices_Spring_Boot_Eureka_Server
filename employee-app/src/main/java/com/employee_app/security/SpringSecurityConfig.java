package com.employee_app.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {	
		//any request needs authentication
		http.authorizeHttpRequests(auth -> auth.anyRequest().authenticated());
		
		//it does not create user session in the server
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		//a login form pop up comes instead of a web page
		http.httpBasic(Customizer.withDefaults()); 
		
		//since it is stateless, this protection is not required
		http.csrf().disable();
		
		//Frames should be allowed --> so disable X-Frame-Options header
		http.headers().frameOptions().disable();
		
		return http.build();				
	}	

}



//http.authorizeHttpRequests(auth -> 
//auth
//  .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll() // allow all the preflight request
//  .anyRequest()
//  .authenticated()