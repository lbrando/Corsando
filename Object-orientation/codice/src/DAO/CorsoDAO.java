package DAO;

import Modelli.Libreria;

import java.util.ArrayList;

import Modelli.Corso;
import Modelli.Lezione;

public interface CorsoDAO {
	
	//METODO CHE INSERISCE UN CORSO ALL'INTERNO DEL DB
	boolean inserisciCorso(String id_utente, String nome, String tematica, String descrizione, int numerolezioni);

	boolean eliminaCorso(int id_corso);

	
	public ArrayList<Corso> ricercaCorsoNome(String nome, String id_utente);
	
	
	public ArrayList<Corso> ricercaCorsoTematica(String tematica, String id_utente);
}
