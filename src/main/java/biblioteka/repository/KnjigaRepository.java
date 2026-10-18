package biblioteka.repository;
import biblioteka.Knjiga;
import biblioteka.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class KnjigaRepository {

    public int dodajKnjigu(Knjiga k) throws SQLException {
        String sql = "INSERT INTO knjiga (naslov, autor, br_strana) VALUES (?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);) {

            statement.setString(1, k.getNaslov());
            statement.setString(2,k.getAutor());
            statement.setInt(3,k.getBrStrana());

            return statement.executeUpdate();
        }
    }
     public int dodajKnjige(Knjiga...knjige) throws SQLException {
        int dodato = 0;
         for (Knjiga k : knjige) {
             dodato += dodajKnjigu(k);
         }
         return dodato;
     }
}
