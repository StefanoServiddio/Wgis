package com.serviddio.gis.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.sql.*;
import org.postgresql.Driver;

public class DB {

	private Connection conn;
	private String name_last_user = "";

	public String getName_last_user() {
		return name_last_user;
	}

	public void setName_last_user(String name_last_user) {
		this.name_last_user = name_last_user;
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

	public boolean check(String email, String password) {
		try {
			startConnection();
			Statement stmt;
			stmt = conn.createStatement();

			String query = "SELECT nome, email FROM utente WHERE email=" + "\'" + email + "\'" + " AND " + "passw="
					+ "\'" + password + "\'";
			ResultSet res = stmt.executeQuery(query);
			res.next();

			if (res.getRow() > 0) {
				setName_last_user(res.getString("nome"));
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
    public int countEl(){
    	
    	startConnection();
		Statement stmt;
		try {
		stmt = conn.createStatement();

		String query = "SELECT COUNT(id) FROM utente";
		ResultSet res = stmt.executeQuery(query);
		res.next();
		int count=res.getInt(1);
		stmt.close();
		closeConn();
		return count;
		}catch (SQLException e) {

			System.out.println("SQL istruzioni");
			e.printStackTrace();
			
			closeConn();
			return -1;
		}
    	
    }
	public List<UserBean> getUsersList() {

		List<UserBean> utenti = new ArrayList();
		startConnection();
		Statement stmt;
		try {
			stmt = conn.createStatement();
			String query = "SELECT * FROM utente ";
			ResultSet res = stmt.executeQuery(query);

			while (res.next()) {
				UserBean usr = new UserBean();
				usr.setName(res.getString("nome"));

				usr.setEmail(res.getString("email"));

				usr.setPassword(res.getString("passw"));

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
		for (UserBean usr : utenti) {

			System.out.println("nome: " + usr.getName());
			System.out.println("mail: " + usr.getEmail());
			System.out.println("password:" + usr.getPassword());

		}
		return utenti;
		
	}

}