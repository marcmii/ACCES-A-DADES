package dam.m6.uf2;

import java.util.List;
import java.util.Scanner;

public class MainView {
    private Scanner sc = new Scanner(System.in);

    public int mainMenu() {
        System.out.println("\n1. Llistar esports");
        System.out.println("2. Afegir esport");
        System.out.println("3. Afegir atleta");
        System.out.println("4. Cercar atleta per nom");
        System.out.println("5. Llistar atletes per esport");
        System.out.println("0. Sortir");
        System.out.print("Opcio: ");
        return Integer.parseInt(sc.nextLine());
    }

    public Deportes EsportForm() {
        System.out.print("Nom de l'esport: ");
        return new Deportes(0, sc.nextLine());
    }

    public Deportistas AtletaForm(List<Deportes> esports) {
        System.out.print("Nom de l'atleta: ");
        String nom = sc.nextLine();
        int esport = DemanaEsport(esports);
        return new Deportistas(0, nom, esport);
    }

    public String DemanaNom() {
        System.out.print("Nom a cercar: ");
        return sc.nextLine();
    }

    public int DemanaEsport(List<Deportes> esports) {
        LlistaEsports(esports);
        System.out.print("Codi esport: ");
        return Integer.parseInt(sc.nextLine());
    }

    public void LlistaEsports(List<Deportes> esports) {
        System.out.println("\nESPORTS");
        for (Deportes d : esports) {
            System.out.println(d.getCod() + " - " + d.getNombre());
        }
    }

    public void LlistaAtletes(List<Deportistas> atletes) {
        System.out.println("\nATLETES");
        for (Deportistas d : atletes) {
            String esport = d.getNombreDeporte();
            if (esport == null) {
                esport = "Sense esport";
            }
            System.out.println(d.getCod() + " - " + d.getNombre() + " - " + esport);
        }
    }
}
