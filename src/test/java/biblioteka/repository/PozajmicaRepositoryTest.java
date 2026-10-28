package biblioteka.repository;
import biblioteka.Pozajmica;
import biblioteka.repository.KorisnikRepository;
import biblioteka.repository.KnjigaRepository;
import biblioteka.database.DatabaseConnection;
import biblioteka.TestDataGenerator;
import biblioteka.Knjiga;
import biblioteka.Korisnik;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class PozajmicaRepositoryTest {
     private final KorisnikRepository korisnikRepo = new KorisnikRepository();
     private final KnjigaRepository knjigaRepo = new KnjigaRepository();
     private  final PozajmicaRepository pozajmicaRepo = new PozajmicaRepository();
     int pozajmicaId;

     @Test
     void canAddNewLoanToDatabase() throws Exception {
         Korisnik korisnik = TestDataGenerator.napraviRandomKorisnika();
         Knjiga knjiga = TestDataGenerator.napraviRandomKnjigu();
         int korisnikId = korisnikRepo.dodajKorisnika(korisnik);
         int knjigaId = knjigaRepo.dodajKnjigu(knjiga);
         pozajmicaId = pozajmicaRepo.dodajPozajmicu(korisnikId, knjigaId);
         assertTrue(pozajmicaId>0);

         Pozajmica pozajmica = pozajmicaRepo.pronadjiPozajmicuPoIdu(pozajmicaId);
         assertNotNull(pozajmica, "Loan with id " + pozajmicaId + " can not be found.");
         assertEquals(korisnikId, pozajmica.getKorisnikId() );
         assertEquals(knjigaId, pozajmica.getKnjigaId());
         assertNotNull(pozajmica.getDanIzdavanja());
         assertNull(pozajmica.getDanVracanja());
     }
     @Test
     void canCloseExistingLoan() throws Exception {
         Korisnik korisnik = TestDataGenerator.napraviRandomKorisnika();
         Knjiga knjiga = TestDataGenerator.napraviRandomKnjigu();
         int korisnikId = korisnikRepo.dodajKorisnika(korisnik);
         int knjigaId = knjigaRepo.dodajKnjigu(knjiga);
         pozajmicaId = pozajmicaRepo.dodajPozajmicu(korisnikId, knjigaId);
         assertTrue(pozajmicaId>0);
         assertTrue(pozajmicaRepo.zatvoriPozajmicu(pozajmicaId));
         Pozajmica pozajmica = pozajmicaRepo.pronadjiPozajmicuPoIdu(pozajmicaId);
         assertEquals(LocalDate.now(), pozajmica.getDanVracanja());

     }
     @AfterEach
     void cleanUp() throws Exception {
         System.out.println("ID pozajmice za brisanje: " + pozajmicaId);
         pozajmicaRepo.izbrisiPozajmicuPoIdu(pozajmicaId);
         korisnikRepo.izbrisiKorisnikaPoPrezimenuIimenu("TEST%");
         knjigaRepo. izbrisiKnjiguPoNaslovu("TEST%");
     }
}
