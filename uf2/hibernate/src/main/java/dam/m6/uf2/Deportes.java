package dam.m6.uf2;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "deportes")
public class Deportes {
    // Clau primaria de la taula deportes.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod")
    private int cod;

    // Camp on es guarda el nom de l'esport.
    @Column(name = "nombre", nullable = false)
    private String nombre;

    public Deportes() {
        // Constructor buit necessari per Hibernate.
    }

    public Deportes(int cod, String nombre) {
        this.cod = cod;
        this.nombre = nombre;
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
}
