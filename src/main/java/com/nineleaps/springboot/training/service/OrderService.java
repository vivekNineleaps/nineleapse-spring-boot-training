package com.nineleaps.springboot.training.service;

import java.util.List;

import com.nineleaps.springboot.training.model.Order;

public interface OrderService {
	
	void createOrder(Order order);
	List<Order> getAllOrders();
	Order getOrderById(Long id);
	void updateOrderById(Long id,Order order);
	void deleteOrderById(Long id);

}
