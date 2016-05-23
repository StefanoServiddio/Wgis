package com.serviddio.gis.model;

public class UserOnline extends User {
	
	private String sessionid;

	public UserOnline(){
		super();
		sessionid="";
		
		
	}
	
	public UserOnline(String id,String name, String email){
		this.sessionid=id;
		this.setName(name);
		this.setEmail(email);
		
		
	}
	public String getId() {
		return sessionid;
	}

	
	public void setId(String id) {
		this.sessionid = id;
	}
	
  
	

}
