package Modelli;
import java.sql.SQLException;


public class Studente {

	String nome_studente, cognome_studente, matricola;
	int corso_iscritto; 
	
	
	public Studente(String nomeS, String cognomeS, String matricolaS, int corso_iscrittoS) {
		nome_studente = nomeS;
		cognome_studente = cognomeS;
		matricola = matricolaS;
		corso_iscritto = corso_iscrittoS;
		
	}
	
	public String getNomeStudente() {
		return nome_studente;
	}
	
	public String getCognomeStudente() {
		return cognome_studente;
	}
	
	public String getMatricola() {
		return matricola;
	}
	
	public int getCorsoIsc() {
		return corso_iscritto;
	}
}
