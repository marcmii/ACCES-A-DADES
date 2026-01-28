package projecte.view;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import projecte.model.Reserva;

public class View {

    private Scanner in = new Scanner(System.in);

    public int menu() {
        System.out.println("\n--- MENÚ RESERVES ---");
        System.out.println("1. Afegir reserva");
        System.out.println("2. Eliminar reserva");
        System.out.println("3. Llistar totes");
        System.out.println("4. Cercar per client");
        System.out.println("0. Sortir");
        System.out.print("Opció: ");
        return in.nextInt();
    }

    public void mostrarReserves(List<Reserva> reserves) {
        for (Reserva r : reserves) {
            System.out.println(r);
        }
    }

    public String demanarNomClient() {
        in.nextLine();
        System.out.print("Nom del client: ");
        return in.nextLine();
    }

    public String demanarId() {
        in.nextLine();
        System.out.print("ID de la reserva: ");
        return in.nextLine();
    }

    public Reserva demanarReserva() {
        in.nextLine();

        System.out.print("Nom client: ");
        String client = in.nextLine();

        System.out.print("Nom restaurant: ");
        String restaurant = in.nextLine();

        System.out.print("Nombre persones: ");
        int persones = in.nextInt();

        return new Reserva(new Date(), client, restaurant, persones, "PENDENT");
    }
}
