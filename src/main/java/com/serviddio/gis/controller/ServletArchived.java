package com.serviddio.gis.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.serviddio.gis.model.DAOUser;
import com.serviddio.gis.model.UserLog;

/**
 * Servlet implementation class Archived
 */
public class ServletArchived extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletArchived() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println(request.getParameter("email"));
	 	System.out.println("Eseguo archiviazione");
	 	UserLog usr = (UserLog)request.getSession().getAttribute("user");
		if(usr.isAdmin())
		{
	
		if (DAOUser.getIstance().toogleArchived(request.getParameter("email")))

			System.out.println("Modifica Completata con Successo");
		else

			System.out.println("Modifica  Errata");
		}

	}
	

}
