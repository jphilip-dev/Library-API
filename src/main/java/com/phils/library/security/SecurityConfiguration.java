package com.phils.library.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		return httpSecurity.authorizeHttpRequests(registry -> {
			registry.requestMatchers(HttpMethod.GET, "/api/books","/api/books/**").hasRole("USER");
			registry.requestMatchers(HttpMethod.POST, "/api/books").hasRole("ADMIN");
			registry.requestMatchers(HttpMethod.PUT, "/api/books/**").hasRole("ADMIN");
			registry.requestMatchers(HttpMethod.DELETE, "/api/books/**").hasRole("ADMIN");
			registry.anyRequest().authenticated(); // any other endpoint user needs to be authenticated
		})
		.csrf(csrf -> csrf.disable())
		.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Set stateless
		.httpBasic(httpBasic -> {})// Enable Basic Auth
		.build();
	}
	
	
	
	
	@Bean // in memory users (temporary)
	public UserDetailsService userDetailsService() {
		UserDetails normalUser = User.builder()
				.username("user")
				.password("$2a$12$nXv3DbvvpyXryxpEAutS9.jbMREXeQu4CCjPZ5NTSe1U1CarvCB8O")
				.roles("USER")
				.build();
		
		UserDetails adminUser = User.builder()
				.username("admin")
				.password("$2a$12$nXv3DbvvpyXryxpEAutS9.jbMREXeQu4CCjPZ5NTSe1U1CarvCB8O")
				.roles("ADMIN","USER")
				.build();
		return new InMemoryUserDetailsManager(normalUser,adminUser);
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
