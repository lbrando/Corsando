package ImplementazioniPostgresDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import javax.swing.JOptionPane;

import Connessione.Connessione;
import DAO.AggiungiDAO;

public class GetAggiungiDAO implements AggiungiDAO{

	private Connection connection;

	//COSTRUTTORE CHE SI OCCUPA DI APRIRE LA CONNESSIONE
	public GetAggiungiDAO() {
		try {
			connection = Connessione.getInstance().getConnection();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean inserisciLezioneInCorso(int id_corso, int id_lezione) {
		
		boolean ok = false;
		 
		try {			
			PreparedStatement st = connection.prepareStatement("INSERT INTO appartenenza(id_corso, id_lezione) values(?, ?)");
			st.setInt(1, id_corso);
			st.setInt(2, id_lezione);
			st.executeUpdate();
			st.close();
			
			int colonne = Statement.RETURN_GENERATED_KEYS;
			if(colonne > 0) {
				ok = true;
			}
			connection.close();
		}catch(SQLException c) {
			JOptionPane.showMessageDialog(null, "La lezione e' gia' stata aggiunta");
		}
		return ok;
	}
	
}
