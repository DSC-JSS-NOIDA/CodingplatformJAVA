package org.gdgjss.codingplatform.service;

import java.util.List;

import org.gdgjss.codingplatform.models.Submissions;


public interface SubmissionService {
	
	public void add(Submissions user);
	public void edit(Submissions user);
	public void delete(String admno);
	public Submissions getUser(String admno);
	public List getAllUser(); 

}
