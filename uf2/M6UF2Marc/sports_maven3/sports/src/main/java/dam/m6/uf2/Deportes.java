package dam.m6.uf2;

public class Deportes {

    private int cod;
    private String nombre;

    public Deportes(int cod, String nombre) {
        this.cod = cod;
        this.nombre = nombre;
    }

    public Deportes() {
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
