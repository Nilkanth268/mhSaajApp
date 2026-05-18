package com.jewellery.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeControler {

	@GetMapping("/")
	public String root() {
	    return "redirect:/home";
	}

	@GetMapping("/home")
	public String home(

	        @RequestParam(required = false)
	        String login,

	        @RequestParam(required = false)
	        String logout,

	        Model model) {

	    // LOGIN MESSAGE
	    if(login != null) {

	        model.addAttribute(
	                "loginMsg",
	                "Login Successfully");
	    }

	    // LOGOUT MESSAGE
	    if(logout != null) {

	        model.addAttribute(
	                "logoutMsg",
	                "Logout Successfully");
	    }

	    return "home";
	}
	
	@GetMapping("/login")
	public String loginPage() {
		return "login";
	}

}
