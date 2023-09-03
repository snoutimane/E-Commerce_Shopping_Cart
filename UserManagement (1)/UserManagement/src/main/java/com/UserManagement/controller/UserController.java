package com.UserManagement.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.UserManagement.exception.InvalidUserException;
import com.UserManagement.model.User;
import com.UserManagement.service.JwtService;
import com.UserManagement.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService serv;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authmanager;

    @GetMapping("/getallusers")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<User> getAllUsers() throws InvalidUserException {
        return serv.getAllUsers();
    }

    @PostMapping("/addUser")
    @CrossOrigin(origins = "http://localhost:4200")
    public User addUser(@RequestBody User user) throws InvalidUserException {
        return serv.addUser(user);
    }

    @GetMapping("/viewByProfileId/{profileId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public User viewByProfileId(@PathVariable int profileId) throws InvalidUserException {
        return serv.viewByProfileId(profileId);
    }

    @GetMapping("/viewByEmailId/{emailId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public User viewByEmailId(@PathVariable String emailId) throws InvalidUserException {
        return serv.viewByEmailId(emailId);
    }

    @DeleteMapping("/deleteUser/{profileId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String deleteUser(@PathVariable int profileId) throws InvalidUserException {
        return serv.deleteUser(profileId);
    }
    @GetMapping("/role/{emailId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String getUserRole(@PathVariable("emailId") String emailId) {
        return serv.getUserRole(emailId);
    }
    
    @PostMapping("/login")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Map<String, String>> loginUser(@RequestBody User user) {
        String emailId = user.getEmailId();
        String password = user.getPassword();

        try {
            // Attempt authentication
            Authentication authentication = authmanager.authenticate(new UsernamePasswordAuthenticationToken(emailId, password));

            // Authentication successful
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String token = jwtService.generateToken(emailId); // Pass UserDetails to include authority information

            // Return the JWT token in the response
            Map<String, String> response = new HashMap<>();
            response.put("token", token);
            return ResponseEntity.ok().body(response);
        } catch (AuthenticationException e) {
            // Authentication failed, handle the exception
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }


}
