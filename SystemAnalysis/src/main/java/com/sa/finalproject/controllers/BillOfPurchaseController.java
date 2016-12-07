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
	
	
	@RequestMapping(value = "/bopList", method = RequestMethod.GET) //顯示所有進貨單
	public ModelAndView getBOPList(){
		ModelAndView model = new ModelAndView("Order");
		BillOfPurchaseDAO billOfPurchaseDAO = (BillOfPurchaseDAO)context.getBean("BillOfPurchaseDAO");
	    ArrayList<BillOfPurchase> billOfPurchaseList = new ArrayList<BillOfPurchase>();
		billOfPurchaseList = billOfPurchaseDAO.getList();
		model.addObject("billofpurchaselist", billOfPurchaseList);
		
		return model;
	}
	@RequestMapping(value = "/bopDetailList", method = RequestMethod.GET) //列出進貨單明細
	public ModelAndView detailList(@ModelAttribute("id") String bopserial){
		ModelAndView model = new ModelAndView("Testfile");
		BillOfPurchaseDAO billofpurchase = (BillOfPurchaseDAO)context.getBean("BillOfPurchaseDAO");
		BillOfPurchase bop = billofpurchase.get(Long.parseLong(bopserial));
		model.addObject("billofpurchasedetail", bop);
		return model;
	}
	
	@RequestMapping(value = "/unpaidList", method = RequestMethod.GET) //顯示到貨且未付款訂單
	public ModelAndView showUnpaidProductList(){
		ModelAndView model = new ModelAndView("ShowUpaidList");
		BillOfPurchaseDAO billOfPurchaseDAO = (BillOfPurchaseDAO)context.getBean("BillOfPurchaseDAO");
		List<BillOfPurchase> billOfPurchaseList = new ArrayList<BillOfPurchase>();
		billOfPurchaseList = billOfPurchaseDAO.showUnpaidProduct();
		model.addObject("showUnpaidProductlist", billOfPurchaseList);
		return model;
	}

//	
//	@RequestMapping(value = "/billofpurchase", method = RequestMethod.POST) // 將請購單轉為進貨單
//	public ModelAndView transferIntoBOP(PurchasingRequisition aPurchaseingRequisition){
//		ModelAndView model = new ModelAndView("billofpurchase");
//		BillOfPurchaseDAO transferIntoBOP = (BillOfPurchaseDAO)context.getBean("BillOfPurchaseDAO");
//		transferIntoBOP.transferIntoBOP(aPurchaseingRequisition);
//		return model;
//	}
    @RequestMapping(value = "/billofpurchaseupdate", method = RequestMethod.GET) //更新資料
	public ModelAndView updateBillOfPurchasePage(@ModelAttribute("id") String serial){
		ModelAndView model = new ModelAndView("Testfile2");
		BillOfPurchaseDAO update = (BillOfPurchaseDAO)context.getBean("BillOfPurchaseDAO");
		BillOfPurchase bop = update.get(Long.parseLong(serial));
		model.addObject("update",bop);
		return model;
	}
	
	@RequestMapping(value = "/billofpurchaseupdate", method = RequestMethod.POST)
	public ModelAndView updateBillOfPurchase(@ModelAttribute("id") String bop, BillOfPurchase aBillOfPurchase, @ModelAttribute("arrived") Boolean arrived,@ModelAttribute("remark")String remark
			,@ModelAttribute("passed") Boolean passed,@ModelAttribute("paid") Boolean paid){
		ModelAndView model = new ModelAndView("redirect:/bopDetailList");
		BillOfPurchaseDAO update = (BillOfPurchaseDAO)context.getBean("BillOfPurchaseDAO");
		aBillOfPurchase.setRemarks(remark);
		aBillOfPurchase.setHasPaid(paid);
		aBillOfPurchase.setPassed(passed);
		aBillOfPurchase.setHasArrived(arrived);
		update.update(Long.parseLong(bop),aBillOfPurchase);	
		return model;
	}
}
//@RequestMapping(value = "/bopDetailList", method = RequestMethod.POST) // 驗貨
//public ModelAndView examineGoods(@ModelAttribute("id") long aBopSerial,boolean passed){
//	ModelAndView model = new ModelAndView("redirect:/bopList");
//	BillOfPurchaseDAO examieGoods = (BillOfPurchaseDAO)context.getBean("BillOfPurchaseDAO");
//	examieGoods.examineGoods(aBopSerial,passed);
//
//	return model;
//}

//@RequestMapping(value = "/billofpurchase", method = RequestMethod.POST) // 付款
//public ModelAndView paid(long aBopSerial){
//	ModelAndView model = new ModelAndView("billofpurchase");
//	BillOfPurchaseDAO paid = (BillOfPurchaseDAO)context.getBean("BillOfPurchaseDAO");
//	paid.paid(aBopSerial);
//	return model;
//}
/*
	@RequestMapping(value = "/remark", method = RequestMethod.GET) //顯示所有備註
	public ModelAndView getRemarkList(@ModelAttribute("bopSerial") long bopSerial){
		ModelAndView model = new ModelAndView("remark");
		RemarkDAO remark = (RemarkDAO)context.getBean("RemarkDAO");
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
		RemarkDAO remarkInsert = (RemarkDAO)context.getBean("RemarkDAO");
		remarkInsert.addRemark(bop_serial,remark);
		return model;
	}
	@RequestMapping(value = "/updateRemark", method = RequestMethod.GET)
	public ModelAndView updateProductPage(@ModelAttribute long bop_serial ,Remark remark){
		ModelAndView model = new ModelAndView("updateProduct");
		RemarkDAO remarkUpdate = (RemarkDAO) context.getBean("RemarkDAO");
		// ArrayList<BillOfPurchaseDAO> remarks  = new  ArrayList<BillOfPurchaseDAO>();
		remarkUpdate =  (RemarkDAO)remarkUpdate.showRemark(bop_serial);
		model.addObject("remark", remarkUpdate);
		return model;
	}
	
	@RequestMapping(value = "/updateRemark", method = RequestMethod.POST)
	public ModelAndView updateProduct(@ModelAttribute long bop_serial, Remark remark){
		ModelAndView model = new ModelAndView("redirect:/billofpurchase");
		RemarkDAO remarkUpdate = (RemarkDAO) context.getBean("RemarkDAO");
		remarkUpdate.updateRemark(bop_serial,remark);	
		return model;
	}

	@RequestMapping(value = "/123", method = RequestMethod.POST)
	public ModelAndView deleteProduct(@ModelAttribute long bop_serial, Remark remark){
		ModelAndView model = new ModelAndView("redirect:/billofpurchase");
		RemarkDAO remarkDelete = (RemarkDAO) context.getBean("RemarkDAO");
		remarkDelete.deleteRemark(bop_serial,remark);
		return model;
	}
   

}
*/