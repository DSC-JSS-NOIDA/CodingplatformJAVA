package org.gdgjss.codingplatform.service.impl;

import java.util.List;

import org.gdgjss.codingplatform.models.Submissions;
import org.gdgjss.codingplatform.service.SubmissionService;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubmissionServiceImpl implements SubmissionService {
	@Autowired
	private SessionFactory session;
	 
	@Override
	public void add(Submissions user) {
		session.getCurrentSession().save(user);
	}
	@Override
	public void edit(Submissions user) {
		session.getCurrentSession().update(user);
	}

	@Override
	public void delete(String admno) {		
		session.getCurrentSession().delete(getUser(admno));
	}

	@Override
	public Submissions  getUser(String admno) {
		return (Submissions)session.getCurrentSession().get(Submissions.class, admno);
	}

	@Override
	public List getAllUser() {
		return session.getCurrentSession().createQuery("from Submissions").list();
	}

}
