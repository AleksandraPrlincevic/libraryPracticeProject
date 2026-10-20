package biblioteka.repository;
import biblioteka.Knjiga;
import biblioteka.database.DatabaseConnection;

import javax.xml.crypto.Data;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class KnjigaRepository {

    public int dodajKnjigu(Knjiga k) throws SQLException {
        String sql = "INSERT INTO knjiga (naslov, autor, br_strana) VALUES (?, ?, ?) RETURNING id";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);) {

            statement.setString(1, k.getNaslov());
            statement.setString(2,k.getAutor());
            statement.setInt(3,k.getBrStrana());

            try (ResultSet rs = statement.executeQuery()){
                rs.next();
                return rs.getInt("id");
            }
        }
    }
     public List<Integer> dodajKnjige(Knjiga...knjige) throws SQLException {
         List<Integer> ids = new ArrayList<>();
         for (Knjiga k : knjige) {
             ids.add(dodajKnjigu(k));
         }
         return ids;
     }

     public Knjiga pronadjiKnjiguPoIdu(int knjigaId) throws  SQLException {
        String sql = "SELECT * FROM knjiga WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, knjigaId);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return new Knjiga(
                            rs.getString("naslov"),
                            rs.getString("autor"),
                            rs.getInt("br_strana")
                    );
                } return null;
            }
        }
     }
}
