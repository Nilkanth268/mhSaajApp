package com.jewellery.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jewellery.model.Product;
import com.jewellery.service.ProductService;

import jakarta.servlet.http.HttpSession;

@Controller
public class CartController {

	@Autowired
	private ProductService productService;

	// Add product to session cart (works for both Guest and Logged-in users)
	@PostMapping("/cart/add/{id}")
	@ResponseBody
	public List<Product> addToCart(@PathVariable Integer id, HttpSession session) {
		List<Product> cart = (List<Product>) session.getAttribute("cartList");
		if (cart == null) {
			cart = new ArrayList<>();
		}

		Product product = productService.getProdById(id);
		if (product != null) {
			cart.add(product);
		}

		session.setAttribute("cartList", cart);
		return cart;
	}

	// Fetch all items currently in the cart
	@GetMapping("/cart/items")
	@ResponseBody
	public List<Product> getCartItems(HttpSession session) {
		List<Product> cart = (List<Product>) session.getAttribute("cartList");
		return (cart != null) ? cart : new ArrayList<>();
	}

	// Remove an item from the cart by its list index
	@PostMapping("/cart/remove/{index}")
	@ResponseBody
	public List<Product> removeFromCart(@PathVariable int index, HttpSession session) {
		List<Product> cart = (List<Product>) session.getAttribute("cartList");
		if (cart != null && index >= 0 && index < cart.size()) {
			cart.remove(index);
			session.setAttribute("cartList", cart);
		}
		return (cart != null) ? cart : new ArrayList<>();
	}
}