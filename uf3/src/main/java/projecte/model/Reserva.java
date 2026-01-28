package projecte.model;

import java.util.Date;

import org.bson.Document;
import org.bson.types.ObjectId;

public class Reserva {

    private ObjectId id;
    private Date dataReserva;
    private String clientNom;
    private String restaurantNom;
    private int nombrePersones;
    private String estat;

    public Reserva(Date dataReserva, String clientNom, String restaurantNom, int nombrePersones, String estat) {
        this.dataReserva = dataReserva;
        this.clientNom = clientNom;
        this.restaurantNom = restaurantNom;
        this.nombrePersones = nombrePersones;
        this.estat = estat;
    }

    public Reserva(ObjectId id, Date dataReserva, String clientNom, String restaurantNom, int nombrePersones, String estat) {
        this.id = id;
        this.dataReserva = dataReserva;
        this.clientNom = clientNom;
        this.restaurantNom = restaurantNom;
        this.nombrePersones = nombrePersones;
        this.estat = estat;
    }

    public ObjectId getId() {
        return id;
    }

    public String getClientNom() {
        return clientNom;
    }

    public Date getDataReserva() {
        return dataReserva;
    }

    public Document toDocument() {
        Document doc = new Document();
        doc.append("dataReserva", dataReserva);
        doc.append("clientNom", clientNom);
        doc.append("restaurantNom", restaurantNom);
        doc.append("nombrePersones", nombrePersones);
        doc.append("estat", estat);
        return doc;
    }

    public static Reserva fromDocument(Document doc) {
        return new Reserva(
            doc.getObjectId("_id"),
            doc.getDate("dataReserva"),
            doc.getString("clientNom"),
            doc.getString("restaurantNom"),
            doc.getInteger("nombrePersones"),
            doc.getString("estat")
        );
    }

    @Override
    public String toString() {
        return id + " | " + clientNom + " | " + restaurantNom + " | " + dataReserva + " | " + estat;
    }
}
