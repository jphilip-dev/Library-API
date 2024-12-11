package com.training.restLibrary.security;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.training.restLibrary.utils.ResponseFormat;

import jakarta.servlet.http.HttpServletResponse;

@Configuration
public class BookSecurityConfig {
	
	@Bean
	public UserDetailsManager userDetailsManager(DataSource dataSource) {
		JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
		
		//query to retrieve user by userName
		jdbcUserDetailsManager.setUsersByUsernameQuery("select user_id, password, active from api_users where user_id=? and active=1");
		
		//query to retrieve roles by userName
		jdbcUserDetailsManager.setAuthoritiesByUsernameQuery("select user_id, role from api_roles where user_id=?");
		
		return jdbcUserDetailsManager;
	}
	
	// For Authorizing page access 
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http,ObjectMapper objectMapper) throws Exception {
		http.authorizeHttpRequests(configurer -> 
				configurer
				// landing end point or Info end point
				.requestMatchers(HttpMethod.GET, "/api").permitAll() 
				
				// Registration for unauthenticated user
				.requestMatchers(HttpMethod.POST, "/api/register").anonymous() 
				
				// Authenticated users can get all or specific book
				.requestMatchers(HttpMethod.GET, "/api/books").hasRole("USER")
				.requestMatchers(HttpMethod.GET, "/api/books/**").hasRole("USER")
				
				// Admin can create, update and delete Books
				.requestMatchers(HttpMethod.POST, "/api/books").hasRole("ADMIN")
				.requestMatchers(HttpMethod.PUT, "/api/books").hasRole("ADMIN")
				.requestMatchers(HttpMethod.PUT, "/api/books").hasRole("ADMIN")
				.requestMatchers(HttpMethod.DELETE, "/api/books/**").hasRole("ADMIN")
				
				// Admin can get all the user and update a user
				.requestMatchers(HttpMethod.GET, "/api/users").hasRole("ADMIN")
				.requestMatchers(HttpMethod.GET, "/api/users/**").hasRole("ADMIN")
				.requestMatchers(HttpMethod.PUT, "/api/users").hasRole("ADMIN")
				)
				.exceptionHandling(exceptionConfigurer -> 
		        exceptionConfigurer
		            .authenticationEntryPoint((request, response, authException) -> {
		                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		                response.setContentType("application/json");
		                
		                // Create the error response object
		                ResponseFormat responseFormat = new ResponseFormat(HttpStatus.UNAUTHORIZED.value(), "Authentication is required.",System.currentTimeMillis());
		                
		                // Write the JSON response
		                response.getWriter().write(objectMapper.writeValueAsString(responseFormat));
		            })
		            .accessDeniedHandler((request, response, accessDeniedException) -> {
	                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
	                    response.setContentType("application/json");
	                    
	                    // Create the error response object
	                    ResponseFormat responseFormat = new ResponseFormat(HttpStatus.FORBIDDEN.value(), "You do not have access to this resource.",System.currentTimeMillis());
			               
	                    // Write the JSON response
	                    response.getWriter().write(objectMapper.writeValueAsString(responseFormat));
	                })
						
					)
				;
		
		// for Basic auth
		http.httpBasic(Customizer.withDefaults());

		// disable CSRF Cross site Request Forgery
		// in general, not required for stateless REST APIs that uses POST, PUT, DELETE
		http.csrf(csrf -> csrf.disable());

		return http.build();
	}
}
