package com.serviddio.gis.rest;


import java.sql.Timestamp;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.geotools.geometry.DirectPosition2D;
import org.json.*;

import com.serviddio.gis.model.DAOUser;
import com.serviddio.gis.model.UserGeo;
import com.serviddio.gis.tools.Crittog;
import com.serviddio.gis.tools.TransformPoints;

import javax.ws.rs.core.MediaType;


@Path("geopos")
public class GeoPosRest {
	DAOUser usr=new DAOUser();
	
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
        System.out.println("lastGeoPos: "+ Timestamp.valueOf(req.getString("lastGeoPos")));
        DirectPosition2D points=TransformPoints.convert(Double.valueOf(req.getString("user_lat")), Double.valueOf(req.getString("user_long")));
		System.out.println("lat in EPSG:3857:  "+String.valueOf(points.y));
		System.out.println("lon in EPSG:3857:  "+String.valueOf(points.x));
		UserGeo usrGeo=new UserGeo(req.getString("user_email"),points.y,points.x,
				Timestamp.valueOf(req.getString("lastGeoPos")));
	    DAOUser.getIstance().saveGeoInfo(usrGeo);
		
		
		JSONObject resp = new JSONObject().put("ack","true");

		return resp.toString();

	}

}
