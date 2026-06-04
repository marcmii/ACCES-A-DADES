package dam.m6.uf2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DeportistasPgDAO implements DAO<Deportistas> {
    private Connection conn;

    public DeportistasPgDAO(Connection conn) {
        this.conn = conn;
    }

    public void add(Deportistas d) {
        String sql = "INSERT INTO deportistas(nombre, cod_deporte) VALUES (?, ?)";

        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, d.getNombre());
            pst.setInt(2, d.getCodDeporte());
            pst.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error afegint atleta: " + e.getMessage());
        }
    }

    public List<Deportistas> getAll() {
        return getDeportistaByName("");
    }

    public List<Deportistas> getDeportistaByName(String name) {
        List<Deportistas> list = new ArrayList<>();
        String sql = "SELECT dep.cod, dep.nombre, dep.cod_deporte, d.nombre AS esport "
                + "FROM deportistas dep LEFT JOIN deportes d ON d.cod = dep.cod_deporte "
                + "WHERE LOWER(dep.nombre) LIKE LOWER(?) ORDER BY dep.nombre";

        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, "%" + name + "%");
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                list.add(new Deportistas(
                        rs.getInt("cod"),
                        rs.getString("nombre"),
                        rs.getInt("cod_deporte"),
                        rs.getString("esport")));
            }
        } catch (Exception e) {
            System.out.println("Error cercant atleta: " + e.getMessage());
        }

        return list;
    }

    public List<Deportistas> getDeportistaBySportID(int sportID) {
        List<Deportistas> list = new ArrayList<>();
        String sql = "SELECT dep.cod, dep.nombre, dep.cod_deporte, d.nombre AS esport "
                + "FROM deportistas dep JOIN deportes d ON d.cod = dep.cod_deporte "
                + "WHERE dep.cod_deporte = ? ORDER BY dep.nombre";

        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, sportID);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                list.add(new Deportistas(
                        rs.getInt("cod"),
                        rs.getString("nombre"),
                        rs.getInt("cod_deporte"),
                        rs.getString("esport")));
            }
        } catch (Exception e) {
            System.out.println("Error llistant atletes: " + e.getMessage());
        }

        return list;
    }
}
