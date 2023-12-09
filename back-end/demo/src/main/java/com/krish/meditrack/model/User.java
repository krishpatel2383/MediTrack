package com.krish.meditrack.model;

public class User {

	public String userType;
	public String email;
	public String password;

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public User(String userType, String email, String password) {
		super();
		this.userType = userType;
		this.email = email;
		this.password = password;
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "User [userType=" + userType + ", email=" + email + ", password=" + password + "]";
	}

}
