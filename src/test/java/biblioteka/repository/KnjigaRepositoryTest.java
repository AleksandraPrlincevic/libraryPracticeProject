package biblioteka.repository;

import biblioteka.Knjiga;
import biblioteka.database.DatabaseConnection;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class KnjigaRepositoryTest {
    private final KnjigaRepository repo = new KnjigaRepository();

    @Test
    void canAddBookToDatabase() throws Exception {
        Knjiga k = new Knjiga("TESTbook1", "autor1", 123);
        int addedBookId = repo.dodajKnjigu(k);
        assertTrue(addedBookId>0);
        Knjiga addedBook = repo.pronadjiKnjiguPoIdu(addedBookId);
        assertNotNull(addedBook, "Knjiga sa id-em : " + addedBookId + " nije pronadjena u bazi.");
        assertEquals(k.getNaslov(), addedBook.getNaslov());
        assertEquals(k.getAutor(), addedBook.getAutor());
        assertEquals(k.getBrStrana(), addedBook.getBrStrana());
    }

    @Test
    void canAddMultipleBooksToDatabase() throws Exception {
        Knjiga[] knjige = {new Knjiga("TESTbook1", "autor1", 123),
                 new Knjiga("TESTbook2", "autor2", 124),
                 new Knjiga("TESTbook3", "autor3", 125)};
        List<Integer> addedBooksIds = repo.dodajKnjige(knjige);
        assertEquals(knjige.length, addedBooksIds.size());
        for(int id: addedBooksIds) {
                    assertTrue(id>0);
        }
        List<Knjiga> ubaceneKnjige = repo.pronadjiKnjigePoIdu(addedBooksIds);
        assertEquals(knjige.length, ubaceneKnjige.size());
        for(int i = 0; i<knjige.length; i++){
            assertEquals(knjige[i].getNaslov(), ubaceneKnjige.get(i).getNaslov());
            assertEquals(knjige[i].getAutor(), ubaceneKnjige.get(i).getAutor());
            assertEquals(knjige[i].getBrStrana(), ubaceneKnjige.get(i).getBrStrana());
        }
    }

    @AfterEach
    void cleanUp() throws Exception {
        String sql ="DELETE FROM knjiga WHERE naslov LIKE ?";
        try(Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, "TEST%");
            statement.executeUpdate();
        }
    }
}
