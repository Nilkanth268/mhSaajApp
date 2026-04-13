package com.jewellery.repository;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;

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
			System.out.println("productJsonObj: " + productJsonObj);
			String sqlStr = "Call SAVE_PRODUCT(?)";

			if (productJsonObj != null) {
				jdbcTemplate.update(sqlStr, productJsonObj);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	
	public List<Product> getAllProductsRepo() {

		System.out.println("getAllProducts method called ");
		List<Product> pList = new ArrayList<>();

		try {
			String sqlStr = "CALL GET_ALL_PRODUCTS()";

			jdbcTemplate.query(sqlStr, (ResultSetExtractor<Void>) rs -> {

				while (rs.next()) {

					Product prod = new Product();
					prod.setPCode(rs.getString("pCd"));
					prod.setPName(rs.getString("pName"));
					prod.setPCategory(rs.getString("pCat"));
					prod.setPDescription(rs.getString("pDesc"));
					prod.setPAmount(rs.getDouble("pAmt"));

					pList.add(prod);
				}
				return null;
			});
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return pList;

	}

}
