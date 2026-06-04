package dam.m6.uf2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AtletaDAO implements DAO<Atleta> {
    private Connection conn;

    public AtletaDAO(Connection conn) {
        this.conn = conn;
    }

    public void add(Atleta atleta) {
        try (PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO deportistas(nombre, cod_deporte) VALUES (?, ?)")) {
            ps.setString(1, atleta.getNombre());
            ps.setInt(2, atleta.getCodDeporte());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error afegint atleta: " + e.getMessage());
        }
    }

    public List<Atleta> getAll() {
        return buscarPerNom("");
    }

    public List<Atleta> buscarPerNom(String nom) {
        List<Atleta> atletes = new ArrayList<>();
        String sql = "SELECT a.cod, a.nombre, a.cod_deporte, e.nombre AS esport "
                + "FROM deportistas a LEFT JOIN deportes e ON e.cod = a.cod_deporte "
                + "WHERE LOWER(a.nombre) LIKE LOWER(?) ORDER BY a.nombre";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + nom + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                atletes.add(new Atleta(
                        rs.getInt("cod"),
                        rs.getString("nombre"),
                        rs.getInt("cod_deporte"),
                        rs.getString("esport")));
            }
        } catch (Exception e) {
            System.out.println("Error cercant atleta: " + e.getMessage());
        }
        return atletes;
    }

    public List<Atleta> buscarPerEsport(int idEsport) {
        List<Atleta> atletes = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM get_deportistas(?)")) {
            ps.setInt(1, idEsport);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                atletes.add(new Atleta(
                        rs.getInt("cod"),
                        rs.getString("atleta"),
                        idEsport,
                        rs.getString("esport")));
            }
        } catch (Exception e) {
            System.out.println("Error llistant atletes: " + e.getMessage());
        }
        return atletes;
    }
}
