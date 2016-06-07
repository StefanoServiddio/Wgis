package com.serviddio.gis.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.serviddio.gis.model.DAOUser;
import com.serviddio.gis.model.UserLog;

/**
 * Servlet implementation class PositionList
 */
public class PositionList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PositionList() {
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

		response.setContentType("application/json");
		UserLog user_loggato = (UserLog) request.getSession().getAttribute("user");

		if (user_loggato != null) {
			System.out.println("email dell'utente: " + user_loggato.getEmail());
			if (!user_loggato.isAdmin()) {
				JSONObject obj = DAOUser.getIstance().getLanLon(user_loggato.getEmail());
				if (obj != null)
					response.getWriter().write((obj).toString());

			} else {
				JSONArray list_obj = DAOUser.getIstance().getAllUserLanLon();
				if (list_obj != null)
					response.getWriter().write((list_obj).toString());

			}
		} else {
			System.out.println("Nessun Login effettuato");
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
