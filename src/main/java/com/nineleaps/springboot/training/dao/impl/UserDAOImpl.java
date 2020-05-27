package com.nineleaps.springboot.training.dao.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nineleaps.springboot.training.dao.UserDAO;
import com.nineleaps.springboot.training.model.User;

@Repository
public class UserDAOImpl implements UserDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void createUser(User user) {
		Session session = sessionFactory.getCurrentSession();
		session.save(user);
	}

	@Override
	public User getUserById(Long id) {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder crt = session.getCriteriaBuilder();
		CriteriaQuery<User> crtqry = crt.createQuery(User.class);
		Root<User> root = crtqry.from(User.class);
		crtqry.select(root);
		crtqry.where(crt.equal(root.get("user_id"), id));
		Query<User> query = session.createQuery(crtqry);
		User user = query.getSingleResult();
		return user;
	}
	
	@Override
	public List<User> getAllUsers() {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder crt = session.getCriteriaBuilder();
		CriteriaQuery<User> crtqry = crt.createQuery(User.class);
		Root<User> root = crtqry.from(User.class);
		crtqry.select(root);
		Query<User> query = session.createQuery(crtqry);
		List<User> usersList = query.list();
		return usersList;
	}

	@Override
	public void UpdateUserById(Long id, User user) {
		Session session = sessionFactory.getCurrentSession();
		user.setUser_id(id);
		session.update(user);
	}

	@Override
	public void DeleteUserById(Long id) {
		Session session = sessionFactory.getCurrentSession();
		User user = session.get(User.class, id);
		session.delete(user);
	}


}
