package Connessione;

import java.sql.*;

import javax.swing.JOptionPane;

//CLASSE CHE PERMETTE DI APRIRE UNA CONNESSIONE COL DB

public class Connessione {

	private static Connessione instance;
	
	private Connection connection = null;
	
	private String url = "jdbc:postgresql://localhost:5432/corsandodatabase";
	
	private String username = "postgres";
	
	private String password = "onedirection123";
	
	private String driver = "org.postgresql.Driver";
	
	
	
	public Connessione() throws SQLException{
		try {
			Class.forName(driver);
			connection = DriverManager.getConnection(url, username, password);
			
		}catch(ClassNotFoundException ex) {
			System.out.println("Problema DataBase: " + ex.getMessage());
		}
	}
	
	
	public Connection getConnection() {
		return connection;
	}
	
	public static Connessione getInstance() throws SQLException{
		if(instance == null) {
			instance = new Connessione();
		}else if(instance.getConnection().isClosed()) {
			instance = new Connessione();
		}
		return instance;
	}
	
}
