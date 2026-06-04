package dam.m6.uf2;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;

public class ConnectionManager {
    public static Connection getConnection(String configFile) throws Exception {
        Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new File(configFile));

        String host = text(doc, "host");
        String port = text(doc, "port");
        String database = text(doc, "database");
        String user = text(doc, "user");
        String password = text(doc, "password");
        String url = "jdbc:postgresql://" + host + ":" + port + "/" + database;

        return DriverManager.getConnection(url, user, password);
    }

    private static String text(Document doc, String tag) {
        return doc.getElementsByTagName(tag).item(0).getTextContent();
    }
}
