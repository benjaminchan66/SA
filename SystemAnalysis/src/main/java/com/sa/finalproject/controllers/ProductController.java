package com.sa.finalproject.controllers;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

import com.sa.finalproject.entity.Product;
import com.sa.finalproject.entity.Supplier;
import com.sa.finalproject.entity.displayClass.DisplayProduct;
import com.sa.finalproject.DAO.ProductDAO;
import com.sa.finalproject.DAO.SupplierDAO;
//import com.sa.finalproject.DAO.impl.ProductDAOImpl;
import com.sa.finalproject.DAO.impl.SupplierDAOImpl;

// tell the spring this is a controller(auto scan)
@Controller
@SessionAttributes(value = {"newaccount", "shoppingCart"})
public class ProductController {
	ApplicationContext context =  new ClassPathXmlApplicationContext("spring-module.xml");
	
	
	// To get the list of products
	@RequestMapping(value = "/productList", method = RequestMethod.GET)
	public ModelAndView getProductList(){
		// get the list of products
		ModelAndView model = new ModelAndView("ProductList");
		ProductDAO productDAO = (ProductDAO) context.getBean("productDAO");
		ArrayList<Product> productList = new ArrayList<Product>();
		productList = productDAO.getList();
		
		
		SupplierDAO  supplierDAO = (SupplierDAO)context.getBean("supplierDAO");
		ArrayList<Supplier> supplierList = new ArrayList<Supplier>();
		supplierList= supplierDAO.getList();
		
		
		
		ArrayList<DisplayProduct> displayProductList = new ArrayList<DisplayProduct>();
		for(int i = 0; i < productList.size(); i++) {
			Product currentItem = productList.get(i);
			for(int j = 0; j < supplierList.size(); j++) {
				Supplier currentSupplier = supplierList.get(j);
				if(currentSupplier.getSupplierID() == currentItem.getSupplierID()) {
					DisplayProduct displayProduct = new DisplayProduct();
					displayProduct.setProduct(currentItem);
					displayProduct.setSupplierName(currentSupplier.getSupplierName());
					displayProductList.add(displayProduct);
					break;
				}
			}
		}
		
		model.addObject("productList", displayProductList);
		model.addObject("supplierList", supplierList);
		
		return model;
	}
	
	// To add the product into the database, after inserting the product back to product list
	@RequestMapping(value = "/newProduct", method = RequestMethod.GET)
	public ModelAndView insertProduct(@ModelAttribute("productName") String name, @ModelAttribute("productPrice") String price, @ModelAttribute("productSupplier") long supCode){
		ModelAndView model = new ModelAndView("redirect:/productList");
		ProductDAO productDAO = (ProductDAO)context.getBean("productDAO");
		Product preparedProduct = new Product(name, Integer.parseInt(price), supCode);
		long newProductCode = productDAO.insert(preparedProduct);
		System.out.println("The ID of the product is : " + newProductCode + ".");
		
		System.out.println("The product's name is : " + name + ".");
		
		return model;
	}
	
	@RequestMapping(value = "/updateProduct", method = RequestMethod.GET)
	public ModelAndView updateProduct(@ModelAttribute("id") String productID){
		// Update the product information
		ModelAndView model = new ModelAndView("ChangeProductDetail");
		ProductDAO productDAO = (ProductDAO)context.getBean("productDAO");
		Product newProductInfo = productDAO.get(Long.parseLong(productID));
//		productDAO.update(productID, newProductInfo);
		model.addObject("productID", productID);
		model.addObject("productName", newProductInfo.getProductName());
		model.addObject("productPrice", newProductInfo.getPrice());
		model.addObject("isInTheMarket", newProductInfo.isInTheMarket());
		model.addObject("supplierID", newProductInfo.getSupplierID());
		
		SupplierDAO supplierDAO = (SupplierDAO)context.getBean("supplierDAO");
		ArrayList<Supplier> supplierList = new ArrayList<Supplier>();
		supplierList = supplierDAO.getList();
		model.addObject("supplierList", supplierList);
		return model;
	}
	
	
	@RequestMapping(value = "/updateProduct", method = RequestMethod.POST)
	public ModelAndView updateProduct(@ModelAttribute("productID")String productID, @ModelAttribute("productName") String newName, @ModelAttribute("productPrice") String newPrice, @ModelAttribute("isInTheMarket") String newProductStatus, @ModelAttribute("productSupplier") String newSupplierID){
		// Update the product information
		ModelAndView model = new ModelAndView("redirect:/productList");
		ProductDAO productDAO = (ProductDAO)context.getBean("productDAO");
		Product newProductInfo = new Product();
		newProductInfo.setProductName(newName);
		newProductInfo.setPrice(Integer.parseInt(newPrice));
		if("true".equals(newProductStatus)) {
			newProductInfo.setInTheMarket(true);
		}else {
			newProductInfo.setInTheMarket(false);
		}
		newProductInfo.setSupplierID(Long.parseLong(newSupplierID));
		productDAO.update(Long.parseLong(productID), newProductInfo);
		
		return model;
	}
}
