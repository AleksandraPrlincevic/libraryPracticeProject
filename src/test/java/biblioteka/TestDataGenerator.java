package biblioteka;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

public class TestDataGenerator {
    private static final Random random = new Random();
    private static final String[] prezimena = {"TEST_Juricev", "TEST_Alfirev", "TEST_CicinSain", "TEST_Sladoljev", "TEST_Spanja", "TEST_Cukrov", "TEST_Skocic", "TEST_Markoc", "TEST_Ivas", "TEST_Macukat", "TEST_Mihic", "TEST_Roca"};
    private static final String[] imena = {"Mate", "Tomislav", "Sanja", "Marica", "Orsula", "Blanka", "Antonio", "Frane", "Ivica", "Nera", "Karmela", "Paola", "Lucija"};
    private static final String [] naslovi ={"TEST_Val", "TEST_Hrid", "TEST_Pegula na barci", "TEST_Maslina je neobrana", "TEST_Dica idu centrun", "TEST_Lancuni i intimela na ponistri", "TEST_Bicve i postole moga Ante", "TEST_Bestije", "TEST_Na kantunu", "TEST_Skapulati spizu"};
    private static final String [] autori = {"Ivo Bresan", "Ante Tomic", "Vedrana Ruden", "Miljenko Smolje", "Igor Mandic", "Arsen Dedic", "Dubravka Ugresic"};

    private static String getRandomPrezime(){
        int x = (int)(Math.random()* prezimena.length);
         return prezimena[x];
    }
    private static String getRandomIme(){
        int y = (int)(Math.random()* imena.length);
        return imena[y];
    }
    private static String getRandomPrezimeIme(){
        return getRandomPrezime() + " " + getRandomIme();
    }
    private static LocalDate getRandomDatumRodjenja(){
        int godina = 1930 + random.nextInt(2022-1930+1);
        int mesec = 1+ random.nextInt(12);
        int dan = 1 + random.nextInt(28);
        return LocalDate.of(godina,mesec,dan);
    }

    public static Korisnik napraviRandomKorisnika(){
        return new Korisnik(getRandomPrezimeIme(), getRandomDatumRodjenja());
    }

    private static String getRandomNaslov(){
        int c = (int)(Math.random()* naslovi.length);
        return naslovi[c];
    }
     private static String getRandomAutor(){
        int z = (int)(Math.random()* autori.length);
        return autori[z];
     }
     private static int getRandomBrStrana(){
        int min = 20;
        return (int)(Math.random()*980 + 20);
     }

     public static Knjiga napraviRandomKnjigu(){
        return new Knjiga(getRandomNaslov(), getRandomAutor(), getRandomBrStrana());
     }
     public static Knjiga[] napraviRandomKnjige(int x) {
         Knjiga[] knjige = new Knjiga[x];
        for(int y = 0; y < x; y++){
            knjige[y] = napraviRandomKnjigu();
        } return knjige;
     }

}

