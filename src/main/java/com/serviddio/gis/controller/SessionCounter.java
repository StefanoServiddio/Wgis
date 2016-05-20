package com.serviddio.gis.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import com.serviddio.gis.model.*;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.serviddio.gis.model.UsersOnline;

public class  SessionCounter implements HttpSessionListener {
	private List<UsersOnline> sessions;

	public static final String COUNTER = "list-user-online";

	public  SessionCounter() {
		sessions = new ArrayList<UsersOnline>();
		System.out.println("Sessione create, dimensione lista: " + sessions.size());
	}

	public void  sessionCreated(HttpSessionEvent event) {
		System.out.println("SessionCounter.sessionCreated");
		System.out.println("Dimensione session attuale: " + sessions.size());
		
		synchronized(this){
		UsersOnline user = null;
		HttpSession session = event.getSession();
		if (session.getAttribute("user") == null || session.getAttribute("name") == null)
			user = new UsersOnline(session.getId(), "", "");

		else
			user = new UsersOnline(session.getId(), (String) session.getAttribute("name"),
					(String) session.getAttribute("user"));
		sessions.add(user);
		System.out.println("Dimensione session aggiornata" + sessions.size());
		session.setAttribute(SessionCounter.COUNTER, this);
		}
	}

	public  void sessionDestroyed(HttpSessionEvent event) {
		System.out.println("SessionCounter.sessionDestroyed");
		System.out.println("Dimensione session attuale: " + sessions.size());
		synchronized(this){
		HttpSession session = event.getSession();
		System.out.println("Distruggo l'utente" + session.getId() + "\nnome: " + session.getAttribute("name")
				+ "\nemail: " + session.getAttribute("user"));
		
        for (UsersOnline us:sessions){
        	if(us.getId().equals(session.getId()))
        			sessions.remove(us);
        }
		
		System.out.println("Dimensione session aggiornata" + sessions.size());
		session.setAttribute(SessionCounter.COUNTER, this);
		}

	}

	public  List<UsersOnline> getSessions() {
		return sessions;
	}

	public   void setSessions(List<UsersOnline> sessions) {
		this.sessions = sessions;
	}

	public  void updateSession(String id, UsersOnline uo) {

		for (Iterator<UsersOnline> it = sessions.iterator(); it.hasNext();) {
			UsersOnline userOnl = it.next();

			if (userOnl.getId().equals(id)) {
				sessions.remove(userOnl);
				sessions.add(uo);
				System.out.println("Dimensione session Aggiornamento Nome, Email: " + sessions.size());
			}

		}

	}

	public  int getActiveSessionNumber() {
		System.out.println("Dimensione session" + sessions.size());
		return sessions.size();
	}

}
