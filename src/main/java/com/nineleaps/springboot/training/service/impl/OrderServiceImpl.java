package com.nineleaps.springboot.training.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nineleaps.springboot.training.dao.OrderDAO;
import com.nineleaps.springboot.training.model.Order;
import com.nineleaps.springboot.training.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderDAO orderDao;

	@Override
	public void createOrder(Order order) {
		
		 orderDao.createOrder(order);
	}

	@Override
	public List<Order> getAllOrders() {
		return orderDao.getAllOrders();
	}

	@Override
	public Order getOrderById(Long id) {
		return  orderDao.getOrderById(id);
		
	}

	@Override
	public void updateOrderById(Long id, Order order) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteOrderById(Long id) {
		// TODO Auto-generated method stub

	}

}
