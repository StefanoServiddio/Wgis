package com.serviddio.gis.model;

public class UserOnline extends User {

	private String sessionid;
	private boolean mobile;

	public UserOnline() {
		super();
		this.sessionid = "";
		this.mobile = false;

	}

	public UserOnline(String id, String name, String email) {
		this.sessionid = id;
		this.setName(name);
		this.setEmail(email);

	}

	public UserOnline(String id, String name, String email, int role) {
		this.sessionid = id;
		this.setName(name);
		this.setEmail(email);
		this.setRole(role);

	}

	public String getId() {
		return sessionid;
	}

	public void setId(String id) {
		this.sessionid = id;
	}
	
	public boolean isMobile() {
		mobile =DAOUser.getIstance().getMobileUserState(this.getEmail());
		return mobile;
	}
	public void setMobile(boolean mobile) {
		this.mobile = mobile;
	}

}
