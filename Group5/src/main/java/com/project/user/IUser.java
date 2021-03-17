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
	
	public int getMobileNumber();
	
	public void setMobileNumber(int mobileNumber);
	
	public boolean passwordValidation(String password, String confirmPassword);
	
	public boolean emailValidation(String email);
	
	public boolean dateValidation(Date date);
	

}
