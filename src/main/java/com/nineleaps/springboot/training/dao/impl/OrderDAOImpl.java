package com.nineleaps.springboot.training.dao.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nineleaps.springboot.training.dao.OrderDAO;
import com.nineleaps.springboot.training.model.Order;

@Repository
@Transactional
public class OrderDAOImpl implements OrderDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void createOrder(Order order) {
		Session session=sessionFactory.getCurrentSession();
		 session.save(order);
	}

	@Override
	public List<Order> getAllOrders() {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder crt = session.getCriteriaBuilder();
		CriteriaQuery<Order> crtqry = crt.createQuery(Order.class);
		Root<Order> root = crtqry.from(Order.class);
		crtqry.select(root);
		Query<Order> query = session.createQuery(crtqry);
		if(!query.list().isEmpty())
		return query.list();
		else
			return null;
	}

	@Override
	public Order getOrderById(Long id) {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder crt = session.getCriteriaBuilder();
		CriteriaQuery<Order> crtqry = crt.createQuery(Order.class);
		Root<Order> root = crtqry.from(Order.class);
		crtqry.select(root);
		crtqry.where(crt.equal(root.get("orderid"), id));
		Query<Order> query = session.createQuery(crtqry);
		if(!query.list().isEmpty())
		return query.list().get(0);
		else
			return null;
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
