package ImplementazioniPostgresDAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Connessione.Connessione;
import DAO.StudenteDAO;
import Modelli.Libreria;
import Modelli.Corso;
import Modelli.Lezione;
import Modelli.Studente;


//implementare tutte le cose che sono state messe all'interno della DAO
//ritorna studenti, inserisci studenti, rimuovi studenti

public class GetStudentiDAO implements StudenteDAO {
	private Connection connection;
	
	public GetStudentiDAO() {
		try {
			connection = Connessione.getInstance().getConnection();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public ArrayList<Studente> ritornaStudenti(String matricola) {
		
		PreparedStatement scaricaStudenti;
		ArrayList<Studente> list = new ArrayList<Studente>();
		
		String nome_studente, cognome_studente, matricola_studente;
		int id_cor = 0;
		
		try {
			scaricaStudenti = connection.prepareStatement("select s.nome, s.cognome, s.matricola\n"
					+ "from studente as s\n"
					+ "where s.matricola = " + matricola + " \n"
					+ "group by s.cognome");
			
			ResultSet rs = scaricaStudenti.executeQuery();
			
			 while(rs.next()) {
	             nome_studente = rs.getString("nome");
	             cognome_studente = rs.getString("cognome");
	             matricola_studente = rs.getString("matricola");
	             
	             
	             Studente nomeobj = new Studente(nome_studente, cognome_studente, matricola_studente, id_cor);
	             list.add(nomeobj);
	             connection.close();
	        }
	        rs.close();
	    }catch(SQLException e) {
	    }
	    return list;
		}

	@Override
	public boolean rimuoviStudente(String matricola) {
		
		boolean ok = false;
		PreparedStatement rimuoviStudente;
        
        try {
        rimuoviStudente = connection.prepareStatement("delete from studente where matricola = '"+ matricola +"'");
        rimuoviStudente.executeUpdate(); 
        
        ok = true;
        connection.close();
        
    }catch(SQLException e) {

    }
    return ok;
	}

	@Override
	public boolean inserisciStudente(String nome, String cognome, String matricola, int corso_iscritto) {
		boolean ok = false;
		
		try {			
			PreparedStatement st = connection.prepareStatement("INSERT INTO studente(nome, cognome, matricola) values(?, ?, ?)");
			st.setString(1, nome);
			st.setString(2, cognome);
			st.setString(4, matricola);
			st.executeUpdate();
			st.close();
			
			int colonne = Statement.RETURN_GENERATED_KEYS;
			if(colonne > 0) {
				ok = true;
			}
			connection.close();

		}catch(SQLException c) {
		}
		return ok;
		
	}

	public ArrayList<Studente> ricercaStudenteNome(String nomeS){
		
		PreparedStatement scaricaLezioni;
        ArrayList<Studente> list = new ArrayList<Studente>();
        
        String nome_s = null;
        String cognome_s = null;
        String matricola_s = null;
        int corso_s;
        
        try {
        scaricaLezioni = connection.prepareStatement("select nome, cognome, matricola, corso_iscritto from studente where nome = '" + nomeS + "' group by nome, cognome, matricola, corso_iscritto");
        ResultSet rs = scaricaLezioni.executeQuery();

        while(rs.next()) {
             nome_s = rs.getString("nome");
             cognome_s = rs.getString("cognome");
             matricola_s = rs.getString("matricola");
             corso_s = rs.getInt("corso_iscritto");
             
             Studente nomeobj = new Studente(nome_s, cognome_s, matricola_s, corso_s);
             list.add(nomeobj);
             connection.close();
        }
        rs.close();
    }catch(SQLException e) {
        e.printStackTrace();
    }
        return list;
	}
	
	public ArrayList<Studente> ricercaStudenteCorso(String nome_corso){
		PreparedStatement scaricaLezioni;
        ArrayList<Studente> list = new ArrayList<Studente>();
        
        String nome_s = null;
        String cognome_s = null;
        String matricola_s = null;
        int corso_s = 0;
        
        try {
        scaricaLezioni = connection.prepareStatement("select s.nome, s.cognome, s.matricola, s.corso_iscritto from studente as s\n"
        		+ "join corso as c on s.corso_iscritto = c.id_corso\n"
        		+ "where c.nome = '" + nome_corso + "'");
        ResultSet rs = scaricaLezioni.executeQuery();

        while(rs.next()) {
             nome_s = rs.getString("nome");
             cognome_s = rs.getString("cognome");
             matricola_s = rs.getString("matricola");
             corso_s = rs.getInt("corso_iscritto");
             
             Studente nomeobj = new Studente(nome_s, cognome_s, matricola_s, corso_s);
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
