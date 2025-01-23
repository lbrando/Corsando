package ImplementazioniPostgresDAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Connessione.Connessione;
import DAO.LezioneDAO;
import Modelli.Libreria;
import Modelli.Corso;
import Modelli.Lezione;

public class GetLezioniDAO implements LezioneDAO{

	private Connection connection;
	
	//COSTRUTTORE CHE SI OCCUPA DI APRIRE LA CONNESSIONE
	public GetLezioniDAO() {
		try {
			connection = Connessione.getInstance().getConnection();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Lezione> ritornaLezioni(int id_corso) {
		PreparedStatement scaricaLezioni;
        ArrayList<Lezione> list = new ArrayList<Lezione>();
        
        String nome_lezione = null;
        int durata;
        String descrizione_lezione = null;
        Date dataL;
        int id_lez;
        
        try { 
        scaricaLezioni = connection.prepareStatement("select nome, descrizione, durata, data, id_lezione from lezione where corso_appartenenza = " + id_corso + "group by nome, descrizione, durata, data, id_lezione");
        ResultSet rs = scaricaLezioni.executeQuery();

        while(rs.next()) {
             nome_lezione = rs.getString("nome");
             descrizione_lezione = rs.getString("descrizione");
             durata = rs.getInt("durata");
             dataL = rs.getDate("data");
             id_lez = rs.getInt("id_lezione");
             
             Lezione nomeobj = new Lezione(nome_lezione, durata, descrizione_lezione, dataL, id_lez);
             list.add(nomeobj);
             connection.close();
        }
        rs.close();
    }catch(SQLException e) {
        e.printStackTrace();
    }
        return list;
	}

	@Override
	public boolean rimuoviLezione(int id_lezione) {
		boolean ok = false;
		PreparedStatement rimuoviLezione;
        
        try {
        rimuoviLezione = connection.prepareStatement("delete from lezione where id_lezione = '"+ id_lezione +"'");
        rimuoviLezione.executeUpdate(); 
        
        ok = true;
        connection.close();
        
    }catch(SQLException e) {
        e.printStackTrace();
    }
    return ok;
	}
	
	public boolean inserisciLezione(String nome, String descrizione, int durata, Date data, int corso_app) {
		boolean ok = false;
		
		try {			
			PreparedStatement st = connection.prepareStatement("INSERT INTO lezione(nome, descrizione, durata, data, corso_appartenenza) values(?, ?, ?, ?, ?)");
			st.setString(1, nome);
			st.setString(2, descrizione);
			st.setInt(3, durata);
			st.setDate(4, data);
			st.setInt(5, corso_app);
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
	
	
}
