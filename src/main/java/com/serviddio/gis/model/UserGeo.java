package com.serviddio.gis.model;

import java.sql.Timestamp;

public class UserGeo extends User {
	private double lat;

	private double lon;
	private Timestamp time;

	public UserGeo() {
		super();
	}

	public UserGeo(String user_email, double lat, double lon, Timestamp time) {
		this.setEmail(user_email);
		this.lat = lat;
		this.lon = lon;
		this.time = time;

	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLon() {
		return lon;
	}

	public void setLon(double lon) {
		this.lon = lon;
	}

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

}
