import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class main {
public static void main(String[] args) {


String url = "jdbc:postgresql://localhost:5432/sports";
String user = "postgres";
String password = "1234";
String query = "SELECT * FROM deportes";


try (Connection conn = DriverManager.getConnection(url, user, password);
Statement stmt = conn.createStatement();
ResultSet rs = stmt.executeQuery(query)) {

while (rs.next()) {
int id = rs.getInt("cod");
String nom = rs.getString("nombre");
System.out.println("Cod: " + id + " | Nombre: " + nom);
}

} catch (SQLException e) {
System.out.println("Error en la connexió o la consulta: " + e.getMessage());
}
}
}