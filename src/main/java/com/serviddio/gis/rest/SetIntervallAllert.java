package com.serviddio.gis.rest;



import java.sql.Timestamp;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


import org.json.JSONObject;

import com.serviddio.gis.model.DAOUser;



@Path("interval")
public class SetIntervallAllert {
	
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String getHtmlMessage() {
		// Return some cliched textual content
		return "<h1 align=\"center\">Inerval setting enable ...<h1>";
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String checkLogin(String msg) {
		
		System.out.println("Stringa di input"+msg);
		JSONObject req= new JSONObject(msg);
		
	  System.out.println("nuovo intervallo ricevuto: "+req.getInt("interval"));
	
	    DAOUser.getIstance().setIntervalAllert(req.getString("user_email"), req.getInt("interval"),
	    		Timestamp.valueOf(req.getString("time")));
		
		
		JSONObject resp = new JSONObject().put("ack","true");

		return resp.toString();

	}

}
