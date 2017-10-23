package com.niit.dao;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.model.User;
@Repository
@Transactional
public class UserDaoImpl implements UserDao {
	@Autowired
private SessionFactory sessionFactory;
	public void registration(User user) {
		Session session=sessionFactory.getCurrentSession();
		session.save(user);
		
	}
	public boolean isEmailValid(String email) {
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery("from User where email=?");
		query.setString(0, email);
		User user=(User)query.uniqueResult();
		if(user==null)  //email is unique
			return true;
		else
			return false; //email already exists
	}
	public boolean isUsernameValid(String username) {
		Session session=sessionFactory.getCurrentSession();
		User user=(User)session.get(User.class, username);
		if(user==null) //username is unique
			return true;
		else
			return false; //username already exists
	}
	public User login(User user) {
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery("from User where username=? and password=?");
		query.setString(0,user.getUsername());
		query.setString(1,user.getPassword());
		return (User)query.uniqueResult();
	}
	public void updateUser(User validUser) {
		Session session=sessionFactory.getCurrentSession();
		session.update(validUser);
		
	}
	public User getUserByUsername(String username) {
		Session session=sessionFactory.getCurrentSession();
		User user=(User)session.get(User.class, username);
		return user;
	}

}
