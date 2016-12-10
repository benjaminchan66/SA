package com.sa.finalproject.controllers;

import java.util.ArrayList;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.sa.finalproject.DAO.impl.SupplierDAOImpl;
import com.sa.finalproject.DAO.impl.WarehouseWarrantDAOImpl;
import com.sa.finalproject.entity.Supplier;
import com.sa.finalproject.entity.WarehouseWarrant;

@Controller
@SessionAttributes(value = {"newaccount", "shoppingCart"})
public class WarehouseWarrantController {
	ApplicationContext context = new ClassPathXmlApplicationContext("spring-module.xml");
	
	@RequestMapping(value = "/wwList", method = RequestMethod.GET)
	public ModelAndView listWW(){
		// 開立請購單
		ModelAndView model = new ModelAndView("StockIn");
		WarehouseWarrantDAOImpl wwDAO = (WarehouseWarrantDAOImpl)context.getBean("warehouseWarrantDAO");
		ArrayList<WarehouseWarrant> wwList = new ArrayList<WarehouseWarrant>();
		wwList = wwDAO.getList();
		
		return model;
	}
}