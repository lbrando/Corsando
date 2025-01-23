
package ImplementazioniPostgresDAO;import java.sql.Connection;

import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Connessione.Connessione;
import DAO.CorsoDAO;
import Modelli.Libreria;
import Modelli.Corso;
import Modelli.Lezione;

public class GetCorsoDAO implements CorsoDAO{

	private Connection connection;
	
	//COSTRUTTORE CHE SI OCCUPA DI APRIRE LA CONNESSIONE
	public GetCorsoDAO() {
		try {
			connection = Connessione.getInstance().getConnection();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean inserisciCorso(String id_utente, String nome, String tematica, String descrizione, int numerolezioni) {
		boolean ok = false;
		
		try {			
			PreparedStatement st = connection.prepareStatement("INSERT INTO corso(nome, descrizione, lezioni, tematica, id_utente) values(?, ?, ?, ?, ?)");
			st.setString(1, nome);
			st.setString(2, descrizione);
			st.setInt(3, numerolezioni);
			st.setString(4, tematica);
			st.setString(5, id_utente);
			st.executeUpdate();
			st.close();
			
			int colonne = Statement.RETURN_GENERATED_KEYS;
			if(colonne > 0) {
				ok = true;
			}
			connection.close();

		}catch(SQLException c) {
			c.printStackTrace();
		}
		return ok;
	}
	

	public boolean eliminaCorso(int id_corso) {
		boolean ok = false;
		
		try {			
			PreparedStatement st = connection.prepareStatement("DELETE FROM corso WHERE id_corso = "+ id_corso);
			st.executeUpdate();
			ok = true;
			st.close();
			connection.close();

		}catch(SQLException c) {
			c.printStackTrace();
		}
		return ok;
	}
	
	
	public ArrayList<Corso> ricercaCorsoNome(String nome, String id_utente){
		PreparedStatement scaricaCorsi;
        ArrayList<Corso> list = new ArrayList<Corso>();
        
        String nome_corso = null;
        String descrizione = null;
        int lezioni = 0;
        String tematica = null;
        int idCorso = 0;
        
        try { 
        scaricaCorsi = connection.prepareStatement("select nome, descrizione, lezioni, tematica, id_corso from corso where nome = '" + nome+ "'");
        ResultSet rs = scaricaCorsi.executeQuery();

        while(rs.next()) {
             nome_corso = rs.getString("nome");
             descrizione = rs.getString("descrizione");
             lezioni = rs.getInt("lezioni");
             tematica = rs.getString("tematica");
             idCorso = rs.getInt("id_corso");
             Corso nomeobj = new Corso(id_utente, nome_corso, tematica, descrizione, lezioni, idCorso);
             list.add(nomeobj);
             connection.close();
        }
        rs.close();
    }catch(SQLException e) {
        e.printStackTrace();
    }
        return list;
	}
	
	
	
	public ArrayList<Corso> ricercaCorsoTematica(String tematica, String id_utente){
		
		PreparedStatement scaricaCorsi;
		ArrayList<Corso> list = new ArrayList<Corso>();
		
		String nome_corso = null;
		String descrizione = null;
		int lezioni = 0;
		String tematica_loc = null;
		int idCorso = 0;
		
		try {
	        scaricaCorsi = connection.prepareStatement("select nome, descrizione, lezioni, tematica, id_corso from corso where tematica = '" + tematica + "'");
	        ResultSet rs = scaricaCorsi.executeQuery();

	        while(rs.next()) {
	             nome_corso = rs.getString("nome");
	             descrizione = rs.getString("descrizione");
	             lezioni = rs.getInt("lezioni");
	             tematica_loc = rs.getString("tematica");
	             idCorso = rs.getInt("id_corso");
	             Corso nomeobj = new Corso(id_utente, nome_corso, tematica_loc, descrizione, lezioni, idCorso);
	             list.add(nomeobj);
	             connection.close();
	        }
	        rs.close();
	    }catch(SQLException e) {
	        e.printStackTrace();
	    }

		return list;
	}
	
	
}
