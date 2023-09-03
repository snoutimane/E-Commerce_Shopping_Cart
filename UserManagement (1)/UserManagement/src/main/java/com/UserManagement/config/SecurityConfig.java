package com.UserManagement.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.UserManagement.security.JwtAuthenticationFilter;
import com.UserManagement.service.UserInfoDetailsService;




@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig{
	
	@Autowired
	private JwtAuthenticationFilter filter;
	
	
	@Bean
	public UserDetailsService userDetailsService() {
		return new UserInfoDetailsService();
	}
	
	
	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        
		return http.csrf().disable()
	            .authorizeRequests()
	            .requestMatchers(request -> {
	                String servletPath = request.getServletPath();
	                return "/users/addUser".equals(servletPath)
	                        || "/users/login".equals(servletPath);
	            }).permitAll()
	            .requestMatchers(request -> {
	                String servletPath = request.getServletPath();
	                return "/users/getallusers".equals(servletPath)
	                        || request.getContextPath().matches("/users/viewByEmailId/.*")
	                        || request.getContextPath().matches("/users/viewByProfileId/.*")
	                        || request.getContextPath().matches("/users/deleteUser/.*")
	                        || request.getContextPath().matches("/cart/.*")
	                        || request.getContextPath().matches("/products/.*")
	                        || request.getContextPath().matches("/users/role/.*");
	            }).authenticated()
	            .and()
	            .sessionManagement()
	            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	            .and()
	            .authenticationProvider(authenticationProvider())
	            .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
	            .build();
    }
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	        
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService());
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}
	
	
}