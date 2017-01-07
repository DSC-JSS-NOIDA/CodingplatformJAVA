package org.gdgjss.codingplatform.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Boot {
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView getadmissionform(HttpSession httpSession) {	
		ModelAndView model = new ModelAndView("index");
		return model;
	}
}
