package com.sa.finalproject.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sa.finalproject.DAO.PurchasingRequisitionDAO;
import com.sa.finalproject.entity.PurchaseOrder;
import com.sa.finalproject.entity.PurchasingRequisition;

@Controller
public class RequisitionController {
	ApplicationContext context = new ClassPathXmlApplicationContext("spring-module.xml");
	
	@RequestMapping(value = "/insertRequisition", method = RequestMethod.GET)
	public ModelAndView checkRequisition(){
		// 開立請購單
		ModelAndView model = new ModelAndView("OpenRequisition");
		
		return model;
	}
	
	@RequestMapping(value = "/insertRequisition", method = RequestMethod.POST)
	public ModelAndView insertRequisition(@ModelAttribute("orderList")PurchaseOrder mixedOrder, @ModelAttribute("staffID")long staffID){
		// 開立請購單
		ModelAndView model = new ModelAndView("redirect:/listRequisition");
		PurchasingRequisitionDAO requisitionDAO = (PurchasingRequisitionDAO)context.getBean("purchaseRequisitionDAO");
		String supplierCurrentLevel = requisitionDAO.insert(mixedOrder, staffID);
		System.out.println("The supplier level is " + supplierCurrentLevel);
		
		return model;
	}
	
	@RequestMapping(value = "/listRequisition", method = RequestMethod.GET)
	public ModelAndView listRequisition(){
	
		ModelAndView model = new ModelAndView("Requisition");
		PurchasingRequisitionDAO requisitionDAO = (PurchasingRequisitionDAO)context.getBean("purchaseRequisitionDAO");
		ArrayList<PurchasingRequisition> requisitionList = new ArrayList<PurchasingRequisition>();
		requisitionList = requisitionDAO.getList();
//		model.addObject("requisitionList", requisitionList);
		
		return model;
	}
	
	
	@RequestMapping(value = "/listRequisition", method = RequestMethod.POST)
	public ModelAndView confirmRequisition(@ModelAttribute("staffID")long staffID, @ModelAttribute("requisitionSerial")long aRequisitionSerial, @ModelAttribute("isConfirmed")boolean isConfirmed){
	
		ModelAndView model = new ModelAndView("redirect:/listRequisition");
		PurchasingRequisitionDAO requisitionDAO = (PurchasingRequisitionDAO)context.getBean("purchaseRequisitionDAO");
		requisitionDAO.confirm(staffID, aRequisitionSerial, isConfirmed);
		
		return model;
	}
}