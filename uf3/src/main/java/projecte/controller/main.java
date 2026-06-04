package projecte.controller;

import projecte.model.Model;
import projecte.model.Reserva;
import projecte.view.View;

public class main {

    public static void main(String[] args) {

        Model model = new Model();
        View view = new View();

        int opcio;
        do {
            opcio = view.menu();
  
            switch (opcio) {
                case 1:
                    Reserva r = view.demanarReserva();
                    model.add(r);
                    System.out.println("Reserva afegida correctament");
                    break;

                case 2:
                    if (model.deleteById(view.demanarId()))
                        System.out.println("Reserva eliminada");
                    else
                        System.out.println("No s'ha trobat la reserva");
                    break;

                case 3:
                    view.mostrarReserves(model.getAll());
                    break;

                case 4:
                    view.mostrarReserves(
                        model.getByClientNom(view.demanarNomClient())
                    );
                    break;

                case 0:
                    System.out.println("Has sortit del progrma");
                    break;
            }

        } while (opcio != 0);
    }
}
