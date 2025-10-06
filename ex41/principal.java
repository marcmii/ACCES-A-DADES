package ex41;

import java.io.*;
import java.util.Scanner;

public class principal {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.print("Usuari: ");
        String nom = sc.nextLine();
        System.out.print("Contrasenya: ");
        String pass = sc.nextLine();

        String filename = nom + ".usr";
        File fitxer = new File(filename);

        if (fitxer.exists()) {
            // Si l'usuari ja existeix, el carreguem
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fitxer))) {
                User usuari = (User) ois.readObject();

                if (usuari.pass.equals(pass)) {
                    System.out.println("Accés correcte al sistema");
                } else {
                    System.out.println(" Accés no concedit: La contrasenya no és correcta");
                }

            } catch (Exception e) {
                System.out.println("Error llegint l'usuari: " + e.getMessage());
            }

        } else {
            // Si no existeix, oferim registrar-lo
            System.out.print("No s’ha trobat l’usuari, vols registrar-te? (s/n): ");
            String resposta = sc.nextLine();

            if (resposta.equalsIgnoreCase("s")) {
                User nou = new User(nom, pass);
                try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fitxer))) {
                    oos.writeObject(nou);
                    System.out.println("Usuari registrat correctament!");
                } catch (IOException e) {
                    System.out.println("Error guardant l'usuari: " + e.getMessage());
                }
            } else {
                System.out.println("Operació cancel·lada.");
            }
        }

        sc.close();
    }
}
