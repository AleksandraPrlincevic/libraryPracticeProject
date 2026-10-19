package biblioteka.repository;

import biblioteka.Knjiga;
import biblioteka.database.DatabaseConnection;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class KnjigaRepositoryTest {
    private final KnjigaRepository repo = new KnjigaRepository();

    @Test
    void canAddBookToDatabase() throws Exception {
        Knjiga k = new Knjiga("TESTbook1", "autor1", 123);
        int addedBookId = repo.dodajKnjigu(k);
        assertTrue(addedBookId>0);
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
