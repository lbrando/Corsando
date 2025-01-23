package Modelli;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Date;

public class Corso {

	String id_appar;

	int id_corso;

	int numero_lezioni;

	String nome_corso;

	String tematica_corso;
	
	String descrizione_corso;
	

	public Corso(String id_utente, String nome, String tematica, String descrizione, int numerolezioni, int idCorso) {
		id_appar = id_utente;
		nome_corso = nome;
		tematica_corso = tematica;
		id_corso = idCorso;
		descrizione_corso = descrizione;
		numero_lezioni = numerolezioni;
	}

	public String getNomeCorso() {
		return nome_corso;
	}

	public String getTematica() {
		return tematica_corso;
	}

	public int getNumeroLezioni() {
		return numero_lezioni;
	}

	public void setnumeroLezioni(int numerolezioni) {
		numero_lezioni = numerolezioni;
	}
	
	public int getIDCorso() {
		return id_corso;
	}

	@Override
	public String toString() {
		return nome_corso;
	}
	
	public String getDescrizione() {
		return descrizione_corso;
	}
}
