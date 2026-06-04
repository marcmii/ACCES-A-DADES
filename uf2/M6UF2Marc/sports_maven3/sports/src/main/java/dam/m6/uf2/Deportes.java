package dam.m6.uf2;

public class Deportes {
    private int cod;
    private String nombre;

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
}
