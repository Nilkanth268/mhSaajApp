package com.jewellery.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jewellery.model.Order;
import com.jewellery.model.Product;
import com.jewellery.service.OrderService;

import jakarta.servlet.http.HttpSession;

@Controller
public class CheckoutController {

	@Autowired
	private OrderService orderService;

	@GetMapping("/checkout")
	public String checkoutPage(HttpSession session, Model model) {
		List<Product> cart = (List<Product>) session.getAttribute("cartList");

		if (cart == null || cart.isEmpty()) {
			return "redirect:/products";
		}

		double totalAmount = 0;
		for (Product p : cart) {
			if (p.getPAmount() != null) {
				totalAmount += p.getPAmount();
			}
		}

		model.addAttribute("cartItems", cart);
		model.addAttribute("totalAmount", totalAmount);
		model.addAttribute("itemCount", cart.size());

		return "checkout";
	}

	@PostMapping("/checkout/place-order")
	@ResponseBody
	public Map<String, Object> placeOrder(@RequestBody Order order, HttpSession session) {
		Map<String, Object> response = new HashMap<>();
		List<Product> cart = (List<Product>) session.getAttribute("cartList");

		if (cart == null || cart.isEmpty()) {
			response.put("success", false);
			response.put("message", "Your cart is empty!");
			return response;
		}

		double totalAmount = 0;
		for (Product p : cart) {
			if (p.getPAmount() != null) {
				totalAmount += p.getPAmount();
			}
		}

		// Attach Header Total + Detail Item List
		order.setTotalAmt(totalAmount);
		order.setItemList(cart);

		Object userId = session.getAttribute("userId");
		order.setOCrId(userId != null ? String.valueOf(userId) : "GUEST");

		try {
			Integer generatedOrderId = orderService.saveOrderService(order);
			session.removeAttribute("cartList"); // Clear cart on success

			response.put("success", true);
			response.put("orderId", generatedOrderId);
		} catch (Exception e) {
			String errMsg = e.getMessage();
			if (e.getCause() != null && e.getCause().getCause() != null) {
				errMsg = e.getCause().getCause().getMessage();
			}
			response.put("success", false);
			response.put("message", errMsg);
		}

		return response;
	}
}