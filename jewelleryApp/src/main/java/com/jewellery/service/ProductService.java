package com.jewellery.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jewellery.model.Product;
import com.jewellery.repository.ProductRepository;

@Service
public class ProductService {
	
	String result = "";

	@Autowired
	private ProductRepository productRepository;

	public String saveProductService(Product product) {

		System.out.println("saveProductService" + product);
		try {
			productRepository.saveProduct(product);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			result = e.getMessage();
		}
		return result;
	}

}
