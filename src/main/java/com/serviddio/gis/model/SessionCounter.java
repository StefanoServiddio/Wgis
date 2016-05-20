package com.serviddio.gis.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import com.serviddio.*;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionCounter implements HttpSessionListener {
	private List<UsersOnline> sessions;

	public static final String COUNTER = "session-counter";

	public SessionCounter() {
		sessions = new ArrayList<UsersOnline>();
		System.out.println("Sessione create, dimensione lista: " + sessions.size());
	}

	public void sessionCreated(HttpSessionEvent event) {
		System.out.println("Dimensione session" + sessions.size());
		System.out.println("SessionCounter.sessionCreated");
		UsersOnline user = null;
		HttpSession session = event.getSession();
		if (session.getAttribute("user") == null || session.getAttribute("name") == null)
			user = new UsersOnline(session.getId(), "","");

		else
			user = new UsersOnline(session.getId(), (String) session.getAttribute("name"),
					(String) session.getAttribute("user"));
		sessions.add(user);
		System.out.println("Dimensione session" + sessions.size());
		session.setAttribute(SessionCounter.COUNTER, this);
	}

	public void sessionDestroyed(HttpSessionEvent event) {
		System.out.println("SessionCounter.sessionDestroyed");
		HttpSession session = event.getSession();
		UsersOnline user = new UsersOnline(session.getId(), (String) session.getAttribute("name"),
				(String) session.getAttribute("user"));

		sessions.remove(user);
		System.out.println("Dimensione session" + sessions.size());
		session.setAttribute(SessionCounter.COUNTER, this);

	}

	public List<UsersOnline> getSessions() {
		return sessions;
	}

	public void setSessions(List<UsersOnline> sessions) {
		this.sessions = sessions;
	}

	public void updateSession(String id, UsersOnline uo) {
		
		for (Iterator<UsersOnline> it = sessions.iterator(); it.hasNext();) {
			UsersOnline userOnl=it.next();
			
			if(userOnl.getId().equals(id))
	      {
	    	  sessions.remove(userOnl);
	    	  sessions.add(uo);
	      }

		}

	}

	public int getActiveSessionNumber() {
		System.out.println("Dimensione session" + sessions.size());
		return sessions.size();
	}

}
