package com.sa.finalproject.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sa.finalproject.entity.PurchaseOrder;

@Controller
public class RequisitionController {
	ApplicationContext context = new ClassPathXmlApplicationContext("spring-module.xml");
	
	@RequestMapping(value = "/checkRequisition", method = RequestMethod.GET)
	public ModelAndView checkRequisition(){
	
		ModelAndView model = new ModelAndView("ListPurchasedOrder");
		
		
		return model;
	}
	
	@RequestMapping(value = "/checkRequisition", method = RequestMethod.POST)
	public ModelAndView insertRequisition(PurchaseOrder order){
	
		ModelAndView model = new ModelAndView("product");
		
		
		return model;
	}
}