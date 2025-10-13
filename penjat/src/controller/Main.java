package controller;

import java.util.Scanner;
import view.PenjatView; // Importem la vista

public class Main {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        PenjatView view = new PenjatView();

        // Mostrem el menú
        view.mostrarMenuPrincipal();

        // Llegim l’opció de l’usuari
        String option = in.nextLine();

        // Tractem l’opció
        switch (option) {
            case "1":
                
                break;
            case "2":
                
                break;
            default:
                System.out.println("Opció no vàlida!");
        }

        in.close();
    }

    private static void login(Scanner in) {
        System.out.print("Usuari: ");
        String usuari = in.nextLine();

        System.out.print("Contrasenya: ");
        String password = in.nextLine();

        System.out.println("Intentant fer login amb usuari: " + usuari);
        // Aquí podries afegir la lògica d'autenticació
    }

    
}
