package org.gdgjss.codingplatform.dao;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.gdgjss.codingplatform.models.Questions;
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
	String avatar;

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public ModelAndView registration(HttpSession httpSession, @RequestParam Map<String,String> requestParams) {
		GoogleIdToken.Payload payLoad;
		ModelAndView indexpage=new ModelAndView("dashboard");
		 String auth_token= requestParams.get("auth_token");
		 String branch=requestParams.get("branch");
		 String year=requestParams.get("year");
		 String admno=requestParams.get("admno");
		
	
		try {
			payLoad = IdTokenVerifierAndParser.getPayload(auth_token);
				String name = (String) payLoad.get("name");
		        String email = payLoad.getEmail();
		        avatar= (String) payLoad.get("picture");
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
				indexpage.addObject("name",name);
				indexpage.addObject("avatar",avatar);
		        
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
		ModelAndView indexpage=new ModelAndView("dashboard");//generating session for the logged in user
		Session session =sessionFactory.openSession();
		session.beginTransaction();
		userdet = (Userdet) session.get(Userdet.class, email);
		String name=userdet.getName();
		String avatar=userdet.getAvatar();
		indexpage.addObject("name",name);
		indexpage.addObject("avatar",avatar);
		return indexpage;
	
	}
	@RequestMapping(value = "/api", method = RequestMethod.POST)
	public void submission(HttpSession httpSession, @RequestParam Map<String,String> requestParams)throws IOException {
		String language = requestParams.get("lang");
        String code = requestParams.get("source");
        System.out.println(language);
        
        System.out.println(code);
        //bug in hackerrank api
        
        String url = "http://api.hackerrank.com/checker/submission.json";
	        URL obj = new URL(url);
	        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

	        //add request header
	        con.setRequestMethod("POST");
	     con.setRequestProperty("User-Agent","chrome");
	        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5"); 	

	        String urlParameters = "source="+code+"&lang="+language+"&testcases=[\"1\"]&api_key=hackerrank|1466488-1173|ece751e6f0df6c5c8fc1e8c3498da5c1b5d73f86";

	        // Send post request
	        con.setDoOutput(true);
	        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
	        wr.writeBytes(urlParameters);
	        wr.flush();
	        wr.close();

	        int responseCode = con.getResponseCode();
	        System.out.println("\nSending 'POST' request to URL : " + url);
	        System.out.println("Post parameters : " + urlParameters);
	        System.out.println("Response Code : " + responseCode);

	          BufferedReader in = new BufferedReader(
	               new InputStreamReader(con.getInputStream()));
	        String inputLine;
	        StringBuffer responses = new StringBuffer();

	        while ((inputLine = in.readLine()) != null) {
	            responses.append(inputLine);
	        }
	        in.close();

	        //print result
	        System.out.println(responses.toString());
	        

	} 
	
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public ModelAndView admin(HttpSession httpSession) {
		ModelAndView model= new ModelAndView("admin");
		return model;
		
	} 
	
	@RequestMapping(value = "/ques", method = RequestMethod.GET)
	public ModelAndView ques(HttpSession httpSession) {
		ModelAndView model= new ModelAndView("Quespage");
		return model;
		
	} 
	@RequestMapping(value = "/adminverify", method = RequestMethod.POST)
	public ModelAndView adminverify(HttpSession httpSession, @RequestParam Map<String,String> requestParams) {
		ModelAndView model;
	
		
		String id = requestParams.get("id");
        String pass = requestParams.get("pass");
        if(id.equals("gdg") && pass.equals("gdg")){
        model= new ModelAndView("adminpannel");
             	model.addObject("name",id);
        	        }
        else {
        	return new ModelAndView("err");
	}
        return model;
	}
	//adding new user from admin pannel
	@RequestMapping(value = "/add_user", method = RequestMethod.POST)
	public ModelAndView adduser(HttpSession httpSession, @RequestParam Map<String,String> requestParams) {
		String email=requestParams.get("email");
	
		 Session session =	sessionFactory.openSession();
	       session.beginTransaction();
	       
	      ModelAndView model = new ModelAndView("add");
	         
	       Query queryResult = session.createQuery("from Userdet");
	       java.util.List allUsers;
	       String email_id;
	       email_id=null;
	       
	       allUsers = queryResult.list();
	       int f;
	       f=0;
	       for (int i = 0; i < allUsers.size(); i++) {
	    	  Userdet user = (Userdet) allUsers.get(i);
	        email_id=user.getEmailid();
		      
	        if(email.equals(email_id)){
	         f=1;
	     
	        break; 
	   
	        
	         }
	        }
	         
	           if(f!=1){
	        	   Userdet user= new   Userdet();  
	               user.setEmailid(email);
	               	session.save(user);
	               	session.getTransaction().commit();
	   
	               	session.close(); 
	               	user=null;
	               	System.out.println(email);
	               	model.addObject("add","record added");
	      	           } 
	           else
	           {   System.out.println("duplicate");
	           model.addObject("dup","duplicate record"); 
	           }
	           return model;  
		
	} 
	
	// add new questions to db
	@RequestMapping(value = "/addques", method = RequestMethod.POST)
	public ModelAndView addques(HttpSession httpSession, @RequestParam Map<String,String> requestParams) {
		String title=requestParams.get("title");
		String details=requestParams.get("detail");
		String constraints=requestParams.get("const");
		String inputformat=requestParams.get("inp_format");
		String outpuformat=requestParams.get("op_format");
		String inputtestcase=requestParams.get("inp_testcase");
		String outputtestcase=requestParams.get("op_testcase");
		String file=requestParams.get("file");
		Session session =	sessionFactory.openSession();
	       session.beginTransaction();
	       
	      ModelAndView model = new ModelAndView("addques");
	         
	       Query queryResult = session.createQuery("from Questions");
	       java.util.List allUsers;
	       String titl;
	       titl=null;
	       
	       allUsers = queryResult.list();
	       int f;
	       f=0;
	       for (int i = 0; i < allUsers.size(); i++) {
	    	  Questions user = (Questions) allUsers.get(i);
	        titl=user.getTitle();
		      
	        if(title.equals(titl)){
	         f=1;
	     
	        break; 
	   
	        
	         }
	        }
	         
	           if(f!=1){
	        	   Questions user= new   Questions();  
	               user.setTitle(title);
	               user.setDetail(details);
	               user.setConstraints(constraints);
	               user.setInputformat(inputformat);
	               user.setInputtestcase(inputtestcase);
	               user.setOutputtestcase(outputtestcase);
	              // user.setPath(path);
	               	session.save(user);
	               	session.getTransaction().commit();
	   
	               	session.close(); 
	               	user=null;
	               	System.out.println(title);
	               	model.addObject("add","record added");
	      	           } 
	           else
	           {   System.out.println("duplicate");
	           model.addObject("dup","duplicate record"); 
	           }
	           return model;  
		
	} 
	// editing the exixting questions
	@RequestMapping(value = "/ques_edit", method = RequestMethod.POST)
	public ModelAndView quesedit(HttpSession httpSession, @RequestParam Map<String,String> requestParams) {
		String quesId = requestParams.get("id");
		
		 Session session =	sessionFactory.openSession();
	       session.beginTransaction();
	       
	      ModelAndView model = new ModelAndView("quesedit");
	         
	       Query queryResult = session.createQuery("from Questions");
	       java.util.List allUsers;
	       String det,title,inp_format,cons,inp_case,op_case,path;
	       int qid;
	      qid=0;
	     det=null;
	      title=null;
	      inp_format=null;
	      cons=null;
	      inp_case=null;
	      op_case=null;
	      path=null;
	       allUsers = queryResult.list();
	       int f;
	       f=0;
	       for (int i = 0; i < allUsers.size(); i++) {
	    	   Questions user = (Questions) allUsers.get(i);
	    	   qid=user.getQuesid();
	    	   title=user.getTitle();
	    	   cons=user.getConstraints();
	    	   det=user.getDetail();
	    	   inp_case=user.getInputtestcase();
	    	   op_case=user.getOutputtestcase();
	    	   path=user.getPath();
	         
	        		  
	         if(quesId.equals(qid) ){
		         f=1;
		         break;
	        
	        }
	         	         
	       }
	       if(f==1){
		         model.addObject("qid",qid); 
		         model.addObject("title",title); 
		         model.addObject("detail",det); 
		         model.addObject("constraints",cons); 
		         model.addObject("inputcase",inp_case); 
		         model.addObject("outputcase",op_case); 
		         model.addObject("path",path); 
		        
	        }
	         else
	         {
	        	  model.addObject("norec","no record found");  
	         }
	         
	       return model;
		
	} 
	//modify questions
	@RequestMapping(value = "/modifyque", method = RequestMethod.POST)
	public ModelAndView modify(HttpSession httpSession, @RequestParam Map<String,String> requestParams) {
	    ModelAndView model = new ModelAndView("quesedit");
					String quesid = requestParams.get("id");
					String det,title,inp_format,cons,inp_case,op_case,path;
					det=requestParams.get("detail");
					title=requestParams.get("title");
					cons=requestParams.get("cons");
					inp_case=requestParams.get("inpcase");
					op_case=requestParams.get("opcase");
		 /*
		  space for code for file upload
		  after which path requsest parameter to be used
		  
		  */
		// path=requestParams.get("");
						Session session =	sessionFactory.openSession();
						session.beginTransaction();
	       
	  
	       Questions user= (Questions) session.get(Questions.class,quesid);
	       				user.setQuesid(Integer.parseInt(quesid));
	       				user.setDetail(det);
	       				user.setConstraints(cons);
	       				user.setInputtestcase(inp_case);
	       				user.setOutputtestcase(op_case);
	       				/*
	       				 path to be set after the file upload code is written
	       				 
	       				   */
	       				//user.setPath(path);
	       					session.update(user);
	       					session.getTransaction().commit();
	       					session.close(); 
	       						
	       					
	       					model.addObject("rec","record edited");
	       					return model;
	}
	
	
}
	 

