package com.jewellery.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jewellery.model.Login;
import com.jewellery.repository.LoginRepository;

@Service
public class LoginService {

	@Autowired
	private LoginRepository loginRepo;

	public Map<String, Object> checkLogin(Login login) {

		return loginRepo.checkLogin(login);
	}
}