package com.project.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

@Controller
//@RestController
//@RequestMapping("/loginInfo")
public class loginController {
	@RequestMapping("/")
	public String viewHomePage(Model model) {
		System.out.println("home");
		return "home";
	}

	@GetMapping("/login")
	public String showLoginPage() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
			System.out.println("in if part");
			return "/login";
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

//	@GetMapping("/station")
//	public String showStation(Model model) {
//		return "station";
//	}
	
//	@RequestMapping(value = "/station", method = RequestMethod.GET)
//	public ModelAndView loadLogin() {
//
//		return new ModelAndView("station");
//	}
}
