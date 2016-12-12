package com.sa.finalproject.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.sa.finalproject.DAO.ProductDAO;
import com.sa.finalproject.DAO.PurchasingRequisitionDAO;
import com.sa.finalproject.DAO.SupplierDAO;
import com.sa.finalproject.entity.Employee;
import com.sa.finalproject.entity.Product;
import com.sa.finalproject.entity.PurchaseOrder;
import com.sa.finalproject.entity.PurchasingRequisition;
import com.sa.finalproject.entity.Supplier;
import com.sa.finalproject.entity.displayClass.DisplayPR;
import com.sa.finalproject.entity.supportingClass.PurchasedProduct;

@Controller
@SessionAttributes(value = {"newaccount", "shoppingCart"})
public class RequisitionController {
	
	@Autowired
	PurchaseOrder cart_session;
	
	ApplicationContext context = new ClassPathXmlApplicationContext("spring-module.xml");
	
	// Indicate whether the user has selected the supplier form
	static boolean hasSelectedSupplier = false;
	Long selectedSupplier = null;
	
	@RequestMapping(value = "/insertRequisition", method = RequestMethod.GET)
	public ModelAndView checkRequisition(){
		// 顯示開立請購單頁面
		ModelAndView model = new ModelAndView("insertRequisition");
		SupplierDAO supplierDAO = (SupplierDAO)context.getBean("supplierDAO");
		ArrayList<Supplier> supplierList = new ArrayList<Supplier>();
		supplierList = supplierDAO.getList();
		model.addObject("supplierList", supplierList);
		
		
		if(this.hasSelectedSupplier && this.selectedSupplier != null) {
			ProductDAO productDAO = (ProductDAO)context.getBean("productDAO");
			ArrayList<Product> productList = new ArrayList<Product>();
			productList = productDAO.getProductOf(this.selectedSupplier);
			model.addObject("productList", productList);
			model.addObject("selectedSupplier", this.selectedSupplier);
			this.selectedSupplier = null;
			this.hasSelectedSupplier = false;
			
			model.addObject("submit", "submit");
		}else {
			model.addObject("productList", new ArrayList<Product>());
			model.addObject("submit", null);
		}
		
		return model;
	}
	
	@RequestMapping(value = "/insertRequisition", method = RequestMethod.POST)
	public ModelAndView insertRequisition(@ModelAttribute("selectedSupplierID")String selectedID){
		// show all the products of that selected supplier
		ModelAndView model = new ModelAndView("redirect:/insertRequisition");
		this.selectedSupplier = Long.parseLong(selectedID);
		this.hasSelectedSupplier = true;
		
		return model;
	}
	
	@RequestMapping(value = "/addProductToRequisition", method = RequestMethod.GET)
	public ModelAndView addProductToShoppingCart(@ModelAttribute("id")String productID){
		// add the product to the shopping cart
		ModelAndView model = new ModelAndView("redirect:/previewDetailRequisition");
		ProductDAO productDAO = (ProductDAO)context.getBean("productDAO");
		Product product = productDAO.get(Long.parseLong(productID));
		
		PurchasedProduct purchasedItem = new PurchasedProduct();
		purchasedItem.setProduct(product, 1);
		
		boolean productInTheCart = false;
		for(int i = 0; i < cart_session.getList().size(); i++) {
			PurchasedProduct currentProduct = cart_session.getList().get(i);
			System.out.println("Product name" + currentProduct.getProductName());
			if(String.valueOf(currentProduct.getProductID()).equals(productID)) {
				currentProduct.addOne();
				productInTheCart = true;
				break;
			}
		}
		
		if(!productInTheCart) {
			System.out.println("Add the product whose ID is " + purchasedItem.getProductID());
			cart_session.add(purchasedItem);
		}
		
		return model;
	}
	
	@RequestMapping(value = "/listRequisition", method = RequestMethod.GET)
	public ModelAndView listRequisition(){
	
		ModelAndView model = new ModelAndView("Requisition");
		PurchasingRequisitionDAO requisitionDAO = (PurchasingRequisitionDAO)context.getBean("purchaseRequisitionDAO");
		ArrayList<PurchasingRequisition> requisitionList = new ArrayList<PurchasingRequisition>();
		requisitionList = requisitionDAO.getList();
		
		
		ArrayList<DisplayPR> displayList = new ArrayList<DisplayPR>();
		for(int i = 0; i < requisitionList.size(); i++) {
			PurchasingRequisition currentRequisition = requisitionList.get(i);
			DisplayPR displayPR = new DisplayPR();
			displayPR.setRequisition(currentRequisition);
			
			Supplier supplier = new Supplier();
			supplier = requisitionDAO.getASupplierOf(currentRequisition.getPrSerial());
			displayPR.setSupplier(supplier);
			
			displayList.add(displayPR);
		}
		
		System.out.println("The length of requisition list : " + requisitionList.size());
		System.out.println("The length of display list : " + displayList.size());
		
		model.addObject("prList", displayList);
		
		return model;
	}
	
	
	@RequestMapping(value = "/listDetailRequisition", method = RequestMethod.GET)
	public ModelAndView confirmRequisition(@ModelAttribute("id")String prSerial){
	
		ModelAndView model = new ModelAndView("RequisitionDetail");
		PurchasingRequisitionDAO requisitionDAO = (PurchasingRequisitionDAO)context.getBean("purchaseRequisitionDAO");
//		requisitionDAO.confirm(staffID, aRequisitionSerial, isConfirmed);
		PurchasingRequisition pr = requisitionDAO.get(Long.parseLong(prSerial)); 
		PurchaseOrder content = new PurchaseOrder();
		content = pr.getRequisitionContent();
		
		for(int i = 0; i < content.getList().size(); i++) {
			System.out.println("Content " + (i+1) + " : " + content.getList().get(i).getProductID());
		}
		
		return model;
	}
	
	@RequestMapping(value = "/previewDetailRequisition", method = RequestMethod.GET)
	public ModelAndView showCart() {
		ModelAndView model = new ModelAndView("previewDetailRequisition");
		// 顯示購物車內容物
		
		return model;
	}
	
	
	@RequestMapping(value = "/previewDetailRequisition", method = RequestMethod.POST)
	public ModelAndView insertCart(HttpSession session) {
		ModelAndView model = new ModelAndView("redirect:/listRequisition");
		// 送出購物車內容
		String accountID = "0";
		if(session.getAttribute("newaccount") != null) {
			Employee staff = (Employee)session.getAttribute("newaccount");
			accountID = staff.getId();
			System.out.println("Staff ID : " + staff.getId() + ".");
		}else {
			System.out.println("Session is null");
		}
		PurchasingRequisitionDAO requisitionDAO = (PurchasingRequisitionDAO)context.getBean("purchaseRequisitionDAO");
		requisitionDAO.insert(cart_session, Long.parseLong(accountID));
		
		return model;
	}
	
	
	@RequestMapping(value = "/insertFakeRequisition", method = RequestMethod.GET)
	public ModelAndView insertFakeRequisition(){
		// 開立請購單動作執行與測試
		ModelAndView model = new ModelAndView("redirect:/listRequisition");
		PurchasingRequisitionDAO requisitionDAO = (PurchasingRequisitionDAO)context.getBean("purchaseRequisitionDAO");
		PurchaseOrder mixedOrder = this.getFakeOrder();
		System.out.println("The length of mixed order is " + mixedOrder.getList().size());
		
		String supplierCurrentLevel = requisitionDAO.insert(mixedOrder, Long.parseLong("403401213"));
		System.out.println("The supplier level is " + supplierCurrentLevel);
		
		return model;
	}
	
	public PurchaseOrder getFakeOrder() {
		PurchaseOrder fakeOrder = new PurchaseOrder();
		
		ProductDAO productDAO = (ProductDAO) context.getBean("productDAO");
		ArrayList<Product> productList = new ArrayList<Product>();
		productList = productDAO.getList();
		
		SupplierDAO supplierDAO = (SupplierDAO)context.getBean("supplierDAO");
		
		for(int i = 0; i < productList.size(); i++) {
			Product currentProduct = productList.get(i);
			int randomAmount = (int)(Math.random() * 20 + 1);
			Supplier currentSupplier = supplierDAO.get(currentProduct.getSupplierID());
			PurchasedProduct purchasedProduct = new PurchasedProduct(currentProduct.getProductID(), currentProduct.getProductName(), randomAmount, currentSupplier.getSupplierID(), currentSupplier.getSupplierName());
			fakeOrder.add(purchasedProduct);
		}
		
		return fakeOrder;
	}
}