package biblioteka;

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
        if(!listaKnjiga.contains(knjiga) || !listaKorisnika.contains(korisnik)){
            throw new IllegalStateException("Ne moze se napraviti pozajmica. Knjiga ne postoji u biblioteci ili korisnik nije uclanjen");
        }
        else if (!knjiga.isDostupna()) {
            throw new IllegalStateException("Žao nam je, ova knjiga je vec izdata.");

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
                listaPozajmica.remove(p);  //potencijalno problematicno u for each petlji
                arhivaPozajmica.add(p);
                return;
            }
        } throw new  IllegalStateException("Ova pozajmica ne postoji. Ovaj korisnik nije uzeo ovu knjigu!");
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

    public ArrayList<Knjiga> getNajpopularnijeKnjige() {
        if (Pozajmica.getUkupanBrPozajmica() < 2 || listaKnjiga.size()<2) {
        System.out.println("Nema dovoljno knjiga ili pozajmica da bi se znale najpopularnije.");
        return new ArrayList<>();
    }
        Knjiga najcitanijaK = listaKnjiga.get(0);
        Knjiga najcitanijaK2 = listaKnjiga.get(1);
        if(najcitanijaK2.getBrPozajmica()<najcitanijaK.getBrPozajmica()) {
            Knjiga temp = najcitanijaK2;
            najcitanijaK2 = najcitanijaK;
            najcitanijaK = temp;
        }  for (int i = 2; i < listaKnjiga.size(); i++) {
                if (najcitanijaK.getBrPozajmica() <= listaKnjiga.get(i).getBrPozajmica()) {
                    najcitanijaK2=najcitanijaK;
                    najcitanijaK=listaKnjiga.get(i);
                } else if (najcitanijaK2.getBrPozajmica() <= listaKnjiga.get(i).getBrPozajmica()){
                    najcitanijaK2 = listaKnjiga.get(i);
                }
        } ArrayList<Knjiga> najcitanijeKnjige = new ArrayList<>();
          najcitanijeKnjige.add(najcitanijaK);
          najcitanijeKnjige.add(najcitanijaK2);
        return najcitanijeKnjige;
    }

    public ArrayList<Korisnik> getKorisniciSortiraniPoBrPozajmicaOdMax(){
           ArrayList<Korisnik> korisniciSortiraniPoBrojuPozajmicaOdMax = new ArrayList<>(listaKorisnika);
            korisniciSortiraniPoBrojuPozajmicaOdMax.sort((k1, k2) ->
                    Integer.compare(k2.getBrPozajmica(), k1.getBrPozajmica())
            );
            return korisniciSortiraniPoBrojuPozajmicaOdMax;
    }

    public ArrayList<Korisnik> getKorisniciSortiraniPoPrezimenu(){
          ArrayList<Korisnik> korisniciSortiraniPoPrezimenu = new ArrayList<>(listaKorisnika);
              korisniciSortiraniPoPrezimenu.sort((k1, k2) ->
                   k1.getPrezimeIime().compareTo(k2.getPrezimeIime()));
          return korisniciSortiraniPoPrezimenu;
    }

   public ArrayList<Korisnik> getKorisniciSortiraniPoStarostiRastuci(){
        ArrayList<Korisnik> korisniciSortiraniPoStrostiRastuci= new ArrayList<>(listaKorisnika);
            korisniciSortiraniPoStrostiRastuci.sort((k1, k2) ->
             k2.getDatumRodjenja().compareTo(k1.getDatumRodjenja()));
          return korisniciSortiraniPoStrostiRastuci;
   }

    public boolean isDovoljnoKorisnika(int x){
        return listaKorisnika.size()>=x;
    }

    public ArrayList<Korisnik> getNajaktivnijeKorisnike(int x) {
        if (!isDovoljnoKorisnika(x)) {
            System.out.println("Ima manje od " + x + "korisnika!");
            return new ArrayList<>();
        }
            return new ArrayList<>(getKorisniciSortiraniPoBrPozajmicaOdMax().subList(0, x));
    }

   public ArrayList<Korisnik> getNajmladjiKorisnici(int x) {
       if (!isDovoljnoKorisnika(x)) {
           System.out.println("Ima manje od " + x + "korisnika!");
           return new ArrayList<>();
       }
           return new ArrayList<>(getKorisniciSortiraniPoStarostiRastuci().subList(0, x));
   }

   public  List<Korisnik> getNajstarijiKorisnici(int x) {
       if (!isDovoljnoKorisnika(x)) {
           System.out.println("Ima manje od " + x + "korisnika!");
           return new ArrayList<>();
       }
           ArrayList<Korisnik> najstarijiKorisnici = getKorisniciSortiraniPoStarostiRastuci();
           return new ArrayList<>(najstarijiKorisnici.subList(najstarijiKorisnici.size() - x, najstarijiKorisnici.size()));
   }

    @Override
    public String toString() {
        return "Biblioteka:" + listaKorisnika + listaKnjiga + listaPozajmica;
    }

}
