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

	String errMsg = "";

	// crate a method that will take save product data to service and enter in
	// database

	@GetMapping("/admin/product_form")
	public String addProductPage(Model model) {
		model.addAttribute("product", new Product());

		return "product_form";
	}

	@PostMapping("/admin/save_product")
	public String saveProduct(Product product, Model model) {

		System.out.println("Save Product Method " + product);
		try {

			if (product != null) {
				System.out.println("Product Object Service send :" + product);
				productService.saveProductService(product);
				return "product_form";
			}

		} catch (Exception e) {
			errMsg = e.getMessage();
			System.out.println(errMsg);

			// If using Spring Data JPA, the real message is often inside the root cause
			if (e.getCause() != null && e.getCause().getCause() != null) {
				errMsg = e.getCause().getCause().getMessage();
			}

			model.addAttribute("errMsg", errMsg);

		}
		return "product_form";
	}

}
