package org.gdgjss.codingplatform.controller;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;


import javax.servlet.http.HttpSession;

import org.aspectj.weaver.patterns.TypePatternQuestions.Question;
import org.gdgjss.codingplatform.models.Questions;
import org.gdgjss.codingplatform.models.Userdet;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.gson.Gson;


/**
 * this class contains all view controllers, maps view to service layer 
 * @author suyash
 *
 */ 

@Controller
public class AllController {
	
	@Autowired
	private SessionFactory sessionFactory;
	private Userdet userdet;
	
//	private String HACKERRANK_API_CREDENTIALS= "hackerrank|1466488-1173|ece751e6f0df6c5c8fc1e8c3498da5c1b5d73f86"; 

	
	/**
	 * simple boot controller for application
	 * @author tilhari
	 * @return
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView indexpage(){
		ModelAndView model = new ModelAndView("index");
		return model;
	}
	
	/**
	 * @author singhal
	 * controller for user login
	 * @author sarthak
	 * @param httpSession
	 * @param requestParams
	 * @return
	 */
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView login(HttpSession httpSession, @RequestParam("email") String emailid,
			@RequestParam("password") String password){
		ModelAndView model;
		Session session = sessionFactory.openSession();
		userdet = (Userdet) session.get(Userdet.class, emailid);
		if (userdet != null) {
			if (userdet.getPassword().equals(password)) {
				httpSession.setAttribute("SESSION_email", userdet.getEmailid());
				httpSession.setAttribute("SESSION_teamname", userdet.getTeam_name());
				model = new ModelAndView("dashboard");
				model.addObject("TeamName", userdet.getTeam_name());				
				List<Questions> ques = session.createCriteria(Questions.class).list();
				session.close();
			    model.addObject("ques", ques);
			  }
			else
			{
				model=new ModelAndView("index");
				model.addObject("invalid","invalid details");
			}
		}
		 
		else
		{
			model= new ModelAndView("index");
			model.addObject("norecord","no record found");
		}
       return model;
	}

	/**
	 * Sign up form controller for participating team
	 * 
	 * @author tilhari
	 * @param requestParams
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public ModelAndView signup(@ModelAttribute("userdet")
				org.gdgjss.codingplatform.models.Userdet userdet){
		Session session = sessionFactory.openSession();
		ModelAndView model = new ModelAndView("index");
		if(session.get(Userdet.class, userdet.getEmailid()) == null)
		{
			session.beginTransaction();
			session.save(userdet);
			session.getTransaction().commit();
			session.close();
			model.addObject("invalid", "Successfully registered, login to proceed!");
			
		}
		else
			model.addObject("invalid", "This email is already registered.");
		
		return model;
	
	}

	
	/**
	 * 
	 * HackerEarth API controller 
	 * @author suyash tilhari
	 * @param httpSession
	 * @param requestParams
	 * @throws IOException
	 * @throws JSONException
	 */
	@RequestMapping(value = "/api", method = RequestMethod.POST)

	public ModelAndView submission(HttpSession httpSession, @RequestParam Map<String,String> requestParams)throws IOException,JSONException  {
			String language = requestParams.get("lang");
			String code = requestParams.get("source");
			String quesid = requestParams.get("qid");
        	 System.out.println(language);        
        	 System.out.println(code);
        	 String inputpath="",outputpath="",y="",z=""; 
        	
        /*
         * to encode source code in utf 8 , as java uses by default utf-16
         */
        String urlencode=URLEncoder.encode(code, "UTF-8");
        Session session =sessionFactory.openSession();
	    session.beginTransaction();
	    Questions ques= (Questions) session.get(Questions.class,Integer.parseInt(quesid));
	    inputpath=ques.getInputfilepath();
	    outputpath=ques.getOutputfilepath();
	     
	    /**
	     * Commented code is a way to read file as it is from its source, but need to include \n by self
	     * more efficient way
	     * @author tilhari
	     */
	    /*BufferedReader br = new BufferedReader(new FileReader(inputpath));
			int x;
	       	while ((x = br.read()) != -1) {	      	       						
	      	if(y!="")
	      		y=y+"\n"+x;
	      	else
	      		y=y+x;
	      	}
	    */
	    
	    	/**
	     	* This is also a way to read file as it is from its source, but is currently without buffer so less efficient
	     	* @author tilhari
	     	*/
	     	FileReader fr=new FileReader(inputpath);    
	               int i;    
	               while((i=fr.read())!=-1)    
	            	   y=y+((char)i);    
	               fr.close();     	       					
	      	
	        /**
	         *Switch upcoming code lines for toggle HackerRank and hackerEarth API
	         *
	         * HackerRank API is in its beta version, does not support TLE, complexity etc.
	         * No terms and condition also not specified hence no guarantee
	         *
	         * @author tilhari
	         */
            String url = "https://api.hackerearth.com/v3/code/run/";             
	      	//String url = "http://api.hackerrank.com/checker/submission.json";
	        
            URL obj = new URL(url);
	        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

	        //add request header
	        con.setRequestMethod("POST");
	        con.setRequestProperty("ENCODING", "UTF-8");
	        con.setRequestProperty("User-Agent","chrome");
	        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5"); 
	      
	       /**
	        * @author tilhari
	        * url parameters for hackerrank api
	        */
	       // String urlParameters = "source="+urlencode+"&lang="+language+"&testcases=["+y+"]&api_key=hackerrank|1466488-1173|ece751e6f0df6c5c8fc1e8c3498da5c1b5d73f86"; 
	        
	        System.out.println("Input file--- \n"+y);
	        
	        /**
		    * @author tilhari
		    * url parameters for hackerearth api
		    * 
		    * add other available parameter for TLE, size etc etc
		    */   
	        String urlParameters = "source="+urlencode+"&lang="+language+"&input="+y+"&client_secret=d442f2d462c5bcc3fd372f79f878f91bb35ceb43";
	        
	        // Send post request
	        con.setDoOutput(true); 
	        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
	        wr.writeBytes(urlParameters);
	        wr.flush();
	        wr.close();

	        /**
	         * @author suyash
	         * reading 
	         */
	        int responseCode = con.getResponseCode();
	        System.out.println("\nSending 'POST' request to URL : " + url);
	        System.out.println("Post parameters : " + urlParameters);
	        System.out.println("Response Code : " + responseCode);

	        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
	        
	        String respLine=in.readLine();
	        System.out.println("JSON response --->>>");
	        System.out.println(respLine);
	        String message="",stdOut="",status="",htmlOutput="";  //declaring variables for outputs of api
	        
	        /*
	         * use try catch in this code below 
	         * to prevent the exception error
	         * 
	         */
	        
	        /*
	        *********************SARTHAK START FROM HERE ONWARD***************************
	        write code to read response in different situations as stated on hackerearth API docs and write to
	        match output file with result from json,,,,,,,,,,,,
	        use json viewer tool online for better understanding.
	        See my code how easily file can be read exactly as it is from source*/
	        
	        
	        
	        
	        /**
	         * @author suyash
	         * TODO: println adds \n at end of last output line for JAVA language,
	         * so test case should also have \n at end for correct match,
	         * but this can be a problem in case of other language.
	         * so some corner cases need to be considered while testing on different languages OR
	         * different test cases can be made for different languages.........
	         */
	        
	        try{
	          	JSONObject json= new JSONObject(respLine);	
	          	
	          	if(json.has("run_status")){
	          	JSONObject resultObject=json.getJSONObject("run_status");
	          	status=resultObject.getString("status");
	          	htmlOutput=resultObject.getString("output_html");
	          	
	          	if(resultObject.has("output"))
	          	stdOut=resultObject.getString("output");
	         
	          	}
	          	
	          	if(json.has("compile_status")){
		          	//JSONObject compileObject=json.getJSONObject("compile_status");
		          	message=json.getString("compile_status");
		          
		        }
//	          	    if(json.has("result"))
//	          	    {   JSONObject resultObject=json.getJSONObject("result");
//	          	    	message=resultObject.getString("message");
//	          	    	stdOut=resultObject.getString("stdout");
//	          	       
//	          	    }
	          	
	          	else{
	          		message="not avilable";
	              	}
	          	
	         
	        }
	        catch(Exception e){
	        	System.out.println("4");
	        }
	        
	        /*
	         * api output converted to json
	         */
	      
	        
	        			Gson gsons=new Gson();
	        			String jsonapi = gsons.toJson(stdOut);
	        			String out_api="["+jsonapi+"]";
	        			
    	    
    	    
	            	System.out.println(message);
	            	System.out.println(status);
	            	System.out.println(stdOut);
	            	System.out.println(htmlOutput);
	            	System.out.println(out_api);
	        	  
	        	    
	        	
	             System.out.println(outputpath);   //output file path
	             
	             /*
	              * Reading OutPut text case file
	              * using BufferReader
	              */
	             
//	             FileReader file=new FileReader(outputpath);    
//	               int j;    
//	               while((j=file.read())!=-1)    
//	            	   z=z+((char)j);    
//	               file.close();  
//	               System.out.println("output path--- \n"+z);
//	   	         
	             /*
	              * code to read the output file from the path provided
	              * from above code 
	              * by using buffer reader
	              */
	             
	             StringBuilder sb =new StringBuilder();
	         	BufferedReader file = new BufferedReader(new FileReader(outputpath));
				
 					while ( (z = file.readLine()) != null ) {
 						// Printing out each line in the file
 						
 					     sb.append(z);
 					     sb.append("\n");
 					    
 					}
 					   
 					/*
 					 * code for converting output text file to json
 					 * for use with hackerrRank API
 					 */
	            
	        	    Gson gson=new Gson();
	        	    String jsons = gson.toJson(sb);
	        	    String out="["+jsons+"]";
	        	    System.out.println(out);
	        	    
	        	    
	        	    
 					/*
 					 * code to check the output of api with text file
 					 * 
 					 */
 					String verify;
 					if(out.equals(out_api))
 					{ 
 						   System.out.println("output matched");
 						   verify="output matched";
 						   /*
 						    * java.util.List marks;
 						    * 
 						    * code to be changed as the score will be updated in submissions table
 						    
					       String t="";
                           String score;
					       Query queryResult = session.createQuery("from Userdet");			       
					       marks = queryResult.list();
					       /**
					        * 
					        * Will see afterward,,, commented to resolve conflicts
					        * @author suyash
					        */
					       /*for (int ii = 0; ii < marks.size(); ii++) 
					       {
					         Userdet user1 = (Userdet)marks.get(ii);
					         t=user1.getTeam_name();
						     score=user1.getScore();
					           if(t.equals(team_name))
					           {
					              user1.setScore(score+100);
					              break;
					           }
 					       }*/
 					}
 					else{
 						System.out.println("outputs not matched");
 						verify="output not matched";
 					}
 				
 		/**
 		 * 
 		 * sending data to Questionpage			
 		 */
 		ModelAndView model= new ModelAndView("ResultPage");
 					model.addObject("message",message);
 					model.addObject("status",status);
 					model.addObject("stdout",stdOut);
 					model.addObject("code",code);
 					model.addObject("verify",verify);
 					model.addObject("quesid",quesid);
 					
	         return model;
	} 
	
	@RequestMapping(value = "/leaderboard", method = RequestMethod.POST)
	public ModelAndView leaderboard(HttpSession httpSession) {
		   ModelAndView model= new ModelAndView("leaderboard");
		   Session session =	sessionFactory.openSession();
	       session.beginTransaction();
           List<Userdet> user = session.createCriteria(Userdet.class).list();
           model.addObject("user",user);
		   return model;
		
	}
	/*
	 * code for admin login
	 */
	
	
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public ModelAndView admin(HttpSession httpSession) {
		ModelAndView model= new ModelAndView("admin");
		return model;
		
	} 
	
	/**
	 * code for editor
	 * @param httpSession
	 * @return
	 */
	@RequestMapping(value = "/ques", method = RequestMethod.GET)
	public ModelAndView ques(HttpSession httpSession, @RequestParam Map<String,String> requestParams)throws IOException,JSONException  {
		 String team_name=(String)httpSession.getAttribute("SESSION_teamname");
	       String email=(String)httpSession.getAttribute("SESSION_email");
	       ModelAndView model;
	       if( email!=null){
		Session session =	sessionFactory.openSession();
	       session.beginTransaction();
	       model= new ModelAndView("Quespage");
	       String id="";
	       String b="";
	       String Question=""; 
           String Constraint ="";
           String InputFormat="";
           String SampleTestCase="";
	       id=requestParams.get("id");
	      
	       Questions a= (Questions) session.get(Questions.class,Integer.parseInt(id));
   			
   			    Question=a.getDetail(); 
   	            Constraint =a.getConstraints();
   	            InputFormat=a.getInputformat();
   	            SampleTestCase=a.getSampletestcase();
   			
   		
           model.addObject("quesid",id);
           model.addObject("Question", Question);
           model.addObject("Constraint", Constraint);
           model.addObject("InputFormat", InputFormat);
           model.addObject("SampleTestCase", SampleTestCase);
           model.addObject("Teamname",team_name);
           model.addObject("email",email);
           System.out.println(Question);
	    }
	       else {
	    	   model= new ModelAndView("index");
	    	   model.addObject("invalid","log in first to continue");
	       }
	       
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
}
	
/*
 * code for admin pannel operations commented for further use
 * 
 */
//	// add new questions to db
//	@RequestMapping(value = "/addques", method = RequestMethod.POST)
//	public ModelAndView addques(HttpSession httpSession, @RequestParam Map<String,String> requestParams) {
//		String title=requestParams.get("title");
//		String details=requestParams.get("detail");
//		String constraints=requestParams.get("const");
//		String inputformat=requestParams.get("inp_format");
//		String outpuformat=requestParams.get("op_format");
//		String inputtestcase=requestParams.get("inp_testcase");
//		String outputtestcase=requestParams.get("op_testcase");
//		String file=requestParams.get("file");
//		Session session =	sessionFactory.openSession();
//	       session.beginTransaction();
//	       
//	      ModelAndView model = new ModelAndView("addques");
//	         
//	       Query queryResult = session.createQuery("from Questions");
//	       java.util.List allUsers;
//	       String titl;
//	       titl=null;
//	       
//	       allUsers = queryResult.list();
//	       int f;
//	       f=0;
//	       for (int i = 0; i < allUsers.size(); i++) {
//	    	  Questions user = (Questions) allUsers.get(i);
//	        titl=user.getTitle();
//		      
//	        if(title.equals(titl)){
//	         f=1;
//	     
//	        break; 
//	   
//	        
//	         }
//	        }
//	         
//	           if(f!=1){
//	        	   Questions user= new   Questions();  
//	               user.setTitle(title);
//	               user.setDetail(details);
//	               user.setConstraints(constraints);
//	               user.setInputformat(inputformat);
//	               user.setInputtestcase(inputtestcase);
//	               user.setOutputtestcase(outputtestcase);
//	              // user.setPath(path);
//	               	session.save(user);
//	               	session.getTransaction().commit();
//	   
//	               	session.close(); 
//	               	user=null;
//	               	System.out.println(title);
//	               	model.addObject("add","record added");
//	      	           } 
//	           else
//	           {   System.out.println("duplicate");
//	           model.addObject("dup","duplicate record"); 
//	           }
//	           return model;  
//		
//	} 
//	// editing the existing questions
//	@RequestMapping(value = "/ques_edit", method = RequestMethod.POST)
//	public ModelAndView quesedit(HttpSession httpSession, @RequestParam Map<String,String> requestParams) {
//		String quesId = requestParams.get("id");
//		
//		 Session session =	sessionFactory.openSession();
//	       session.beginTransaction();
//	       
//	      ModelAndView model = new ModelAndView("quesedit");
//	         
//	       Query queryResult = session.createQuery("from Questions");
//	       java.util.List allUsers;
//	       String det,title,inp_format,cons,inp_case,op_case,path;
//	       int qid;
//	      qid=0;
//	     det=null;
//	      title=null;
//	      inp_format=null;
//	      cons=null;
//	      inp_case=null;
//	      op_case=null;
//	      path=null;
//	       allUsers = queryResult.list();
//	       int f;
//	       f=0;
//	       for (int i = 0; i < allUsers.size(); i++) {
//	    	   Questions user = (Questions) allUsers.get(i);
//	    	   qid=user.getQuesid();
//	    	   title=user.getTitle();
//	    	   cons=user.getConstraints();
//	    	   det=user.getDetail();
//	    	   inp_case=user.getInputtestcase();
//	    	   op_case=user.getOutputtestcase();
//	    	   path=user.getPath();
//	         
//	        		  
//	         if(quesId.equals(qid) ){
//		         f=1;
//		         break;
//	        
//	        }
//	         	         
//	       }
//	       if(f==1){
//		         model.addObject("qid",qid); 
//		         model.addObject("title",title); 
//		         model.addObject("detail",det); 
//		         model.addObject("constraints",cons); 
//		         model.addObject("inputcase",inp_case); 
//		         model.addObject("outputcase",op_case); 
//		         model.addObject("path",path); 
//		        
//	        }
//	         else
//	         {
//	        	  model.addObject("norec","no record found");  
//	         }
//	         
//	       return model;
//		
//	} 
//	//modify questions
//	@RequestMapping(value = "/modifyque", method = RequestMethod.POST)
//	public ModelAndView modify(HttpSession httpSession, @RequestParam Map<String,String> requestParams) {
//	    ModelAndView model = new ModelAndView("quesedit");
//					String quesid = requestParams.get("id");
//					String det,title,inp_format,cons,inp_case,op_case,path;
//					det=requestParams.get("detail");
//					title=requestParams.get("title");
//					cons=requestParams.get("cons");
//					inp_case=requestParams.get("inpcase");
//					op_case=requestParams.get("opcase");
//		 /*
//		  space for code for file upload
//		  after which path requsest parameter to be used
//		  
//		  */
//		// path=requestParams.get("");
//						Session session =	sessionFactory.openSession();
//						session.beginTransaction();
//	       
//	  
//	       Questions user= (Questions) session.get(Questions.class,quesid);
//	       				user.setQuesid(Integer.parseInt(quesid));
//	       				user.setDetail(det);
//	       				user.setConstraints(cons);
//	       				user.setInputtestcase(inp_case);
//	       				user.setOutputtestcase(op_case);
//	       				/*
//	       				 path to be set after the file upload code is written
//	       				 
//	       				   */
//	       				//user.setPath(path);
//	       					session.update(user);
//	       					session.getTransaction().commit();
//	       					session.close(); 
//	       						
//	       					
//	       					model.addObject("rec","record edited");
//	       					return model;
//	}
//	
//	
//}


/*
 * google login oauth commented for further use
 * 
 */


//@RequestMapping(value = "/registration", method = RequestMethod.POST)
//public ModelAndView registration(HttpSession httpSession, @RequestParam Map<String,String> requestParams) {
//	GoogleIdToken.Payload payLoad;
//	ModelAndView model=new ModelAndView("dashboard");
//	 String auth_token= requestParams.get("auth_token");
//	 String branch=requestParams.get("branch");
//	 String year=requestParams.get("year");
//	 String admno=requestParams.get("admno");
//	
//	 // code for fetching user data from google api
//	try {
//		payLoad = IdTokenVerifierAndParser.getPayload(auth_token);
//			String name = (String) payLoad.get("name");
//	        String email = payLoad.getEmail();
//	        avatar= (String) payLoad.get("picture");
//	        System.out.println("User name: " + name);
//	        System.out.println("User email: " + email);
//	        System.out.println("avatar :" + avatar);
//	        System.out.println("branch :" + branch);
//	        System.out.println("year :" + year);
//	        System.out.println("admission no :" + admno);
//	      userdet=new Userdet
//	        		(email,avatar,year,branch,name,admno);
//	        Session session = sessionFactory.openSession();
//			session.beginTransaction();
//			
//			session.save(userdet);
//			session.getTransaction().commit();
//			session.close();
//			model.addObject("name",name);
//			model.addObject("avatar",avatar);
//	        
//	} catch (Exception e) {
//		// TODO Auto-generated catch block 
//		e.printStackTrace();
//	}
//	
//	return model;
//	
//} 
/**
// * Controller, after completion of registration or login verification
// * 
// * @param httpSession
// * @param email
// * @return ModelAndView
// */
//@RequestMapping(value = "/dashboard", method = RequestMethod.POST)
//public ModelAndView login(HttpSession httpSession, @RequestParam ("email") String email){
//	httpSession.setAttribute("loggedinuser",email);
//	ModelAndView model=new ModelAndView("dashboard");//generating session for the logged in user
//	Session session =sessionFactory.openSession();
//	session.beginTransaction();
//	userdet = (Userdet) session.get(Userdet.class, email);
//	//String name=userdet.getName();
//	//String avatar=userdet.getAvatar();
//	//model.addObject("name",name);
//	model.addObject("avatar",avatar);
//	return model;
//
//}
//
///**
// * controller for first time logging user for registration
// * 
// * @param httpSession
// * @param requestParams
// * @return ModelAndView 
// */
//
/**
// *for login verification through google API 
// * @param httpSession
// * @param email
// * @return String
// */
///*MediaType.APPLICATION_JSON_VALUE return string*/
//@RequestMapping(value = "/loginverifier",
//		method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE) 
//public @ResponseBody String loginverify(HttpSession httpSession,
//		@RequestParam ("user_email") String email){
//Session session =sessionFactory.openSession();
//session.beginTransaction(); 
//
//userdet = (Userdet) session.get(Userdet.class, email);
//
//session.close();
//if(userdet != null)
//{
//	return "registered";
//}
//
//else 
//	return "new_user";
//
//}  
	 

