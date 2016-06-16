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
			PreparedStatement stmt;
			
			
			
			
			String query = "SELECT nome, email, role FROM utente WHERE email=?  AND passw=? ";
			
			stmt=conn.prepareStatement(query);
			stmt.setString(1, email);
			stmt.setString(2, password);
			ResultSet res = stmt.executeQuery();
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
		PreparedStatement stmt;

		try {
			
			String query = "INSERT INTO utente(nome,email,passw,role) VALUES (? , ? , ? , ?)";
			
			stmt=conn.prepareStatement(query);
			stmt.setString(1, user.getName());
			stmt.setString(2, user.getEmail());
			stmt.setString(3, user.getPassword());
			stmt.setInt(4, user.getRole());
			int result = stmt.executeUpdate();

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
		PreparedStatement stmt;

		try {
			
			email = email.trim();
			String query = "UPDATE utente set mobile=? where email=?";
			stmt=conn.prepareStatement(query);
			stmt.setBoolean(1,true);
			stmt.setString(2, email);
			int a = stmt.executeUpdate();
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
		PreparedStatement stmt;

		try {
			

			email = email.trim();
			String query = "UPDATE utente set mobile=? where email=?";
			stmt=conn.prepareStatement(query);
			stmt.setBoolean(1, false);
			stmt.setString(2, email);
			int a = stmt.executeUpdate();
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
		PreparedStatement stmt;

		try {
			

			email = email.trim();

			String query = "SELECT mobile FROM utente WHERE email=?";
			stmt=conn.prepareStatement(query);
			stmt.setString(1,email);
			ResultSet res = stmt.executeQuery();
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
		PreparedStatement stmt;

		try {
		

			String query = "SELECT email, lat, lon FROM utente  WHERE  mobile=?";
			
			stmt=conn.prepareStatement(query);
			stmt.setBoolean(1, true);
			ResultSet res = stmt.executeQuery();

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
		PreparedStatement stmt;

		try {
		

			
			
			String query = "SELECT lat, lon FROM utente WHERE email=?  AND mobile=?";
			stmt=conn.prepareStatement(query);
			stmt.setString(1, user_email);
			stmt.setBoolean(2, true);
			ResultSet res = stmt.executeQuery();
			res.next();

			obj.put("user_email", user_email).put("lat", res.getDouble("lat")).put("lon", res.getDouble("lon"));

			System.out.println(" utente" + obj.toString());

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
		PreparedStatement stmt;

		try {
			

			if (role.equals("Admin")) {
				
				String query = "update utente set role=1 where email=?";
				stmt=conn.prepareStatement(query);
				stmt.setString(1,email);
				int a = stmt.executeUpdate();
				System.out.println("valore query ritornato per Admin: " + a);

			} else {
				
				String query = "UPDATE utente set role=0 where email=? ";
				stmt=conn.prepareStatement(query);
				stmt.setString(1,email);
				int a = stmt.executeUpdate();
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
		PreparedStatement stmt;
		String query1 = "SELECT archived FROM Utente WHERE email=?";
		
		try {
			stmt = conn.prepareStatement(query1);
            stmt.setString(1,email);
			
			ResultSet res = stmt.executeQuery();
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
		PreparedStatement stmt;
		String query1 = "SELECT archived FROM Utente WHERE email=?";
		String query2 = "UPDATE utente" + " SET archived='true' WHERE email= ?";
		String query3 = "UPDATE utente" + " SET archived='false' WHERE email= ?";
		int a;
		try {
			stmt = conn.prepareStatement(query1);
            stmt.setString(1,email);
		
			ResultSet res = stmt.executeQuery();
			System.out.println("utente trovato, modifico stato di archiviazione ");
			res.next();
			if (res.getBoolean("archived"))
			{
				stmt.close();
				stmt=conn.prepareStatement(query3);
				 stmt.setString(1,email);
					
				a = stmt.executeUpdate();
				System.out.println("utente disabilitato");
			}
			
			else{
				stmt.close();
				stmt=conn.prepareStatement(query2);
				 stmt.setString(1,email);
				
				a = stmt.executeUpdate();
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
		PreparedStatement stmt;

		try {
		

			String query = "update utente set last_time_pos= ? , lat=? , lon=?  WHERE email=?";
			
			System.out.println("query trasmessa: " + query);
			
			stmt=conn.prepareStatement(query);
			stmt.setTimestamp(1, user.getTime());
			stmt.setDouble(2, user.getLat());
			stmt.setDouble(3, user.getLon());
			stmt.setString(4, user.getEmail());
			int a = stmt.executeUpdate();
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

	public Boolean setIntervalAllert(String user_email, int interval, Timestamp time) {

		startConnection();
		PreparedStatement stmt;

		try {
			
           
			
			String query = "update utente set interval=?  , ref_time_update=? WHERE  email=?";
		
			stmt =conn.prepareStatement(query) ;
			stmt.setInt(1, interval);
			stmt.setTimestamp(2, time);
			stmt.setString(3,user_email);
			System.out.println("query trasmessa: " + query);

			int a = stmt.executeUpdate();
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