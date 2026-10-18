package com.jewellery.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jewellery.model.Order;
import com.jewellery.model.Product;
import com.jewellery.repository.OrderRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;

	public Integer saveOrderService(Order order) throws Exception {
		return orderRepository.saveOrderRepo(order);
	}
	
	public List<Order> getAllOrdersService() {
		return orderRepository.getAllOrdersRepo();
	}

	public List<Product> getOrderDetailsByIdService(Integer oId) {
		return orderRepository.getOrderDtlByIdRepo(oId);
	}

	public void updateOrderStatusService(Integer oId, String status) {
		orderRepository.updateOrderStatusRepo(oId, status);
	}
}