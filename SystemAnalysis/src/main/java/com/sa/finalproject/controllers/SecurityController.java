package com.sa.finalproject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.servlet.ModelAndView;

//import java.security.Principal;

//with Spring Security
//these methods are called after /j_spring_security_check
@Controller
public class SecurityController {
	//@RequestMapping(value = "/loginSuccessful", method = RequestMethod.GET)
//	public ModelAndView loginSuccessful(Principal principal) {
//		
//		ModelAndView model = new ModelAndView("redirect:/product");
//		//http://www.baeldung.com/get-user-in-spring-security
//        System.out.println(principal.getName());
//
//		return model;
//	}
	@RequestMapping(value = "/loginFailed", method = RequestMethod.GET)
	public ModelAndView loginFailed() {
		ModelAndView model = new ModelAndView("index");
		System.out.println("failed");
		model.addObject("message", "登入失敗");
		return model;
	}
//	@RequestMapping(value = {"/", "/login"}, method = RequestMethod.GET)
//	//@RequestMapping(value = "/login", method = RequestMethod.GET)
//	public ModelAndView login() {
//		ModelAndView model = new ModelAndView("index");
//		
//		return model;
//	}
	


}
