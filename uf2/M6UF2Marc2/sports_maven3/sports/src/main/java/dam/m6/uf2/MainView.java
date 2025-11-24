package dam.m6.uf2;

import java.util.List;
import java.util.Scanner;

public class MainView {

    private Scanner sc = new Scanner(System.in);

  
    public int mainMenu() {
        System.out.println("\n--- MENÚ PRINCIPAL ---");
        System.out.println("1. Llistar esports");
        System.out.println("2. Afegir esport");
        System.out.println("3. Afegir atleta");
        System.out.println("4. Cercar atleta per nom");
        System.out.println("5. Llistar atletes per esport");
        System.out.println("0. Sortir");

        System.out.print("Opció: ");
        return sc.nextInt();
    }

    public void showUsers(List<User> list) {
        System.out.println("MOSTRAR LLISTAT USUARIS....");
        for (User user : list) {
            System.out.println(user.name + "\t" + user.password + "\tetc");
        }
    }

    public User addUserForm() {
        System.out.println("FORMULARI USUARI....nom, pass, etc");
        sc.nextLine(); 

        String username = sc.nextLine();
        String password = "1234";

        return new User(username, password);
    }


    public void LlistaEsports(List<Deportes> list) {
        System.out.println("LLISTA D'ESPORTS:");
            for (Deportes d : list) {
                System.out.println(d.getCod() + " - " + d.getNombre());
            }
    }


    public Deportes EsportForm() {
        sc.nextLine(); 

        System.out.print("Introdueix el nom del nou esport: ");
        String nom = sc.nextLine();

        return new Deportes(0, nom);
    }

    public Deportistas AtletaForm(List<Deportes> esports) {
        sc.nextLine(); 

        System.out.print("Nom de l'atleta: ");
        String nom = sc.nextLine();

        System.out.println("Selecciona esport:");
        for (Deportes d : esports) {
            System.out.println(d.getCod() + ") " + d.getNombre());
        }

        int id = sc.nextInt();

        return new Deportistas(0, nom, id);
    }

    public String DemanaNom() {
        sc.nextLine();
        System.out.print("Nom a cercar: ");
        return sc.nextLine();
    }

    public int DemanaEsport(List<Deportes> esports) {
        System.out.println("Selecciona esport:");

        for (Deportes d : esports) {
            System.out.println(d.getCod() + ") " + d.getNombre());
        }

        return sc.nextInt();
    }

    public void LlistaAtletes(List<Deportistas> list) {
        System.out.println("\n--- LLISTAT D'ATLETES ---");
        for (Deportistas d : list) {
            System.out.println(d.getCod() + " - " + d.getNombre() + " (Esport ID: " + d.getCod_deporte() + ")");
        }
    }
}
