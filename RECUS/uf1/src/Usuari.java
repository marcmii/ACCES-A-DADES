import java.io.Serializable;

// Classe que representa l'usuari que es guarda al fitxer user.dat.
public class Usuari implements Serializable {
    private static final long serialVersionUID = 1L;

    // Dades de l'usuari.
    private final String nom;
    private final String pass;

    public Usuari(String nom, String pass) {
        this.nom = nom;
        this.pass = pass;
    }

    public String getNom() { return nom; }

    public String getPass() { return pass; }
}
