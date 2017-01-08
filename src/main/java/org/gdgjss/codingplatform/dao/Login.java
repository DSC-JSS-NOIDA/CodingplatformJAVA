package org.gdgjss.codingplatform.dao;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;


@Controller
public class Login {

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public void login(HttpSession httpSession, @RequestParam Map<String,String> requestParams) {
		GoogleIdToken.Payload payLoad;
		 String auth_token= requestParams.get("auth_token");
		 String branch=requestParams.get("branch");
		 String year=requestParams.get("year");
		 String admno=requestParams.get("admno");
		
	
		try {
			payLoad = IdTokenVerifierAndParser.getPayload(auth_token);
				String name = (String) payLoad.get("name");
		        String email = payLoad.getEmail();
		        String avatar= (String) payLoad.get("picture");
		        System.out.println("User name: " + name);
		        System.out.println("User email: " + email);
		        System.out.println("avatar :" + avatar);
		        System.out.println("branch :" + branch);
		        System.out.println("year :" + year);
		        System.out.println("admission no :" + admno);
		} catch (Exception e) {
			// TODO Auto-generated catch block 
			e.printStackTrace();
		}
       
		
	} 


} 
	 

