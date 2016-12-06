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
import com.sa.finalproject.DAO.impl.EmployeeDAOImpl;
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
	
	@RequestMapping(value = "/Login2", method = RequestMethod.GET)
	public ModelAndView login2(){
		// show the page that let user scan their id card
		ModelAndView model = new ModelAndView("login");
		return model;
	}
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ModelAndView checkLogin(@ModelAttribute("userID") String employeeID){
		// show the page that let user scan their id card
		ModelAndView model = new ModelAndView("redirect:/productList");
		EmployeeDAOImpl staffDAO = (EmployeeDAOImpl) context.getBean("EmployeeDAO");
		List<Employee> staffList = staffDAO.getList();
		
		System.out.println("The employee ID is " + employeeID + ".");
		System.out.println("");
		
		for(int i = 0; i < staffList.size(); i++) {
			Employee currentStaff = staffList.get(i);
			if(currentStaff.getId().equals(employeeID)) {
				System.out.println("Current staff ID is " + currentStaff.getId() + ".");
				model = new ModelAndView("redirect:/Dashboard");
				break;
			}
		}
		return model;
	}
	
	@RequestMapping(value = "/Dashboard", method = RequestMethod.GET)
	public ModelAndView dashboard(){
		// check the identity
		ModelAndView model = new ModelAndView("Dashboard");
		
		
		return model;
	}
	
}
