package com.serviddio.gis.model;

public class UserLog extends User {
	
	
	
	private String password;
	
	public UserLog(){
		super();
		this.password="";
		
	}
	public UserLog(String email, String Password)
	{
		this.setName("");
		this.setEmail(email);
		this.password=Password;
		
	}
	

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	

	

}