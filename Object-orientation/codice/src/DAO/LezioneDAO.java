package DAO;

import java.sql.Date;
import java.util.ArrayList;

import Modelli.Lezione;

public interface LezioneDAO {
	
	public ArrayList<Lezione> ritornaLezioni(int id_corso);
	
	public boolean rimuoviLezione(int id_lezione);
	
	
	
	public boolean inserisciLezione(String nome, String descrizione, int durata, Date data, int corso_app);
	
}
