package Modelli;

import java.sql.SQLException;
import java.util.Date;

import ImplementazioniPostgresDAO.GetUtenteDAO;

public class Utente {

	private String username;

	private String cognome;

	private String codice;

	private Libreria libreria;

	public Utente(String us) {
		username = us;
		GetUtenteDAO utente = new GetUtenteDAO();
		utente.getUtenteInDB(us);
		
		cognome = utente.getCognome();
		codice = utente.getCodice();
		
	}
	
	public String getUsername() {
		return username;
	}

	public String getCodice() {
		return codice;
	}

	public String getCognome() {
		return cognome;
	}
	
	public void setLibreria(Libreria libreria) {
		this.libreria = libreria;
	}
	
	public Libreria getLibreria() {
		return libreria;
	}
	
}
