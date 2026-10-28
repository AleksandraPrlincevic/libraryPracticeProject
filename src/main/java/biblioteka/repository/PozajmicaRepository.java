package biblioteka.repository;
import biblioteka.model.Pozajmica;
import biblioteka.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PozajmicaRepository {

    public int dodajPozajmicu(int korisnikId, int knjigaId) throws SQLException {
        String sql = "INSERT INTO pozajmica (korisnik_id, knjiga_id) VALUES (?, ?) RETURNING id";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);){
             ps.setInt(1,korisnikId);
             ps.setInt(2,knjigaId);
             try(ResultSet rs = ps.executeQuery()){
                 rs.next();
                 int pozajmicaId =rs.getInt("id");
                 return pozajmicaId;
             }
        }
    }

    public Pozajmica pronadjiPozajmicuPoIdu(int pozajmicaId) throws SQLException {
        String sql = "SELECT korisnik_id, knjiga_id, dan_izdavanja, dan_vracanja FROM pozajmica WHERE id = ?";
        try(Connection connection = DatabaseConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1, pozajmicaId);
            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    return new Pozajmica (
                            rs.getInt("korisnik_id"),
                            rs.getInt("knjiga_id"),
                            rs.getDate("dan_izdavanja").toLocalDate(),
                            rs.getDate("dan_vracanja") == null ? null : rs.getDate("dan_vracanja").toLocalDate()
                    );
                } return null;
            }
        }
    }
    public boolean izbrisiPozajmicuPoIdu(int pozajmicaId) throws SQLException {
        String sql = "DELETE FROM pozajmica WHERE id = ?";
        try(Connection connection = DatabaseConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)){
             ps.setInt(1, pozajmicaId);
             int rowsDeleted = ps.executeUpdate();
             return rowsDeleted ==1;
        }
    }
    public boolean zatvoriPozajmicu(int pozajmicaId) throws SQLException {
        String sql = """
                UPDATE pozajmica
                SET dan_vracanja = CURRENT_DATE
                WHERE id = ?
                AND dan_vracanja IS NULL
                """;
        try(Connection connection = DatabaseConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, pozajmicaId);
            int rowsUpdated = ps.executeUpdate();
            return rowsUpdated == 1;
        }
    }
    public boolean jeKnjigaDostupna(int knjigaId) throws SQLException {
        String sql = """
                SELECT NOT EXISTS(
                SELECT 1
                FROM pozajmica
                WHERE knjiga_id = ?
                AND dan_vracanja IS NULL)
                """;
        try(Connection connection = DatabaseConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)){
             ps.setInt(1, knjigaId);
             try(ResultSet rs = ps.executeQuery()){
                 return rs.next() && rs.getBoolean(1);
             }
        }
    }
}
