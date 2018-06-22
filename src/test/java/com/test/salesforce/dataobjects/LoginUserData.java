package com.test.salesforce.dataobjects;

public class LoginUserData {
	
	private String userType;
	private String userName;
	private String password;
	private String firstName;
	private String lastName;
	private String mobileNumber;
	private String appPassword;

	
	public LoginUserData setUserType(String userType) {
		this.userType = userType;
		return this;
	}

	public String getUserType() {
		return userType;
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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
	
	public String getMobileNumber(){
		return mobileNumber;
		
	}
	
	public void setMobileNumber(String mobileNumber){ 
		this.mobileNumber = mobileNumber;
	}
	
	public String getAppPassword(){
		return appPassword;
		
	}
	
	public void setAppPassword (String appPassword){ 
		this.appPassword = appPassword;
	}
}
