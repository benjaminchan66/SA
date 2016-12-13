package com.sa.finalproject.controllers;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.sa.finalproject.DAO.EmployeeDAO;
import com.sa.finalproject.DAO.PurchasingRequisitionDAO;
import com.sa.finalproject.DAO.impl.SupplierDAOImpl;
import com.sa.finalproject.DAO.impl.WarehouseWarrantDAOImpl;
import com.sa.finalproject.entity.Employee;
import com.sa.finalproject.entity.PurchasingRequisition;
import com.sa.finalproject.entity.Supplier;
import com.sa.finalproject.entity.WarehouseWarrant;
import com.sa.finalproject.entity.displayClass.DisplayWW;

@Controller
@SessionAttributes(value = {"newaccount", "shoppingCart"})
public class WarehouseWarrantController {
	ApplicationContext context = new ClassPathXmlApplicationContext("spring-module.xml");
	
	@RequestMapping(value = "/wwList", method = RequestMethod.GET)
	public ModelAndView listWW(){
		// 列出所有入庫單
		ModelAndView model = new ModelAndView("StockIn");
		WarehouseWarrantDAOImpl wwDAO = (WarehouseWarrantDAOImpl)context.getBean("warehouseWarrantDAO");
		ArrayList<WarehouseWarrant> wwList = new ArrayList<WarehouseWarrant>();
		wwList = wwDAO.getList();
		model.addObject("wwList", wwList);
		
		return model;
	}
	

	
	@RequestMapping(value = "/StockIn", method = RequestMethod.GET)
	public ModelAndView insertWW(@ModelAttribute("id")String serial, HttpSession session){
		// 列出所有入庫單
		if(serial.length() == 0) {
			serial = "0";
		}
		
		ModelAndView model = new ModelAndView("redirect:/wwList");
		PurchasingRequisitionDAO prDAO = (PurchasingRequisitionDAO)context.getBean("purchaseRequisitionDAO");
		PurchasingRequisition pr = new PurchasingRequisition();
		
		WarehouseWarrantDAOImpl wwDAO = (WarehouseWarrantDAOImpl)context.getBean("warehouseWarrantDAO");
		long employeeID = 0;
		if(session.getAttribute("newaccount") != null) {
			Employee staff = new Employee();
			staff = (Employee)(session.getAttribute("newaccount"));
			employeeID = Long.parseLong(staff.getId());
		}
		
		wwDAO.insert(pr, employeeID);
		
		return model;
	}
	
	@RequestMapping(value = "/StockInDetail", method = RequestMethod.GET)
	public ModelAndView getWWDetail(@ModelAttribute("id")String wwSerial){
		// 檢視單一入庫單細節
		ModelAndView model = new ModelAndView("StockInDetail");
		WarehouseWarrantDAOImpl wwDAO = (WarehouseWarrantDAOImpl)context.getBean("warehouseWarrantDAO");
		WarehouseWarrant ww = new WarehouseWarrant();
		if(wwSerial.length() == 0) {
			wwSerial = "0";
		}else {
			ww = wwDAO.get(Long.parseLong(wwSerial));
		}
		DisplayWW displayWW = new DisplayWW();
		displayWW.setWW(ww);
		
		EmployeeDAO staffDAO = (EmployeeDAO)context.getBean("EmployeeDAO");
		Employee staff = new Employee();
		staff = staffDAO.getAEmployee(ww.getEmployeeID());
		displayWW.setEmployee(staff);
		
		PurchasingRequisitionDAO prDAO = (PurchasingRequisitionDAO)context.getBean("purchaseRequisitionDAO");
		Supplier supplier = new Supplier();
		supplier = prDAO.getASupplierOf(Long.parseLong(wwSerial));
		displayWW.setSupplier(supplier);
		
		
		// ww info
		model.addObject("wwSerial", displayWW.getWwSerial());
		model.addObject("wwSupplierName", displayWW.getSupplierName());
		model.addObject("wwEmployeeName", displayWW.getEmployeeName());
		model.addObject("wwDate", displayWW.getDate());
		
		// content
		model.addObject("productList", displayWW.getWwContent().getList());
		
		return model;
	}
}