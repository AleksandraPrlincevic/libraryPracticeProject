package biblioteka.database;
import org.junit.jupiter.api.Test;
import java.sql.Connection;
import java.sql.Statement;

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
        int addedBookId;
        try (Connection connection = DatabaseConnection.getConnection();
             var statement = connection.prepareStatement("INSERT INTO knjiga(naslov, autor, br_strana) VALUES('Pokoravanje', 'Misel Uelbek', 289)",
                    Statement.RETURN_GENERATED_KEYS)) {
            int rowsInserted = statement.executeUpdate();
            assertEquals(1, rowsInserted);
            try (var resultSet = statement.getGeneratedKeys()){
                assertTrue(resultSet.next());
                addedBookId = resultSet.getInt(1);
            }
            try (var selectStatement = connection.prepareStatement("SELECT * FROM knjiga WHERE id = ?")){
                selectStatement.setInt(1,addedBookId);
                try (var resultSet = selectStatement.executeQuery()) {
                    assertTrue(resultSet.next());
                    assertEquals("Pokoravanje", resultSet.getString("naslov"));
                    assertEquals("Misel Uelbek", resultSet.getString("autor"));
                    assertEquals(289, resultSet.getInt("br_strana"));
                }
            }
            try (var deleteStatement = connection.prepareStatement("DELETE FROM knjiga WHERE id = ?")){
                  deleteStatement.setInt(1,addedBookId);
                  int rowsDeleted = deleteStatement.executeUpdate();
                  assertEquals(1, rowsDeleted);
        }}
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
        int addedBookId;
        try (Connection connection = DatabaseConnection.getConnection();
              var statement = connection.createStatement()){
              var rowsInserted = statement.executeUpdate("INSERT INTO knjiga(naslov, autor, br_strana) VALUES('Plavi kit', 'Arsen Diklic', 12)");
                  assertEquals(1, rowsInserted);
              try (var resultSet = statement.executeQuery("SELECT id FROM knjiga WHERE naslov = 'Plavi kit'")){
              assertTrue(resultSet.next());
              addedBookId = resultSet.getInt("id");
              }
              var rowsDeleted = statement.executeUpdate("DELETE FROM  knjiga WHERE id = " + addedBookId);
                  assertEquals(1,rowsDeleted);
               try (var resultSet2 = statement.executeQuery("SELECT * FROM knjiga WHERE naslov = 'Plavi kit'  AND id = " + addedBookId)){
               assertFalse(resultSet2.next());
        }}
     }

     @Test
     void canUpdateBookById() throws Exception {
        try(Connection connection = DatabaseConnection.getConnection();
            var statement = connection.createStatement()) {
            var rowsUpdated = statement.executeUpdate("UPDATE knjiga SET autor = 'Dzek London' WHERE id = 13");
            assertEquals(1, rowsUpdated);
            try (var resultSet = statement.executeQuery("SELECT autor FROM knjiga WHERE id = " + 13)){
            assertTrue(resultSet.next());
            assertEquals("Dzek London", resultSet.getString("autor"));
        }}
     }
}
