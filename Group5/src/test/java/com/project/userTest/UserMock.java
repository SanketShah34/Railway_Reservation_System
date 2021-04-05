package com.project.userTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.project.user.IUser;

public class UserMock {
	public IUser createUserMock(IUser user) {
		user.setId(5);
		user.setUserName("dhara@gmail.com");
		user.setFirstName("Dhara");
		user.setLastName("Gohil");
		user.setDateOfBirth(null);
		user.setGender("Female");
		
		String dateStr = "2000-04-06";
		try {
			Date date = (Date) new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
			user.setDateOfBirth(date);
		} catch (ParseException exception) {
			exception.printStackTrace();
		}
		
		user.setEnabled(true);
		user.setMobileNumber("9933562165");
		user.setPassword("Dhara");
		user.setRole("USER");
		
		return user;
	}

}
