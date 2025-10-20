package view;

import java.util.Scanner;
import model.User;

public class PenjatView {
    
    
    public void mostrarMenuPrincipal() {
        System.out.println("-------------");
        System.out.println("    MENU");
        System.out.println("-------------");
        System.out.println("0 - Sortir");
        System.out.println("1 - Login");
        System.out.println("2 - Registrar-se");
        System.out.print("--> ");
    }

     public void mostrarMenuUsuari(Scanner in, User usuari) {
        int opcio;

        do {
            System.out.println("\n--- MENÚ D'USUARI ---");
            System.out.println("1. Afegir paraules" + (usuari.isAdmin() ? "" : " (només per administradors)"));
            System.out.println("2. Jugar");
            System.out.println("0. Tancar sessió");
            System.out.print("Escull una opció: ");
            opcio = in.nextInt();
            in.nextLine();

            switch (opcio) {
                case 1:
                    if (usuari.isAdmin()) {
                        //afegirParaules(in);
                    } else {
                        System.out.println("❌ No tens permís per fer això.");
                    }
                    break;
                case 2:
                    //jugar(in, usuari);
                    break;
                case 0:
                    System.out.println("Sessió tancada.");
                    break;
                default:
                    System.out.println("Opció no vàlida.");
            }

        } while (opcio != 0);
    }


}
