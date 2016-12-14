package com.sa.finalproject.controllers;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.sa.finalproject.DAO.BillOfPurchaseDAO;
import com.sa.finalproject.DAO.PurchasingRequisitionDAO;
import com.sa.finalproject.DAO.impl.SupplierDAOImpl;
import com.sa.finalproject.entity.BillOfPurchase;
import com.sa.finalproject.entity.Supplier;
import com.sa.finalproject.entity.displayClass.DisplayBOP;

@Controller
@SessionAttributes(value = {"newaccount", "shoppingCart"})
public class SupplierController {
	ApplicationContext context = new ClassPathXmlApplicationContext("spring-module.xml");
	
	
	@RequestMapping(value = "/SupplierList", method = RequestMethod.GET)
	public ModelAndView listSupplier(){
		// 開立請購單
		ModelAndView model = new ModelAndView("SupplierList");
		SupplierDAOImpl supplierDAO = (SupplierDAOImpl)context.getBean("supplierDAO");
		ArrayList<Supplier> supplierList = new ArrayList<Supplier>();
		supplierList = supplierDAO.getList();
		model.addObject("supplierList", supplierList);
		
		return model;
	}
	
	
	@RequestMapping(value = "/newSupplier", method = RequestMethod.POST)
	public ModelAndView addSupplier(HttpServletRequest request) throws UnsupportedEncodingException {
		
		
		request.setCharacterEncoding("utf-8");
		String name = request.getParameter("supplierName");
		String phone = request.getParameter("supplierPhone");
		String address = request.getParameter("supplierAddress");
		
		// Add the supplier information
		ModelAndView model = new ModelAndView("redirect:/SupplierList");
		SupplierDAOImpl supplierDAO = (SupplierDAOImpl)context.getBean("supplierDAO");
		Supplier newSupplier = new Supplier();
		
		newSupplier.setSupplierName(name);
		newSupplier.setPhone(phone);
		newSupplier.setAddress(address);
		System.out.println(newSupplier.getSupplierName());
		supplierDAO.insert(newSupplier);
		
		
		return model;
	}
	
	@RequestMapping(value = "/updateSupplier", method = RequestMethod.GET)
	public ModelAndView updateSupplier(@ModelAttribute("id")String id){
		// Add the supplier information
		ModelAndView model = new ModelAndView("ChangeSupplierDetail");
		SupplierDAOImpl supplierDAO = (SupplierDAOImpl)context.getBean("supplierDAO");
		Supplier supplier = new Supplier();
		supplier = supplierDAO.get(Long.parseLong(id));
		model.addObject("supplierID", id);
		model.addObject("supplierName", supplier.getSupplierName());
		model.addObject("supplierPhone", supplier.getPhone());
		model.addObject("supplierAddress", supplier.getAddress());
		
		if("A".equals(supplier.getLevel())) {
			model.addObject("isClassA", true);
		}else {
			model.addObject("isClassA", false);
		}
		
		return model;
	}
	
	@RequestMapping(value = "/updateSupplier", method = RequestMethod.POST)
	public ModelAndView sendSupplierInfo(@ModelAttribute("supplierID")String id, HttpServletRequest request) throws UnsupportedEncodingException{
		
		request.setCharacterEncoding("utf-8");
		String name = request.getParameter("supplierName");
		String phone = request.getParameter("supplierPhone");
		String address = request.getParameter("supplierAddress");
		String level = request.getParameter("supplierLevel");
		
		
		
		// Add the supplier information
		ModelAndView model = new ModelAndView("redirect:/SupplierList");
		SupplierDAOImpl supplierDAO = (SupplierDAOImpl)context.getBean("supplierDAO");
		Supplier newSupplierInfo = new Supplier();
		newSupplierInfo.setSupplierName(name);
		newSupplierInfo.setPhone(phone);
		newSupplierInfo.setAddress(address);
		newSupplierInfo.setLevel(level);
		supplierDAO.update(Long.parseLong(id), newSupplierInfo);
		
		return model;
	}
	
	
	@RequestMapping(value = "/supplierRecord", method = RequestMethod.GET)
	public ModelAndView listRemarkedSupplier(){
		// 顯示有備註的進貨單（廠商紀錄）
		ModelAndView model = new ModelAndView("SupplierListDetails");
		BillOfPurchaseDAO bopDAO = (BillOfPurchaseDAO)context.getBean("BillOfPurchaseDAO");
		ArrayList<BillOfPurchase> bopList = new ArrayList<BillOfPurchase>(); 
		bopList = bopDAO.getRemarkedList();
		
		
		ArrayList<DisplayBOP> displayBOPList = new ArrayList<DisplayBOP>();
		PurchasingRequisitionDAO prDAO = (PurchasingRequisitionDAO)context.getBean("purchaseRequisitionDAO");
		for(int i = 0; i < bopList.size(); i++) {
			DisplayBOP displayBOP = new DisplayBOP();
			BillOfPurchase currentBOP = bopList.get(i);
			
			displayBOP.setBOP(currentBOP);
			Supplier supplier = new Supplier();
			supplier = prDAO.getASupplierOf(currentBOP.getBopSerial());
			
			displayBOP.setSupplier(supplier);
			
			displayBOPList.add(displayBOP);
			
		}
		
		model.addObject("bopWithRemarkList", displayBOPList);
		
		
		return model;
	}
}