package org.gdgjss.codingplatform.service.impl;

import java.util.List;

import org.gdgjss.codingplatform.models.Questions;
import org.gdgjss.codingplatform.models.Userdet;
import org.gdgjss.codingplatform.service.QuestionService;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class QuestionServiceImpl implements QuestionService {
	
	 
	@Autowired
	private SessionFactory session;
	 
	@Override
	public void add(Questions ques) {
		session.getCurrentSession().save(ques);
	}
	@Override
	public void edit(Questions ques) {
		session.getCurrentSession().update(ques);
	}

	@Override
	public void delete(int quesid) {		
		session.getCurrentSession().delete(getques(quesid));
	}

	@Override
	public Questions getques(int quesid) {
		return (Questions)session.getCurrentSession().get(Questions.class, quesid);
	}

	@Override
	public List getAllQues() {
		return session.getCurrentSession().createQuery("from Questions").list();
	}

}
