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
    private String korisnik;
    private Knjiga knjiga;
    private LocalDate danIzdavanja;
    private LocalDate danVracanja;

    public Pozajmica(String korisnik, Knjiga knjiga, LocalDate danIzdavanja, LocalDate danVracanja){
        this.korisnik=korisnik;
        this.knjiga=knjiga;
        this.danIzdavanja=danIzdavanja;
        this.danVracanja = danVracanja;
        ukupanBrPozajmica++;
    }

  public static int getUkupanBrPozajmica(){
        return ukupanBrPozajmica;
  }

  public int izracunajBrDana(){
        long brDana= ChronoUnit.DAYS.between(danIzdavanja, LocalDate.now());
        return (int)brDana;
  }
   public boolean prekoracenje(){
        return izracunajBrDana()>30;
   }

    public Knjiga getKnjiga() {
        return knjiga;
    }

    public void setKnjiga(Knjiga knjiga) {
        this.knjiga = knjiga;
    }

    public String getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(String korisnik) {
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
                "korisnik='" + korisnik + '\'' +
                ", knjiga='" + getKnjiga().getNaslov() + '\'' +
                ", brDana=" + izracunajBrDana()  +
                ", danIzdavanja=" + getDanIzdavanja()  +
                ", danVracanja=" + getDanVracanja()  +
                '}';
    }
}
