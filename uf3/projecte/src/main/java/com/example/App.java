package com.example;

import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;

public class App {

    public static void main(String[] args) {

        // ⚠️ CANVIA USUARI, PASSWORD i CLUSTER
        String uri = "mongodb+srv://marc:1234@cluster0.7bfaucb.mongodb.net/?appName=Cluster0\r\n" + //
                        "";

        try (MongoClient mongoClient = MongoClients.create(uri)) {

            MongoDatabase db = mongoClient.getDatabase("practica3");
            MongoCollection<Document> col = db.getCollection("reserves");

            // INSERT
            Document novaReserva = new Document("dataReserva", new java.util.Date())
                    .append("clientNom", "Prova")
                    .append("restaurantNom", "El Racó")
                    .append("nombrePersones", 2)
                    .append("estat", "pendent");

            col.insertOne(novaReserva);
            System.out.println("Reserva inserida");

            // FIND
            col.find(eq("estat", "pendent"))
                    .forEach(doc -> System.out.println(doc.toJson()));

            // UPDATE
            col.updateOne(eq("clientNom", "Prova"), set("estat", "confirmada"));
            System.out.println("Reserva actualitzada");

            // DELETE
            col.deleteOne(eq("clientNom", "Prova"));
            System.out.println("Reserva eliminada");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
