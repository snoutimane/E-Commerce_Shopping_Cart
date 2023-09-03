package com.UserManagement.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.antlr.v4.runtime.misc.NotNull;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name="users")
public class User{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int profileId;
	@NotBlank(message="Name is empty")
	private String fullName;
	private String userName;
	
	@Column(name="emailId", unique = true)
	@Pattern(regexp = "^[a-z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-z0-9.-]+$")
	@NotBlank(message = "Email cannot be empty")
	private String emailId;
	
	private long mobileNumber;
	
	private LocalDate dateOfBirth;
	
	@Column (name= "gender")
	private String gender;
	
	@Column (name= "role")
	@NotEmpty
	private String role;
	private String password;
	
	
	public int getProfileId() {
		return profileId;
	}
	public void setProfileId(int profileId) {
		this.profileId = profileId;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public long getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	public User(int profileId, String fullName, String userName,
			@Pattern(regexp = "^[a-z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-z0-9.-]+$") String emailId,
			@Pattern(regexp = "\\d{10}") long mobileNumber, LocalDate dateOfBirth,
			@Pattern(regexp = "Male|Female") String gender, @Pattern(regexp = "CUSTOMER|DEALER|ADMIN") String role,
			String password) {
		super();
		this.profileId = profileId;
		this.fullName = fullName;
		this.userName = userName;
		this.emailId = emailId;
		this.mobileNumber = mobileNumber;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.role = role;
		this.password = password;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
		
	
}