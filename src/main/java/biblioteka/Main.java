package biblioteka;
/*MINI PROJEKAT: Library Tracker (knjige + pozajmice)
Zamisli mali sistem za evidenciju knjiga i njihovog korišćenja u biblioteci.
Osnovni koncept:
Sistem prati:

🔹 4. Biblioteka
Sadrži:

kolekciju knjiga i audio knjiga
Ponašanje:
ukupno pozajmica svih knjiga
najpopularnija knjiga (po tvom kriterijumu)
uklanjanje knjige
filtriranje “popularnih”

🔹 BONUS (QA mindset)

Testiraj:

šta ako knjiga nema nijednu pozajmicu
šta ako audio knjiga ima 0 minuta (ili nelogičan unos)
šta ako ukloniš knjigu koja ne postoji
šta ako ima duplikata knjiga
šta ako lista/biblioteka bude prazna
⚠️ Važno

Neću ti reći:
koje klase da praviš
kako da ih povežeš
koje metode gde idu
To je tvoj deo.

Kad završiš, pošalji kao i do sada — i onda ću ti dati:
review kao za ovaj projekat
OOP optimizacije
i QA edge-case korekcije*/

import java.sql.SQLOutput;
import java.time.LocalDate;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        Knjiga k1 = new Knjiga("1984", "George Orwel", 248);
        Knjiga k2 = new Knjiga("Sidarta", "Herman Hesse", 164);
        Knjiga k3 = new Knjiga("Slikar prolaznog sveta", "Kazuo Ishiguro", 230);
        Knjiga k4 = new Knjiga("Tvrdjava", "Ivica Bresan", 389);
        Knjiga k5 = new Knjiga("Pokoravanje", "Misel Uelbek", 230);
        Knjiga k6 = new Knjiga("Bog malih stvari", "Anundati Roj", 428);



        Korisnik c1 = new Korisnik("Milovic Lucija",  LocalDate.of(2004, 10, 22));
        Korisnik c2 = new Korisnik("Prlincevic Marica",  LocalDate.of(1946, 11, 23));
        Korisnik c3 = new Korisnik("Zivkovic Maja",  LocalDate.of(1971, 8, 20));
        Korisnik c4 = new Korisnik("Bilan Ivica",  LocalDate.of(1966, 2, 7));



        Biblioteka b1 = new Biblioteka();
        b1.dodajKnjigu(k1, k2, k3, k4, k5, k6);
        b1.dodajKorisnika(c1, c2, c3, c4);
        b1.izdajKnjigu(c1, k1);
        b1.izdajKnjigu(c2,k2);
        b1.izdajKnjigu(c1,k3);
        b1.izdajKnjigu(c3,k4);
        b1.izdajKnjigu(c4, k5);
        b1.vratiKnjigu(c1,k1);
        b1.izdajKnjigu(c3,k1);
        b1.vratiKnjigu(c3,k1);
        b1.izdajKnjigu(c4,k1);
        b1.vratiKnjigu(c2,k2);
        b1.izdajKnjigu(c2,k4);

        System.out.println(Pozajmica.getUkupanBrPozajmica());

        System.out.println(k1.getBrPozajmica());
        System.out.println();
        System.out.println(k1.isDostupna());
        System.out.println(b1.getListaPozajmica());
        System.out.println(b1.getNajpopularnijeKnjige());
        System.out.println(b1.getArhivaPozajmica());
    }
}