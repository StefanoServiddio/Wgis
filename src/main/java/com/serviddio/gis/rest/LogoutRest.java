package com.serviddio.gis.rest;

	
	

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import org.json.*;


import javax.ws.rs.core.MediaType;

import com.serviddio.gis.model.DAOUser;





@Path("logout")
public class LogoutRest {

	/**
	 * Method handling HTTP GET requests. The returned object will be sent to
	 * the client as "text/plain" media type.
	 *
	 * @return String that will be returned as a text/plain response.
	 */
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String getHtmlMessage() {
		// Return some cliched textual content
		return "<h1 align=\"center\">Logout Server Up ...<h1>";
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String checkLogin(String msg) {
		
		System.out.println("Stringa di input"+msg);
		JSONObject req= new JSONObject(msg);
		
	
		System.out.println("utente: "+req.getString("user_email"));
       
		

			System.out.println("controllo superato");
		
			DAOUser.getIstance().setMobileFalse(req.getString("user_email"));
			
	
		JSONObject resp = new JSONObject().put("logout", "true");
		System.out.println("trasmetto: "+resp.toString());

		return resp.toString();

	}

}

	


