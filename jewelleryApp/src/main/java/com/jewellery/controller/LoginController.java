package com.jewellery.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.jewellery.model.Login;
import com.jewellery.service.LoginService;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {
	
	@Autowired
	private LoginService loginService;
	
	@PostMapping("/check-login")
	public String checkLogin(
			@ModelAttribute Login req,
			Model model,
			HttpSession httpSession) {
		
		Map<String, Object> response = loginService.checkLogin(req);
	
		  Integer status =(Integer) response.get("status");
		  
		  if (status != null && status == 1) {

		        // SESSION STORE
			  httpSession.setAttribute(
		                "sessionId",
		                response.get("sessionId"));

		        httpSession.setAttribute(
		                "userId",
		                response.get("userId"));

		        httpSession.setAttribute(
		                "userName",
		                response.get("userName"));

		        httpSession.setAttribute(
		                "role",
		                response.get("role"));
		        
		        return "redirect:/home?login=true";

		    } else {

		        model.addAttribute("errorMsg",
		                response.get("message"));

		        return "login";
		    }
		
	}

}
