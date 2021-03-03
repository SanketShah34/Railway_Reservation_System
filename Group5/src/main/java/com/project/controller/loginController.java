package com.project.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.dao.UserDAO;
import com.project.entity.User;
import com.project.service.RouteService;

@Controller
public class loginController {
	
	@Autowired
	UserDAO userDAO;
	
	@RequestMapping("/")
	public String viewHomePage(Model model) {
		System.out.println("home");
		return "home";
	}

	@RequestMapping("/login")
	public String showLoginPage() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
			System.out.println("in if part");
			return "login";
		} else {
			System.out.println("in else part");
			return "redirect:/";
		}
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
	
	@PostMapping(value = "/signup/save")
	public String signUpPage(@Valid @ModelAttribute("user") User user, BindingResult result) {

		System.out.println("signup page");
		System.out.println(result);
		
		if (result.hasErrors()) {
			System.out.println("in if");
			return "signup";
		} else{
			System.out.println("in else");
			userDAO.saveUser(user);
			return "redirect:/login";
		}
		
	}

}
