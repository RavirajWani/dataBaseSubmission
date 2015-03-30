package edu.neu.cs5200.jdbc.entity;

import java.util.Date;

public class User {

	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String email;
	private Date datOfBirth;
	
	public User(){
		
	}
	
    public User(String userName, String password, String firstName, String lastName, String email, Date dateOfBirth){
    	this.username = userName;
    	this.password = password;
    	this.firstName = firstName;
    	this.lastName = lastName;
    	this.email = email;
    	this.datOfBirth = dateOfBirth;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getDatOfBirth() {
		return datOfBirth;
	}
	public void setDatOfBirth(Date datOfBirth) {
		this.datOfBirth = datOfBirth;
	}
	
	
}
