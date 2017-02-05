package org.gdgjss.codingplatform.service;

import java.util.List;

import org.gdgjss.codingplatform.models.Userdet;
 
public interface LoginService {
	public void add(Userdet User);
	public void edit(Userdet User);
	public void delete(String emailid);
	public Userdet getUser(String emailid);
	public List getAllUser();
	

}
