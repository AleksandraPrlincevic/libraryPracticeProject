package biblioteka.service;

import biblioteka.TestDataGenerator;
import biblioteka.model.Pozajmica;
import biblioteka.repository.KnjigaRepository;
import biblioteka.repository.KorisnikRepository;
import biblioteka.repository.PozajmicaRepository;
import org.checkerframework.checker.units.qual.K;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import biblioteka.service.BibliotekaService;
import biblioteka.model.Korisnik;
import biblioteka.model.Knjiga;

import java.sql.SQLException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class BibliotekaServiceTest {
    int pozajmicaId;
    public final KnjigaRepository knjigaRepo = new KnjigaRepository();
    public final KorisnikRepository korisnikRepo = new KorisnikRepository();
    public final PozajmicaRepository pozajmicaRepo = new PozajmicaRepository();
    public final BibliotekaService bibliotekaS =new BibliotekaService();

    @Test
    void canRentBook() throws SQLException {
        Korisnik korisnik = TestDataGenerator.napraviRandomKorisnika();
        Knjiga knjiga = TestDataGenerator.napraviRandomKnjigu();
        int korisnikId = korisnikRepo.dodajKorisnika(korisnik);
        int knjigaId = knjigaRepo.dodajKnjigu(knjiga);

        pozajmicaId = bibliotekaS.izdajKnjigu(korisnikId, knjigaId);
        assertTrue(pozajmicaId>0);
        Pozajmica pozajmica = pozajmicaRepo.pronadjiPozajmicuPoIdu(pozajmicaId);
        assertEquals(korisnikId, pozajmica.getKorisnikId());
        assertEquals( knjigaId, pozajmica.getKnjigaId());
        assertEquals(LocalDate.now(), pozajmica.getDanIzdavanja());
    }
    @Test
    void canReturnBook() throws SQLException {
        Korisnik korisnik = TestDataGenerator.napraviRandomKorisnika();
        Knjiga knjiga = TestDataGenerator.napraviRandomKnjigu();
        int korisnikId = korisnikRepo.dodajKorisnika(korisnik);
        int knjigaId = knjigaRepo.dodajKnjigu(knjiga);
        pozajmicaId = bibliotekaS.izdajKnjigu(korisnikId, knjigaId);
        assertTrue(pozajmicaId > 0);

        bibliotekaS.vratiKnjigu(korisnikId, knjigaId);
        Pozajmica pozajmica = pozajmicaRepo.pronadjiPozajmicuPoIdu(pozajmicaId);
        assertEquals(LocalDate.now(), pozajmica.getDanVracanja());
    }

    @AfterEach
    void cleanUp() throws  Exception {
        pozajmicaRepo.izbrisiPozajmicuPoIdu(pozajmicaId);
        korisnikRepo.izbrisiKorisnikaPoPrezimenuIimenu("TEST%");
        knjigaRepo.izbrisiKnjiguPoNaslovu("TEST%");
    }
}
