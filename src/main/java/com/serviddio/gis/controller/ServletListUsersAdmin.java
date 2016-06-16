package com.serviddio.gis.controller;

import java.io.IOException;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.serviddio.gis.model.DAOUser;
import com.serviddio.gis.model.UserLog;

/**
 * Servlet implementation class ListUsersAdmin
 */
public class ServletListUsersAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletListUsersAdmin() {
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
		UserLog usr=(UserLog)request.getSession().getAttribute("user");
		if(usr!=null && usr.isAdmin()){
		

		List<UserLog> usersList = DAOUser.getIstance().getUsersList();

		request.setAttribute("usersList", usersList);
		if (DAOUser.getIstance().countEl() > -1)
			request.setAttribute("numUtenti", DAOUser.getIstance().countEl());
		request.getRequestDispatcher("/WEB-INF/listUsers.jsp").forward(request, response);
		}
		else
		{
			response.sendRedirect("./Gis");
		}
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
