package dam.m6.uf2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EsportDAO implements DAO<Esport> {
    private Connection conn;

    public EsportDAO(Connection conn) {
        this.conn = conn;
    }

    public void add(Esport esport) {
        try (PreparedStatement ps = conn.prepareStatement("INSERT INTO deportes(nombre) VALUES (?)")) {
            ps.setString(1, esport.getNombre());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error afegint esport: " + e.getMessage());
        }
    }

    public List<Esport> getAll() {
        List<Esport> esports = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement("SELECT cod, nombre FROM llista_esports()");
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                esports.add(new Esport(rs.getInt("cod"), rs.getString("nombre")));
            }
        } catch (Exception e) {
            System.out.println("Error llistant esports: " + e.getMessage());
        }
        return esports;
    }
}
