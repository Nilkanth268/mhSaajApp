package com.jewellery.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.jewellery.model.User;

import tools.jackson.databind.ObjectMapper;

@Repository
public class UserRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public Map<String, Object> saveUser(User user) {

		System.out.println("saveUser meathod called " + user);

		Map<String, Object> resultMap = new HashMap<>();

		try {
			ObjectMapper objectMapper = new ObjectMapper();
			String userJsonObj = objectMapper.writeValueAsString(user);
			System.out.println("userJsonObj: " + userJsonObj);
			String sqlStr = "Call CREATE_USER(?)";

			if (userJsonObj != null) {
				Map<String, Object> response = jdbcTemplate.queryForMap(sqlStr, userJsonObj);
				resultMap.put("status", response.get("status"));
				resultMap.put("message", response.get("message"));
			}

		} catch (Exception e) {

			e.printStackTrace();

			resultMap.put("status", 0);

			resultMap.put("message", "Something Went Wrong");
		}

		return resultMap;
	}

}
