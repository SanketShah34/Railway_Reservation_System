package com.project.userTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.util.Assert;

import com.project.user.IUser;
import com.project.user.IUserDAO;
import com.project.user.UserAbstractFactory;

public class UserDAOMock implements IUserDAO {
	
	UserAbstractFactory userAbstractFactory =  UserAbstractFactory.instance();
	IUser user = userAbstractFactory.createUser();

	@Override
	public IUser getUserByUsername(String username) {
		user.setUserName(username);
		user.setId(5);
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

	@Override
	public void saveUser(IUser user) {
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
		
	}

	@Override
	public boolean isUserExists(String username) {
		user.setId(5);
		user.setUserName(username);
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
		
		//Assert.isTrue(user.isEnabled() == true);
		return true;
	}

}
