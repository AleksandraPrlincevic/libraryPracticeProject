package biblioteka;
/*4. Biblioteka
Sadrži:

kolekciju knjiga i audio knjiga
Ponašanje:
ukupno pozajmica svih knjiga
najpopularnija knjiga (po tvom kriterijumu)
uklanjanje knjige
filtriranje “popularnih”*/

import javax.swing.plaf.PanelUI;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Biblioteka {

    private ArrayList<Knjiga> listaKnjiga;
    private ArrayList<Korisnik> listaKorisnika;
    private ArrayList<Pozajmica> listaPozajmica;
    private ArrayList<Pozajmica> arhivaPozajmica;

    public Biblioteka() {
        this.listaKnjiga = new ArrayList<>();
        this.listaKorisnika = new ArrayList<>();
        this.listaPozajmica = new ArrayList<>();
        this.arhivaPozajmica = new ArrayList<>();
    }

    public int ukupnoPozajmica() {
        return Pozajmica.getUkupanBrPozajmica();
    }

    public void dodajKnjigu(Knjiga... knjige) {
        for (Knjiga k : knjige) {
            listaKnjiga.add(k);
        }
    }

    public void dodajKorisnika(Korisnik... korisnici) {
        for (Korisnik c : korisnici) {
            listaKorisnika.add(c);
        }
    }

    public Pozajmica izdajKnjigu(Korisnik korisnik, Knjiga knjiga) {
        if (!knjiga.isDostupna()) {
            System.out.println("Žao nam je, ova knjiga je vec izdata.");
            return null;
        }
        Pozajmica p = new Pozajmica(korisnik, knjiga);
        knjiga.setDostupna(false);
        p.setDanIzdavanja(LocalDate.now());
        listaPozajmica.add(p);
        knjiga.povecajBrPozajmica();
        korisnik.povecajBrPozajmica();
        System.out.println(p);
        return p;
    }

    public void vratiKnjigu(Korisnik korisnik, Knjiga knjiga) {
        for (Pozajmica p : listaPozajmica) {
            if (p.getKorisnik().equals(korisnik) && p.getKnjiga().equals(knjiga)) {
                p.setDanVracanja(LocalDate.now());
                p.kolikoDanaKasni();
                p.getKnjiga().setDostupna(true);
                listaPozajmica.remove(p);
                arhivaPozajmica.add(p);
                break;
            }
        }
    }

    public ArrayList<Pozajmica> prikaziKasnjenja() {
        ArrayList<Pozajmica> zakasnelePozajmice = new ArrayList<>();
        for (Pozajmica p : listaPozajmica) {
            if (p.prekoracenje()) {
                zakasnelePozajmice.add(p);
            }
        }
        System.out.println(zakasnelePozajmice);
        return zakasnelePozajmice;
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

    public ArrayList<Pozajmica> getArhivaPozajmica() {
        return arhivaPozajmica;
    }

    public ArrayList<Knjiga> najpopularnije() {
        Knjiga najcitanijaK = listaKnjiga.get(0);
        Knjiga najcitanijaK2 = listaKnjiga.get(1);
        if(najcitanijaK2.getBrPozajmica()<najcitanijaK.getBrPozajmica()){
            Knjiga temp = najcitanijaK2;
            najcitanijaK2 = najcitanijaK;
            najcitanijaK = temp;
        }
        if (Pozajmica.getUkupanBrPozajmica() < 4 || listaKnjiga.size()<2) {
            System.out.println("Nema dovoljno knjiga ili pozajmica da bi se znale najpopularnije.");
            return null;
        } else {
            for (int i = 2; i < listaKnjiga.size(); i++) {
                if (najcitanijaK.getBrPozajmica() <= listaKnjiga.get(i).getBrPozajmica()) {
                    najcitanijaK2=najcitanijaK;
                    najcitanijaK=listaKnjiga.get(i);
                } else if (najcitanijaK2.getBrPozajmica() <= listaKnjiga.get(i).getBrPozajmica()){
                    najcitanijaK2 = listaKnjiga.get(i);
                }
            }
        } ArrayList<Knjiga> najcitanijeKnjige = new ArrayList<>();
          najcitanijeKnjige.add(najcitanijaK);
          najcitanijeKnjige.add(najcitanijaK2);
        return najcitanijeKnjige;
    }

    public ArrayList<Korisnik> najaktivnijiKorisnici(){
        if(Pozajmica.getUkupanBrPozajmica()<4 || listaKorisnika.size()<2){
            System.out.println("Nema dovoljno korisnika ili pozjmica da bi se nasli najaktivniji korisnici");
            return null;
        } else {
            listaKorisnika.sort((k1, k2) ->
                    Integer.compare(k2.getBrPozajmica(), k1.getBrPozajmica())
            );
            return new ArrayList<>(listaKorisnika.subList(0, 2));
        }
    }
    @Override
    public String toString() {
        return "Biblioteka:" + listaKorisnika + listaKnjiga + listaPozajmica;
    }
}
