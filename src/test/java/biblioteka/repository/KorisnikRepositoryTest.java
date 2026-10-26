package biblioteka.repository;
import  biblioteka.Korisnik;
import biblioteka.TestDataGenerator;
import biblioteka.database.DatabaseConnection;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;

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
        System.out.println(korisnik);
        System.out.println(receivedKorisnik);
        System.out.println("ID dodatog korisnika: " + addedUserId);
        System.out.println("Pronađeni korisnik: " + receivedKorisnik);
        assertEquals(korisnik.getPrezimeIime(), receivedKorisnik.getPrezimeIime());
        assertEquals(korisnik.getDatumRodjenja(), receivedKorisnik.getDatumRodjenja());
        assertTrue(receivedKorisnik.getClanskiBroj()>0);
    }

   /* @AfterEach
    void cleanUp() throws Exception {
        String sql = "DELETE FROM korisnik WHERE prezime_i_ime LIKE ?";
        try(Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, "TEST%");
            statement.executeUpdate();
        }
    } */
}
