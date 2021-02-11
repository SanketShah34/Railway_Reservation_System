package com.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class mainController {

	@GetMapping(value = "/showInfo")
	public String showInfo(Model model) {
		
		model.addAttribute("theDate", new java.util.Date());
		return "index";
	}
}
