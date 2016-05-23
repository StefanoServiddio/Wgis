package com.serviddio.gis.model;

public class User {
	private String name;
	private String email;
	private int role;

	public User() {
		super();
		name = "";
		email = "";
		role = -1;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
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
	public String returnRole(){
		
		if(role==1)
			return "Admin";
		else
		 if(role==0)
			return "UserLogged";
		 else
			 return "No Role";
	}
	public Boolean isAdmin(){
		if(role==1)
			return true;
		else
			return false;
	}

}