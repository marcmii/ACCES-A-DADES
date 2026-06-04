package dam.m6.uf2;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "deportistas")
public class Deportistas {
    // Clau primaria de la taula deportistas.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod")
    private int cod;

    // Nom de l'atleta.
    @Column(name = "nombre", nullable = false)
    private String nombre;

    // Relacio molts atletes a un esport, feta amb la columna cod_deporte.
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cod_deporte")
    private Deportes deporte;

    public Deportistas() {
        // Constructor buit necessari per Hibernate.
    }

    public Deportistas(int cod, String nombre, int codDeporte) {
        this.cod = cod;
        this.nombre = nombre;
        this.deporte = new Deportes(codDeporte, null);
    }

    public int getCod() {
        return cod;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCodDeporte() {
        if (deporte == null) {
            return 0;
        }
        return deporte.getCod();
    }

    public String getNombreDeporte() {
        // Si no hi ha esport assignat, la vista mostrara "Sense esport".
        if (deporte == null) {
            return null;
        }
        return deporte.getNombre();
    }

    public Deportes getDeporte() {
        return deporte;
    }

    public void setDeporte(Deportes deporte) {
        this.deporte = deporte;
    }
}
