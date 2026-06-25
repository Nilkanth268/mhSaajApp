package com.jewellery.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jewellery.model.Product;
import com.jewellery.service.ProductService;

@RestController
@RequestMapping("api/products")
public class ProductRestController {

	@Autowired
	private ProductService productService;

	@GetMapping
	public List<Product> getAllProducts() {
		return productService.getAllProd();
	}

	@GetMapping("/{pId}")
	public Product getProductById(@PathVariable Integer pId) {
		return productService.getProdById(pId);
	}

	@DeleteMapping("/{pId}")
	public ResponseEntity<String> deleteProductById(@PathVariable Integer pId) {
		boolean deleted = productService.delProdById(pId);

		if (deleted) {
			return ResponseEntity.ok("Product Deleted Successfully");
		}

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
	}

}
