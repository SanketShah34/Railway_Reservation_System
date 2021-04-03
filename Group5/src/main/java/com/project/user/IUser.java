package com.project.user;

import java.util.Date;

public interface IUser {
	
	public String getRole();
	
	public void setRole(String role);
	
	public int getId();
	
	public void setId(int id);
	
	public String getUserName();
	
	public void setUserName(String userName);
	
	public String getPassword();
	
	public void setPassword(String password);
	
	public boolean isEnabled();
	
	public void setEnabled(boolean enabled);
	
	public String getFirstName();
	
	public void setFirstName(String firstName);
	
	public String getLastName();
	
	public void setLastName(String lastName);
	
	public String getGender();
	
	public void setGender(String gender);
	
	public Date getDateOfBirth();
	
	public void setDateOfBirth(Date dateOfBirth);
	
	public String getMobileNumber();
	
	public void setMobileNumber(String mobileNumber);
	
	public  boolean passwordValidation(String password, String confirmPassword);
	
	public boolean emailValidation(String email);
	
	public boolean dateValidation(Date date);
	
	public  boolean isStringNullOrEmpty(String s);
	
	public boolean isEmailIdValid(String emailId);
	
	public  boolean isFirstNameValid(String firstName);
	
	public  boolean isLastNameValid(String lastName);
	
	public  boolean isPasswordEmpty(String password);
	
	public  boolean isConfirmPasswordEmpty(String confirmPassword);
	
	public  boolean isPasswordValid(String password, String confirmPassword);
	
	public  boolean isDateValid(Date date);
	
	public boolean isPhoneNumberValid(String number);
	

	
	

}
