import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;

public class Main {

    public static void main(String[] args) {

        String uri = "mongodb+srv://USUARI:PASSWORD@cluster.mongodb.net/?retryWrites=true&w=majority";

        try (MongoClient mongoClient = MongoClients.create(uri)) {

            MongoDatabase db = mongoClient.getDatabase("practica3");
            MongoCollection<Document> col = db.getCollection("reserves");

            Document novaReserva = new Document("dataReserva", new java.util.Date())
                    .append("clientNom", "Prova")
                    .append("restaurantNom", "El Racó")
                    .append("nombrePersones", 2)
                    .append("estat", "pendent");

            col.insertOne(novaReserva);

            col.find(eq("estat", "pendent"))
                    .forEach(doc -> System.out.println(doc.toJson()));

            col.updateOne(eq("clientNom", "Prova"), set("estat", "confirmada"));

            col.deleteOne(eq("clientNom", "Prova"));
        }
    }
}
