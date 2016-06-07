package com.serviddio.gis.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.serviddio.gis.model.DAOUser;

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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("application/json");
		
		System.out.println("email dell'utente: "+request.getParameter("user"));
		double[] pos=DAOUser.getIstance().getLanLon(request.getParameter("user"));
		if(pos!=null)
		{
			JSONObject json_obj=new JSONObject().put("lat",pos[0] ).put("lon",pos[1]);
		 response.getWriter().write(( json_obj).toString());
		
	
		 
		}
		 
		
	}

}
