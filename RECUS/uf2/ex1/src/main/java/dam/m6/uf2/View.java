package dam.m6.uf2;

import java.util.List;
import java.util.Scanner;

public class View {
    private Scanner sc = new Scanner(System.in);

    public int menu() {
        System.out.println("\n1. Llistar esports");
        System.out.println("2. Afegir esport");
        System.out.println("3. Afegir atleta");
        System.out.println("4. Cercar atleta per nom");
        System.out.println("5. Llistar atletes per esport");
        System.out.println("0. Sortir");
        System.out.print("Opcio: ");
        return Integer.parseInt(sc.nextLine());
    }

    public Esport esportForm() {
        System.out.print("Nom de l'esport: ");
        return new Esport(0, sc.nextLine());
    }

    public Atleta atletaForm(List<Esport> esports) {
        System.out.print("Nom de l'atleta: ");
        String nom = sc.nextLine();
        int esport = demanaEsport(esports);
        return new Atleta(0, nom, esport, "");
    }

    public String demanaNom() {
        System.out.print("Nom a cercar: ");
        return sc.nextLine();
    }

    public int demanaEsport(List<Esport> esports) {
        llistaEsports(esports);
        System.out.print("Codi esport: ");
        return Integer.parseInt(sc.nextLine());
    }

    public void llistaEsports(List<Esport> esports) {
        System.out.println("\nESPORTS");
        for (Esport e : esports) {
            System.out.println(e.getCod() + " - " + e.getNombre());
        }
    }

    public void llistaAtletes(List<Atleta> atletes) {
        System.out.println("\nATLETES");
        for (Atleta a : atletes) {
            System.out.println(a.getCod() + " - " + a.getNombre() + " - " + a.getNombreDeporte());
        }
    }
}
