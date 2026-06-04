package dam.m6.uf2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DeportesPgDAO implements DAO<Deportes> {
    private Connection conn;

    public DeportesPgDAO(Connection conn) {
        this.conn = conn;
    }

    public void add(Deportes d) {
        try (PreparedStatement pst = conn.prepareStatement("INSERT INTO deportes(nombre) VALUES(?)")) {
            pst.setString(1, d.getNombre());
            pst.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error afegint esport: " + e.getMessage());
        }
    }

    public List<Deportes> getAll() {
        List<Deportes> list = new ArrayList<>();
        try (PreparedStatement pst = conn.prepareStatement("SELECT cod, nombre FROM llista_esports()");
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                list.add(new Deportes(rs.getInt("cod"), rs.getString("nombre")));
            }
        } catch (Exception e) {
            System.out.println("Error llistant esports: " + e.getMessage());
        }
        return list;
    }
}
