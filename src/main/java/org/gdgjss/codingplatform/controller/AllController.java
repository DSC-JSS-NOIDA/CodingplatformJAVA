package org.gdgjss.codingplatform.controller;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.aspectj.weaver.patterns.TypePatternQuestions.Question;
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

import com.google.api.client.http.HttpResponse;
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
				model = new ModelAndView("dashboard");
				model.addObject("TeamName", userdet.getTeam_name());
				List<Questions> ques = session.createCriteria(Questions.class).list();
				model.addObject("ques", ques);
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
			session.beginTransaction();
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
		String quesid = requestParams.get("qid");
		System.out.println(language);
		System.out.println(code);
		String inputpath = "", outputpath = "", y = "", z = "";

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
		System.out.println("Input file------------------------------------- \n" + y);
		String respLine="";
		try{
			
			System.out.println(" market.mashape.com wala---------------->>>>");
		com.mashape.unirest.http.HttpResponse<JsonNode> response = Unirest.post("https://ideas2it-hackerearth.p.mashape.com/run/")
				.header("X-Mashape-Key", "n4bl58t4OrmshTdRKLfrUIR3f2V7p1GpWVXjsnbhJnPZvPOpoE")
				.header("Content-Type", "application/x-www-form-urlencoded")
				.header("Accept", "application/json")
				.field("async", 0)
				.field("client_secret", "285d57d6ef8550023946fb0e7cbb50377fb464a7")
				.field("input", y)
				.field("lang", language)
				.field("memory_limit", 262144)
				.field("source", code)
				.field("time_limit", 10)
				.asJson();
				System.out.println("Response here ------------------------------------------");
				int responseCode = response.getStatus();
				if (responseCode == 403 || responseCode == 500 ) {
					ModelAndView model = new ModelAndView("Errorpage");
					model.addObject("code", code);
					model.addObject("msg","INTERNET PROBLEM TRY REFRESHING PAGE");
					model.addObject("TeamName", (String) httpSession.getAttribute("SESSION_teamname"));
					return model;
				}
				else if(responseCode == 504){
					ModelAndView model = new ModelAndView("Errorpage");
					model.addObject("code", code);
					model.addObject("msg","YOUR CODE  EXCEEDED MAX CHARACTER LIMIT  TRY AGAIN!!!");
					model.addObject("TeamName", (String) httpSession.getAttribute("SESSION_teamname"));
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

		System.out.println(message);
		System.out.println(status);
		System.out.println(stdOut);
		System.out.println(htmlOutput);
		System.out.println(out_api);

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
			String lang = language;
			if (language.equals("CPP")) {
				lang = "C";
			}
			String correspondingQuesMark = "ques" + quesid + "_" + lang;
			String correspondingQuesLength = "ques" + quesid + "_" + lang + "_l";
			String emailid = (String) httpSession.getAttribute("SESSION_email");
			// chiki
			String hql_current_user_length = "SELECT " + correspondingQuesLength + " FROM Result R WHERE R.email = '"
					+ emailid + "'";
			Query query = session.createQuery(hql_current_user_length);
			List r = query.list();
			int current_user_length = (int) r.get(0);
			System.out.println("current_user_length --->> " + current_user_length);
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
							int updated_marks = 100 - (length - min_length);
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
				} else {
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
							int updated_marks = 100 - (length - min_length);
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
	    	 	return model;
	    	 	
			}
			
			else if (status.equals("TIME LIMIT EXCEED")){
				
				
				System.out.println("4");
				ModelAndView model=new ModelAndView("ResultPage");
	    	 	model.addObject("TeamName", (String) httpSession.getAttribute("SESSION_teamname"));
	    	 	model.addObject("code",code);
	    	 	model.addObject("colour","blue");
	    	 	model.addObject("status",status);
	    	 	return model;
			}
			
			else if(status.equals("RUNTIME ERROR")){
				
				System.out.println("5");
				ModelAndView model=new ModelAndView("ResultPage");
	    	 	model.addObject("TeamName", (String) httpSession.getAttribute("SESSION_teamname"));
	    	 	model.addObject("code",code);
	    	 	model.addObject("colour","orange");
	    	 	model.addObject("status",status);
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
	    	 	return model;
	    	 	
			}
			
			else if (status.equals("TIME LIMIT EXCEED")){
				
				
				System.out.println("7");
				ModelAndView model=new ModelAndView("ResultPage");
	    	 	model.addObject("TeamName", (String) httpSession.getAttribute("SESSION_teamname"));
	    	 	model.addObject("code",code);
	    	 	model.addObject("colour","blue");
	    	 	model.addObject("status",status);
	    	 	return model;
			}
			
			else if(status.equals("RUNTIME ERROR")){
				
				System.out.println("8");
				ModelAndView model=new ModelAndView("ResultPage");
	    	 	model.addObject("TeamName", (String) httpSession.getAttribute("SESSION_teamname"));
	    	 	model.addObject("code",code);
	    	 	model.addObject("colour","orange");
	    	 	model.addObject("status",status);
	    	 	return model;
			}
				else if(status.equals(null)){
				
				System.out.println("9");
				ModelAndView model=new ModelAndView("Errorpage");
	    	 	model.addObject("TeamName", (String) httpSession.getAttribute("SESSION_teamname"));
	    	 	model.addObject("code",code);
	    	 	model.addObject("colour","orange");
	    	 	model.addObject("msg","UNEXPECTED ERROR TRY AGAIN!!!");
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
	
	
/*
ORIGINAL CODE
*
*
*
*
*
*
*
*/	/**
	 * 
	 * HackerEarth API controller
	 * 
	 * @author suyash tilhari
	 * @param httpSession
	 * @param requestParams
	 * @throws IOException
	 * @throws JSONException
	 */
	@RequestMapping(value = "/api2", method = RequestMethod.POST)

	public ModelAndView submission(HttpSession httpSession, @RequestParam Map<String, String> requestParams)
			throws IOException, JSONException {
		
		if((String) httpSession.getAttribute("SESSION_email")!=null){
		String language = requestParams.get("lang");
		String code = requestParams.get("source");
		String quesid = requestParams.get("qid");
		System.out.println(language);
		System.out.println(code);
		String inputpath = "", outputpath = "", y = "", z = "";

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

		/**
		 * Commented code is a way to read file as it is from its source, but
		 * need to include \n by self more efficient way
		 * 
		 * @author tilhari
		 */
		/*
		 * BufferedReader br = new BufferedReader(new FileReader(inputpath));
		 * int x; while ((x = br.read()) != -1) { if(y!="") y=y+"\n"+x; else
		 * y=y+x; }
		 */

		/**
		 * This is also a way to read file as it is from its source, but is
		 * currently without buffer so less efficient
		 * 
		 * @author tilhari
		 */
		FileReader fr = new FileReader(inputpath);
		int i;
		while ((i = fr.read()) != -1)
			y = y + ((char) i);
		fr.close();

		/**
		 * Switch upcoming code lines for toggle HackerRank and hackerEarth API
		 *
		 * HackerRank API is in its beta version, does not support TLE,
		 * complexity etc. No terms and condition also not specified hence no
		 * guarantee
		 *
		 * @author tilhari
		 */
		String url = "https://api.hackerearth.com/v3/code/run/";
		// String url = "http://api.hackerrank.com/checker/submission.json";

		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// add request header
		con.setRequestMethod("POST");
		con.setRequestProperty("ENCODING", "UTF-8");
		con.setRequestProperty("User-Agent", "chrome");
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

		/**
		 * @author tilhari url parameters for hackerrank api
		 */
		// String urlParameters =
		// "source="+urlencode+"&lang="+language+"&testcases=["+y+"]&api_key=hackerrank|1466488-1173|ece751e6f0df6c5c8fc1e8c3498da5c1b5d73f86";

		System.out.println("Input file--- \n" + y);

		/**
		 * @author tilhari url parameters for hackerearth api
		 * 
		 *         add other available parameter for TLE, size etc etc
		 */
		String urlParameters = "source=" + urlencode + "&lang=" + language + "&input=" + y
				+ "&client_secret=d442f2d462c5bcc3fd372f79f878f91bb35ceb43"+"&time_limit=5&memory_limit=262144&async=0";

		// Send post request
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(urlParameters);
		wr.flush();
		wr.close();

		/**
		 * @author suyash reading
		 */
		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'POST' request to URL : " + url);
		System.out.println("Post parameters : " + urlParameters);
		System.out.println("Response Code : " + responseCode);

		/**
		 * @author singhal code for exception handling of incomplete post
		 *         request due to internet connectivity problem
		 * @author suyash due to bad request problem also.
		 */
		if (responseCode == 403 || responseCode == 500 || responseCode == 504) {
			ModelAndView model = new ModelAndView("Errorpage");
			model.addObject("code", code);
			return model;
		}

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));

		String respLine = in.readLine();
		System.out.println("JSON response --->>>");
		System.out.println(respLine);
		String message = "", stdOut = "", status = "", htmlOutput = "";

		/*
		 * use try catch in this code below to prevent the exception error
		 * 
		 */

		/**
		 * @author suyash TODO: println adds \n at end of last output line for
		 *         JAVA language, so test case should also have \n at end for
		 *         correct match, but this can be a problem in case of other
		 *         language. so some corner cases need to be considered while
		 *         testing on different languages OR different test cases can be
		 *         made for different languages.........
		 */

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
				// JSONObject
				// compileObject=json.getJSONObject("compile_status");
				message = json.getString("compile_status");

			}
			// if(json.has("result"))
			// { JSONObject resultObject=json.getJSONObject("result");
			// message=resultObject.getString("message");
			// stdOut=resultObject.getString("stdout");
			//
			// }

			else {
				message = "not avilable";
			}

		} catch (Exception e) {
			System.out.println("ERROR in /API");
		}

		/*
		 * api output converted to json
		 */

		Gson gsons = new Gson();
		String jsonapi = gsons.toJson(stdOut);
		String out_api = "[" + jsonapi + "]";

		System.out.println(message);
		System.out.println(status);
		System.out.println(stdOut);
		System.out.println(htmlOutput);
		System.out.println(out_api);

		System.out.println(outputpath); // output file path

		/*
		 * Reading OutPut text case file using BufferReader
		 */

		// FileReader file=new FileReader(outputpath);
		// int j;
		// while((j=file.read())!=-1)
		// z=z+((char)j);
		// file.close();
		// System.out.println("output path--- \n"+z);
		//
		/*
		 * code to read the output file from the path provided from above code
		 * by using buffer reader
		 */

		StringBuilder sb = new StringBuilder();
		BufferedReader file = new BufferedReader(new FileReader(outputpath));

		while ((z = file.readLine()) != null) {
			// Printing out each line in the file
			sb.append(z);
			sb.append("\n");
		}

		/*
		 * code for converting output text file to json for use with hackerEarth
		 * use API
		 */

		Gson gson = new Gson();
		String jsons = gson.toJson(sb);
		String out = "[" + jsons + "]";
		System.out.println(out);

		/*
		 * code to check the output of api with text file
		 * 
		 */
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
			String lang = language;
			if (language.equals("CPP")) {
				lang = "C";
			}
			String correspondingQuesMark = "ques" + quesid + "_" + lang;
			String correspondingQuesLength = "ques" + quesid + "_" + lang + "_l";
			String emailid = (String) httpSession.getAttribute("SESSION_email");
			// chiki
			String hql_current_user_length = "SELECT " + correspondingQuesLength + " FROM Result R WHERE R.email = '"
					+ emailid + "'";
			Query query = session.createQuery(hql_current_user_length);
			List r = query.list();
			int current_user_length = (int) r.get(0);
			System.out.println("current_user_length --->> " + current_user_length);
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
							int updated_marks = 100 - (length - min_length);
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
				} else {
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
							int updated_marks = 100 - (length - min_length);
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
			status = "NO COMPILATION ERROR";
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
		}
    
		System.out.println(status);
		
		System.out.println(message);
		if(message.equals("OK"))
		{
			
			System.out.println("-1");
			if(status.equals("NO COMPILATION ERROR"))
				
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
						model.addObject("stdout","YOUR OUTPUT IS" + " " + stdOut);
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
			    	 	model.addObject("stdout","YOUR OUTPUT IS" + " " + stdOut);
			    	 	model.addObject("verify",verify);
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
	    	 	return model;
	    	 	
			}
			
			else if (status.equals("TIME LIMIT EXCEED")){
				
				
				System.out.println("4");
				ModelAndView model=new ModelAndView("ResultPage");
	    	 	model.addObject("TeamName", (String) httpSession.getAttribute("SESSION_teamname"));
	    	 	model.addObject("code",code);
	    	 	model.addObject("colour","blue");
	    	 	model.addObject("status",status);
	    	 	return model;
			}
			
			else if(status.equals("RUNTIME ERROR")){
				
				System.out.println("5");
				ModelAndView model=new ModelAndView("ResultPage");
	    	 	model.addObject("TeamName", (String) httpSession.getAttribute("SESSION_teamname"));
	    	 	model.addObject("code",code);
	    	 	model.addObject("colour","orange");
	    	 	model.addObject("status",status);
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
	    	 	return model;
	    	 	
			}
			
			else if (status.equals("TIME LIMIT EXCEED")){
				
				
				System.out.println("7");
				ModelAndView model=new ModelAndView("ResultPage");
	    	 	model.addObject("TeamName", (String) httpSession.getAttribute("SESSION_teamname"));
	    	 	model.addObject("code",code);
	    	 	model.addObject("colour","blue");
	    	 	model.addObject("status",status);
	    	 	return model;
			}
			
			else if(status.equals("RUNTIME ERROR")){
				
				System.out.println("8");
				ModelAndView model=new ModelAndView("ResultPage");
	    	 	model.addObject("TeamName", (String) httpSession.getAttribute("SESSION_teamname"));
	    	 	model.addObject("code",code);
	    	 	model.addObject("colour","orange");
	    	 	model.addObject("status",status);
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

	/*
	 * code for admin login
	 */

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
		if (id.equals("gdg") && pass.equals("gdg")) {
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
	public ModelAndView RulesPage() {

		ModelAndView model = new ModelAndView("rulespage");
		model.addObject("TeamName", userdet.getTeam_name());
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
	public ModelAndView ErrorPage() {

		ModelAndView model = new ModelAndView("Errorpage");
		model.addObject("TeamName", userdet.getTeam_name());
		return model;
	}

	/**
	 * temporary controller for result page
	 * 
	 * @author sarthak
	 * @return
	 */
	@RequestMapping(value = "/resultpage", method = RequestMethod.GET)
	public ModelAndView ResultPage() {

		ModelAndView model = new ModelAndView("ResultPage");
		model.addObject("TeamName", userdet.getTeam_name());
		return model;
	}
}

// adding new user from admin pannel
/*
 * @RequestMapping(value = "/add_user", method = RequestMethod.POST) public
 * ModelAndView adduser(HttpSession httpSession, @RequestParam Map<String,
 * String> requestParams) { String email = requestParams.get("email"); Session
 * session = sessionFactory.openSession(); session.beginTransaction();
 * ModelAndView model = new ModelAndView("add"); Query queryResult =
 * session.createQuery("from Userdet"); java.util.List allUsers; String
 * email_id; email_id = null; allUsers = queryResult.list(); int f; f = 0; for
 * (int i = 0; i < allUsers.size(); i++) { Userdet user = (Userdet)
 * allUsers.get(i); email_id = user.getEmailid(); if (email.equals(email_id)) {
 * f = 1; break; } } if (f != 1) { Userdet user = new Userdet();
 * user.setEmailid(email); session.save(user);
 * session.getTransaction().commit(); session.close(); user = null;
 * System.out.println(email); model.addObject("add", "record added"); } else {
 * System.out.println("duplicate"); model.addObject("dup", "duplicate record");
 * } return model; } }
 */
/*
 * code for admin pannel operations commented for further use
 * 
 */
// // add new questions to db
// @RequestMapping(value = "/addques", method = RequestMethod.POST)
// public ModelAndView addques(HttpSession httpSession, @RequestParam
// Map<String,String> requestParams) {
// String title=requestParams.get("title");
// String details=requestParams.get("detail");
// String constraints=requestParams.get("const");
// String inputformat=requestParams.get("inp_format");
// String outpuformat=requestParams.get("op_format");
// String inputtestcase=requestParams.get("inp_testcase");
// String outputtestcase=requestParams.get("op_testcase");
// String file=requestParams.get("file");
// Session session = sessionFactory.openSession();
// session.beginTransaction();
//
// ModelAndView model = new ModelAndView("addques");
//
// Query queryResult = session.createQuery("from Questions");
// java.util.List allUsers;
// String titl;
// titl=null;
//
// allUsers = queryResult.list();
// int f;
// f=0;
// for (int i = 0; i < allUsers.size(); i++) {
// Questions user = (Questions) allUsers.get(i);
// titl=user.getTitle();
//
// if(title.equals(titl)){
// f=1;
//
// break;
//
//
// }
// }
//
// if(f!=1){
// Questions user= new Questions();
// user.setTitle(title);
// user.setDetail(details);
// user.setConstraints(constraints);
// user.setInputformat(inputformat);
// user.setInputtestcase(inputtestcase);
// user.setOutputtestcase(outputtestcase);
// // user.setPath(path);
// session.save(user);
// session.getTransaction().commit();
//
// session.close();
// user=null;
// System.out.println(title);
// model.addObject("add","record added");
// }
// else
// { System.out.println("duplicate");
// model.addObject("dup","duplicate record");
// }
// return model;
//
// }
// // editing the existing questions
// @RequestMapping(value = "/ques_edit", method = RequestMethod.POST)
// public ModelAndView quesedit(HttpSession httpSession, @RequestParam
// Map<String,String> requestParams) {
// String quesId = requestParams.get("id");
//
// Session session = sessionFactory.openSession();
// session.beginTransaction();
//
// ModelAndView model = new ModelAndView("quesedit");
//
// Query queryResult = session.createQuery("from Questions");
// java.util.List allUsers;
// String det,title,inp_format,cons,inp_case,op_case,path;
// int qid;
// qid=0;
// det=null;
// title=null;
// inp_format=null;
// cons=null;
// inp_case=null;
// op_case=null;
// path=null;
// allUsers = queryResult.list();
// int f;
// f=0;
// for (int i = 0; i < allUsers.size(); i++) {
// Questions user = (Questions) allUsers.get(i);
// qid=user.getQuesid();
// title=user.getTitle();
// cons=user.getConstraints();
// det=user.getDetail();
// inp_case=user.getInputtestcase();
// op_case=user.getOutputtestcase();
// path=user.getPath();
//
//
// if(quesId.equals(qid) ){
// f=1;
// break;
//
// }
//
// }
// if(f==1){
// model.addObject("qid",qid);
// model.addObject("title",title);
// model.addObject("detail",det);
// model.addObject("constraints",cons);
// model.addObject("inputcase",inp_case);
// model.addObject("outputcase",op_case);
// model.addObject("path",path);
//
// }
// else
// {
// model.addObject("norec","no record found");
// }
//
// return model;
//
// }
// //modify questions
// @RequestMapping(value = "/modifyque", method = RequestMethod.POST)
// public ModelAndView modify(HttpSession httpSession, @RequestParam
// Map<String,String> requestParams) {
// ModelAndView model = new ModelAndView("quesedit");
// String quesid = requestParams.get("id");
// String det,title,inp_format,cons,inp_case,op_case,path;
// det=requestParams.get("detail");
// title=requestParams.get("title");
// cons=requestParams.get("cons");
// inp_case=requestParams.get("inpcase");
// op_case=requestParams.get("opcase");
// /*
// space for code for file upload
// after which path requsest parameter to be used
//
// */
// // path=requestParams.get("");
// Session session = sessionFactory.openSession();
// session.beginTransaction();
//
//
// Questions user= (Questions) session.get(Questions.class,quesid);
// user.setQuesid(Integer.parseInt(quesid));
// user.setDetail(det);
// user.setConstraints(cons);
// user.setInputtestcase(inp_case);
// user.setOutputtestcase(op_case);
// /*
// path to be set after the file upload code is written
//
// */
// //user.setPath(path);
// session.update(user);
// session.getTransaction().commit();
// session.close();
//
//
// model.addObject("rec","record edited");
// return model;
// }
//
//
// }

/*
 * google login oauth commented for further use
 * 
 */

// @RequestMapping(value = "/registration", method = RequestMethod.POST)
// public ModelAndView registration(HttpSession httpSession, @RequestParam
// Map<String,String> requestParams) {
// GoogleIdToken.Payload payLoad;
// ModelAndView model=new ModelAndView("dashboard");
// String auth_token= requestParams.get("auth_token");
// String branch=requestParams.get("branch");
// String year=requestParams.get("year");
// String admno=requestParams.get("admno");
//
// // code for fetching user data from google api
// try {
// payLoad = IdTokenVerifierAndParser.getPayload(auth_token);
// String name = (String) payLoad.get("name");
// String email = payLoad.getEmail();
// avatar= (String) payLoad.get("picture");
// System.out.println("User name: " + name);
// System.out.println("User email: " + email);
// System.out.println("avatar :" + avatar);
// System.out.println("branch :" + branch);
// System.out.println("year :" + year);
// System.out.println("admission no :" + admno);
// userdet=new Userdet
// (email,avatar,year,branch,name,admno);
// Session session = sessionFactory.openSession();
// session.beginTransaction();
//
// session.save(userdet);
// session.getTransaction().commit();
// session.close();
// model.addObject("name",name);
// model.addObject("avatar",avatar);
//
// } catch (Exception e) {
// // TODO Auto-generated catch block
// e.printStackTrace();
// }
//
// return model;
//
// }
/**
 * // * Controller, after completion of registration or login verification // *
 * // * @param httpSession // * @param email // * @return ModelAndView //
 */
// @RequestMapping(value = "/dashboard", method = RequestMethod.POST)
// public ModelAndView login(HttpSession httpSession, @RequestParam ("email")
// String email){
// httpSession.setAttribute("loggedinuser",email);
// ModelAndView model=new ModelAndView("dashboard");//generating session for the
// logged in user
// Session session =sessionFactory.openSession();
// session.beginTransaction();
// userdet = (Userdet) session.get(Userdet.class, email);
// //String name=userdet.getName();
// //String avatar=userdet.getAvatar();
// //model.addObject("name",name);
// model.addObject("avatar",avatar);
// return model;
//
// }
//
/// **
// * controller for first time logging user for registration
// *
// * @param httpSession
// * @param requestParams
// * @return ModelAndView
// */
//
/**
 * // *for login verification through google API // * @param httpSession //
 * * @param email // * @return String //
 */
/// *MediaType.APPLICATION_JSON_VALUE return string*/
// @RequestMapping(value = "/loginverifier",
// method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
// public @ResponseBody String loginverify(HttpSession httpSession,
// @RequestParam ("user_email") String email){
// Session session =sessionFactory.openSession();
// session.beginTransaction();
//
// userdet = (Userdet) session.get(Userdet.class, email);
//
// session.close();
// if(userdet != null)
// {
// return "registered";
// }
//
// else
// return "new_user";
//
// }

// code from index
// controller-----------------------------------------------------
// String correspondingQuesMark="ques1_JAVA";
// String correspondingQuesLength="ques1_JAVA_l";
// Session session = sessionFactory.openSession();
// Result res = (Result) session.get(Result.class, "shasha@grey.com");
// String hql="SELECT "+correspondingQuesMark+" , "+correspondingQuesLength+"
// FROM Result R";
// String hql="SELECT email , "+correspondingQuesMark+" ,
// "+correspondingQuesLength+" FROM Result R";

// String hql="UPDATE Result R set "+ correspondingQuesLength+" = "+1000000+"
// WHERE R.email = 'sss@hh.com'";
// String hql="SELECT min("+ correspondingQuesLength + ") FROM Result R";
// String hql= "SELECT"+" ques1_JAVA_l"+" FROM Result R WHERE R.email =
// 'shasha@grey.com' ";
// System.out.println("HQL is --->>>");
// System.out.println(hql);

// Query query = session.createQuery(hql);
// int rr=query.executeUpdate();
// List<Object> r =(List<Object>) query.list();
// Iterator itr = r.iterator();

// System.out.println("YAHI HHHHHH -------- >>>>");

/*
 * while(itr.hasNext()){ Object[] obj = (Object[]) itr.next(); //now you have
 * one array of Object for each row String client = String.valueOf(obj[0]);
 * Integer marks = Integer.parseInt(String.valueOf(obj[1])); Integer length =
 * Integer.parseInt(String.valueOf(obj[2])); System.out.println(client +
 * " "+marks +"  "+ length); //SERVICE assumed as int //same way for all obj[2],
 * obj[3], obj[4] }
 */
// System.out.println(rr);
/*
 * for(int i=0; i<r.size();i++) {
 * System.out.println(((Fetch)r.get(i)).getCorresponding_length()); }
 */ // System.out.println(r.get(0));
// session.close();