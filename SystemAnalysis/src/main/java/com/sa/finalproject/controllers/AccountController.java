package com.sa.finalproject.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.beans.factory.annotation.Autowired;

import com.sa.finalproject.entity.Employee;

@Controller
public class AccountController {
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView chechLogin(@ModelAttribute Employee employee){
		// check the password
		ModelAndView model = new ModelAndView("index");
		if("account".equals(employee.getId())){
			// it's successful
		}else {  
			// there are some errors
		}
		
		return model;
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(){
		// After logging
		ModelAndView model = new ModelAndView("index");
		return model;
	}
	
}
