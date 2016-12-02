package com.sa.finalproject.controllers;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

import com.sa.finalproject.entity.Product;
import com.sa.finalproject.DAO.ProductDAO;
//import com.sa.finalproject.DAO.impl.ProductDAOImpl;

// tell the spring this is a controller(auto scan)
@Controller
public class ProductController {
	ApplicationContext context =  new ClassPathXmlApplicationContext("spring-module.xml");
	
	
	// To get the list of products
	@RequestMapping(value = "/productList", method = RequestMethod.GET)
	public ModelAndView getProductList(){
		// get the list of products
		ModelAndView model = new ModelAndView("ProductList");
		ProductDAO productDAO = (ProductDAO) context.getBean("productDAO");
		
//		List<Product> productList = new ArrayList<Product>();
		
		return model;
	}
	
	// To add the product into the database
	@RequestMapping(value = "/productList", method = RequestMethod.POST)
	public ModelAndView insertProduct(@ModelAttribute("productName") String name, @ModelAttribute("price") int price, @ModelAttribute("supplierID") long supCode){
		ModelAndView model = new ModelAndView("Testfile");
		ProductDAO productDAO = (ProductDAO)context.getBean("productDAO");
		Product preparedProduct = new Product(name, price, supCode);
		long newProductCode = productDAO.insert(preparedProduct);
		model.addObject("productCode", newProductCode);
		model.addObject("productName", name);
		model.addObject("productPrice" , price);
		model.addObject("supplierCode", supCode);
		
		return model;
	}
	
	@RequestMapping(value = "/productLists", method = RequestMethod.POST)
	public ModelAndView updateProduct(@ModelAttribute("producID") long productID, @ModelAttribute("productName") long newName, @ModelAttribute("productPrice") int newPrice, @ModelAttribute("supplierID") long newSupplierID){
		// Update the product information
		ModelAndView model = new ModelAndView("Testfile");
		return model;
	}
	
	
	@RequestMapping(value = "/productIDSearch", method = RequestMethod.GET)
	public ModelAndView searchID(){
		// get the list of products
		ModelAndView model = new ModelAndView("Testfile2");
//		ProductDAO productDAO = (ProductDAO)context.getBean("productDAO");
		return model;
	}
	
	@RequestMapping(value = "/productIDSearch", method = RequestMethod.POST)
	public ModelAndView showProduct(@ModelAttribute("producID") long productID){
		// 
		ModelAndView model = new ModelAndView("Testfile");
		ProductDAO productDAO = (ProductDAO)context.getBean("productDAO");
		Product aProduct = productDAO.get(productID);
		
		model.addObject("productCode", aProduct.getProductID());
		model.addObject("productName", aProduct.getProductName());
		model.addObject("productPrice", aProduct.getPrice());
		model.addObject("supplierCode", aProduct.getSupplierID());
		
		return model;
	}
	
	
}
