package com.nineleaps.springboot.training.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.nineleaps.springboot.training.dao.UserDAO;
import com.nineleaps.springboot.training.exception.UserNotFoundException;
import com.nineleaps.springboot.training.model.Order;
import com.nineleaps.springboot.training.model.User;
import com.nineleaps.springboot.training.service.OrderService;

@RestController
public class OrderController {

	@Autowired
	private OrderService orderService;
	@Autowired
	private UserDAO userDao;

	@PostMapping(path = "/create/order/{userid}")
	public ResponseEntity<String> createOrder(@PathVariable("userid") Long userid, @RequestBody Order order)
			throws UserNotFoundException {
		User user = userDao.getUserById(userid);
		Optional<User> op = Optional.ofNullable(user);
		if (!op.isPresent()) {
			throw new UserNotFoundException("User Not Found. When creating order.");
		}
		else {
			order.setUser(user);
			orderService.createOrder(order);
			return new ResponseEntity<String>("Order created successfully for userid -"+userid,HttpStatus.CREATED);
		}
	}
	
	@GetMapping(value="/order/byid/{id}")
	public Order getOrderById(@PathVariable ("id") Long id) {
		 Order order=orderService.getOrderById(id);
		Link selfLink=ControllerLinkBuilder.linkTo(this.getClass()).slash(id).withSelfRel();
		order.add(selfLink);
		
		/*
		 * EntityModel<Order> resource = new EntityModel<Order>(order);
		 * 
		 * ControllerLinkBuilder linkTo =
		 * linkTo(methodOn(this.getClass()).retrieveAllStudents());
		 * 
		 * resource.add(linkTo.withRel("all-students"));
		 */
		
		return order;
	}
	
	@GetMapping(value="/orders")
	public List<Order> getAllOrders() {
		return orderService.getAllOrders();
	}

}
