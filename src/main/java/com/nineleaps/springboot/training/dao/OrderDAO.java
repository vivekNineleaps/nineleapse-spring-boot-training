package com.nineleaps.springboot.training.dao;

import java.util.List;

import com.nineleaps.springboot.training.model.Order;

public interface OrderDAO {
	void createOrder(Order order);
	List<Order> getAllOrders();
	Order getOrderById(Long id);
	void updateOrderById(Long id,Order order);
	void deleteOrderById(Long id);

}
