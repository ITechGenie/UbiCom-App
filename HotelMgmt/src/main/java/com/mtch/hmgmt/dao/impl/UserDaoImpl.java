package com.mtch.hmgmt.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.mtch.hmgmt.dao.UserDao;
import com.mtch.hmgmt.model.User;

/**
 * 
 * @author Karthick
 *
 */
@Repository
public class UserDaoImpl extends HibernateDaoSupport implements UserDao{

	@Autowired
	public void setMySessionFactory(SessionFactory sessionFactory){
		setSessionFactory(sessionFactory);
	}
	
	@Override
	public User findById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveUser(User user) {
		getHibernateTemplate().save(user);
		
	}

	@Override
	public void updateUser(User user) {
		getHibernateTemplate().saveOrUpdate(user);
		
	}

	@Override
	public void deleteUserById(long id) {
		
	}

	@Override
	public List<User> findAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteAllUsers() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isUserExist(User user) {
		// TODO Auto-generated method stub
		return false;
	}
	
	 

}
