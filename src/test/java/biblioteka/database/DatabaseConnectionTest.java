package biblioteka.database;
import org.junit.jupiter.api.Test;
import java.sql.Connection;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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

        while (resultSet.next()) {
            System.out.println(resultSet.getString("naslov") + " - " +
                    resultSet.getString("autor"));
        }
    }}
}
