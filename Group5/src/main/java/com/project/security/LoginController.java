package com.project.security;

import java.sql.Date;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.project.user.IUser;
import com.project.user.IUserDAO;
import com.project.user.UserAbstractFactory;
import com.project.user.UserConcreteFactory;

@Controller
public class LoginController {
	
	@RequestMapping("/admin/home")
	public String viewHomePageForAdmin(Model model) {
		return "home";
	}
	
	@RequestMapping("/user/home")
	public String viewHomePageForUser(Model model) {
		return "searchTrain/searchTrain";
	}
	
	

	@RequestMapping("/login")
	public String showLoginPage() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
//		} 
		return "login";
	}

	@RequestMapping("/login-error")
	public String loginError(Model model) {
		model.addAttribute("loginError", true);
		return "login";
	}
	
	@RequestMapping("/signup")
	public String signUpPage(Model model) {
		
		UserAbstractFactory userAbstractFactory = new UserConcreteFactory();
		IUser user = userAbstractFactory.createUser();
		System.out.println("signup");
		model.addAttribute(user);
		return "signup";
	}
	
	@RequestMapping(value = "/signup/save", method = RequestMethod.POST)
	public String signUpPage(@RequestParam(name = "firstName") String firstName,
								@RequestParam(name = "lastName") String lastName,
								@RequestParam(name = "gender") String gender,
								@RequestParam(name = "dateOfBirth") Date dateOfBirth,
								@RequestParam(name = "mobileNumber") int mobileNubmer,
								@RequestParam(name = "userName") String userName,
								@RequestParam(name = "password") String password,
								@RequestParam(name = "confirmPassword") String confirmPassword,
								Model model) {
		
		UserAbstractFactory userAbstractFactory = new UserConcreteFactory();
		IUserDAO userDAO =  userAbstractFactory.createUserDAO();
		IUser user = userAbstractFactory.createUser();
		
		System.out.println("signup page");
		
		
		if(user.dateValidation(dateOfBirth) == false) {
			model.addAttribute("dobError", true);
			return "signup";
		}
		if(user.emailValidation(userName) == false) {
			model.addAttribute("emailError", true);
			return "signup";
		}
		
		
		else if(user.passwordValidation(password, confirmPassword) == false) {
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
