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

import com.sa.finalproject.DAO.BillOfPurchaseDAO;
import com.sa.finalproject.DAO.EmployeeDAO;
import com.sa.finalproject.DAO.PurchasingRequisitionDAO;
import com.sa.finalproject.DAO.impl.EmployeeDAOImpl;
import com.sa.finalproject.DAO.impl.ProductDAOImpl;
import com.sa.finalproject.DAO.impl.SupplierDAOImpl;
import com.sa.finalproject.DAO.impl.WarehouseWarrantDAOImpl;
import com.sa.finalproject.entity.Employee;
import com.sa.finalproject.entity.Product;
import com.sa.finalproject.entity.PurchasingRequisition;
import com.sa.finalproject.entity.Supplier;
import com.sa.finalproject.entity.WarehouseWarrant;
import com.sa.finalproject.entity.displayClass.DisplayWW;
import com.sa.finalproject.entity.supportingClass.PurchasedProduct;

@Controller
@SessionAttributes(value = {"newaccount", "shoppingCart"})
public class WarehouseWarrantController {
	ApplicationContext context = new ClassPathXmlApplicationContext("spring-module.xml");
	
	@RequestMapping(value = "/wwList", method = RequestMethod.GET)
	public ModelAndView listWW(){
		// 列出所有入庫單
		ModelAndView model = new ModelAndView("StockIn");
		WarehouseWarrantDAOImpl wwDAO = (WarehouseWarrantDAOImpl)context.getBean("warehouseWarrantDAO");
		SupplierDAOImpl supplierDAO = (SupplierDAOImpl)context.getBean("supplierDAO");
		EmployeeDAOImpl staffDAO = (EmployeeDAOImpl)context.getBean("EmployeeDAO");
		
		ArrayList<WarehouseWarrant> wwList = new ArrayList<WarehouseWarrant>();
		wwList = wwDAO.getList();
		
		ArrayList<DisplayWW> displayList = new ArrayList<DisplayWW>();
		for(int i = 0; i < wwList.size(); i++) {
			DisplayWW displayWW = new DisplayWW();
			WarehouseWarrant currentWW = wwList.get(i);
			displayWW.setWW(currentWW);
			Supplier supplier = new Supplier();
			supplier = supplierDAO.get(currentWW.getSupplierID());
			displayWW.setSupplier(supplier);
			Employee staff = new Employee();
			staff = staffDAO.getAEmployee(currentWW.getEmployeeID());
			displayWW.setEmployee(staff);
			displayWW.setEmployeeName(staff.getName());
			
			displayList.add(displayWW);
		}
		
		model.addObject("wwList", displayList);
		
		return model;
	}
	

	
	@RequestMapping(value = "/StockIn", method = RequestMethod.GET)
	public ModelAndView insertWW(@ModelAttribute("id")String serial, HttpSession session){
		ModelAndView model = new ModelAndView("redirect:/wwList");
		
		// 進行入庫還有將BOP改為已到貨
		if(serial.length() == 0) {
			serial = "0";
		}
		System.out.println("The serial is " + serial);
		
		// 執行進貨單資料更新
		BillOfPurchaseDAO bopDAO = (BillOfPurchaseDAO)context.getBean("BillOfPurchaseDAO");
		bopDAO.examineGoods(Long.parseLong(serial), true);
		
		PurchasingRequisitionDAO prDAO = (PurchasingRequisitionDAO)context.getBean("purchaseRequisitionDAO");
		PurchasingRequisition pr = new PurchasingRequisition();
		pr = prDAO.get(Long.parseLong(serial));
//		pr.setRequisitionContent(prDAO.getContentOf(Long.parseLong(serial)));
		System.out.println("The length of pr content is " + pr.getRequisitionContent().count());
		
		WarehouseWarrantDAOImpl wwDAO = (WarehouseWarrantDAOImpl)context.getBean("warehouseWarrantDAO");
		long employeeID = 0;
		if(session.getAttribute("newaccount") != null) {
			Employee staff = new Employee();
			staff = (Employee)(session.getAttribute("newaccount"));
			employeeID = Long.parseLong(staff.getId());
		}
		System.out.println("Hello there");
		
		wwDAO.insert(pr, employeeID);
		
		return model;
	}
	
	@RequestMapping(value = "/StockInDetail", method = RequestMethod.GET)
	public ModelAndView getWWDetail(@ModelAttribute("id")String wwSerial){
		// 檢視單一入庫單細節
		ModelAndView model = new ModelAndView("StockInDetail");
		WarehouseWarrantDAOImpl wwDAO = (WarehouseWarrantDAOImpl)context.getBean("warehouseWarrantDAO");
		ProductDAOImpl productDAO = (ProductDAOImpl)context.getBean("productDAO");
		WarehouseWarrant ww = new WarehouseWarrant();
		if(wwSerial.length() == 0) {
			wwSerial = "0";
		}else {
			ww = wwDAO.get(Long.parseLong(wwSerial));
		}
		DisplayWW displayWW = new DisplayWW();
		displayWW.setWW(ww);
		
		EmployeeDAOImpl staffDAO = (EmployeeDAOImpl)context.getBean("EmployeeDAO");
		Employee staff = new Employee();
		staff = staffDAO.getAEmployee(ww.getEmployeeID());
		displayWW.setEmployee(staff);
		
		PurchasingRequisitionDAO prDAO = (PurchasingRequisitionDAO)context.getBean("purchaseRequisitionDAO");
		Supplier supplier = new Supplier();
		supplier = prDAO.getASupplierOf(Long.parseLong(wwSerial));
		displayWW.setSupplier(supplier);
		
		for(int i = 0; i < displayWW.getWwContent().count(); i++) {
			PurchasedProduct purchasedProduct = displayWW.getWwContent().getList().get(i);
			Product product = new Product();
			product = productDAO.get(purchasedProduct.getProductID());
			purchasedProduct.setProductName(product.getProductName());
		}
		
		
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