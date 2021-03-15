package com.project.controller;

import java.sql.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.dao.UserDAO;
import com.project.entity.User;
import com.project.validation.UserValidation;


@Controller
public class loginController {
	
	@Autowired
	UserDAO userDAO;
	
	UserValidation userVal = new UserValidation();
	
	@RequestMapping("/admin/home")
	public String viewHomePageForAdmin(Model model) {
		//System.out.println("home");
		return "home";
	}
	
	@RequestMapping("/user/home")
	public String viewHomePageForUser(Model model) {
		//System.out.println("home");
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
		System.out.println("signup");
		User user = new User();
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
		
		System.out.println("signup page");
		
		
		if(userVal.dateValidation(dateOfBirth) == false) {
			model.addAttribute("dobError", true);
			return "signup";
		}
		if(userVal.emailValidation(userName) == false) {
			model.addAttribute("emailError", true);
			return "signup";
		}
		
		
		else if(userVal.passwordValidation(password, confirmPassword) == false) {
		  model.addAttribute("passwordError", true); 
		  return "signup"; 
		}
		
		User user = new User();
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
