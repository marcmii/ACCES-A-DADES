package dam.m6.uf2;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List; 

public class UserPgDAO implements DAO<User> {
    private Connection conn;

    public UserPgDAO(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void add(User item) {
        // TODO Auto-generated method stub

        if (conn == null)
            return;
        try (Statement statement = conn.createStatement()) {

            // ResultSet resultSet = statement.executeQuery("INSERT ...");

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        throw new UnsupportedOperationException("Unimplemented method 'add'");

    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();

          String sql = "SELECT * FROM deportistas";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                users.add(new User(
                        rs.getString("nombre"),
                        rs.getString("cod_deporte")
                        ));
            }


        } catch (SQLException e) {
            System.err.println("Error al buscar usuaris: " + e.getMessage());
        }

        return users;
    }

    public void callFunction(String p1, String p2) {
        CallableStatement cStmt = null;
        try {
            cStmt = conn.prepareCall    ("{call get_user_name(?,?)}");
            cStmt.setString(1, p1);
            cStmt.setString(2, p2);
            cStmt.execute();
            cStmt.registerOutParameter(1, Types.INTEGER);

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    

    }

    public boolean userExists(String name) {
        return false;
    }

}
