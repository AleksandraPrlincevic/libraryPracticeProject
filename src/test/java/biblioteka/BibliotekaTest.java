package biblioteka;

import com.sun.source.tree.AssertTree;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class BibliotekaTest {

    private Biblioteka biblioteka;

    @BeforeEach
    void  setUp(){
        biblioteka = new Biblioteka();
    }

    @Test
    public void dodajKnjiguDodajeJednuKnjiguUBibliotku() {
        Knjiga knjiga = TestDataGenerator.napraviRandomKnjigu();
        biblioteka.dodajKnjigu(knjiga);

        assertEquals(1, biblioteka.getListaKnjiga().size());
        assertTrue(biblioteka.getListaKnjiga().contains(knjiga));

    }

    @Test
    public void dodajKnjiguDodajeTriKnjigeUBibliotku() {
        Knjiga knjiga1 = TestDataGenerator.napraviRandomKnjigu();
        Knjiga knjiga2 = TestDataGenerator.napraviRandomKnjigu();
        Knjiga knjiga3 = TestDataGenerator.napraviRandomKnjigu();

        biblioteka.dodajKnjigu(knjiga1, knjiga2, knjiga3);

        assertEquals(3, biblioteka.getListaKnjiga().size());
        assertTrue(biblioteka.getListaKnjiga().contains(knjiga1));
        assertTrue(biblioteka.getListaKnjiga().contains(knjiga2));
        assertTrue(biblioteka.getListaKnjiga().contains(knjiga3));
    }

    @Test
    public void dodajKnjiguBezParametraNeDodajeNistaUBibliotku() {
        biblioteka.dodajKnjigu();

        assertEquals(0, biblioteka.getListaKnjiga().size());
    }
    @Test
    public void izdajJednuKnjiguIzdajeKnjigu() {
        Knjiga knjiga1 = TestDataGenerator.napraviRandomKnjigu();
        Korisnik korisnik = TestDataGenerator.napraviRandomKorisnika();

        biblioteka.dodajKnjigu(knjiga1);
        biblioteka.dodajKorisnika(korisnik);
        biblioteka.izdajKnjigu(korisnik, knjiga1);

        assertEquals(1, biblioteka.getListaPozajmica().size());
        assertEquals(korisnik, biblioteka.getListaPozajmica().get(0).getKorisnik());
        assertEquals(knjiga1, biblioteka.getListaPozajmica().get(0).getKnjiga());
    }

    @Test
    public void izdajKnjiguKojaNePostojiUBiblioteciNeMozeDaJeIzda() {
        Knjiga knjiga1 = TestDataGenerator.napraviRandomKnjigu();
        Korisnik korisnik = TestDataGenerator.napraviRandomKorisnika();
        biblioteka.dodajKorisnika();
        assertThrows(IllegalStateException.class, ()-> { biblioteka.izdajKnjigu(korisnik, knjiga1);
        });

        assertTrue(biblioteka.getListaPozajmica().isEmpty());
    }

    @Test
    public void izdajKnjiguNeupisanomKorisnikuNeMozeDaJeIzda(){
        Knjiga knjiga1 = TestDataGenerator.napraviRandomKnjigu();
        Korisnik korisnik = TestDataGenerator.napraviRandomKorisnika();
        biblioteka.dodajKnjigu(knjiga1);
        assertThrows(IllegalStateException.class, ()-> {biblioteka.izdajKnjigu(korisnik, knjiga1);
        });
    }

    @Test
    public void vecIzdataKnjigaNeMozeOpetDaSeIzda() {  //odlucili smo da nemamo duplikate
        Knjiga knjiga = TestDataGenerator.napraviRandomKnjigu();
        Korisnik korisnik = TestDataGenerator.napraviRandomKorisnika();
        biblioteka.dodajKnjigu(knjiga);
        biblioteka.dodajKorisnika(korisnik);
        biblioteka.izdajKnjigu(korisnik, knjiga);
        IllegalStateException exception = assertThrows(IllegalStateException.class, ()-> {
            biblioteka.izdajKnjigu(korisnik, knjiga);
        });
        assertEquals("Žao nam je, ova knjiga je vec izdata.", exception.getMessage());
    }

    @Test
    public  void izdataKnjigaMozeDaSeVrati(){
        Knjiga knjiga = TestDataGenerator.napraviRandomKnjigu();
        Korisnik korisnik = TestDataGenerator.napraviRandomKorisnika();
        biblioteka.dodajKnjigu(knjiga);
        biblioteka.dodajKorisnika(korisnik);
        Pozajmica p1 = biblioteka.izdajKnjigu(korisnik, knjiga);
        biblioteka.vratiKnjigu(korisnik,knjiga);
         assertTrue(knjiga.isDostupna());
         assertTrue(biblioteka.getArhivaPozajmica().contains(p1));
         assertFalse(biblioteka.getListaPozajmica().contains(p1));
    }

    @Test
    public void pozajmicaKojaNePostojiNeMozeDaSeVrati(){
        Knjiga knjiga = TestDataGenerator.napraviRandomKnjigu();
        Korisnik korisnik = TestDataGenerator.napraviRandomKorisnika();
        biblioteka.dodajKnjigu(knjiga);
        biblioteka.dodajKorisnika(korisnik);

        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            biblioteka.vratiKnjigu(korisnik, knjiga);
        });
       assertEquals("Ova pozajmica ne postoji. Ovaj korisnik nije uzeo ovu knjigu!", exception.getMessage());
    }
}