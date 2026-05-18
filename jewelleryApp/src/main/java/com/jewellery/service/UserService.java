package com.jewellery.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jewellery.model.User;
import com.jewellery.repository.UserRepository;

@Service
public class UserService {
			
	@Autowired
	private UserRepository userRepository;
	
	String result="";
	public Map<String, Object> saveUserService(User user) {
		
		Map<String, Object> resultMap = new HashMap<>();

		System.out.println("saveUserService" + user);
		try {
			resultMap = userRepository.saveUser(user);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			result = e.getMessage();
		}
		return resultMap;
	}

}


