package com.jewellery.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeControler {
	
	@GetMapping("/")
	public String homeMethod() {
		return "home";
	}

}
