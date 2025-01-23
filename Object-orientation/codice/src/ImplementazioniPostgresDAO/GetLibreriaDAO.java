package ImplementazioniPostgresDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import Connessione.Connessione;
import DAO.LibreriaDAO;
import Modelli.Libreria;
import Modelli.Corso;

public class GetLibreriaDAO implements LibreriaDAO{

	private Connection connection;
	
	//COSTRUTTORE CHE SI OCCUPA DI APRIRE LA CONNESSIONE
	public GetLibreriaDAO() {
		try {
			connection = Connessione.getInstance().getConnection();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Libreria ritornaLibreria(String id_utente) {
		PreparedStatement scaricaCorsoPS;
        Libreria l = new Libreria(0);

        String nome_corso = null;
        String tematica_corso = null;
        String descrizione = null;
        int numerolezioni = 0;
        int id_corso = 0;
        
        try {
        scaricaCorsoPS = connection.prepareStatement(
                "SELECT nome, descrizione, lezioni, tematica, id_corso FROM corso WHERE id_utente = '" + id_utente + "'"); 
        ResultSet rs = scaricaCorsoPS.executeQuery();

        while(rs.next()) {
             nome_corso = rs.getString("nome");
             descrizione = rs.getString("descrizione");
             numerolezioni = rs.getInt("lezioni");
             tematica_corso = rs.getString("tematica");
             id_corso = rs.getInt("id_corso");

             
             Corso nomeobj = new Corso(id_utente, nome_corso, tematica_corso, descrizione, numerolezioni, id_corso);
             nomeobj.setnumeroLezioni(numerolezioni);
             
             l.addCorso(nomeobj);
             
             connection.close();
        }
        rs.close();
    }catch(SQLException e) {
        e.printStackTrace();
    }
    return l;
	}

}

