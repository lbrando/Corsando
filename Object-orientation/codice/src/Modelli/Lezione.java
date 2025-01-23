package Modelli;

import java.sql.Date;

public class Lezione {
	
	String nome_lezione, descrizione_lezione;
	
	int durataL, id_lezione;
	Date dataL; 
	
	public Lezione(String nomeLezione, int durata, String descrizioneLezione, Date data, int idLezione) {
		nome_lezione = nomeLezione;
		durataL = durata;
		descrizione_lezione = descrizioneLezione;
		id_lezione = idLezione;
		dataL = data;
	}
	

	public String getNomeLezione() {
		return nome_lezione;
	}
	
	public String getDescrizioneLezione() {
		return descrizione_lezione;
	}
	
	public Date getDataLezione() {
		return dataL;
	}
	
	public int getDurataLezione() {
		return durataL;
	}
	
	public int getIdLezione() {
		return id_lezione;
	}
	
}
