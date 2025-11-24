package dam.m6.uf2;


import java.sql.SQLException;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionManager {
    private static String host = "";   
    private static String port = "";
    private static String database = "";
    private static String username = "";
    private static String password = "";
    private static Connection con;
    private static String urlstring;

    public ConnectionManager() {
    }

    public static Connection getConnection(String config_file) {
        ReadConfigXML config =  new ReadConfigXML(config_file);

        host = config.getHost();
        port = config.getPort();
        database = config.getDatabase();
        username = config.getUser();
        password = config.getPassword();

        urlstring = "jdbc:postgresql://" + host + ":" + port + "/" + database;


        System.out.println("DEBUG: "+ host + " / " + port + " / "+ database + " / "+ username);


        try {
            try {
                con = DriverManager.getConnection(urlstring, username, password);
                System.out.println("Connection established successfully.");
            } catch (SQLException ex) {
                // log an exception. fro example:
                System.out.println("Failed to create the database connection."); 
            }
        } catch (Exception ex) {
            // log an exception. for example:
            System.out.println("Driver not found."); 
        }
        return con;
    }


}