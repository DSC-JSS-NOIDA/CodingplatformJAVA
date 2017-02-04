package org.gdgjss.codingplatform.service.impl;
import java.util.List;

import org.gdgjss.codingplatform.models.Userdet;
import org.gdgjss.codingplatform.service.LoginService;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class LoginServiceImpl implements LoginService {
	@Autowired
	private SessionFactory session;
	 
	@Override
	public void add(Userdet user) {
		session.getCurrentSession().save(user);
	}
	@Override
	public void edit(Userdet user) {
		session.getCurrentSession().update(user);
	}

	@Override
	public void delete(String emailid) {		
		session.getCurrentSession().delete(getUser(emailid));
	}

	@Override
	public Userdet getUser(String emailid) {
		return (Userdet)session.getCurrentSession().get(Userdet.class, emailid);
	}

	@Override
	public List getAllUser() {
		return session.getCurrentSession().createQuery("from Userdet").list();
	}
}
