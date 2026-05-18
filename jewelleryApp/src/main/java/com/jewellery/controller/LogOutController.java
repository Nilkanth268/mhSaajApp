package com.jewellery.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class LogOutController {

	@GetMapping("/logout")
	public String logoutUser(HttpSession session) {

		session.invalidate();

		return "redirect:/home?logout=true";

	}
}
