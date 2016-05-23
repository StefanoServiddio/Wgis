package com.serviddio.gis.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.serviddio.gis.model.DAOUser;
import com.serviddio.gis.model.UserLog;
import com.serviddio.gis.model.UserOnline;

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
	
		
			
			DAOUser gis = new DAOUser();
			Boolean checked = gis.check(request.getParameter("email"), request.getParameter("password"));
			UserLog ut = new UserLog(request.getParameter("email"), request.getParameter("password"));
			ut.setName(gis.getName_last_user());
			ut.setRole(gis.getRole());

			if (checked) {
				HttpSession session = request.getSession();
				session.setAttribute("user", ut);
				
				UserOnline us=new UserOnline(session.getId(),ut.getName(),
						ut.getEmail(),ut.getRole());
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