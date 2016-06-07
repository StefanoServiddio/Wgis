package com.serviddio.gis.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;


import com.serviddio.gis.model.UserOnline;

public class  SessionCounter implements HttpSessionListener {
	public static boolean isNull=true;
	private List<UserOnline> sessions;
	
	public static final String COUNTER = "list-users-online";

	public  SessionCounter() {
		sessions = new ArrayList<UserOnline>();
		System.out.println("Sessione create, dimensione lista: " + sessions.size());
	}
	
	
	
	
	public void  sessionCreated(HttpSessionEvent event) {
		System.out.println("SessionCounter.sessionCreated");
		System.out.println("Dimensione session attuale: " + sessions.size());
		isNull=false;
		
		synchronized(this){
		UserOnline user = null;
		HttpSession session = event.getSession();
		if (session.getAttribute("user") == null || session.getAttribute("name") == null)
			user = new UserOnline(session.getId(), "", "",-1);

		else
			user = new UserOnline(session.getId(), (String) session.getAttribute("name"),
					(String) session.getAttribute("user"), (Integer)session.getAttribute("role"));
		sessions.add(user);
		System.out.println("Dimensione session aggiornata: " + sessions.size());
		session.setAttribute(SessionCounter.COUNTER, this);
		}
	}

	public  void sessionDestroyed(HttpSessionEvent event) {
		System.out.println("SessionCounter.sessionDestroyed");
		System.out.println("Dimensione session attuale: " + sessions.size());
		synchronized(this){
		HttpSession sess = event.getSession();
		System.out.println("Distruggo l'utente: " + sess.getId() + "\nnome: " + sess.getAttribute("name")
				+ "\nemail: " + sess.getAttribute("user")+ "\nruolo: "+(Integer)sess.getAttribute("role"));
		
        for (UserOnline us:sessions){
        	if(us.getId().equals(sess.getId()))
        			sessions.remove(us);
        }
		
		System.out.println("Dimensione session aggiornata" + sessions.size());
		if(sessions.size()==0)
		    isNull=true;
		sess.setAttribute(SessionCounter.COUNTER, this);
		
		}

	}

	public  List<UserOnline> getSessions() {
		return sessions;
	}
	

	public   void setSessions(List<UserOnline> sessions) {
		this.sessions = sessions;
	}

	public  void updateSession(String id, UserOnline uo) {

		for (Iterator<UserOnline> it = sessions.iterator(); it.hasNext();) {
			UserOnline userOnl = it.next();

			if (userOnl.getId().equals(id)) {
				sessions.remove(userOnl);
				sessions.add(uo);
				System.out.println("Dimensione session Aggiornamento Nome, Email: " + sessions.size());
			}

		}

	}
	
	public  synchronized void updateSessionMobile(String user_email)
	{
		for (Iterator<UserOnline> it = sessions.iterator(); it.hasNext();) {
			UserOnline userOnl = it.next();

			if (userOnl.getEmail().equals(user_email)) {
				
				userOnl.setMobile(true);
				System.out.println("l'utente "+user_email + " ha effettuato l'accesso da mobile");
				System.out.println("Dimensione session Aggiornamento: " + sessions.size());
			}

		}
	}
	
	

	public  int getActiveSessionNumber() {
		System.out.println("Dimensione session: " + sessions.size());
		return sessions.size();
	}

}
