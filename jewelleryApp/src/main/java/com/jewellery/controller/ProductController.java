package com.jewellery.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.jewellery.model.Product;
import com.jewellery.service.ProductService;

@Controller
public class ProductController {

	private final HomeControler homeControler;

	@Autowired
	private ProductService productService;

	ProductController(HomeControler homeControler) {
		this.homeControler = homeControler;
	}

	// make the methos that will take me to save Product froent end page

	// crate a method that will take save product data to service and enter in
	// database

	@GetMapping("/admin/product_form")
	public String addProductPage(Model model) {
		model.addAttribute("product", new Product());

		return "product_form";
	}

	@PostMapping("/admin/save_product")
	public String saveProduct(Product product) {

		System.out.println("Save Product Method " + product);
		return "product_form";
	}

}
