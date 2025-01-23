package DAO;

//CLASSE CHE SI OCCUPA DELL'AGGIUNTA DELLE LEZIONI IN UN CORSO
public interface AggiungiDAO {
	
	//METODO CHE INSERISCE LE LEZIONI ALL'INTERNO DI UN CORSO
	public boolean inserisciLezioneInCorso(int id_corso, int id_lezione);
	
}
