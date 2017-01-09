package org.gdgjss.codingplatform.dao;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.gdgjss.codingplatform.models.Userdet;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;


@Controller
public class Login {
	
	@Autowired
	private SessionFactory sessionFactory;
	Userdet  userdet;

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public ModelAndView registration(HttpSession httpSession, @RequestParam Map<String,String> requestParams) {
		GoogleIdToken.Payload payLoad;
		ModelAndView indexpage=new ModelAndView("index");
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
		        userdet=new Userdet
		        		(email,avatar,year,branch,name,admno);
		        Session session = sessionFactory.openSession();
				session.beginTransaction();
				session.save(userdet);
				session.getTransaction().commit();
				session.close();
		        
		} catch (Exception e) {
			// TODO Auto-generated catch block 
			e.printStackTrace();
		}
		
		return indexpage;
		
	} 
	
	/*MediaType.APPLICATION_JSON_VALUE return string*/
	@RequestMapping(value = "/loginverifier", method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String loginverify(HttpSession httpSession, @RequestParam ("user_email") String email){
	Session session =sessionFactory.openSession();
	session.beginTransaction();
	userdet = (Userdet) session.get(Userdet.class, email);
	session.close();
	if(userdet != null)
	{
		return "registered";
	}
	       
	else 
		return "new_user";

} 
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView login(HttpSession httpSession, @RequestParam ("email") String email){
		System.out.println("SUYASH SUYASH TILHJARI");
		httpSession.setAttribute("loggedinuser",email);
		ModelAndView indexpage=new ModelAndView("index");//generating session for the logged in user
		return indexpage;
	
	}
	@RequestMapping(value = "/submission", method = RequestMethod.POST)
	public void submission(HttpSession httpSession, @RequestParam Map<String,String> requestParams) {
		String language = requestParams.get("lang");
        String code = requestParams.get("code");
        System.out.println(language);
        System.out.println(code);
	} 
} 
	 

