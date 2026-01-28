package projecte.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.gte;
import static com.mongodb.client.model.Filters.lte;

public class ReservaModel {

    private MongoCollection<Document> collection;

    public ReservaModel() {
        MongoDatabase db = ConnectionManager.getConnection();
        collection = db.getCollection("reserves");
    }

    public void add(Reserva r) {
        collection.insertOne(r.toDocument());
    }

    public boolean deleteById(String id) {
        return collection
            .deleteOne(eq("_id", new ObjectId(id)))
            .getDeletedCount() > 0;
    }

    public List<Reserva> getAll() {
        List<Reserva> llista = new ArrayList<>();
        for (Document d : collection.find()) {
            llista.add(Reserva.fromDocument(d));
        }
        return llista;
    }

    public List<Reserva> getByClientNom(String nom) {
        List<Reserva> llista = new ArrayList<>();
        for (Document d : collection.find(eq("clientNom", nom))) {
            llista.add(Reserva.fromDocument(d));
        }
        return llista;
    }

    public List<Reserva> getBetweenDates(Date inici, Date fi) {
        List<Reserva> llista = new ArrayList<>();
        for (Document d : collection.find(
                and(gte("dataReserva", inici), lte("dataReserva", fi))
        )) {
            llista.add(Reserva.fromDocument(d));
        }
        return llista;
    }
}
