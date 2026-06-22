package biblioteka;
/*3. Pozajmica
Evidentira:

naziv korisnika
knjigu koja je pozajmljena
broj dana trajanja pozajmice
Ponašanje:
mora da zna da li je “duga pozajmica” (po tvom kriterijumu)
*/

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Pozajmica {
    private static int ukupanBrPozajmica;
    private Korisnik korisnik;
    private Knjiga knjiga;
    private LocalDate danIzdavanja;
    private LocalDate danVracanja;
    private int brPozajmice;

    public Pozajmica(Korisnik korisnik, Knjiga knjiga){
        this.korisnik=korisnik;
        this.knjiga=knjiga;
        this.danIzdavanja=null;
        this.danVracanja = null;
        ukupanBrPozajmica++;
        brPozajmice=ukupanBrPozajmica;// ili umesto dva reda koda: brPozajmica==++ukupanBrojPozajmica
    }

  public static int getUkupanBrPozajmica(){
        return ukupanBrPozajmica;
  }

 public int izracunajBrDana(){
        LocalDate krajnjiDatum = (danVracanja==null)? LocalDate.now(): danVracanja;
        long brDana= ChronoUnit.DAYS.between(danIzdavanja, krajnjiDatum);
        return (int)brDana;
  }
   public boolean prekoracenje(){
        return izracunajBrDana()>30;
   }

   public void kolikoDanaKasni() { //metodu koristi samo biblioteka prilikom vracanja knjige!
           if ( danVracanja != null && !prekoracenje()) {
               System.out.println("Knjiga je vracena u roku!");
           } else {
               int daniZakasnjenja = izracunajBrDana() - 30;
               System.out.println("Korisnik kasni s vracanjem knjige :" + daniZakasnjenja +"dana,");
           }
       }

    public Knjiga getKnjiga() {
        return knjiga;
    }

    public void setKnjiga(Knjiga knjiga) {
        this.knjiga = knjiga;
    }

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
    }
    public LocalDate getDanIzdavanja() {
        return danIzdavanja;
    }

    public void setDanIzdavanja(LocalDate danIzdavanja) {
        this.danIzdavanja = danIzdavanja;
    }

    public LocalDate getDanVracanja() {
        return danVracanja;
    }
    public void setDanVracanja(LocalDate danVracanja) {
        this.danVracanja = danVracanja;
    }

    @Override
    public String toString() {
        return "Pozajmica{" +
                 korisnik.getPrezimeIime() + ", clanski br: " + korisnik.getClanskiBroj() +
                ", knjiga: '" + getKnjiga().getNaslov() + '\'' +
                ", brDana: " + izracunajBrDana()  +
                ", danIzdavanja: " + getDanIzdavanja()  +
                ", danVracanja: " + getDanVracanja()  +
                '}';
    }
}
