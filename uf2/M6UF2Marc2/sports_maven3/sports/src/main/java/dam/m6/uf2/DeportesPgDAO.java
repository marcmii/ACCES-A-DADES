package dam.m6.uf2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DeportesPgDAO implements DAO<Deportes> {

    private Connection conn;

    public DeportesPgDAO(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void add(Deportes d) {
        if (conn == null) return;

        try (PreparedStatement pst = conn.prepareStatement(
            "INSERT INTO deportes(nombre) VALUES(?)")) {

            pst.setString(1, d.getNombre());
            pst.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<Deportes> getAll() {
        List<Deportes> list = new ArrayList<>();

        if (conn == null) return list;

        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery("SELECT cod, nombre FROM deportes ORDER BY nombre")) {

            while (rs.next()) {
                int cod = rs.getInt("cod");
                String nom = rs.getString("nombre");
                list.add(new Deportes(cod, nom));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}
