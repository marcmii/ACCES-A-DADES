package tasques;

import org.bson.Document;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public final class App {

    private App() {}

    public static void main(String[] args) {

        String uri = "mongodb+srv://marc:1234@cluster0.7bfaucb.mongodb.net/";

        try (MongoClient mongoClient = MongoClients.create(uri)) {

            MongoDatabase adminDb = mongoClient.getDatabase("admin");
            adminDb.runCommand(new Document("ping", 1));
            System.out.println("Connexió a MongoDB Atlas correcta!");


            MongoDatabase db = mongoClient.getDatabase("gestioRestaurants");
            System.out.println("Base de dades: " + db.getName());

            MongoCollection<Document> reserves = db.getCollection("reserves");
            System.out.println("Col·lecció: " + reserves.getNamespace().getCollectionName());


            FindIterable<Document> resultats = reserves.find();

            System.out.println("📋 Reserves trobades:");
            for (Document doc : resultats) {
                System.out.println(doc.toJson());
            }

        } catch (com.mongodb.MongoException e) {
            System.err.println("Error de MongoDB");
            e.printStackTrace();
        }
    }
}
