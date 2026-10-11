package biblioteka.database;
import org.junit.jupiter.api.Test;
import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

public class DatabaseConnectionTest {
    @Test
    void canConnectToDatabase() throws Exception {
        Connection connection = DatabaseConnection.getConnection();
        assertNotNull(connection);
        connection.close();
    }
    @Test
    void canReadBooksFromDatabase() throws Exception {

        try(Connection connection = DatabaseConnection.getConnection();
           var statement = connection.createStatement();
           var resultSet = statement.executeQuery("SELECT * FROM knjiga")) {

            int numberOfBooks = 0;
        while (resultSet.next()) {
            numberOfBooks ++;
            System.out.println(resultSet.getString("naslov") + " - " +
                    resultSet.getString("autor"));
        }
        assertTrue(numberOfBooks > 0);
    }}

    @Test
    void canAddBookToDatabase() throws Exception {
        try (Connection connection = DatabaseConnection.getConnection();
            var statement = connection.createStatement()) {
            int rowsInserted = statement.executeUpdate("INSERT INTO knjiga(naslov, autor, br_strana) VALUES('Pokoravanje', 'Misel Uelbek', 289)");
            assertEquals(1, rowsInserted);
            var resultSet = statement.executeQuery("SELECT * FROM knjiga WHERE naslov = 'Pokoravanje'");
            assertTrue(resultSet.next());
            assertEquals("Pokoravanje", resultSet.getString("naslov"));
            assertEquals("Misel Uelbek", resultSet.getString("autor"));
            assertEquals(289, resultSet.getInt("br_strana"));

            int addedBookId = resultSet.getInt("id");
            int rowsDeleted = statement.executeUpdate("DELETE FROM knjiga WHERE id = " + addedBookId);
            assertEquals(1, rowsDeleted);
        }
    }
     @Test
     void canGetBookById() throws Exception {
         try (Connection connection = DatabaseConnection.getConnection();
             var statement = connection.createStatement();
             var resultSet = statement.executeQuery("SELECT * FROM knjiga WHERE id = 5")) {
             assertTrue(resultSet.next());
             System.out.println(resultSet.getString("naslov") + " - " + resultSet.getString("autor") +
                     " - " + resultSet.getInt("br_strana"));
             assertEquals(5, resultSet.getInt("id"));
             assertEquals("Gospodja Dalovej", resultSet.getString("naslov"));
             assertEquals("Virdzinija Vulf", resultSet.getString("autor"));
             assertEquals(149, resultSet.getInt("br_strana"));
         }
     }

     @Test
     void canDeleteBookById() throws Exception {
        try (Connection connection = DatabaseConnection.getConnection();
              var statement = connection.createStatement()){
              var rowsInserted = statement.executeUpdate("INSERT INTO knjiga(naslov, autor, br_strana) VALUES('Plavi kit', 'Arsen Diklic', 12)");
                  assertEquals(1, rowsInserted);
              var resultSet = statement.executeQuery("SELECT id FROM knjiga WHERE naslov = 'Plavi kit'");
              assertTrue(resultSet.next());
              var addedBookId = resultSet.getInt("id");
              var rowsDeleted = statement.executeUpdate("DELETE FROM  knjiga WHERE id = " + addedBookId);
                  assertEquals(1,rowsDeleted);
               var resultSet2 = statement.executeQuery("SELECT * FROM knjiga WHERE naslov = 'Plavi kit'  AND id = " + addedBookId);
               assertFalse(resultSet2.next());
        }
     }
}
