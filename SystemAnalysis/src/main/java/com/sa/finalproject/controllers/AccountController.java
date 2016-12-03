package com.sa.finalproject.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sa.finalproject.DAO.EmployeeDAO;

import com.sa.finalproject.entity.Employee;

@Controller
public class AccountController {
	
	ApplicationContext context =  new ClassPathXmlApplicationContext("spring-module.xml");
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView login(){
		// show the page that let user scan their id card
		ModelAndView model = new ModelAndView("index");
		return model;
	}
	
	@RequestMapping(value = "/Dashboard", method = RequestMethod.GET)
	public ModelAndView dashboard(@ModelAttribute("userID") long employeeID){
		// check the identity
		ModelAndView model = new ModelAndView("Dashboard");
		EmployeeDAO staffDAO = (EmployeeDAO)context.getBean("EmployeeDAO");
		List<Employee> staffList = new ArrayList<Employee>();
		staffList = staffDAO.getList();
		
		for(int i = 0; i < staffList.size(); i++) {
			Employee currentStaff = staffList.get(i);
			if(currentStaff.getId() == String.valueOf(employeeID)) {
				// let user log into the system
				break;
			}
		}
		
		
		return model;
	}
	
}
