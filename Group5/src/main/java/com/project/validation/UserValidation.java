package com.project.validation;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class UserValidation {
	
	public UserValidation() {
		
	}
	
	public boolean passwordValidation(String password, String confirmPassword) {
		if(password.equals(confirmPassword)) {
			return true;   //password matches
		}
		else {
			return false;  //Password doesn't match
		}
		
	}
	
	public boolean emailValidation(String email) {
		String regex = "^(.+)@(.+)$";  
		Pattern pattern = Pattern.compile(regex);  
		Matcher matcher = pattern.matcher(email);  
		if(matcher.matches() == true) {
			return true;    //Email pattern is correct
		}
		else {
			return false;    //Email pattern is wrong
		}
		
	}
	
	//source: https://stackoverflow.com/questions/14892536/to-check-if-the-date-is-after-the-specified-date
		public boolean dateValidation(Date date) {
			long MILLIS_IN_A_DAY = 1000 * 60 * 60 * 24;
			Date current = new Date();
			String dateStr = date.toString();
		    Date dateParse;
			try {
				dateParse = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
			    Long dateTime = dateParse.getTime();
			    Date nextDate = new Date(dateTime);
			    long nextDateTime = dateTime + MILLIS_IN_A_DAY;
			    Date nextDay = new Date(nextDateTime);
			    if(nextDay.after(current) || current.equals(nextDate)){
			    	return false;   //The date is future day or the present day
			    } 
			    else {
			        return true;   //The date is older than current day
			    }
			
			}
			catch(Exception ex) {
		        ex.printStackTrace();
		    }
			return true;
			
		}

}
