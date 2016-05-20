package com.serviddio.gis.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.serviddio.gis.model.DB;
import com.serviddio.gis.model.UserBean;
import com.serviddio.gis.model.UsersOnline;

/**
 * Servlet implementation class ServletLogin
 */
public class ServletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    
    
	

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	

		
	public ServletLogin()  {
		super();		
		
		
		// TODO Auto-generated constructor stub
	}


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		 if(request.getSession(false).getAttribute("user")!=null)
		 {
			 response.sendRedirect("./Success");
		 }
		 else{
		   System.out.println("utente non trovato, cerco nel DB");
	
		
			
			DB gis = new DB();
			Boolean checked = gis.check(request.getParameter("email"), request.getParameter("password"));
			UserBean ut = new UserBean(request.getParameter("email"), request.getParameter("password"));
			ut.setName(gis.getName_last_user());

			if (checked) {
				HttpSession session = request.getSession();
				session.setAttribute("user", request.getParameter("email"));
				session.setAttribute("name", ut.getName());
				UsersOnline us=new UsersOnline(session.getId(),(String)session.getAttribute("name"),
						(String)session.getAttribute("user"));
				SessionCounter contatoreSessione= (SessionCounter)session.getAttribute(SessionCounter.COUNTER);
				contatoreSessione.updateSession(session.getId(), us);
			
 	           session.setMaxInactiveInterval(30*60);
				response.sendRedirect("./Success");
			
			} else {
			
				request.getRequestDispatcher("/WEB-INF/loginError.jsp").forward(request, response);
				
			}
			
		 }
		
		
	}

}