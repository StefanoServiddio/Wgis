package com.serviddio.gis.model;

import com.serviddio.gis.tools.Crittog;

public class UserReg extends User {
	private String password;
	
	public UserReg(){
		super();
		password="";
	}
   public UserReg(String name, String email, String password){
	   setName(name);
	   setEmail(email);
	   setPassword(password);
	   setRole(0);
   }
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
	
		this.password=Crittog.getIstance().encrypt(password);
	}
	
}
