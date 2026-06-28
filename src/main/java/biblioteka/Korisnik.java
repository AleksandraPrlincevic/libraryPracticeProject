package biblioteka;

import java.time.LocalDate;

public class Korisnik {
    private String prezimeIime;
    private LocalDate datumRodjenja;
    private static int pomocniBroj = 100;
    private int clanskiBroj;
    private int brPozajmica;

    public Korisnik(String prezimeIime, LocalDate datumRodjenja){
        this.prezimeIime=prezimeIime;
        this.datumRodjenja=datumRodjenja;
        this.clanskiBroj = pomocniBroj;
        pomocniBroj++; // ova dva reda koda su se mogla napisati i skraceno: this.clanskiBroj=pomocniBroj++
        this.brPozajmica = 0;
    }

    public String getPrezimeIime() {
        return prezimeIime;
    }

    public void setPrezimeIime(String prezimeIime) {
        this.prezimeIime = prezimeIime;
    }

    public int getClanskiBroj() {
        return clanskiBroj;
    }

    public LocalDate getDatumRodjenja() {
        return datumRodjenja;
    }

    public void setDatumRodjenja(LocalDate datumRodjenja) {
        this.datumRodjenja = datumRodjenja;
    }

    public int getBrPozajmica() {
        return brPozajmica;
    }
   public void povecajBrPozajmica(){
        brPozajmica ++;
   }
    @Override
    public String toString() {
        return "Korisnik{" +
                "prezimeIime='" + prezimeIime + '\'' +
                ", clanskiBroj=" + clanskiBroj +
                ", datumRodjenja=" + datumRodjenja +
                ", brPozajmica=" + brPozajmica +
                '}';
    }


}
