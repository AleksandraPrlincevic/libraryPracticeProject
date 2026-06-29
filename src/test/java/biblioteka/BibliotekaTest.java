package biblioteka;

import com.sun.source.tree.AssertTree;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class BibliotekaTest {

    private Biblioteka biblioteka;

    //helper metode:
    private Knjiga napraviKnjiguTijeloNjenogTijela(){
        return new Knjiga("Tijelo njenog tijela", "Slavenka Drakulic", 182);
    }
    private Knjiga napraviKnjiguMojaGenijalnaPrijateljica(){
        return new Knjiga("Moja genijalna prijateljica", "Elena Ferante", 326);
    }
    private Knjiga napraviKnjiguKatedrala(){
        return new Knjiga("Katedrala", "Ivo Bresan", 278);
    }
    private Korisnik napraviKorisnikaMaricaPrlincevic(){
        return new Korisnik("Marica Prlincevic", LocalDate.of(1946,11,23));
    }

    @BeforeEach
    void  setUp(){
        biblioteka = new Biblioteka();
    }

    @Test
    public void dodajKnjiguDodajeJednuKnjiguUBibliotku() {
        Knjiga knjiga = napraviKnjiguTijeloNjenogTijela();

        biblioteka.dodajKnjigu(knjiga);

        assertEquals(1, biblioteka.getListaKnjiga().size());
        assertTrue(biblioteka.getListaKnjiga().contains(knjiga));

    }

    @Test
    public void dodajKnjiguDodajeTriKnjigeUBibliotku() {
        Knjiga knjiga1 = napraviKnjiguTijeloNjenogTijela();
        Knjiga knjiga2 = napraviKnjiguMojaGenijalnaPrijateljica();
        Knjiga knjiga3 = napraviKnjiguKatedrala();

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
        Knjiga knjiga1 = new Knjiga("Tijelo njenog tijela", "Slavenka Drakulic", 182);
        Korisnik korisnik = napraviKorisnikaMaricaPrlincevic();

        biblioteka.dodajKnjigu(knjiga1);
        biblioteka.dodajKorisnika(korisnik);
        biblioteka.izdajKnjigu(korisnik, knjiga1);

        assertEquals(1, biblioteka.getListaPozajmica().size());
        assertEquals(korisnik, biblioteka.getListaPozajmica().get(0).getKorisnik());
        assertEquals(knjiga1, biblioteka.getListaPozajmica().get(0).getKnjiga());
    }

    @Test
    public void izdajKnjiguKojaNePostojiUBiblioteciNeMozeDaJeIzda() {
        Knjiga knjiga1 = napraviKnjiguTijeloNjenogTijela();
        Korisnik korisnik = napraviKorisnikaMaricaPrlincevic();
        biblioteka.dodajKorisnika();
        assertThrows(IllegalStateException.class, ()-> { biblioteka.izdajKnjigu(korisnik, knjiga1);
        });

        assertTrue(biblioteka.getListaPozajmica().isEmpty());
    }
}