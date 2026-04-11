package com.jewellery.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {
	
	@GetMapping("/a")
	public String greetHello() {
		return "hello";
	}
	

}
