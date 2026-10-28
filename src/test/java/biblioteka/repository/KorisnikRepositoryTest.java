package biblioteka.repository;
import biblioteka.model.Korisnik;
import biblioteka.TestDataGenerator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class KorisnikRepositoryTest {

    private final KorisnikRepository repo = new KorisnikRepository();

    @Test
    void canAddUserToDatabase() throws Exception {
        Korisnik korisnik = TestDataGenerator.napraviRandomKorisnika();
        int addedUserId = repo.dodajKorisnika(korisnik);
        assertTrue(addedUserId>0);

        Korisnik receivedKorisnik = repo.pronadjiKorisnikaPoIdu(addedUserId);
        assertNotNull(receivedKorisnik, "Koristik sa id-em " + addedUserId + " nije pronadjen u bazi.");
        assertEquals(korisnik.getPrezimeIime(), receivedKorisnik.getPrezimeIime());
        assertEquals(korisnik.getDatumRodjenja(), receivedKorisnik.getDatumRodjenja());
        assertTrue(receivedKorisnik.getClanskiBroj()>0);
    }

    @AfterEach
    void cleanUp() throws Exception {
        repo.izbrisiKorisnikaPoPrezimenuIimenu("TEST%");
    }
}
