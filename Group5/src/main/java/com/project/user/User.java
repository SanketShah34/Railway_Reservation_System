package com.project.user;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class User implements IUser{
	
	public int id;
	
	@NotNull
	@Size(min=2, max=30 , message = "should be more than 2")
	public String userName;
	
	public String password;
	public boolean enabled;
	public String role;
	
	@NotNull(message = "First Name may not be null")
	public String firstName;
	
	@NotNull(message = "Last Name may not be null")
	public String lastName;
	
	public String gender;
	
	@NotNull(message = "Date of birth may not be null")
	public Date dateOfBirth;
	
	@NotNull(message = "Mobile Number may not be null")
	public int mobileNumber;
	
	public User() {
		
	}
	
	public User(int id , String userName , String password , String  role , boolean enabled, 
			String firstName, String lastName, String gender,  Date dateOfBirth, int mobileNumber) {
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.enabled = enabled;
		this.role = role;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.dateOfBirth = dateOfBirth;
		this.mobileNumber = mobileNumber;
	}
	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
	
	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
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
	
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	public int getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(int mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	
	public boolean passwordValidation(String password, String confirmPassword) {
		if(password.equals(confirmPassword)) {
			return true;  
		}
		else {
			return false; 
		}
		
	}
	
	public boolean emailValidation(String email) {
		String regex = "^(.+)@(.+)$";  
		Pattern pattern = Pattern.compile(regex);  
		Matcher matcher = pattern.matcher(email);  
		if(matcher.matches() == true) {
			return true;    
		}
		else {
			return false;
		}
		
	}
	
		//source: https://stackoverflow.com/questions/14892536/to-check-if-the-date-is-after-the-specified-date
		public boolean dateValidation(Date date) {
			
			long MILLIS_IN_A_DAY = 1000 * 60 * 60 * 24;
			Date current = new Date();
			String dateStr = date.toString();
		    Date dateParse;
			try {
				dateParse = (Date) new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
			    Long dateTime = dateParse.getTime();
			    Date nextDate = new Date(dateTime);
			    long nextDateTime = dateTime + MILLIS_IN_A_DAY;
			    Date nextDay = new Date(nextDateTime);
			    if(nextDay.after(current) || current.equals(nextDate)){
			    	return false;   
			    } 
			    else {
			        return true;
			    }
			
			}
			catch(Exception ex) {
		        ex.printStackTrace();
		    }
			return true;
			
		}

}
