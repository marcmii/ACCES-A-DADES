package dam.m6.uf2;

public class Deportistas {
    private int cod;
    private String nombre;
    private int codDeporte;
    private String nombreDeporte;

    public Deportistas(int cod, String nombre, int codDeporte) {
        this(cod, nombre, codDeporte, null);
    }

    public Deportistas(int cod, String nombre, int codDeporte, String nombreDeporte) {
        this.cod = cod;
        this.nombre = nombre;
        this.codDeporte = codDeporte;
        this.nombreDeporte = nombreDeporte;
    }

    public int getCod() {
        return cod;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCodDeporte() {
        return codDeporte;
    }

    public String getNombreDeporte() {
        return nombreDeporte;
    }
}
