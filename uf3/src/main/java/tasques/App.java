package tasques;


import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;


/**
 * Classe principal que comprova la connexió a MongoDB Atlas.
 */
public final class App {

    // Constructor privat per evitar instanciació (requisit Checkstyle HideUtilityClassConstructor)
    private App() {}

    /**
     * Punt d'entrada de l'aplicació.
     *
     * @param args Arguments de línia de comandes
     */
    public static void main(String[] args) {

        String uri = "mongodb+srv://marc:1234@cluster0.7bfaucb.mongodb.net/?retryWrites=true&w=majority";

        // Captura només excepcions específiques de MongoDB
        try (MongoClient mongoClient = MongoClients.create(uri)) {

            // Ping per comprovar connexió
            MongoDatabase adminDb = mongoClient.getDatabase("admin");
            adminDb.runCommand(new Document("ping", 1));
            System.out.println("✅ Connexió a MongoDB Atlas correcta!");

            // Exemple d'accés a una base de dades
            MongoDatabase db = mongoClient.getDatabase("practica3");
            System.out.println("📦 Base de dades seleccionada: " + db.getName());

        } catch (com.mongodb.MongoException me) {
            System.err.println("❌ Error de connexió a MongoDB");
            System.err.println(me.getMessage());
            me.printStackTrace();
        }
    }
}
