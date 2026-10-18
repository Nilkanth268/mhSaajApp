package com.jewellery.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jewellery.model.Order;
import com.jewellery.repository.OrderRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;

	public Integer saveOrderService(Order order) throws Exception {
		return orderRepository.saveOrderRepo(order);
	}
}