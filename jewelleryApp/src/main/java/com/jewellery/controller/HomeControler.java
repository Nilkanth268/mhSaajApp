package com.jewellery.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeControler {

	@GetMapping("/")
	public String root() {
	    return "redirect:/home";
	}

	@GetMapping("/home")
	public String home() {
	    return "home";
	}
	
	@GetMapping("/login")
	public String loginPage() {
		return "login";
	}
}
