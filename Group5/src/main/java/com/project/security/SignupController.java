package com.project.security;

import java.sql.Date;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.project.user.IUser;
import com.project.user.IUserDAO;
import com.project.user.User;
import com.project.user.UserAbstractFactory;
import com.project.user.UserConcreteFactory;

@Controller
public class SignupController {
	
	private final String USERNAME = "userName";
    private final String PASSWORD = "password";
    private final String CONFIRM_PASSWORD = "confirmPassword";
    private final String FIRST_NAME = "firstName";
    private final String LAST_NAME = "lastName";
    private final String GENDER = "gender";
    private final String DATE_OF_BIRTH = "dateOfBirth";
    private final String MOBILE_NUMBER = "mobileNumber";
	
	@RequestMapping("/signup")
	public String signUpPage(Model model) {
		
		UserAbstractFactory userAbstractFactory = new UserConcreteFactory();
		IUser user = userAbstractFactory.createUser();
		System.out.println("signup");
		model.addAttribute(user);
		return "signup";
	}
	
	@RequestMapping(value = "/signup/save", method = RequestMethod.POST)
	public String signUpPage(@RequestParam(name = FIRST_NAME) String firstName,
								@RequestParam(name = LAST_NAME) String lastName,
								@RequestParam(name = GENDER) String gender,
								@RequestParam(name = DATE_OF_BIRTH) Date dateOfBirth,
								@RequestParam(name = MOBILE_NUMBER) int mobileNubmer,
								@RequestParam(name = USERNAME) String userName,
								@RequestParam(name = PASSWORD) String password,
								@RequestParam(name = CONFIRM_PASSWORD) String confirmPassword,
								Model model) {
		
		UserAbstractFactory userAbstractFactory =  UserAbstractFactory.instance();
		IUserDAO userDAO =  userAbstractFactory.createUserDAO();
		IUser user = userAbstractFactory.createUser();
		
		System.out.println("signup page");
		
		if(User.isEmailIdValid(userName) == false) {
			model.addAttribute("emailError", true);
			return "signup";
		}
		
		if(userDAO.isUserExists(userName) == true) {
			model.addAttribute("userExists", true); 
			System.out.println("user exist");
			return "redirect:/login"; 
		}
		
		if(User.isFirstNameValid(firstName) == true || User.isLastNameValid(lastName) == true ||
				User.isPasswordEmpty(password) || User.isConfirmPasswordEmpty(confirmPassword) ) {
			model.addAttribute("fieldEmptyError", true);
			return "signup";
		}
		
		if(User.isDateValid(dateOfBirth) == false) {
			model.addAttribute("dobError", true);
			return "signup";
		}
		
		else if(User.isPasswordValid(password, confirmPassword) == false) {
		  model.addAttribute("passwordError", true); 
		  return "signup"; 
		}
		
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setGender(gender);
		user.setDateOfBirth(dateOfBirth);
		user.setMobileNumber(mobileNubmer);
		user.setUserName(userName);
		user.setPassword(password);
		userDAO.saveUser(user);
		return "redirect:/login";
		
	}


}
