package biblioteka;
/*4. Biblioteka
Sadrži:

kolekciju knjiga i audio knjiga
Ponašanje:
ukupno pozajmica svih knjiga
najpopularnija knjiga (po tvom kriterijumu)
uklanjanje knjige
filtriranje “popularnih”*/

import java.time.LocalDate;
import java.util.ArrayList;

public class Biblioteka {

    private ArrayList<Knjiga>listaKnjiga;
    private ArrayList<Korisnik> listaKorisnika;
    private ArrayList<Pozajmica> listaPozajmica;
    private ArrayList<Pozajmica> arhivaPozajmica;
    public Biblioteka(ArrayList<Knjiga> listaKnjiga, ArrayList<Korisnik> listaKorisnika){
        if(listaKnjiga== null){
            throw  new NullPointerException("Biblioteka mora posedovati listu knjiga!");
        } this.listaKnjiga=listaKnjiga;
        if(listaKorisnika== null){
            throw  new NullPointerException("Biblioteka mora posedovati listu korisnika!");
        }this.listaKorisnika=listaKorisnika;
        this.listaPozajmica = new ArrayList<>();
        this.arhivaPozajmica = new ArrayList<>();
    }

   public int ukupnoPozajmica(){
        return Pozajmica.getUkupanBrPozajmica();
    }

    public Pozajmica izdajKnjigu(Korisnik korisnik, Knjiga knjiga){
        if(!knjiga.isDostupna()){
            System.out.println("Žao nam je, ova knjiga je vec izdata.");
            return null;
        }
            Pozajmica p = new Pozajmica(korisnik, knjiga);
            knjiga.setDostupna(false);
            p.setDanIzdavanja(LocalDate.now());
            listaPozajmica.add(p);
            knjiga.povecajBrPozajmica();
            System.out.println(p);
            return p;
    }
    public void vratiKnjigu(Pozajmica p) {
        p.setDanVracanja(LocalDate.now());
        p.kolikoDanaKasni();
        p.getKnjiga().setDostupna(true);
        listaPozajmica.remove(p);
        arhivaPozajmica.add(p);
    }
    public ArrayList<Knjiga> getListaKnjiga() {
        return listaKnjiga;
    }

    public void setListaKnjiga(ArrayList<Knjiga> listaKnjiga) {
        this.listaKnjiga = listaKnjiga;
    }

    public ArrayList<Korisnik> getListaKorisnika() {
        return listaKorisnika;
    }

    public void setListaKorisnika(ArrayList<Korisnik> listaKorisnika) {
        this.listaKorisnika = listaKorisnika;
    }


    public ArrayList<Pozajmica> getListaPozajmica() {
        return listaPozajmica;
    }

    public void setListaPozajmica(ArrayList<Pozajmica> listaPozajmica) {
        this.listaPozajmica = listaPozajmica;
    }

    @Override
    public String toString() {
        return "Biblioteka:" + listaKorisnika + listaKnjiga + listaPozajmica;
    }
    /*public Knjiga najpopulrnija(){

    }*/
}
