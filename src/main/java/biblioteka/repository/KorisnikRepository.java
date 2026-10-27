package biblioteka.repository;
import biblioteka.Korisnik;
import biblioteka.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class KorisnikRepository {

    public int dodajKorisnika(Korisnik korisnik) throws SQLException {
        String sql = "INSERT INTO korisnik (prezime_i_ime, datum_rodjenja) VALUES (?, ?) RETURNING id, clanski_broj";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, korisnik.getPrezimeIime());
            statement.setDate(2, java.sql.Date.valueOf(korisnik.getDatumRodjenja()));

            try (ResultSet rs = statement.executeQuery()) {
                rs.next();
                int clanskiBroj = rs.getInt("clanski_broj");
                korisnik.setClanskiBroj(clanskiBroj);
                return rs.getInt("id");
            }
        }
    }
    public Korisnik pronadjiKorisnikaPoIdu(int korisnikId) throws SQLException {
        String sql = "SELECT prezime_i_ime, datum_rodjenja, clanski_broj FROM korisnik WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, korisnikId);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return new Korisnik(
                            rs.getString("prezime_i_ime"),
                            rs.getDate("datum_rodjenja").toLocalDate(),
                            rs.getInt("clanski_broj")
                    );
                }
                return null;
            }
        }
    }
    public boolean izbrisiKorisnikaPoPrezimenuIimenu(String x) throws Exception {
        String sql = "DELETE FROM korisnik WHERE prezime_i_ime LIKE ?";
        try(Connection connection = DatabaseConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setString(1, x);
            int rowsDeleted = ps.executeUpdate();
            return rowsDeleted >0;
        }
    }
}