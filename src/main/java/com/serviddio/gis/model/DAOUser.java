package com.serviddio.gis.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.*;

public class DAOUser {

	private Connection conn;
	private String name_last_user = "";
	private int role;
	private static DAOUser istance = null;

	public String getName_last_user() {
		return name_last_user;
	}

	public void setName_last_user(String name_last_user) {
		this.name_last_user = name_last_user;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public static synchronized DAOUser getIstance() {
		if (istance == null) {
			istance = new DAOUser();
		}
		return istance;
	}

	public void startConnection() {

		try {

			Class.forName("org.postgresql.Driver");

			String url = "jdbc:postgresql://localhost:5432/Wgis";
			Properties props = new Properties();
			props.setProperty("user", "stefano");
			props.setProperty("password", "ste86");

			conn = DriverManager.getConnection(url, props);

			System.out.println("Database aperto con successo!");
		} catch (SQLException sql) {

			System.out.println("problema connessione sql");
			sql.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println("Classe non trovata");
			e.printStackTrace();
		}

	}

	public void closeConn() {

		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Non è stato possibile chiudere la connessione con il DB Wgis");
			e.printStackTrace();
		}
	}

	// il check controlla se esiste l'utente e carica role
	public boolean check(String email, String password) {
		try {
			startConnection();
			Statement stmt;
			stmt = conn.createStatement();

			String query = "SELECT nome, email, role FROM utente WHERE email=" + "\'" + email + "\'" + " AND "
					+ "passw=" + "\'" + password + "\'";
			ResultSet res = stmt.executeQuery(query);
			res.next();

			if (res.getRow() > 0) {
				setName_last_user(res.getString("nome"));
				setRole(res.getInt("role"));
				System.out.println("Trovato!");
				stmt.close();
				closeConn();
				return true;

			}

			System.out.println("Non trovato!");
			stmt.close();
			closeConn();
			return false;
		} catch (SQLException s) {
			s.printStackTrace();
			return false;
		}

	}

	public int countEl() {

		startConnection();
		Statement stmt;
		try {
			stmt = conn.createStatement();

			String query = "SELECT COUNT(id) FROM utente";
			ResultSet res = stmt.executeQuery(query);
			res.next();
			int count = res.getInt(1);
			stmt.close();
			closeConn();
			return count;
		} catch (SQLException e) {

			System.out.println("SQL istruzioni");
			e.printStackTrace();

			closeConn();
			return -1;
		}

	}

	public List<UserLog> getUsersList() {

		List<UserLog> utenti = new ArrayList();
		startConnection();
		Statement stmt;
		try {
			stmt = conn.createStatement();
			String query = "SELECT * FROM utente ";
			ResultSet res = stmt.executeQuery(query);

			while (res.next()) {
				UserLog usr = new UserLog();
				usr.setName(res.getString("nome"));

				usr.setEmail(res.getString("email"));

				usr.setPassword(res.getString("passw"));
				usr.setRole(res.getInt("role"));
				usr.setArchived(res.getBoolean("archived"));
				utenti.add(usr);
			}
			stmt.close();

			closeConn();

		} catch (SQLException e) {

			System.out.println("SQL istruzioni");
			e.printStackTrace();
			closeConn();
			return null;
		}

		System.out.println("lista utenti caricata dal Database");
		for (UserLog usr : utenti) {

			System.out.println("nome: " + usr.getName());
			System.out.println("mail: " + usr.getEmail());
			System.out.println("password:" + usr.getPassword());

		}
		return utenti;

	}

	public List<UserOnline> getMobileUserListOnline() {

		List<UserOnline> user_mobile = new ArrayList<UserOnline>();
		startConnection();
		Statement stmt;
		try {
			stmt = conn.createStatement();
			String query = "SELECT nome,email,role FROM utente where mobile='true'; ";
			ResultSet res = stmt.executeQuery(query);

			while (res.next()) {
				UserOnline usr = new UserOnline();
				usr.setName(res.getString("nome"));

				usr.setEmail(res.getString("email"));

				usr.setRole(res.getInt("role"));
				user_mobile.add(usr);
			}
			stmt.close();

			closeConn();

		} catch (SQLException e) {

			System.out.println("SQL istruzioni");
			e.printStackTrace();
			closeConn();
			return null;
		}

		System.out.println("lista utenti caricata dal Database");
		for (UserOnline usr : user_mobile) {

			System.out.println("nome: " + usr.getName());
			System.out.println("mail: " + usr.getEmail());

		}
		return user_mobile;
	}

	public int saveUsr(UserReg user) {
		startConnection();
		Statement stmt;

		try {
			stmt = conn.createStatement();

			String query = "INSERT INTO utente(nome,email,passw,role) VALUES (" + "\'" + user.getName() + "\'" + ","
					+ "\'" + user.getEmail() + "\'" + "," + "\'" + user.getPassword() + "\'" + "," + user.getRole()
					+ ");";
			int result = stmt.executeUpdate(query);

			stmt.close();
			closeConn();
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block

			e.printStackTrace();
			closeConn();
			return 2;
		}

	}

	public Boolean setMobileTrue(String email) {

		startConnection();
		Statement stmt;

		try {
			stmt = conn.createStatement();

			email = email.trim();
			String query = "UPDATE utente " + "set mobile=true where email='" + email + "\';";
			int a = stmt.executeUpdate(query);
			System.out.println("valore query ritornato per User: " + a);

			stmt.close();
			closeConn();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block

			e.printStackTrace();
			closeConn();
			return false;

		}

	}

	public Boolean setMobileFalse(String email) {

		startConnection();
		Statement stmt;

		try {
			stmt = conn.createStatement();

			email = email.trim();
			String query = "UPDATE utente " + "set mobile=false where email='" + email + "\';";
			int a = stmt.executeUpdate(query);
			System.out.println("valore query ritornato per User: " + a);

			stmt.close();
			closeConn();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block

			e.printStackTrace();
			closeConn();
			return false;

		}

	}

	public Boolean getMobileUserState(String email) {

		startConnection();
		Statement stmt;

		try {
			stmt = conn.createStatement();

			email = email.trim();

			String query = "SELECT mobile FROM utente WHERE email=" + "\'" + email + "\'";

			ResultSet res = stmt.executeQuery(query);
			res.next();
			boolean value = res.getBoolean("mobile");
			System.out.println("Mobile User value: " + res.getBoolean("mobile"));

			stmt.close();
			closeConn();
			return value;
		} catch (SQLException e) {
			// TODO Auto-generated catch block

			e.printStackTrace();
			closeConn();
			return false;

		}

	}

	public JSONArray getAllUserLanLon() {

		JSONArray ja = new JSONArray();
		startConnection();
		Statement stmt;

		try {
			stmt = conn.createStatement();

			String query = "SELECT email, lat, lon FROM utente " + " WHERE" + " mobile='" + true + "\';";
			ResultSet res = stmt.executeQuery(query);

			while (res.next()) {
				JSONObject obj = new JSONObject();
				obj.put("user_email", res.getString("email")).put("lat", res.getString("lat")).put("lon",
						res.getString("lon"));
				System.out.println("Oggetto: " + obj.toString());
				ja.put(obj);

			}
			System.out.println("Trasmetto l'array: " + ja.toString());

			stmt.close();
			closeConn();
			return ja;
		} catch (SQLException e) {
			// TODO Auto-generated catch block

			e.printStackTrace();
			closeConn();

		}

		return null;
	}

	public JSONObject getLanLon(String user_email) {
		JSONObject obj = new JSONObject();
		startConnection();
		Statement stmt;

		try {
			stmt = conn.createStatement();

			String email = user_email.trim();

			String query = "SELECT lat, lon FROM utente WHERE email=" + "\'" + email + "\'";

			ResultSet res = stmt.executeQuery(query);
			res.next();

			obj.put("lat", res.getDouble("lat")).put("lon", res.getDouble("lon"));

			System.out.println("user: " + email + "posizione utente" + obj.toString());

			stmt.close();
			closeConn();
			return obj;
		} catch (SQLException e) {
			// TODO Auto-generated catch block

			e.printStackTrace();
			closeConn();

		}

		return null;
	}

	public Boolean tooglePerm(String role, String email) {

		startConnection();
		Statement stmt;

		try {
			stmt = conn.createStatement();

			if (role.equals("Admin")) {
				email = email.trim();
				String query = "update utente" + " set role=1 where" + " email='" + email + "\' ;";

				int a = stmt.executeUpdate(query);
				System.out.println("valore query ritornato per Admin: " + a);

			} else {
				email = email.trim();
				String query = "UPDATE utente " + "set role=0 where email='" + email + "\';";
				int a = stmt.executeUpdate(query);
				System.out.println("valore query ritornato per User: " + a);

			}
			stmt.close();
			closeConn();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block

			e.printStackTrace();
			closeConn();
			return false;

		}

	}
	public Boolean checkArchived(String email) {

		startConnection();
		Statement stmt;
		String query1 = "SELECT archived FROM Utente WHERE email='" + email + "\';";
		
		try {
			stmt = conn.createStatement();

			email = email.trim();
			ResultSet res = stmt.executeQuery(query1);
			System.out.println("utente trovato, modifico stato di archiviazione ");
			res.next();
			if (res.getBoolean("archived"))
			{
				return true;
				
			}
			
			
			stmt.close();
			closeConn();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block

			e.printStackTrace();
			closeConn();
			

		}
		return false;

	}
	public Boolean toogleArchived(String email) {

		startConnection();
		Statement stmt;
		String query1 = "SELECT archived FROM Utente WHERE email='" + email + "\';";
		String query2 = "UPDATE utente" + " SET archived='true' WHERE" + " email='" + email + "\' ;";
		String query3 = "UPDATE utente" + " SET archived='false' WHERE" + " email='" + email + "\' ;";
		int a;
		try {
			stmt = conn.createStatement();

			email = email.trim();
			ResultSet res = stmt.executeQuery(query1);
			System.out.println("utente trovato, modifico stato di archiviazione ");
			res.next();
			if (res.getBoolean("archived"))
			{
				
				a = stmt.executeUpdate(query3);
				System.out.println("utente disabilitato");
			}
			
			else{
				a = stmt.executeUpdate(query2);
				System.out.println("utente attivato");
			}
			
			System.out.println("modifica archiviazione utente  db completata con successo");
			stmt.close();
			closeConn();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block

			e.printStackTrace();
			closeConn();
			return false;

		}

	}

	public Boolean saveGeoInfo(UserGeo user) {

		startConnection();
		Statement stmt;

		try {
			stmt = conn.createStatement();

			String email = user.getEmail().trim();
			String query = "update utente set last_time_pos=" + "\'" + user.getTime() + "\'" + "," + " lat="
					+ user.getLat() + "," + " lon=" + user.getLon() + " where" + " email='" + email + "\' ;";
			System.out.println("query trasmessa: " + query);

			int a = stmt.executeUpdate(query);
			System.out.println("valore ritornato query: " + a);

			stmt.close();
			closeConn();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block

			e.printStackTrace();
			closeConn();
			return false;

		}

	}

	public Boolean setIntervalAllert(String user_email, int interval) {

		startConnection();
		Statement stmt;

		try {
			stmt = conn.createStatement();

			String email = user_email.trim();
			String query = "update utente set interval=" + interval + " where" + " email='" + email + "\' ;";
			System.out.println("query trasmessa: " + query);

			int a = stmt.executeUpdate(query);
			System.out.println("valore ritornato query: " + a);

			stmt.close();
			closeConn();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block

			e.printStackTrace();
			closeConn();
			return false;

		}

	}

}