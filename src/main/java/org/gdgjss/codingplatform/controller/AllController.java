package org.gdgjss.codingplatform.controller;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Connection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.aspectj.weaver.patterns.TypePatternQuestions.Question;
import org.gdgjss.codingplatform.models.Codesave;
import org.gdgjss.codingplatform.models.Questions;
import org.gdgjss.codingplatform.models.Result;
import org.gdgjss.codingplatform.models.Userdet;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;

/**
 * this class contains all view controllers, maps view to service layer
 * 
 * @author suyash
 *
 */

@Controller
public class AllController {

	@Autowired
	private SessionFactory sessionFactory;
	private Userdet userdet;
	private Question question;

	// private String HACKERRANK_API_CREDENTIALS=
	// "hackerrank|1466488-1173|ece751e6f0df6c5c8fc1e8c3498da5c1b5d73f86";

	/**
	 * simple boot controller for application
	 * 
	 * @author tilhari
	 * @return
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView indexpage() {

		ModelAndView model = new ModelAndView("index");
		return model;
	}

	/**
	 * 
	 * controller for session logout
	 * 
	 * @author sarthak
	 * @param httpSession
	 * @return
	 */

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ModelAndView LogoutController(HttpSession httpSession) {
		httpSession.invalidate();
		ModelAndView model = new ModelAndView("index");
		model.addObject("invalid", "successfully logged out");
		return model;
	}

	/**
	 * 
	 * controller for user login
	 * 
	 * @author singhal
	 * @author sarthak
	 * @param httpSession
	 * @param requestParams
	 * @return
	 */

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView login(HttpSession httpSession, @RequestParam("id") String emailid,
			@RequestParam("pass") String password) {
		ModelAndView model;
		Session session = sessionFactory.openSession();
		userdet = (Userdet) session.get(Userdet.class, emailid);
		if (userdet != null) {
					if (userdet.getPassword().equals(password)) {
							httpSession.setAttribute("SESSION_email", userdet.getEmailid());
							httpSession.setAttribute("SESSION_teamname", userdet.getTeam_name());
							if((String) httpSession.getAttribute("SESSION_email")!=null){
								model = new ModelAndView("dashboard");
								model.addObject("TeamName", userdet.getTeam_name());
								List<Questions> ques = session.createCriteria(Questions.class).list();
								model.addObject("ques", ques);
				               }
							else{
								model = new ModelAndView("index");
								model.addObject("invalid", "LOG IN FIRST TO CONTINUE");
							}
			} else {
				model = new ModelAndView("index");
				model.addObject("invalid", "invalid details");
			}
		}

		else {
			model = new ModelAndView("index");
			model.addObject("norecord", "no record found");
		}
		session.close();
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
	public ModelAndView signup(@ModelAttribute("userdet") org.gdgjss.codingplatform.models.Userdet userdet) {
		Session session = sessionFactory.openSession();
		ModelAndView model = new ModelAndView("index");
		if (session.get(Userdet.class, userdet.getEmailid()) == null) {
			Result result = new Result(userdet.getEmailid(), userdet.getTeam_name());
			Codesave codesave = new Codesave(userdet.getEmailid());
			session.beginTransaction();
			session.save(codesave);
			session.save(userdet);
			session.save(result);
			session.getTransaction().commit();
			model.addObject("invalid", "Successfully registered, login to proceed!");

		} else
			model.addObject("invalid", "This email is already registered.");
		session.close();
		return model;

	}
	
	
	
	
	
	
	
	
	
/*	https://market.mashape.com/ideas2it/hacker-earth#api-to-run-program-code

*
*
*
*/	
	@RequestMapping(value = "/api", method = RequestMethod.POST)

	public ModelAndView submission2(HttpSession httpSession, @RequestParam Map<String, String> requestParams)
			throws IOException, JSONException {
		
		if((String) httpSession.getAttribute("SESSION_email")!=null){
			
			
		String language = requestParams.get("lang");
		String code = requestParams.get("source");
		String quesid = requestParams.get("qid");;
		String lang=language;
		if (language.equals("CPP")) {
			lang = "C";
		}
		String inputpath = "", outputpath = "", y = "", z = "";
		String codetobesaved ="ques" + quesid + "_" + lang + "_code";
		
		/*
		 * to encode source code in utf 8 , as java uses by default utf-16
		 */
		String urlencode = URLEncoder.encode(code, "UTF-8");
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Questions ques = (Questions) session.get(Questions.class, Integer.parseInt(quesid));
		inputpath = ques.getInputfilepath();
		outputpath = ques.getOutputfilepath();
		

		session.close();
		
		
		System.out.println("-----------------------code is------------------");
		System.out.println(code);
		System.out.println("-----------------------lang is------------------");
		System.out.println(language);
		
		FileReader fr = new FileReader(inputpath);
		int i;
		while ((i = fr.read()) != -1)
			y = y + ((char) i);
		fr.close();						
		System.out.println("Input file---- \n" + y);
		String respLine="";
		System.out.println("length of input file--->>>> "+ y.length());
		System.out.println("length of code file--->>>> "+ code.length());
		
		try{
			
		com.mashape.unirest.http.HttpResponse<JsonNode> response = Unirest.post("https://ideas2it-hackerearth.p.mashape.com/run/")
				.header("X-Mashape-Key", "aijEZvSbU0mshvNAiU0dUAq8wXzqp18BF1ijsnKwLt7Kz3MESA")
				.header("Content-Type", "application/x-www-form-urlencoded")
				.header("Accept", "application/json")
				.field("async", 0)
				.field("client_secret", "bb6e3803ef6f2b827cd42a654bfce001f20ae883")
				.field("input", y)
				.field("lang", language)
				.field("memory_limit", 262144)
				.field("source", code)
				.field("time_limit", 10)
				.asJson();
				System.out.println("Response here ------------------------------------------");
				int responseCode = response.getStatus();
				if (responseCode == 403 || responseCode == 500 || responseCode == 504 ) {
					ModelAndView model = new ModelAndView("Errorpage");
					model.addObject("code", code);
					model.addObject("msg","INTERNET PROBLEM GO BACK AND SUBMIT AGAIN");
					model.addObject("TeamName", (String) httpSession.getAttribute("SESSION_teamname"));
					model.addObject("lang",language);
					return model;
				}
				System.out.println(response.getStatus());
				System.out.println(response.getStatusText());
				System.out.println(response.getBody());
				
				BufferedReader in = new BufferedReader(new InputStreamReader(response.getRawBody()));

				respLine = in.readLine();
				System.out.println("JSON response --->>>");
				System.out.println(respLine);
				
						
				}
				catch(Exception e)
				{

					System.out.println("UNIREST ERROR BLOCK");
					ModelAndView model=new ModelAndView("Errorpage");
					model.addObject("msg","INTERNET PROBLEM  GO BACK AND SUBMIT AGAIN");
					model.addObject("lang",language);
					model.addObject("code",code);
					return model;

				}		

			String message = "", stdOut = "", status = "", htmlOutput = "";
		try {
			JSONObject json = new JSONObject(respLine);

			if (json.has("run_status")) {

				JSONObject resultObject = json.getJSONObject("run_status");
				status = resultObject.getString("status");
				htmlOutput = resultObject.getString("output_html");

				if (resultObject.has("output"))
					stdOut = resultObject.getString("output");

			}

			if (json.has("compile_status")) {
				message = json.getString("compile_status");

			}
			else {
				message = "not avilable";
			}

		} catch (Exception e) {
			System.out.println("ERROR in /API");
		}
		Gson gsons = new Gson();
		String jsonapi = gsons.toJson(stdOut);
		String out_api = "[" + jsonapi + "]";

		
		System.out.println(outputpath); // output file path
		StringBuilder sb = new StringBuilder();
		BufferedReader file = new BufferedReader(new FileReader(outputpath));

		while ((z = file.readLine()) != null) {
			sb.append(z);
			sb.append("\n");
		}


		Gson gson = new Gson();
		String jsons = gson.toJson(sb);
		String out = "[" + jsons + "]";
		System.out.println(out);

		String verify;
		if (out.equals(out_api)) {
			System.out.println("output matched");
			verify = "CORRECT ANSWER";

			System.out.println("-----------------------------------calculation begins here-----------------------");
			session = sessionFactory.openSession();

			/**
			 * @author suyash
			 * 
			 *         code for leaderboard and marking scheme
			 */
			int lengthOfCode = code.length();
			
			
			String correspondingQuesMark = "ques" + quesid + "_" + lang;
			String correspondingQuesLength = "ques" + quesid + "_" + lang + "_l";
			String emailid = (String) httpSession.getAttribute("SESSION_email");
		
			String hql_current_user_length = "SELECT " + correspondingQuesLength + " FROM Result R WHERE R.email = '"
					+ emailid + "'";
			
			Query query = session.createQuery(hql_current_user_length);
			List r = query.list();
			int current_user_length = (int) r.get(0);
			System.out.println(lengthOfCode);
			
			if (lengthOfCode < current_user_length) {
				
				String hql_min_length = "SELECT min(" + correspondingQuesLength + ") FROM Result R";
				query = session.createQuery(hql_min_length);
				r = query.list();
				int min_length = (int) r.get(0);
				System.out.println("min_length --->> " + min_length);
				String hql_update_length = "UPDATE Result R set " + correspondingQuesLength + " = " + lengthOfCode
						+ " WHERE R.email = '" + emailid + "'";
				query = session.createQuery(hql_update_length);
				int effected_rows = query.executeUpdate();
				System.out.println("updated length --->> " + lengthOfCode + " effected row ---->> " + effected_rows);
				if (lengthOfCode < min_length) {
					min_length = lengthOfCode;
					String fetch_corresponding_marks_length = "SELECT email , " + correspondingQuesMark + " , "
							+ correspondingQuesLength + " , total FROM Result R";
					query = session.createQuery(fetch_corresponding_marks_length);
					List<Object> re = (List<Object>) query.list();
					Iterator itr = re.iterator();
					System.out.println("DATA FROM TABLE--->>");
					while (itr.hasNext()) {
						Object[] obje = (Object[]) itr.next();
						// now you have one array of Object for each row
						String idemail = String.valueOf(obje[0]);
						Integer marks = Integer.parseInt(String.valueOf(obje[1]));
						Integer length = Integer.parseInt(String.valueOf(obje[2]));
						Integer total = Integer.parseInt(String.valueOf(obje[3]));
						System.out.println(idemail + "  " + marks + "  " + length + " " + total);
						if (length < 1000000) {
							
							int updated_marks = 100 - ((length - min_length)/2);
							if (updated_marks < 20)
								updated_marks = 20;
							total = total - marks + updated_marks;
							String hql_update_marks = "UPDATE Result R set " + correspondingQuesMark + " = "
									+ updated_marks + " WHERE R.email = '" + idemail + "'";
							query = session.createQuery(hql_update_marks);
							int effected_rows_marks = query.executeUpdate();
							System.out.println(
									"updated marks of  --->> " + effected_rows_marks + " marks is   " + updated_marks);
							String hql_update_total_marks = "UPDATE Result R set R.total = " + total
									+ " WHERE R.email = '" + idemail + "'";
							query = session.createQuery(hql_update_total_marks);
							int hql_update_totalmarks = query.executeUpdate();

						}

					}
				} 
				else {
					String fetch_corresponding_marks_length = "SELECT " + correspondingQuesMark + " , "
							+ correspondingQuesLength + " , total FROM Result R where R.email= '" + emailid + "'";
					query = session.createQuery(fetch_corresponding_marks_length);
					List<Object> re = (List<Object>) query.list();
					Iterator itr = re.iterator();

					while (itr.hasNext()) {
						Object[] obje = (Object[]) itr.next();
						// now you have one array of Object for each row
						Integer marks = Integer.parseInt(String.valueOf(obje[0]));
						Integer length = Integer.parseInt(String.valueOf(obje[1]));
						Integer total = Integer.parseInt(String.valueOf(obje[2]));
						System.out.println(marks + "  " + length);
						if (length < 1000000) {
							int updated_marks = 100 - ((length - min_length)/2);
							if (updated_marks < 20)
								updated_marks = 20;
							total = total - marks + updated_marks;
							System.out.println("MArks to be updated-->>>  " + updated_marks);
							String hql_update_marks = "UPDATE Result R set " + correspondingQuesMark + " = "
									+ updated_marks + " WHERE R.email = '" + emailid + "'";
							query = session.createQuery(hql_update_marks);
							int effected_rows_marks = query.executeUpdate();
							String hql_update_total_marks = "UPDATE Result R set R.total = " + total
									+ " WHERE R.email = '" + emailid + "'";
							query = session.createQuery(hql_update_total_marks);
							int hql_update_totalmarks = query.executeUpdate();
							
						}
					}

				}
			}
			
			
			
			/**
			 * under observation
			 */
			//session.getTransaction().commit();
			session.close();

		} else {
			System.out.println("outputs not matched");
			verify = "WRONG ANSWER";
		}

		/**
		 * 
		 * sending data to Questionpage
		 */

		switch (status) {
		case "AC":
			status = "COMPILED SUCCESSFULLY";
			break;
		case "CE":
			status = "COMPILATION ERROR";
			break;
		case "TLE":
			status = "TIME LIMIT EXCEED";
			break;
		case " ":
			status = "RUNTIME ERROR";
			break;
		case "RE":
			status = "RUNTIME ERROR";
			break;
			
		case "MLE":
			status = "MEMORY LIMIT EXCEEDED";
			break;
		}
    
		System.out.println(status);
		
		System.out.println(message);
		if(message.equals("OK"))
		{
			
			System.out.println("-1");
			if(status.equals("COMPILED SUCCESSFULLY"))
				
			{  
				
				System.out.println("0");
				if(verify.equals("CORRECT ANSWER")){
				
					
					System.out.println("1");
						ModelAndView 	model=new ModelAndView("ResultPage");
						model.addObject("TeamName", (String) httpSession.getAttribute("SESSION_teamname"));
						model.addObject("code",code);
						model.addObject("colour","green");
						model.addObject("status",status);									
						model.addObject("verify",verify);
						model.addObject("lang",language);
						
							return model;	
			           }
			
				else if(  verify.equals("WRONG ANSWER"))
			          {
					System.out.println("2");
						ModelAndView  model=new ModelAndView("ResultPage");
			    	 	model.addObject("TeamName", (String) httpSession.getAttribute("SESSION_teamname"));
			    	 	model.addObject("code",code);
			    	 	model.addObject("colour","red");
			    	 	model.addObject("status",status);
			    	 	model.addObject("verify",verify);
			    	 	model.addObject("msg","GO BACK AND SUBMIT AGAIN !!!");	
			    	 	model.addObject("lang",language);
			    	 	return model;
						}
			}
			else if(status.equals("COMPILATION ERROR"))
			{
				
				System.out.println("3");
				ModelAndView  model=new ModelAndView("ResultPage");
	    	 	model.addObject("TeamName", (String) httpSession.getAttribute("SESSION_teamname"));
	    	 	model.addObject("code",code);
	    	 	model.addObject("colour","red");
	    	 	model.addObject("status",status);
	    	 	model.addObject("msg","GO BACK AND SUBMIT AGAIN !!!");	
	    	 	model.addObject("lang",language);
	    	 	return model;
	    	 	
			}
			
			else if (status.equals("TIME LIMIT EXCEED")){
				
				
				System.out.println("4");
				ModelAndView model=new ModelAndView("ResultPage");
	    	 	model.addObject("TeamName", (String) httpSession.getAttribute("SESSION_teamname"));
	    	 	model.addObject("code",code);
	    	 	model.addObject("colour","blue");
	    	 	model.addObject("status",status);
	    	 	model.addObject("msg","GO BACK AND SUBMIT AGAIN !!!");	
	    	 	model.addObject("lang",language);
	    	 	return model;
			}
			
			else if(status.equals("RUNTIME ERROR")){
				
				System.out.println("5");
				ModelAndView model=new ModelAndView("ResultPage");
	    	 	model.addObject("TeamName", (String) httpSession.getAttribute("SESSION_teamname"));
	    	 	model.addObject("code",code);
	    	 	model.addObject("colour","orange");
	    	 	model.addObject("status",status);
	    	 	model.addObject("msg","GO BACK AND SUBMIT AGAIN !!!");	
	    	 	model.addObject("lang",language);
	    	 	return model;
			}
			
			
	         else if(status.equals("MEMORY LIMIT EXCEEDED")){
				
				System.out.println("9");
				ModelAndView model=new ModelAndView("ResultPage");
	    	 	model.addObject("TeamName", (String) httpSession.getAttribute("SESSION_teamname"));
	    	 	model.addObject("code",code);
	    	 	model.addObject("colour","orange");
	    	 	model.addObject("status",status);
	    	 	model.addObject("lang",language);
	    	 	model.addObject("msg","GO BACK AND SUBMIT AGAIN !!!");	
	    	 	return model;
	         }
			
	         else{
	        	 System.out.println("11");
					ModelAndView model=new ModelAndView("ResultPage");
		    	 	model.addObject("TeamName", (String) httpSession.getAttribute("SESSION_teamname"));
		    	 	model.addObject("code",code);
		    	 	model.addObject("colour","orange");
		    	 	model.addObject("status",status);
		    	 	model.addObject("lang",language);
		    	 	model.addObject("msg","GO BACK AND SUBMIT AGAIN !!!");	
		    	 	return model;
	         }
           }
		
		else{
			
			 if(status.equals("COMPILATION ERROR"))
			{
				
				System.out.println("6");
				ModelAndView  model=new ModelAndView("ResultPage");
	    	 	model.addObject("TeamName", (String) httpSession.getAttribute("SESSION_teamname"));
	    	 	model.addObject("code",code);
	    	 	model.addObject("colour","red");
	    	 	model.addObject("status",status);
	    	 	model.addObject("msg","GO BACK AND SUBMIT AGAIN !!!");	
	    	 	model.addObject("lang",language);
	    	 	return model;
	    	 	
			}
			
			else if (status.equals("TIME LIMIT EXCEED")){
				
				
				System.out.println("7");
				ModelAndView model=new ModelAndView("ResultPage");
	    	 	model.addObject("TeamName", (String) httpSession.getAttribute("SESSION_teamname"));
	    	 	model.addObject("code",code);
	    	 	model.addObject("colour","blue");
	    	 	model.addObject("status",status);
	    	 	model.addObject("lang",language);
	    	 	model.addObject("msg","GO BACK AND SUBMIT AGAIN !!!");	
	    	 	return model;
			}
			
			else if(status.equals("RUNTIME ERROR")){
				
				System.out.println("8");
				ModelAndView model=new ModelAndView("ResultPage");
	    	 	model.addObject("TeamName", (String) httpSession.getAttribute("SESSION_teamname"));
	    	 	model.addObject("code",code);
	    	 	model.addObject("colour","orange");
	    	 	model.addObject("status",status);
	    	 	model.addObject("lang",language);
	    	 	model.addObject("msg","GO BACK AND SUBMIT AGAIN !!!");	
	    	 	return model;
			}
			 
				else if(status.equals("MEMORY LIMIT EXCEEDED")){
				
				System.out.println("9");
				ModelAndView model=new ModelAndView("ResultPage");
	    	 	model.addObject("TeamName", (String) httpSession.getAttribute("SESSION_teamname"));
	    	 	model.addObject("code",code);
	    	 	model.addObject("colour","orange");
	    	 	model.addObject("status",status);
	    	 	model.addObject("lang",language);
	    	 	model.addObject("msg","GO BACK AND SUBMIT AGAIN !!!");	
	    	 	return model;
			}
				else if(status.equals(null)){
				
				System.out.println("10");
				ModelAndView model=new ModelAndView("Errorpage");
	    	 	model.addObject("TeamName", (String) httpSession.getAttribute("SESSION_teamname"));
	    	 	model.addObject("code",code);
	    	 	model.addObject("colour","orange");
	    	 	model.addObject("msg","UNEXPECTED ERROR PROBLEM GO BACK AND SUBMIT AGAIN !!!");
	    	 	model.addObject("lang",language);
	    	 	return model;
			}
		  }
		}
		
		else
		{
			ModelAndView model=new ModelAndView("index");
			model.addObject("invalid","KINDLY LOGIN FIRST");
			return model;
		}
		return null;
	}
	
	


	@RequestMapping(value = "/leaderboard", method = RequestMethod.GET)
	public ModelAndView leaderboard(HttpSession httpSession) {
		ModelAndView model = new ModelAndView("leaderboard");
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Criteria c = session.createCriteria(Result.class);
		c.addOrder(Order.desc("total"));
		List<Result> result = c.list();
		model.addObject("resultRows", result);
		model.addObject("TeamName", (String) httpSession.getAttribute("SESSION_teamname"));
		return model;

	}

	
	@RequestMapping(value = "/addques", method = RequestMethod.POST)
	public ModelAndView addques(@ModelAttribute("question") org.gdgjss.codingplatform.models.Questions question) {
		Session session = sessionFactory.openSession();
		ModelAndView model = new ModelAndView("adminpannel");
		if (session.get(Questions.class, question.getQuesid()) == null) {
			session.beginTransaction();
			session.save(question);
			session.getTransaction().commit();
			session.close();
			System.out.println(question.getQuesid());
			model.addObject("invalid", "Successfully added question");

		} else

			model.addObject("invalid", "This question is alredy added");

		return model;

	}

	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public ModelAndView admin(HttpSession httpSession) {
		ModelAndView model = new ModelAndView("admin");
		return model;

	}

	/**
	 * code for editor
	 * 
	 * @param httpSession
	 * @return
	 */
	@RequestMapping(value = "/ques", method = RequestMethod.GET)
	public ModelAndView ques(HttpSession httpSession, @RequestParam Map<String, String> requestParams)
			throws IOException, JSONException {
		String team_name = (String) httpSession.getAttribute("SESSION_teamname");
		String email = (String) httpSession.getAttribute("SESSION_email");
		ModelAndView model;
		if (email != null) {
			model = new ModelAndView("Quespage");
			String id = "";
			String b = "";
			String Question = "";
			String Constraint = "";
			String InputFormat = "";
			String OutPutFormat;
			String title;
			String SampleTestCase = "";
			id = requestParams.get("id");
			Session session = sessionFactory.openSession();
			Questions a = (Questions) session.get(Questions.class, Integer.parseInt(id));
			session.close();
			Question = a.getDetail();
			Constraint = a.getConstraints();
			InputFormat = a.getInputformat();
			SampleTestCase = a.getSampletestcase();
			OutPutFormat = a.getOutputformat();
			title = a.getTitle();

			model.addObject("quesid", id);
			model.addObject("Title", title);
			model.addObject("Question", Question);
			model.addObject("Constraint", Constraint);
			model.addObject("InputFormat", InputFormat);
			model.addObject("SampleTestCase", SampleTestCase);
			model.addObject("Teamname", team_name);
			model.addObject("email", email);
			model.addObject("OutputFormat", OutPutFormat);
			model.addObject("TeamName", team_name);
			System.out.println(Question);
		} else {
			model = new ModelAndView("index");
			model.addObject("invalid", "log in first to continue");
		}

		return model;

	}

	@RequestMapping(value = "/adminverify", method = RequestMethod.POST)
	public ModelAndView adminverify(HttpSession httpSession, @RequestParam Map<String, String> requestParams) {
		ModelAndView model;
		String id = requestParams.get("id");
		String pass = requestParams.get("pass");
		if (id.equals("gdgadmin") && pass.equals("gdgpassword")) {
			model = new ModelAndView("adminpannel");
			model.addObject("name", id);
		} else {
			return new ModelAndView("err");
		}
		return model;
	}

	/**
	 * controller for rules page
	 * 
	 * @author sarthak
	 * @return
	 */

	@RequestMapping(value = "/rules", method = RequestMethod.GET)
	public ModelAndView RulesPage(HttpSession httpSession) {

		ModelAndView model = new ModelAndView("rulespage");
		model.addObject("TeamName", (String) httpSession.getAttribute("SESSION_teamname"));
		return model;
	}

	/**
	 * controller for dashboard to view ques list
	 * 
	 * @author sarthak
	 * @return
	 */
	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public ModelAndView dashboard(HttpSession httpSession) {
		ModelAndView model;
		Session session = sessionFactory.openSession();
		if ((String) httpSession.getAttribute("SESSION_email") != null) {
			model = new ModelAndView("dashboard");
			model.addObject("TeamName", (String) httpSession.getAttribute("SESSION_teamname"));
			List<Questions> ques = session.createCriteria(Questions.class).list();
			model.addObject("ques", ques);

		} else {
			model = new ModelAndView("index");
			model.addObject("invalid", "LOG IN FIRST TO CONTINUE");
		}
		return model;
	}

	/**
	 * temporary controller for error page
	 * 
	 * @author sarthak
	 * @return
	 */
	@RequestMapping(value = "/errorpage", method = RequestMethod.GET)
	public ModelAndView ErrorPage(HttpSession httpSession) {

		ModelAndView model = new ModelAndView("Errorpage");
		model.addObject("TeamName", (String) httpSession.getAttribute("SESSION_teamname"));
		return model;
	}

	/**
	 * temporary controller for result page
	 * 
	 * @author sarthak
	 * @return
	 */
	@RequestMapping(value = "/resultpage", method = RequestMethod.GET)
	public ModelAndView ResultPage(HttpSession httpSession) {

		ModelAndView model = new ModelAndView("ResultPage");
		model.addObject("TeamName",(String) httpSession.getAttribute("SESSION_teamname"));
		return model;
	}
	
	/**
	 * controller for handling get request to api
	 * @author sarthak
	 * @param httpSession
	 * @return
	 */
	@RequestMapping(value = "/api", method = RequestMethod.GET)
	public ModelAndView API(HttpSession httpSession) {

		ModelAndView model = new ModelAndView("errorpage2");
		model.addObject("TeamName",(String) httpSession.getAttribute("SESSION_teamname"));
	
		return model;
	}
	
}