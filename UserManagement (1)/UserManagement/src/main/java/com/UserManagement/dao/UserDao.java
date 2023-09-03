package com.UserManagement.dao;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.UserManagement.exception.InvalidUserException;
import com.UserManagement.model.User;
import com.UserManagement.repository.UserRepository;

@Component
public class UserDao {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<User> getAllUsers() throws InvalidUserException {
        // Check if the authenticated user is an admin
        if (!isAdminUser()) {
            throw new InvalidUserException("Access denied");
        }

        return userRepo.findAll();
    }

    public User addUser(User user) throws InvalidUserException {
        String emailId = user.getEmailId();
        User existingUser = userRepo.findByEmailId(emailId);
        if (existingUser != null) {
            throw new InvalidUserException("User with email ID: " + emailId + " already exists");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    public User viewByProfileId(int profileId) throws InvalidUserException {
        // Check if the authenticated user is an admin
        if (!isAdminUser()) {
            throw new InvalidUserException("Access denied");
        }

        User user = userRepo.findById(profileId).orElse(null);
        if (user == null) {
            throw new InvalidUserException("User not found with profile ID: " + profileId);
        }
        return user;
    }

    public User viewByEmailId(String emailId) throws InvalidUserException {
        // Check if the authenticated user is an admin
        if (!isAdminUser()) {
            throw new InvalidUserException("Access denied");
        }

        User user = userRepo.findByEmailId(emailId);
        if (user == null) {
            throw new InvalidUserException("User not found with email ID: " + emailId);
        }
        return user;
    }

    public User viewUserByEmailIdAndPassword(String emailId, String password) throws InvalidUserException {
        User user = userRepo.findByEmailIdAndPassword(emailId, password);
        if (user == null) {
            throw new InvalidUserException("Invalid email ID or password");
        }
        return user;
    }

    public String deleteUser(int userId) throws InvalidUserException {
        // Check if the authenticated user is an admin
        if (!isAdminUser()) {
            throw new InvalidUserException("Access denied");
        }

        User user = userRepo.findById(userId).orElse(null);
        if (user == null) {
            throw new InvalidUserException("User not found with ID: " + userId);
        }
        userRepo.deleteById(userId);
        return "The user is deleted successfully";
    }
    
    
    
    private boolean isAdminUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null && authentication.getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals("ADMIN"));
    }
}
