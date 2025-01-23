package DAO;

import java.util.ArrayList;
import Modelli.Studente;

public interface StudenteDAO {

	public ArrayList<Studente> ritornaStudenti(String matricola); //mostra una lista di studenti
	
	boolean inserisciStudente(String nome, String cognome, String matricola, int corso_iscritto); //inserisce lo studente

	public boolean rimuoviStudente(String matricola); //elimina uno studente
	
	public ArrayList<Studente> ricercaStudenteNome(String nomeS);
	
	public ArrayList<Studente> ricercaStudenteCorso(String nome_corso);
	
	
}
