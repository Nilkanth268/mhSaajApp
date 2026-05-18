package com.jewellery.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.jewellery.model.Product;
import com.jewellery.model.User;
import com.jewellery.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	String errMsg ="";
	
	
	@GetMapping("/user_form")
	public String userPage(Model model) {
		
		model.addAttribute("user", new User());
		return "createUser";
	}
	
	
	@PostMapping("/save_user")
	public String saveUser(User user, Model model) { // when create btn is et pressed 

		System.out.println("Save user Method " + user);
		try {

			if (user != null) {
				System.out.println("User Object Service send :" + user);
				userService.saveUserService(user);
				
				return "redirect:/home";
			}

		} catch (Exception e) {
			errMsg = e.getMessage();
			System.out.println(errMsg);

			// If using Spring Data JPA, the real message is often inside the root cause
			if (e.getCause() != null && e.getCause().getCause() != null) {
				errMsg = e.getCause().getCause().getMessage();
			}

			model.addAttribute("errMsg", errMsg);

		}
		
		return "redirect:/home";
	}
	

}
