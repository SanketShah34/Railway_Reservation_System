package com.project.userTest;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import static org.junit.Assert.*;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;
import com.project.user.IUser;
import com.project.user.UserAbstractFactory;

@SuppressWarnings("deprecation")
public class UserTest {
	
	UserAbstractFactory userAbstractFactory =  UserAbstractFactory.instance();
	IUser user = userAbstractFactory.createUser();

	@Test
	public void getRoleTest() {
		user.setRole("USER");
        assertEquals(user.getRole(), "USER");
	}

	@Test
	public void setRoleTest() {
		user.setRole("USER");
        assertEquals(user.getRole(), "USER");
	}
	
	@Test
	public void getIdTest() {
		user.setId(5);
        assertEquals(user.getId(), 5);
	}
	
	@Test
	public void setIdTest() {
		user.setId(5);
		assertEquals(user.getId(), 5);
	}
	
	@Test
	public void getUserNameTest() {
		user.setUserName("dhara@gmail.com");
        assertEquals(user.getUserName(), "dhara@gmail.com");

	}
	
	@Test
	public void setUserNameTest() {
		user.setUserName("dhara@gmail.com");
        assertEquals(user.getUserName(), "dhara@gmail.com");
	}
	
	@Test
	public void getPasswordTest() {
		user.setPassword("Dhara");
        assertEquals(user.getPassword(), "Dhara");
	}
	
	@Test
	public void setPasswordTest() {
		user.setPassword("Dhara");
        assertEquals(user.getPassword(), "Dhara");
	}
	
	@Test
	public void setFirstNameTest() {
	    user.setFirstName("Dhara");
	    assertEquals(user.getFirstName(), "Dhara");
	}
	
	@Test
    public void getFirstNameTest() {
        user.setFirstName("Dhara");
        assertEquals(user.getFirstName(), "Dhara");
    }
	
	@Test
    public void getLastNameTest() {
        user.setLastName("Gohil");
        assertEquals(user.getLastName(), "Gohil");
    }
	
	@Test
    public void setLastNameTest() {
        user.setLastName("Gohil");
        assertEquals(user.getLastName(), "Gohil");
    }
	
	@Test
    public void getIsEnabledTest() {
        user.setEnabled(true);
        assertEquals(user.isEnabled(), true);
    }
	
	@Test
    public void setisEnabledTest() {
        user.setEnabled(true);
        assertEquals(user.isEnabled(), true);
    }
	
	@Test
    public void getDateOfBirthTest() {
		String dateStr = "2000-04-06";
		try 
		{
			Date date = (Date) new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
			user.setDateOfBirth(date);
			assertEquals(user.getDateOfBirth(), date);
		} catch (ParseException exception)
		{
			exception.printStackTrace();
		}
    }
	
	@Test
    public void setDateOfBirthTest() {
		String dateStr = "2000-04-06";
		try 
		{
			Date date = (Date) new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
			user.setDateOfBirth(date);
			assertEquals(user.getDateOfBirth(), date);
		} catch (ParseException exception)
		{
			exception.printStackTrace();
		}
    }
	
	@Test
    public void getMobileNumberTest() {
        user.setMobileNumber("9933562165");
        assertEquals(user.getMobileNumber(), "9933562165");
    }
	
	@Test
    public void setMobileNumberTest() {
        user.setMobileNumber("9933562165");
        assertEquals(user.getMobileNumber(), "9933562165");
    }
	
	@Test
    public void passwordValidationTest() {
		Assert.isTrue(user.passwordValidation("Dhara", "Dhara"));
		assertFalse(user.passwordValidation("Dhara", "Hello"));
		assertFalse(user.passwordValidation("Dhara", null));	
    }
	
	@Test
	public void emailValidationTest() {
		 Assert.isTrue(user.emailValidation("dhara@gmail.com"));
		 assertFalse(user.emailValidation(null));
		 assertFalse(user.emailValidation(""));	
		 assertFalse(user.emailValidation("@gmail.com"));	
	}
	 
	@Test
    public void dateValidationTest() {
    	String trueDateStr = "2000-04-06";
    	String falseDateStr = "2025-04-02";
		try {
			Date trueDate = (Date) new SimpleDateFormat("yyyy-MM-dd").parse(trueDateStr);
			Date falseDate = (Date) new SimpleDateFormat("yyyy-MM-dd").parse(falseDateStr);
			Assert.isTrue(user.dateValidation(trueDate));
			assertFalse(user.dateValidation(falseDate));
		} catch (ParseException exception)
		{
			exception.printStackTrace();
		}
    }
	
	@Test
	public void isStringNullOrEmptyTest() {
		assertEquals(user.isStringNullOrEmpty("Dhara"), false);
		assertEquals(user.isStringNullOrEmpty(""), true);
		assertEquals(user.isStringNullOrEmpty(null), true);
	}
	
	@Test
	public void isFirstNameValidTest() {
		assertEquals(user.isFirstNameValid("Dhara"), false);
		assertEquals(user.isFirstNameValid(""), true);
		assertEquals(user.isFirstNameValid(null), true);
	}
	
	@Test
    public void isLastNameValidTest() {
    	assertEquals(user.isLastNameValid("Gohil"), false);
    	assertEquals(user.isLastNameValid(null), true);
    	assertEquals(user.isLastNameValid(""), true);
    }
	
	@Test
    public void isEmailValidTest() {
		assertEquals(user.isEmailIdValid("dhara@gmail.com"), true);
		assertEquals(user.isEmailIdValid(null), false);
		assertEquals(user.isEmailIdValid(""), false);
    }
	
	@Test
    public void isPasswordEmptyTest() {
		assertEquals(user.isPasswordEmpty("Dhara"), false);
		assertEquals(user.isPasswordEmpty(null), true);
		assertEquals(user.isPasswordEmpty(""), true);
    }
	
	@Test
    public void isConfirmPasswordEmptyTest() {
		assertEquals(user.isPasswordEmpty("Dhara"), false);
		assertEquals(user.isPasswordEmpty(null), true);
		assertEquals(user.isPasswordEmpty(""), true);
    }
	
	@Test
    public void isDateValidTest() {
    	String trueDateStr = "2000-04-06";
    	String falseDateStr = "2025-04-02";
		try 
		{
			Date trueDate = (Date) new SimpleDateFormat("yyyy-MM-dd").parse(trueDateStr);
			Date falseDate = (Date) new SimpleDateFormat("yyyy-MM-dd").parse(falseDateStr);
			assertEquals(user.isDateValid(trueDate), true);
			assertEquals(user.isDateValid(falseDate), false);
		} catch (ParseException exception)
		{
			exception.printStackTrace();
		}
    }
	
	@Test
    public void isPasswordValidTest() {
		assertEquals(user.isPasswordValid("Dhara", "Dhara"), true);
		assertEquals(user.isPasswordValid("Dhara", "Hello"), false);	
    }
	
	@Test
	public void isPhoneNumberValidTest() {
		assertEquals(user.isPhoneNumberValid("9945825422"), false);
		assertEquals(user.isPhoneNumberValid(""), true);	
		assertEquals(user.isPhoneNumberValid(null), true);	
	}
}
