package com.jewellery.repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jewellery.model.Login;

import tools.jackson.databind.ObjectMapper;


@Repository
public class LoginRepository {
	
	@Autowired
	private DataSource dataSource;
	
	public Map<String, Object> checkLogin(Login login){
		
		Map<String, Object> resultMap = new HashMap<>();
		
		try {
	
			ObjectMapper mapper = new ObjectMapper();
			
			String inJson = mapper.writeValueAsString(Map.of(
					"UserMobNo", login.getUserMobileNo(),
					"UserPassword", login.getUserPassword()
					));
			
			Connection conn = dataSource.getConnection();
			
			CallableStatement stsmt = conn.prepareCall("{CALL CHECK_PASSWORD(?)}");
			
			stsmt.setString(1, inJson);
			
			ResultSet rs = stsmt.executeQuery();
			
			while(rs.next()) {
				resultMap.put("status", rs.getInt("status"));
				resultMap.put("message", rs.getString("message"));
				
				if(rs.getInt("status") == 1) {
					resultMap.put("userId", rs.getInt("userId"));
					resultMap.put("userName", rs.getString("userName"));
					resultMap.put("sessionId", rs.getString("sessionId"));
					resultMap.put("role", rs.getString("userRole"));
				}
				
			}
			rs.close();
			conn.close();
			stsmt.close();
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return resultMap;
	}
}
