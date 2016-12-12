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
import com.sa.finalproject.entity.PurchaseOrder;

@Controller
@SessionAttributes(value = {"newaccount", "shoppingCart"})
public class AccountController {
	
	
	@Autowired
	private Employee account_session;
	
	ApplicationContext context =  new ClassPathXmlApplicationContext("spring-module.xml");
	
	@RequestMapping(value={"/", "/index"}, method = RequestMethod.GET)
	public ModelAndView login(){
		// show the page that let user scan their id card
		ModelAndView model = new ModelAndView("index");
		
//		if(account_session != null) {
//			account_session.setId("");
//		}
		account_session.setId("");
		
		return model;
	}
	
	
	@RequestMapping(value={"/", "/index"}, method = RequestMethod.POST)
	public ModelAndView checkLogin(@ModelAttribute("userID") String employeeID){
		// show the page that let user scan their id card
		ModelAndView model = new ModelAndView("");
		EmployeeDAOImpl staffDAO = (EmployeeDAOImpl) context.getBean("EmployeeDAO");
		List<Employee> staffList = staffDAO.getList();
		
		System.out.println("The employee ID is " + employeeID + ".");
		System.out.println("");
		
		for(int i = 0; i < staffList.size(); i++) {
			Employee currentStaff = staffList.get(i);
			if(currentStaff.getId().equals(employeeID)) {
				System.out.println("Current staff ID is " + currentStaff.getId() + ".");
				account_session.setId(employeeID);
				model.addObject("newaccount", account_session);
				model.addObject("accountID", account_session.getId());
				System.out.println("ID : " + account_session.getId());
				model = new ModelAndView("redirect:/Dashboard");
				
				break;
			}else {
				model = new ModelAndView("index");
				account_session.setId("");
				model.addObject("newaccount", account_session);
			}
		}
		return model;
	}
	
	@RequestMapping(value = "/Dashboard", method = RequestMethod.GET)
	public ModelAndView dashboard(){
		// check the identity
		ModelAndView model = new ModelAndView("Dashboard");
		model.addObject("newaccount", account_session);
		model.addObject("accountID", account_session.getId());
		
		return model;
	}
	
	@RequestMapping(value = "/Profile", method = RequestMethod.GET)
	public ModelAndView showProfile(@ModelAttribute("id")String profileID){
		// check the identity
		ModelAndView model = new ModelAndView("Profile");
		EmployeeDAOImpl staffDAO = (EmployeeDAOImpl) context.getBean("EmployeeDAO");
		Employee staff = staffDAO.getAEmployee(Long.parseLong(profileID));
		
		
		model.addObject("staffID", profileID);
		model.addObject("staffName", staff.getName());
		model.addObject("staffLevel", staff.getLevel());
		model.addObject("staffDep", staff.getDep());
		
		
		return model;
	}
}
