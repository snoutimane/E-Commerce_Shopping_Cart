package com.UserManagement.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.UserManagement.dao.UserDao;
import com.UserManagement.exception.InvalidUserException;
import com.UserManagement.model.User;

@Service
public class UserService {
	
	@Autowired
	UserDao dao;
	
	public List<User> getAllUsers() throws InvalidUserException {
		return dao.getAllUsers();
	}
	
	public User addUser(User user) throws InvalidUserException{
		return dao.addUser(user);
	}
	
	public User viewByProfileId(int profileId) throws InvalidUserException{
		return dao.viewByProfileId(profileId);
	}
	
	public User viewByEmailId(String emailId) throws InvalidUserException{
		return dao.viewByEmailId(emailId);
	}
	
	public User ViewUserByEmailIdAndPassword(String emailId, String password) throws InvalidUserException {
		return dao.viewUserByEmailIdAndPassword(emailId, password);
	}
	
	public String deleteUser(int userId) throws InvalidUserException {
		return dao.deleteUser(userId);	
	}

	public String getUserRole(String emailId) {
        try {
            User user = dao.viewByEmailId(emailId);
            return user.getRole();
        } catch (InvalidUserException e) {
            // Handle the exception as per your application's requirements
            // For example, return a default role or throw a custom exception
            return "Unknown";
        }
    }
}
