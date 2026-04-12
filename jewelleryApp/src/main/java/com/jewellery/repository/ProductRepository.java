package com.jewellery.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.jewellery.model.Product;

import tools.jackson.databind.ObjectMapper;

@Repository
public class ProductRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void saveProduct(Product product) {

		System.out.println("saveProductRepo meathod called " + product);

		try {
			ObjectMapper objectMapper = new ObjectMapper();
			String productJsonObj = objectMapper.writeValueAsString(product);
			System.out.println("productJsonObj: "+productJsonObj);

			if (productJsonObj != null) {
				jdbcTemplate.update("Call SAVE_PRODUCT", productJsonObj);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
