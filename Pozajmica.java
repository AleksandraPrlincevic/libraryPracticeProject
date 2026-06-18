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
    private static int brPozajmica;
    private String korisnik;
    private Knjiga knjiga;
    private LocalDate danIzdavanja;

    public Pozajmica(String korisnik, Knjiga knjiga, LocalDate danIzdavanja){
        this.korisnik=korisnik;
        this.knjiga=knjiga;
        this.danIzdavanja=danIzdavanja;
        brPozajmica++;
    }

  public static int getBrPozajmica(){
        return brPozajmica;
  }
git
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

    @Override
    public String toString() {
        return "Pozajmica{" +
                "korisnik='" + korisnik + '\'' +
                ", knjiga='" + getKnjiga().getNaslov() + '\'' +
                ", brDana=" + danIzdavanja +
                '}';
    }
}
