package com.jewellery.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.jewellery.model.Product;
import com.jewellery.service.ProductService;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	public String saveProduct(Product product, Model model) {

		System.out.println("Save Product Method " + product);
		try {
			if (product != null) {
				MultipartFile[] files = product.getImageFiles();
				List<String> savedFileNames = new java.util.ArrayList<>();
				String uploadDir = "src/main/resources/static/images/";

				if (files != null && files.length > 0) {
					for (MultipartFile file : files) {
						if (file != null && !file.isEmpty()) {
							String fileName = file.getOriginalFilename();
							Path path = Paths.get(uploadDir + fileName);
							Files.write(path, file.getBytes());
							savedFileNames.add(fileName);
						}
					}
				}

				// Join all uploaded image names with commas for database storage
				if (!savedFileNames.isEmpty()) {
					product.setImageName(String.join(",", savedFileNames));
				}

				productService.saveProductService(product);
				return "redirect:/products";
			}
		} catch (Exception e) {
			errMsg = e.getMessage();
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
		System.out.println(prodList);

		return "productList";

	}

	@GetMapping("/productById/{id}")
	public String getProductById(@PathVariable Integer id, Model model) {

		Product product = productService.getProdById(id);

		model.addAttribute("product", product);

		return "products_details";
	}

	@GetMapping("/delProductById/{id}")
	public String deleteProductById(@PathVariable Integer id, RedirectAttributes redirectAttributes) {

		boolean deleted = productService.delProdById(id);

		if (deleted) {
			redirectAttributes.addFlashAttribute("successMsg", "Product Deleted SuccessFully");
		} else {
			redirectAttributes.addFlashAttribute("errMsg", "Unable to delete");
		}
		return "redirect:/products";
	}

}
