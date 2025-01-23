package Modelli;

import java.util.ArrayList;

public class Libreria {

    private ArrayList<Corso> corso;

    public Libreria(int id_libreria) {
        corso = new ArrayList<Corso>();
    }

    public ArrayList<Corso> getCorso(){
        return corso;
    }

    public void creaLibreria() {
        
    }

    public void addCorso(Corso p) {
        corso.add(p);
    }

}
