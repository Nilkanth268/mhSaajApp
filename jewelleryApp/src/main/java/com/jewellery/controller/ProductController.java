package com.jewellery.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.jewellery.model.Product;
import com.jewellery.service.ProductService;

import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class ProductController {

	@Autowired
	private ProductService productService;

	String errMsg = "";

	// crate a method that will take save product data to service and enter in
	// database

	@GetMapping("/admin/product_form")
	public String addProductPage(Model model) {
		model.addAttribute("product", new Product());

		return "product_form";
	}

	@PostMapping("/admin/save_product")
	public String saveProduct(Product product, Model model) { // when save btn is et pressed

		System.out.println("Save Product Method " + product);
		try {

			if (product != null) {
				System.out.println("Product Object Service send :" + product);

				// IMAGE FILE
				MultipartFile file = product.getImageFile();

				if (file != null && !file.isEmpty()) {

					// IMAGE NAME
					String fileName = file.getOriginalFilename();

					// SAVE NAME IN PRODUCT
					product.setImageName(fileName);

					// FOLDER PATH
					String uploadDir = "src/main/resources/static/images/";

					// SAVE FILE
					Path path = Paths.get(uploadDir + fileName);

					Files.write(path, file.getBytes());
				}

				productService.saveProductService(product);

				return "redirect:/products";
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

		return "home";
	}

	@GetMapping("/products")
	public String getAllProduct(Model model) {

		List<Product> prodList = productService.getAllProd();

		model.addAttribute("product", prodList);

		return "productList";

	}

}
