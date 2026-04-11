package com.jewellery.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jewellery.model.Product;
import com.jewellery.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	public void saveProductService(Product product) {
		
		productRepository.saveProduct(product);
	}

}
