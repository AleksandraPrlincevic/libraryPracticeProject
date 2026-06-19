package biblioteka;
/*4. Biblioteka
Sadrži:

kolekciju knjiga i audio knjiga
Ponašanje:
ukupno pozajmica svih knjiga
najpopularnija knjiga (po tvom kriterijumu)
uklanjanje knjige
filtriranje “popularnih”*/

import java.util.ArrayList;

public class Biblioteka {

    private ArrayList<Knjiga>listaKnjiga;

    public Biblioteka(ArrayList<Knjiga> listaKnjiga){
        if(listaKnjiga== null){
            throw  new NullPointerException("Biblioteka mora posedovati listu knjiga!");
        } this.listaKnjiga=listaKnjiga;
    }
   public int ukupnoPozajmica(){
        return Pozajmica.getUkupanBrPozajmica();
    }
    public void izdajKnjigu(Pozajmica p){
        p.getKnjiga().povecajBrPozajmica();
    }

    public ArrayList<Knjiga> getListaKnjiga() {
        return listaKnjiga;
    }

    public void setListaKnjiga(ArrayList<Knjiga> listaKnjiga) {
        this.listaKnjiga = listaKnjiga;
    }

    @Override
    public String toString() {
        return "Biblioteka{" +
                "listaKnjiga=" + listaKnjiga +
                '}';
    }

    /*public Knjiga najpopulrnija(){

    }*/
}
