package controller;

import java.io.*;
import java.util.Scanner;
import model.User;
import view.PenjatView;

public class Main {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        PenjatView view = new PenjatView();

        int option;

        do {
            view.mostrarMenuPrincipal();
            System.out.print("Escull una opció: ");
            option = in.nextInt();
            in.nextLine();

            if (option == 1) {
                User usuari = login(in);
                if (usuari != null) {
                    // 🔹 Cridem la vista per mostrar el menú d’usuari
                    view.mostrarMenuUsuari(in, usuari);
                }
            } else if (option == 2) {
                register(in);
            } else if (option != 0) {
                System.out.println("Opció no vàlida");
            }

        } while (option != 0);

        System.out.println("Has sortit del programa.");
        in.close();
    }

    // ---------- LOGIN ----------
    private static User login(Scanner in) {
        System.out.print("Nom d'usuari: ");
        String username = in.nextLine();
        System.out.print("Contrasenya: ");
        String password = in.nextLine();

        File fitxer = new File("data/" + username + ".usr");

        if (!fitxer.exists()) {
            System.out.println("No s’ha trobat l’usuari.");
            return null;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fitxer))) {
            User usuari = (User) ois.readObject();

            if (usuari.getPassword().equals(password)) {
                System.out.println("✅ Accés correcte! Benvingut/da, " + usuari.getName());
                System.out.println("Rol: " + (usuari.isAdmin() ? "Administrador" : "Usuari normal"));
                System.out.println("Punts actuals: " + usuari.getPunts());
                return usuari;
            } else {
                System.out.println("❌ Contrasenya incorrecta.");
                return null;
            }

        } catch (Exception e) {
            System.out.println("Error llegint l'usuari: " + e.getMessage());
            return null;
        }
    }

    // ---------- REGISTER ----------
    private static void register(Scanner in) {

        File dataFolder = new File("data");
        if (!dataFolder.exists()) {
            dataFolder.mkdir();
        }

        System.out.print("Nom complet: ");
        String name = in.nextLine();

        System.out.print("Nom d'usuari: ");
        String username = in.nextLine();

        File fitxer = new File(dataFolder, username + ".usr");

        if (fitxer.exists()) {
            System.out.println("Aquest usuari ja existeix!");
            return;
        }

        System.out.print("Contrasenya: ");
        String password = in.nextLine();

        System.out.print("Vols que aquest usuari sigui administrador? (s/n): ");
        String resposta = in.nextLine();
        boolean admin = resposta.equalsIgnoreCase("s");

        int punts = 0;

        User nou = new User(name, username, password, admin, punts);

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fitxer))) {
            oos.writeObject(nou);
            System.out.println("Usuari registrat correctament!");
        } catch (IOException e) {
            System.out.println("Error guardant l’usuari: " + e.getMessage());
        }
    }
}
