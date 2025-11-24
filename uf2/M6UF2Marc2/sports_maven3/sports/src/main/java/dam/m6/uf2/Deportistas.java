package dam.m6.uf2;

public class Deportistas {

    private int cod;
    private String nombre;
    private int codDeporte;

   
    public Deportistas(int cod, String nombre, int codDeporte) {
        this.cod = cod;
        this.nombre = nombre;
        this.codDeporte = codDeporte;
    }

    
    public Deportistas() {
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

    public int getCodDeporte() {
        return codDeporte;
    }

    public void setCodDeporte(int codDeporte) {
        this.codDeporte = codDeporte;
    }
}
