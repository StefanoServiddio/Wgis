package com.serviddio.gis.rest;


import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import org.json.*;

import com.serviddio.gis.model.DAOUser;
import com.serviddio.gis.tools.Crittog;

import javax.ws.rs.core.MediaType;


@Path("geopos")
public class GeoPosRest {
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String getHtmlMessage() {
		// Return some cliched textual content
		return "<h1 align=\"center\">Geo Server Up ...<h1>";
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String checkLogin(String msg) {
		
		System.out.println("Stringa di input"+msg);
		JSONObject req= new JSONObject(msg);
		
	
		System.out.println("latitude: "+req.getString("user_lat"));
        System.out.println("longitude: "+req.getString("user_long"));
		
		
		JSONObject resp = new JSONObject().put("ack","true");

		return resp.toString();

	}

}
