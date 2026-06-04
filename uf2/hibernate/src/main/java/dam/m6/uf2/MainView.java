package dam.m6.uf2;

import java.util.List;
import java.util.Scanner;

public class MainView {
    private Scanner sc = new Scanner(System.in);

    public int mainMenu() {
        // Menu principal de l'aplicacio per consola.
        System.out.println("\n1. Llistar esports");
        System.out.println("2. Afegir esport");
        System.out.println("3. Afegir atleta");
        System.out.println("4. Cercar atleta per nom");
        System.out.println("5. Llistar atletes per esport");
        System.out.println("6. Modificar esport");
        System.out.println("7. Modificar atleta");
        System.out.println("0. Sortir");
        System.out.print("Opcio: ");
        return Integer.parseInt(sc.nextLine());
    }

    public Deportes EsportForm() {
        System.out.print("Nom de l'esport: ");
        return new Deportes(0, sc.nextLine());
    }

    public Deportistas AtletaForm(List<Deportes> esports) {
        // Primer demanem el nom i despres l'esport al qual pertany.
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
        // Mostrem la llista per poder triar el codi correcte.
        LlistaEsports(esports);
        System.out.print("Codi esport: ");
        return Integer.parseInt(sc.nextLine());
    }

    public int DemanaAtleta(List<Deportistas> atletes) {
        LlistaAtletes(atletes);
        System.out.print("Codi atleta: ");
        return Integer.parseInt(sc.nextLine());
    }

    public String DemanaNouNom() {
        System.out.print("Nou nom: ");
        return sc.nextLine();
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
            // El nom de l'esport surt de la relacio carregada per Hibernate.
            String esport = d.getNombreDeporte();
            if (esport == null) {
                esport = "Sense esport";
            }
            System.out.println(d.getCod() + " - " + d.getNombre() + " - " + esport);
        }
    }
}
