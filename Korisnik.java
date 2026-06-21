package biblioteka;

import java.time.LocalDate;

public class Korisnik {
    private String prezimeIime;
    private int clanskiBroj;
    private LocalDate datumRodjenja;;

    public Korisnik(String prezimeIime, int clanskiBroj, LocalDate datumRodjenja){
        this.prezimeIime=prezimeIime;
        this.clanskiBroj=clanskiBroj;
        this.datumRodjenja=datumRodjenja;
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

    public void setClanskiBroj(int clanskiBroj) {
        this.clanskiBroj = clanskiBroj;
    }

    public LocalDate getDatumRodjenja() {
        return datumRodjenja;
    }

    public void setDatumRodjenja(LocalDate datumRodjenja) {
        this.datumRodjenja = datumRodjenja;
    }

    @Override
    public String toString() {
        return "Korisnik{" +
                "prezimeIime='" + prezimeIime + '\'' +
                ", clanskiBroj=" + clanskiBroj +
                ", datumRodjenja=" + datumRodjenja +
                '}';
    }
}
