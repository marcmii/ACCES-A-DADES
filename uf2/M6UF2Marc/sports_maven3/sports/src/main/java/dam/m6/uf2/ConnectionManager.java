package dam.m6.uf2;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;

public class ConnectionManager {
    public static Connection getConnection(String fitxer) throws Exception {
        Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new File(fitxer));

        String host = doc.getElementsByTagName("host").item(0).getTextContent();
        String port = doc.getElementsByTagName("port").item(0).getTextContent();
        String db = doc.getElementsByTagName("database").item(0).getTextContent();
        String user = doc.getElementsByTagName("user").item(0).getTextContent();
        String pass = doc.getElementsByTagName("password").item(0).getTextContent();

        return DriverManager.getConnection("jdbc:postgresql://" + host + ":" + port + "/" + db, user, pass);
    }
}
