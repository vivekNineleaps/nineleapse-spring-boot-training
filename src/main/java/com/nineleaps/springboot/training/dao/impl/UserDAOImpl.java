package com.nineleaps.springboot.training.dao.impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nineleaps.springboot.training.dao.UserDAO;
import com.nineleaps.springboot.training.model.User;

@Repository
@Transactional
public class UserDAOImpl implements UserDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void createUser(User user) {
		Session session = sessionFactory.getCurrentSession();
		session.save(user);
	}

	@Override
	public User getUserById(Long id){
		
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder crt = session.getCriteriaBuilder();
		CriteriaQuery<User> crtqry = crt.createQuery(User.class);
		Root<User> root = crtqry.from(User.class);
		crtqry.select(root);
		crtqry.where(crt.equal(root.get("userid"), id));
		Query<User> query = session.createQuery(crtqry);
		if(!query.list().isEmpty())
		return query.list().get(0);
		else
			return null;
		
	}
	
	@Override
	public User getUserByName(String username) {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder crt = session.getCriteriaBuilder();
		CriteriaQuery<User> crtqry = crt.createQuery(User.class);
		Root<User> root = crtqry.from(User.class);
		crtqry.select(root);
		crtqry.where(crt.equal(root.get("username"), username));
		Query<User> query = session.createQuery(crtqry);
		if(!query.list().isEmpty())
		return query.list().get(0);
		else
			return null;
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
	public User updateUserById(Long id, User user) {
		Session session = sessionFactory.getCurrentSession();
		User checkUser =session.get(User.class, id);
		Optional<User> op=Optional.ofNullable(checkUser);
		if(!op.isPresent()) {
			return null;
		}
		else {
		user.setUserid(id);
		session.update(user);
		return user;
		}
	}

	@Override
	public void deleteUserById(Long id) {
		Session session = sessionFactory.getCurrentSession();
		User user = session.get(User.class, id);
		session.delete(user);
	}


}
