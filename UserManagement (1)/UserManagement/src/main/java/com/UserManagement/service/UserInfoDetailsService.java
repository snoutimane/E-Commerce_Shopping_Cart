package com.UserManagement.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.UserManagement.config.UserInfoDetails;
import com.UserManagement.model.User;
import com.UserManagement.repository.UserRepository;

import jakarta.transaction.Transactional;

@Component
public class UserInfoDetailsService implements UserDetailsService {
	
	@Autowired
	private UserRepository repository;
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String emailId) throws UsernameNotFoundException {
	    User user = repository.findByEmailId(emailId);
	    if (user == null) {
	        throw new UsernameNotFoundException("User NOT Found : " + emailId);
	    }
	    return new UserInfoDetails(user);
	}

}
