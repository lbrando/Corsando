package DAO;

import java.sql.Date;

//CLASSE CHE SI OCCUPA DELLA REGISTRAZIONE, LOGIN DI UN UTENTE E RACCOLTA DATI
public interface UtenteDAO {

	boolean logInDB(String username, String cognome, String codice);

	boolean registerInDB(String username, String cognome, String codice); 
	
	public void getUtenteInDB(String username);
	
	
}
