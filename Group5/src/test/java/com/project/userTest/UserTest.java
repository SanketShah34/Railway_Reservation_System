package com.project.userTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
//import static org.junit.Assert.*;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;
import com.project.user.IUser;
import com.project.user.User;
import com.project.user.UserAbstractFactory;

@SuppressWarnings("deprecation")
public class UserTest {
	
	UserAbstractFactory userAbstractFactory =  UserAbstractFactory.instance();
	IUser user = userAbstractFactory.createUser();

	@Test
	public void getRoleTest() {
		user.setRole("USER");
        Assert.isTrue(user.getRole().equals("USER"));
	}

	@Test
	public void setRoleTest() {
		user.setRole("USER");
        Assert.isTrue(user.getRole().equals("USER"));
	}
	
	@Test
	public void getIdTest() {
		user.setId(5);
        Assert.isTrue(5 == user.getId());
	}
	
	@Test
	public void setIdTest() {
		user.setId(5);
		Assert.isTrue(5 == user.getId());
	}
	
	@Test
	public void getUserNameTest() {
		user.setUserName("dhara@gmail.com");
        Assert.isTrue(user.getUserName().equals("dhara@gmail.com"));
	}
	
	@Test
	public void setUserNameTest() {
		user.setUserName("dhara@gmail.com");
        Assert.isTrue(user.getUserName().equals("dhara@gmail.com"));
	}
	
	@Test
	public void getPasswordTest() {
		user.setPassword("Dhara");
        Assert.isTrue(user.getPassword().equals("Dhara"));
	}
	
	@Test
	public void setPasswordTest() {
		user.setPassword("Dhara");
        Assert.isTrue(user.getPassword().equals("Dhara"));
	}
	
	@Test
	    public void setFirstNameTest() {
	        user.setFirstName("Dhara");
	        Assert.isTrue(user.getFirstName().equals("Dhara"));
	    }
	
	@Test
    public void getFirstNameTest() {
        user.setFirstName("Dhara");
        Assert.isTrue(user.getFirstName().equals("Dhara"));
    }
	
	@Test
    public void getLastNameTest() {
        user.setLastName("Dhara");
        Assert.isTrue(user.getLastName().equals("Dhara"));
    }
	
	@Test
    public void setLastNameTest() {
        user.setLastName("Dhara");
        Assert.isTrue(user.getLastName().equals("Dhara"));
    }
	
	@Test
    public void getIsEnabledTest() {
        user.setEnabled(true);
        Assert.isTrue(user.isEnabled() == true);
    }
	
	@Test
    public void setisEnabledTest() {
        user.setEnabled(true);
        Assert.isTrue(user.isEnabled() == true);
    }
	
	@Test
    public void getDateOfBirthTest() {
		String dateStr = "2000-04-06";
		try 
		{
			Date date = (Date) new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
			user.setDateOfBirth(date);
			Assert.isTrue(user.getDateOfBirth().equals(date));
		} catch (ParseException e)
		{
			e.printStackTrace();
		}
    }
	
	@Test
    public void setDateOfBirthTest() {
		String dateStr = "2000-04-06";
		try 
		{
			Date date = (Date) new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
			user.setDateOfBirth(date);
			Assert.isTrue(user.getDateOfBirth().equals(date));
		} catch (ParseException e)
		{
			e.printStackTrace();
		}
    }
	
	@Test
    public void getMobileNumberTest() {
        user.setMobileNumber(Integer.parseInt("1234567890"));
        Assert.isTrue(user.getMobileNumber() == Integer.parseInt("1234567890"));
    }
	
	@Test
    public void setMobileNumberTest() {
        user.setMobileNumber(Integer.parseInt("1234567890"));
        Assert.isTrue(user.getMobileNumber() == Integer.parseInt("1234567890"));
    }
	
	
	@Test
    public void isFirstNameValidTest() {
        Assert.isTrue(User.isFirstNameValid("Dhara"));
        Assert.isTrue(!User.isFirstNameValid(null));
        Assert.isTrue(!User.isFirstNameValid(""));
    }

    /*@Test
    public void isLastNameValidTest() {
        //Assert.isTrue(User.isLastNameValid("Gohil"));
        Assert.isTrue(!User.isLastNameValid(""));
        Assert.isTrue(!User.isLastNameValid(null));
  
    }
	
	@Test
    public void isPasswordEmptyTest() {
        //Assert.isTrue(User.isPasswordEmpty("Dhara"));
        Assert.isTrue(!User.isPasswordEmpty(""));
        Assert.isTrue(!User.isPasswordEmpty(null));
  
    }
	
	@Test
    public void isConfirmPasswordEmptyTest() {
        //Assert.isTrue(User.isConfirmPasswordEmpty("Dhara"));
        Assert.isTrue(!User.isConfirmPasswordEmpty(""));
        Assert.isTrue(!User.isConfirmPasswordEmpty(null));
  
    }*/

    @Test
    public void isEmailValidTest() {
        Assert.isTrue(User.isEmailIdValid("dhara@gmail.com"));
        Assert.isTrue(!User.isEmailIdValid(null));
        Assert.isTrue(!User.isEmailIdValid(""));
        Assert.isTrue(!User.isEmailIdValid("@gmail.com"));
    }
    
    @Test
    public void isPasswordValidTest() {
    	Assert.isTrue(User.isPasswordValid("Dhara", "Dhara"));
    	Assert.isTrue(!User.isPasswordValid("Dhara", "Hello"));
    }
    
   /* @Test
    public void isDateValidTest() {
    	String trueDateStr = "2000-04-06";
    	String falseDateStr = "2025-04-02";
		try 
		{
			Date trueDate = (Date) new SimpleDateFormat("yyyy-MM-dd").parse(trueDateStr);
			Date falseDate = (Date) new SimpleDateFormat("yyyy-MM-dd").parse(falseDateStr);
			Assert.isTrue(User.isDateValid(trueDate));
			Assert.isTrue(!User.isDateValid(falseDate));
		} catch (ParseException e)
		{
			e.printStackTrace();
		}
    }*/
}
