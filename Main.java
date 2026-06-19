package biblioteka;
/*MINI PROJEKAT: Library Tracker (knjige + pozajmice)
Zamisli mali sistem za evidenciju knjiga i njihovog korišćenja u biblioteci.
Osnovni koncept:
Sistem prati:

knjige
audio knjige
evidenciju pozajmica
i kratke statistike korišćenja
🔹 1. Knjiga
Svaka knjiga ima:

naslov
autora
broj strana
broj pozajmica
Ponašanje:
mora da zna da kaže da li je “popularna”
mora da može da poveća broj pozajmica
🔹 2. Audio knjiga

Isto kao knjiga, ali ima:

trajanje (u minutima)
Ponašanje:
ima svoj kriterijum popularnosti (nije isti kao kod knjige)
🔹 3. Pozajmica

Evidentira:

naziv korisnika
knjigu koja je pozajmljena
broj dana trajanja pozajmice
Ponašanje:
mora da zna da li je “duga pozajmica” (po tvom kriterijumu)

🔹 4. Biblioteka
Sadrži:

kolekciju knjiga i audio knjiga
Ponašanje:
ukupno pozajmica svih knjiga
najpopularnija knjiga (po tvom kriterijumu)
uklanjanje knjige
filtriranje “popularnih”

🔹 5. Posebno ponašanje (ti odlučuješ)
U nekom delu sistema moraš da odlučiš:

kako definišeš “popularno”
kako meriš “dugo”
šta znači “najviše korišćeno”
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

import java.time.LocalDate;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        Knjiga k1 =new Knjiga("1984", "George Orwel", 248, 0);
        Knjiga k2 = new Knjiga("Sidarta",  "Herman Hesse", 164, 0);
        Knjiga k3 = new Knjiga("Slikar prolaznog sveta", "Kazuo Ishiguro", 230, 0);

        ArrayList<Knjiga> listaKnjiga= new ArrayList<>();
        listaKnjiga.add(k1);
        listaKnjiga.add(k2);
        listaKnjiga.add(k3);

        Pozajmica p1 = new Pozajmica("Petar",k1, LocalDate.now(), null);
        Pozajmica p2 = new Pozajmica("Maja",k2, LocalDate.of(2026,4,1), null);
        Pozajmica p3 = new Pozajmica("Luka",k3, LocalDate.of(2026,5,7), null);
        System.out.println(Pozajmica.getUkupanBrPozajmica());

        //Biblioteka b1 = new Biblioteka();
        System.out.println(p1);
        System.out.println(p2);
        System.out.println(k2);
        System.out.println(listaKnjiga);
    }
}
