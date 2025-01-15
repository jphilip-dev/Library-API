package com.phils.library.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.phils.library.service.MyUserService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

	@Autowired
	private MyUserService myUserService;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		return httpSecurity.authorizeHttpRequests(registry -> {
			registry.requestMatchers(HttpMethod.POST, "/security/register").anonymous();
			
			registry.requestMatchers("/security/**").hasRole("ADMIN");
			
			registry.requestMatchers(HttpMethod.GET, "/api/books", "/api/books/**").hasRole("USER");
			registry.requestMatchers(HttpMethod.POST, "/api/books").hasRole("ADMIN");
			registry.requestMatchers(HttpMethod.PUT, "/api/books/**").hasRole("ADMIN");
			registry.requestMatchers(HttpMethod.DELETE, "/api/books/**").hasRole("ADMIN");
			
			registry.requestMatchers(HttpMethod.GET, "/api/loans", "/api/loans/**").hasRole("USER");
			registry.requestMatchers(HttpMethod.POST, "/api/loans").hasRole("USER");
			
			//registry.requestMatchers(HttpMethod.PUT, "/api/loans/return/**").hasRole("ADMIN");
			registry.requestMatchers(HttpMethod.DELETE, "/api/loans/**").hasRole("ADMIN");
			
			registry.anyRequest().authenticated(); // any other endpoint user needs to be authenticated
			
		}).csrf(csrf -> csrf.disable())
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Set stateless
				.httpBasic(httpBasic -> {})// Enable Basic Auth
				.build();
	}

	@Bean
	public UserDetailsService userDetailsService() {
		return myUserService;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(myUserService);
		provider.setPasswordEncoder(passwordEncoder());
		return provider;
	}
}

/*
 * FOR REFERENCE
 * 
 * UserDetails normalUser = User.builder() .username("user")
 * .password("$2a$12$nXv3DbvvpyXryxpEAutS9.jbMREXeQu4CCjPZ5NTSe1U1CarvCB8O")
 * .roles("USER") .build();
 * 
 * UserDetails adminUser = User.builder() .username("admin")
 * .password("$2a$12$nXv3DbvvpyXryxpEAutS9.jbMREXeQu4CCjPZ5NTSe1U1CarvCB8O")
 * .roles("ADMIN","USER") .build(); return new
 * InMemoryUserDetailsManager(normalUser,adminUser);\
 */
