package com.sa.finalproject.servlet;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Servlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
	String secretName = getInitParameter("name");
	String name = request.getParameter("name");
	//String secretName = getServletConfig().getInitParameter("name");
	//getInitParameterNames() 取得所有init param 的名字
	
	if(name.equals(secretName))
	{
		request.getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
		return;
	}


	System.out.println("");		
	System.out.println("Hello: " + name);	

	System.out.println(getServletContext().getInitParameter("message"));
	}
	
}

