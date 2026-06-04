package projecte.model;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class ConnectionManager {

    private static final String URI = "mongodb://localhost:27017";
    private static MongoClient client;

    public static MongoDatabase getConnection() {
        if (client == null) {
            client = MongoClients.create(URI);
        }
        return client.getDatabase("gestioRestaurants");
        
    }
}