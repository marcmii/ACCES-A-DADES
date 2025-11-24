package dam.m6.uf2;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class DeportistasPgDAO implements DAO<Deportistas> {
    
    private Connection conn;

    public DeportistasPgDAO(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void add(Deportistas item) {
        
        if (conn == null)
            return;
        try (Statement statement = conn.createStatement()) {

            // ResultSet resultSet = statement.executeQuery("INSERT ...");

        } catch (SQLException e) {
           
            e.printStackTrace();
        }

        throw new UnsupportedOperationException("Unimplemented method 'add'");    
    }

    @Override
    public List<Deportistas> getAll() {
        
        return null;
    }

   
    public List<Deportistas> getDeportistaByName(String name) {
        
        return null;
    }

    public List<Deportistas> getDeportistaBySportID(int SportID) {
        
        return null;
    }
   
}
