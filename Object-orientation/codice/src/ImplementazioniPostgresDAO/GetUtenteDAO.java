package ImplementazioniPostgresDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;

import javax.swing.JOptionPane;

import Connessione.Connessione;
import DAO.UtenteDAO;

public class GetUtenteDAO implements UtenteDAO{

	private String ecco_Codice = null;

	private String ecco_Cognome = null;

	private String ecco_Nome = null; 

	private Connection con;

	//COSTRUTTORE CHE SI OCCUPA DI APRIRE LA CONNESSIONE
	public GetUtenteDAO() {
		
		try {
			con = Connessione.getInstance().getConnection();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	public void getUtenteInDB(String username) {
		
		try {
			
			String getUser = "select cognome, codice from utente where username = '"+ username + "'";
			Statement richiestaUsername = con.createStatement();
			ResultSet gotUser = richiestaUsername.executeQuery(getUser);
			
			while(gotUser.next()) {
                ecco_Cognome = gotUser.getString("cognome");
                ecco_Codice = gotUser.getString("codice");
            }
			
		}catch(SQLException c) {
		}
	}
	
	@Override
	public boolean logInDB(String username, String cognome, String codice) {
		boolean ok = false;
		
		String eccoUs = null;
		String eccoCogn = null;
		String eccoCodice = null;
		
		try {
			
		while(!(username.equals(eccoUs) && cognome.equals(eccoCogn) && codice.equals(eccoCodice))){
			
		String getUser = "SELECT username FROM Utente where username = '" + username + "'";
		Statement richiestaUsername = con.createStatement();
		ResultSet gotUser = richiestaUsername.executeQuery(getUser);
		gotUser.next();
		eccoUs = gotUser.getString("username");
		
		String getCognome = "SELECT cognome FROM Utente where cognome = '"+ cognome + "'";
		Statement richiestaCognome = con.createStatement();
		ResultSet gotCognome = richiestaCognome.executeQuery(getCognome);
		gotCognome.next();
		eccoCogn = gotCognome.getString("cognome");
		
		
		String getCodice = "SELECT Codice FROM Utente where Codice = '"+ codice + "'";
		Statement richiestaCodice = con.createStatement();
		ResultSet gotCodice = richiestaCodice.executeQuery(getCodice);
		gotCodice.next();
		eccoCodice = gotCodice.getString("codice");
		
		
		ok = true;
		
		con.close();
		}
	}catch(SQLException c) {
	}
		return ok;
	}

	@Override
	public boolean registerInDB(String username, String cognome, String codice) {
		
		boolean ok = false;
		
		try {			
			
			PreparedStatement st = con.prepareStatement("INSERT INTO utente(username, cognome, codice) values(?, ?, ?)");
			st.setString(1, username);
			st.setString(2, cognome);
			st.setString(3, codice);
			st.executeUpdate();
			st.close();
			
			int colonne = Statement.RETURN_GENERATED_KEYS;
			if(colonne > 0) {
				ok = true;
			}
			con.close();
		}catch(SQLException c) {
			JOptionPane.showMessageDialog(null, "Registrazione non riuscita, ritenta.");
		}
		return ok;
	}
	

	public String getCodice() {
		return ecco_Codice;
	}
	

	public String getCognome() {
		return ecco_Cognome;
	}
	

	public String getUsername() {
		return ecco_Nome;
	}
}
