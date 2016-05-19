package com.serviddio.gis;

public class UserBean {
	
	
	private String name;
	private String email;
	private String password;
	
	public UserBean(){
		this.name="";
		this.email="";
		this.password="";
		
	}
	public UserBean(String email, String Password)
	{
		this.name="";
		this.email=email;
		this.password=Password;
		
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	

	

}