package com.serviddio.gis.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import com.serviddio.gis.model.*;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.serviddio.gis.model.UserOnline;

public class  SessionCounter implements HttpSessionListener {
	private List<UserOnline> sessions;

	public static final String COUNTER = "list-user-online";

	public  SessionCounter() {
		sessions = new ArrayList<UserOnline>();
		System.out.println("Sessione create, dimensione lista: " + sessions.size());
	}

	public void  sessionCreated(HttpSessionEvent event) {
		System.out.println("SessionCounter.sessionCreated");
		System.out.println("Dimensione session attuale: " + sessions.size());
		
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
		HttpSession session = event.getSession();
		System.out.println("Distruggo l'utente: " + session.getId() + "\nnome: " + session.getAttribute("name")
				+ "\nemail: " + session.getAttribute("user")+ "\nruolo: "+(Integer)session.getAttribute("role"));
		
        for (UserOnline us:sessions){
        	if(us.getId().equals(session.getId()))
        			sessions.remove(us);
        }
		
		System.out.println("Dimensione session aggiornata" + sessions.size());
		session.setAttribute(SessionCounter.COUNTER, this);
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

	public  int getActiveSessionNumber() {
		System.out.println("Dimensione session: " + sessions.size());
		return sessions.size();
	}

}
