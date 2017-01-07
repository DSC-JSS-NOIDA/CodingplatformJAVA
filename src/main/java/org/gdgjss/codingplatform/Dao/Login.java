package org.gdgjss.codingplatform.Dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class Login {
	private SessionFactory sessionFactory;
	
     @Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
     
	public void create(){
	Session sessionFactory = getSessionFactory().getCurrentSession();
	sessionFactory.beginTransaction();
	
	sessionFactory.getTransaction().commit();
	} 
	 
}
