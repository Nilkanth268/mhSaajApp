package com.jewellery.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jewellery.model.Product;
import com.jewellery.service.ProductService;

@RestController
@RequestMapping("api/getAllProducts")
public class ProductRestController {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping
	public List<Product>getAllProducts(){
		return productService.getAllProd();
	}

}
