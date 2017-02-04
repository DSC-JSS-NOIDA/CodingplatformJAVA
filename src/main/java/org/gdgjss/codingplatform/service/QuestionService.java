package org.gdgjss.codingplatform.service;

import java.util.List;

import org.gdgjss.codingplatform.models.Questions;



public interface QuestionService {
	
	public void add(Questions ques); 
	public void edit(Questions ques);
	public void delete(int quesid);
	public Questions getques(int quesid);
	public List getAllQues();

}
