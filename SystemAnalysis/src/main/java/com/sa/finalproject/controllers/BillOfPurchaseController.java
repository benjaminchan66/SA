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

import com.sa.finalproject.DAO.BillOfPurchaseDAO;
import com.sa.finalproject.DAO.RemarkDAO;
import com.sa.finalproject.entity.BillOfPurchase;
import com.sa.finalproject.entity.PurchasingRequisition;
import com.sa.finalproject.entity.Remark;



@Controller
public class BillOfPurchaseController {
	ApplicationContext context =  new ClassPathXmlApplicationContext("spring-module.xml");
	
	
	@RequestMapping(value = "/billofpurchase", method = RequestMethod.GET) //顯示所有進貨單
	public ModelAndView getProductList(){
		ModelAndView model = new ModelAndView("billofpurchase");
		BillOfPurchaseDAO billOfPurchaseDAO = (BillOfPurchaseDAO)context.getBean("BillOfPurchase");
	    ArrayList<BillOfPurchase> billOfPurchaseList = new ArrayList<BillOfPurchase>();
		billOfPurchaseList = billOfPurchaseDAO.getList();
		model.addObject("billofpurchaselist", billOfPurchaseList);
		
		return model;
	}
	
	@RequestMapping(value = "/billofpurchase", method = RequestMethod.POST) // 驗貨
	public ModelAndView examineGoods(long aBopSerial,boolean passed){
		ModelAndView model = new ModelAndView("billofpurchase");
		BillOfPurchaseDAO examieGoods = (BillOfPurchaseDAO)context.getBean("BillOfPurchase");
		examieGoods.examineGoods(aBopSerial,passed);
		return model;
	}
	
//	@RequestMapping(value = "/billofpurchase", method = RequestMethod.POST) // 付款
//	public ModelAndView paid(long aBopSerial){
//		ModelAndView model = new ModelAndView("billofpurchase");
//		BillOfPurchaseDAO paid = (BillOfPurchaseDAO)context.getBean("BillOfPurchase");
//		paid.paid(aBopSerial);
//		return model;
//	}
	
	
	@RequestMapping(value = "/unpaidList", method = RequestMethod.GET) //顯示到貨且未付款商品
	public ModelAndView showUnpaidProductList(){
		ModelAndView model = new ModelAndView("billofpurchase");
		BillOfPurchaseDAO billOfPurchaseDAO = (BillOfPurchaseDAO)context.getBean("BillOfPurchase");
		List<BillOfPurchase> billOfPurchaseList = new ArrayList<BillOfPurchase>();
		billOfPurchaseList = billOfPurchaseDAO.showUnpaidProduct();
		model.addObject("showUnpaidProductlist", billOfPurchaseList);
		
		return model;
	}
	
//	
//	@RequestMapping(value = "/billofpurchase", method = RequestMethod.POST) // 將請購單轉為進貨單
//	public ModelAndView transferIntoBOP(PurchasingRequisition aPurchaseingRequisition){
//		ModelAndView model = new ModelAndView("billofpurchase");
//		BillOfPurchaseDAO transferIntoBOP = (BillOfPurchaseDAO)context.getBean("BillOfPurchase");
//		transferIntoBOP.transferIntoBOP(aPurchaseingRequisition);
//		return model;
//	}
	
	@RequestMapping(value = "/remark", method = RequestMethod.GET) //顯示所有備註
	public ModelAndView getRemarkList(@ModelAttribute("bopSerial") long bopSerial){
		ModelAndView model = new ModelAndView("remark");
		RemarkDAO remark = (RemarkDAO)context.getBean("Remark");
		ArrayList<Remark> remarkList = new ArrayList<Remark>();
		remarkList = remark.showRemark(bopSerial);
		model.addObject("remarkList", remarkList);
		
		return model;
	}
	
	@RequestMapping(value = "/insertRemark", method = RequestMethod.GET)
	public ModelAndView insertProductPage(){
		ModelAndView model = new ModelAndView("insertRemark");
		return model;
	}
	
	@RequestMapping(value = "/insertRemark", method = RequestMethod.POST)
	public ModelAndView insertProduct(@ModelAttribute long bop_serial ,Remark remark){
		ModelAndView model = new ModelAndView("redirect:/billofpurchase");
		RemarkDAO remarkInsert = (RemarkDAO)context.getBean("Remark");
		remarkInsert.addRemark(bop_serial,remark);
		return model;
	}
	@RequestMapping(value = "/updateRemark", method = RequestMethod.GET)
	public ModelAndView updateProductPage(@ModelAttribute long bop_serial ,Remark remark){
		ModelAndView model = new ModelAndView("updateProduct");
		RemarkDAO remarkUpdate = (RemarkDAO) context.getBean("Remark");
		// ArrayList<BillOfPurchaseDAO> remarks  = new  ArrayList<BillOfPurchaseDAO>();
		remarkUpdate =  (RemarkDAO)remarkUpdate.showRemark(bop_serial);
		model.addObject("remark", remarkUpdate);
		return model;
	}
	
	@RequestMapping(value = "/updateRemark", method = RequestMethod.POST)
	public ModelAndView updateProduct(@ModelAttribute long bop_serial, Remark remark){
		ModelAndView model = new ModelAndView("redirect:/billofpurchase");
		RemarkDAO remarkUpdate = (RemarkDAO) context.getBean("Remark");
		remarkUpdate.updateRemark(bop_serial,remark);	
		return model;
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ModelAndView deleteProduct(@ModelAttribute long bop_serial, Remark remark){
		ModelAndView model = new ModelAndView("redirect:/billofpurchase");
		RemarkDAO remarkDelete = (RemarkDAO) context.getBean("Remark");
		remarkDelete.deleteRemark(bop_serial,remark);
		return model;
	}
   

}
