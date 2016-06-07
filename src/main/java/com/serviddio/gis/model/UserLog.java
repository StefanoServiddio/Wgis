package com.serviddio.gis.model;

public class UserLog extends User {

	private String password;
	
	public UserLog() {
		super();
		this.password = "";
		
		

	}
	
	public UserLog(String email, String password){
		super();
		this.setName("");
		this.setEmail(email);
		this.password = password;
	}

	public UserLog(String email, String password, int role) {
		this.setName("");
		this.setEmail(email);		
		this.setRole(role);
		this.password = password;

	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	

}