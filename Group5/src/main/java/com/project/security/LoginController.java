package com.project.security;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
	
	@GetMapping("/admin/home")
	public String viewHomePageForAdmin(Model model) {
		return "home";
	}
	

	@RequestMapping("/login")
	public String showLoginPage() {
		return "login";
	}

	@RequestMapping("/login-error")
	public String loginError(Model model) {
		model.addAttribute("loginError", true);
		return "login";
	}
	
	/*@RequestMapping("/signup")
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
		
		UserAbstractFactory userAbstractFactory =  UserAbstractFactory.instance();
		IUserDAO userDAO =  userAbstractFactory.createUserDAO();
		IUser user = userAbstractFactory.createUser();
		
		System.out.println("signup page");
		
		if(User.isFirstNameValid(firstName) == false || User.isLastNameValid(lastName) == false)
		{
			model.addAttribute("fieldEmptyError", true);
			return "signup";
		}
		if(User.isDateValid(dateOfBirth) == false)
		{
			model.addAttribute("dobError", true);
			return "signup";
		}
		else if(User.isEmailIdValid(userName) == false) 
		{
			model.addAttribute("emailError", true);
			return "signup";
		}
		else if(User.isPasswordValid(password, confirmPassword) == false) {
		  model.addAttribute("passwordError", true); 
		  return "signup"; 
		}
		
		if(userDAO.isUserExists(userName))
		{
			 model.addAttribute("userExist", true); 
			 return "redirect:/login";
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
		
	}*/

}
