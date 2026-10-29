package biblioteka.repository;
import biblioteka.model.Knjiga;
import biblioteka.database.DatabaseConnection;

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
        String sql = "SELECT naslov, autor, br_strana FROM knjiga WHERE id = ?";
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

     public List<Knjiga> pronadjiKnjigePoIdu(List<Integer> ids) throws SQLException {
        String sql = "SELECT naslov, autor, br_strana FROM knjiga WHERE id = ANY(?) ORDER BY id";
        List<Knjiga> knjige = new ArrayList<>();
        try (Connection connection =DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
             Array niz = connection.createArrayOf("integer", ids.toArray());
             statement.setArray(1, niz);

             try (ResultSet rs = statement.executeQuery()) {
                 while(rs.next()) {
                     knjige.add(new Knjiga(
                             rs.getString("naslov"),
                             rs.getString("autor"),
                             rs.getInt("br_strana")
                             )
                     );}
             }
        }return knjige;
     }

    public boolean izbrisiKnjiguPoNaslovu(String x) throws Exception {
        String sql = "DELETE FROM knjiga WHERE naslov LIKE ?";
        try(Connection connection = DatabaseConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setString(1, x);
            int rowsDeleted = ps.executeUpdate();
            return rowsDeleted >0;
        }
    }
    public boolean postojiKnjiga(int knjigaId) throws SQLException {
        String sql = """
                SELECT EXISTS (
                SELECT 1
                FROM knjiga
                WHERE id = ?
                );
                """;
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
             ps.setInt(1,knjigaId);
             try (ResultSet rs = ps.executeQuery()) {
                 return rs.next() && rs.getBoolean(1);
             }
        }
    }
}
