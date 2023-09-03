package com.UserManagement.security;

public class AuthRequest {
	private String password;
	private String emailId;
	
	
	public AuthRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AuthRequest(String userName, String password) {
		super();
		this.password = password;
		this.emailId=emailId;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	
}
